package modelo.dao;

import java.util.List;

import modelo.entidades.Categoria;

public interface CategoriaDAO extends GenericDAO<Categoria, Integer>{
	
	public List<Categoria> getCategoriasPorTipo(String tipo);
	
}
