<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dashboard</title>
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
					<a href="/movimiento.jsp">
						<li>MOVIMIENTOS</li>
					</a>
					<a href="../index.jsp">
						<li>CERRAR SESIÓN</li>
					</a>
				</ul>
			</nav>
		</header>
		<section class="sectionBienvenida fondoCelesteDegradado">
			<h1 class="titulo1">
				Bienvenido de vuelta
				<c:out value="${sessionScope.usuarioLogeado.nombre}" />
				!
			</h1>
		</section>
		<section class="sectionCentro">
			<!-- Contenedor Ingresos Egresos -->
			<div class="contenedorIngresoEgreso ">
				<div class="fondoGrisClaro Ingresos">
					<!-- Combo box de meses -->
					<div class="encabezadoSeccionIzquierda"
						style="display: flex; justify-content: center;">
						<form action="DashboardController?ruta=mostrar&filtromes=mes"
							method="POST">
							<select name="months" id="months">
								<option value="-1" selected>Seleccione un mes</option>
								<option value="0">Enero</option>
								<option value="1">Febrero</option>
								<option value="2">Marzo</option>
								<option value="3">Abril</option>
								<option value="4">Mayo</option>
								<option value="5">Junio</option>
								<option value="6">Julio</option>
								<option value="7">Agosto</option>
								<option value="8">Septiembre</option>
								<option value="9">Octubre</option>
								<option value="10">Noviembre</option>
								<option value="11">Diciembre</option>
							</select>
							<button type="submit">Visualizar</button>
						</form>
					</div>
					<div class="encabezadoSeccionIzquierda">
						<h3>Ingresos</h3>
						<form action="MovimientoController?ruta=movimiento&tipo=Ingreso"
							method="POST">
							<button class="botonSectionIzquierda fondoBotones"
								id="openIngreso">Nuevo Ingreso</button>
						</form>
						<%@ include file='../templates/ingresotemplate.jsp'%>
					</div>
					<br>
					<div style="padding-left: 100px;" id="contenedorIngresosTotal">
						<div class="contenedorCategoriaIngresos ">
							<c:forEach items="${ingresos}" var="ingreso">
								<div class="contenedorValoresIngesos">
									<p>$ ${ingreso.valor}</p>
									<h5>${ingreso.categoria.nombre}</h5>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="fondoGrisClaro Egresos ">
					<div class="encabezadoSeccionIzquierda">
						<h3>Egresos</h3>
						<form action="MovimientoController?ruta=movimiento&tipo=Egreso">
							<button class="botonSectionIzquierda fondoBotones"
								id="openEgreso">Nuevo Egreso</button>
						</form>
						<%@ include file='../templates/egresotemplate.jsp'%>
					</div>
					<div class="extra" id="contenedorEgresosTotal">
						<div class=" fondoCuadroInformacion ">
							<c:forEach items="${egresos}" var="egreso">
								<div
									style="display: flex; align-items: center; justify-content: space-between; padding-left: 15px; padding-right: 15px;">
									<p>${egreso.nombre}</p>
									<p>-${egreso.valor}</p>
								</div>
								<c:forEach items="${egresosSubcategoria}" var="subcategoria">
									<div class="contenedorCategoriaEgresos">
										<div class="contenedorValoresEgresos">
											<c:if
												test="${egreso.categoria==subcategoria.categoriaPadre}">
												<p>$ ${subcategoria.valor}</p>
												<h5>${subcategoria.nombre}</h5>
											</c:if>
											<c:if test="${egreso.categoria==null}">
												<p></p>
												<h5></h5>
											</c:if>
										</div>
									</div>
								</c:forEach>
							</c:forEach>
						</div>
					</div>
					<br>
				</div>
			</div>
			<!-- fin contenedorIngresoEgreso -->
			<div class="fondoGrisClaro MisCuentas borderContenedorMisCuentas">
				<div class="encabezadoSeccionIzquierda"
					style="padding-bottom: 20px;">
					<h3>Mis Cuentas</h3>
					<a href="trasferencia.jsp"><button
							class="botonSectionMisCuentas fondoBotones">Realizar
							Transferencia</button></a>
				</div>
				<div
					style="display: flex; justify-content: space-between; padding-left: 70px; padding-right: 70px;">
					<button class="botonSectionMisCuentas fondoBotones"
						id="openAgregarCuenta">Agregar Cuenta</button>
					<div id="modal_container_AgregarCuenta"
						class="modal-container contenedorModal">
						<div class="modal2">
							<h3>Agregar Cuenta</h3>
							<form action=".........DashboardController" method="POST">
								<div
									style="display: flex; justify-content: space-between; padding: 20px 10px 20px 10px;">
									<p>Nombre</p>
									<input type="text"
										placeholder="Introduce el nombre de la cuenta"
										id="nombreCuenta" class="styled-combo2" name="nombre">
								</div>
								<div
									style="display: flex; justify-content: space-between; padding: 20px 10px 20px 10px;">
									<p>Número</p>
									<input type="number"
										placeholder="Introduce el número de la cuenta"
										id="numeroCuenta" class="styled-combo2" name="numero">
								</div>
								<div
									style="display: flex; justify-content: space-between; padding: 20px 10px 20px 10px;">
									<p>Saldo</p>
									<input type="number"
										placeholder="Introduce el saldo en la cuenta" id="SaldoCuenta"
										class="styled-combo2" name="saldo">
								</div>

								<div class="contenedorBotonesModal">
									<button id="guardarAgregarCuenta"
										class="botonSectionIzquierda fondoBotones">Guardar</button>
								</div>
							</form>
							<div class="contenedorBotonesModal">
								<button id="closeAgregarCuenta"
									class="botonSectionIzquierda fondoBotones">Cancelar</button>
							</div>
						</div>
					</div>
					<button class="botonSectionMisCuentas fondoBotones"
						id="openEliminarCuenta">Eliminar Cuenta</button>
					<div id="modal_container_EliminarCuenta"
						class="modal-container contenedorModal">
						<div class="modalEliminarCuenta">
							<h3>Eliminar Cuenta</h3>
							<form action="........DashboardController" method="POST">
								<div
									style="display: flex; justify-content: space-between; padding: 20px 10px 20px 10px;">
									<p>Número</p>
									<input type="number"
										placeholder="Introduce el número de la cuenta"
										id="numeroCuenta" class="styled-combo2">
								</div>

								<div class="contenedorBotonesModal">
									<button id="guardarEliminarCuenta"
										class="botonSectionIzquierda fondoBotones">Eliminar</button>
								</div>
							</form>
							<div class="contenedorBotonesModal">
								<button id="closeEliminarCuenta"
									class="botonSectionIzquierda fondoBotones">Cancelar</button>
							</div>
						</div>
					</div>
				</div>
				<div class="contenedorcuadrosCuenta">
					<form action="MovimientoController?ruta=mostrar" method="POST">
						<c:forEach items="${cuentas}" var="cuenta">
							<a href="MovimientoController?ruta=mostrar">
								<div class="contenedorCuenta">
									<p style="font-size: 30px; padding-left: 20px;" name="nombre"
										value="${cuenta.nombre}">${cuenta.nombre}</p>
									<div class="numCuenta">
										<p>${cuenta.numeroCuenta}</p>
										<p>${cuenta.saldo}</p>
									</div>
								</div>
							</a>
						</c:forEach>
					</form>
				</div>
			</div>
		</section>
		<footer class="piepagina">
			<div>Desarrollado Por Grupo 5 Aplicaciones Web</div>
			<div>
				<h5>Síguenos En</h5>
				<i class="fa-brands fa-facebook fa-xl"></i> <i
					class="fa-brands fa-x-twitter fa-xl"></i> <i
					class="fa-brands fa-youtube fa-xl"></i>
			</div>
		</footer>
	</main>
	<script src="${pageContext.request.contextPath}/js/app.js">
		
	</script>
	<script src="https://kit.fontawesome.com/85e6f64c7f.js"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>

</html>