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
                url: "I_000_Produccion_Por_Planta_Mes_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs.         
                data: {
                    aniojs: $("#anio").val(),
                    mesjs: $("#mes").val()
                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data) {
                    queryObject = eval('(' + JSON.stringify(data) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                },
                error: function () {

                    alert('No existen datos para los parametros ingresados');
                    document.getElementById("mes").value = MesActual();
                    document.getElementById("anio").value = AnioActual();
                    location.reload();

                },
                async: false
            });

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
        hAxis: {title: 'Year', titleTextStyle: {color: 'Black'}},
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
}

/*---------------------------------------------------------------------------------------------------*/

function DibujarChartDetalle() {
    $.ajax
            ({
                type: "POST",
                url: "I_000_Produccion_Por_Planta_Commodity_Servlet",
                data: {planta: getParameterByName("planta"), anio: getParameterByName("anio"), mes: getParameterByName("mes")},
                dataType: "json",
                success: function (data2) {
                    queryObject = eval('(' + JSON.stringify(data2) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data = new google.visualization.DataTable();

                    data.addColumn('string', 'commodity');
                    data.addColumn('number', 'val');

                    for (var i = 0; i < queryObjectLen; i++)
                    {
                        var commodity = queryObject.ListaValores[i].commodity;
                        var valor = queryObject.ListaValores[i].valor;

                        data.addRows([
                            [commodity, parseInt(valor)]
                        ]);
                    }
                    var options = {
                        title: 'Planta ' + getParameterByName("planta"),
                        hAxis: {title: 'Year', titleTextStyle: {color: 'red'}},
                        is3D: true
                    };
                    var chart = new google.visualization.PieChart(document.getElementById('piechart_detalle'));

                    function selectHandler() {
                        var selectedItem = chart.getSelection()[0];
                        if (selectedItem) {

                            var topping = data.getValue(selectedItem.row, 0);
                            var val = data.getValue(selectedItem.row, 1)
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

