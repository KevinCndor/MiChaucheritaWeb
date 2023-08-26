package modelo.dao;

import java.util.List;

import modelo.entidades.Transferencia;
import modelo.entidades.Usuario;

public interface TransferenciaDAO extends GenericDAO<Transferencia, Integer>{
	public List<Transferencia> getTransferenciasPorMes(Usuario usuario, int mes);
	public List<Transferencia> getTransferenciasPorUsuario(Usuario usuario);
}
