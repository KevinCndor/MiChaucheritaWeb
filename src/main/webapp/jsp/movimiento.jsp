<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
					<a href="DashboardController?ruta=mostrar">
						<li>REGRESAR</li>
					</a>
					<a href="AccesoController?ruta=inicio">
						<li>CERRAR SESIÓN</li>
					</a>
				</ul>
			</nav>
		</header>
		<section class="sectionBienvenida2 fondoCelesteDegradado">
			<h1 class="titulo1">
				Bienvenido de vuelta,
				<c:out value="${sessionScope.usuarioLogeado.nombre}" />
				!
			</h1>
			<div></div>
			<div></div>
		</section>
		<section class="contenedorMovimiento">
			<div class="sectionFiltros">
				<h3 class="seccionMovimiento">Movimientos</h3>
				
				<form action="MovimientoController?ruta=mostrar&filtromes=mes&filtrotipo=tipo" method="POST">
					<select name="months" id="months">
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
					
					<select name="categoria" id="categoria">
						<option value="-1" selected disabled="disabled">Seleccione una opcion</option>
						<option value="1">Ingreso</option>
						<option value="2">Egreso</option>
						<option value="3">Transferencias</option>
					</select>

					<button type="submit">Buscar</button>
				</form>

			</div>
			<div>

				<table border="1" class="styled-table">
					<tr>
						<th>ID Movimiento</th>
						<th>Cuenta</th>
						<th>Categoría/th>
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
						<td>${movimiento.cuenta}</td>
						<td>${movimiento.categoria}</td>
						<td>${movimiento.subcategoria}</td>
						<td>${movimiento.tipo_movimiento}</td>
						<td>${movimiento.valor}</td>
						<td>${movimiento.descripcion}</td>
						<td>${movimiento.fecha}</td>
						<td>${movimiento.cuentaDestino}</td>
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
	<script src="https://kit.fontawesome.com/85e6f64c7f.js" crossorigin="anonymous"></script>
</body>
</html>