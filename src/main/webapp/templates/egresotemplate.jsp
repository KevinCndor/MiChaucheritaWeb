<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="modal_container_Egreso"
		class="modal-container contenedorModal">
		<div class="modal">
			<h3>Nuevo Egreso</h3>
			<form action="MovimientoController?ruta=nuevomovimiento&tipo=Egreso"
				method="POST">
				<div>
					<div
						style="display: flex; justify-content: space-between; padding-top: 15px;">
						<p>Categoría</p>
						<select name="categoriaEgreso" id="" class="styled-combo">
							<option value="default" selected disabled="disabled">Seleccione
								una categoria</option>
							<c:forEach items="${categoriasEgresos}" var="categoriaEgresos">
								<option value="${categoriaEgresos.id}"
									${categoriaEgresos==categoriaEgresos ? 'selected':''}>${categoriaEgresos.nombre}</option>
							</c:forEach>
						</select>
					</div>
					<div
						style="display: flex; justify-content: space-between; padding-top: 15px;">
						<p>Subcategoría</p>
						<select name="subcategoriaEgreso" id="" class="styled-combo">
							<option value="default" selected disabled="disabled">Seleccione
								una subcategoria</option>
							<c:forEach items="${subcategorias}" var="subcategoria">
								<option value="${subcategoria.nombre}"
									${subcategoria==subcategoria ? 'selected':''}>${subcategoria.nombre}</option>
								<!--  <option value="Persona">Peluquería</option>-->
							</c:forEach>
						</select>
					</div>
				</div>
				<div
					style="display: flex; justify-content: space-between; padding-top: 25px;">
					<p>Cuenta</p>
					<select name="cuentaEgreso" id="" class="styled-combo">
						<option value="default" selected disabled="disabled">Seleccione
							una cuenta</option>
						<c:forEach items="${cuentas}" var="cuenta">
							<option value="${cuenta.nombre}"
								${cuenta==cuenta ? 'selected':''}>${cuenta.nombre}</option>
							<!--  <option value="Cuenta1">Produbanco</option> -->
						</c:forEach>
					</select>
				</div>
				<div
					style="display: flex; justify-content: space-between; padding-top: 25px;">
					<p>Valor</p>
					<input type="number" placeholder="Introduce el Valor del egreso"
						id="valorEgreso" class="styled-combo" name="valor">
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
						id="descripcionEgreso" class="styled-combo" name="descripcion">
				</div>
				<div class="contenedorBotonesModal">
					<button id="guardarEgreso"
						class="botonSectionIzquierda fondoBotones">Guardar</button>
				</div>
			</form>
			<div class="contenedorBotonesModal">
				<button id="closeEgreso" class="botonSectionIzquierda fondoBotones">Cancelar</button>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/app.js">
	</script>
</body>
</html>