package modelo.jpa;

import javax.persistence.Query;

import modelo.dao.UsuarioDAO;
import modelo.entidades.Usuario;

public class JPAUsuarioDAO extends JPAGenericDAO<Usuario, Integer> implements UsuarioDAO {

	public JPAUsuarioDAO() {
		super(Usuario.class);
	}

	@Override
	public Usuario getByName(String username) {
		
		String sentencia = "SELECT u FROM Usuario u WHERE u.username = :username";
		Query query = em.createQuery(sentencia);

		query.setParameter("username", username);

		Usuario persona = (Usuario) query.getSingleResult();

		return persona;
	}

}
