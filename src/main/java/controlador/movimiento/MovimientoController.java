package controlador.movimiento;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
		String ruta = (request.getParameter("ruta") == null) ? "" : request.getParameter("ruta");
		
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
		case "nuevatransferencia":
			this.nuevaTransferencia(request, response);
			break;
		case "subcategoria":
			this.mostrarSubcategoria(request, response);
			break;
		case "filtrar":
			this.filtrar(request, response);
			break;
		default:
			break;
		}
	}

		private void nuevaTransferencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. Obtener datos que me envian en la solicitud
		Usuario usuario = getSession(request);
		List<Cuenta> cuentas = DAOFactory.getFactory().getCuentaDAO().getCuentasUsuario(usuario);
		
		request.setAttribute("cuentas", cuentas);
		
		request.getRequestDispatcher("jsp/transferencia.jsp").forward(request, response);	
	}

	private void mostrarSubcategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Subcategoria> subcategoriasEgresos = null;
        Categoria catParaSubcat = null;

        String catSelected = request.getParameter("categoriaEgreso");
        catParaSubcat = DAOFactory.getFactory().getCategoriaDAO().getById(Integer.parseInt(catSelected));
        System.out.println(catSelected);
        subcategoriasEgresos = DAOFactory.getFactory().getSubcategoriaDAO().getSubcategoriasPorCategoria(catParaSubcat);

        StringBuilder subcategoriasTexto = new StringBuilder();
        for (Subcategoria subcategoria : subcategoriasEgresos) {
            subcategoriasTexto.append(subcategoria.getNombre()).append("\n");
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.write(subcategoriasTexto.toString());
        out.close();
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
		Cuenta cuentaOrigen = DAOFactory.getFactory().getCuentaDAO().getPorNombreYUsuario(request.getParameter("cuentaOrigen"), usuario);
		Cuenta cuentaDestino = DAOFactory.getFactory().getCuentaDAO().getPorNombreYUsuario(request.getParameter("cuentaDestino"), usuario);
		
		//2. Llamo al Modelo para obtener datos
		Transferencia nuevaTransferencia = new Transferencia(descripcion, "Trasnferencia", fecha, valor, cuentaOrigen, cuentaDestino);
		DAOFactory.getFactory().getTransferenciaDAO().create(nuevaTransferencia);
		Cuenta cuentaOrigenAct = nuevaTransferencia.getCuenta();
		Cuenta cuentaOrigenActualizar = new Cuenta(cuentaOrigenAct.getNumeroCuenta(),cuentaOrigenAct.getNombre(),cuentaOrigenAct.getSaldo()-nuevaTransferencia.getValor(),cuentaOrigenAct.getPropietario());
		DAOFactory.getFactory().getCuentaDAO().update(cuentaOrigenActualizar);
		Cuenta cuentaDestinoAct = nuevaTransferencia.getCuentaDestino();
		Cuenta cuentaDestinoActualizar = new Cuenta(cuentaDestinoAct.getNumeroCuenta(),cuentaDestinoAct.getNombre(),cuentaDestinoAct.getSaldo()+nuevaTransferencia.getValor(),cuentaDestinoAct.getPropietario());
		DAOFactory.getFactory().getCuentaDAO().update(cuentaDestinoActualizar);
		
		//3. Llamo a la Vista
		response.sendRedirect("DashboardController?ruta=mostrar");
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
		
		//1. Obtener datos que me envian en la solicitud
		Usuario usuario = getSession(request);
		
		String tipoMovimiento = request.getParameter("tipo");
		String descripcion = request.getParameter("descripcion");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = new Date();
		try {
			fecha = dateFormat.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
		}
		
		Double valor = Double.parseDouble(request.getParameter("valor"));
		Cuenta cuenta = DAOFactory.getFactory().getCuentaDAO().getPorNombreYUsuario(request.getParameter("cuenta"), usuario);
		Categoria categoria = new Categoria();
		
		//2. Llamo al Modelo para obtener datos
		if (tipoMovimiento.equals("Ingreso")) {
			categoria = DAOFactory.getFactory().getCategoriaDAO().getById(Integer.parseInt(request.getParameter("categoriaIngreso")));
			Ingreso nuevoIngreso = new Ingreso(descripcion, tipoMovimiento, fecha, valor, cuenta, categoria);			
			DAOFactory.getFactory().getIngresoDAO().create(nuevoIngreso);
			Cuenta cuentaAct = nuevoIngreso.getCuenta();
			Cuenta cuentaAActualizar = new Cuenta(cuentaAct.getNumeroCuenta(),cuentaAct.getNombre(),cuentaAct.getSaldo()+nuevoIngreso.getValor(),cuentaAct.getPropietario());
			DAOFactory.getFactory().getCuentaDAO().update(cuentaAActualizar);
		} else  {
			categoria = DAOFactory.getFactory().getCategoriaDAO().getById(Integer.parseInt(request.getParameter("categoriaEgreso")));
			Subcategoria subcategoria = DAOFactory.getFactory().getSubcategoriaDAO().getByName(request.getParameter("subcategoriaEgreso"));
			Egreso nuevoEgreso = new Egreso(descripcion, fecha, valor, cuenta, categoria, subcategoria, tipoMovimiento);
			DAOFactory.getFactory().getEgresoDAO().create(nuevoEgreso);
			Cuenta cuentaAct = nuevoEgreso.getCuenta();
			Cuenta cuentaAActualizar = new Cuenta(cuentaAct.getNumeroCuenta(),cuentaAct.getNombre(),cuentaAct.getSaldo()-nuevoEgreso.getValor(),cuentaAct.getPropietario());
			DAOFactory.getFactory().getCuentaDAO().update(cuentaAActualizar);
		}
		
		//3. Llamo a la Vista
		response.sendRedirect("DashboardController?ruta=mostrar");	
	}
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//1. Obtener datos que me envian en la solicitud
		String nombreCuenta = request.getParameter("nombre");
		String numCuenta = null;
		HttpSession session = request.getSession();
		Usuario usuario = getSession(request);
		List<Movimiento> movimientos = null;
		if(nombreCuenta != null){
			session.setAttribute("cuenta", DAOFactory.getFactory().getCuentaDAO().getPorNombreYUsuario(nombreCuenta, usuario));
			numCuenta = DAOFactory.getFactory().getCuentaDAO().getPorNombreYUsuario(nombreCuenta, usuario).getNumeroCuenta();
			movimientos = DAOFactory.getFactory().getMovimientoDAO().getByCuenta(numCuenta, usuario);
		}else {
			session.removeAttribute("cuenta");
			movimientos = DAOFactory.getFactory().getMovimientoDAO().getAllByUser(usuario);
		}
		//3. Llamo a la Vista
		request.setAttribute("movimientos", movimientos);
		request.getRequestDispatcher("jsp/movimiento.jsp").forward(request, response);
	}
	
	private void filtrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cuenta cuenta = (Cuenta) session.getAttribute("cuenta");
		Usuario usuario = getSession(request);
		int mes = -1;
		if(request.getParameter("months") != null) {
			mes = Integer.parseInt(request.getParameter("months"));
		}
		
		String tipo = request.getParameter("tipo");
		System.out.println(tipo);
		List<Movimiento> movimientos = null;
		if(cuenta == null) {
			if(mes >= 0) {
				if(tipo == null) {
					movimientos = DAOFactory.getFactory().getMovimientoDAO().getAllByMonth(usuario, mes);
				}else {
					movimientos = DAOFactory.getFactory().getMovimientoDAO().getAllByMonthAndType(usuario, mes, tipo);
				}
			}else {
				if(tipo != null) {
					movimientos = DAOFactory.getFactory().getMovimientoDAO().getAllByType(usuario, tipo);
				}else {
					movimientos = DAOFactory.getFactory().getMovimientoDAO().getAllByUser(usuario);
				}
			}
		}else {
			if(mes >= 0) {
				if(tipo == null) {
					movimientos = DAOFactory.getFactory().getMovimientoDAO().getByMonth(cuenta.getNumeroCuenta(), mes);
				}else {
					movimientos = DAOFactory.getFactory().getMovimientoDAO().getByMothAndType(cuenta.getNumeroCuenta(), mes, tipo);
				}
			}else {
				if(tipo != null) {
					movimientos = DAOFactory.getFactory().getMovimientoDAO().getByType(cuenta.getNumeroCuenta(), tipo);
				}else {
					movimientos = DAOFactory.getFactory().getMovimientoDAO().getByCuenta(cuenta.getNumeroCuenta(), usuario);
				}
			}
		}
		
		request.setAttribute("movimientos", movimientos);
		request.getRequestDispatcher("jsp/movimiento.jsp").forward(request, response);
	}
	
}


