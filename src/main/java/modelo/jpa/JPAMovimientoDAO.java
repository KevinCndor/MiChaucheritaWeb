package modelo.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import modelo.dao.MovimientoDAO;
import modelo.entidades.Cuenta;
import modelo.entidades.Movimiento;

public class JPAMovimientoDAO extends JPAGenericDAO<Movimiento, Integer> implements MovimientoDAO {

	public JPAMovimientoDAO() {
		super(Movimiento.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> getByMonth(int idCuenta, int mes) {
	    String sentencia = "SELECT m FROM Movimiento m WHERE m.cuenta = :idCuenta AND MONTH(m.fecha) = :mes";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("idCuenta", idCuenta);
	    query.setParameter("mes", mes);
	    
		List<Movimiento> movimientosPorMes = query.getResultList();
	    return movimientosPorMes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> getByType(int idCuenta, String tipo) {
		String sentencia = "SELECT m FROM Movimiento m WHERE m.cuenta = :idCuenta AND m.tipo_movimiento = :tipo";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("idCuenta", idCuenta);
	    query.setParameter("tipo", tipo);
	    
		List<Movimiento> movimientosPorTipo = query.getResultList();
	    return movimientosPorTipo;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Movimiento> getByMothAndType(int idCuenta, int mes, String tipo) {
		List<Movimiento> movimientosPorTipo = getByType(idCuenta, tipo);
		List<Movimiento> movPorTipoYMes = new ArrayList<Movimiento>();
		for (Movimiento movimiento : movimientosPorTipo) {
			int mesMovimiento = movimiento.getFecha().getMonth();
			if( mesMovimiento == mes) {
				movPorTipoYMes.add(movimiento);
			}
		}
		return movPorTipoYMes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> getAllByUser(int idCuenta) {
		String sentencia = "SELECT m FROM Movimiento m WHERE m.propietario = :propietario";
	    Cuenta cuenta = JPADAOFactory.getFactory().getCuentaDAO().getById(idCuenta);
		
		Query query = em.createQuery(sentencia);
	    
	    query.setParameter("propietario", cuenta.getPropietario());
	    
		List<Movimiento> movimientosPorUsuario = query.getResultList();
	    return movimientosPorUsuario;
	}


}
