package modelo.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import modelo.dao.EgresoDAO;
import modelo.entidades.Egreso;
import modelo.entidades.Movimiento;
import modelo.entidades.Subcategoria;
import modelo.entidades.Usuario;

public class JPAEgresoDAO extends JPAGenericDAO<Egreso, Integer> implements EgresoDAO {

	public JPAEgresoDAO() {
		super(Egreso.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Egreso> getEgresosPorSubcategoria(Usuario usuario) {
		String sentencia = "SELECT e.subcategoria, e, SUM(e.valor) as total_subcategoria "
							+ "FROM Egreso e "
							+ "JOIN Cuenta c ON c.NUMEROCUENTA = e.cuenta "
							+ "WHERE c.propietario = :propietario "
							+ "GROUP BY e.subcategoria ";
		Query query = em.createQuery(sentencia);

		query.setParameter("propietario", usuario);

		List<Object[]> resultados = query.getResultList();
		List<Egreso> egresosPorSubcategoria = new ArrayList<>();

		for (Object[] resultado : resultados) {
		    Subcategoria subcategoria = (Subcategoria) resultado[0];
		    Movimiento movimiento = (Movimiento) resultado[1];
		    double totalSubcategoria = ((Number) resultado[2]).doubleValue();
		    Egreso egreso = new Egreso(movimiento.getDescripcion(), movimiento.getFecha(),
		                               movimiento.getValor(), movimiento.getCuenta(), subcategoria.getCategoriaPadre(), 
		                               subcategoria);
		    subcategoria.setValor(totalSubcategoria);
		    egresosPorSubcategoria.add(egreso);
		}
		return egresosPorSubcategoria;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Egreso> getEgresosPorCategoria(Usuario usuario) {
		String sentencia = "SELECT e, SUM(e.valor) as total_subcategoria "
                + "FROM Egreso e "
                + "JOIN FETCH e.categoria "
                + "JOIN FETCH e.subcategoria "
                + "JOIN Cuenta c ON c.NUMEROCUENTA = e.cuenta "
                + "WHERE c.propietario = :propietario "
                + "GROUP BY e.categoria";

		Query query = em.createQuery(sentencia);
		query.setParameter("propietario", usuario);

		List<Object[]> resultados = query.getResultList();
		List<Egreso> egresosPorCategoria = new ArrayList<>();

		for (Object[] resultado : resultados) {
		    Egreso movimiento = (Egreso) resultado[0];
		    double totalCategoria = ((Number) resultado[1]).doubleValue();
		    Egreso egreso = new Egreso(movimiento.getDescripcion(), movimiento.getFecha(),
		    							movimiento.getValor(), movimiento.getCuenta(), movimiento.getCategoria(), 
		    							movimiento.getSubcategoria());
		    movimiento.getCategoria().setValor(totalCategoria);
		    egresosPorCategoria.add(egreso);
		}
		return egresosPorCategoria;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<Egreso> getEgresosPorCategoriaYMes(Usuario usuario, int mes) {
	    List<Egreso> egresosPorCategoria = getEgresosPorCategoria(usuario);
	    List<Egreso> egresosPorMesYCateg = new ArrayList<Egreso>();
	    for (Egreso egreso : egresosPorCategoria) {
	        int mesEgreso = egreso.getFecha().getMonth();
	        if (mesEgreso == mes) {
	        	egresosPorMesYCateg.add(egreso);
	        }
	    }
	    return egresosPorMesYCateg;
	}



	@SuppressWarnings("deprecation")
	@Override
	public List<Egreso> getEgresosPorSubCatYMes(Usuario usuario, int mes) {
		List<Egreso> egresosPorSubcategoria = getEgresosPorSubcategoria(usuario);
	    List<Egreso> egresosPorMesYSubcateg = new ArrayList<Egreso>();
	    for (Egreso egreso : egresosPorSubcategoria) {
	    	int mesEgreso = egreso.getFecha().getMonth();
	        if (mesEgreso == mes) {
	        	egresosPorMesYSubcateg.add(egreso);
	        }
	    }
	    return egresosPorMesYSubcateg;
	}
}
