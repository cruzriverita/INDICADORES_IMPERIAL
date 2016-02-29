<%-- 
    Document   : I_000_Produccion_Por_Planta_Commodity
    Created on : 27/02/2016, 08:11:45 AM
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
        google.charts.setOnLoadCallback(DibujarChartDetalle);
    </script>    

    <head>
        <title>Produccion Por Planta</title>
    </head>

    <body onload="DibujarChartDetalle();">
        <%
            String anio = request.getParameter("anio");
            String mes = request.getParameter("mes");
            String planta = request.getParameter("planta");
        %>
        <form method="get" action="I_000_Produccion_Por_Planta_Commodity_XLS">
            <div id="encabezado">

                <%--Campos escondidos, utilizados para enviar los valores al servlet que 
                    genera el excel.--%>              
                <input type="text" id="anio" name="anio" onkeypress="" value="<%=anio%>" hidden="true" />
                <input type="text" id="mes" name="mes" onkeypress="" value="<%=mes%>" hidden="true" />
                <input type="text" id="anio" name="planta" onkeypress="" value="<%=planta%>" hidden="true" />             
                <INPUT TYPE="SUBMIT" value="Descargar">        
            </div>          
            <div id="piechart_detalle" style="width: 900px; height: 500px;"></div>
        </form>
    </body>
</html>
