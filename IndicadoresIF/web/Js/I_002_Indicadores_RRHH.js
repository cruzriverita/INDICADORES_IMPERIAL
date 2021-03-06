/* global google */

function DibujarChartPrincipal() {
    $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "I_002_Indicadores_RRHH_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs.         
                data: {
                    aniojs: $("#anio").val(),
                    tipojs: $('#tipo option:selected').val(),
                    indicador: $('#indicador').val() //$('#indicador').val()
                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data_) {

                    var tamlinea = tamalinea();

                    queryObject = eval('(' + JSON.stringify(data_) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data = new google.visualization.DataTable();

                    /*convertir #anio a entero*/
                    var x = parseInt($('#anio').val(), 10);

                    data.addColumn('string', 'periodo');
                    data.addColumn({type: 'string', role: 'annotation'})

                    data.addColumn('number', x - 1); //año anterior  
                    data.addColumn('number', 'Mayor historico');
                    data.addColumn('number', x);   //año actual  
                    data.addColumn('number', 'Promedio ' + (x - 1));
                    data.addColumn('number', 'Menor historico');

                    for (var i = 0; i < queryObjectLen; i++)
                    {
                        var periodo = queryObject.ListaValores[i].periodo;

                        //Si se selecciona la opcion NOMINA se trabaja con meses.
                        if ($('#tipo option:selected').val() === "1")
                        {
                            periodo = ConvertirMes(queryObject.ListaValores[i].periodo);
                        }


                        var a1 = queryObject.ListaValores[i].valor1;
                        var a2 = queryObject.ListaValores[i].valor2;
                        a1 = DevolverNull(a1);
                        a2 = DevolverNull(a2);

                        var mayor = queryObject.ListaValores[i].valor3;
                        var mayormes = queryObject.ListaValores[i].valor4;
                        var mayoranio = queryObject.ListaValores[i].valor5;
                        var menor = queryObject.ListaValores[i].valor6;
                        var menormes = queryObject.ListaValores[i].valor7;
                        var menoranio = queryObject.ListaValores[i].valor8;
                        var promedio = queryObject.ListaValores[i].valor9;

                        data.addRows([
                            [periodo, "", parseFloat(a1), parseFloat(mayor),
                                parseFloat(a2), parseFloat(promedio), parseFloat(menor)
                            ]
                        ]);
                    }


                    if ($('#tipo option:selected').val() === "1") //Si es Nomina
                    {
                        //Si es el indicador NUMERO DE EMPLEADOS
                        if ($('#indicador').val() === "INDICADOR7")
                        {
                            var options = {
                                title: '',
                                vAxis: {title: 'Cantidad de empleados', titleTextStyle: {color: ColorFuenteGrafica()}, textStyle: {color: ColorFuenteGrafica()},
                                    gridlines: {count: 20}, viewWindow: {
                                        min: (menor - 10),
                                        max: (mayor + 20)
                                    }
                                },
                                hAxis: {title: '*El valor "Menor historico" corresponde a ' + ConvertirMes(menormes) + ' de ' + menoranio + '\n' +
                                            '*El valor "Mayor historico" corresponde a ' + ConvertirMes(mayormes) + ' de ' + mayoranio, titleTextStyle: {color: ColorFuenteGrafica()},
                                    textStyle: {color: ColorFuenteGrafica()}},
                                is3D: true,
                                colors: Coloresrrhh(),
                                annotations:
                                        {
                                            style: 'line'
                                        },
                                series:
                                        {
                                            0: {pointShape: 'circle', pointSize: tamapunto()},
                                            2: {pointShape: 'circle', pointSize: tamapunto()}

                                        },
                                lineWidth: tamlinea,
                                backgroundColor: FondoGrafica(),
                                legend: {
                                    textStyle: {
                                        color: ColorFuenteGrafica()
                                    }}
                            };
                        }
                        else //Si es el indicador DEVENGADO/NO_EMPLEADOS
                        {
                            var options = {
                                title: '', //Promedio Devengado por empleado (Nomina) ' + x,
                                vAxis: {title: '(Q) Promedio devengado por empleado ', titleTextStyle: {color: ColorFuenteGrafica()}, textStyle: {color: ColorFuenteGrafica()},
                                    gridlines: {count: 20}, viewWindow: {
                                        min: (menor - 100),
                                        max: (mayor + 100)
                                    }},
                                hAxis: {title: '*El valor "Menor historico" corresponde a ' + ConvertirMes(menormes) + ' de ' + menoranio + '\n' +
                                            '*El valor "Mayor historico" corresponde a ' + ConvertirMes(mayormes) + ' de ' + mayoranio, titleTextStyle: {color: ColorFuenteGrafica()}
                                    , textStyle: {color: ColorFuenteGrafica()}},
                                is3D: true,
                                colors: Coloresrrhh(),
                                annotations: {
                                    style: 'line'
                                },
                                series: {
                                    0: {pointShape: 'circle', pointSize: tamapunto()},
                                    2: {pointShape: 'circle', pointSize: tamapunto()}

                                }, lineWidth: tamlinea,
                                backgroundColor: FondoGrafica(),
                                legend: {
                                    textStyle: {
                                        color: ColorFuenteGrafica()
                                    }}
                            };


                        }
                    }
                    /*-----------------------------------Si es Planilla --------------------------------*/
                    else
                    {
                        if ($('#indicador').val() === "INDICADOR7")
                        {
                            var options = {
                                title: '', //'Cantidad de empleados Planilla '+ x,
                                vAxis: {title: 'Cantidad de empleados', titleTextStyle: {color: ColorFuenteGrafica()}, textStyle: {color: ColorFuenteGrafica()},
                                    gridlines: {count: 20}, viewWindow: {
                                        min: (menor - 10),
                                        max: (mayor + 10)
                                    }},
                                hAxis: {title: 'Catorcena\n' + '*El valor "Menor historico" corresponde a la catorcena ' + menormes + ' de ' + menoranio + '\n' +
                                            '*El valor "Mayor historico" corresponde a la catorcena ' + mayormes + ' de ' + mayoranio, titleTextStyle: {color: ColorFuenteGrafica()},
                                    textStyle: {color: ColorFuenteGrafica()}},
                                is3D: true,
                                colors: Coloresrrhh(),
                                annotations: {
                                    style: 'line'
                                },
                                series: {
                                    0: {pointShape: 'circle', pointSize: tamapunto()},
                                    2: {pointShape: 'circle', pointSize: tamapunto()}

                                }, lineWidth: tamlinea,
                                backgroundColor: FondoGrafica(),
                                legend: {
                                    textStyle: {
                                        color: ColorFuenteGrafica()
                                    }}
                            };
                        }
                        else
                        {
                            var options = {
                                title: '', //Promedio devengado por empleado (Planilla) ' + x,
                                vAxis: {title: '(Q) Promedio devengado por empleado', titleTextStyle: {color: ColorFuenteGrafica()}, textStyle: {color: ColorFuenteGrafica()},
                                    gridlines: {count: 20}, viewWindow: {
                                        min: (menor - 100),
                                        max: (mayor + 100)
                                    }},
                                hAxis: {title: 'Catorcena\n' + '*El valor menor corresponde a la catorcena ' + menormes + ' de ' + menoranio + '\n' +
                                            '*El valor mayor corresponde a la catorcena ' + mayormes + ' de ' + mayoranio, titleTextStyle: {color: ColorFuenteGrafica()},
                                    textStyle: {color: ColorFuenteGrafica()}},
                                is3D: true,
                                colors: Coloresrrhh(),
                                annotations: {
                                    style: 'line'
                                },
                                series: {
                                    0: {pointShape: 'circle', pointSize: tamapunto()},
                                    2: {pointShape: 'circle', pointSize: tamapunto()}

                                }, lineWidth: tamlinea,
                                backgroundColor: FondoGrafica(),
                                legend: {
                                    textStyle: {
                                        color: ColorFuenteGrafica()
                                    }}
                            };

                        }
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
                    //document.getElementById("mes").value = 2; //MesActual();
                    location.reload();
                }

            });
}

//Obtener titulo para las graficas de RRHH
function GetTituloG2() {
    var e = document.getElementById("indicador").value;

    var t = document.getElementById("tipo");
    var val = t.options[t.selectedIndex].value;

    if (val === "1") {
        if (e === "INDICADOR7")
        {
            document.getElementById('titulo').innerHTML = "Cantidad de empleados Nomina";
        }

        else if (e === "INDICADOR8")
        {
            document.getElementById('titulo').innerHTML = "Promedio Devengado por empleado (Nomina) ";
        }
    }
    else
    {

        if (e === "INDICADOR7")
        {
            document.getElementById('titulo').innerHTML = "Cantidad de empleados Planilla";
        }

        else if (e === "INDICADOR8")
        {
            document.getElementById('titulo').innerHTML = "Promedio Devengado por empleado (Planilla) ";
        }
    }
}

function GetSubTituloG2() {
    var anio = document.getElementById("anio").value;
    document.getElementById('subtitulo').innerHTML = anio;
}