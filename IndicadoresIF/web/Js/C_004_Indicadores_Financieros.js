
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
/* global google */

//TABLA DE INDICADORES DE INVENTARIOS.

function DibujarTabla() {
    $.ajax
            ({
                type: "POST",
                url: "C_004_Indicadores_Financieros_Servlet",
                data: {
                    aniojs: $("#anio").val(),
                    mesjs:  $("#mes").val()
                    //opcionjs: $('#opciones option:selected').val()
                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data_) {
                    //if ($('#opciones option:selected').val() === "ALL")
                    //{
                    queryObject = eval('(' + JSON.stringify(data_) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data = new google.visualization.DataTable();

                    var x = parseInt($("#anio").val(), 10);
                    
                    data.addColumn('string', 'INDICADOR');
                    
                    data.addColumn('number', 'Imperial Fashion');
                    data.addColumn('number', 'MT Textil');
                    
                    data.addColumn('number', 'Blake');                   
                    //data.addColumn('number', ''+ (x-1));

                    data.addRows(queryObjectLen);
                    for (var i = 0; i < queryObjectLen; i++)
                    {
                        var tipo = queryObject.ListaValores[i].tipo;
                        
                        var a1 = queryObject.ListaValores[i].valor1; //IF
                        var a2 = queryObject.ListaValores[i].valor2; //MT
                        var a3 = queryObject.ListaValores[i].valor3; //Blake
                        
                        
                        var a4 = queryObject.ListaValores[i].valor4; //promedio IF
                        var a5 = queryObject.ListaValores[i].valor5; //promedio MT
                        var a6 = queryObject.ListaValores[i].valor6; //Promedio blake
                        //
                        //1ra columna de la tabla, tipo de inventario de tipo string 
                        data.setCell(i, 0, tipo);
                        
                        
                        
                        //Comparar promedio vs valor actual para elegir color de la celda
                        if (a4 > a1) {
                            data.setCell(i, 1, parseFloat(a1), a1, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 1, parseFloat(a1), a1, {'className': 'green-background'});
                        }
                        
                        if (a5 > a2) {
                            data.setCell(i, 2, parseFloat(a2), a2, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 2, parseFloat(a2), a2, {'className': 'green-background'});
                        }
                        
                        if (a6 > a3) {
                            data.setCell(i, 3, parseFloat(a4), a4, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 3, parseFloat(a4), a4, {'className': 'green-background'});
                        }

                        

                    }


                    /*Opciones Css de la tabla, los valores asignados a las propiedades estan declarados como
                     clases en el archivo Estilo.css*/
                    var cssClassNames = {
                        'headerRow': 'large-font bold-font',
                        'tableRow': '',
                        //'oddTableRow': 'beige-background',
                        //'selectedTableRow': 'orange-background large-font',
                        //'hoverTableRow': '',
                        'headerCell': 'gold-border',
                        'tableCell': '',
                        'rowNumberCell': 'black-font'};

                    var options = {'showRowNumber': true, 'allowHtml': false, 'cssClassNames': cssClassNames, height: '100%'};

                    var table = new google.visualization.Table(document.getElementById('table'));

                    
                    table.draw(data, options);

                    //Redirecciona dependiendo de la celda a la que se le da click.
                    $("#table table tbody tr td").click(function () {
                        Redireccionar_inv(this, 'table');
                    });

                    //Cambiar color al estar sobre la celda
                    $("#table table tbody tr td").mouseover(function () {
                        CambiarColorCelda(this, 'table');
                    });

                    //Regresar al color original la celda
                    $("#table table tbody tr td").mouseout(function () {
                        ColorOriginalCelda(this, 'table');
                    });

                },
                error: function () {
                    alert('No existen datos para los parametros elegidos');
                    //document.getElementById("anio").value = (AnioActual() - 1);
                    location.reload();
                }

            });
}

//Se ejecuta al dar click sobre una celda de la tabla de produccion en kilogramos.
function Redireccionar_inv(x, element) {
    var y = parseInt($("#anio").val(), 10);
    var rows = document.getElementById(element).getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function () {

            if (this.rowIndex === 2) /*Indicador 1*/
            {
                if (x.cellIndex === 1)
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=1&tipo=1&anio="+y;
                }
                else if (x.cellIndex === 2 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=1&tipo=1&anio="+y;
                }
                
                else if (x.cellIndex === 3 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=1&tipo=1&anio="+(y-1);
                }
                
                 else if (x.cellIndex === 4 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=1&tipo=2&anio="+(y);
                }
                 else if (x.cellIndex === 5 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=1&tipo=2&anio="+(y-1);
                }

            }
            
            else
            if (this.rowIndex === 3) /*Indicador 2*/
            {
                if (x.cellIndex === 1)
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=2&tipo=1&anio="+y;
                }
                else if (x.cellIndex === 2 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=2&tipo=1&anio="+y;
                }
                
                else if (x.cellIndex === 3 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=2&tipo=1&anio="+(y-1);
                }
                
                 else if (x.cellIndex === 4 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=2&tipo=2&anio="+(y);
                }
                 else if (x.cellIndex === 5 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=2&tipo=2&anio="+(y-1);
                }
            }

            else
            if (this.rowIndex === 4) /*Indicador 3*/
            {
                  if (x.cellIndex === 1)
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=3&tipo=1&anio="+y;
                }
                else if (x.cellIndex === 2 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=3&tipo=1&anio="+y;
                }
                
                else if (x.cellIndex === 3 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=3&tipo=1&anio="+(y-1);
                }
                
                 else if (x.cellIndex === 4 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=3&tipo=2&anio="+(y);
                }
                 else if (x.cellIndex === 5 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=3&tipo=2&anio="+(y-1);
                }
            
            }
            else
            if (this.rowIndex === 5) /*Indicador 3*/
            {
                   if (x.cellIndex === 1)
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=4&tipo=1&anio="+y;
                }
                else if (x.cellIndex === 2 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=4&tipo=1&anio="+y;
                }
                
                else if (x.cellIndex === 3 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=4&tipo=1&anio="+(y-1);
                }
                
                 else if (x.cellIndex === 4 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=4&tipo=2&anio="+(y);
                }
                 else if (x.cellIndex === 5 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=4&tipo=2&anio="+(y-1);
                }
            }
            
            else
            if (this.rowIndex === 6) /*Indicador 3*/
            {
                    if (x.cellIndex === 1)
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=5&tipo=1&anio="+y;
                }
                else if (x.cellIndex === 2 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=5&tipo=1&anio="+y;
                }
                
                else if (x.cellIndex === 3 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=5&tipo=1&anio="+(y-1);
                }
                
                 else if (x.cellIndex === 4 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=5&tipo=2&anio="+(y);
                }
                 else if (x.cellIndex === 5 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=5&tipo=2&anio="+(y-1);
                }
            }
            
            else
            if (this.rowIndex === 7) /*Indicador 3*/
            {
                    if (x.cellIndex === 1)
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=6&tipo=1&anio="+y;
                }
                else if (x.cellIndex === 2 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=6&tipo=1&anio="+y;
                }
                
                else if (x.cellIndex === 3 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=6&tipo=1&anio="+(y-1);
                }
                
                 else if (x.cellIndex === 4 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=6&tipo=2&anio="+(y);
                }
                 else if (x.cellIndex === 5 )
                {
                    location.href = "I_003_Indicadores_Inventarios.jsp?tipo2=6&tipo=2&anio="+(y-1);
                }
            }

        };
    }
}




