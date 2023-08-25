package controlador.dashboard;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dao.DAOFactory;
import modelo.entidades.Categoria;
import modelo.entidades.Cuenta;
import modelo.entidades.Egreso;
import modelo.entidades.Ingreso;
import modelo.entidades.Usuario;

@WebServlet("/DashboardController")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DashboardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
	}
	
	private void ruteador(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String ruta = (request.getParameter("ruta") == null) ? "mostrar" : request.getParameter("ruta");
		
		switch(ruta) {
		case "mostrar":
			this.mostrar(request, response);
			break;
		case "nuevomovimiento":
			String tipoMovimiento = request.getParameter("tipo");
			this.guardarMovimiento(request, response, tipoMovimiento);
			break;
		case "nuevacuenta":
			this.guardarCuenta(request, response);
			break;
		case "eliminarcuenta":
			this.eliminarCuenta(request, response);
			break;
		case "transferencia":
			this.realizarTransferencia(request, response);
			break;
		default:
			break;
		}
	}
	
	private void realizarTransferencia(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//1. Obtener datos que me envian en la solicitud
		
		
		//2. Llamo al Modelo para obtener datos
		
		
		//3. Llamo a la Vista
		response.sendRedirect("jsp/dashboard.jsp");
	}
	
	private void eliminarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//1. Obtener datos que me envian en la solicitud		
		String numeroCuenta = request.getParameter("numero");
		
		//2. Llamo al Modelo para obtener datos
		
		// REVISAR el metodo porque en la BD el ID es String !!!
		DAOFactory.getFactory().getCuentaDAO().deleteById(Integer.parseInt(numeroCuenta));
		
		//3. Llamo a la Vista
		response.sendRedirect("jsp/dashboard.jsp");
	}
	
	private void guardarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//1. Obtener datos que me envian en la solicitud		
		String numeroCuenta = request.getParameter("numero");
		String nombre = request.getParameter("nombre");
		double saldo = Double.parseDouble(request.getParameter("saldo")); // Por que String???
		
		//2. Llamo al Modelo para obtener datos
		Cuenta nuevaCuenta = new Cuenta();
		nuevaCuenta.setNumeroCuenta(numeroCuenta);
		nuevaCuenta.setNombre(nombre);
		nuevaCuenta.setSaldo(saldo);
		
		DAOFactory.getFactory().getCuentaDAO().create(nuevaCuenta);
		
		//3. Llamo a la Vista
		response.sendRedirect("jsp/dashboard.jsp");
	}
	
	private void guardarMovimiento(HttpServletRequest request, HttpServletResponse response, String tipoMovimiento) throws IOException {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		
		//1. Obtener datos que me envian en la solicitud
		HttpSession session = request.getSession(false);
		Usuario usuario = new Usuario();
		
		if (session != null) {
            usuario = (Usuario) session.getAttribute("usuarioLogeado");
        } else {
            usuario = null;
        }
		
		int id = 0;
		String descripcion = request.getParameter("descripcion");
		
		Date fecha = new Date();
		try {
			fecha = formatoFecha.parse(request.getParameter("fecha"));
		} catch (Exception e) {
			fecha = null;
		}
		
		Double valor = Double.parseDouble(request.getParameter("valor"));
		// Cuenta cuenta = DAOFactory.getFactory().getCuentaDAO().getByName(request.getParameter("nombreCuenta"), usuario);
		Categoria categoria = null; // request.getParameter("categoria");
		
		//2. Llamo al Modelo para obtener datos
		if (tipoMovimiento.equals("ingreso")) {
			Ingreso nuevoIngreso = new Ingreso();
			nuevoIngreso.setId(id);
			nuevoIngreso.setDescripcion(descripcion);
			nuevoIngreso.setFecha(fecha);
			nuevoIngreso.setValor(valor);
			// nuevoIngreso.setCuenta(cuenta);
			nuevoIngreso.setCategoria(categoria);
			
			DAOFactory.getFactory().getIngresoDAO().create(nuevoIngreso);
		} else  {
			Egreso nuevoEgreso = new Egreso();
			nuevoEgreso.setId(id);
			nuevoEgreso.setDescripcion(descripcion);
			nuevoEgreso.setFecha(null);
			nuevoEgreso.setValor(valor);
			// nuevoEgreso.setCuenta(cuenta);
			nuevoEgreso.setCategoria(categoria);
			
			DAOFactory.getFactory().getEgresoDAO().create(nuevoEgreso);
		}
		
		//3. Llamo a la Vista
		response.sendRedirect("jsp/dashboard.jsp");	
	}
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//1. Obtener datos que me envian en la solicitud
		int mes = -1;
		String filtroMes = request.getParameter("filtromes");
		if( filtroMes != null) {
			mes = Integer.parseInt(request.getParameter("months"));
		}
		// REVISAR LA OBTENCION DEL USUARIO !!!
		HttpSession session = request.getSession();
		Usuario usuario = new Usuario();
		
		if (session != null) {
            usuario = (Usuario) session.getAttribute("usuarioLogeado");
        } else {
            usuario = null;
        }
		
		//2. Llamo al Modelo para obtener datos
		List<Ingreso> ingresosPorCategoria = null;
		List<Egreso> egresosPorCategoria = null;
		List<Cuenta> misCuentas = null;
		
		if (mes != -1) {
			ingresosPorCategoria = DAOFactory.getFactory().getIngresoDAO().getIngresosPorCategoriaYMes(usuario, mes);
			egresosPorCategoria = DAOFactory.getFactory().getEgresoDAO().getEgresosPorCategoriaYMes(usuario, mes);
		} else {
			ingresosPorCategoria = DAOFactory.getFactory().getIngresoDAO().getIngresosPorCategoria(usuario);
			egresosPorCategoria = DAOFactory.getFactory().getEgresoDAO().getEgresosPorCategoria(usuario);
		}
		
		misCuentas = DAOFactory.getFactory().getCuentaDAO().getCuentasUsuario(usuario);
		
		//3. Llamo a la Vista
		request.setAttribute("ingresos", ingresosPorCategoria);
		request.setAttribute("egresos", egresosPorCategoria);
		request.setAttribute("cuentas", misCuentas);
		request.getRequestDispatcher("jsp/dashboard.jsp").forward(request, response);
	}

}
