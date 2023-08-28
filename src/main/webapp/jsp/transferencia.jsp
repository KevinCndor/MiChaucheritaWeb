<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Transferencia</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/estilos.css" />
</head>

<body>
	<main>
		<header class=" fondoGrisClaro">
			<div>
				<div>CHAUCHERITA</div>
			</div>
			<nav class="MenuHeader">
				<ul>
					<li><a href="DashboardController?ruta=mostrar">REGRESAR</a></li>
					<li><a href="AccesoController?ruta=inicio">CERRAR SESION</a></li>
				</ul>
			</nav>
		</header>

		<section class="sectionBienvenida fondoCelesteDegradado">
			<h1 class="titulo1">
				Bienvenido de vuelta,
				<c:out value="${sessionScope.usuarioLogeado.nombre}" />
				!
			</h1>
		</section>

		<section class="fondoMisCuentas">
			<h3 class="titulo1 seccionMovimiento">Nueva Transferencia</h3>
			<div class="botonCancelar">
					<a href="DashboardController?ruta=mostrar">
						<button type="submit" value="Cancelar"
							class="botonInicio fondoBotones" style="font-size: 30px;">Cancelar</button>
					</a>
				</div>
			<form class="inputInicio-container"
				action="MovimientoController?ruta=transferencia" method="POST">

				<div class="seccionTransferencia">
					<!-- Escoger cuenta de origen -->
					<div id="seccion1">
						<div class="centrarBotones">
							<div class="titulo2 seccionMovimiento">1. Elija la cuenta
								de origen</div>
							<div class="contenedorcuadrosCuentaTransferencia">

								<c:forEach items="${cuentas}" var="cuenta">
									<div class="contenedorCuentaTransferencia clickable-div">
										<div style="font-size: 30px; padding-left: 20px;"
											id="cuentaOrigen">${cuenta.nombre}</div>
										<div class="numCuenta">
											<p>${cuenta.numeroCuenta}</p>
											<p>$ ${cuenta.saldo}</p>
										</div>
									</div>
								</c:forEach>
							</div>
							<button id="showAll" class="botonInicio fondoBotones"
								style="font-size: 30px;">Regresar</button>
						</div>
					</div>

					<div id="seccion2">
						<!-- Escoger cuenta de destino -->
						<div class="centrarBotones">
							<div class="titulo2 seccionMovimiento">2. Elija la cuenta
								de destino</div>
							<div class="contenedorcuadrosCuentaTransferencia">

								<c:forEach items="${cuentas}" var="cuenta">
									<div class="contenedorCuentaTransferencia clickable-div1">
										<div style="font-size: 30px; padding-left: 20px;"
											id="cuentaDestino">${cuenta.nombre}</div>
										<div class="numCuenta">
											<p>${cuenta.numeroCuenta}</p>
											<p>$ ${cuenta.saldo}</p>
										</div>
									</div>
								</c:forEach>

							</div>
							<button id="showAll1" class="botonInicio fondoBotones"
								style="font-size: 30px;">Regresar</button>
						</div>
					</div>

					<!-- Info Transferencia -->
					<div id="seccion3">
						<div class="centrarBotones2">
							<div class="titulo2 seccionMovimiento">3. Informacion de
								transferencia</div>
							<div
								class="fondoCuadroInformacion contenedorcuadrosCuentaTransferencia">
								<div>
									<div class="titulo2">Monto</div>
									<input type="text" name="monto" placeholder="$00.00" required
										class="styled-inputTransferenciaMonto">
								</div>
								<div>
									<div class="titulo2">Descripcion</div>
									<input type="text" name="descripcion" required
										class="styled-inputTransferenciaDescripcion">
								</div>
							</div>

							<button type="submit" value="HacerTransferencia"
								class="botonInicio fondoBotones">Hacer Transferencia</button>
						</div>
					</div>
				</div>
			</form>
		</section>

		<footer class="piepagina">
			<div>Desarrollado Por Grupo 5 Aplicaciones Web</div>
			<div>
				<h5>Siguenos En</h5>
				<i class="fa-brands fa-facebook fa-xl"></i> <i
					class="fa-brands fa-x-twitter fa-xl"></i> <i
					class="fa-brands fa-youtube fa-xl"></i>
			</div>
		</footer>

	</main>
	<script src="${pageContext.request.contextPath}/js/app1.js"></script>
	<script src="https://kit.fontawesome.com/85e6f64c7f.js"
		crossorigin="anonymous"></script>
	<script>
		function mostrarSeccion(numero) {
			document.getElementById(`seccion${numero}`).classList
					.remove('hidden');
		}
	</script>
</body>

</html>