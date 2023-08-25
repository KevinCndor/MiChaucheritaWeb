package modelo.dao;

import java.util.List;

import modelo.entidades.Categoria;
import modelo.entidades.Egreso;
import modelo.entidades.Usuario;

public interface EgresoDAO extends GenericDAO<Egreso, Integer>{
	public List<Egreso> getTotalizadoPorCategoria(Usuario usuario, Categoria categoria);
	public List<Egreso> getTotalizadoPorCategoriaYMes(Usuario usuario, Categoria categoria, int mes);
}
