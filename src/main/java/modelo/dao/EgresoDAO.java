package modelo.dao;

import java.util.Date;
import java.util.List;

import modelo.entidades.Egreso;
import modelo.entidades.Usuario;

public interface EgresoDAO extends GenericDAO<Egreso, Integer>{
	public List<Egreso> getEgresosPorCategoria(Usuario usuario);
	public List<Egreso> getEgresosPorCategoriaYMes(Usuario usuario, int mes);
	public List<Egreso> getEgresosFecha(Usuario usuario,Date fecha);
	public List<Egreso> getEgresosPorUsuario(Usuario usuario);
}
