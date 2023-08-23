package modelo.jpa;

import modelo.dao.TransferenciaDAO;
import modelo.entidades.Transferencia;

public class JPATransferenciaDAO extends JPAGenericDAO<Transferencia, Integer> implements TransferenciaDAO {

	public JPATransferenciaDAO() {
		super(Transferencia.class);
	}

}
