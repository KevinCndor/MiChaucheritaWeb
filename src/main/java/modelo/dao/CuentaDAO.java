package modelo.dao;

import java.util.List;

import modelo.entidades.Cuenta;
import modelo.entidades.Usuario;

public interface CuentaDAO extends GenericDAO<Cuenta, Integer>{
	public List<Cuenta> getTotalizadoPorUsuario(Usuario usuario);
	public Cuenta getByName(String nombre, Usuario usuario);
}
