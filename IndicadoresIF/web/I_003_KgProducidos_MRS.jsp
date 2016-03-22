<%-- 
    Document   : I_003_KgProducidos_MRS
    Created on : 17/03/2016, 03:29:46 PM
    Author     : rcruz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html">

    <!-----------------------------------------Archivos y Fuentes JavaScript-------------------------------> 
    <script type="text/javascript" src="Js/js/FuncionesGlobales.js"></script>
    <script type="text/javascript" src="Js/I_003_KgProducidos_MRS.js"></script>

    <!--JS GOOGLE CHARTS--> 
    <script type="text/javascript" src="Js/js/loader.js"></script>
    <script type="text/javascript" src="Js/js/jquery-1.12.1.min.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(DibujarChartPrincipal1);
    </script>    

    <!--JS MENU DESPLEGABLE--> 
    <script type="text/javascript" src="Js/js/modernizr.min.js"></script>
    <script src="Js/js/jquery.multilevelpushmenu.min.js"></script>
    <script type="text/javascript" src="Js/js/basicjs.js"></script>
    <script type="text/javascript">
    //mantener la posicion actual del menu
        //mantener la posicion actual del menu
        $(document).ready(function () {
            $('#menu').multilevelpushmenu('expand', 'Produccion Por Planta');
            $(window).resize(function () {
                DibujarChartPrincipal();
            });
        });
    </script> 

    <!--Refrescar pagina-->
    <script type="text/javascript" src="Js/js/UpdateBrowser.js"></script>

    <!-------------------------------------------------------------------------------------------------------> 


    <head>
        <!-------------------------------------------ARCHIVOS CSS ----------------------------------------------> 
        <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700&subset=latin,cyrillic-ext,latin-ext,cyrillic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/jquery.multilevelpushmenu_red.css">
        <link rel="stylesheet" href="css/basicjs.css">
        <link rel="stylesheet" href="css/EstiloJSP.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
        <!-------------------------------------------------------------------------------------------------------> 

        <title>Produccion Por Planta</title>
    </head>

    <body class="body">

        <div id="DivMenu" class="MenuDesplegable">
            <div id="menu"> </div>
        </div>

        <div id="DivPrincipal" class="divprincipal">

            <form method="get" action="I_001_Kilos_Producidos_Hora_Hombre_XLS" style="padding:5px">
                <input type="text" id="lblmes"  name="lblmes"  onkeypress="" value="Ingresar Mes" disabled="true" class="texto" />  
                  <input type="text" id="lbla"  name="lbla"  onkeypress="" value="Ingresar Año" disabled="true" class="texto" /> 
                <INPUT TYPE="SUBMIT" value="Exportar Datos" class="boton">
                <br>
                <input type="text" id="mes"  name="mes"  onkeypress="" value="<%=Utilidades.MetodosGlobales.month_actual%>" class="texto"/>  
                                   <input type="text" id="anio"  name="anio"  onkeypress="" value="2016" class="texto"/>  
                <input type="button" value="Visualizar" onclick="DibujarChartPrincipal1()" class="boton"/>
            </form> 

            <div id="GraficaPrincipal" class="divimagen"></div>

        </div> 
    </body>
</html>
