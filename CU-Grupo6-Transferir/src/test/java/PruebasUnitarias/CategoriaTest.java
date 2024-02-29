package PruebasUnitarias;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import entities.Categoria;

class CategoriaTest {

	@Test
	void testGetAllOfIngresoType() {
		List<Categoria> categorias = Categoria.getAllOfIngresoType();
		assertEquals(1, categorias.size());
		
	}
	
	@Test
	void testCategoriaTransferencia() {
		Categoria categoria = Categoria.getCategoriaTransferencia();
		assertEquals("Tranferencia entre cuentas", categoria.getNombre());
		
	}
	
	
	
	
	

}
