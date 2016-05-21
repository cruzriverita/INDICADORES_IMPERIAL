<%-- 
    Document   : I_005_Indicadores_Calidad
    Created on : May 12, 2016, 8:47:59 AM
    Author     : rcruz
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html">

    <!---------------------------------------------CODIGO JSP---------------------------------------------->
    <%
        String planta = request.getParameter("planta");
        String mes = request.getParameter("mes");
        String an = request.getParameter("anio");
        String ind = request.getParameter("indicador");
        String amb=request.getParameter("ambito");
        String v = "ALL";
        String m = "3";
        String a = "2016";
        String vind = "1";
        String vamb="I";

        if (planta == null) {
        } else {
            v = planta;
            a = an;
            m = mes;
        }

        if (ind == null) {
        } else {
            vind = ind;
        }
        
         if (amb == null) {
        } else {
            vamb = amb;
        } 
    %>
    <!----------------------------------------------------------------------------------------------------->

    <!-----------------------------------------Archivos y Fuentes JavaScript-------------------------------> 
    <script type="text/javascript" src="Js/js/FuncionesGlobales.js"></script>
    <script type="text/javascript" src="Js/js/loader.js"></script>
    <script type="text/javascript" src="Js/js/jquery-1.12.1.min.js"></script>
    <script type="text/javascript" src="Js/js/CuerpoMenuHorizontal.js"></script>
    <!-----------------------------------------------------------------------------------------------------> 

    <!------------------------------------------JS GOOGLE CHARTS-------------------------------------------> 
    <script type="text/javascript" src="Js/I_005_Indicadores_Calidad.js"></script>
    <script type="text/javascript">
        //API de Google Chart, Se llama en cada jsp  
        /* global google */
        // google.charts.load('current', {'packages': ['corechart']});
        google.charts.load('current', {packages: ['corechart', 'line']});
        google.charts.setOnLoadCallback(DibujarChartPrincipal);
    </script>   
    <!-----------------------------------------------------------------------------------------------------> 

    <!---------------------------------Se ejecuta al cargar la pagina-------------------------------------->    
    <script type="text/javascript">

        $(document).ready(function () {
            $(window).resize(function () {
                DibujarChartPrincipal();
            });
            //Setear valores por defecto para los parametros.
            $('#opciones').val("<%=v%>");
            $('#mes').val("<%=m%>");
            $('#ambito').val("<%=vamb%>");
            hideMes();
            GetTituloG5();
            GetSubTituloG();
            hideInterno();
        });
    </script> 
    <!-----------------------------------------------------------------------------------------------------> 
    
    <head>
        <!---------------------------------------------ARCHIVOS CSS-------------------------------------------> 
        <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700&subset=latin,cyrillic-ext,latin-ext,cyrillic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/EstiloJSP.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <!-----------------------------------------------------------------------------------------------------> 

        <title>CALIDAD</title>

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
                                <input type="text" id="lblP"  name="lblP"  onkeypress="" value="Planta" disabled="true" class="texto" /> 
                            </div>
                            
                             <div class="divtexto" id = "divamb">
                                <input type="text" id="lblamb"  name="lblamb"  onkeypress="" value="Int/Ext" disabled="true" class="texto" /> 
                            </div>
                            
                        </div>

                        <div style="clear:both;">    
                            <div class="divselect" id="divselectmes">
                                <select id="mes" name="mes" onchange="DibujarChartPrincipal();
                                        GetTituloG5();
                                        GetSubTituloG(); hideInterno();" class="select">
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
                                        GetTituloG5();
                                        GetSubTituloG();hideInterno(); " class="select">

                                    <option value="<%=Utilidades.Metodos_Globales.year_actual - 2%>"> <%=Utilidades.Metodos_Globales.year_actual - 2%> </option>
                                    <option value="<%=Utilidades.Metodos_Globales.year_actual - 1%>"> <%=Utilidades.Metodos_Globales.year_actual - 1%> </option>
                                    <option value="<%=Utilidades.Metodos_Globales.year_actual%>" selected> <%=Utilidades.Metodos_Globales.year_actual%> </option>
                                    <option value="<%=Utilidades.Metodos_Globales.year_actual + 1%>"> <%=Utilidades.Metodos_Globales.year_actual + 1%> </option>
                                    <option value="<%=Utilidades.Metodos_Globales.year_actual + 2%>"> <%=Utilidades.Metodos_Globales.year_actual + 2%> </option>
                                </select>

                            </div>

                            <div class="divselect">
                                <select id="opciones" name="opciones" onchange="DibujarChartPrincipal();
                                        hideMes();
                                        GetTituloG5();
                                        GetSubTituloG();hideInterno();" class="select">

                                    <option value="ALL">Todas las plantas</option>
                                
                                    <option value="1">RST</option> 
                                    <option value="2">RSM</option>  
                                    <option value="3">RLRS</option>
                                    <option value="4">FPS</option>
                                    <option value="5">KNIT</option>
                                    <option value="6">DPF</option>
                                    
                                </select>
                            </div>

                            <div class="divselect" id ="divselectamb">
                                <select id="ambito" name="ambito" onchange="DibujarChartPrincipal();
                                        hideMes();
                                        GetTituloG5();
                                        GetSubTituloG();hideInterno();" class="select">

                                    <option value="I">Interno</option>
                                    <option value="E">Externo</option> 
                                </select>
                            </div>  
                        </div>
                    </div>
                    <input type="hidden" id="indicador"  name="indicador" value="<%=vind%>"/>  
                </form> 
                <br style="line-height: 10px">

                <center>
                    <div style="line-height: 5px">
                        <h3 id="titulo" class="titulos"></h3>
                        <h4 id="subtitulo" class="titulos"></h4>
                    </div>
                </center>

                <div id="GraficaPrincipal" class="divimagen"></div>
            </div> 
        </div>
    </body>
</html>
