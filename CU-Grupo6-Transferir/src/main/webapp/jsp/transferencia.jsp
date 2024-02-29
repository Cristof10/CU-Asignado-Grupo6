<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/styleTransferencia.css">
    <script src="https://code.iconify.design/iconify-icon/1.0.7/iconify-icon.min.js"></script>
</head>

<body>
    <header class="header_pagina_principal">
        <button class="boton boton2 usuario_shortcut">
            <img src="https://picsum.photos/30" alt="imagen de usuario">
            <p>Usuario</p>
        </button>
        <div>
            <a href="dashboard.html">
                <iconify-icon class="icono" icon="carbon:view-filled" width="18"></iconify-icon>
                <span>Dashboard</span>
            </a>
            <a href="configuracion.html">
                <iconify-icon class="icono" icon="carbon:view-filled" width="18"></iconify-icon>
                <span>Configuración</span>
            </a>
        </div>
    </header>

    <div class="cuerpo_transferencias">
        <main class="main_pagina_principal">
            <div class="contenedor_default dash_head " style="margin-bottom: 0px">
                <header class="header_contenedor">
                    <h3>${cuentaOrigen.nombre}</h3>
                    <button class="boton boton2 color_inactivo_boton">
                        <iconify-icon class="icono" icon="carbon:view-filled" width="18"></iconify-icon>
                    </button>
                </header>
                <main class="balance">
                    <iconify-icon class="icono" icon="cryptocurrency:usd" style="color: white;"
                        width="32"></iconify-icon>
                    <h1>${cuentaOrigen.total}</h1>
                </main>
            </div>

            <div class="flechas">
                <iconify-icon class="icono" icon="carbon:arrow-right" width="32"></iconify-icon>
                <iconify-icon class="icono" icon="carbon:arrow-left" width="32"></iconify-icon>
            </div>

            <div class="flechas-resposive">
                <iconify-icon class="icono" icon="carbon:arrow-up" width="32"></iconify-icon>
                <iconify-icon class="icono" icon="carbon:arrow-down" width="32"></iconify-icon>
            </div>
			
			<div class="contenedor_default dash_head" style="margin-bottom: 0px">
			    <header class="header_contenedor">
			        <h3>Cuenta a Transferir</h3>
			        <div class="botones_remitente">
			            <button class="boton boton2 color_inactivo_boton">
			                <iconify-icon class="icono" icon="carbon:view-filled" width="18"></iconify-icon>
			            </button>
			            <select name="idCuenta" id="tipoCuenta" class="select-banco-boton">
			                <c:forEach items="${cuentasDestino}" var="c">
			                    <option value="${c.id}" data-total="${c.total}">${c.nombre}</option>
			                </c:forEach>
			            </select>
			        </div>
			    </header>
			    <main class="balance">
			        <iconify-icon class="icono" icon="cryptocurrency:usd" style="color: white;" width="32"></iconify-icon>
			        <h1 id="totalCuenta">${cuentaOrigen.total}</h1>
			    </main>
			</div>


        </main>

        <div class="contenedor-transferencia">
            <p class="titulo">Datos Transferencia</p>
            <form class="form-agregar-cuenta" action="RegistrarMovimientosController?ruta=guardarTransferencia" method="post">
			    <input type="hidden" name="idCuentaOrigen" value="${cuentaOrigen.id}">
			    <input type="hidden" name="idCuentasDestino" id="idCuentasDestino" value="${cuentasDestino[0].id}">
			    <label for="valor">Ingresa el monto a transferir </label>
			    <input type="text" name="valor" class="txt-nombre-banco" placeholder="Monto" pattern="^\d+(\.\d{1,2})?$">
			    <label for="concepto">Ingresa el motivo de transferencia </label>
			    <input type="text" name="concepto" class="txt-nombre-banco" placeholder="Motivo de transferencia">
			    <label for="fecha">Ingresa la Fecha </label>
			    <input type="date" name="fecha" class="fecha-transferencia" placeholder="Fecha de transferencia">
			    <label for="idCategoria">Selecciona categoria de la Cuenta</label>
			    <select name="idCategoria" id="tipo" class="select-categoria">
			        <option value="${categorias.id}">${categorias.nombre}</option>
			    </select>
			    <div class="btn-confirmacion">
			        <button type="submit" class="boton boton1">Transferir</button>
			        <button type="button" onclick="cancelar()" class="boton boton-cancelar">Cancelar</button>
			    </div>
			</form>
        </div>
    </div>
    
    
	    
	<script>
	    
	    var selectCuenta = document.getElementById("tipoCuenta");
	    var totalCuenta = document.getElementById("totalCuenta");
	    
	    var totalSeleccionado = selectCuenta.options[selectCuenta.selectedIndex].getAttribute("data-total");
	    totalCuenta.textContent = totalSeleccionado;
	    
	    selectCuenta.addEventListener("change", function() {
	    	var totalSeleccionado = selectCuenta.options[selectCuenta.selectedIndex].getAttribute("data-total");
	        totalCuenta.textContent = totalSeleccionado;
	    });
	    
	    
	    // Función para actualizar el valor de idCuentasDestino
	    function actualizarIdCuentasDestino() {
	        var selectCuenta = document.getElementById("tipoCuenta");
	        var idCuentasDestinoInput = document.querySelector('input[name="idCuentasDestino"]');
	        
	        // Obtener el valor seleccionado en el select
	        var valorSeleccionado = selectCuenta.value;
	        
	        // Actualizar el valor del input idCuentasDestino
	        idCuentasDestinoInput.value = valorSeleccionado;
	    }
	    
	    // Llamar a la función cuando cambie la selección en el select
	    document.getElementById("tipoCuenta").addEventListener("change", actualizarIdCuentasDestino);
	    
	    // Llamar a la función al cargar la página para asegurar que el valor esté actualizado
	    window.addEventListener("load", actualizarIdCuentasDestino);
	    
	    function cancelar() {
	        // Redirige al usuario al dashboard
	        window.location.href = 'DashboardController?ruta=ver';
	    }
	    
	</script>


</body>

</html>