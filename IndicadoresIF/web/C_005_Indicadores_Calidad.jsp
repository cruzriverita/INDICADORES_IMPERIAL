<%-- 
    Document   : C_005_Indicadores_Calidad
    Created on : May 12, 2016, 10:22:30 AM
    Author     : rcruz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html">

    <!-----------------------------------------Archivos y Fuentes JavaScript-------------------------------> 
    <script type="text/javascript" src="Js/js/loader.js"></script>
    <script type="text/javascript" src="Js/js/jquery-1.12.1.min.js"></script>
    <script type="text/javascript" src="Js/js/FuncionesGlobales.js"></script>
    <!-----------------------------------------------------------------------------------------------------> 

    <!------------------------------------------JS GOOGLE CHARTS-------------------------------------------> 
    <script type="text/javascript" src="Js/C_005_Indicadores_Calidad.js"></script>
    <script type="text/javascript">
        /* global google */
        google.charts.load('current', {'packages': ['table']});
        google.charts.setOnLoadCallback(DibujarTabla);
    </script>   
    <!-----------------------------------------------------------------------------------------------------> 

    <!---------------------------------Se ejecuta al cargar la pagina--------------------------------------> 
    <script type="text/javascript">
        $(document).ready(function () {
            //Valores por defecto al cargar la pagina, seteados con javascript
            $('#anio').val("2015");

        });
    </script> 
    <!-----------------------------------------------------------------------------------------------------> 

    <head>
        <!---------------------------------------------ARCHIVOS CSS-------------------------------------------> 
        <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700&subset=latin,cyrillic-ext,latin-ext,cyrillic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/EstiloJSP.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <!-----------------------------------------------------------------------------------------------------> 

        <title>Calidad</title>
    </head>

    <body class="body">

        <script type="text/javascript" src="Js/js/CuerpoMenuHorizontal.js"></script>

        <div id="DivPrincipal" class="divprincipal">

            <div class="DivWithScroll">

                <form method="get" action="C_005_Indicadores_Calidad_XLS" class="formulario">

                    <div style="float: left; width: 70%;">

                        <div>

                            <div class="divtexto" id="divlblmes">
                                <input id="lblmes" name="lblmes" onkeypress="" value="Mes" disabled="true" class="texto" type="text"> 
                            </div>

                            <div class="divtexto">
                                <input id="lbla" name="lbla" onkeypress="" value="Año" disabled="true" class="texto" type="text"> 
                            </div>

                        </div>

                        <div style="clear:both;">                


                            <div class="divselect">

                                <select id="anio" name="anio" onchange="DibujarTabla()" class="select">
                                    <option value="<%=Utilidades.Metodos_Globales.year_actual - 2%>"> <%=Utilidades.Metodos_Globales.year_actual - 2%> </option>
                                    <option value="<%=Utilidades.Metodos_Globales.year_actual - 1%>"> <%=Utilidades.Metodos_Globales.year_actual - 1%> </option>
                                    <option value="<%=Utilidades.Metodos_Globales.year_actual%>" selected> <%=Utilidades.Metodos_Globales.year_actual%> </option>
                                    <option value="<%=Utilidades.Metodos_Globales.year_actual + 1%>"> <%=Utilidades.Metodos_Globales.year_actual + 1%> </option>
                                    <option value="<%=Utilidades.Metodos_Globales.year_actual + 2%>"> <%=Utilidades.Metodos_Globales.year_actual + 2%> </option>
                                </select>

                            </div>

                            <div class="divselect" id="divselectmes">

                                <select id="mes" name="mes" onchange="DibujarTabla()" class="select">
                                    <option value="1">Enero</option>
                                    <option value="2">Febrero</option>
                                    <option value="3">Marzo</option>
                                    <option value="4">Abril</option>      
                                    <option value="5">Mayo</option>
                                    <option value="6">Junio</option>   
                                    <option value="7">Julio</option>
                                    <option value="8">Agosto</option> 
                                    <option value="9">Septiembre</option>  
                                    <option value="10">Octubre</option>  
                                    <option value="11">Noviembre</option>  
                                    <option value="12">Diciembre</option>  
                                </select>

                            </div>

                        </div>
                    </div>

                    <div class="divboton" id="divb">
                        <input   type="image" style="height:100%;width:100%;"  
                                 src="Images/ddw.png" title="Exportar a Excel">
                                
                    </div>

                </form> 

                <br>
                <center>
                    <h2 class="titulos">Indicadores De Calidad</h2>
                </center>
                <br>
                <center>
                    <div id="table"  class="divtabla">
                    </div>
                    <div > 

                        <p class="parrafo_tabla">
                            *En color verde se muestran los valores mayores al promedio del año anterior.<br/>
                            *En color rojo se muestran los valores menores al promedio del año anterior.
                        </p>

                    </div>
                </center>
            </div>
        </div>
    </body>
</html>