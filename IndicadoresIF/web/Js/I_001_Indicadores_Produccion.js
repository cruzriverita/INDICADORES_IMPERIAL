/* global google */

function DibujarChartPrincipal() {
    $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "I_001_Indicadores_Produccion_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs,opcion.         
                data: {
                    mesjs: $("#mes").val(),
                    aniojs: $("#anio").val(),
                    opcion: $('#opciones option:selected').val(), //trae la planta seleccionada.
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

                        data.addColumn('string', 'Planta');                                  //Planta
                        data.addColumn('number', ConvertirMes($("#mes").val())+' '+(x - 1)); //2015/2016                           //Año anterior
                        data.addColumn('number', MayorMenor());                              //Mejor
                        data.addColumn('number', ConvertirMes($("#mes").val())+' '+$("#anio").val());                 //Año actual
                       
                        data.addColumn('number', 'Promedio ' + (x - 1));                     //Promedio


                        for (var i = 0; i < queryObjectLen; i++)
                        {
                            var planta = queryObject.ListaValores[i].planta;
                            
                            //Colocar en el titulo "RSM" en lugar de "RSM O&M"                      
                           if (planta==="PLANTA RSM O&M" || planta==="PLANTA MRS O&M" )
                                planta = "PLANTA RSM";

                            var a1 = queryObject.ListaValores[i].valor1;
                            var a2 = queryObject.ListaValores[i].valor2;
                            var a3 = queryObject.ListaValores[i].valor3;
                            var a6 = queryObject.ListaValores[i].valor6;

                           

                            data.addRows([
                                [planta, /*parseFloat(a4)*/ parseFloat(a1), parseFloat(a3),
                                    parseFloat(a2), /*parseFloat(a5)*/parseFloat(a6)
                                ]
                            ]);
                        }

                        //Si se eligen todas las plantas
                   if ($('#opciones option:selected').val() === "ALL") {
                                var options = {
                                    title:'',
                                    vAxis: {title: GetTituloEje()+'\n\n', 
                                    titleTextStyle: {color: 'Black'}},
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
                            //Si se selecciona FPS
                            else
                            {
                                var options = {
                                    title: '',//GetTituloDPF() + ConvertirMes($("#mes").val()) + ' ' + $("#anio").val(),
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
                        //Colocar en el titulo "RSM" en lugar de "RSM O&M"
                        var planta = $('#opciones option:selected').val();
                        if (planta==="PLANTA RSM O&M" || planta==="PLANTA MRS O&M" )
                            planta="PLANTA RSM";
                        
                        queryObject = eval('(' + JSON.stringify(data_) + ')');
                        queryObjectLen = queryObject.ListaValores.length;
                        var data = new google.visualization.DataTable();

                        var x = parseInt($("#anio").val(), 10);
                        data.addColumn('string', 'mes');
                        data.addColumn({type: 'string', role: 'annotation'});
                        data.addColumn('number', (x-1));
                        data.addColumn('number', MayorMenor());
                        data.addColumn('number', x);
                        //data.addColumn({type:'string', role:'annotation'});
                        data.addColumn('number', 'Promedio ' + (x - 1));
                        //data.addColumn({type:'string', role:'annotation'});

                        for (var i = 0; i < queryObjectLen; i++)
                        {
                            var mes = queryObject.ListaValores[i].mes;
                            var a1 = queryObject.ListaValores[i].valor1;
                              var a2 = queryObject.ListaValores[i].valor2;
                            
                            if (mes==="Dic" && a1!==0 && a2===0)
                            {
                                a2=a1;
                            }
                            
                            if (a1===0)
                            {
                                a1=null;
                            }
                            
                            
                          
                            if (a2===0)
                            {
                                a2=null;
                            }
                            var a3 = queryObject.ListaValores[i].valor3;
                            var a6 = queryObject.ListaValores[i].valor6;
                            var m = queryObject.ListaValores[i].mejormes;
                            var a = queryObject.ListaValores[i].mejoranio;

                            data.addRows([
                                [mes,".", parseFloat(a1), /*String(a1),*/ parseFloat(a3),
                                    parseFloat(a2), /*String(a2),*/parseFloat(a6)
                                ]
                            ]);
                        }

                        if ($('#opciones option:selected').val() === "PLANTA FPS")
                        {
                            var options = {
                                title: '',//GetTituloDPF() + $("#anio").val() + ' ' + $('#opciones option:selected').val(),
                                vAxis: {title: GetTituloDPFEje(), titleTextStyle: {color: 'Black'}},
                                hAxis: {title: '*El valor "'+MayorMenor()+'" corresponde a ' + ConvertirMes(m) + ' de ' + a, titleTextStyle: {color: 'Blue'}},
                                is3D: true,
                                colors: Colores(),
                                 annotations: {
                                 style: 'line'
                                    }, 
                                     series: {
                0: { pointShape: 'circle', pointSize: 5},
               
                2: { pointShape: 'square',pointSize: 5 },
                3: { pointShape: 'none' },
                4: { pointShape: '' },
                5: { pointShape: 'polygon' }
            }
                            };
                        }
                        else
                        {
                            var options = {
                                title: '',//GetTitulo() + $("#anio").val() + ' ' + planta,
                                vAxis: {title: GetTituloEje(), titleTextStyle: {color: 'Black'}},
                                hAxis: {title: '*El valor "'+ MayorMenor()+'" corresponde a ' + ConvertirMes(m) + ' de ' + a, titleTextStyle: {color: 'Blue'}},
                                is3D: true,
                                colors: Colores(),
                                 annotations: {
                                 style: 'line'
                                    }
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



