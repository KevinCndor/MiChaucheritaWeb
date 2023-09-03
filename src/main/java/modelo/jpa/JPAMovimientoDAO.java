package modelo.jpa;

import java.util.List;

import javax.persistence.Query;

import modelo.dao.DAOFactory;
import modelo.dao.MovimientoDAO;
import modelo.entidades.Cuenta;
import modelo.entidades.Movimiento;
import modelo.entidades.Usuario;

public class JPAMovimientoDAO extends JPAGenericDAO<Movimiento, Integer> implements MovimientoDAO {

	public JPAMovimientoDAO() {
		super(Movimiento.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> getByMonth(String idCuenta, int mes) {
	    String sentencia = "SELECT DISTINCT m FROM Movimiento m WHERE m.cuenta = :idCuenta AND FUNCTION('MONTH', m.fecha) = :mes";
	    Query query = em.createQuery(sentencia);
	    Cuenta cuenta = DAOFactory.getFactory().getCuentaDAO().getById(idCuenta);
	    
	    query.setParameter("idCuenta", cuenta);
	    query.setParameter("mes", mes+1);
	    
		List<Movimiento> movimientosPorMes = query.getResultList();
	    return movimientosPorMes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> getByType(String idCuenta, String tipo) {
		String sentencia = "SELECT DISTINCT m FROM Movimiento m WHERE m.cuenta = :cuenta AND m.tipoMovimiento = :tipo";
	    Query query = em.createQuery(sentencia);
	    Cuenta cuenta = DAOFactory.getFactory().getCuentaDAO().getById(idCuenta);
	    query.setParameter("cuenta", cuenta);
	    query.setParameter("tipo", tipo);
	    
		List<Movimiento> movimientosPorTipo = query.getResultList();
	    return movimientosPorTipo;
	}

	@SuppressWarnings({"unchecked" })
	@Override
	public List<Movimiento> getByMothAndType(String idCuenta, int mes, String tipo) {
		String sentencia = "SELECT DISTINCT m FROM Movimiento m WHERE m.cuenta = :idCuenta AND FUNCTION('MONTH', m.fecha) = :mes AND m.tipoMovimiento = :tipo";
	    Query query = em.createQuery(sentencia);
	    Cuenta cuenta = DAOFactory.getFactory().getCuentaDAO().getById(idCuenta);
	    
	    query.setParameter("idCuenta", cuenta);
	    query.setParameter("mes", mes+1);
	    query.setParameter("tipo", tipo);
	    
		List<Movimiento> movPorTipoYMes = query.getResultList();
		return movPorTipoYMes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> getAllByUser(Usuario usuario) {
		String sentencia = "SELECT DISTINCT m FROM Movimiento m "
				+ "JOIN Cuenta c ON m.cuenta = c "
				+ "WHERE c.propietario = :propietario";
		
		Query query = em.createQuery(sentencia);
	    
	    query.setParameter("propietario", usuario);
	    
		List<Movimiento> movimientosPorUsuario = query.getResultList();
	    return movimientosPorUsuario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> getByCuenta( String idCuenta, Usuario usuario) {
		String sentencia = "SELECT DISTINCT m FROM Movimiento m JOIN Cuenta c ON m.cuenta = :cuenta WHERE c.propietario = :propietario ";
		
		Cuenta cuenta = DAOFactory.getFactory().getCuentaDAO().getById(idCuenta);
		Query query = em.createQuery(sentencia);
	    
		query.setParameter("cuenta", cuenta);
	    query.setParameter("propietario", usuario);
	    
		List<Movimiento> movimientosPorCuenta = query.getResultList();
	    return movimientosPorCuenta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> getAllByMonth(Usuario usuario, int mes) {
		String sentencia = "SELECT m FROM Movimiento m JOIN m.cuenta c JOIN c.propietario u "
								+ "WHERE u.id = :idUsuario AND FUNCTION('MONTH', m.fecha) = :mes ";

	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("idUsuario", usuario.getId());
	    query.setParameter("mes", mes+1);
	    
		List<Movimiento> movimientosPorMes = query.getResultList();
	    return movimientosPorMes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> getAllByType(Usuario usuario, String tipo) {
		String sentencia = "SELECT m FROM Movimiento m JOIN m.cuenta c JOIN c.propietario u "
                				+ "WHERE u.id = :idUsuario AND m.tipoMovimiento = :tipoMovimiento";


	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("idUsuario", usuario.getId());
	    query.setParameter("tipoMovimiento", tipo);
	    
		List<Movimiento> movimientosPorTipo = query.getResultList();
	    return movimientosPorTipo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> getAllByMonthAndType(Usuario usuario, int mes, String tipo) {
		String sentencia = "SELECT m FROM Movimiento m JOIN m.cuenta c JOIN c.propietario u "
				                + "WHERE u.id = :idUsuario AND m.tipoMovimiento = :tipoMovimiento "
				                + "AND FUNCTION('MONTH', m.fecha) = :mes";


		Query query = em.createQuery(sentencia);
		
		query.setParameter("idUsuario", usuario.getId());
		query.setParameter("tipoMovimiento", tipo);
	    query.setParameter("mes", mes+1);
		
		List<Movimiento> movimientosPorMes = query.getResultList();
		return movimientosPorMes;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> getByIdUsuario( String idCuenta) {
		String sentencia = "SELECT DISTINCT m FROM Movimiento m JOIN Cuenta c ON m.cuenta = :cuenta WHERE c.propietario = :propietario ";
		
		Cuenta cuenta = DAOFactory.getFactory().getCuentaDAO().getById(idCuenta);
		Query query = em.createQuery(sentencia);
	    
		query.setParameter("cuenta", cuenta);
	    query.setParameter("propietario", cuenta.getPropietario());
	    
		List<Movimiento> movimientosPorCuenta = query.getResultList();
	    return movimientosPorCuenta;
	}

	
}
