<%-- 
    Document   : C_001_Produccion_Por_Planta
    Created on : 21/03/2016, 11:01:02 AM
    Author     : rcruz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html">
      
     <script type="text/javascript" src="Js/js/loader.js"></script>
    <script type="text/javascript" src="Js/js/jquery-1.12.1.min.js"></script>
    
    <!-----------------------------------------Archivos y Fuentes JavaScript-------------------------------> 


                      <!---------------------------JS GOOGLE CHARTS----------------------------> 
   
    <script type="text/javascript">    
        /* global google */
        google.charts.load('current', {'packages':['table']});   

    </script>    

            <script type="text/javascript" src="Js/js/FuncionesGlobales.js"></script>
    <script type="text/javascript" src="Js/C_001_Produccion_Por_Planta.js"></script>
    
    
        <script type="text/javascript">    
    google.charts.setOnLoadCallback(DibujarTabla);
    </script>    
    
    <!------------------------------------------JS MENU DESPLEGABLE-------------------------------------------> 
    <script type="text/javascript" src="Js/js/modernizr.min.js"></script>
    <script src="Js/js/jquery.multilevelpushmenu.min.js"></script>
    <script type="text/javascript" src="Js/js/basicjs.js"></script>

      <script type="text/javascript">
        //mantener la posicion actual del menu
        $(document).ready(function () {
            
            $('#menu').multilevelpushmenu('expand', 'Produccion Por Planta');
            $(window).resize(function () {
                DibujarTabla();
            });
           // $('#table').style.backgroundColor = "lightblue";
        });
    </script> 
    
      <!-----------------------------------------Refrescar pagina---------------------------------------------->
    <script type="text/javascript" src="Js/js/UpdateBrowser.js"></script>

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
            <form method="get" action="I_000_Produccion_Por_Planta_Mes_Servlet_XLS" class="formulario">
                <input type="text" id="anio" name="anio" onkeypress="" value="<%=Utilidades.MetodosGlobales.year_actual%>" />
                <input type="text" id="mes"  name="mes"  onkeypress="" value="<%=Utilidades.MetodosGlobales.month_actual%>"/>  
                <input type="button" value="Visualizar" onclick="DibujarTabla()"/>
                <INPUT TYPE="SUBMIT" value="Exportar Datos">
            </form> 
            
           <div id="table" class="divimagenP"></div>
           <div id="output"></div>
           <!-- <div id="GraficaPrincipal1" class="divimagenP"></div>-->
             <!--<div id="GraficaPrincipal2" class="divimagenP"></div>-->
        </div> 
    </body>
</html>