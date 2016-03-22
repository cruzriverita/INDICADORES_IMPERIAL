/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global google */

function DibujarChartPrincipal() {

    $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "I_000_Produccion_Por_Planta_Mes_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs.         
                data: {
                    aniojs: $("#anio").val(),
                    mesjs: $("#mes").val()
                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (dataS) {
                    queryObject = eval('(' + JSON.stringify(dataS) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data = new google.visualization.DataTable();

                    data.addColumn('string', 'planta');
                    data.addColumn('number', '');

                    //Columna adicional para dar color a la grafica.
                    data.addColumn({type: 'string', role: 'style'});

                    for (var i = 0; i < queryObjectLen; i++)
                    {
                        var name = queryObject.ListaValores[i].planta;
                        var empid = queryObject.ListaValores[i].valor;
                        var Color = GenerarColorRandom();


                        data.addRows([
                            [name, parseFloat(empid), Color]
                        ]);
                    }
                    var options = {
                        title: 'Produccion Por Planta Mes De ' + ConvertirMes($("#mes").val()),
                        hAxis: {title: 'Plantas', titleTextStyle: {color: 'Black'}},
                        is3D: true
                    };

                    var chart = new google.visualization.ColumnChart(document.getElementById('GraficaPrincipal'));

                    function ClickBarra() {
                        var selectedItem = chart.getSelection()[0];
                        if (selectedItem) {

                            location.href = "I_000_Produccion_Por_Planta_Commodity.jsp?planta=" + data.getValue(selectedItem.row, 0) + "&anio=" + $("#anio").val() + "&mes=" + $("#mes").val();
                        }
                    }
                    google.visualization.events.addListener(chart, 'select', ClickBarra);
                    chart.draw(data, options);

                },
                error: function () {

                    alert('No existen datos para el año ' + $("#anio").val() + ' y el mes' + $("#mes").val());
                    document.getElementById("mes").value = MesActual();
                    document.getElementById("anio").value = AnioActual();
                    location.reload();

                },
                async: false
            });
            /*
             $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "I_001_CostoKWH_KgProducido_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs.         
                data: {
                    mesjs: 2 , aniojs: $("#anio").val()
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
                        title: 'Costo KWH/Kg producido - ' + ConvertirMes($("#mes").val()),
                        vAxis: {title: 'Kilogramos', titleTextStyle: {color: 'Black'}},
                        is3D: true

                    };
                    var chart = new google.visualization.ColumnChart(document.getElementById('GraficaPrincipal1'));

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
                   // location.reload();
                },
                async: false
            });
            
            
             $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "I_004_CostoMo_KgProducido_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs.         
                data: {
                    mesjs: 2,
                    aniojs: $("#anio").val(),
                    opcion: "ALL"
                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data_) {
                    queryObject = eval('(' + JSON.stringify(data_) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data = new google.visualization.DataTable();
                    var x = parseInt($("#anio").val(), 10);

                    data.addColumn('string', 'Planta');
                    //data.addColumn('number', '2015');
                    data.addColumn('number', 'Acumulado ' + String(x - 1));
                    data.addColumn('number', x - 1);
                    
                    data.addColumn('number', 'mejor');
                    data.addColumn('number', $("#anio").val());
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
                            [planta, parseFloat(a4), parseFloat(a1), parseFloat(a3),
                                parseFloat(a2), parseFloat(a5)
                            ]
                        ]);
                    }

                    //Asignar anotacion en las barras de la grafica.
                  
                                 var view = new google.visualization.DataView(data);
                    view.setColumns([0, 1,
                       2, 3, 4, 5
                    ]); 

                        var options = {
                        title: 'Costo Mano Obra/Kg Producido: ' + ConvertirMes($("#mes").val()),
                        hAxis: {title: 'Dolares', titleTextStyle: {color: 'Black'}},
                        is3D: true,
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
                
                
                    var chart = new google.visualization.BarChart(document.getElementById('GraficaPrincipal2'));


                    function ClickBarra() {
                        var selectedItem = chart.getSelection()[0];
                        if (selectedItem) {

                            //location.href = "I_000_Produccion_Por_Planta_Commodity.jsp?planta=" + data.getValue(selectedItem.row, 0) + "&anio=" + $("#anio").val() + "&mes=" + $("#mes").val();
                        }
                    }
                    google.visualization.events.addListener(chart, 'select', ClickBarra);
                    chart.draw(view, options);

                },
                error: function () {

                    alert('No existen datos para el mes' + $("#mes").val());
                    document.getElementById("mes").value = 2; //MesActual();
                    location.reload();

                },
                async: false
            });

            */
}

/*---------------------------------------------------------------------------------------------------*/

function DibujarChartDetalle() {
    $.ajax
            ({
                type: "POST",
                url: "I_000_Produccion_Por_Planta_Commodity_Servlet",
                data: {planta: getParameterByName("planta"), anio: getParameterByName("anio"), mes: getParameterByName("mes")},
                dataType: "json",
                success: function (data3) {
                    queryObject = eval('(' + JSON.stringify(data3) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data2 = new google.visualization.DataTable();

                    data2.addColumn('string', 'commodity');
                    data2.addColumn('number', 'val');

                    for (var i = 0; i < queryObjectLen; i++)
                    {
                        var commodity = queryObject.ListaValores[i].commodity;
                        var valor = queryObject.ListaValores[i].valor;

                        data2.addRows([
                            [commodity, parseInt(valor)]
                        ]);
                    }
                    var options = {
                        title: 'Planta ' + getParameterByName("planta"),
                        is3D: true
                    };
                    var chart = new google.visualization.PieChart(document.getElementById('piechart_detalle'));

                    function selectHandler() {
                        var selectedItem = chart.getSelection()[0];
                        if (selectedItem) {

                            var topping = data2.getValue(selectedItem.row, 0);
                            var val = data2.getValue(selectedItem.row, 1)
                            alert(topping + " " + val);
                        }
                    }
                    google.visualization.events.addListener(chart, 'select', selectHandler);
                    chart.draw(data2, options);
                },
                error: function (xhr, type) {

                    alert('server error occoured ' + xhr.toString() + ' ' + type.toString());
                },
                async: false
            });
}

