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
		String tipo = "Egreso";
		String sentencia = "SELECT m.subcategoria, m, SUM(m.valor) as total_subcategoria "
							+ "FROM Movimiento m "
							+ "JOIN Cuenta c ON c.NUMEROCUENTA = m.cuenta "
							+ "WHERE c.propietario = :propietario AND m.tipo_movimiento = :tipo "
							+ "GROUP BY m.subcategoria;";
		Query query = em.createQuery(sentencia);

		query.setParameter("propietario", usuario.getId());
		query.setParameter("tipo", tipo);

		List<Object[]> resultados = query.getResultList();
		List<Egreso> egresosPorSubcategoria = new ArrayList<>();

		for (Object[] resultado : resultados) {
		    Subcategoria subcategoria = (Subcategoria) resultado[0];
		    Movimiento movimiento = (Movimiento) resultado[1];
		    double totalSubcategoria = (double) resultado[2];
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
		String tipo = "Egreso";
		String sentencia = "SELECT m, SUM(m.valor) as total_subcategoria "
							+ "FROM Movimiento m "
							+ "JOIN Cuenta c ON c.NUMEROCUENTA = m.cuenta "
							+ "WHERE c.propietario = :propietario AND m.tipo_movimiento = :tipo "
							+ "GROUP BY m.categoria;";
		Query query = em.createQuery(sentencia);

		query.setParameter("propietario", usuario.getId());
		query.setParameter("tipo", tipo);

		List<Object[]> resultados = query.getResultList();
		List<Egreso> egresosPorCategoria = new ArrayList<>();

		for (Object[] resultado : resultados) {
		    Egreso movimiento = (Egreso) resultado[0];
		    double totalCategoria = (double) resultado[1];
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
