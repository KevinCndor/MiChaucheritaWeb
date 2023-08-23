package modelo.dao;

import modelo.entidades.Usuario;

public interface UsuarioDAO {
	
	public Usuario autorizar(String username, String password);
}
