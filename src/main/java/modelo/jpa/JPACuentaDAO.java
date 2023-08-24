package modelo.jpa;

import java.util.List;

import modelo.dao.CuentaDAO;
import modelo.entidades.Cuenta;
import modelo.entidades.Usuario;

public class JPACuentaDAO extends JPAGenericDAO<Cuenta, Integer> implements CuentaDAO {

	public JPACuentaDAO() {
		super(Cuenta.class);
	}

	@Override
	public List<Cuenta> getCuentasUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuenta getByName(String nombre, Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
