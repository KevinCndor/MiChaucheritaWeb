package modelo.jpa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import modelo.dao.IngresoDAO;
import modelo.entidades.Categoria;
import modelo.entidades.Ingreso;
import modelo.entidades.Usuario;

public class JPAIngresoDAO extends JPAGenericDAO<Ingreso, Integer> implements IngresoDAO {

	public JPAIngresoDAO() {
		super(Ingreso.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingreso> getIngresosPorCategoria(Usuario usuario) {
		String sentencia = "SELECT i FROM Ingreso i WHERE i.propietario = :usuario";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("propietario", usuario);
	    
		List<Ingreso> ingresosPorCategoria = query.getResultList();
	    return ingresosPorCategoria;
	}

	@Override
	public List<Ingreso> getIngresosPorCategoriaYMes(Usuario usuario, int mes) {
	    List<Ingreso> ingresosPorCategoria = getIngresosPorCategoria(usuario);
	    List<Ingreso> ingresosPorMesYCateg = new ArrayList<Ingreso>();
	    for (Ingreso ingreso : ingresosPorCategoria) {
	        LocalDate fechaIngreso = ingreso.getFecha();
	        int mesIngreso = fechaIngreso.getMonthValue();
	        if (mesIngreso == mes) {
	            ingresosPorMesYCateg.add(ingreso);
	        }
	    }
	    return ingresosPorMesYCateg;
	}



}
