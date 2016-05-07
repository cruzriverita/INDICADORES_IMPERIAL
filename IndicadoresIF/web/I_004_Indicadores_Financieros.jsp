<%-- 
    Document   : I_001_Kilos_Producidos_Hora_Hombre
    Created on : 9/03/2016, 09:07:09 AM
    Author     : rcruz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html">

    <%
        String planta = request.getParameter("planta");
        String mes = request.getParameter("mes");
        String an = request.getParameter("anio");
        String ind = request.getParameter("indicador");
        String v = "ALL";
        String m = "3";
        String a = "2016";
        String vind = "1";

        if (planta == null) {
        }  
        
        else {
            v = planta;
            a = an;
            m = mes;
        }

        if (ind == null) {
        } else {
            vind = ind;
        }
    %>
    <!------------------------------------------JS GOOGLE CHARTS-------------------------------------------> 
    <script type="text/javascript" src="Js/js/loader.js"></script>
    <script type="text/javascript" src="Js/js/jquery-1.12.1.min.js"></script>

    <!-----------------------------------------Archivos y Fuentes JavaScript-------------------------------> 
    <script type="text/javascript" src="Js/js/FuncionesGlobales.js"></script>
    <script type="text/javascript" src="Js/I_004_Indicadores_Financieros.js"></script>
    <script type="text/javascript" src="Js/js/CuerpoMenuHorizontal.js"></script>

    <script type="text/javascript">
        //API de Google Chart, Se llama en cada jsp  
        /* global google */
        // google.charts.load('current', {'packages': ['corechart']});
        google.charts.load('current', {packages: ['corechart', 'line']});
        google.charts.setOnLoadCallback(DibujarChartPrincipal);
    </script>   

    <script type="text/javascript">

        $(document).ready(function () {
            $(window).resize(function () {
                DibujarChartPrincipal();
            });
            //Setear valores por defecto para los parametros.
            $('#opciones').val("<%=v%>");
            $('#mes').val("<%=m%>");
            hideMes();
            GetTituloFinancieros();
            GetSubTituloG();
        });
    </script> 

    <head>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700&subset=latin,cyrillic-ext,latin-ext,cyrillic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/EstiloJSP.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <title>PRODUCCION</title>

    </head>

    <body class="body" >
        <div id="DivPrincipal" class="divprincipal">
            <div class="DivWithScroll">
                <form method="get" action="" class="formulario">

                    <div style="float: left; width: 70%;">

                        <div>
                            <div class="divtexto" id="divlblmes">
                                <input type="text" id="lblmes"  name="lblmes"  onkeypress="" value="Mes" disabled="true" class="texto" /> 
                            </div>
                            <div class="divtexto">
                                <input type="text" id="lbla"  name="lbla"  onkeypress="" value="Año" disabled="true" class="texto" /> 
                            </div> 
                            <div class="divtexto">
                                <input type="text" id="lblP"  name="lblP"  onkeypress="" value="Empresa" disabled="true" class="texto" /> 
                            </div>
                        </div>

                        <div style="clear:both;">    
                            <div class="divselect" id="divselectmes">
                                <select id="mes" name="mes" onchange="DibujarChartPrincipal();
                                        GetTituloFinancieros();
                                        GetSubTituloG();" class="select">
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
                                <select id="anio" name="anio" onchange="DibujarChartPrincipal();
                                        GetTituloFinancieros();
                                        GetSubTituloG();" class="select">
                                    
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual - 2%>"> <%=Utilidades.MetodosGlobales.year_actual - 2%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual - 1%>"> <%=Utilidades.MetodosGlobales.year_actual - 1%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual%>" selected> <%=Utilidades.MetodosGlobales.year_actual%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual + 1%>"> <%=Utilidades.MetodosGlobales.year_actual + 1%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual + 2%>"> <%=Utilidades.MetodosGlobales.year_actual + 2%> </option>
                                </select>
                                
                            </div>
                                
                            <div class="divselect">
                                <select id="opciones" name="opciones" onchange="DibujarChartPrincipal();
                                        hideMes();
                                        GetTituloFinancieros();
                                        GetSubTituloG();" class="select">
                                    
                                    <option value="ALL">Todas Las Empresas</option>

                                    <option value="1">Imperial Fashion</option> 
                                    <option value="2">MT TEXTIL</option> 
                                    <option value="3">BLAKE S.A.</option>  
                                    <option value="4">IMPERIALTEX</option>
                                </select>
                            </div>
                        </div>
                    </div>


                    <input type="hidden" id="indicador"  name="indicador" value="<%=vind%>"/>  
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
