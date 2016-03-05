<%-- 
    Document   : I_000_Produccion_Por_Planta_Mes
    Created on : 19-feb-2016, 10:06:29
    Author     : rcruz
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html">
    
    <!-----------------------------JAVA CODE----------------------------------->
    <%@page import="java.util.Calendar"%>
    <%Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);%>
    <!-----------------------------JAVA CODE----------------------------------->
        
    <!-----------------------------------------Archivos y Fuentes JavaScript-------------------------------> 
    <script type="text/javascript" src="Js/FuncionesGlobales.js"></script>
    <script type="text/javascript" src="Js/I_000_Produccion_Por_Planta.js"></script>

    <!------------------------------------------JS GOOGLE CHARTS-------------------------------------------> 
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

    <!------------------------------------------JS MENU DESPLEGABLE-------------------------------------------> 
    <script type="text/javascript" src="http://oss.maxcdn.com/libs/modernizr/2.6.2/modernizr.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="Js/jquery.multilevelpushmenu.min.js"></script>
    <script type="text/javascript" src="Js/basicjs.js"></script>

      <!-----------------------------------------Refrescar pagina---------------------------------------------->
    <script type="text/javascript" src="Js/UpdateBrowser.js"></script>

    <head>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700&subset=latin,cyrillic-ext,latin-ext,cyrillic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.1/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/jquery.multilevelpushmenu_red.css">
        <link rel="stylesheet" href="css/basicjs.css">
        <link rel="stylesheet" href="css/EstiloJSP.css">
        <title>Produccion Por Planta</title>
    </head>
    
    <body class="body">

        <div id="DivMenu" class="MenuDesplegable">
            <div id="menu"> </div>
        </div>
        
        <div id="DivPrincipal" class="divprincipal">
            <form method="get" action="I_000_Produccion_Por_Planta_Mes_Servlet_XLS" style="padding:5px">
                <input type="text" id="anio" name="anio" onkeypress="" value="<%=year%>" />
                <input type="text" id="mes"  name="mes"  onkeypress="" value="<%=month+1%>"/>  
                <input type="button" value="Visualizar" onclick="DibujarChartPrincipal()"/>
                <INPUT TYPE="SUBMIT" value="Descargar">
            </form> 
            
            <div id="GraficaPrincipal" class="divimagen"></div>
        </div> 




    </body>
</html>