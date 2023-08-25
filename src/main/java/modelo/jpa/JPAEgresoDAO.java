package modelo.jpa;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import modelo.dao.EgresoDAO;
import modelo.entidades.Categoria;
import modelo.entidades.Egreso;
import modelo.entidades.Ingreso;
import modelo.entidades.Usuario;

public class JPAEgresoDAO extends JPAGenericDAO<Egreso, Integer> implements EgresoDAO {

	public JPAEgresoDAO() {
		super(Egreso.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Egreso> getEgresosPorCategoria(Usuario usuario) {
		String tipo = "Ingreso";
		String sentencia = "SELECT m FROM Movimiento m WHERE m.propietario = :usuario AND m.tipo_movimiento = :egreso";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("propietario", usuario);
	    query.setParameter("egreso", tipo);
	    
		List<Egreso> egresosPorCategoria = query.getResultList();
	    return egresosPorCategoria;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Egreso> getEgresosPorCategoriaYMes(Usuario usuario, int mes) {
	    List<Egreso> egresosPorCategoria = getEgresosPorCategoria(usuario);
	    List<Egreso> egresosPorMesYCateg = new ArrayList<Egreso>();
	    for (Egreso egreso : egresosPorCategoria) {
	        Date fechaIngreso = (Date) egreso.getFecha();
	        int mesIngreso = fechaIngreso.getMonth();
	        if (mesIngreso == mes) {
	        	egresosPorMesYCateg.add(egreso);
	        }
	    }
	    return egresosPorMesYCateg;
	}



}
