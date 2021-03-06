/* global google */

function DibujarTabla() {
    $.ajax
            ({
                type: "POST",
                url: "C_001_Produccion_Por_Planta_Servlet",
                data: {
                    mesjs: $('#mes option:selected').val(),
                    aniojs: $("#anio").val(),
                    opcion: $('#opciones option:selected').val()
                },
                dataType: "json",
                success: function (data_) {

                    queryObject = eval('(' + JSON.stringify(data_) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data = new google.visualization.DataTable();

                    data.addColumn('string', 'INDICADOR');
                    data.addColumn('number', 'RST');
                    data.addColumn('number', 'RLRS');
                    data.addColumn('number', 'RSM');
                    data.addColumn('number', 'KNIT');
                    data.addColumn('number', 'DPF');


                    data.addRows(queryObjectLen);
                    for (var i = 0; i < queryObjectLen; i++)
                    {
                        var indicador = queryObject.ListaValores[i].indicador;
                        var a1 = queryObject.ListaValores[i].valor1; //RST
                        var a2 = queryObject.ListaValores[i].valor2; //Promedio RST
                        var a3 = queryObject.ListaValores[i].valor3; //RLRS
                        var a4 = queryObject.ListaValores[i].valor4; //Promedio RLRS
                        var a5 = queryObject.ListaValores[i].valor5; //RSM
                        var a6 = queryObject.ListaValores[i].valor6; //Promedio RLRS
                        var a7 = queryObject.ListaValores[i].valor7; //KNIT
                        var a8 = queryObject.ListaValores[i].valor8; //Promedio KNIT
                        var a11 = queryObject.ListaValores[i].valor11; //DPF
                        var a12 = queryObject.ListaValores[i].valor12; //Promedio DPF

                        //Agregar nombre del indicador.
                        data.setCell(i, 0, indicador);

                        /*Comparar promedio (promedio del año anterior) vs valor actual, si el promedio es mayor la celda del indicador
                         se muestra en color rojo, en caso contrario se muestra en color verde.*/

                        if (a2 > a1) {
                            data.setCell(i, 1, parseFloat(a1), a1, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 1, parseFloat(a1), a1, {'className': 'green-background'});
                        }
                        if (a4 > a3) {
                            data.setCell(i, 2, parseFloat(a3), a3, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 2, parseFloat(a3), a3, {'className': 'green-background'});
                        }
                        if (a6 > a5) {
                            data.setCell(i, 3, parseFloat(a5), a5, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 3, parseFloat(a5), a5, {'className': 'green-background'});
                        }
                        if (a8 > a7) {
                            data.setCell(i, 4, parseFloat(a7), a7, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 4, parseFloat(a7), a7, {'className': 'green-background'});
                        }
                        if (a12 > a11) {
                            data.setCell(i, 5, parseFloat(a11), a11, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 5, parseFloat(a11), a11, {'className': 'green-background'});

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

                    var options = {'showRowNumber': true, 'allowHtml': false, 'cssClassNames': cssClassNames, width: '100%', height: '100%'};
                    var table = new google.visualization.Table(document.getElementById('table'));
                    table.draw(data, options);

                    //Redirecciona dependiendo de la celda a la que se le da click.
                    $("#table table tbody tr td").click(function () {
                        Redireccionar(this, 'table');
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
                    /*Solo se mostrara el mensaje en una de las dos tablas para evitar doble mensaje de error*/
                    //alert('No existen datos para los parametros elegidos');
                    //document.getElementById("mes").value = MesActual();
                    //location.reload();
                }

            });

    /********************tabla con indicadores basados en costos, se recibe la informacion para llenar la 2da tabla.***********************/
    $.ajax
            ({
                type: "POST",
                url: "C_001_Produccion_Por_Planta_Servlet2",
                data: {
                    mesjs: $("#mes").val(),
                    aniojs: $("#anio").val()
                },
                dataType: "json",
                success: function (data_) {

                    queryObject = eval('(' + JSON.stringify(data_) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data = new google.visualization.DataTable();

                    data.addColumn('string', 'INDICADOR');
                    data.addColumn('number', 'RST');
                    data.addColumn('number', 'RLRS');
                    data.addColumn('number', 'RSM');
                    data.addColumn('number', 'KNIT');
                    data.addColumn('number', 'DPF');

                    data.addRows(queryObjectLen);
                    for (var i = 0; i < queryObjectLen; i++)
                    {
                        var indicador = queryObject.ListaValores[i].indicador;
                        var a1 = queryObject.ListaValores[i].valor1;
                        var a2 = queryObject.ListaValores[i].valor2;
                        var a3 = queryObject.ListaValores[i].valor3;
                        var a4 = queryObject.ListaValores[i].valor4;
                        var a5 = queryObject.ListaValores[i].valor5;
                        var a6 = queryObject.ListaValores[i].valor6;
                        var a7 = queryObject.ListaValores[i].valor7;
                        var a8 = queryObject.ListaValores[i].valor8;

                        var a11 = queryObject.ListaValores[i].valor11;
                        var a12 = queryObject.ListaValores[i].valor12;

                        data.setCell(i, 0, indicador);
                        //Comparar promedio vs valor actual para elegir color de la celda
                        if (a1 > a2) {
                            data.setCell(i, 1, parseFloat(a1), a1, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 1, parseFloat(a1), a1, {'className': 'green-background'});
                        }
                        if (a3 > a4) {
                            data.setCell(i, 2, parseFloat(a3), a3, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 2, parseFloat(a3), a3, {'className': 'green-background'});
                        }
                        if (a5 > a6) {
                            data.setCell(i, 3, parseFloat(a5), a5, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 3, parseFloat(a5), a5, {'className': 'green-background'});
                        }
                        if (a7 > a8) {
                            data.setCell(i, 4, parseFloat(a7), a7, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 4, parseFloat(a7), a7, {'className': 'green-background'});
                        }

                        if (a11 > a12) {
                            data.setCell(i, 5, parseFloat(a11), a11, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 5, parseFloat(a11), a11, {'className': 'green-background'});
                        }

                    }


                    //Opciones Css de la tabla
                    var cssClassNames = {
                        'headerRow': 'large-font bold-font',
                        'tableRow': '',
                        'headerCell': 'gold-border',
                        'tableCell': '',
                        'rowNumberCell': 'black-font'};

                    var options = {'showRowNumber': true, 'allowHtml': false, 'cssClassNames': cssClassNames, width: '100%', height: '100%'};
                    var table = new google.visualization.Table(document.getElementById('table2'));
                    table.draw(data, options);

                    //Redirecciona dependiendo de la celda a la que se le da click.
                    $("#table2 table tbody tr td").click(function () {
                        Redireccionar2(this, 'table2');
                    });

                    //Cambiar color al estar sobre la celda
                    $("#table2 table tbody tr td").mouseover(function () {
                        CambiarColorCelda(this, 'table2');
                    });

                    //Regresar al color original la celda
                    $("#table2 table tbody tr td").mouseout(function () {
                        ColorOriginalCelda(this, 'table2');
                    });
                },
                error: function () {
                    alert('No existen datos para los parametros elegidos');
                    //location.reload(); //Para cargar la pagina nuevamente y que se cargue con los valores de parametros por defecto.
                    window.location.href = window.location;
                }

            });
}

/*Se ejecuta al dar click sobre una celda de la tabla de produccion en kilogramos.
 se evalua primero por fila y luego por columna, cada fila (rowindex) representa un indicador y cada columna
 (cellindex) representa una planta */

function Redireccionar(x, element) {
    var rows = document.getElementById(element).getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function () {

            if (x.cellIndex === 1)
            {
                location.href = "I_001_IndicadoresProduccion.jsp?planta=ALL&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "&indicador=INDICADOR" + this.rowIndex;
            }
            else
            if (x.cellIndex === 2)
            {
                location.href = "I_001_IndicadoresProduccion.jsp?planta=PLANTA RST&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "&indicador=INDICADOR" + this.rowIndex;
            }
            else
            if (x.cellIndex === 3)
            {
                location.href = "I_001_IndicadoresProduccion.jsp?planta=PLANTA RLRS&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "&indicador=INDICADOR" + this.rowIndex;
            }
            else
            if (x.cellIndex === 4)
            {
                location.href = "I_001_IndicadoresProduccion.jsp?planta=PLANTA RSM&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "&indicador=INDICADOR" + this.rowIndex;
            }
            else
            if (x.cellIndex === 5)
            {
                location.href = "I_001_IndicadoresProduccion.jsp?planta=PLANTA KNIT&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "&indicador=INDICADOR" + this.rowIndex;
            }
            else
            if (x.cellIndex === 6)
            {
                location.href = "I_001_IndicadoresProduccion.jsp?planta=PLANTA DPF&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "&indicador=INDICADOR" + this.rowIndex;
            }
        };
    }
}


//se ejecuta al dar clic sobre una celda de la tabla de indicadores basados en costos
function Redireccionar2(x, element) {
    var rows = document.getElementById(element).getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function () {
            //alert("Columna " + x.cellIndex + "FILA " + this.rowIndex);

            if (x.cellIndex === 1)
            {
                location.href = "I_001_IndicadoresProduccion.jsp?planta=ALL&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "&indicador=INDICADOR" + (this.rowIndex + 3);
            }
            else
            if (x.cellIndex === 2)
            {
                location.href = "I_001_IndicadoresProduccion.jsp?planta=PLANTA RST&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "&indicador=INDICADOR" + (this.rowIndex + 3);
            }
            else
            if (x.cellIndex === 3)
            {
                location.href = "I_001_IndicadoresProduccion.jsp?planta=PLANTA RLRS&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "&indicador=INDICADOR" + (this.rowIndex + 3);
            }
            else
            if (x.cellIndex === 4)
            {
                location.href = "I_001_IndicadoresProduccion.jsp?planta=PLANTA RSM&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "&indicador=INDICADOR" + (this.rowIndex + 3);
            }
            else
            if (x.cellIndex === 5)
            {
                location.href = "I_001_IndicadoresProduccion.jsp?planta=PLANTA KNIT&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "&indicador=INDICADOR" + (this.rowIndex + 3);
            }
            else
            if (x.cellIndex === 6)
            {
                location.href = "I_001_IndicadoresProduccion.jsp?planta=PLANTA DPF&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "&indicador=INDICADOR" + (this.rowIndex + 3);
            }
        };
    }
}