<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
					<li><a href="MovimientoController?ruta=mostrar&general=general">MOVIMIENTOS</a></li>
					<li><a href="AccesoController?ruta=inicio">CERRAR SESIÓN</a></li>
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
		<section class="sectionCentro">
			<!-- Contenedor Ingresos Egresos -->
			<div class="contenedorIngresoEgreso ">
				<div class="fondoGrisClaro Ingresos">
					<!-- Combo box de meses -->
					<div class="encabezadoSeccionIzquierda"
						style="display: flex; justify-content: center;">
			<form action="DashboardController?ruta=mostrar&filtromes=mes" method="POST" style="padding-right: 200px">
							<select name="months" id="months" class="comboBox">
								<option value="-1" selected disabled="disabled">Seleccione un mes</option>
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
							<button type="submit" class="botonSectionIzquierda fondoBotones" style="padding-left: 15px">Visualizar</button>
						</form>
					</div>
					<div class="encabezadoSeccionIzquierda">
						<h3>Ingresos</h3>
							<button class="botonSectionIzquierda fondoBotones" id="openIngreso">Nuevo Ingreso</button>
						<div id="modal_container_Ingreso"
							class="modal-container contenedorModal">
							<div class="modal">
								<h3>Nuevo Ingreso</h3>
								<form action="MovimientoController?ruta=nuevomovimiento&tipo=Ingreso" method="POST">
									<div
										style="display: flex; justify-content: space-between; padding-top: 15px;">
										<p>Categoría</p>
										<select name="categoriaIngreso" id="" class="styled-combo">
											<option value="default" selected disabled="disabled">Seleccione
												una categoria</option>
											<c:forEach items="${categoriasIngreso}"
												var="categoriaIngreso">
												<option value="${categoriaIngreso.id}"${categoriaIngreso.nombre ? 'selected':''}>${categoriaIngreso.nombre}</option>
											</c:forEach>
										</select>
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Cuenta</p>
										<select name="cuenta" id="" class="styled-combo">
											<option value="default" selected disabled="disabled">Seleccione una cuenta</option>
											<c:forEach items="${cuentas}" var="cuenta">
												<option value="${cuenta.nombre}" ${cuenta.nombre  ? 'selected':''}>${cuenta.nombre}</option>
											</c:forEach>
										</select>
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Valor</p>
										<input type="number" step="0.01" min = "0.01"
											placeholder="Introduce el Valor del ingreso" id="valorIngeso"
											class="styled-combo" name="valor">
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Fecha</p>
										<c:set var="fechaActual" value="<%= new java.util.Date() %>" />
										<fmt:formatDate var="fechaFormateada" value="${fechaActual}" pattern="yyyy-MM-dd" />
										<input type="date" min="2023-07-22" max="${fechaFormateada}" class="styled-combo" name="fecha">		
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Descripción</p>
										<input type="text" placeholder="Introduce una Descripción "
											id="valorIngreso" class="styled-combo" name="descripcion">
									</div>

									<div class="contenedorBotonesModal">
										<button id="guardarIngreso"
											class="botonSectionIzquierda fondoBotones">Guardar</button>
									</div>
								</form>
								<div class="contenedorBotonesModal">
									<button id="closeIngreso"
										class="botonSectionIzquierda fondoBotones">Cancelar</button>
								</div>
							</div>
						</div>
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
							<button class="botonSectionIzquierda fondoBotones" id="openEgreso">Nuevo Egreso</button>
						<div id="modal_container_Egreso"
							class="modal-container contenedorModal">
							<div class="modal">
								<h3>Nuevo Egreso</h3>
								<form action="MovimientoController?ruta=nuevomovimiento&tipo=Egreso" method="POST">
									<div>
										<div
											style="display: flex; justify-content: space-between; padding-top: 15px;">
											<p>Categoría</p>
											    <select name="categoriaEgreso" id="categoriaEgreso" class="styled-combo" onchange="enviarCategoria(${categoriaEgresos.id})">
											        <option value="default" selected disabled="disabled">Seleccione una categoria</option>
											        <c:forEach items="${categoriasEgreso}" var="categoriaEgresos">
											        <option value="${categoriaEgresos.id}" ${categoriaEgresos.nombre ? 'selected':''}>${categoriaEgresos.nombre}</option>
											   		</c:forEach>
											    </select>
										</div>
										<div
											style="display: flex; justify-content: space-between; padding-top: 15px;">
											<p>Subcategoría</p>
											<select name="subcategoriaEgreso" id="subcategoriaEgreso" class="styled-combo">
												<option value="default" selected disabled="disabled">Seleccione una subcategoria</option>
												<c:if test="${subcategorias!=null}">
													<c:forEach items="${subcategorias}" var="subcategoria">
														<option value="${subcategoria.id}"
															${subcategoria.nombre ? 'selected':''}>${subcategoria.nombre}</option>
														<!--  <option value="Persona">Peluquería</option>-->
													</c:forEach>
												</c:if>
												<c:if test="${subcategorias==null}">
													<option value="default"></option>
												</c:if>
											</select>
										</div>
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Cuenta</p>
										<select name="cuenta" id="categoriaEgreso" class="styled-combo">
											<option value="default" selected disabled="disabled">Seleccione una cuenta</option>
											<c:forEach items="${cuentas}" var="cuenta">
												<option value="${cuenta.nombre}" ${cuenta.nombre ? 'selected':''}>${cuenta.nombre}</option>
												<!--  <option value="Cuenta1">Produbanco</option> -->
											</c:forEach>
										</select>
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Valor</p>
										<input type="number" step="0.01" min = "0.01"
											placeholder="Introduce el Valor del egreso" id="valorEgreso"
											class="styled-combo" name="valor">
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Fecha</p>
										<c:set var="fechaActual" value="<%= new java.util.Date() %>" />
										<fmt:formatDate var="fechaFormateada" value="${fechaActual}" pattern="yyyy-MM-dd" />
										<input type="date" min="2023-07-22" max="${fechaFormateada}" class="styled-combo" name="fecha">			
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Descripción</p>
										<input type="text" placeholder="Introduce una Descripción "
											id="descripcionEgreso" class="styled-combo"
											name="descripcion">
									</div>
									<div class="contenedorBotonesModal">
										<button id="guardarEgreso"
											class="botonSectionIzquierda fondoBotones">Guardar</button>
									</div>
								</form>
								<div class="contenedorBotonesModal">
									<button id="closeEgreso"
										class="botonSectionIzquierda fondoBotones">Cancelar</button>
								</div>
							</div>
						</div>
					</div>
					<c:forEach items="${egresos}" var="egreso">
						<div class="extra" id="contenedorEgresosTotal">
							<div class=" fondoCuadroInformacion ">
								<div
									style="display: flex; align-items: center; justify-content: space-between; padding-left: 15px; padding-right: 15px;">
									<p>${egreso.categoria.nombre}</p>
									<p>-${egreso.categoria.valor}</p>
								</div>
								<div class="contenedorCategoriaEgresos">
									<c:forEach items="${egresosSubcategoria}" var="egresosSub">
										<c:if test="${egreso.categoria.id == egresosSub.categoria.id}">
											<div class="contenedorValoresEgresos">
												<p>$ ${egresosSub.subcategoria.valor}</p>
												<h5>${egresosSub.subcategoria.nombre}</h5>
											</div>
										</c:if>
									</c:forEach>
								</div>

							</div>
						</div>
					</c:forEach>
					<br>
				</div>
			</div>
			<!-- fin contenedorIngresoEgreso -->
			<div class="fondoGrisClaro MisCuentas borderContenedorMisCuentas">
				<div class="encabezadoSeccionIzquierda"
					style="padding-bottom: 20px;">
					<h3>Mis Cuentas</h3>
					<a href="MovimientoController?ruta=nuevatransferencia"><button
							class="botonSectionMisCuentas fondoBotones">Realizar Transferencia</button></a>
				</div>
				<div
					style="display: flex; justify-content: space-between; padding-left: 70px; padding-right: 70px;">
					<button class="botonSectionMisCuentas fondoBotones" id="openAgregarCuenta">Agregar Cuenta</button>
					<div id="modal_container_AgregarCuenta"
						class="modal-container contenedorModal">
						<div class="modal2">
							<h3>Agregar Cuenta</h3>
							<form action="DashboardController?ruta=nuevacuenta" method="POST">
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
							<form action="DashboardController?ruta=eliminarcuenta"
								method="POST">
								<div
									style="display: flex; justify-content: space-between; padding: 20px 10px 20px 10px;">
									<p>Número</p>
									<input type="number" name = "numero"
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
					<form id="cuentaForm" action="MovimientoController?ruta=mostrar"
						method="POST">
						<c:forEach items="${cuentas}" var="cuenta">
							<div class="contenedorCuenta"
								onclick="enviarCuenta('${cuenta.nombre}')">
								<p style="font-size: 30px; padding-left: 20px;" name="nombre"
									value="${cuenta.nombre}">${cuenta.nombre}</p>
								<div class="numCuenta">
									<p>${cuenta.numeroCuenta}</p>
									<p>${cuenta.saldo}</p>
								</div>
							</div>
						</c:forEach>
						<input type="hidden" id="cuentaNombre" name="nombre" value="">
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
	<script src="${pageContext.request.contextPath}/js/app.js"></script>
	<script>
		function enviarCuenta(nombreCuenta) {
			console.log(nombreCuenta);
			// Establecer el valor del campo oculto con el nombre de la cuenta
			document.getElementById("cuentaNombre").value = nombreCuenta;
			// Enviar el formulario
			document.getElementById("cuentaForm").submit();
		}
	</script>

	<script src="https://kit.fontawesome.com/85e6f64c7f.js"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>

</html>