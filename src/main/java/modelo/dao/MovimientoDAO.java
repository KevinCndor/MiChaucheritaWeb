package modelo.dao;

import java.util.List;

import modelo.entidades.Movimiento;
import modelo.entidades.Usuario;

public interface MovimientoDAO extends GenericDAO<Movimiento, Integer>{
	
	
	public List<Movimiento> getByMonth(String idCuenta, int mes);
	public List<Movimiento> getByType(String idCuenta, String tipo);
	public List<Movimiento> getByMothAndType(String idCuenta, int mes, String tipo);
	public List<Movimiento> getByCuenta(String idCuenta, Usuario usuario);
	
	public List<Movimiento> getAllByUser(Usuario usuario);
	public List<Movimiento> getAllByMonth(Usuario usuario, int mes);
	public List<Movimiento> getAllByType(Usuario usuario, String tipo);
	public List<Movimiento> getAllByMonthAndType(Usuario usuario, int mes, String tipo);
	public List<Movimiento> getByIdUsuario(String idCuenta);
}
