package controlador.movimiento;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import modelo.entidades.Movimiento;
import modelo.entidades.Subcategoria;
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
		case "nuevomovimiento":
			this.guardarMovimiento(request, response);
			break;
		case "transferencia":
			this.realizarTransferencia(request, response);
			break;
		case "movimiento":
			this.movimiento(request, response);
			break;
		case "subcategoria":
			this.mostrarSubcategoria(request, response);
			break;
		default:
			break;
		}
	}
	
	private void mostrarSubcategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Subcategoria> subcategoriasEgresos = null;		
		Categoria catParaSubcat = null;
		
		// Es necesario este if???
		if (request.getParameter("filtrosubcat") != null) {
			String catSelected = request.getParameter("categoriaEgreso");
			catParaSubcat = DAOFactory.getFactory().getCategoriaDAO().getById(Integer.parseInt(catSelected));			
			subcategoriasEgresos = DAOFactory.getFactory().getSubcategoriaDAO().getSubcategoriasPorCategoria(catParaSubcat);
			
			response.setContentType("application/json");
	        PrintWriter out = response.getWriter();
	        out.print("[");
	        for (int i = 0; i < subcategoriasEgresos.size(); i++) {
	            Subcategoria subcategoria = subcategoriasEgresos.get(i);
	            out.print("{");
	            out.print("\"id\": \"" + subcategoria.getId() + "\",");
	            out.print("\"nombre\": \"" + subcategoria.getNombre() + "\"");
	            out.print("}");
	            if (i < subcategoriasEgresos.size() - 1) {
	                out.print(",");
	            }
	        }
	        out.print("]");
	        out.close();
		}
		
		/*
		//1. Obtener datos que me envian en la solicitud
		Categoria categoria = DAOFactory.getFactory().getCategoriaDAO().getById(Integer.parseInt(request.getParameter("categoria")));
		
		//2. Llamo al Modelo para obtener datos
		List<Subcategoria> subcategorias = DAOFactory.getFactory().getSubcategoriaDAO().getSubcategoriasPorCategoria(categoria);
		
		//3. Llamo a la Vista
		request.setAttribute("subcategorias", subcategorias);
		// request.getRequestDispatcher("jsp/dashboard.jsp").forward(request, response);
		 */
	}

	private void movimiento(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//1. Obtener datos que me envian en la solicitud
		String tipo = request.getParameter("tipo");
		Usuario usuario = getSession(request);
		
		//2. Llamo al Modelo para obtener datos		
		if (tipo.equals("Ingreso")) {
			//2. Llamo al Modelo para obtener datos	
			List<Categoria> categoriasIngresos = DAOFactory.getFactory().getCategoriaDAO().getCategoriasPorTipo("Ingreso");
			
			//3. Llamo a la Vista
			request.setAttribute("categoriasIngreso", categoriasIngresos);
			request.getRequestDispatcher("templates/ingresotemplate.jsp").forward(request, response);			
		} else {
			//2. Llamo al Modelo para obtener datos	
			List<Categoria> categoriasEgresos = DAOFactory.getFactory().getCategoriaDAO().getCategoriasPorTipo("Egreso");
			
			//3. Llamo a la Vista
			request.setAttribute("categoriasEgreso", categoriasEgresos);
			request.getRequestDispatcher("templates/egresotemplate.jsp").forward(request, response);
		}
	}
	
	private void realizarTransferencia(HttpServletRequest request, HttpServletResponse response) throws IOException {	
		//1. Obtener datos que me envian en la solicitud
		Usuario usuario = getSession(request);
		
		String descripcion = request.getParameter("monto");
		
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = LocalDate.now().format(formato);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = new Date();
		try {
			fecha = formatoFecha.parse(fechaFormateada);
		} catch (Exception e) {
			fecha = null;
		}
		
		double valor = Double.parseDouble(request.getParameter("monto"));
		// En el jsp donde esta el div que muestra el nombre del banco, poner name="cuentaOrigen" o "cuentaDestino" !!!
		Cuenta cuentaOrigen = DAOFactory.getFactory().getCuentaDAO().getPorNombreYUsuario(request.getParameter("cuentaOrigen"), usuario);
		Cuenta cuentaDestino = DAOFactory.getFactory().getCuentaDAO().getPorNombreYUsuario(request.getParameter("cuentaDestino"), usuario);
		
		//2. Llamo al Modelo para obtener datos
		Transferencia nuevaTransferencia = new Transferencia(descripcion, fecha, valor, cuentaOrigen, cuentaDestino);
		DAOFactory.getFactory().getTransferenciaDAO().create(nuevaTransferencia);
		
		//3. Llamo a la Vista
		response.sendRedirect("jsp/dashboard.jsp");
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
	
	private void guardarMovimiento(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		
		//1. Obtener datos que me envian en la solicitud
		Usuario usuario = getSession(request);
		
		String tipoMovimiento = request.getParameter("tipo");
		String descripcion = request.getParameter("descripcion");
		
		Date fecha = new Date();
		try {
			fecha = formatoFecha.parse(request.getParameter("fecha"));
		} catch (Exception e) {
			fecha = null;
		}
		
		Double valor = Double.parseDouble(request.getParameter("valor"));
		Cuenta cuenta = DAOFactory.getFactory().getCuentaDAO().getPorNombreYUsuario(request.getParameter("nombreCuenta"), usuario);
		Categoria categoria = new Categoria();
		
		//2. Llamo al Modelo para obtener datos
		if (tipoMovimiento.equals("Ingreso")) {
			categoria = DAOFactory.getFactory().getCategoriaDAO().getById(Integer.parseInt(request.getParameter("categoriaIngreso")));
			Ingreso nuevoIngreso = new Ingreso(descripcion, fecha, valor, cuenta, categoria);			
			DAOFactory.getFactory().getIngresoDAO().create(nuevoIngreso);
		} else  {
			categoria = DAOFactory.getFactory().getCategoriaDAO().getById(Integer.parseInt(request.getParameter("categoriaEgreso")));
			Subcategoria subcategoria = DAOFactory.getFactory().getSubcategoriaDAO().getById(Integer.parseInt(request.getParameter("subcategoriaEgreso")));
			Egreso nuevoEgreso = new Egreso(descripcion, fecha, valor, cuenta, categoria, subcategoria);
			DAOFactory.getFactory().getEgresoDAO().create(nuevoEgreso);
		}
		
		//3. Llamo a la Vista
		response.sendRedirect("jsp/dashboard.jsp");	
	}
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//1. Obtener datos que me envian en la solicitud
		String verGeneral = null;
		if (request.getParameter("general") != null) {
			verGeneral = request.getParameter("general");
		}
		
		int mes = -1;
		String filtroMes = request.getParameter("filtromes");
		if( filtroMes != null) {
			mes = Integer.parseInt(request.getParameter("months"));
		}
		
		String tipo = null;
		String filtroTipo = request.getParameter("filtrotipo");
		if (filtroTipo != null) {
			tipo = request.getParameter("filtrotipo");
		}
		
		Usuario usuario = getSession(request);
		
		//2. Llamo al Modelo para obtener datos		
		List<Movimiento> movimientos = null;
		
		if (verGeneral != null) {
			// FALTA IMPLEMENTAR !!!
			// movimientos = DAOFactory.getFactory().getMovimientoDAO().getUserMovements(usuario);
		} else {
			String nombreCuenta = request.getParameter("nombre");
			DAOFactory.getFactory().getCuentaDAO().getPorNombreYUsuario(nombreCuenta, usuario);
			
			if (mes >= 0)  {
				if (tipo != null) {
					// movimientos = DAOFactory.getFactory().getMovimientoDAO().getByMothAndType(idCuenta, mes, filtroTipo);
				} else {
					// movimientos = DAOFactory.getFactory().getMovimientoDAO().getByMonth(idCuenta, mes);
				}
			} else {
				if (tipo != null) {
					// movimientos = DAOFactory.getFactory().getMovimientoDAO().getByType(idCuenta, tipo);
				} else {
					// movimientos = DAOFactory.getFactory().getMovimientoDAO().getAllByUser(idCuenta);
				}
			}
		}
		
		//3. Llamo a la Vista
		request.setAttribute("movimientos", movimientos);
		request.getRequestDispatcher("jsp/movimiento.jsp").forward(request, response);
	}
}
