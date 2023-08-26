package modelo.jpa;


import java.util.List;

import javax.persistence.Query;

import modelo.dao.TransferenciaDAO;
import modelo.entidades.Transferencia;
import modelo.entidades.Usuario;

public class JPATransferenciaDAO extends JPAGenericDAO<Transferencia, Integer> implements TransferenciaDAO {

	public JPATransferenciaDAO() {
		super(Transferencia.class);
	}

	@Override
	public List<Transferencia> getTransferenciasPorUsuario(Usuario usuario) {
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transferencia> getTransferenciasPorMes(Usuario usuario, int mes) {
		String tipo = "Transferencia";
		String sentencia = "SELECT m FROM Movimiento m "
								+ "JOIN Cuenta c ON c.NUMEROCUENTA = m.cuenta "
								+ "WHERE c.propietario = :propietario AND m.tipo_movimiento = :tipo "
								+ "AND MONTH(m.fecha) = :mes;";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("propietario", usuario.getId());
	    query.setParameter("tipo", tipo);
	    query.setParameter("mes", mes);
	    
		List<Transferencia> transferenciasPorMes = query.getResultList();
	    return transferenciasPorMes;
	}

}
