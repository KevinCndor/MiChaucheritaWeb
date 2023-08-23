<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <title>Login</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css" />
</head>

<body>
    <main>
        <section class="LoginChaucherita ">
            <div class="divLogoInicio ">
                <h1>CHAUCHERITA</h1>
            </div>
            <div class="divFormularioIngreso fondoInicio">
                <h3 style="font-size: 50px;">Crear Una Cuenta</h3>
                <div>
                    <form method="POST" action="AccesoController?ruta=crearusuario" class="inputInicio inputInicio-container">
                        <div>
                            <p>Nombre</p><input type="text" name="nombre" placeholder="Ingrese su Nombre" required
                                class="styled-input">
                        </div>
                        <div>
                            <p>Apellido</p><input type="text" name="apellido" placeholder="Ingrese su Apellido" required
                                class="styled-input">
                        </div>
                        <div>
                            <p>Usuario</p><input type="text" name="usuario" placeholder="Ingrese su Usuario" required
                                class="styled-input">
                        </div>
                        <div>
                            <p>Contraseña</p><input type="password" name="contrasena"
                                placeholder="Ingrese su Contraseña" required class="styled-input">
                        </div>
                    </form>
                </div>
                <button type="submit" value="Iniciar" class="botonRegistrar fondoBotones">Crear</button>
                <p>¿Tienes una cuenta? ¡Inicia Sesión!</p>
                <a href="login.jsp" >
                    <button type="submit" value="CrearCuenta" class="botonRegistrar fondoBotones "> Regresar</button>
                </a>
                <p>Desarrollado Por Grupo 5 Aplicaciones Web</p>
            </div>
        </section>
    </main>
</body>

</html>