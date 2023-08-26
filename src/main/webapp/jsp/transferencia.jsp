<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
					<a href="DashboardController?ruta=mostrar">
						<li>REGRESAR</li>
					</a>
					<a href="AccesoController?ruta=inicio">
						<li>CERRAR SESI�N</li>
					</a>
				</ul>
			</nav>
		</header>
		<section class="sectionBienvenida fondoCelesteDegradado">
			<h1 class="titulo1">
				Bienvenido de vuelta,
				<c:out value="${sessionScope.usuarioLogeado.nombre}" />
				!
			</h1>
			<div></div>
			<div></div>
		</section>

		<section class="fondoMisCuentas">
			<h3 class="titulo1 seccionMovimiento">Nueva Transferencia</h3>
			<div class="seccionMovimiento">

				<!-- Escoger cuenta de origen -->
				<div class="centrarBotones">
					<div class="titulo2 seccionMovimiento">1. Elija la cuenta de origen</div>
					<div class="contenedorcuadrosCuentaTransferencia">

						<c:forEach items="${cuentas}" var="cuenta">
							<div class="contenedorCuentaTransferencia clickable-div">
								<div style="font-size: 30px; padding-left: 20px;">${cuenta.nombre}</div>
								<div class="numCuenta">
									<p>${cuenta.numeroCuenta}</p>
									<p>$ ${cuenta.saldo}</p>
								</div>
							</div>
						</c:forEach>

					</div>
					<a href="">
						<button type="submit" value="regresar" class="botonInicio fondoBotones" style="font-size: 30px;">Regresar</button>
					</a>
				</div>

				<!-- Escoger cuenta de destino -->
				<div class="centrarBotones">
					<div class="titulo2 seccionMovimiento">2. Elija la cuenta de destino</div>
					<div class="contenedorcuadrosCuentaTransferencia">

						<c:forEach items="${cuentas}" var="cuenta">
							<div class="contenedorCuentaTransferencia clickable-div1">
								<div style="font-size: 30px; padding-left: 20px;">${cuenta.nombre}</div>
								<div class="numCuenta">
									<p>${cuenta.numeroCuenta}</p>
									<p>$ ${cuenta.saldo}</p>
								</div>
							</div>
						</c:forEach>

					</div>
					<!--  <a href=""> -->
						<button type="submit" value="regresar" class="botonInicio fondoBotones" style="font-size: 30px;">Regresar</button>
					<!-- </a> -->
				</div>

				<!-- Info Transferencia -->
				<div class="centrarBotones">
					<div class="titulo2 seccionMovimiento">3. Informaci�n de transferencia</div>
					<div class="fondoCuadroInformacion contenedorFormTransferencia">
						
						<form class="inputInicio-container" action="MovimientoController?ruta=transferencia" method="POST">
							<div>
								<div class="titulo2">Monto</div>
								<input type="text" name="monto" placeholder="$00.00" required class="styled-inputTransferencia">
							</div>
							<div>
								<div class="titulo2">Descripci�n</div>
								<input type="text" name="descripcion" required class="styled-inputTransferencia2">
							</div>

							<!-- <a href=""> -->
								<button type="submit" value="HacerTransferencia" class="botonInicio fondoBotones">Hacer Transferencia</button>
							<!-- </a> -->
						</form>
					</div>

					<a href="DashboardController?ruta=mostrar">
						<button type="submit" value="Cancelar" class="botonInicio fondoBotones ">Cancelar</button>
					</a>
				</div>

			</div>
		</section>

		<footer class="piepagina">
			<div>Desarrollado Por Grupo 5 Aplicaciones Web</div>
			<div>
				<h5>S�guenos En</h5>
				<i class="fa-brands fa-facebook fa-xl"></i> <i
					class="fa-brands fa-x-twitter fa-xl"></i> <i
					class="fa-brands fa-youtube fa-xl"></i>
			</div>
		</footer>
	</main>

	<script src="${pageContext.request.contextPath}/js/app1.js">
		
	</script>
	<script src="https://kit.fontawesome.com/85e6f64c7f.js"
		crossorigin="anonymous"></script>
</body>

</html>