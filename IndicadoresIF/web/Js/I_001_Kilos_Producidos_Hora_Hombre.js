/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
/* global google */

function DibujarChartPrincipal() {
    $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "I_001_Kilos_Producidos_Hora_Hombre_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs,opcion.         
                data: {
                    mesjs: $("#mes").val(),
                    aniojs: $("#anio").val(),
                    opcion: $('#opciones option:selected').val()
                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data_) {
                    queryObject = eval('(' + JSON.stringify(data_) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data = new google.visualization.DataTable();
                    //convertir #anio a entero
                    var x = parseInt($("#anio").val(), 10);

                    data.addColumn('string', 'Planta');                         //Planta
                    data.addColumn('number', 'Acumulado ' + String(x - 1));     //Acumulado año anterior
                    data.addColumn('number', x - 1);                            //Año anterior
                    data.addColumn('number', 'mejor');                          //Mejor
                    data.addColumn('number', $("#anio").val());                 //Año actual
                    data.addColumn('number', 'Acumulado ' + $("#anio").val());  //Acumulado año actual 
                   // data.addColumn({type: 'string', role: 'style'});
                    //var vector = Colores();
                    //var vector = ["#000000", "#FFFF00", "#33CCCC","#000000", "#FFFF00"];
                    for (var i = 0; i < queryObjectLen; i++)
                    {
                        var planta = queryObject.ListaValores[i].planta;
                        var a1 = queryObject.ListaValores[i].valor1;
                        var a2 = queryObject.ListaValores[i].valor2;
                        var a3 = queryObject.ListaValores[i].valor3;
                        var a4 = queryObject.ListaValores[i].valor4;
                        var a5 = queryObject.ListaValores[i].valor5;
                        //var c=vector[i];

                        data.addRows([
                            [planta, parseFloat(a4), parseFloat(a1), parseFloat(a3),
                                parseFloat(a2), parseFloat(a5)//,c
                            ]
                        ]);
                    }

                    
                    if ($('#opciones option:selected').val() === "ALL")
                    {
                        var view = new google.visualization.DataView(data);
                        view.setColumns([0, 1,
                            2, 3, 4, 5
                        ]);
                    }
                    else
                    {
                        //Asignar anotacion en las barras de la grafica.
                        var view = new google.visualization.DataView(data);
                        view.setColumns([0, 
                            1,
                            {calc: "stringify",
                                sourceColumn: 1,
                                type: "string",
                                role: "annotation"},
                            2, {calc: "stringify",
                                sourceColumn: 2,
                                type: "string",
                                role: "annotation"}, 
                            3, {calc: "stringify",
                                sourceColumn: 3,
                                type: "string",
                                role: "annotation"}, 
                            4, {calc: "stringify",
                                sourceColumn: 4,
                                type: "string",
                                role: "annotation"}, 
                            5, {calc: "stringify",
                                sourceColumn: 5,
                                type: "string",
                                role: "annotation"}
                        ]);

                    }

                    if ($('#opciones option:selected').val() === "PLANTA FPS")
                    {
                        var options = {
                           
                            title: 'Docenas Producidas/ Hora - Hombre Mes: ' + ConvertirMes($("#mes").val()),
                            hAxis: {title: 'Docenas', titleTextStyle: {color: 'Black'}},
                            is3D: true,
                            annotations: {
                                textStyle: {
                                    fontSize: 10,
                                    auraColor: 'transparent' // The color of the text outline.
                                }
                            }
                        };

                    }
                    else {
                        var options = {
                         
                            title: 'Kilos Producidos/ Hora - Hombre Mes: ' + ConvertirMes($("#mes").val()),
                            hAxis: {title: 'Kilos', titleTextStyle: {color: 'Black'}},
               
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
                    }


/* var options = {
          title: 'Company Performance',
          curveType: 'function',
          legend: { position: 'bottom' }
        };*/
                    var chart = new google.visualization.LineChart(document.getElementById('GraficaPrincipal'));


                    function ClickBarra() {
                        var selectedItem = chart.getSelection()[0];
                        if (selectedItem) {
                            //location.href = "I_000_Produccion_Por_Planta_Commodity.jsp?planta=" + data.getValue(selectedItem.row, 0) + "&anio=" + $("#anio").val() + "&mes=" + $("#mes").val();
                        }
                    }
                    google.visualization.events.addListener(chart, 'select', ClickBarra);
                    chart.draw(data, options);
                },
                error: function () {
                    alert('No existen datos para los parametros elegidos');
                    document.getElementById("mes").value = 2; //MesActual();
                    location.reload();
                },
                async: false
            });
}


