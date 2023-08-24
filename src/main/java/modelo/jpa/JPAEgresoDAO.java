package modelo.jpa;

import java.util.List;

import modelo.dao.EgresoDAO;
import modelo.entidades.Egreso;
import modelo.entidades.Usuario;

public class JPAEgresoDAO extends JPAGenericDAO<Egreso, Integer> implements EgresoDAO {

	public JPAEgresoDAO() {
		super(Egreso.class);
	}

	@Override
	public List<Egreso> getTotalizadoPorCategoria(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Egreso> getTotalizadoPorCategoriaYMes(Usuario usuario, String mes) {
		// TODO Auto-generated method stub
		return null;
	}

}
