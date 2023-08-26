package modelo.jpa;

import java.util.List;

import javax.persistence.Query;

import modelo.dao.CuentaDAO;
import modelo.entidades.Cuenta;
import modelo.entidades.Usuario;

public class JPACuentaDAO extends JPAGenericDAO<Cuenta, Integer> implements CuentaDAO {

	public JPACuentaDAO() {
		super(Cuenta.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cuenta> getCuentasUsuario(Usuario usuario) {
		String sentencia = "SELECT c FROM Cuenta c WHERE c.propietario = :usuario;";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("usuario", usuario.getId());
	    
		List<Cuenta> cuentasPorUsuario = query.getResultList();
	    return cuentasPorUsuario;
	}

	@Override
	public Cuenta getPorIdYUsuario(int idCuenta, Usuario usuario) {
		String sentencia = "SELECT c FROM Cuenta c WHERE c.propietario = :usuario and c.numeroCuenta = :idCuenta";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("usuario", usuario.getId());
	    query.setParameter("idCuenta", idCuenta);
	    
		Cuenta cuenta = (Cuenta) query.getSingleResult();
	    return cuenta;
	}

}
