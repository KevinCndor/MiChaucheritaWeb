package modelo.dao;

import java.util.List;

import modelo.entidades.Categoria;
import modelo.entidades.Subcategoria;

public interface SubcategoriaDAO extends GenericDAO<Subcategoria, Integer>{

	public List<Subcategoria> getSubcategoriasPorCategoria(Categoria categoria);
	public Subcategoria getByName(String nombre);
	
}
