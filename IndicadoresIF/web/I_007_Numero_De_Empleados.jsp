<%-- 
    Document   : I_007_Numero_De_Empleados
    Created on : 9/04/2016, 10:35:14 AM
    Author     : rcruz
--%>

<%@page import="java.util.Calendar"%>
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
    <!--<script type="text/javascript" src="Js/I_001_Kilos_Producidos_Hora_Hombre.js"></script>-->
  <script type="text/javascript" src="Js/IndicadoresRRHH.js"></script>
     <script type="text/javascript">
        //API de Google Chart, Se llama en cada jsp  
        /* global google */
       // google.charts.load('current', {'packages': ['corechart']});
    google.charts.load('current', {packages: ['corechart', 'line']});    
    google.charts.setOnLoadCallback(DibujarChartPrincipal);
    </script>   

    <!------------------------------------------JS MENU DESPLEGABLE-------------------------------------------> 
    <script type="text/javascript" src="Js/js/modernizr/2.6.2/modernizr.min.js"></script>
    <script src="Js/js/jquery.multilevelpushmenu.min.js"></script>
    <script type="text/javascript" src="Js/js/basicjs.js"></script>
    <script type="text/javascript">
        //mantener la posicion actual del menu
        $(document).ready(function () {
            
            $('#menu').multilevelpushmenu('expand', 'RRHH');
            $(window).resize(function () {
                DibujarChartPrincipal();
            });
            
            $('#indicador').val("<%=vindicador%>");

        });
    </script> 

    <head>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700&subset=latin,cyrillic-ext,latin-ext,cyrillic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/jquery.multilevelpushmenu_red.css">
        <link rel="stylesheet" href="css/basicjs.css">
        <link rel="stylesheet" href="css/EstiloJSP.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
        <title>Produccion Por Planta</title>
    </head>

    <body class="body">

        <div id="DivMenu" class="MenuDesplegable">
            <div id="menu"> </div>
        </div>

        <div id="DivPrincipal" class="divprincipal">
            <form method="get" action="I_001_Kilos_Producidos_Hora_Hombre_XLS" class="formulario">
                
                <input type="text" id="lblmes"  name="lblmes"  onkeypress="" value="Tipo" disabled="true" class="texto" /> 
                <input type="text" id="lbla"  name="lbla"  onkeypress="" value="Ingresar Año" disabled="true" class="texto" />                  
                <input type="text" id="lblindicador"  name="lblindicador"  onkeypress="" value="Indicador" disabled="true" class="texto" />  
                <INPUT TYPE="SUBMIT" value="Exportar Datos" class="botongrande">

                <br>
                
                <select id="tipo" name="tipo" onchange="DibujarChartPrincipal()" class="select">
                    <option value="1">Nomina</option>
                    <option value="2">Planilla</option>
                </select> 
                
                
                <select id="anio" name="anio" onchange="DibujarChartPrincipal()" class="select">
                    <option value="<%=Utilidades.MetodosGlobales.year_actual-2%>"> <%=Utilidades.MetodosGlobales.year_actual-2%> </option>
                    <option value="<%=Utilidades.MetodosGlobales.year_actual-1%>"> <%=Utilidades.MetodosGlobales.year_actual-1%> </option>
                    <option value="<%=Utilidades.MetodosGlobales.year_actual%>" selected> <%=Utilidades.MetodosGlobales.year_actual%> </option>
                    <option value="<%=Utilidades.MetodosGlobales.year_actual+1%>"> <%=Utilidades.MetodosGlobales.year_actual+1%> </option>
                    <option value="<%=Utilidades.MetodosGlobales.year_actual+2%>"> <%=Utilidades.MetodosGlobales.year_actual+2%> </option>
                </select>
                
                <select id="indicador" name="indicador" onchange="DibujarChartPrincipal()" class="select">
                    <option value="INDICADOR7">No. Empleados</option>
                    <option value="INDICADOR8">Devengado/Empleado</option>
                </select>
 
            </form> 

            <div id="GraficaPrincipal" class="divimagen"></div>

        </div> 

    </body>
</html>
