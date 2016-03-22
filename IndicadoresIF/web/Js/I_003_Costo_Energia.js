/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global google */

function ChartCostoEnergiaQ() {

    $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "I_003_Costo_Energia_Q_Servlet",
                data: {
                    aniojs: $("#anio").val(),
                    opcion: $('#opciones option:selected').val()
                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data) {
                    queryObject = eval('(' + JSON.stringify(data) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                },
                error: function () {
                    alert('No existen datos para el año ' + $("#anio").val());
                    document.getElementById("anio").value = AnioActual();
                    //location.reload();
                },
                async: false
            });

    var data = new google.visualization.DataTable();

    data.addColumn('string', 'Planta');
    data.addColumn('number', 'Enero');
    data.addColumn('number', 'Febrero');
    data.addColumn('number', 'Marzo');
    data.addColumn('number', 'Abril');
    data.addColumn('number', 'Mayo');
    data.addColumn('number', 'Junio');
    data.addColumn('number', 'Julio');
    data.addColumn('number', 'Agosto');
    data.addColumn('number', 'Septiembre');
    data.addColumn('number', 'Octubre');
    data.addColumn('number', 'Noviembre');
    data.addColumn('number', 'Diciembre');


    for (var i = 0; i < queryObjectLen; i++)
    {
        var planta = queryObject.ListaValores[i].planta;
        var enero = queryObject.ListaValores[i].valor1;
        var febrero = queryObject.ListaValores[i].valor2;
        var marzo = queryObject.ListaValores[i].valor3;
        var abril = queryObject.ListaValores[i].valor4;
        var mayo = queryObject.ListaValores[i].valor5;
        var junio = queryObject.ListaValores[i].valor6;
        var julio = queryObject.ListaValores[i].valor7;
        var agosto = queryObject.ListaValores[i].valor8;
        var sept = queryObject.ListaValores[i].valor9;
        var oct = queryObject.ListaValores[i].valor10;
        var nov = queryObject.ListaValores[i].valor11;
        var dic = queryObject.ListaValores[i].valor12;

        data.addRows([
            [planta, parseFloat(enero), parseFloat(febrero),parseFloat(marzo),
            parseFloat(abril), parseFloat(mayo),parseFloat(junio),
            parseFloat(julio), parseFloat(agosto),parseFloat(sept),
            parseFloat(oct), parseFloat(nov),parseFloat(dic)
            ]
        ]);
    }

    var options = {
        chart: {
            title: 'Costo Energia',
            subtitle: set_subtitulo($('#opciones option:selected').val())//'Costo de energia en Quetzales'
        },
        bars: 'horizontal', // Required for Material Bar Charts.

        series: {
            0: {axis: 'EjeX'}
        },
        axes: {
            x: {
                EjeX: {side: 'top', label: set_subtitulo_haxis($('#opciones option:selected').val())}
            }
        },
        hAxis: {format: 'decimal'
                    /*,viewWindow: {
                     min: 0,
                     max: 3000000
                     }*/
                    //,ticks: [0, 50000, 10000, 150000, 200000]
        }
        //colors: ['#1b9e77', '#d95f02', '#7570b3']
    };

    //var chart = new google.visualization.Bar(document.getElementById('GraficaPrincipal'));
    var chart = new google.charts.Bar(document.getElementById('GraficaPrincipal'));


    function ClickBarra() {
        var selectedItem = chart.getSelection()[0];
        if (selectedItem) {
            var planta = data.getValue(selectedItem.row, 0);
            var cant = data.getValue(selectedItem.row, 1);
            var mes = selectedItem.column;
            //alert(planta + " " + cant + " " + mes);
            location.href = "I_003_Costo_Energia_Detalle.jsp?planta=" + planta + "&anio=" + $("#anio").val() + "&mes=" + mes;
        }
    }

  

    google.visualization.events.addListener(chart, 'select', ClickBarra);
    google.visualization.events.addListener(chart, 'error', errorHandler);
    //chart.draw(data, options);
    chart.draw(data, google.charts.Bar.convertOptions(options));
}

function CostoEnergiaDetalle() {
    $.ajax
            ({
                type: "POST",
                url: "I_003_Costo_Energia_Detalle_Servlet",
                data: {planta: getParameterByName("planta"), anio: getParameterByName("anio"), mes: getParameterByName("mes")},
                dataType: "json",
                success: function (data2) {
                    queryObject = eval('(' + JSON.stringify(data2) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data = new google.visualization.DataTable();

                    data.addColumn('string', 'planta');
                    data.addColumn('number', 'valor');

                    for (var i = 0; i < queryObjectLen; i++)
                    {
                        var planta = queryObject.ListaValores[i].planta;
                        var valor = queryObject.ListaValores[i].valor;

                        data.addRows([
                            [planta, parseFloat(valor)]
                        ]);
                    }
                    var options = {
                        title: getParameterByName("planta")+'   ' + ConvertirMes(getParameterByName("mes"))+'   '+getParameterByName("anio"),
                       
                        is3D: true
                    };
                    var chart = new google.visualization.PieChart(document.getElementById('piechart_detalle'));

                    function selectHandler() {
                        var selectedItem = chart.getSelection()[0];
                        if (selectedItem) {

                            var topping = data.getValue(selectedItem.row, 0);
                            var val = data.getValue(selectedItem.row, 1);
                            alert(topping + " " + val);
                        }
                    }
                    google.visualization.events.addListener(chart, 'select', selectHandler);
                    chart.draw(data, options);
                },
                error: function (xhr, type) {
                                  
                    alert('server error occoured ' + xhr.toString() + ' '+ type.toString());
                },
                async: false
            });
}



function set_subtitulo(opcion)
{
    if (opcion === "money")
    {
        return "Consumo de energia en Quetzales";
    }
    else
    {
        return "Consumo de energia en KWH";
    }
}

function set_subtitulo_haxis(opcion)
{
    if (opcion === "money")
    {
        return "Quetzales";
    }
    else
    {
        return "KWH";
    }
}

  function errorHandler(errorMessage) {
        //curisosity, check out the error in the console
        console.log(errorMessage);
        //simply remove the error, the user never see it
        google.visualization.errors.removeError(errorMessage.id);
    }