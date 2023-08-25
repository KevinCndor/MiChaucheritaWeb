package controlador.movimiento;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dao.DAOFactory;
import modelo.entidades.Egreso;
import modelo.entidades.Ingreso;
import modelo.entidades.Transferencia;
import modelo.entidades.Usuario;

@WebServlet("/MovimientoController")
public class MovimientoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MovimientoController() {
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
		default:
			break;
		}
	}
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//1. Obtener datos que me envian en la solicitud
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date();
		try {
			fecha = formatoFecha.parse(request.getParameter("fecha"));
		} catch (Exception e) {
			fecha = null;
		}
		
		HttpSession session = request.getSession();
		Usuario usuario = new Usuario();
		
		if (session != null) {
            usuario = (Usuario) session.getAttribute("usuarioLogeado");
        } else {
            usuario = null;
        }
		
		//2. Llamo al Modelo para obtener datos
		List<Ingreso> ingresosGenerales = null;
		List<Egreso> egresosGenerales = null;
		List<Transferencia> tranferenciasGenerales = null;
		
		if (fecha != null) {
			ingresosGenerales = DAOFactory.getFactory().getIngresoDAO().getIngresosFecha(usuario, fecha);
			egresosGenerales = DAOFactory.getFactory().getEgresoDAO().getEgresosFecha(usuario, fecha);
			tranferenciasGenerales = DAOFactory.getFactory().getTransferenciaDAO().getTransferenciasFecha(usuario, fecha);
		} else {
			ingresosGenerales = DAOFactory.getFactory().getIngresoDAO().getIngresosPorUsuario(usuario);
			egresosGenerales = DAOFactory.getFactory().getEgresoDAO().getEgresosPorUsuario(usuario);
			tranferenciasGenerales = DAOFactory.getFactory().getTransferenciaDAO().getTransferenciasPorUsuario(usuario);
		}
		
		//3. Llamo a la Vista
		request.setAttribute("ingresos", ingresosGenerales);
		request.setAttribute("egresos", egresosGenerales);
		request.setAttribute("transferencias", tranferenciasGenerales);
		request.getRequestDispatcher("jsp/dashboard.jsp").forward(request, response);
	}
}
