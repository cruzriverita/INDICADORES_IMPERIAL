
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
/* global google */

//TABLA DE INDICADORES FINANCIEROS.

function DibujarTabla() {
    $.ajax
            ({
                type: "POST",
                url: "C_004_Indicadores_Financieros_Servlet",
                data: {
                    aniojs: $("#anio").val(),
                    mesjs: $("#mes").val()

                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data_) {

                    queryObject = eval('(' + JSON.stringify(data_) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data = new google.visualization.DataTable();


                    data.addColumn('string', 'INDICADOR');
                    data.addColumn('number', 'Imperial Fashion');
                    data.addColumn('number', 'MT Textil');
                    data.addColumn('number', 'Blake');
                    data.addColumn('number', 'Imperialtex');
                    data.addColumn('number', 'Fabrica Imperial');
                    data.addColumn('number', 'Consolidado');
                    //data.addColumn('number', ''+ (x-1));

                    data.addRows(queryObjectLen);
                    for (var i = 0; i < queryObjectLen; i++)
                    {
                        var tipo = queryObject.ListaValores[i].tipo;

                        var a1 = queryObject.ListaValores[i].valor1; //IF
                        var a2 = queryObject.ListaValores[i].valor2; //MT
                        var a3 = queryObject.ListaValores[i].valor3; //Blake

                        var a7 = queryObject.ListaValores[i].valor7; //Imperialtex
                        var a8 = queryObject.ListaValores[i].valor8; //Fabrica Imperial
                        var a9 = queryObject.ListaValores[i].valor9; //Consolidado


                        var a4 = queryObject.ListaValores[i].valor4; //promedio IF
                        var a5 = queryObject.ListaValores[i].valor5; //promedio MT
                        var a6 = queryObject.ListaValores[i].valor6; //Promedio blake

                        var a10 = queryObject.ListaValores[i].valor10; //promedio Imperialtex
                        var a11 = queryObject.ListaValores[i].valor11; //promedio Fabrica Imperial
                        var a12 = queryObject.ListaValores[i].valor12; //Promedio Consolidado

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
                            data.setCell(i, 3, parseFloat(a3), a3, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 3, parseFloat(a3), a3, {'className': 'green-background'});
                        }

                        if (a10 > a7) {
                            data.setCell(i, 4, parseFloat(a7), a7, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 4, parseFloat(a7), a7, {'className': 'green-background'});
                        }

                        if (a11 > a8) {
                            data.setCell(i, 5, parseFloat(a8), a8, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 5, parseFloat(a8), a8, {'className': 'green-background'});
                        }

                        if (a12 > a9) {
                            data.setCell(i, 6, parseFloat(a9), a9, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 6, parseFloat(a9), a9, {'className': 'green-background'});
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

                    var options = {'showRowNumber': true, 'allowHtml': false, 'cssClassNames': cssClassNames, height: '100%', width: '100%'};

                    var table = new google.visualization.Table(document.getElementById('table'));

                    table.draw(data, options);

                    //Redirecciona dependiendo de la celda a la que se le da click.
                    $("#table table tbody tr td").click(function () {
                        Redireccionar_financieros(this, 'table');
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



function Redireccionar_financieros(x, element) {
    
    var rows = document.getElementById(element).getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function () {

            /*Si es la celda con el nombre del indicador se va a la grafica general (barras) en caso contrario se va a la grafica por empresa (lineal)*/
            if (x.cellIndex === 1)
            {
                location.href = "I_004_Indicadores_Financieros.jsp?indicador=" + this.rowIndex+"&anio="+$("#anio").val();
            }
            else
            {

                location.href = "I_004_Indicadores_Financieros.jsp?indicador=" + this.rowIndex + "&empresa=" + (x.cellIndex - 1)+"&anio="+$("#anio").val();
            }
        };
    }
}
