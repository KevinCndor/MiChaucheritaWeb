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
	public List<Ingreso> getTotalizadoPorCategoria(Usuario usuario, Categoria categoria) {
		String sentencia = "SELECT i FROM Ingreso i WHERE i.propietario = :usuario AND categoria = :categoria";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("propietario", usuario);
	    query.setParameter("categoria", categoria);
	    
		List<Ingreso> ingresosPorCategoria = query.getResultList();
	    return ingresosPorCategoria;
	}

	@Override
	public List<Ingreso> getTotalizadoPorCategoriaYMes(Usuario usuario, Categoria categoria, int mes) {
	    List<Ingreso> ingresosPorCategoria = getTotalizadoPorCategoria(usuario, categoria);
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
