/* global google */

function DibujarChartPrincipal() {
    $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "I_003_Indicadores_Inventarios_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs.         
                data: {
                    aniojs: $("#anio").val(),
                    tipojs: $('#tipo option:selected').val(),
                    artjs: $('#tipo2 option:selected').val(),
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
                    data.addColumn('number', 'promedio');
                    data.addColumn('number', 'Menor historico');
                    


                    for (var i = 0; i < queryObjectLen; i++)
                    {
                        var periodo = queryObject.ListaValores[i].periodo;
                        
                        periodo = ConvertirMes(queryObject.ListaValores[i].periodo);
                       
                        var a1 = queryObject.ListaValores[i].valor1;
                        var a2 = queryObject.ListaValores[i].valor2;
                        var mayor = queryObject.ListaValores[i].valor3;
                        var mayormes = queryObject.ListaValores[i].valor4;
                        var mayoranio = queryObject.ListaValores[i].valor5;
                        var menor = queryObject.ListaValores[i].valor6;
                        var menormes = queryObject.ListaValores[i].valor7;
                        var menoranio = queryObject.ListaValores[i].valor8;
                        var promedio = queryObject.ListaValores[i].valor9;
           
                        
                        a1 = DevolverNull(a1);
                        a2 = DevolverNull(a2);

                        data.addRows([
                            [periodo, "", parseFloat(a1), parseFloat(mayor),
                               parseFloat(a2), parseFloat(promedio), parseFloat(menor)
                                
                                
                            ]
                        ]);
                    }


                    if ($('#tipo option:selected').val() === "1") //Si es indice
                    {

                        var options = {
                            title: '', //Cantidad de empleados Nomina '+ x,
                            vAxis: {title: 'Indice del mes', titleTextStyle: {color: 'Black'}, gridlines: {count: 20}, viewWindow: {
                                    min: (menor - 0.1),
                                    max: (mayor + 0.1)
                                }},
                            hAxis: {title: '*El valor "Menor historico" corresponde a ' + ConvertirMes(menormes) + ' de ' + menoranio + '\n' +
                                        '*El valor "Mayor historico" corresponde a ' + ConvertirMes(mayormes) + ' de ' + mayoranio, titleTextStyle: {color: 'Blue'}},
                            is3D: true,
                            colors: Coloresrrhh(),
                            annotations: {
                                style: 'line'
                            },
                            series: {
                                 0: {pointShape: 'circle', pointSize: tamapunto()},
                                 2: {pointShape: 'circle', pointSize: tamapunto()}
               
                            },
                            lineWidth: tamlinea,
                            
                           
                        }; 


                    }
                    /*-----------------------------------Si es dias --------------------------------*/
                    else
                    {

                        var options = {
                            title: '', //'Cantidad de empleados Planilla '+ x,
                            vAxis: {title: 'Dias de inventario', titleTextStyle: {color: 'Black'}, gridlines: {count: 20}, viewWindow: {
                                    min: (menor - 10),
                                    max: (mayor + 10)
                                }},
                            hAxis: {title: '*El valor "Menor historico" corresponde a ' + ConvertirMes(menormes) + ' de ' + menoranio + '\n' +
                                        '*El valor "Mayor historico" corresponde a ' + ConvertirMes(mayormes) + ' de ' + mayoranio, titleTextStyle: {color: 'Blue'}},
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



                    var chart = new google.visualization.ComboChart(document.getElementById('GraficaPrincipal'));


                    function ClickBarra() {
                        var selectedItem = chart.getSelection()[0];
                        if (selectedItem) {
                             var planta = data.getValue(selectedItem.row, 0);
                             var anio = 2015 //data.getValue(selectedItem.row, 2);
                           
                             if (planta==="Ene"){
                            DibujarChartPrincipal2("1",anio);
                            
                            ShowDef2();}
                            else if (planta==="Feb"){
                            
                                DibujarChartPrincipal2("2",anio);
                            
                            ShowDef2();
                            }
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


function GetTituloG3() {
    var t = document.getElementById("tipo");
    var val = t.options[t.selectedIndex].value;

    var t = document.getElementById("tipo2");
    var val2 = t.options[t.selectedIndex].value;

    if (val === "1") {

        document.getElementById('titulo').innerHTML = "Rotacion de Inventarios por Indice del mes - " + GetNombreArticulo(val2);
    }
    else
    {
        document.getElementById('titulo').innerHTML = "Rotacion de Inventarios por Dias de Inventario - " + GetNombreArticulo(val2);
    }
}

function GetSubTituloG3() {
    var anio = document.getElementById("anio").value;
    document.getElementById('subtitulo').innerHTML = anio;
}


function GetNombreArticulo(valor)
{
    if (valor === "1")
        return "Algodon";
    else if (valor === "2")
        return "Poliester Fibra";
    else if (valor === "3")
        return "Hilo Producido";
    else if (valor === "4")
        return "Hilo Comprado";
    else if (valor === "5")
        return "Tela Cruda";
    else if (valor === "6")
        return "Tela Producida";
}