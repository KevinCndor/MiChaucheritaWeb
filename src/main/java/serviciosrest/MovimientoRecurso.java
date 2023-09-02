package serviciosrest;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.dao.DAOFactory;
import modelo.entidades.Movimiento;
import modelo.entidades.Usuario;
import javax.websocket.server.PathParam;

@Path("/movimiento") 
public class MovimientoRecurso {

    @GET
    @Path("/{idCuenta}/{mes}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movimiento> getByMonth(@PathParam("idCuenta") String idCuenta , @PathParam("mes") int mes) {
    	
        	return DAOFactory.getFactory().getMovimientoDAO().getByMonth(idCuenta, mes);
    }
	
   
    @GET
    @Path("/leer") 
    @Produces(MediaType.APPLICATION_JSON)
	public  List<Movimiento> getAll(){
		return DAOFactory.getFactory().getMovimientoDAO().getAll();
    }
	

    @GET
    @Path("/{idCuenta}/{tipo}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movimiento> getByType(@PathParam("idCuenta") String idCuenta , @PathParam("tipo") String tipo) {
    	
    	return DAOFactory.getFactory().getMovimientoDAO().getByType(idCuenta, tipo);
    }
    
    
    @GET
    @Path("/{idCuenta}/{mes}/{tipo}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movimiento> getByMothAndType(@PathParam("idCuenta") String idCuenta, @PathParam("mes") int mes, @PathParam("tipo") String tipo) {
    	return DAOFactory.getFactory().getMovimientoDAO().getByMothAndType(idCuenta, mes, tipo);
	}

    
    @GET
    @Path("/{usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movimiento> getAllByUser(@PathParam("usuario") Usuario usuario) {
    	return DAOFactory.getFactory().getMovimientoDAO().getAllByUser(usuario);
	}

    @GET
    @Path("/{idCuenta}/{usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movimiento> getByCuenta(@PathParam("idCuenta")  String idCuenta,@PathParam("usuario")  Usuario usuario) {
    	return DAOFactory.getFactory().getMovimientoDAO().getByCuenta(idCuenta, usuario);
	}
    
    
    @GET
    @Path("/{usuario}/{mes}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movimiento> getAllByMonth(@PathParam("usuario") Usuario usuario,@PathParam("mes") int mes) {
    	return DAOFactory.getFactory().getMovimientoDAO().getAllByMonth(usuario, mes);
	
    }
    
    @GET
    @Path("/{usuario}/{tipo}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<Movimiento> getAllByType(@PathParam("usuario") Usuario usuario, @PathParam("tipo") String tipo) {
    	return DAOFactory.getFactory().getMovimientoDAO().getAllByType(usuario, tipo);
	}
    
    @GET
    @Path("/{usuario}/{mes}/{tipo}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<Movimiento> getAllByMonthAndType( @PathParam("usuario")  Usuario usuario, @PathParam("mes")  int mes, @PathParam("tipo") String tipo) {
    	return DAOFactory.getFactory().getMovimientoDAO().getAllByMonthAndType(usuario, mes, tipo);
	}
    
   
}