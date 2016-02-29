<%-- 
    Document   : I_000_Produccion_Por_Planta
    Created on : 19-feb-2016, 10:06:29
    Author     : rcruz
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

    <!------------------------ Archivos y Fuentes JavaScript-------------------------> 
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript" src="Js/FuncionesGlobales.js"></script>
    <script type="text/javascript" src="Js/I_000_Produccion_Por_Planta.js"></script>
   
    <script type="text/javascript">
        //API de Google Chart, Se llama en cada jsp 
        google.charts.load('current', {'packages': ['corechart']});
        /* Set a callback to run when the Google Visualization API is loaded
         Se especifica la funcion javascript que dibuja el chart 
         */
        google.charts.setOnLoadCallback(DibujarChartPrincipal);
        $(window).on("throttledresize", function (event) {
    initChart();
});
    </script>    


    <head>
        <title>Produccion Por Planta</title>
    </head>
    <body onload="DibujarChartPrincipal();">
        <form method="get" action="I_000_Produccion_Por_Planta_Mes_Servlet_XLS">
            <div id="encabezado">
                <input type="text" id="anio" name="anio" onkeypress="" value="2016" />
                <input type="text" id="mes"  name="mes"  onkeypress="" value="2"/>  
                 <input type="button" value="Visualizar" onclick="DibujarChartPrincipal()"/>
                  <div id="GraficaPrincipal" style="width: 100%; height: 100%;"></div>
                <INPUT TYPE="SUBMIT" value="Descargar">
            </div>          
        </form>
    </body>
</html>