package modelo.dao;

import java.util.List;

import modelo.entidades.Cuenta;
import modelo.entidades.Usuario;

public interface CuentaDAO extends GenericDAO<Cuenta, Integer>{
	
	public List<Cuenta> getCuentasUsuario(Usuario usuario);
	public Cuenta getPorNombreYUsuario(String nombreCuenta, Usuario usuario);

}
