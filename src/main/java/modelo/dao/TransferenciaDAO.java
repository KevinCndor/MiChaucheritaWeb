package modelo.dao;

import java.util.Date;
import java.util.List;

import modelo.entidades.Transferencia;
import modelo.entidades.Usuario;

public interface TransferenciaDAO extends GenericDAO<Transferencia, Integer>{
	public List<Transferencia> getTransferenciasFecha(Usuario usuario,Date fecha);
	public List<Transferencia> getTransferenciasPorUsuario(Usuario usuario);
}
