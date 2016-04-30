

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html">

    <script type="text/javascript" src="Js/js/loader.js"></script>
    <script type="text/javascript" src="Js/js/jquery-1.12.1.min.js"></script>
    <script type="text/javascript" src="Js/js/FuncionesGlobales.js"></script>
    <script type="text/javascript" src="Js/C_003_Indicadores_Inventarios.js"></script>
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


                <form method="get" action="C_001_Produccion_Por_Planta_XLS" class="formulario">

                    <div style="float: left; width: 70%;">
                        <div>

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


                        </div>

                    </div>
                                   <div class="divboton" id="divb">
                                    <input   type="image" style="height:48px;width:48px;"  onmouseover="this.style.background='#0fa1e0'; ShowDef();" onmouseout="this.style.background='white'; HideDef();" src="Images/dd.svg">
                                </div>

                                <div class="DefStyle" id="EmaliographyDef">Descargar</div>

                </form> 

                <br>

                <CENTER>
                    <h2>Rotacion De Inventarios</h2>
                    <div id="table">
                    </div>
                </CENTER>

                <center>
                    <div> 
                        <p>*En color verde se muestran los valores mayores al promedio<br/>
                         *En color rojo se muestran los valores menores al promedio</p>
                    </div>
                </center>
            </div>
        </div>
    </body>
</html>