package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Categoria;
import entities.Cuenta;
import entities.Movimiento;
import entities.TipoMovimiento;

import java.util.Date;

/**
 * @author Carlos Iñiguez
 */
@WebServlet("/RegistrarMovimientosController")
public class RegistrarMovimientosController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ruteador(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ruteador(request, response);
	}

	private void ruteador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ruta = (request.getParameter("ruta") != null) ? request.getParameter("ruta") : "ver";

		switch (ruta) {
		case "ver":
			
			request.getRequestDispatcher("/DashboardController?ruta=ver").forward(request, response);
			break;
		case "nuevoIngreso":
			nuevoIngreso(request, response);
			break;
		case "nuevaTransferencia":
			nuevaTransferencia(request, response);
			break;
		case "guardarTransferencia":
			try {
				guardarTransferencia(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	

	private void nuevaTransferencia(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//1.- Obtengo Datos
		int idCuentaOrigen = Integer.parseInt(request.getParameter("idCuentaOrigen"));
		//2.- Llamar al modelo
		Cuenta cuentaOrigen = Cuenta.getById(idCuentaOrigen);
		List<Cuenta> cuentasDestino = Cuenta.getAllDestinos(idCuentaOrigen);
		Categoria categoria = Categoria.getCategoriaTransferencia();
		//3.- Llamar a la vista
		request.setAttribute("cuentaOrigen", cuentaOrigen);
		request.setAttribute("cuentasDestino", cuentasDestino);
		request.setAttribute("categorias", categoria);
		request.getRequestDispatcher("/jsp/transferencia.jsp").forward(request, response);
		
	}

	private void nuevoGasto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void nuevoIngreso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.- obtengo datos
		int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
		// 2.- Llamo al Modelo
		List<Categoria> categoria = Categoria.getAllOfIngresoType();
		Cuenta cuenta = Cuenta.getById(idCuenta);
		// 3.- llamo a la vista
		request.setAttribute("categorias", categoria);
		request.setAttribute("cuenta", cuenta);
		request.getRequestDispatcher("/jsp/ingreso.jsp").forward(request, response);

	}

	private void eliminarGasto(HttpServletRequest request, HttpServletResponse response) {

	}

	private void eliminarIngreso(HttpServletRequest request, HttpServletResponse response) {

	}

	private void guardarTransferencia(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
		// TODO Auto-generated method stub
		
		//1.- Obtener Datos
		/*
		String concepto = request.getParameter("concepto");
		String fechaExtraida = request.getParameter("fecha");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date fecha = dateFormat.parse(fechaExtraida);
	    Double valor = Double.parseDouble(request.getParameter("valor"));
        int idCuentaOrigen = Integer.parseInt(request.getParameter("idCuentaOrigen"));
        int idCuentaDestino = Integer.parseInt(request.getParameter("idCuentaDestino"));
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
		*/
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = formato.parse(request.getParameter("fecha"));
	    String concepto = request.getParameter("concepto");
	    double valor = Double.parseDouble(request.getParameter("valor"));
        int idCuentaOrigen = Integer.parseInt(request.getParameter("idCuentaOrigen"));
        
        //String idCuentasDestinoValue = request.getParameter("idCuentasDestino_0");
        //int idCuentaDestino = Integer.parseInt(idCuentasDestinoValue);
        int idCuentaDestino = Integer.parseInt(request.getParameter("idCuentasDestino"));
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        
        //2. Llamar al modelo
/*
        Categoria categoria = Categoria.getById(idCategoria);
        Cuenta cuentaOrigen = Cuenta.getById(idCuentaOrigen);
        Cuenta cuentaDestino = Cuenta.getById(idCuentaDestino);
        Movimiento movTransferencia = new Movimiento();//Por revisar metodo
        boolean movimiento = movTransferencia.createTransferencia(movTransferencia, movTransferencia);
        */
        
        Categoria categoria = Categoria.getById(idCategoria);
        Cuenta cuentaOrigen = Cuenta.getById(idCuentaOrigen);
        Cuenta cuentaDestino = Cuenta.getById(idCuentaDestino);
        Movimiento ingreso = new Movimiento(fecha, valor, concepto, TipoMovimiento.INGRESO, null, cuentaDestino , categoria);
        Movimiento egreso = new Movimiento(fecha, valor, concepto, TipoMovimiento.EGRESO, cuentaOrigen, null, categoria);
        boolean movimiento = Movimiento.createTransferencia(ingreso, egreso);
        
        
     // 3.- llamo a la vista
        //request.getRequestDispatcher("/DashboardController?ruta=ver").forward(request, response);
        response.sendRedirect(request.getContextPath() + "/DashboardController?ruta=ver");

        
	}

	private void guardarEgreso(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void guardarIngreso(HttpServletRequest request, HttpServletResponse response) throws IOException {

	}

	/**
	 * Default constructor
	 */
	public RegistrarMovimientosController() {
	}

}