/* global google */

function DibujarChartPrincipal() {
    $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "General_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs,opcion.         
                data: {
                    mesjs: $("#mes").val(),
                    aniojs: $("#anio").val(),
                    opcion: $('#opciones option:selected').val(),
                    indicador: $('#indicador').val()
                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data_) {

                    //Si se elige una de estas dos opciones entonces se muestra la grafica de barras, se separa DPF (trabaja con docenas y no KG)
                    if ($('#opciones option:selected').val() === "ALL" || $('#opciones option:selected').val() === "FPS MES")
                    {
                        queryObject = eval('(' + JSON.stringify(data_) + ')');
                        queryObjectLen = queryObject.ListaValores.length;
                        var data = new google.visualization.DataTable();
                        /*convertir #anio a entero*/
                        var x = parseInt($("#anio").val(), 10);

                        data.addColumn('string', 'Planta');                         //Planta
                        //data.addColumn('number', 'Acumulado ' + String(x - 1));   //Acumulado año anterior
                        data.addColumn('number', x - 1);                            //Año anterior
                        data.addColumn('number', MayorMenor());                          //Mejor
                        data.addColumn('number', $("#anio").val());                 //Año actual
                        //data.addColumn('number', 'Acumulado ' + $("#anio").val());//Acumulado año actual 
                        data.addColumn('number', 'Promedio ' + (x - 1));                       //Promedio

                        for (var i = 0; i < queryObjectLen; i++)
                        {
                            var planta = queryObject.ListaValores[i].planta;
                            var a1 = queryObject.ListaValores[i].valor1;
                            var a2 = queryObject.ListaValores[i].valor2;
                            var a3 = queryObject.ListaValores[i].valor3;
                            //var a4 = queryObject.ListaValores[i].valor4;
                            //var a5 = queryObject.ListaValores[i].valor5;
                            var a6 = queryObject.ListaValores[i].valor6;

                            data.addRows([
                                [planta, /*parseFloat(a4)*/ parseFloat(a1), parseFloat(a3),
                                    parseFloat(a2), /*parseFloat(a5)*/parseFloat(a6)
                                ]
                            ]);
                        }

                  

                   if ($('#opciones option:selected').val() === "ALL") {
                                var options = {
                                    title: GetTitulo() + ConvertirMes($("#mes").val()) + ' ' + $("#anio").val(),
                                    vAxis: {title: GetTituloEje(), titleTextStyle: {color: 'Black'}},
                                    is3D: true,
                                    colors: Colores(),
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
                            else
                            {
                                var options = {
                                    title: GetTituloDPF() + ConvertirMes($("#mes").val()) + ' ' + $("#anio").val(),
                                    vAxis: {title:GetTituloDPFEje() , titleTextStyle: {color: 'Black'}},
                                    is3D: true,
                                    colors: Colores()
                                };

                            }

                        var chart = new google.visualization.ColumnChart(document.getElementById('GraficaPrincipal'));

                        //funcion que se ejecuta al dar click sobre la barra
                        function ClickBarra() {
                            var selectedItem = chart.getSelection()[0];
                            if (selectedItem) {

                            }
                        }
                        google.visualization.events.addListener(chart, 'select', ClickBarra);
                        chart.draw(data, options);

                    }


                    //Si en el Select se elige una planta especifica se muestra por medio de grafica lineal
                    else
                    {
                        queryObject = eval('(' + JSON.stringify(data_) + ')');
                        queryObjectLen = queryObject.ListaValores.length;
                        var data = new google.visualization.DataTable();

                        var x = parseInt($("#anio").val(), 10);
                        data.addColumn('string', 'planta');
                        data.addColumn('number', x - 1);
                        data.addColumn('number', MayorMenor());
                        data.addColumn('number', $("#anio").val());
                        //data.addColumn({type:'string', role:'annotation'});
                        data.addColumn('number', 'Promedio ' + (x - 1));
                        //data.addColumn({type:'string', role:'annotation'});

                        for (var i = 0; i < queryObjectLen; i++)
                        {
                            var planta = queryObject.ListaValores[i].planta;
                            var a1 = queryObject.ListaValores[i].valor1;
                            var a2 = queryObject.ListaValores[i].valor2;
                            var a3 = queryObject.ListaValores[i].valor3;
                            var a6 = queryObject.ListaValores[i].valor6;
                            var m = queryObject.ListaValores[i].mejormes;
                            var a = queryObject.ListaValores[i].mejoranio;

                            data.addRows([
                                [planta, parseFloat(a1), /*String(a1),*/ parseFloat(a3),
                                    parseFloat(a2), /*String(a2),*/parseFloat(a6)
                                ]
                            ]);
                        }

                        if ($('#opciones option:selected').val() === "PLANTA FPS")
                        {
                            var options = {
                                title: GetTituloDPF() + $("#anio").val() + ' ' + $('#opciones option:selected').val(),
                                vAxis: {title: GetTituloDPFEje(), titleTextStyle: {color: 'Black'}},
                                hAxis: {title: '*El valor '+MayorMenor()+' corresponde a ' + ConvertirMes(m) + ' de ' + a, titleTextStyle: {color: 'Blue'}},
                                is3D: true,
                                colors: Colores()
                            };
                        }
                        else
                        {
                            var options = {
                                title: GetTitulo() + $("#anio").val() + ' ' + $('#opciones option:selected').val(),
                                vAxis: {title: GetTituloEje(), titleTextStyle: {color: 'Black'}},
                                hAxis: {title: '*El valor '+ MayorMenor()+' corresponde a ' + ConvertirMes(m) + ' de ' + a, titleTextStyle: {color: 'Blue'}},
                                is3D: true,
                                colors: Colores()
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



