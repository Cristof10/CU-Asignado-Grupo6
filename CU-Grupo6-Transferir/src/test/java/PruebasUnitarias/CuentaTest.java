package PruebasUnitarias;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import entities.Cuenta;

class CuentaTest {

	@Test
	void testGetById() {
		Cuenta cuenta = Cuenta.getById(1);
		assertEquals(700, cuenta.getTotal());
	}
	
	@Test
	void testGetAllDestinos() {
		List<Cuenta> cuentasDetino = Cuenta.getAllDestinos(1);
		assertEquals(3,cuentasDetino.size());
		
	}

}
