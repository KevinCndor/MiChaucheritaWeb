package modelo.dao;

import java.util.List;

import modelo.entidades.Movimiento;

public interface MovimientoDAO extends GenericDAO<Movimiento, Integer>{
	
	
	public List<Movimiento> getByMonth(int idCuenta, int mes);
	public List<Movimiento> getByType(int idCuenta, String tipo);
	public List<Movimiento> getByMothAndType(int idCuenta, int mes, String tipo);
	public List<Movimiento> getAllByUser(int idCuenta);
	
}
