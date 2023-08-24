package modelo.dao;

import java.util.Date;
import java.util.List;

import modelo.entidades.Movimiento;

public interface MovimientoDAO extends GenericDAO<Movimiento, Integer>{
	
	public List<Movimiento> getByDate(int idCuenta, Date fechaInicio, Date fechaFin);
	
}
