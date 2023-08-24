package modelo.dao;

import java.util.List;

import modelo.entidades.Ingreso;
import modelo.entidades.Usuario;

public interface IngresoDAO extends GenericDAO<Ingreso, Integer>{
	public List<Ingreso> getTotalizadoPorCategoria(Usuario usuario);
	public List<Ingreso> getTotalizadoPorCategoriaYMes(Usuario usuario, String mes);
}
