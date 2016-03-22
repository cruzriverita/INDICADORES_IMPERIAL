/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
/* global google */

function DibujarTabla() {
    $.ajax
            ({
                type: "POST",
                url: "C_001_Produccion_Por_Planta_Servlet",
                data: {
                    mesjs: $("#mes").val(),
                    aniojs: $("#anio").val(),
                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data_) {
                    queryObject = eval('(' + JSON.stringify(data_) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data = new google.visualization.DataTable();

                    data.addColumn('string', 'INDICADOR');
                    data.addColumn('number', 'RST');
                    data.addColumn('number', 'KNIT');
                    data.addColumn('number', 'DPF');
                    data.addColumn('number', 'RLRS');
                    data.addColumn('number', 'FPS');
                    data.addColumn('number', 'RSM O&M');

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
                        var a9 = queryObject.ListaValores[i].valor9;
                        var a10 = queryObject.ListaValores[i].valor10;
                        var a11 = queryObject.ListaValores[i].valor11;
                        var a12 = queryObject.ListaValores[i].valor12;

                        data.setCell(i, 0, indicador);
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

                        if (a9 > a10) {
                            data.setCell(i, 5, parseFloat(a9), a9, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 5, parseFloat(a9), a9, {'className': 'green-background'});
                        }

                        if (a11 > a12) {
                            data.setCell(i, 6, parseFloat(a11), a11, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 6, parseFloat(a11), a11, {'className': 'green-background'});
                        }
                    }

                    var cssClassNames = {
                        'headerRow': 'italic-darkblue-font large-font bold-font',
                        'tableRow': '',
                        'oddTableRow': 'beige-background',
                        //'selectedTableRow': 'orange-background large-font',
                        //'hoverTableRow': '',
                        'headerCell': 'gold-border',
                        'tableCell': '',
                        'rowNumberCell': 'underline-blue-font'};

                    var options = {'showRowNumber': true, 'allowHtml': false, 'cssClassNames': cssClassNames};
                    var table = new google.visualization.Table(document.getElementById('table'));
                    table.draw(data, options);


                    $("#table table tbody tr td").click(function () {
                        myFunction(this);
                    });
                },
                error: function () {
                    alert('No existen datos para los parametros elegidos');
                    document.getElementById("mes").value = 2; //MesActual();
                    location.reload();
                },
                async: false
            });
}

function myFunction(x) {
    var rows = document.getElementById('table').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function () {
            //alert("Columna " + x.cellIndex + "FILA " + this.rowIndex);

            if (this.rowIndex === 1)
            {
                if (x.cellIndex === 2)
                {
                    location.href = "I_001_Kilos_Producidos_Hora_Hombre.jsp?planta=PLANTA RST";
                }
                else
                if (x.cellIndex === 3)
                {
                    location.href = "I_001_Kilos_Producidos_Hora_Hombre.jsp?planta=PLANTA KNIT";
                }
                else
                if (x.cellIndex === 4)
                {
                    location.href = "I_001_Kilos_Producidos_Hora_Hombre.jsp?planta=PLANTA DPF";
                }
                else
                if (x.cellIndex === 5)
                {
                    location.href = "I_001_Kilos_Producidos_Hora_Hombre.jsp?planta=PLANTA RLRS";
                }
                else
                if (x.cellIndex === 6)
                {
                    location.href = "I_001_Kilos_Producidos_Hora_Hombre.jsp?planta=PLANTA FPS";
                }
                if (x.cellIndex === 7)
                {
                    location.href = "I_001_Kilos_Producidos_Hora_Hombre.jsp?planta=PLANTA RSM";
                }
            }
        };
    }
}



