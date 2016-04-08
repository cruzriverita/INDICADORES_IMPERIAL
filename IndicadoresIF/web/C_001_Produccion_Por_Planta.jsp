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
    <script type="text/javascript" src="Js/js/FuncionesGlobales.js"></script>
    <script type="text/javascript" src="Js/C_001_Produccion_Por_Planta.js"></script>
    <script type="text/javascript">
        /* global google */
        google.charts.load('current', {'packages': ['table']});
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
             $('#opciones').val("ALL");
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
            
          

          
                <div class="DivWithScroll">
                    
                                <form method="get" action="C_001_Produccion_Por_Planta_XLS" class="formulario">

                <input type="text" id="lblmes"  name="lblmes"  onkeypress="" value="Ingresar Mes" disabled="true" class="texto" /> 
                <input type="text" id="lbla"  name="lbla"  onkeypress="" value="Ingresar Año" disabled="true" class="texto" /> 
                 <input type="text" id="lblP"  name="lblP"  onkeypress="" value="Planta" disabled="true" class="texto" /> 
                <INPUT TYPE="SUBMIT" value="Exportar Datos" class="boton">
                <br>
                <input type="text" id="mes"  name="mes"  onkeypress="" value="<%=Utilidades.MetodosGlobales.month_actual-1%>" class="texto"/>  
                <input type="text" id="anio" name="anio" onkeypress="" value="<%=Utilidades.MetodosGlobales.year_actual%>" class="texto" />
               
                <select id="opciones" name="opciones" onchange="DibujarTabla();" class="select">
                    <option value="ALL">Todas</option>                
                    <option value="PLANTA FPS">FPS</option>                    
                </select>
                <input type="button" value="Visualizar" onclick="DibujarTabla()" class="boton"/>

            </form> 
                    <CENTER>
                    <h1> INDICADORES PRODUCCION</h1>
                    <h3> Produccion Kilogramos</h3>
                    <div id="table" class="divtablascroll">
                        
                    </div>
                    <h3> Produccion Costos</h3>
                      <div id="table2" class="divtablascroll">
                        
                    </div>
                    </CENTER>
                    <div> 
                        <p>*En color verde se muestran los valores mayores al promedio</p>
                        <p>*En color rojo se muestran los valores menores al promedio</p>
                    </div>
                </div>
        </div>
    </body>
</html>