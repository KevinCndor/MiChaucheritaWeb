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
		String tipoCategoria = "Categoria";
		String sentencia = "SELECT c FROM Categoria c WHERE c.tipo = :tipo AND c.tipoSubcategoria = :tipoCategoria";

		Query query = em.createQuery(sentencia);
	
		query.setParameter("tipo", tipo);
		query.setParameter("tipoCategoria", tipoCategoria);

		List<Categoria> categoriasPorTipo = query.getResultList();
		
		return categoriasPorTipo;
	}
	
}
