package modelo.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import modelo.dao.MovimientoDAO;
import modelo.entidades.Movimiento;

public class JPAMovimientoDAO extends JPAGenericDAO<Movimiento, Integer> implements MovimientoDAO {

	public JPAMovimientoDAO() {
		super(Movimiento.class);
	}

	@Override
	public List<Movimiento> getByDate(int idCuenta, Date fechaInicio, Date fechaFin) {
		String sentencia = "SELECT m FROM Movimiento m WHERE m.cuenta = :idCuenta AND m.fecha BETWEEN :fechaInicio AND :fechaFin";
		Query query = em.createQuery(sentencia);
		
		// Parametros ...
		query.setParameter("idCuenta", idCuenta);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
	
		List<Movimiento> movimientosxFecha = query.getResultList();
		return movimientosxFecha;
	}

}
