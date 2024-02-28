package PruebasUnitarias;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import entities.Categoria;
import entities.Cuenta;
import entities.Movimiento;
import entities.TipoMovimiento;

class MovimientoTest {
/*
	@Test
	void createTransferenciaConCuentasCreadas() {
		// Crear instancias de Cuenta, Movimiento (ingreso y egreso) 
        Cuenta origen = new Cuenta("Pichincha", 600); // Supongamos que se crea la cuenta de origen
        Cuenta destino = new Cuenta("Pichincha", 200); // Supongamos que se crea la cuenta de destino
        
        Movimiento ingreso = new Movimiento();
        ingreso.setOrigen(origen);
        ingreso.setDestino(destino);
        ingreso.setMonto(100); // Supongamos que el monto del ingreso es de 100
        
        Movimiento egreso = new Movimiento();
        egreso.setOrigen(origen);
        egreso.setDestino(destino);
        egreso.setMonto(100); // Supongamos que el monto del egreso es de 50
        
        // Llamada al método createTransferencia y verificación del resultado
        assertTrue(Movimiento.createTransferencia(ingreso, egreso));
        
        // Verificar que el saldo de la cuenta origen se redujo en 100
        assertEquals(origen.getTotal(), 500);
        
        // Verificar que el saldo de la cuenta destino se aumentó en 100
        assertEquals(destino.getTotal(), 300);
	}
	*/
	@Test
	void createTransferencia() {
		// Definir los valores para los parámetros del constructor
		// Crear una instancia de fecha
        Date fecha = new Date(); // Supongamos que la fecha es la actual
        double monto = 100.0;
        String concepto = "Transferencia";
        TipoMovimiento tipoEgreso = TipoMovimiento.EGRESO; // Supongamos que es un egreso
        TipoMovimiento tipoIngreso = TipoMovimiento.INGRESO; 
        Cuenta origen = Cuenta.getById(1);
        Cuenta destino = Cuenta.getById(2);
        Categoria categoria = Categoria.getCategoriaTransferencia();

        // Crear una instancia de Movimiento utilizando el constructor
        Movimiento egreso = new Movimiento(fecha, monto, concepto, tipoEgreso, origen, destino, categoria);
        Movimiento ingreso = new Movimiento(fecha, monto, concepto, tipoIngreso, origen, destino, categoria);

        
        // Llamada al método createTransferencia y verificación del resultado
        assertTrue(Movimiento.createTransferencia(ingreso, egreso));
        
        // Verificar que el saldo de la cuenta origen se redujo en 100
        assertEquals(300, origen.getTotal());
        
        // Verificar que el saldo de la cuenta destino se aumentó en 100
        assertEquals(500, destino.getTotal());
	}

}
