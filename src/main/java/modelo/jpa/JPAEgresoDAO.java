package modelo.jpa;

import modelo.dao.EgresoDAO;
import modelo.entidades.Egreso;

public class JPAEgresoDAO extends JPAGenericDAO<Egreso, Integer> implements EgresoDAO {

	public JPAEgresoDAO() {
		super(Egreso.class);
	}

}
