function DibujarChartPrincipal() {
    $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "IndicadoresRRHH_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs.         
                data: {
                    aniojs: $("#anio").val(),
                    tipojs: $('#tipo option:selected').val(),
                    indicador: $('#indicador').val()
                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data_) {

                    queryObject = eval('(' + JSON.stringify(data_) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data = new google.visualization.DataTable();

                    /*convertir #anio a entero*/
                    var x = parseInt($('#anio').val(), 10);


                    data.addColumn('string', 'periodo');

                    data.addColumn('number', x - 1); //año anterior  
                    data.addColumn('number', 'Mayor');
                    data.addColumn('number', x);   //año actual  
                    data.addColumn('number', 'promedio');
                    data.addColumn('number', 'Menor');




                    for (var i = 0; i < queryObjectLen; i++)
                    {
                         if ($('#tipo option:selected').val() === "1")
                    {
                        var periodo =  ConvertirMes(queryObject.ListaValores[i].periodo);
                        
                    }
                            var a1 = queryObject.ListaValores[i].valor1;
                        var a2 = queryObject.ListaValores[i].valor2;
                        var mayor = queryObject.ListaValores[i].valor3;
                        var mayormes = queryObject.ListaValores[i].valor4;
                        var mayoranio = queryObject.ListaValores[i].valor5;
                        var menor = queryObject.ListaValores[i].valor6;
                        var menormes = queryObject.ListaValores[i].valor7;
                        var menoranio = queryObject.ListaValores[i].valor8;
                        var promedio = queryObject.ListaValores[i].valor9;

                        data.addRows([
                            [periodo, parseFloat(a1), parseFloat(mayor),
                                parseFloat(a2), parseFloat(promedio), parseFloat(menor)
                            ]
                        ]);
                    }

                    if ($('#tipo option:selected').val() === "1")
                    {
                        var options = {
                            title: 'Numero de empleados ' + (x - 1) + '/' + x,
                            vAxis: {title: 'Numero De Empleados', titleTextStyle: {color: 'Black'}},
                            hAxis: {title: '*El valor menor corresponde a ' + ConvertirMes(menormes) + ' de ' + menoranio + '\n' +
                                        'El valor mayor corresponde a ' + ConvertirMes(mayormes) + ' de ' + mayoranio, titleTextStyle: {color: 'Blue'}},
                            is3D: true,
                            colors: Coloresrrhh()
                        };

                    }
                    else
                    {

                        var options = {
                            title: 'Numero de empleados ' + (x - 1) + '/' + x,
                            vAxis: {title: 'Numero De Empleados', titleTextStyle: {color: 'Black'}},
                            hAxis: {title: '*El valor menor corresponde a la catorcena ' + menormes + ' de ' + menoranio + '\n' +
                                        'El valor mayor corresponde a la catorcena ' + mayormes + ' de ' + mayoranio, titleTextStyle: {color: 'Blue'}},
                            is3D: true,
                            colors: Coloresrrhh()
                        };


                    }
                    var chart = new google.visualization.LineChart(document.getElementById('GraficaPrincipal'));


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
                }

            });
}