package modelo.jpa;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Query;

import modelo.dao.IngresoDAO;
import modelo.entidades.Ingreso;
import modelo.entidades.Usuario;

public class JPAIngresoDAO extends JPAGenericDAO<Ingreso, Integer> implements IngresoDAO {

	public JPAIngresoDAO() {
		super(Ingreso.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingreso> getIngresosPorCategoria(Usuario usuario) {
		String sentencia = "SELECT i, SUM(i.valor) as total_categoria "
							+ "FROM Ingreso i JOIN Cuenta c ON c.NUMEROCUENTA = i.cuenta "
							+ "WHERE c.propietario = :propietario "
							+ "GROUP BY i.categoria";
	
		Query query = em.createQuery(sentencia);

		query.setParameter("propietario", usuario);

		List<Object[]> resultados = query.getResultList();
		List<Ingreso> ingresosPorCategoria = new ArrayList<>();

		for (Object[] resultado : resultados) {
		    Ingreso movimiento = (Ingreso) resultado[0];
		    double totalCategoria = ((Number) resultado[1]).doubleValue();
		    Ingreso ingreso = new Ingreso(movimiento.getDescripcion(), movimiento.getTipoMovimiento(), movimiento.getFecha(),
		    							movimiento.getValor(), movimiento.getCuenta(), movimiento.getCategoria());
		    movimiento.getCategoria().setValor(totalCategoria);
		    ingresosPorCategoria.add(ingreso);
		}
		return ingresosPorCategoria;
	}
	
	@Override
	public List<Ingreso> getIngresosPorCategoriaYMes(Usuario usuario, int mes) {
	    List<Ingreso> ingresosPorCategoria = getIngresosPorCategoria(usuario);
	    List<Ingreso> ingresosPorMesYCateg = new ArrayList<Ingreso>();
	    
	    for (Ingreso ingreso : ingresosPorCategoria) {
	        Date fechaIngreso =  ingreso.getFecha();  
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(fechaIngreso);
	        int mesIngreso = calendar.get(Calendar.MONTH);

	        if (mesIngreso == mes) {
	            ingresosPorMesYCateg.add(ingreso);
	        }
	    }   
	    return ingresosPorMesYCateg;
	}

	
	
	
	
	
	
	
	
	
}
