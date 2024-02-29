<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Mi Chauchera</title>
<link rel="stylesheet" href="./css/style.css">
<script
	src="https://code.iconify.design/iconify-icon/1.0.7/iconify-icon.min.js"></script>

</head>

<body>
	<!-- nav -->

	<header class="header_pagina_principal">
		<button class="boton boton2 usuario_shortcut">
			<img src="https://picsum.photos/30" alt="imagen de usuario">
			<p>Usuario</p>
		</button>
		<div>
			<a href=""> <iconify-icon class="icono" icon="carbon:view-filled"
					width="18"></iconify-icon> <span>Dashboard</span>
			</a> <a href=""> <iconify-icon class="icono"
					icon="carbon:view-filled" width="18"></iconify-icon> <span>Configuración</span>
			</a>
		</div>
	</header>

	<!-- dashboard de cartera digital -->
	<main class="main_pagina_principal">
		<div class="contenedor_default dash_head">
			<header class="header_contenedor">
				<h3>Balance Total</h3>
				<button class="boton boton2 color_inactivo_boton">
					<iconify-icon class="icono" icon="carbon:view-filled" width="18"></iconify-icon>
				</button>
			</header>

			<main class="balance">
				<iconify-icon class="icono" icon="cryptocurrency:usd"
					style="color: white;" width="32"></iconify-icon>
				<h1>3000.00</h1>
			</main>



		</div>

		<div class="contenedor-resumen">
			<div class="contenedor_default">
				<!-- CUENTAS -->
				<header class="header_contenedor">
					<h3>Cuentas</h3>
				</header>


				<main class="cuentas">

					<c:forEach items="${cuentas}" var="cuenta">

						<div class="card-cuenta">
							<div>
								<div class=" cuenta_icono">
									<iconify-icon class="icono" icon="mdi:bank" width="32"></iconify-icon>
								</div>
								<h4>${cuenta.nombre}</h4>
								<p>${cuenta.total}</p>
							</div>
							<div class="botones_transacciones">

								<a href="RegistrarMovimientosController?ruta=nuevoingreso&idCuenta=${cuenta.id}"> <iconify-icon icon="ph:arrow-down-bold"
										width="18"></iconify-icon> <span class="texto_boton">Depositar</span>
								</a> 
								
								<a href=""> <iconify-icon icon="ph:arrow-up-bold"
										width="18"></iconify-icon> <span class="texto_boton">Retirar</span>
								</a> 
								
								<a href="RegistrarMovimientosController?ruta=nuevaTransferencia&idCuentaOrigen=${cuenta.id}"> <iconify-icon icon="mingcute:transfer-fill"
										width="18"></iconify-icon> <span class="texto_boton">Transferir</span>
								</a>

							</div>
						</div>
					</c:forEach>


				</main>

			</div>


			<!-- CATEGORIAS -->
			<div class="contenedor_default">

				<header class="header_contenedor">
					<h3>Resumen por categorÃ­a</h3>

				</header>
				<main class="clasificacion">
					<div class="card-cuenta">
						<div>
							<div class=" cuenta_icono">
								<iconify-icon class="icono" icon="mdi:bank" width="32"></iconify-icon>
							</div>
							<h4>Comida</h4>
							<p>-500.00</p>
						</div>
						<div class="botones_transacciones">

							<a href="ingreso.html"> <iconify-icon
									icon="ph:arrow-down-bold" width="18"></iconify-icon> <span
								class="texto_boton">Movimientos</span>
							</a>

						</div>
					</div>

				</main>
			</div>
<div class="contenedor_default grid_abajo">
    <header class="header_contenedor">
        <h3>Transacciones</h3>
    </header>

    <main id="transacciones" class="mostrar">
        <c:forEach items="${movimientos}" var="movimiento">
            <div class="transaccion">
                <div class="cuenta">
                    <div class="cuenta_icono">
                        <!-- Aquí puedes ajustar el icono según la cuenta de origen -->
                        <iconify-icon class="icono" icon="mdi:bank" width="20"></iconify-icon>
                    </div>
                    <div class="cuenta_info">
                        <p>Cuenta Origen: ${movimiento.origen.nombre}</p>
                    </div>
                </div>

                <div class="cuenta">
                    <div class="cuenta_icono">
                        <!-- Aquí puedes ajustar el icono según la cuenta de destino -->
                        <iconify-icon class="icono" icon="mdi:bank" width="20"></iconify-icon>
                    </div>
                    <div class="cuenta_info">
                        <p>Cuenta Destino: ${movimiento.destino.nombre}</p>
                    </div>
                </div>

                <div class="concepto">
                    <p>Concepto: ${movimiento.concepto}</p>
                </div>

                <div class="transaccion_monto">
                    <h4>Monto: ${movimiento.monto}</h4>
                </div>
                
                <div class="tipo_transaccion">
                    <h4>Tipo: ${movimiento.tipo}</h4>
                </div>

                <div class="transaccion_info">
                    <fmt:formatDate pattern="yyyy-MM-dd" value="${movimiento.fecha}" var="fechaFormateada" />
                    <p>Fecha: ${fechaFormateada}</p>
                </div>
            </div>
        </c:forEach>
    </main>
</div>
		</div>
	</main>
</body>

</html>