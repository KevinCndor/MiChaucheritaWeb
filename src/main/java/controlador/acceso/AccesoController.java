package controlador.acceso;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dao.DAOFactory;
import modelo.entidades.Usuario;

@WebServlet("/AccesoController")
public class AccesoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AccesoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
	}
	
	private void ruteador(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String ruta = (request.getParameter("ruta") == null) ? "inicio" : request.getParameter("ruta");
		
		switch(ruta) {
		case "inicio":
			this.inicio(request, response);
			break;
		case "ingresar":
			this.ingresar(request, response);
			break;
		case "salir":
			this.salir(request, response);
			break;
		case "registrar":
			this.registrar(request, response);
			break;
		case "crearusuario":
			this.crearUsuario(request, response);
			break;
		case "error":
			break;
		default:
			break;
		}
	}
	
	private void inicio(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//1. Obtener datos que me envian en la solicitud
		
		//2. Llamo al Modelo para obtener datos
		
		//3. Llamo a la Vista
		response.sendRedirect("jsp/login.jsp");
	}
	
	private void salir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect("jsp/login.jsp");
	}
	
	private void ingresar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//1. Obtener datos que me envian en la solicitud		
		String nombre= request.getParameter("usuario");
		String clave = request.getParameter("contrasena");
		
		//2. Llamo al Modelo para obtener datos		
		Usuario usuarioAutenticado = DAOFactory.getFactory().getUsuarioDAO().autorizar(nombre, clave);
		
		if(usuarioAutenticado != null) {			
			// Crear la sesion
			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogeado", usuarioAutenticado);
			
			//3. Llamo a la vista
			response.sendRedirect("DashboardController?ruta=mostrar");
			return;
		}else {
			//3. Llamo a la vista
			String mensaje = "Ingresaste mal tu usuario y clave";
			// Enviar datos a la vista
			request.setAttribute("mensaje", mensaje);
			// Redireccionar a la vista
			request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
		}
	}
	
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//1. Obtener datos que me envian en la solicitud
		
		//2. Llamo al Modelo para obtener datos
		
		//3. Llamo a la Vista
		response.sendRedirect("jsp/registrar.jsp");
	}
	
	private void crearUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//1. Obtener datos que me envian en la solicitud		
		String nombre= request.getParameter("nombre");
		String apellido= request.getParameter("apellido");
		String usuario= request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		
		//2. Llamo al Modelo para obtener datos		
		Usuario usuarioNuevo = new Usuario();
		usuarioNuevo.setId(1);
		usuarioNuevo.setNombre(nombre);
		usuarioNuevo.setApellido(apellido);
		usuarioNuevo.setUsername(usuario);
		usuarioNuevo.setPassword(contrasena);
		
		DAOFactory.getFactory().getUsuarioDAO().create(usuarioNuevo);
		
		//3. Llamo a la Vista
		response.sendRedirect("AccesoController?ruta=inicio");
	}
}
