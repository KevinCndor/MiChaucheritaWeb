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
	public List<Egreso> getTotalizadoPorCategoria(Usuario usuario, Categoria categoria) {
		String sentencia = "SELECT e FROM Egreso e WHERE e.propietario = :usuario AND categoria = :categoria";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("propietario", usuario);
	    query.setParameter("categoria", categoria);
	    
		List<Egreso> egresosPorCategoria = query.getResultList();
	    return egresosPorCategoria;
	}

	@Override
	public List<Egreso> getTotalizadoPorCategoriaYMes(Usuario usuario, Categoria categoria, int mes) {
	    List<Egreso> egresosPorCategoria = getTotalizadoPorCategoria(usuario, categoria);
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
