/* global google */

function DibujarChartPrincipal() {
    $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "I_003_Indicadores_Inventarios_Planta_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs,opcion.         
                data: {
                    mesjs: $("#mes").val(),
                    aniojs: $("#anio").val(),
                    opcion: $('#opciones option:selected').val(), //trae la planta seleccionada.
                    tipojs: $('#tipo2').val()
                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data_) {

                    //Si se elige una de estas dos opciones entonces se muestra la grafica de barras, se separa DPF (trabaja con docenas y no KG)
                    if ($('#opciones option:selected').val() === "ALL")
                    {
                        queryObject = eval('(' + JSON.stringify(data_) + ')');
                        queryObjectLen = queryObject.ListaValores.length;
                        var data = new google.visualization.DataTable();
                        /*convertir #anio a entero*/
                        var x = parseInt($("#anio").val(), 10);


                        data.addColumn('string', 'Planta');
                        data.addColumn('number', (x - 1));
                        data.addColumn('number', 'Mayor Historico');
                        data.addColumn('number', x);
                        data.addColumn('number', 'Promedio ' + (x - 1));
                        data.addColumn('number', 'Menor Historico');

                        for (var i = 0; i < queryObjectLen; i++)
                        {
                            var planta = queryObject.ListaValores[i].planta;

                            
                          

                            var a1 = queryObject.ListaValores[i].valor1;
                            var a2 = queryObject.ListaValores[i].valor2;
                            var a3 = queryObject.ListaValores[i].valor3;
                            var a6 = queryObject.ListaValores[i].valor6;
                            var a4 = queryObject.ListaValores[i].valor4;



                            data.addRows([
                                [planta, /*parseFloat(a4)*/ parseFloat(a1), parseFloat(a3),
                                    parseFloat(a2), /*parseFloat(a5)*/parseFloat(a6), parseFloat(a4)
                                ]
                            ]);
                        }

                        //Si se eligen todas las plantas
                        if ($('#opciones option:selected').val() === "ALL") {
                            var options = {
                                title: '',
                                vAxis: {title: GetTituloEje() + '\n\n',
                                    titleTextStyle: {color: 'Black'}},
                                is3D: true,
                                colors: Coloresrrhh(),
                                //Formato de anotaciones sobre la grafica si las llevara
                                annotations: {
                                    textStyle: {
                                        //fontName: 'Times-Roman',
                                        //fontSize: 10,
                                        // bold: true,
                                        // italic: true,
                                        //color: '#fff',// The color of the text.
                                        //auraColor: 'transparent' // The color of the text outline.
                                        //opacity: 0.8 // The transparency of the text.
                                    }
                                }
                            };
                        }
                        //Si se selecciona FPS
                        else
                        {
                            var options = {
                                title: '', //GetTituloDPF() + ConvertirMes($("#mes").val()) + ' ' + $("#anio").val(),
                                vAxis: {title: GetTituloDPFEje(), titleTextStyle: {color: 'Black'}},
                                is3D: true,
                                colors: Coloresrrhh()
                            };

                        }

                        var chart = new google.visualization.ColumnChart(document.getElementById('GraficaPrincipal'));

                        //funcion que se ejecuta al dar click sobre la barra
                        function ClickBarra() {

                            var selectedItem = chart.getSelection()[0];
                            if (selectedItem) {
                                var planta = data.getValue(selectedItem.row, 0);
                                click_grafica(planta);
                                
                                
                            }
                        }
                        google.visualization.events.addListener(chart, 'select', ClickBarra);
                        chart.draw(data, options);

                    }

/*********************************************GRAFICA LINEAL****************************************************************/
                    //Si en el Select se elige una planta especifica se muestra por medio de grafica lineal
                    else
                    {
                        var tamlinea = tamalinea();
                        //Colocar en el titulo "RSM" en lugar de "RSM O&M"
                        var planta = $('#opciones option:selected').val();
                        if (planta === "PLANTA RSM O&M" || planta === "PLANTA MRS O&M")
                            planta = "PLANTA RSM";
                        
                      

                        queryObject = eval('(' + JSON.stringify(data_) + ')');
                        queryObjectLen = queryObject.ListaValores.length;
                        var data = new google.visualization.DataTable();

                        var x = parseInt($("#anio").val(), 10);

                        data.addColumn('string', 'mes');
                        data.addColumn({type: 'string', role: 'annotation'});
                        data.addColumn('number', (x - 1));
                        //data.addColumn('number', MayorMenor());
                        data.addColumn('number', 'Mayor Historico');
                        data.addColumn('number', x);
                        //data.addColumn({type:'string', role:'annotation'});
                        data.addColumn('number', 'Promedio ' + (x - 1));
                        //data.addColumn({type:'string', role:'annotation'});
                        data.addColumn('number', 'Menor Historico');

                        for (var i = 0; i < queryObjectLen; i++)
                        {
                            var mes = queryObject.ListaValores[i].mes;
                            var a1 = queryObject.ListaValores[i].valor1;
                            var a2 = queryObject.ListaValores[i].valor2;

                            a1=DevolverNull(a1);
                            a2=DevolverNull(a2);
                            
                            var a3 = queryObject.ListaValores[i].valor3;
                            var a4 = queryObject.ListaValores[i].valor4;
                            var a6 = queryObject.ListaValores[i].valor6;
                            var m = queryObject.ListaValores[i].mejormes;
                            var a = queryObject.ListaValores[i].mejoranio;
                            var mm = queryObject.ListaValores[i].peormes;
                            var aa = queryObject.ListaValores[i].peoranio;

                            data.addRows([
                                [mes, "",parseFloat(a1), parseFloat(a3),
                                    parseFloat(a2), parseFloat(a6), parseFloat(a4)
                                ]
                            ]);
                        }

                        if ($('#opciones option:selected').val() === "PLANTA FPS")
                        {
                            var options = {
                                title: '',
                                vAxis: {title: GetTituloDPFEje(), titleTextStyle: {color: 'Black'}},
                                hAxis: {title: '*El valor "' + "Mayor historico" + '" corresponde a ' + ConvertirMes(m) + ' de ' + a + '\n'
                                            + '*El valor "' + "Menor historico" + '" corresponde a ' + ConvertirMes(mm) + ' de ' + aa
                                    , titleTextStyle: {color: 'Blue'}},
                                is3D: true,
                                colors: Coloresrrhh(),
                                annotations: {
                                    style: 'line'
                                },
                                series: {
                                    0: {pointShape: 'circle', pointSize: tamapunto()},
                                    2: {pointShape: 'circle', pointSize: tamapunto()}
                                  
                                }, lineWidth: tamlinea
                            };
                        }
                        else
                        {
                            var options = {
                                title: '',
                                vAxis: {title: GetTituloEje(), titleTextStyle: {color: 'Black'}},
                                hAxis: {title: '*El valor "' + "Mayor historico" + '" corresponde a ' + ConvertirMes(m) + ' de ' + a + '\n'
                                            + '*El valor "' + "Menor historico" + '" corresponde a ' + ConvertirMes(mm) + ' de ' + aa

                                    , titleTextStyle: {color: 'Blue'}},
                                is3D: true,
                                colors: Coloresrrhh(),
                                annotations: {
                                    style: 'line'
                                },
                                 series: {
                                    0: {pointShape: 'circle', pointSize: tamapunto()},
                                    2: {pointShape: 'circle', pointSize: tamapunto()}
                                  
                                }
                                , lineWidth: tamlinea
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
                    }
                },
                error: function () {
                    alert('No existen datos para los parametros elegidos');
                    document.getElementById("mes").value = 2; //MesActual();
                    location.reload();
                }
            });
}

function click_grafica(planta)
{
    if (planta === "PLANTA RST") {
        document.getElementById('opciones').value = "PLANTA RST";
    }
    else if (planta === "PLANTA RLRS") {
        document.getElementById('opciones').value = "PLANTA RLRS";
    }
    else if (planta === "PLANTA RSM") {
        document.getElementById('opciones').value = "PLANTA RSM O&M";
    }
     else if (planta === "PLANTA KNIT") {
        document.getElementById('opciones').value = "PLANTA KNIT";
    }
    else if (planta === "PLANTA DPF") {
        document.getElementById('opciones').value = "PLANTA DPF";
    }
    else if (planta === "PLANTA FPS") {
        document.getElementById('opciones').value = "PLANTA FPS";
    }
    DibujarChartPrincipal();
    hideMes();
}
