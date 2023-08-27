package modelo.dao;

import java.util.List;

import modelo.entidades.Cuenta;
import modelo.entidades.Usuario;

public interface CuentaDAO extends GenericDAO<Cuenta, String>{
	
	public List<Cuenta> getCuentasUsuario(Usuario usuario);
	public Cuenta getPorNombreYUsuario(String nombreCuenta, Usuario usuario);
	public Cuenta getByNumCuenta(String idCuenta);

}
