package modelo.dao;

import java.util.List;

import modelo.entidades.Ingreso;
import modelo.entidades.Usuario;

public interface IngresoDAO extends GenericDAO<Ingreso, Integer>{
	public List<Ingreso> getIngresosPorCategoria(Usuario usuario);
	public List<Ingreso> getIngresosPorCategoriaYMes(Usuario usuario, int mes);

}
