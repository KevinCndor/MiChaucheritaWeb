package modelo.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import modelo.dao.EgresoDAO;
import modelo.entidades.Categoria;
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
		String sentencia = "SELECT e.subcategoria, e, e.subcategoria.categoriaPadre, SUM(e.valor) as total_subcategoria "
						+ "FROM Egreso e "
						+ "JOIN e.cuenta c "
						+ "JOIN c.propietario u "
						+ "WHERE u.id = :userId "
						+ "GROUP BY e.subcategoria";
		Query query = em.createQuery(sentencia);

		query.setParameter("userId", usuario.getId());

		List<Object[]> resultados = query.getResultList();
		List<Egreso> egresosPorSubcategoria = new ArrayList<>();

		for (Object[] resultado : resultados) {
		    Subcategoria subcategoria = (Subcategoria) resultado[0];
		    Movimiento movimiento = (Movimiento) resultado[1];
		    Categoria categoria = (Categoria) resultado[2];
		    double totalSubcategoria = ((Number) resultado[3]).doubleValue();
		    Egreso egreso = new Egreso(movimiento.getDescripcion(), movimiento.getFecha(),
		                               movimiento.getValor(), movimiento.getCuenta(), categoria, 
		                               subcategoria, movimiento.getTipoMovimiento());
			
		    subcategoria.setValor(totalSubcategoria);
		    egresosPorSubcategoria.add(egreso);
		}
		return egresosPorSubcategoria;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Egreso> getEgresosPorCategoria(Usuario usuario) {
		String sentencia = "SELECT e, SUM(e.valor) as total_categoria "
                + "FROM Egreso e "
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
		    							movimiento.getSubcategoria(), movimiento.getTipoMovimiento());
		    movimiento.getCategoria().setValor(totalCategoria);
		    egresosPorCategoria.add(egreso);
		}
		return egresosPorCategoria;
	}
	
	@SuppressWarnings({"unchecked" })
	@Override
	public List<Egreso> getEgresosPorCategoriaYMes(Usuario usuario, int mes) {
		String sentencia = "SELECT e, SUM(e.valor) as total_categoria FROM Egreso e "
                + "JOIN e.cuenta c "
                + "WHERE c.propietario.id = :usuarioId "
                + "AND FUNCTION('MONTH', e.fecha) = :mes "
                + "GROUP BY e.categoria";
		
		Query query = em.createQuery(sentencia);

		query.setParameter("usuarioId", usuario.getId());
		query.setParameter("mes", mes+1);
		
		List<Object[]> resultados = query.getResultList();
		List<Egreso> egresosPorMesYCateg = new ArrayList<>();

		for (Object[] resultado : resultados) {
		    Egreso movimiento = (Egreso) resultado[0];
		    double totalCategoria = ((Number) resultado[1]).doubleValue();
		    Egreso egreso = new Egreso(movimiento.getDescripcion(), movimiento.getFecha(),
		    							movimiento.getValor(), movimiento.getCuenta(), movimiento.getCategoria(), 
		    							movimiento.getSubcategoria(), movimiento.getTipoMovimiento());
		    movimiento.getCategoria().setValor(totalCategoria);
		    egresosPorMesYCateg.add(egreso);
		}
		
	    return egresosPorMesYCateg;
	}



	@SuppressWarnings({ "unchecked"})
	@Override
	public List<Egreso> getEgresosPorSubCatYMes(Usuario usuario, int mes) {
		String sentencia = "SELECT e.subcategoria, e, SUM(e.valor) as total_categoria FROM Egreso e "
                + "JOIN e.cuenta c "
                + "WHERE c.propietario.id = :usuarioId "
                + "AND FUNCTION('MONTH', e.fecha) = :mes "
                + "GROUP BY e.subcategoria";
		
		Query query = em.createQuery(sentencia);

		query.setParameter("usuarioId", usuario.getId());
		query.setParameter("mes", mes+1);
		
		List<Object[]> resultados = query.getResultList();
		List<Egreso> egresosPorMesYSubcateg = new ArrayList<>();

		for (Object[] resultado : resultados) {
		    Subcategoria subcategoria = (Subcategoria) resultado[0];
		    Movimiento movimiento = (Movimiento) resultado[1];
		    double totalSubcategoria = ((Number) resultado[2]).doubleValue();
		    Egreso egreso = new Egreso(movimiento.getDescripcion(), movimiento.getFecha(),
		                               movimiento.getValor(), movimiento.getCuenta(), subcategoria.getCategoriaPadre(), 
		                               subcategoria, movimiento.getTipoMovimiento());
			
		    subcategoria.setValor(totalSubcategoria);
		    egresosPorMesYSubcateg.add(egreso);
		}
		
	    return egresosPorMesYSubcateg;
	}
}
