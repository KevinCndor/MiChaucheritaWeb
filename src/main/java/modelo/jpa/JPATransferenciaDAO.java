package modelo.jpa;

import java.util.Date;
import java.util.List;

import modelo.dao.TransferenciaDAO;
import modelo.entidades.Transferencia;
import modelo.entidades.Usuario;

public class JPATransferenciaDAO extends JPAGenericDAO<Transferencia, Integer> implements TransferenciaDAO {

	public JPATransferenciaDAO() {
		super(Transferencia.class);
	}

	@Override
	public List<Transferencia> getTransferenciasFecha(Usuario usuario, Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transferencia> getTransferenciasPorUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
