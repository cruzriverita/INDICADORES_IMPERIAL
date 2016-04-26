<%-- 
    Document   : I_003_Indicadores_Inventarios_Planta
    Created on : Apr 23, 2016, 8:25:57 AM
    Author     : rcruz
--%>


<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html">

    <%
        String indicador = request.getParameter("indicador");
        String tipo = request.getParameter("tipoinv");

        String vindicador = "INDICADOR9";
        String vtipo = "1";

        if (indicador == null) {

        } else {
            vindicador = indicador;

        }

        if (tipo == null) {

        } else {
            vtipo = tipo;
        }
    %>

    <!------------------------------------------JS GOOGLE CHARTS-------------------------------------------> 
    <script type="text/javascript" src="Js/js/loader.js"></script>
    <script type="text/javascript" src="Js/js/jquery-1.12.1.min.js"></script>

    <!-----------------------------------------Archivos y Fuentes JavaScript-------------------------------> 
    <script type="text/javascript" src="Js/js/FuncionesGlobales.js"></script>
    <script type="text/javascript" src="Js/I_003_Indicadores_Inventarios.js"></script>
    <script type="text/javascript">
        //API de Google Chart, Se llama en cada jsp  
        /* global google */
        google.charts.load('current', {packages: ['corechart', 'line']});
        google.charts.setOnLoadCallback(DibujarChartPrincipal);
    </script>   

    <script type="text/javascript">

        $(document).ready(function () {

            $(window).resize(function () {
                DibujarChartPrincipal();
            });

            $('#indicador').val("<%=vindicador%>");
            $('#tipo2').val("<%=vtipo%>");
            GetTituloG3();
            GetSubTituloG3();

        });
    </script> 

    <head>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700&subset=latin,cyrillic-ext,latin-ext,cyrillic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/EstiloJSP.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <title>Inventarios</title>
    </head>

    <body class="body">

        <script type="text/javascript" src="Js/js/CuerpoMenuHorizontal.js"></script>
        <div id="DivPrincipal" class="divprincipal">
            <div class="DivWithScroll">
                <form method="get" action="I_002_Indicadores_RRHH_XLS" class="formulario">

                    <div style="float: left; width: 70%;">
                        <div>

                            <div class="divtexto">
                                <input type="text" id="lbla"  name="lbla"  onkeypress="" value="Año" disabled="true" class="texto" />                  
                            </div>

                            <div class="divtexto">
                                <input type="text" id="lblmes"  name="lblmes"  onkeypress="" value="Mes" disabled="true" class="texto" /> 
                            </div>

                            <div class="divtexto">
                                <input type="text" id="lblart"  name="lblart"  onkeypress="" value="Planta" disabled="true" class="texto" /> 
                            </div>

                            <div class="divboton">
                                <input value="Exportar Datos" class="boton" type="SUBMIT">
                            </div>
                        </div>

                        <div style="clear:both;">  

                            <div class="divselect">
                                <select id="anio" name="anio" onchange="DibujarChartPrincipal();
                            GetTituloG3();
                            GetSubTituloG3();" class="select">

                                    <option value="<%=Utilidades.MetodosGlobales.year_actual - 2%>"> <%=Utilidades.MetodosGlobales.year_actual - 2%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual - 1%>"> <%=Utilidades.MetodosGlobales.year_actual - 1%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual%>" selected> <%=Utilidades.MetodosGlobales.year_actual%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual + 1%>"> <%=Utilidades.MetodosGlobales.year_actual + 1%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual + 2%>"> <%=Utilidades.MetodosGlobales.year_actual + 2%> </option>

                                </select>
                            </div>
                                    
                                  <div class="divselect">  
                                     <select id="mes" name="mes" onchange="DibujarChartPrincipal();" class="select">
                                    <option value="1">Enero</option>
                                    <option value="2">Febrero</option>
                                    <option value="3">Marzo</option>
                                    <option value="4">Abril</option>      
                                    <option value="5">Mayo</option>
                                    <option value="6">Junio</option>   
                                    <option value="7">Julio</option>
                                    <option value="8">Agosto</option> 
                                    <option value="9">Septiembre</option>  
                                    <option value="10">Octubre</option>  
                                    <option value="11">Noviembre</option>  
                                    <option value="12">Diciembre</option>  
                                </select> 
     </div>
                                    
                            <div class="divselect">
                                <select id="planta" name="planta" onchange="DibujarChartPrincipal();
                                    <option value="ALL">Todas</option>
                                    <option value="2">FPS</option>
                                    <option value="3">KNIT</option>
                                </select> 
                            </div>



                            <input type="hidden" id="indicador"  name="indicador" value="INDICADOR9"/>  

                        </div>
                    </div>


                </form> 

                <br style="line-height: 10px">

                <center>
                    <div style="line-height: 5px">
                        <h3 id="titulo"></h3>
                        <h4 id="subtitulo"></h4>
                    </div>
                </center>

                <div id="GraficaPrincipal" class="divimagen"></div>
            </div>
        </div>
    </body>
</html>
