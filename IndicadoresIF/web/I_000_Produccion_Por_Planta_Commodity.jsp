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
    <script type="text/javascript" src="Js/js/FuncionesGlobales.js"></script>
    <script type="text/javascript" src="Js/I_000_Produccion_Por_Planta.js"></script>

    <!JS GOOGLE CHARTS> 
    <script type="text/javascript" src="Js/js/loader.js"></script>

    <script type="text/javascript">
        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(DibujarChartDetalle);
    </script>    

    <script type="text/javascript" src="Js/js/jquery-1.12.1.min.js"></script>
    <!JS MENU DESPLEGABLE> 

    <script type="text/javascript" src="Js/js/modernizr.min.js"></script>
    <script src="Js/js/jquery.multilevelpushmenu.min.js"></script>
    <script type="text/javascript" src="Js/js/basicjs.js"></script>
            <script type="text/javascript">
    //mantener la posicion actual del menu
        //mantener la posicion actual del menu
        $(document).ready(function () {
                $('#menu').multilevelpushmenu('expand', 'Demo');
            $(window).resize(function () {
                DibujarChartDetalle();
            });
        });
    </script> 

    <!Refrescar pagina>
    <script type="text/javascript" src="Js/js/UpdateBrowser.js"></script>
    <!----------------------------------------------------------------------------------------------------------> 
    <head>
        <!Archivos CSS>
    <!--------------------------------------------------------------------------------------------------------------> 
    <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700&subset=latin,cyrillic-ext,latin-ext,cyrillic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
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
            <%--Campos escondidos, utilizados para enviar los valores al servlet que genera el excel.--%>              
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
