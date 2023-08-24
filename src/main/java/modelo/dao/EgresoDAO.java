package modelo.dao;

import java.util.List;

import modelo.entidades.Egreso;
import modelo.entidades.Usuario;

public interface EgresoDAO extends GenericDAO<Egreso, Integer>{
	public List<Egreso> getTotalizadoPorCategoria(Usuario usuario);
	public List<Egreso> getTotalizadoPorCategoriaYMes(Usuario usuario, String mes);
}
