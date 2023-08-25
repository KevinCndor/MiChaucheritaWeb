package modelo.jpa;

import java.sql.Date;
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
		String tipo = "Ingreso";
		String sentencia = "SELECT m FROM Movimiento m WHERE m.propietario = :usuario AND m.tipo_movimiento = :ingreso";
	    Query query = em.createQuery(sentencia);
	    
	    query.setParameter("propietario", usuario);
	    query.setParameter("ingreso", tipo);
	    
		List<Ingreso> ingresosPorCategoria = query.getResultList();
	    return ingresosPorCategoria;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Ingreso> getIngresosPorCategoriaYMes(Usuario usuario, int mes) {
	    List<Ingreso> ingresosPorCategoria = getIngresosPorCategoria(usuario);
	    List<Ingreso> ingresosPorMesYCateg = new ArrayList<Ingreso>();
	    for (Ingreso ingreso : ingresosPorCategoria) {
	        Date fechaIngreso = (Date) ingreso.getFecha();
	        int mesIngreso = fechaIngreso.getMonth();
	        if (mesIngreso == mes) {
	            ingresosPorMesYCateg.add(ingreso);
	        }
	    }
	    return ingresosPorMesYCateg;
	}



}
