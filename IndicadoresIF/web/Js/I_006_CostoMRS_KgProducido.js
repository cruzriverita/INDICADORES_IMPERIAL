/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//* global google */

/* global google */

function DibujarChartPrincipal() {
    $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "I_006_CostoMRS_KgProducido_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs.         
                data: {
                    mesjs: $("#mes").val(), aniojs: $("#anio").val(),
                    opcion: $('#opciones option:selected').val()
                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data_) {
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
                        data.addColumn('number', 'mejor');                          //Mejor
                        data.addColumn('number', $("#anio").val());                 //Año actual
                        //data.addColumn('number', 'Acumulado ' + $("#anio").val());//Acumulado año actual 
                        data.addColumn('number', 'promedio');                       //Promedio

                        for (var i = 0; i < queryObjectLen; i++)
                        {
                            var planta = queryObject.ListaValores[i].planta;
                            
                            if (planta==="PLANTA LRS")
                            {
                                planta="PLANTA RLRS";
                            }
                            else if (planta==="PLANTA MRS O&M")
                            {
                                planta="PLANTA RSM O&M";
                            }
                            
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

                        var options = {
                            title: 'Costo MRS/Kg Producidos ' + ConvertirMes($("#mes").val()) + ' ' + $("#anio").val(),
                            vAxis: {title: 'Dolares', titleTextStyle: {color: 'Black'}},
                            is3D: true,
                            colors: Colores()
                        };

                        var chart = new google.visualization.ColumnChart(document.getElementById('GraficaPrincipal'));
                        function ClickBarra() {
                            var selectedItem = chart.getSelection()[0];
                            if (selectedItem) {
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

                        data.addColumn('string', 'mes');
                        data.addColumn('number', x - 1);
                        data.addColumn('number', 'mejor');
                        data.addColumn('number', $("#anio").val());
                        //data.addColumn({type:'string', role:'annotation'});
                        data.addColumn('number', 'Promedio');
                        //data.addColumn({type:'string', role:'annotation'});

                        for (var i = 0; i < queryObjectLen; i++)
                        {
                            var planta = queryObject.ListaValores[i].mes;
                            var a1 = queryObject.ListaValores[i].valor1;
                            var a2 = queryObject.ListaValores[i].valor2;
                            var a3 = queryObject.ListaValores[i].valor3;
                            var a6 = queryObject.ListaValores[i].valor6;
                            var m=queryObject.ListaValores[i].mejormes;
                            var a=queryObject.ListaValores[i].mejoranio;
                          
                            data.addRows([
                                [planta, parseFloat(a1), /*String(a1),*/ parseFloat(a3),
                                    parseFloat(a2), /*String(a2),*/parseFloat(a6)
                                ]
                            ]);
                        }

                        if ($('#opciones option:selected').val() === "PLANTA FPS")
                        {
                            var options = {
                                title: 'Costo MRS/Docenas Producidas ' + $("#anio").val() + ' ' + $('#opciones option:selected').val(),
                                vAxis: {title: 'Dolares', titleTextStyle: {color: 'Black'}},
                                hAxis: {title: '*El valor menor corresponde a '+ConvertirMes(m) +' de '+ a, titleTextStyle: {color: 'Blue'}},
                                is3D: true,
                                colors: Colores()
                            };
                        }
                        else
                        {
                            var options = {
                                title: 'Costo MRS/Kg Producidos ' + $("#anio").val() + ' ' + $('#opciones option:selected').val(),
                                vAxis: {title: 'Dolares', titleTextStyle: {color: 'Black'}},
                                 hAxis: {title: '*El valor menor corresponde a '+ConvertirMes(m) +' de '+ a, titleTextStyle: {color: 'Blue'}},
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

                    alert('No existen datos para el mes' + $("#mes").val());
                    document.getElementById("mes").value = 2; //MesActual();
                    location.reload();
                }
           
            });
}
