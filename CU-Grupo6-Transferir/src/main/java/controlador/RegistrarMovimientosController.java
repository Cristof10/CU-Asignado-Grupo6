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
		case "nuevoingreso":
			nuevoIngreso(request, response);
			break;
		}

	}

	private void nuevaTransferencia(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//1.- Obtengo Datos
		int idcuentaOrigen = Integer.parseInt(request.getParameter("idCuentaOrigen"));
		//2.- Llamar al modelo
		Cuenta cuentaOrigen = Cuenta.getById(Integer.parseInt("idCuenta"));
		List<Cuenta> cuentasDestino = Cuenta.getAllDestinos("idCuentaOrigen");
		Categoria categoriaTransferencia = new Categoria();
		Categoria categoria = categoriaTransferencia.getAllOfTransferType();
		//3.- Llamar a la vista
		request.setAttribute("cuentaOrigen", cuentaOrigen);
		request.setAttribute("cuentasDestino", cuentasDestino);
		request.setAttribute("categoria", categoria);
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

	private void guardarTransferencia(HttpServletRequest request, HttpServletResponse response) 
			throws ParseException {
		// TODO Auto-generated method stub
		//1.- Obtener Datos
		String concepto = request.getParameter("concepto");
		
		String fechaExtraida = request.getParameter("fecha");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = dateFormat.parse(fechaExtraida);
        
        Double valor = Double.parseDouble(request.getParameter("valor"));
        
        int idCuentaOrigen = Integer.parseInt(request.getParameter("idCuentaOrigen"));
        
        int idCuentaDestino = Integer.parseInt(request.getParameter("idCuentaDestino"));
        
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        
        //2. Llamar al modelo
        
        Cuenta cuentaOrigen = Cuenta.getById(idCuentaOrigen);
        Cuenta cuentaDestino = Cuenta.getById(idCuentaDestino);
        Categoria categoria = Categoria.getById(idCategoria);
        Movimiento movTransferencia = new Movimiento();//Por revisar metodo
        Movimiento movimiento = movTransferencia.createTransferencia(movTransferencia, movTransferencia);
        
        
 
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