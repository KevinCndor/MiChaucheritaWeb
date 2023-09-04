package modelo.rest;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import modelo.dao.DAOFactory;
import modelo.entidades.Categoria;
import modelo.entidades.Cuenta;
import modelo.entidades.Egreso;
import modelo.entidades.Ingreso;
import modelo.entidades.Movimiento;
import modelo.entidades.Transferencia;
import modelo.entidades.Usuario;

import javax.persistence.Query;
import javax.websocket.server.PathParam;

@Path("/movimiento") 
public class MovimientoRecurso  {

    @GET
    @Path("/filtromes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movimiento> getByMonth(@QueryParam("idCuenta") String idCuenta , @QueryParam("mes") int mes) {
    	
        	return DAOFactory.getFactory().getMovimientoDAO().getByMonth(idCuenta, mes);
    }
	

    @GET
    @Path("/filtrotipo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movimiento> getByType(@QueryParam("idCuenta") String idCuenta , @QueryParam("tipo") String tipo) {
    	
    	return DAOFactory.getFactory().getMovimientoDAO().getByType(idCuenta, tipo);
    }
    
    @GET
    @Path("/movimientosusuario")
    @Produces(MediaType.APPLICATION_JSON)
	public List<Movimiento> getByIdUsuario(@QueryParam("idCuenta")  String idCuenta) {
    	return DAOFactory.getFactory().getMovimientoDAO().getByIdUsuario(idCuenta);
	
	}
    
    @POST
    @Path("/crearingreso")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void crearIngreso(Ingreso i) {
        Cuenta c = i.getCuenta();
        Cuenta cAct = DAOFactory.getFactory().getCuentaDAO().getByNumCuenta(c.getNumeroCuenta());
        cAct.setSaldo(i.getValor()+cAct.getSaldo());
        DAOFactory.getFactory().getCuentaDAO().update(cAct);
        
        DAOFactory.getFactory().getIngresoDAO().create(new Ingreso(i.getDescripcion(),i.getTipoMovimiento(),i.getFecha(),i.getValor(),cAct,i.getCategoria()));
    }
    
    @POST
    @Path("/crearegreso")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void crearEgreso(Egreso e) {
    	Cuenta c = e.getCuenta();
        Cuenta cAct = DAOFactory.getFactory().getCuentaDAO().getByNumCuenta(c.getNumeroCuenta());
        cAct.setSaldo(e.getValor()-cAct.getSaldo());
        DAOFactory.getFactory().getCuentaDAO().update(cAct);
        DAOFactory.getFactory().getEgresoDAO().create(new Egreso(e.getDescripcion(),e.getFecha(),e.getValor(),cAct,e.getCategoria(),e.getSubcategoria(),e.getTipoMovimiento()));
    }
    
    @POST
    @Path("/creartransferencia")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void crearTransferencia(Transferencia nuevaTransferencia) {
        // Guarda la nuevaTransferencia en la base de datos (si es necesario)
        DAOFactory.getFactory().getTransferenciaDAO().create(nuevaTransferencia);

        Cuenta cuentaOrigenAct = nuevaTransferencia.getCuenta();
        Cuenta cuentaDestinoAct = nuevaTransferencia.getCuentaDestino();
        
        // Actualiza la cuenta de origen restando el valor
        Cuenta cuentaOrigenActualizar = new Cuenta(
            cuentaOrigenAct.getNumeroCuenta(),
            cuentaOrigenAct.getNombre(),
            cuentaOrigenAct.getSaldo() - nuevaTransferencia.getValor(),
            cuentaOrigenAct.getPropietario()
        );
        DAOFactory.getFactory().getCuentaDAO().update(cuentaOrigenActualizar);
        
        // Actualiza la cuenta de destino sumando el valor
        Cuenta cuentaDestinoActualizar = new Cuenta(
            cuentaDestinoAct.getNumeroCuenta(),
            cuentaDestinoAct.getNombre(),
            cuentaDestinoAct.getSaldo() + nuevaTransferencia.getValor(),
            cuentaDestinoAct.getPropietario()
        );
        DAOFactory.getFactory().getCuentaDAO().update(cuentaDestinoActualizar);
    }

    
}