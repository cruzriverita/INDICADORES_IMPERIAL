/* global google */

function DibujarChartPrincipal() {
    $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "I_002_Kg_Producidos_Kwh_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs.         
                data: {
                    mesjs: $("#mes").val(), aniojs: $("#anio").val(),
                    opcion: $('#opciones option:selected').val()
                },
                dataType: "json", //Se reciben los datos en formato JSON 
                
                success: function (data_) 
                {
                 if ($('#opciones option:selected').val() === "ALL")
                 {
                    queryObject = eval('(' + JSON.stringify(data_) + ')');
                        queryObjectLen = queryObject.ListaValores.length;
                        var data = new google.visualization.DataTable();
                        /*convertir #anio a entero*/
                        var x = parseInt($("#anio").val(), 10);

                        data.addColumn('string', 'Planta');                         //Planta
                        //data.addColumn('number', 'Acumulado ' + String(x - 1));   //Acumulado año anterior
                        data.addColumn('number', x - 1);                            //Año anterior
                        data.addColumn('number', 'mejor');                          //Mejor
                        data.addColumn('number', $("#anio").val());                 //Año actual
                        //data.addColumn('number', 'Acumulado ' + $("#anio").val());//Acumulado año actual 
                        data.addColumn('number', 'promedio');                       //Promedio

                        for (var i = 0; i < queryObjectLen; i++)
                        {
                            var planta = queryObject.ListaValores[i].planta;
                            var a1 = queryObject.ListaValores[i].valor1;
                            var a2 = queryObject.ListaValores[i].valor2;
                            var a3 = queryObject.ListaValores[i].valor3;
                            // var a4 = queryObject.ListaValores[i].valor4;
                            //var a5 = queryObject.ListaValores[i].valor5;
                            var a6 = queryObject.ListaValores[i].valor6;

                            data.addRows([
                                [planta, /*parseFloat(a4)*/ parseFloat(a1), parseFloat(a3),
                                    parseFloat(a2), /*parseFloat(a5)*/parseFloat(a6)
                                ]
                            ]);
                        }

                        var view = new google.visualization.DataView(data);
                        view.setColumns([0, 1,
                            2, 3,4
                        ]);

                        var options = {
                            title: 'Kilos Producidos/Kilovatio-Hora ' + ConvertirMes($("#mes").val())+' '+$("#anio").val() ,
                            vAxis: {title: 'Kilogramos', titleTextStyle: {color: 'Black'}},
                            is3D: true,
                            colors: Colores(),//['#e0440e', '#e6693e', '#ec8f6e', '#f3b49f', '#f6c7b6'],
                            annotations: {
                                textStyle: {
                                    //fontName: 'Times-Roman',
                                    fontSize: 10,
                                    // bold: true,
                                    // italic: true,
                                    //color: '#fff',// The color of the text.
                                    auraColor: 'transparent' // The color of the text outline.
                                    //opacity: 0.8 // The transparency of the text.
                                }
                            }
                        };

                        var chart = new google.visualization.ColumnChart(document.getElementById('GraficaPrincipal'));
                        function ClickBarra() {
                            var selectedItem = chart.getSelection()[0];
                            if (selectedItem) {
                                //location.href = "I_000_Produccion_Por_Planta_Commodity.jsp?planta=" + data.getValue(selectedItem.row, 0) + "&anio=" + $("#anio").val() + "&mes=" + $("#mes").val();
                            }
                        }
                        google.visualization.events.addListener(chart, 'select', ClickBarra);
                        chart.draw(data, options);

                }
                
                else
                {
                     queryObject = eval('(' + JSON.stringify(data_) + ')');
                        queryObjectLen = queryObject.ListaValores.length;
                        var data = new google.visualization.DataTable();
                        //convertir #anio a entero
                        var x = parseInt($("#anio").val(), 10);

                        data.addColumn('string', 'planta');
                        data.addColumn('number', x - 1);
                        data.addColumn('number', 'mejor');
                        data.addColumn('number', $("#anio").val());
                        //data.addColumn({type:'string', role:'annotation'});
                        data.addColumn('number', 'Promedio');
                        //data.addColumn({type:'string', role:'annotation'});

                        for (var i = 0; i < queryObjectLen; i++)
                        {
                            var planta = queryObject.ListaValores[i].planta;
                            var a1 = queryObject.ListaValores[i].valor1;
                            var a2 = queryObject.ListaValores[i].valor2;
                            var a3 = queryObject.ListaValores[i].valor3;
                            var a6 = queryObject.ListaValores[i].valor6;
                            //var c=vector[i];

                            data.addRows([
                                [planta, parseFloat(a1), /*String(a1),*/ parseFloat(a3),
                                    parseFloat(a2), /*String(a2),*/parseFloat(a6)
                                ]
                            ]);
                        }
                        
                        if ($('#opciones option:selected').val() === "PLANTA FPS")
                        {
                            var options = {
                                title: 'Docenas Producidas/Kilovatio-Hora '+$("#anio").val()+' '+ $('#opciones option:selected').val(),
                                vAxis: {title: 'Docenas', titleTextStyle: {color: 'Black'}},
                                is3D: true,
                                colors: Colores(),
                                annotations: {
                                    textStyle: {
                                        fontSize: 10,
                                        auraColor: 'transparent' // The color of the text outline.
                                    }
                                }
                            };
                        }
                        else
                        {
                            var options = {
                         
                                title: 'Kilos Producidos/Kilovatio-Hora '+$("#anio").val()+' '+ $('#opciones option:selected').val(),
                                vAxis: {title: 'Kilogramos', titleTextStyle: {color: 'Black'}},
                                is3D: true,
                                colors: Colores(),
                                annotations: {
                                    textStyle: {
                                        //fontName: 'Times-Roman',
                                        fontSize: 10,
                                        // bold: true,
                                        // italic: true,
                                        //color: '#fff',// The color of the text.
                                        auraColor: 'transparent' // The color of the text outline.
                                                //opacity: 0.8 // The transparency of the text.
                                    }
                                }
                            };
                        }
                        
                        
                        
                        var chart = new google.visualization.LineChart(document.getElementById('GraficaPrincipal'));


                        function ClickBarra() {
                            var selectedItem = chart.getSelection()[0];
                            if (selectedItem) {
                                //alert("fsdf");
                                //location.href = "I_000_Produccion_Por_Planta_Commodity.jsp?planta=" + data.getValue(selectedItem.row, 0) + "&anio=" + $("#anio").val() + "&mes=" + $("#mes").val();
                            }
                        }
                        google.visualization.events.addListener(chart, 'select', ClickBarra);
                        chart.draw(data, options);
                    
                }

                },
                error: function () {

                    alert('No existen datos para el mes' + $("#mes").val());
                    document.getElementById("mes").value = 2; //MesActual();
                    location.reload();
                }        
            });
}