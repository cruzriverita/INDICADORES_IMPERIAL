<%-- 
    Document   : I_000_Produccion_Por_Planta_Commodity
    Created on : 27/02/2016, 08:11:45 AM
    Author     : rcruz
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html">


    <%
        //Se recogen los parametros que envia el jsp.
        String anio = request.getParameter("anio");
        String mes = request.getParameter("mes");
        String planta = request.getParameter("planta");
    %>

    <!-----------------------------------------Archivos y Fuentes JavaScript--------------------------------------> 
    <script type="text/javascript" src="Js/FuncionesGlobales.js"></script>
    <script type="text/javascript" src="Js/I_000_Produccion_Por_Planta.js"></script>

    <!JS GOOGLE CHARTS> 
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">
        //API de Google Chart, Se llama en cada jsp 
        google.charts.load('current', {'packages': ['corechart']});
        /* Set a callback to run when the Google Visualization API is loaded
         Se especifica la funcion javascript que dibuja el chart 
         */
        google.charts.setOnLoadCallback(DibujarChartPrincipal);
    </script>    

    <!JS MENU DESPLEGABLE> 
    <script type="text/javascript" src="http://oss.maxcdn.com/libs/modernizr/2.6.2/modernizr.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="Js/jquery.multilevelpushmenu.min.js"></script>
    <script type="text/javascript" src="Js/basicjs.js"></script>
     <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

    <!Refrescar pagina>
    <script type="text/javascript" src="Js/UpdateBrowser.js"></script>

    <!--------------------------------------------------------------------------------------------------------------> 


    <head>
        <!Archivos CSS>
    <!--------------------------------------------------------------------------------------------------------------> 
    <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700&subset=latin,cyrillic-ext,latin-ext,cyrillic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.1/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/jquery.multilevelpushmenu_red.css">
    <link rel="stylesheet" href="css/basicjs.css">
    <link rel="stylesheet" href="css/EstiloJSP.css">
    <!--------------------------------------------------------------------------------------------------------------> 
    <title>Produccion Planta <%=planta%></title>

</head>

<body onload="DibujarChartDetalle();" class="body">

    <div id="DivMenu" class="MenuDesplegable">
        <div id="menu"> </div>
    </div>

    <div id="DivPrincipal" class="divprincipal">
        <form method="get" action="I_000_Produccion_Por_Planta_Commodity_XLS" style="padding:5px">
            <%--Campos escondidos, utilizados para enviar los valores al servlet que 
                genera el excel.--%>              
            <input type="text" id="anio" name="anio" onkeypress="" value="<%=anio%>" hidden="true" />
            <input type="text" id="mes" name="mes" onkeypress="" value="<%=mes%>" hidden="true" />
            <input type="text" id="anio" name="planta" onkeypress="" value="<%=planta%>" hidden="true" />             
            <input id ="atras" type="button" value="Regresar" onclick="window.history.back()"/>
            <INPUT TYPE="SUBMIT" value="Exportar Datos">        
        </form>

        <div id="piechart_detalle" class="divimagen"></div>

    </div>

</body>
</html>
