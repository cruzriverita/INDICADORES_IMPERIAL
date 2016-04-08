<%-- 
    Document   : I_001_Kilos_Producidos_Hora_Hombre
    Created on : 9/03/2016, 09:07:09 AM
    Author     : rcruz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html">

    <%
        String planta = request.getParameter("planta");
        String mes = request.getParameter("mes");
        String an = request.getParameter("anio");
        String v = "ALL";
        String m = "2";
        String a = "2016";
        /*Se utiliza la decision para convertir el parmetro enviado como RSM ya que no se puede enviar
         por url "RSM 0&M"*/
        if (planta == null) {
        } else if (planta.equals("PLANTA RSM")) {
            v = "PLANTA RSM O&M";
        } else {
            v = planta;
            a = an;
            m = mes;
        }
    %>
        <!------------------------------------------JS GOOGLE CHARTS-------------------------------------------> 
    <script type="text/javascript" src="Js/js/loader.js"></script>
    <script type="text/javascript" src="Js/js/jquery-1.12.1.min.js"></script>
 
    <!-----------------------------------------Archivos y Fuentes JavaScript-------------------------------> 
    <script type="text/javascript" src="Js/js/FuncionesGlobales.js"></script>
    <!--<script type="text/javascript" src="Js/I_001_Kilos_Producidos_Hora_Hombre.js"></script>-->
  <script type="text/javascript" src="Js/JsGeneral.js"></script>
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
            
            $('#menu').multilevelpushmenu('expand', 'Produccion Por Planta');
            $(window).resize(function () {
                DibujarChartPrincipal();
            });
            
            $('#opciones').val("<%=v%>");
            
            hideMes();


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
            <form method="get" action="I_001_Kilos_Producidos_Hora_Hombre_XLS" class="formulario">
                <input type="text" id="lblmes"  name="lblmes"  onkeypress="" value="Ingresar Mes" disabled="true" class="texto" /> 
                <input type="text" id="lbla"  name="lbla"  onkeypress="" value="Ingresar Año" disabled="true" class="texto" /> 
                <input type="text" id="lblP"  name="lblP"  onkeypress="" value="Planta" disabled="true" class="texto" /> 
                <INPUT TYPE="SUBMIT" value="Exportar Datos" class="boton">
                <br>
                <input type="text" id="mes"  name="mes"  onkeypress="" value="<%=m%>" class="texto"/>  
                <input type="text" id="anio"  name="anio"  onkeypress="" value="<%=a%>" class="texto"/>  
   
                <select id="opciones" name="opciones" onchange="DibujarChartPrincipal(); hideMes();" class="select">
                    <option value="ALL">Todas</option>
                    <option value="FPS MES">FPS Mensual</option>
                    <option value="PLANTA RLRS">RLRS</option>
                    <option value="PLANTA RST">RST</option>      
                    <option value="PLANTA KNIT">KNIT</option>
                    <option value="PLANTA DPF">DPF</option>   
                    <option value="PLANTA FPS">FPS</option>
                    <option value="PLANTA RSM O&M">RSM O&M</option>   
                </select>
                

                <input type="button" value="Visualizar" onclick="DibujarChartPrincipal()" class="boton"/>
                <input type="hidden" id="indicador"  name="indicador" value="INDICADOR1"/>  
            </form> 

            <div id="GraficaPrincipal" class="divimagen"></div>

        </div> 

    </body>
</html>
