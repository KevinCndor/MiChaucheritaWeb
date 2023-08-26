package modelo.jpa;

import java.util.List;

import javax.persistence.Query;

import modelo.dao.SubcategoriaDAO;
import modelo.entidades.Categoria;
import modelo.entidades.Subcategoria;

public class JPASubcategoriaDAO extends JPAGenericDAO<Subcategoria, Integer> implements SubcategoriaDAO{

	public JPASubcategoriaDAO() {
		super(Subcategoria.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subcategoria> getSubcategoriasPorCategoria(Categoria categoria) {
		String sentencia = "SELECT c FROM Categoria c WHERE c.categoriaPadre = :categoria";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("categoria", categoria.getId());

	    List<Subcategoria> subcategorias = query.getResultList();
	    return subcategorias;
	}
}
