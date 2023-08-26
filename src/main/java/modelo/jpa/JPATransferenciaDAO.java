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
		String sentencia = "SELECT t FROM Transferencia t "
								+ "JOIN Cuenta c ON c.NUMEROCUENTA = t.cuenta "
								+ "WHERE c.propietario = :propietario "
								+ "AND MONTH(t.fecha) = :mes";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("propietario", usuario);
	    query.setParameter("mes", mes);
	    
		List<Transferencia> transferenciasPorMes = query.getResultList();
	    return transferenciasPorMes;
	}

}
