

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html">

    <script type="text/javascript" src="Js/js/loader.js"></script>
    <script type="text/javascript" src="Js/js/jquery-1.12.1.min.js"></script>
    <script type="text/javascript" src="Js/js/FuncionesGlobales.js"></script>
    <script type="text/javascript" src="Js/C_004_Indicadores_Financieros.js"></script>
    <script type="text/javascript">
        /* global google */
        google.charts.load('current', {'packages': ['table']});
        google.charts.setOnLoadCallback(DibujarTabla);
    </script>    

    <script type="text/javascript">
        $(document).ready(function () {
            // $('#opciones').val("1");
            $('#anio').val("2015");
        });
    </script> 

    <head>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700&subset=latin,cyrillic-ext,latin-ext,cyrillic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/EstiloJSP.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <title>Inventarios</title>
    </head>

    <body class="body">

        <script type="text/javascript" src="Js/js/CuerpoMenuHorizontal.js"></script>

        <div id="DivPrincipal" class="divprincipal">

            <div class="DivWithScroll">


                <form method="get" action="" class="formulario">

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
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual - 2%>"> <%=Utilidades.MetodosGlobales.year_actual - 2%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual - 1%>"> <%=Utilidades.MetodosGlobales.year_actual - 1%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual%>" selected> <%=Utilidades.MetodosGlobales.year_actual%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual + 1%>"> <%=Utilidades.MetodosGlobales.year_actual + 1%> </option>
                                    <option value="<%=Utilidades.MetodosGlobales.year_actual + 2%>"> <%=Utilidades.MetodosGlobales.year_actual + 2%> </option>
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
                        <input   type="image" style="height:48px;width:48px;"  onmouseover="this.style.background = '#0fa1e0';
                                ShowDef();" onmouseout="this.style.background = 'white';
                                        HideDef();" src="Images/dd.svg">
                    </div>

                    <div class="DefStyle" id="EmaliographyDef">Descargar</div>

                </form> 

                <br>

                <center>
                    <h2>Indicadores Financieros</h2>
                </center>
                <br>
                <center>
                 <div id="table">
                </div>


                
                    <div > 
                        <p >
                            *En color verde se muestran los valores mayores al promedio del año anterior.<br/>
                            *En color rojo se muestran los valores menores al promedio del año anterior.</p>
                    </div>
                </center>>
            </div>
        </div>
    </body>
</html>