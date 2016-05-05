
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
                url: "C_003_Indicadores_Inventarios_Servlet",
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
                    
                    data.addColumn('string', '');
                    
                    data.addColumn('number', '' + (x));
                    data.addColumn('number', '' + (x-1));
                    
                    data.addColumn('number', ''+ (x));                   
                    data.addColumn('number', ''+ (x-1));

                    data.addRows(queryObjectLen);
                    for (var i = 0; i < queryObjectLen; i++)
                    {
                        var tipo = queryObject.ListaValores[i].tipo;
                        var a1 = queryObject.ListaValores[i].valor1; //valor año actual 
                        var a2 = queryObject.ListaValores[i].valor2; //valor año anterior
                        var a3 = queryObject.ListaValores[i].valor3; //valor promedio año anterior
                        
                        
                        var a4 = queryObject.ListaValores[i].valor4; //valor dias año actual
                        var a5 = queryObject.ListaValores[i].valor5; //valor dias año anterior
                        var a6 = queryObject.ListaValores[i].valor6; //valor dias promedio año anterior

                        //1ra columna de la tabla, tipo de inventario de tipo string 
                        data.setCell(i, 0, tipo);
                        
                        
                        
                        //Comparar promedio vs valor actual para elegir color de la celda
                        if (a3 > a1) {
                            data.setCell(i, 1, parseFloat(a1), a1, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 1, parseFloat(a1), a1, {'className': 'green-background'});
                        }
                        
                        if (a3 > a2) {
                            data.setCell(i, 2, parseFloat(a2), a2, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 2, parseFloat(a2), a2, {'className': 'green-background'});
                        }
                        
                        if (a4 > a6) {
                            data.setCell(i, 3, parseFloat(a4), a4, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 3, parseFloat(a4), a4, {'className': 'green-background'});
                        }

                        if (a5 > a6) {
                            data.setCell(i, 4, parseFloat(a5), a5, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 4, parseFloat(a5), a5, {'className': 'green-background'});
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

                    var options = {'showRowNumber': true, 'allowHtml': false, 'cssClassNames': cssClassNames, width: '50%', height: '100%'};

                    //var table = new google.visualization.Table(document.getElementById('table'));

                    /*****************************************merge columnas para titulo***********************/
                    var divTableChart;
                    divTableChart = document.getElementById('table');

                    var table = new google.visualization.Table(divTableChart);

                    google.visualization.events.addListener(table, 'ready', function () {
                        var headerRow;
                        var newRow;

                        // get header row and clone to keep google chart style
                        headerRow = divTableChart.getElementsByTagName('THEAD')[0].rows[0];
                        newRow = headerRow.cloneNode(true);

                        // modify new row to combine cells and add labels
                        newRow.deleteCell(newRow.cells.length - 1);
                        newRow.deleteCell(newRow.cells.length - 1);
                        newRow.deleteCell(newRow.cells.length - 1);

                        newRow.cells[0].colSpan = 2;
                        newRow.cells[0].innerHTML = 'Tipo De Inventario';
                        newRow.cells[1].colSpan = 2;
                        newRow.cells[1].innerHTML = 'Indice Del Mes';
                        newRow.cells[2].colSpan = 2;
                        newRow.cells[2].innerHTML = 'Dias de Inventario';

                        // insert new / cloned row
                        divTableChart.getElementsByTagName('THEAD')[0].insertBefore(newRow, headerRow);
                    });


                    
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




