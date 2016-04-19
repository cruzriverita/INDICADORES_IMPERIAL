<%-- 
    Document   : C_001_Produccion_Por_Planta
    Created on : 21/03/2016, 11:01:02 AM
    Author     : rcruz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="html">
    <% String mes = String.valueOf(Utilidades.MetodosGlobales.month_actual);%>
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
            $('#menu').multilevelpushmenu('expand', 'PRODUCCION');
            $(window).resize(function () {
                DibujarTabla();
            });
            $('#opciones').val("ALL");
            $('#mes').val(<%=mes%>);
        });
    </script> 
    <head>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700&subset=latin,cyrillic-ext,latin-ext,cyrillic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/jquery.multilevelpushmenu_red.css">
        <link rel="stylesheet" href="css/basicjs.css">
        <link rel="stylesheet" href="css/EstiloJSP.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <title>Produccion Por Planta</title>
    </head>

    <body class="body">

         <!-- <div id="DivMenu" class="MenuDesplegable">
            <div id="menu"> </div>
        </div>-->

         <script type="text/javascript" src="Js/js/CuerpoMenuHorizontal.js"></script>
        <div id="DivPrincipal" class="divprincipal">

            <div class="DivWithScroll">


                <form method="get" action="C_001_Produccion_Por_Planta_XLS" class="formulario">


                    <div style="float: left; width: 70%;">
                        
                        <div>
                            <div class="divtexto" id="divlblmes">
                                <input id="lblmes" name="lblmes" onkeypress="" value="Mes" disabled="true" class="texto" type="text"> 
                            </div>
                            
                            <div class="divtexto">
                                <input id="lbla" name="lbla" onkeypress="" value="Año" disabled="true" class="texto" type="text"> 
                            </div>
                            
                            <div class="divtexto">
                                <input id="lblP" name="lblP" onkeypress="" value="Planta" disabled="true" class="texto" type="text"> 
                            </div>
                            
                            <div class="divboton">
                                <input value="Exportar Datos" class="boton" type="SUBMIT">
                            </div>

                        </div>
                        
                        <div style="clear:both;">                

                          
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
                            
                            <div class="divselect">
                                <select id="anio" name="anio" onchange="DibujarTabla()" class="select">
                                    <option value="2014"> 2014 </option>
                                    <option value="2015"> 2015 </option>
                                    <option value="2016" selected=""> 2016 </option>
                                    <option value="2017"> 2017 </option>
                                    <option value="2018"> 2018 </option>
                                </select>
                            </div>
                            
                            <div class="divselect">
                                <select id="opciones" name="opciones" onchange="DibujarTabla();" class="select">
                                    <option value="ALL">Todas</option>                
                                    <option value="PLANTA FPS">FPS</option>                    
                                </select>
                            </div>
                      
                        </div>
                   
                    </div>
                </form> 
                <br>
                <CENTER>
                    <h2> INDICADORES PRODUCCION</h2>
                    <h4> Produccion Kilogramos</h4>
                    <div id="table" class="divtablascroll">
                    </div>
                </CENTER>
                
               
                <div> 
                    <p class="formato_parrafo">  *En color verde se muestran los valores mayores al promedio<br/>
                        *En color rojo se muestran los valores menores al promedio</p>
                </div>
                
                <CENTER>
                    <h4> Produccion Costos</h4>
                    <div id="table2" class="divtablascroll">
                    </div>
                    
                </CENTER>

                <p class="formato_parrafo">*En color verde se muestran los valores menores al promedio<br/>
                    *En color rojo se muestran los valores mayores al promedio</p>        
            </div>


        </div>
    </div>
</body>
</html>