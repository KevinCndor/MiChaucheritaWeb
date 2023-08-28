<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movimiento</title>
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
		<section class="contenedorMovimiento fondoGrisClaro">
			<div class="sectionFiltros">
				<h3 class="seccionMovimiento">Movimientos</h3>
				<form action="MovimientoController?ruta=filtrar" method="POST">
					<div class="secciones" style="gap: 30px;">
						<select name="months" id="months" class="comboBox">
							<option value="-1" selected disabled="disabled">Seleccione
								un mes</option>
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
						</select> <select name="tipo" id="tipo" class="comboBox">
							<option value="-1" selected disabled="disabled">Seleccione
								una opcion</option>
							<option value="1">Ingreso</option>
							<option value="2">Egreso</option>
							<option value="3">Transferencias</option>
						</select>

						<button type="submit" class="botonSectionIzquierda fondoBotones">Buscar</button>
					</div>
				</form>
			</div>
			
			<div class="contenedorTabla" style="min-height: 516px">
				<table border="1" class="styled-table">
					<tr>
						<th>ID Movimiento</th>
						<th>Cuenta</th>
						<th>Categoría</th>
						<th>Subcategoría</th>
						<th>Tipo</th>
						<th>Monto</th>
						<th>Descripción</th>
						<th>Fecha</th>
						<th>Cuenta Destino</th>
					</tr>

					<c:forEach items="${movimientos}" var="movimiento">
						<tr>
							<td>${movimiento.id}</td>
							<td>${movimiento.cuenta.numeroCuenta}</td>
							<c:if test="${movimiento.tipoMovimiento ne 'Transferencia'}">
								<td>${movimiento.categoria.nombre}</td>
							</c:if>
							<c:if test="${movimiento.tipoMovimiento eq 'Transferencia'}">
								<td>-</td>
							</c:if>
							<c:if test="${movimiento.tipoMovimiento eq 'Egreso'}">
								<td>${movimiento.subcategoria.nombre}</td>
							</c:if>
							<c:if test="${movimiento.tipoMovimiento ne 'Egreso'}">
								<td>-</td>
							</c:if>
							<td>${movimiento.tipoMovimiento}</td>
							<td>${movimiento.valor}</td>
							<td>${movimiento.descripcion}</td>
							<td><fmt:formatDate value="${movimiento.fecha}"
									pattern="dd/MM/yyyy" /></td>
							<c:if test="${movimiento.tipoMovimiento eq 'Transferencia'}">
								<td>${movimiento.cuentaDestino.numeroCuenta}</td>
							</c:if>
							<c:if test="${movimiento.tipoMovimiento ne 'Transferencia'}">
								<td>-</td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
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
	<script src="https://kit.fontawesome.com/85e6f64c7f.js"
		crossorigin="anonymous"></script>
</body>
</html>