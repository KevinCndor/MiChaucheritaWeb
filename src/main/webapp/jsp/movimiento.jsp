<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movimiento</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css" />
</head>
<body>
    <main>
        <header class=" fondoGrisClaro">
            <div>
                <div>CHAUCHERITA</div>
            </div>
            <nav class="MenuHeader">
                <ul>
                    <a href="dashboard.html">  <li>REGRESAR</li></a>
                    <a href="index.html">  <li>CERRAR SESIÓN</li></a>
                </ul>
            </nav>
        </header>
        <section class="sectionBienvenida2 fondoCelesteDegradado">
        <!-- Aquí hice un cambiooooooooooooooooooooooooooo -->
            <h1 class="titulo1" >Bienvenido de vuelta, <c:out value="${sessionScope.usuarioLogeado.nombre}"/>!</h1>
            <div></div>
            <div></div>
        </section>
        <section class="contenedorMovimiento">
            <div class="sectionFiltros">
                <h3 class="seccionMovimiento">Movimientos</h3>

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

                <select name="categoria" id="categoria">
                    <option value="1">Ingreso</option>
                    <option value="2">Egreso</option>
                </select>
                
            </div>
            <div>

                <table border="1" class="styled-table">
                    <tr>
                        <th>ID Movimiento</th>
                        <th>De</th>
                        <th>A</th>
                        <th>Monto</th>
                        <th>Categoría</th>
                        <th>Descripción</th>
                        <th>Fecha</th>
                    </tr>
                    <tr>
                        <td>Dato 1</td>
                        <td>Dato 2</td>
                        <td>Dato 3</td>
                        <td>Dato 4</td>
                        <td>Dato 5</td>
                        <td>Dato 6</td>
                        <td>Dato 7</td>
                    </tr>

                    <!-- Puedes agregar más filas si lo deseas -->
                </table>
            </div>
        </section>

        <footer class="piepagina">
            <div> Desarrollado Por Grupo 5 Aplicaciones Web</div>
            <div>
                <h5>Síguenos En</h5>
                <i class="fa-brands fa-facebook fa-xl"></i>
                <i class="fa-brands fa-x-twitter fa-xl"></i>
                <i class="fa-brands fa-youtube fa-xl"></i>
            </div>
        </footer>
    </main>
    <script src="${pageContext.request.contextPath}/js/app.js">	</script>
    <script src="https://kit.fontawesome.com/85e6f64c7f.js" crossorigin="anonymous"></script>
</body>
</html>