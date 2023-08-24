package modelo.jpa;

import java.util.List;

import modelo.dao.IngresoDAO;
import modelo.entidades.Ingreso;
import modelo.entidades.Usuario;

public class JPAIngresoDAO extends JPAGenericDAO<Ingreso, Integer> implements IngresoDAO {

	public JPAIngresoDAO() {
		super(Ingreso.class);
	}

	@Override
	public List<Ingreso> getTotalizadoPorCategoria(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ingreso> getTotalizadoPorCategoriaYMes(Usuario usuario, String mes) {
		// TODO Auto-generated method stub
		return null;
	}

}
