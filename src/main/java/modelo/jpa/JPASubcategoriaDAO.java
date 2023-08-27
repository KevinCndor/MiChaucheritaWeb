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
		String sentencia = "SELECT s FROM Subcategoria s WHERE s.categoriaPadre = :categoria";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("categoria", categoria);

	    List<Subcategoria> subcategorias = query.getResultList();
	    return subcategorias;
	}

	@Override
	public Subcategoria getByName(String nombre) {
		String sentencia = "SELECT s FROM Subcategoria s WHERE s.nombre = :nombre";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("nombre", nombre);

	    Subcategoria subcategorias = (Subcategoria) query.getSingleResult();
	    return subcategorias;
	}
}
