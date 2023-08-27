<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/estilos.css" />
</head>
<body>
	<div id="modal_container_Ingreso"
		class="modal-container contenedorModal">
		<div class="modal">
			<h3>Nuevo Ingreso</h3>
			<form action="MovimientoController?ruta=movimiento&tipo=Ingreso"
				method="POST">
				<div
					style="display: flex; justify-content: space-between; padding-top: 15px;">
					<p>Categoría</p>
					<select name="categoriaIngreso" id="" class="styled-combo">
						<option value="default" selected disabled="disabled">Seleccione
							una categoria</option>
						<c:forEach items="${categoriasIngreso}" var="categoriaIngreso">
							<option value="${categoriaIngreso.id}"
								${categoriaIngreso==categoriaIngreso ? 'selected':''}>${categoriaIngreso.nombre}</option>
						</c:forEach>
					</select>
				</div>
				<div
					style="display: flex; justify-content: space-between; padding-top: 25px;">
					<p>Cuenta</p>
					<select name="cuentaIngreso" id="" class="styled-combo">
						<option value="default" selected disabled="disabled">Seleccione
							una cuenta</option>
						<c:forEach items="${cuentas}" var="cuenta">
							<option value="${cuenta.nombre}"
								${cuenta==cuenta ? 'selected':''}>${cuenta.nombre}</option>
						</c:forEach>
					</select>
				</div>
				<div
					style="display: flex; justify-content: space-between; padding-top: 25px;">
					<p>Valor</p>
					<input type="number" placeholder="Introduce el Valor del ingreso"
						id="valorIngeso" class="styled-combo" name="valor">
				</div>
				<div
					style="display: flex; justify-content: space-between; padding-top: 25px;">
					<p>Fecha</p>
					<input type="date" min="2023-08-22" max="2030-08-22"
						class="styled-combo" name="fecha">
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
				<button id="closeIngreso" class="botonSectionIzquierda fondoBotones">Cancelar</button>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>