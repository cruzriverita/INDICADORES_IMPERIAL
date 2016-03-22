<%-- 
    Document   : I_000_Costo_Energia_Q
    Created on : 4/03/2016, 10:19:57 AM
    Author     : rcruz
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html">

    <!-----------------------------------------Archivos y Fuentes JavaScript-------------------------------> 
    <script type="text/javascript" src="Js/js/FuncionesGlobales.js"></script>
    <script type="text/javascript" src="Js/I_003_Costo_Energia.js"></script>
    <!---------------------------------------------------------------------------------------------------------> 

    <!------------------------------------------JS GOOGLE CHARTS-------------------------------------------> 
    <script type="text/javascript" src="Js/js/loader.js"></script>
    <script type="text/javascript" src="Js/js/jquery-1.12.1.min.js"></script>
    <script type="text/javascript">
        //API de Google Chart, Se llama en cada jsp 
        google.charts.load('current', {'packages': ['bar']});
        google.charts.setOnLoadCallback(ChartCostoEnergiaQ);
    </script>    
    <!---------------------------------------------------------------------------------------------------------> 

    <!------------------------------------------JS MENU DESPLEGABLE-------------------------------------------> 
    <script type="text/javascript" src="Js/js/modernizr.min.js"></script>
     <script type="text/javascript" src="Js/js/jquery-1.12.1.min.js"></script>
    <script src="Js/js/jquery.multilevelpushmenu.min.js"></script>
    <script type="text/javascript" src="Js/js/basicjs.js"></script>
    <!---------------------------------------------------------------------------------------------------------> 

    <!-----------------------------------------Refrescar pagina---------------------------------------------->
    <script type="text/javascript" src="Js/js/UpdateBrowser.js"></script>
    <!---------------------------------------------------------------------------------------------------------> 
    <head>
        <!---------------------------------ARCHIVOS DE ESTILO-------------------------------------------------->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700&subset=latin,cyrillic-ext,latin-ext,cyrillic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/jquery.multilevelpushmenu_red.css">
    <link rel="stylesheet" href="css/basicjs.css">
    <link rel="stylesheet" href="css/EstiloJSP.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
    <title>Consumo De Energia </title>
</head> 

<body class="body">

    <div id="DivMenu" class="MenuDesplegable">
        <div id="menu"> </div>
    </div>

    <div id="DivPrincipal" class="divprincipal">
        <form method="get" action="I_003_Costo_Energia_XLS" style="padding:5px">
            <input type="text" id="anio" name="anio" onkeypress="" value="<%=Utilidades.MetodosGlobales.year_actual%>" />

            <select id="opciones" name="opciones">
                <option value="money">Quetzales</option>
                <option value="KWH">KWH</option>              
            </select>

            <input type="button" value="Visualizar" onclick="ChartCostoEnergiaQ()"/>
            <INPUT TYPE="SUBMIT" value="Exportar Datos">
        </form> 
        <div class="DivWithScroll">
            <div id="GraficaPrincipal" class="divimagenscroll"></div>
        </div>
    </div> 

</body>
</html>