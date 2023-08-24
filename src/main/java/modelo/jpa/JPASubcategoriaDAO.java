package modelo.jpa;

import modelo.dao.SubcategoriaDAO;
import modelo.entidades.Subcategoria;

public class JPASubcategoriaDAO extends JPAGenericDAO<Subcategoria, Integer> implements SubcategoriaDAO{

	public JPASubcategoriaDAO() {
		super(Subcategoria.class);
	}

}
