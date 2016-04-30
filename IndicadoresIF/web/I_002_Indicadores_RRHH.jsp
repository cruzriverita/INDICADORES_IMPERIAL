<%-- 
    Document   : I_002_Indicadores_RRHH
    Created on : 9/04/2016, 10:35:14 AM
    Author     : rcruz
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html">

    <%
        String indicador = request.getParameter("indicador");
        String vindicador = "INDICADOR7";

        if (indicador == null) {

        } else {
            vindicador = indicador;

        }
    %>

    <!------------------------------------------JS GOOGLE CHARTS-------------------------------------------> 
    <script type="text/javascript" src="Js/js/loader.js"></script>
    <script type="text/javascript" src="Js/js/jquery-1.12.1.min.js"></script>

    <!-----------------------------------------Archivos y Fuentes JavaScript-------------------------------> 
    <script type="text/javascript" src="Js/js/FuncionesGlobales.js"></script>
    <script type="text/javascript" src="Js/I_002_Indicadores_RRHH.js"></script>

    <script type="text/javascript">
        //API de Google Chart, Se llama en cada jsp  
        /* global google */
        google.charts.load('current', {packages: ['corechart', 'line']});
        google.charts.setOnLoadCallback(DibujarChartPrincipal);
    </script>   

    <script type="text/javascript">
        //mantener la posicion actual del menu
        $(document).ready(function () {

            $(window).resize(function () {
                DibujarChartPrincipal();
            });

            $('#indicador').val("<%=vindicador%>");
            GetTituloG2();
            GetSubTituloG2();

        });

    </script> 

    <head>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700&subset=latin,cyrillic-ext,latin-ext,cyrillic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/EstiloJSP.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <title>Produccion Por Planta</title>
    </head>

    <body class="body">

        <script type="text/javascript" src="Js/js/CuerpoMenuHorizontal.js"></script>
        <div id="DivPrincipal" class="divprincipal">
            <div class="DivWithScroll">

                <form method="get" action="I_002_Indicadores_RRHH_XLS" class="formulario">

                    <div style="float: left; width: 70%;">

                        <div>
                            <div class="divtexto">
                                <input type="text" id="lblmes"  name="lblmes"  onkeypress="" value="Tipo" disabled="true" class="texto" /> 
                            </div>
                            <div class="divtexto">
                                <input type="text" id="lbla"  name="lbla"  onkeypress="" value="Año" disabled="true" class="texto" />                  
                            </div>

                        </div>

                        <div style="clear:both;">  

                            <div class="divselect">
                                <select id="tipo" name="tipo" onchange="DibujarChartPrincipal();
            GetTituloG2();
            GetSubTituloG2();" class="select">
                                    <option value="1">Nomina</option>
                                    <option value="2">Planilla</option>
                                </select> 
                            </div>
                            <div class="divselect">
                                <select id="anio" name="anio" onchange="DibujarChartPrincipal();
                                        GetTituloG2();
                                        GetSubTituloG2();" class="select">
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual - 2%>"> <%=Utilidades.MetodosGlobales.year_actual - 2%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual - 1%>"> <%=Utilidades.MetodosGlobales.year_actual - 1%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual%>" selected> <%=Utilidades.MetodosGlobales.year_actual%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual + 1%>"> <%=Utilidades.MetodosGlobales.year_actual + 1%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual + 2%>"> <%=Utilidades.MetodosGlobales.year_actual + 2%> </option>
                                </select>
                            </div>

                            <input type="hidden" id="indicador"  name="indicador" value="INDICADOR7"/>  

                        </div>
                    </div>

                    <div class="divboton" id="divb">
                        <input   type="image" style="height:48px;width:48px;"  onmouseover="this.style.background = '#0fa1e0';
                                ShowDef();" onmouseout="this.style.background = 'white';
                                        HideDef();" src="Images/dd.svg">
                    </div>
                    <div class="DefStyle" id="EmaliographyDef">Descarga</div>

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
