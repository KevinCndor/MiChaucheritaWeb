package modelo.dao;

import java.util.List;

import modelo.entidades.Movimiento;

public interface MovimientoDAO extends GenericDAO<Movimiento, Integer>{
	
	public List<Movimiento> getByMonth(int idCuenta, int mes);
	
}
