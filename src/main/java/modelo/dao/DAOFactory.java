package modelo.dao;

import modelo.jpa.JPADAOFactory;

public abstract class DAOFactory {
	
	protected static DAOFactory factory = new JPADAOFactory();
	
	public static DAOFactory getFactory() {
		return factory;
	}
	
	public abstract UsuarioDAO getUsuarioDAO();
	public abstract MovimientoDAO getMovimientoDAO();
	public abstract CuentaDAO getCuentaDAO();
	public abstract EgresoDAO getEgresoDAO();
	public abstract IngresoDAO getIngresoDAO();
	public abstract TransferenciaDAO getTransferenciaDAO();
	
}
