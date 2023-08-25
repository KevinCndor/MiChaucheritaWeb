package modelo.dao;

import java.util.Date;
import java.util.List;

import modelo.entidades.Ingreso;
import modelo.entidades.Usuario;

public interface IngresoDAO extends GenericDAO<Ingreso, Integer>{
	public List<Ingreso> getIngresosPorCategoria(Usuario usuario);
	public List<Ingreso> getIngresosPorCategoriaYMes(Usuario usuario, int mes);
	public List<Ingreso> getIngresosFecha(Usuario usuario,Date fecha);
	public List<Ingreso> getIngresosPorUsuario(Usuario usuario);
}
