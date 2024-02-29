package PruebasUnitarias;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import entities.Categoria;
import entities.Cuenta;
import entities.Movimiento;
import entities.TipoMovimiento;

class MovimientoTest {

	@Test
	void createTransferencia() {
		// Definir los valores para los parámetros del constructor
		// Crear una instancia de fecha
        Date fecha = new Date(); // Supongamos que la fecha es la actual
        double monto = 350.0;
        String concepto = "Transferencia";
        TipoMovimiento tipoEgreso = TipoMovimiento.EGRESO; // Supongamos que es un egreso
        TipoMovimiento tipoIngreso = TipoMovimiento.INGRESO; 
        Cuenta origen = Cuenta.getById(1);
        Cuenta destino = Cuenta.getById(3);
        Categoria categoria = Categoria.getCategoriaTransferencia();

        // Crear una instancia de Movimiento utilizando el constructor
        Movimiento egreso = new Movimiento(fecha, monto, concepto, tipoEgreso, origen, destino, categoria);
        Movimiento ingreso = new Movimiento(fecha, monto, concepto, tipoIngreso, origen, destino, categoria);

        
        // Llamada al método createTransferencia y verificación del resultado
        //assertTrue(Movimiento.createTransferencia(ingreso, egreso));
        //assertFalse(Movimiento.createTransferencia(ingreso, egreso));
        
        // Verificar que el saldo de la cuenta origen se redujo en 100
        assertEquals(350, origen.getTotal());
        
        // Verificar que el saldo de la cuenta destino se aumentó en 100
        assertEquals(450, destino.getTotal());
	}

}
