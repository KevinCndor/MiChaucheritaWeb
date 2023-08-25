<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					<a href="/movimiento.jsp" >
						<li>MOVIMIENTOS</li>
					</a>
					<a href="../index.jsp" >
						<li>CERRAR SESIÓN</li>
					</a>
				</ul>
			</nav>
		</header>
		<section class="sectionBienvenida fondoCelesteDegradado">
			<h1 class="titulo1">Bienvenido de vuelta <c:out value="${sessionScope.usuarioLogeado.nombre}"/>!</h1>
		</section>
		<section class="sectionCentro">
			<div>
				<div class="fondoGrisClaro Ingresos">
					<!-- Combo box de meses -->
					<div class="encabezadoSeccionIzquierda"
						style="display: flex; justify-content: center;">
						<form action="DashboardController?ruta=mostrar&filtromes=mes" method="POST">
						<select name="months" id="months">
					       <option value="-1">Seleccione un mes</option>
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
						<button class="botonSectionIzquierda fondoBotones"
							id="openIngreso">Nuevo Ingreso</button>
						<div id="modal_container_Ingreso"
							class="modal-container contenedorModal">
							<div class="modal">
								<h3>Nuevo Ingreso</h3>
								<form action="">
									<div style="display: flex; justify-content: space-between; padding-top: 15px;">
										<p>Categoría</p>
										<select name="categoriaIngreso" id="" class="styled-combo">
											<option value="Nomina">Nomina</option>
											<option value="Senecyt">Senecyt</option>
											<option value="Senecyt">Universidad</option>
										</select>
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Cuenta</p>
										<select name="cuentaEgreso" id="" class="styled-combo">
											<option value="Cuenta1">Produbanco</option>
											<option value="Cuenta2">Pichincha Ahorros</option>
											<option value="Cuenta3">Pacifico Ahorros</option>
										</select>
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Valor</p>
										<input type="number"
											placeholder="Introduce el Valor del ingreso" id="valorIngeso"
											class="styled-combo"> 
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Fecha</p>
										<input type="date" min="2023-08-22" max="2030-08-22"
											class="styled-combo">
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Descripción</p>
										<input type="text" placeholder="Introduce una Descripción "
											id="valorIngreso" class="styled-combo">
									</div>
								</form>
								<div class="contenedorBotonesModal">
									<button id="guardarIngreso"
										class="botonSectionIzquierda fondoBotones">Guardar</button>
									<button id="closeIngreso"
										class="botonSectionIzquierda fondoBotones">Cancelar</button>
								</div>
							</div>
						</div>
					</div>
					<br>
					<div style="padding-left: 100px;">
						<div class="contenedorCategoriaIngresos ">
							<c:forEach items ="${ingresos}" var="ingreso">
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
						<button class="botonSectionIzquierda fondoBotones" id="openEgreso">Nuevo
							Egreso</button>
						<div id="modal_container_Egreso"
							class="modal-container contenedorModal">
							<div class="modal">
								<h3>Nuevo Egreso</h3>
								<form action="" method="">
									<div
										style="display: flex; justify-content: space-between; padding-top: 15px;">
										<p>Categoría</p>
										<select name="categoriaEgreso" id="" class="styled-combo">
											<option value="Persona">Persona</option>
										</select>
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 15px;">
										<p>Subcategoría</p>
										<select name="categoriaEgreso" id="" class="styled-combo">
											<option value="Persona">Peluquería</option>
										</select>
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Cuenta</p>
										<select name="cuentaEgreso" id="" class="styled-combo">
											<option value="Cuenta1">Produbanco</option>
											<option value="Cuenta2">Pichincha Ahorros</option>
											<option value="Cuenta3">Pacifico Ahorros</option>
										</select>
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Valor</p>
										<input type="number"
											placeholder="Introduce el Valor del egreso" id="valorEgreso"
											class="styled-combo">
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Fecha</p>
										<input type="date" min="2023-08-22" max="2030-08-22"
											class="styled-combo">
									</div>
									<div
										style="display: flex; justify-content: space-between; padding-top: 25px;">
										<p>Descripción</p>
										<input type="text" placeholder="Introduce una Descripción "
											id="descripcionEgreso" class="styled-combo">
									</div>
								</form>
								<div class="contenedorBotonesModal">
									<button id="guardarEgreso"
										class="botonSectionIzquierda fondoBotones">Guardar</button>
									<button id="closeEgreso"
										class="botonSectionIzquierda fondoBotones">Cancelar</button>
								</div>
							</div>
						</div>
					</div>
					<div class="extra">
						<div class=" fondoCuadroInformacion ">
							<c:forEach items="categorias" var="categoria">
								<div
									style="display: flex; align-items: center; justify-content: space-between; padding-left: 15px; padding-right: 15px;">
									<p>${categoria.nombre}</p>
									<p>-${categoria.valor}</p>
								</div>
								<c:forEach items="${categoria.subcategoria}" var="subcategoria">
									<div class="contenedorCategoriaEgresos">
										<div class="contenedorValoresEgresos">
											<p>$ ${subcategoria.valor}</p>
											<h5>${subcategoria.nombre}</h5>
										</div>
									</div>
								</c:forEach>
							</c:forEach>
						</div>
					</div>
					<br>
				</div>
			</div>
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
							<form action="" method="">
								<div
									style="display: flex; justify-content: space-between; padding: 20px 10px 20px 10px;">
									<p>Nombre</p>
									<input type="text"
										placeholder="Introduce el nombre de la cuenta"
										id="nombreCuenta" class="styled-combo2">
								</div>
								<div
									style="display: flex; justify-content: space-between; padding: 20px 10px 20px 10px;">
									<p>Número</p>
									<input type="number"
										placeholder="Introduce el número de la cuenta"
										id="numeroCuenta" class="styled-combo2">
								</div>
								<div
									style="display: flex; justify-content: space-between; padding: 20px 10px 20px 10px;">
									<p>Saldo</p>
									<input type="number"
										placeholder="Introduce el saldo en la cuenta" id="SaldoCuenta"
										class="styled-combo2">
								</div>
							</form>
							<div class="contenedorBotonesModal">
								<button id="guardarAgregarCuenta"
									class="botonSectionIzquierda fondoBotones">Guardar</button>
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
							<form action="">
								<div
									style="display: flex; justify-content: space-between; padding: 20px 10px 20px 10px;">
									<p>Número</p>
									<input type="number"
										placeholder="Introduce el número de la cuenta"
										id="numeroCuenta" class="styled-combo2">
								</div>
							</form>
							<div class="contenedorBotonesModal">
								<button id="guardarEliminarCuenta"
									class="botonSectionIzquierda fondoBotones">Eliminar</button>
								<button id="closeEliminarCuenta"
									class="botonSectionIzquierda fondoBotones">Cancelar</button>
							</div>
						</div>
					</div>
				</div>
				<div class="contenedorcuadrosCuenta">
					<c:forEach items="${cuentas}" var="cuenta">
						<a href= "movimiento.jsp">
							<div class="contenedorCuenta">
								<p style="font-size: 30px; padding-left: 20px;">${cuenta.nombre}</p>
								<div class="numCuenta">
									<p>${cuenta.numeronumeroCuenta}</p>
									<p>${cuenta.saldo}</p>
								</div>
							</div>
						</a>
					</c:forEach>
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
</body>

</html>