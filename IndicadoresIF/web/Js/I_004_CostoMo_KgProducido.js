/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function DibujarChartPrincipal() {
    $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "I_004_CostoMo_KgProducido_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs.         
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
                    if($('#opciones option:selected').val()==="ALL")
                    {
                                 var view = new google.visualization.DataView(data);
                    view.setColumns([0, 1,
                       2, 3, 4, 5
                    ]); 
                    }
                    else
                    {
                            var view = new google.visualization.DataView(data);
                    view.setColumns([0, 1,
                        {calc: "stringify",
                            sourceColumn: 1,
                            type: "string",
                            role: "annotation"
                        },
                        2,
                        {calc: "stringify",
                            sourceColumn: 2,
                            type: "string",
                            role: "annotation"}, 3,{calc: "stringify",
                            sourceColumn: 3,
                            type: "string",
                            role: "annotation"}, 4,{calc: "stringify",
                            sourceColumn: 4,
                            type: "string",
                            role: "annotation"}, 5,{calc: "stringify",
                            sourceColumn: 5,
                            type: "string",
                            role: "annotation"}
                    ]);
                        
                    }
                 
            
                
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
                
                
                    var chart = new google.visualization.BarChart(document.getElementById('GraficaPrincipal'));


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


}
