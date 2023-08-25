package modelo.jpa;

import java.util.List;

import javax.persistence.Query;

import modelo.dao.MovimientoDAO;
import modelo.entidades.Movimiento;

public class JPAMovimientoDAO extends JPAGenericDAO<Movimiento, Integer> implements MovimientoDAO {

	public JPAMovimientoDAO() {
		super(Movimiento.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> getByMonth(int idCuenta, int mes) {
	    String sentencia = "SELECT m FROM Movimiento m WHERE m.cuenta = :idCuenta ";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("idCuenta", idCuenta);
	    query.setParameter("mes", mes);
	    
		List<Movimiento> movimientosPorMes = query.getResultList();
	    return movimientosPorMes;
	}


}
