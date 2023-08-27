package controlador.dashboard;

import java.io.IOException;
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
		case "nuevacuenta":
			this.guardarCuenta(request, response);
			break;
		case "eliminarcuenta":
			this.eliminarCuenta(request, response);
			break;
		default:
			break;
		}
	}
	
	private void eliminarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//1. Obtener datos que me envian en la solicitud		
		String numeroCuenta = request.getParameter("numero");
		
		//2. Llamo al Modelo para obtener datos
		
		// REVISAR el metodo porque en la BD el ID es String !!!
		DAOFactory.getFactory().getCuentaDAO().deleteById(numeroCuenta);
		
		//3. Llamo a la Vista
		response.sendRedirect("DashboardController?ruta=mostrar");
	}
	
	private void guardarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//1. Obtener datos que me envian en la solicitud		
		String numeroCuenta = request.getParameter("numero");
		String nombre = request.getParameter("nombre");
		double saldo = Double.parseDouble(request.getParameter("saldo"));
		Usuario usuario = getSession(request);
		
		//2. Llamo al Modelo para obtener datos
		Cuenta nuevaCuenta = new Cuenta(numeroCuenta, nombre, saldo, usuario);
		DAOFactory.getFactory().getCuentaDAO().create(nuevaCuenta);
		
		//3. Llamo a la Vista
		response.sendRedirect("DashboardController?ruta=mostrar");
	}
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//1. Obtener datos que me envian en la solicitud
		int mes = -1;
		String filtroMes = request.getParameter("filtromes");
		if( filtroMes != null) {
			mes = Integer.parseInt(request.getParameter("months"));
		}
		// REVISAR LA OBTENCION DEL USUARIO !!!
		Usuario usuario = getSession(request);
		
		//2. Llamo al Modelo para obtener datos
		List<Ingreso> ingresosPorCategoria = null;
		List<Egreso> egresosPorCategoria = null;
		List<Egreso> egresosPorSubcategoria = null;
		List<Cuenta> misCuentas = null;
		
		if (mes != -1) {
			ingresosPorCategoria = DAOFactory.getFactory().getIngresoDAO().getIngresosPorCategoriaYMes(usuario, mes);
			egresosPorCategoria = DAOFactory.getFactory().getEgresoDAO().getEgresosPorCategoriaYMes(usuario, mes);
			egresosPorSubcategoria = DAOFactory.getFactory().getEgresoDAO().getEgresosPorSubCatYMes(usuario, mes);
		} else {
			ingresosPorCategoria = DAOFactory.getFactory().getIngresoDAO().getIngresosPorCategoria(usuario);
			egresosPorCategoria = DAOFactory.getFactory().getEgresoDAO().getEgresosPorCategoria(usuario);
			egresosPorSubcategoria = DAOFactory.getFactory().getEgresoDAO().getEgresosPorSubcategoria(usuario);
		}
		
		misCuentas = DAOFactory.getFactory().getCuentaDAO().getCuentasUsuario(usuario);
		List<Categoria> categoriasIngresos = DAOFactory.getFactory().getCategoriaDAO().getCategoriasPorTipo("Ingreso");
		List<Categoria> categoriasEgresos = DAOFactory.getFactory().getCategoriaDAO().getCategoriasPorTipo("Egreso");

		request.setAttribute("categoriasEgreso", categoriasEgresos);
		request.setAttribute("categoriasIngreso", categoriasIngresos);
		request.setAttribute("ingresos", ingresosPorCategoria);
		request.setAttribute("egresos", egresosPorCategoria);
		request.setAttribute("egresosSubcategoria", egresosPorSubcategoria);
		request.setAttribute("cuentas", misCuentas);
		request.getRequestDispatcher("jsp/dashboard.jsp").forward(request, response);
	}

	private Usuario getSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Usuario usuario = new Usuario();
		
		if (session != null) {
            usuario = (Usuario) session.getAttribute("usuarioLogeado");
        } else {
            usuario = null;
        }
		return usuario;
	}

}
