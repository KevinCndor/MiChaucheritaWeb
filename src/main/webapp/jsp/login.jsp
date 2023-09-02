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
                <h1 style="padding-left: 140px">CHAUCHERITA</h1>
            </div>
            <div class="divFormularioIngreso fondoInicio">
                <h3 style="font-size: 50px;">Iniciar Sesión</h3>
                <div>
                    <form method="POST" action="../AccesoController?ruta=ingresar" class="inputInicio inputInicio-container">
                        <div>
                            <p>Usuario</p><input type="text" name="usuario" placeholder=" Ingrese su Usuario" required
                                class="styled-input">
                        </div>
                        <div>
                            <p>Contraseña</p><input type="password" name="contrasena"
                                placeholder="Ingrese su Contraseña" required class="styled-input">
                        </div>
                        <button type="submit" value="Iniciar" class="botonInicio fondoBotones">Iniciar</button>
                    </form>
                </div>
<<<<<<< HEAD
=======
                
>>>>>>> 993c7c2ca62124ab8edc65c163ca258b6513c382
                <p style="padding:30px">¿Sin cuenta? ¡Crea una!</p>
                <a href="../AccesoController?ruta=registrar">
                    <button type="submit" value="CrearCuenta" class="botonInicio fondoBotones "> Crear Cuenta</button>
                </a>
                <br>
                <br>
                <p>Desarrollado Por Grupo 5 Aplicaciones Web</p>
            </div>
        </section>
    </main>
</body>

</html>