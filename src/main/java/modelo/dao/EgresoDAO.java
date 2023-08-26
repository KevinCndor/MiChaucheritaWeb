package modelo.dao;

import java.util.List;

import modelo.entidades.Egreso;
import modelo.entidades.Usuario;

public interface EgresoDAO extends GenericDAO<Egreso, Integer>{
	public List<Egreso> getEgresosPorSubcategoria(Usuario usuario);
	public List<Egreso> getEgresosPorCategoria(Usuario usuario);
	public List<Egreso> getEgresosPorCategoriaYMes(Usuario usuario, int mes);
	public List<Egreso> getEgresosPorSubCatYMes(Usuario usuario,int mes);
}
