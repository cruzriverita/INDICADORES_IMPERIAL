/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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


    for (var i = 0; i < queryObjectLen; i++)
    {
        var planta = queryObject.ListaValores[i].planta;
        var enero = queryObject.ListaValores[i].valor1;
        var febrero = queryObject.ListaValores[i].valor2;
        var marzo = queryObject.ListaValores[i].valor3;

        data.addRows([
            [planta, parseFloat(enero), parseFloat(febrero),parseFloat(marzo)]
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
            location.href = "I_003_Costo_Energia_Detalle.jsp?planta=" +planta+ "&anio=" + $("#anio").val() + "&mes=" +mes;
        }
    }
    google.visualization.events.addListener(chart, 'select', ClickBarra);

    //chart.draw(data, options);
    chart.draw(data, google.charts.Bar.convertOptions(options));
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