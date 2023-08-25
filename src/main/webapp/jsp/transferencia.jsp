<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transferencia</title>
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
        <section class="sectionBienvenida fondoCelesteDegradado">
            <h1 class="titulo1" >Bienvenido de vuelta, Jean..!</h1>
            <div></div>
            <div></div>
        </section>
        
        <section class="fondoMisCuentas">
            <h3 class="titulo1 seccionMovimiento">Nueva Transferencia</h3>
            <div class="seccionMovimiento">
                
                <!-- Escoger cuenta de origen -->
                <div class="centrarBotones">
                    <div class="titulo2 seccionMovimiento">1. Elija la cuenta de origen</div>
                    <div class="contenedorcuadrosCuentaTransferencia">
                        
                        <div class="contenedorCuentaTransferencia clickable-div">
                            <div style="font-size: 30px; padding-left: 20px;">Produbanco1</div>
                            <div class="numCuenta">
                                <p>0123456789</p>
                                <p>$0,000.00</p>
                            </div>
                        </div>

                        <div class="contenedorCuentaTransferencia clickable-div">
                            <div style="font-size: 30px; padding-left: 20px;">Produbanco2</div>
                            <div class="numCuenta">
                                <p>0123456789</p>
                                <p>$0,000.00</p>
                            </div>
                        </div>

                        <div class="contenedorCuentaTransferencia clickable-div">
                            <div style="font-size: 30px; padding-left: 20px;">Produbanco3</div>
                            <div class="numCuenta">
                                <p>0123456789</p>
                                <p>$0,000.00</p>
                            </div>
                        </div>
                    </div>
                    <a href="">
                        <button type="submit" value="HacerTransferencia" class="botonInicio fondoBotones" style="font-size: 30px;"> Regresar </button>
                    </a>
                </div>

                <!-- Escoger cuenta de destino -->
                <div class="centrarBotones">
                    <div class="titulo2 seccionMovimiento">2. Elija la cuenta de destino</div>
                    <div class="contenedorcuadrosCuentaTransferencia">
                        
                        <div class="contenedorCuentaTransferencia clickable-div1">
                            <div style="font-size: 30px; padding-left: 20px;">Produbanco1</div>
                            <div class="numCuenta">
                                <p>0123456789</p>
                                <p>$0,000.00</p>
                            </div>
                        </div>
                        
                        <div class="contenedorCuentaTransferencia clickable-div1">
                            <div style="font-size: 30px; padding-left: 20px;">Produbanco2</div>
                            <div class="numCuenta">
                                <p>0123456789</p>
                                <p>$0,000.00</p>
                            </div>
                        </div>

                        <div class="contenedorCuentaTransferencia clickable-div1">
                            <div style="font-size: 30px; padding-left: 20px;">Produbanco3</div>
                            <div class="numCuenta">
                                <p>0123456789</p>
                                <p>$0,000.00</p>
                            </div>
                        </div>
                    </div>
                    <a href="">
                        <button type="submit" value="HacerTransferencia" class="botonInicio fondoBotones" style="font-size: 30px;"> Regresar </button>
                    </a>
                </div>

                <!-- Info Transferencia -->
                <div class="centrarBotones">
                    <div class="titulo2 seccionMovimiento">3. Información de transferencia</div>
                    <div class="fondoCuadroInformacion contenedorFormTransferencia">
                        <form class="inputInicio-container">
                            <div>
                                <div class="titulo2">Monto</div>
                                <input type="text" name="monto" placeholder=" $00.00" required class="styled-inputTransferencia">
                            </div>
                            <div>
                                <div class="titulo2">Descripción</div>
                                <input type="text" name="descripción" required class="styled-inputTransferencia2">
                            </div>
                        </form>
                    </div>
                    <a href="">
                        <button type="submit" value="HacerTransferencia" class="botonInicio fondoBotones">Hacer Transferencia</button>
                    </a>
                    <a href="dashboard.html">
                        <button type="submit" value="Cancelar" class="botonInicio fondoBotones ">Cancelar</button>
                    </a>
                </div>

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
    
    <script src="${pageContext.request.contextPath}/js/app1.js">	</script>
    <script src="https://kit.fontawesome.com/85e6f64c7f.js" crossorigin="anonymous"></script>
</body>

</html>