package modelo.jpa;

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
		String sentencia = "SELECT e FROM Egreso e WHERE e.propietario = :usuario";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("propietario", usuario);
	    
		List<Egreso> egresosPorCategoria = query.getResultList();
	    return egresosPorCategoria;
	}

	@Override
	public List<Egreso> getEgresosPorCategoriaYMes(Usuario usuario, int mes) {
	    List<Egreso> egresosPorCategoria = getEgresosPorCategoria(usuario);
	    List<Egreso> egresosPorMesYCateg = new ArrayList<Egreso>();
	    for (Egreso egreso : egresosPorCategoria) {
	        LocalDate fechaIngreso = egreso.getFecha();
	        int mesIngreso = fechaIngreso.getMonthValue();
	        if (mesIngreso == mes) {
	        	egresosPorMesYCateg.add(egreso);
	        }
	    }
	    return egresosPorMesYCateg;
	}



}
