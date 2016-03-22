/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global google */

function DibujarChartPrincipal1() {
    $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "I_002_Kg_Producidos_Kwh_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs.         
                data: {
                    mesjs: $("#mes").val(), aniojs: $("#anio").val()
                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data_) {
                    queryObject = eval('(' + JSON.stringify(data_) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data = new google.visualization.DataTable();

                    var x = parseInt($("#anio").val(), 10);

                    data.addColumn('string', 'Planta');
                    //data.addColumn('number', '2015');
                    data.addColumn('number', x - 1);
                    data.addColumn('number', $("#anio").val());
                    data.addColumn('number', 'mejor');
                    data.addColumn('number', 'Acumulado ' + String(x - 1));
                    data.addColumn('number', 'Acumulado ' + $("#anio").val());

                    for (var i = 0; i < queryObjectLen; i++)
                    {
                        var planta = queryObject.ListaValores[i].planta;
                        var a1 = queryObject.ListaValores[i].valor1;
                        var a2 = queryObject.ListaValores[i].valor2;
                        var a3 = queryObject.ListaValores[i].valor3;
                        var a4 = queryObject.ListaValores[i].valor4;
                        var a5 = queryObject.ListaValores[i].valor5;

                        data.addRows([
                            [planta, parseFloat(a1), parseFloat(a2), parseFloat(a3),
                                parseFloat(a4), parseFloat(a5)
                            ]
                        ]);
                    }

                    var options = {
                        title: 'Kilos Producidos/Kilovatio hora - ' + ConvertirMes($("#mes").val()),
                        vAxis: {title: 'Kilogramos', titleTextStyle: {color: 'Black'}},
                        is3D: true

                    };
                    var chart = new google.visualization.ColumnChart(document.getElementById('GraficaPrincipal'));

                    function ClickBarra() {
                        var selectedItem = chart.getSelection()[0];
                        if (selectedItem) {

                        }
                    }
                    google.visualization.events.addListener(chart, 'select', ClickBarra);
                    chart.draw(data, options);

                },
                error: function () {

                    alert('No existen datos para el mes' + $("#mes").val());
                    document.getElementById("mes").value = 2; //MesActual();
                    location.reload();
                },
                async: false
            });
}