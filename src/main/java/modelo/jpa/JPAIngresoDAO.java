package modelo.jpa;

import modelo.dao.IngresoDAO;
import modelo.entidades.Ingreso;

public class JPAIngresoDAO extends JPAGenericDAO<Ingreso, Integer> implements IngresoDAO {

	public JPAIngresoDAO() {
		super(Ingreso.class);
	}

}
