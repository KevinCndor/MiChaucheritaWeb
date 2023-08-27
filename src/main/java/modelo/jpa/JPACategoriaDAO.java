package modelo.jpa;

import java.util.List;

import javax.persistence.Query;

import modelo.dao.CategoriaDAO;
import modelo.entidades.Categoria;

public class JPACategoriaDAO extends JPAGenericDAO<Categoria, Integer> implements CategoriaDAO{

	public JPACategoriaDAO() {
		super(Categoria.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> getCategoriasPorTipo(String tipo) {
		String sentencia = "SELECT c FROM Categoria c WHERE c.tipo = :tipo";

		Query query = em.createQuery(sentencia);
	
		query.setParameter("tipo", tipo);

		List<Categoria> categoriasPorTipo = query.getResultList();
		
		return categoriasPorTipo;
	}
	
}
