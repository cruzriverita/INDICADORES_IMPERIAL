<%-- 
    Document   : I_003_Indicadores_Inventarios
    Created on : Apr 20, 2016, 2:48:12 PM
    Author     : rcruz
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html">

    <%
        String indicador = request.getParameter("indicador");
        String tipo = request.getParameter("tipo2");
        String tipoinv = request.getParameter("tipo");
        String anio = request.getParameter("anio");

        String vindicador = "INDICADOR9";
        String vtipo = "1";
        String vtipoinv = "1";
        String vanio = "2015";

        if (indicador == null) {

        } else {
            vindicador = indicador;

        }

        if (tipo == null) {

        } else {
            vtipo = tipo;
        }

        if (tipoinv == null) {

        } else {
            vtipoinv = tipoinv;
        }
        
        if (anio == null) {

        } else {
            vanio = anio;
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
            $('#tipo').val("<%=vtipoinv%>");
            $('#anio').val("<%=vanio%>");
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
                                <input type="text" id="lblmes"  name="lblmes"  onkeypress="" value="Vista Inventario" disabled="true" class="texto" /> 
                            </div>

                            <div class="divtexto">
                                <input type="text" id="lblart"  name="lblart"  onkeypress="" value="Tipo Inventario" disabled="true" class="texto" /> 
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
                                <select id="tipo" name="tipo" onchange="DibujarChartPrincipal();
                                        GetTituloG3();
                                        GetSubTituloG3();" class="select">
                                    <option value="1">Rotacion Inventarios</option>
                                    <option value="2">Dias Inventario</option>
                                </select> 
                            </div>

                            <div class="divselect">
                                <select id="tipo2" name="tipo2" onchange="DibujarChartPrincipal();
                                        GetTituloG3();
                                        GetSubTituloG3();" class="select">
                                    <option value="1">Algodon</option>
                                    <option value="2">Poliester Fibra</option>
                                    <option value="3">Hilo Producido</option>
                                    <option value="4">Hilo Comprado</option>
                                    <option value="5">Tela Cruda</option>
                                    <option value="6">Tela Terminada</option>
                                </select> 
                            </div>

                            <input type="hidden" id="indicador"  name="indicador" value="INDICADOR9"/>  

                        </div>
                    </div>
                    
                    <!--
                    <div class="divboton" id="divb">
                        <input   type="image" style="height:48px;width:48px;"  onmouseover="this.style.background = '#0fa1e0';
                                ShowDef();" onmouseout="this.style.background = 'white';
                                        HideDef();" src="Images/dd.svg">
                    </div>
                    <div class="DefStyle" id="EmaliographyDef">Descarga</div>
                    -->

                </form> 

                <br style="line-height: 10px">

                <center>
                    <div style="line-height: 5px">
                        <h3 id="titulo"></h3>
                        <h4 id="subtitulo"></h4>
                    </div>
                </center>

                <div id="hidden_div" style="display:none; width: 400px" ></div>
                <div id="GraficaPrincipal" class="divimagen">

                </div>
            </div>
        </div>
    </div>
</body>
</html>
