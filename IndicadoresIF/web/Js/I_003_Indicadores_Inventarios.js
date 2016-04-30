
//Se obtienen los datos para la grafica principal, esto desde el servlet.
//Devuelve una cadena separada por comas desde el servlet, la cual contiene los valores a graficar.
function carga()
{
    var val;
    $.ajax({
        type: 'POST',
        url: 'I_003_Indicadores_Inventarios_Servlet',
        data: {
            aniojs: $("#anio").val(),
            tipojs: $('#tipo option:selected').val(),
            artjs: $('#tipo2 option:selected').val(),
            indicador: $('#indicador').val()
        },
        complete: function (r) {
            // alert(r.responseText);
            val = r.responseText;
        }, async: false
    });
    return val;
}

//Se obtienen los datos para la grafica secundaria, esto desde el servlet.
function carga2()
{
    var val;
    $.ajax({
        type: 'POST',
        url: 'I_003_Indicadores_Inventarios_Planta_Servlet',
        data: {
            tipo: $("#tipo").val(),
            aniojs: $("#anio").val(),
            opcion: $('#opciones option:selected').val(), //trae la planta seleccionada.
            tipojs: $('#tipo2').val()
        },
        complete: function (r) {
            // alert(r.responseText);
            val = r.responseText;
        }, async: false
    });
    return val;
}

var tamlinea = tamalinea();

var primaryData; //vector grafica principal
var tooltipData; //vector grafica secundaria

var primaryOptions; //Opciones grafica principal
var tooltipOptions; //opciones grafica secundaria


// Draws your charts to pull the PNGs for your tooltips.
//Funcion principal que se llama desde jsp para dibujar las graficas.
function DibujarChartPrincipal() {

    //Se obtienen las dos cadenas, 1 de cada servlet y con al informacion para las graficas, principal y secundaria respectivamente.
    var cadena = carga();
    var cadena2 = carga2();
    //Split a las dos cadenas para tener los datos como vector y darles formato.
    cadena = cadena.split(",");
    cadena2 = cadena2.split(",");

    //Se arma el vector con los datos de la grafica principal de forma manual.
    primaryData =
            [
                [ConvertirMes(cadena[0]), '', parseFloat(cadena[1]), parseFloat(cadena[2]), parseFloat(cadena[3]), parseFloat(cadena[4]), parseFloat(cadena[5])],
                [ConvertirMes(cadena[6]), '', parseFloat(cadena[7]), parseFloat(cadena[8]), parseFloat(cadena[9]), parseFloat(cadena[10]), parseFloat(cadena[11])],
                [ConvertirMes(cadena[12]), '', parseFloat(cadena[13]), parseFloat(cadena[14]), parseFloat(cadena[15]), parseFloat(cadena[16]), parseFloat(cadena[17])],
                [ConvertirMes(cadena[18]), '', parseFloat(cadena[19]), parseFloat(cadena[20]), parseFloat(cadena[21]), parseFloat(cadena[22]), parseFloat(cadena[23])],
                [ConvertirMes(cadena[24]), '', parseFloat(cadena[25]), parseFloat(cadena[26]), parseFloat(cadena[27]), parseFloat(cadena[28]), parseFloat(cadena[29])],
                [ConvertirMes(cadena[30]), '', parseFloat(cadena[31]), parseFloat(cadena[32]), parseFloat(cadena[33]), parseFloat(cadena[34]), parseFloat(cadena[35])],
                [ConvertirMes(cadena[36]), '', parseFloat(cadena[37]), parseFloat(cadena[38]), parseFloat(cadena[39]), parseFloat(cadena[40]), parseFloat(cadena[41])],
                [ConvertirMes(cadena[42]), '', parseFloat(cadena[43]), parseFloat(cadena[44]), parseFloat(cadena[45]), parseFloat(cadena[46]), parseFloat(cadena[47])],
                [ConvertirMes(cadena[48]), '', parseFloat(cadena[49]), parseFloat(cadena[50]), parseFloat(cadena[51]), parseFloat(cadena[52]), parseFloat(cadena[53])],
                [ConvertirMes(cadena[54]), '', parseFloat(cadena[55]), parseFloat(cadena[56]), parseFloat(cadena[57]), parseFloat(cadena[58]), parseFloat(cadena[59])],
                [ConvertirMes(cadena[60]), '', parseFloat(cadena[61]), parseFloat(cadena[62]), parseFloat(cadena[63]), parseFloat(cadena[64]), parseFloat(cadena[65])],
                [ConvertirMes(cadena[66]), '', parseFloat(cadena[67]), parseFloat(cadena[68]), parseFloat(cadena[69]), parseFloat(cadena[70]), parseFloat(cadena[71])]
            ];

    //Datos necesarios para utilizar en la grafica principal.        
    var menor = parseFloat(cadena[4]);
    var mayor = parseFloat(cadena[2]);
    var mayormes = cadena[72];
    var menormes = cadena[73];
    var mayoranio = parseInt(cadena[74]);
    var menoranio = parseInt(cadena[75]);



    /*******************************************OPCIONES DE LA GRAFICA PRINCIPAL (LINEAL)*************************************/

    //Se crean las opciones dependiendo si se vera la grafica por indice o por dias de inventario.
    if ($('#tipo option:selected').val() === "1") //Si es indice
    {
        primaryOptions = {
            title: '',
            //legend: 'none',
            tooltip: {isHtml: true}, // This MUST be set to true for your chart to show.
            vAxis: {title: 'Indice del mes', titleTextStyle: {color: 'Black'}, gridlines: {count: 20}, viewWindow: {
                    min: (menor - 0.1),
                    max: (mayor + 0.1)
                }},
            hAxis: {title: '*El valor "Menor historico" corresponde a ' + ConvertirMes(menormes) + ' de ' + menoranio + '\n' +
                        '*El valor "Mayor historico" corresponde a ' + ConvertirMes(mayormes) + ' de ' + mayoranio, titleTextStyle: {color: 'Blue'}},
            colors: ["#EAD008", "#40FF00", "#01DFD7", "#FF0000", "#000000"],
            annotations: {
                style: 'line'
            },
            series: {
                0: {pointShape: 'circle', pointSize: tamapunto()},
                4: {pointShape: 'circle', pointSize: tamapunto()}

            },
            lineWidth: tamlinea
        };
    }
    else
    {

        primaryOptions = {
            title: '', //'Cantidad de empleados Planilla '+ x,
            tooltip: {isHtml: true}, // This MUST be set to true for your chart to show.
            vAxis: {title: 'Dias de inventario', titleTextStyle: {color: 'Black'}, gridlines: {count: 20}, viewWindow: {
                    min: (menor - 1),
                    max: (mayor + 1)
                }},
            hAxis: {title: '*El valor "Menor historico" corresponde a ' + ConvertirMes(menormes) + ' de ' + menoranio + '\n' +
                        '*El valor "Mayor historico" corresponde a ' + ConvertirMes(mayormes) + ' de ' + mayoranio, titleTextStyle: {color: 'Blue'}},
            is3D: true,
            colors: ["#EAD008", "#40FF00", "#01DFD7", "#FF0000", "#000000"],
            annotations: {
                style: 'line'
            },
            series: {
                0: {pointShape: 'circle', pointSize: tamapunto()},
                4: {pointShape: 'circle', pointSize: tamapunto()}

            }, lineWidth: tamlinea
        };
    }

    /*
     tooltipData =
     [
     [cadena2[0], parseFloat(cadena2[1]), parseFloat(cadena2[2]), parseFloat(cadena2[3]), parseFloat(cadena2[4]), parseFloat(cadena2[5]), parseFloat(cadena2[6]), parseFloat(cadena2[7]), parseFloat(cadena2[8]), parseFloat(cadena2[9]), parseFloat(cadena2[10]), parseFloat(cadena2[11]), parseFloat(cadena2[12])],
     [cadena2[13], parseFloat(cadena2[14]), parseFloat(cadena2[15]), parseFloat(cadena2[16]), parseFloat(cadena2[17]), parseFloat(cadena2[18]), parseFloat(cadena2[19]), parseFloat(cadena2[20]), parseFloat(cadena2[21]), parseFloat(cadena2[22]), parseFloat(cadena2[23]), parseFloat(cadena2[24]), parseFloat(cadena2[25])],
     [cadena2[26], parseFloat(cadena2[27]), parseFloat(cadena2[28]), parseFloat(cadena2[29]), parseFloat(cadena2[30]), parseFloat(cadena2[31]), parseFloat(cadena2[32]), parseFloat(cadena2[33]), parseFloat(cadena2[34]), parseFloat(cadena2[35]), parseFloat(cadena2[36]), parseFloat(cadena2[37]), parseFloat(cadena2[38])]
     
     ];*/

    //Se especifican las opciones de la grafica ya sea si es por indice o por dias.
    if ($('#tipo option:selected').val() === "1")
    {
        tooltipOptions = {
            width: 400,
            height: 300,
            title: 'Rotacion de Inventarios (Plantas)',
            is3D: true,
            // chartArea: {  width: "90%", height: "90%" },

            legend: 'none',
            hAxis: {title: 'Plantas', titleTextStyle: {color: 'Black'}},
            vAxis: {title: 'Indice del mes', titleTextStyle: {color: 'Black'}, gridlines: {count: 15}, format: "#.##"}
            //legend: 'none'
        };
    }
    else
    {
        tooltipOptions = {
            title: 'Rotacion de Inventarios (Plantas)',
            is3D: true,
            //chartArea: {  width: "100%", height: "70%" },

            legend: 'none',
            hAxis: {title: 'Plantas', titleTextStyle: {color: 'Black'}},
            vAxis: {title: 'Dias de Inventario', titleTextStyle: {color: 'Black'}, gridlines: {count: 15}, format: "#.##"}
            //legend: 'none'
        };
    }

    //var data = new google.visualization.arrayToDataTable(tooltipData);
    //Se crea el datatable para los datos de la grafica secundaria.
    var data = new google.visualization.DataTable();
    data.addColumn('string', '');
    data.addColumn('number', '');
    data.addColumn('number', '');
    data.addColumn('number', '');
    data.addColumn('number', '');
    data.addColumn('number', '');
    data.addColumn('number', '');
    data.addColumn('number', '');
    data.addColumn('number', '');
    data.addColumn('number', '');
    data.addColumn('number', '');
    data.addColumn('number', '');
    data.addColumn('number', '');

    //Se agregan los datos al datatable de forma manual por medio de los valores leidos del servlet para la grafica secundaria.
    data.addRows([
        [cadena2[0], parseFloat(cadena2[1]), parseFloat(cadena2[2]), parseFloat(cadena2[3]), parseFloat(cadena2[4]), parseFloat(cadena2[5]), parseFloat(cadena2[6]), parseFloat(cadena2[7]), parseFloat(cadena2[8]), parseFloat(cadena2[9]), parseFloat(cadena2[10]), parseFloat(cadena2[11]), parseFloat(cadena2[12])],
        [cadena2[13], parseFloat(cadena2[14]), parseFloat(cadena2[15]), parseFloat(cadena2[16]), parseFloat(cadena2[17]), parseFloat(cadena2[18]), parseFloat(cadena2[19]), parseFloat(cadena2[20]), parseFloat(cadena2[21]), parseFloat(cadena2[22]), parseFloat(cadena2[23]), parseFloat(cadena2[24]), parseFloat(cadena2[25])],
        [cadena2[26], parseFloat(cadena2[27]), parseFloat(cadena2[28]), parseFloat(cadena2[29]), parseFloat(cadena2[30]), parseFloat(cadena2[31]), parseFloat(cadena2[32]), parseFloat(cadena2[33]), parseFloat(cadena2[34]), parseFloat(cadena2[35]), parseFloat(cadena2[36]), parseFloat(cadena2[37]), parseFloat(cadena2[38])],
        [cadena2[39], parseFloat(cadena2[40]), parseFloat(cadena2[41]), parseFloat(cadena2[42]), parseFloat(cadena2[43]), parseFloat(cadena2[44]), parseFloat(cadena2[45]), parseFloat(cadena2[46]), parseFloat(cadena2[47]), parseFloat(cadena2[48]), parseFloat(cadena2[49]), parseFloat(cadena2[50]), parseFloat(cadena2[51])]
    ]);

    var view = new google.visualization.DataView(data);


    // For each row of primary data, draw a chart of its tooltip data.
    for (var i = 0; i < primaryData.length; i++) {
        // Set the view for each event's data
        view.setColumns([0, i + 1]);

        var hiddenDiv = document.getElementById('hidden_div');
        var tooltipChart = new google.visualization.ColumnChart(hiddenDiv);

        google.visualization.events.addListener(tooltipChart, 'ready', function () {

            // Get the PNG of the chart and set is as the src of an img tag.
            var tooltipImg = '<center><h3>' + primaryData[i][0].toString() + ' ' + $("#anio").val() + '</h3></center><br><img src="' + tooltipChart.getImageURI() + '">';

            // Si el tipo de inventario (algodon, poliester, etc) no tiene subgrafica se agrega esta opcion
            var tooltipImg2 = '<h2>' + '&nbsp&nbsp' + primaryData[i][0].toString() + '<br><br>' + '&nbsp&nbsp' + $("#anio").val() + ': ' + primaryData[i][6].toString() + '&nbsp&nbsp' + '</h2>';

            // Add the new tooltip image to your data rows.
            // Se agrega de acuerdo al tipo de inventario.
            if ($('#tipo2').val() === "1" || $('#tipo2').val() === "2" || $('#tipo2').val() === "4")
            {
                primaryData[i][7] = tooltipImg2;
            }

            else
            {
                primaryData[i][7] = tooltipImg;
            }
        });

        tooltipChart.draw(view, tooltipOptions);
    }
    drawPrimaryChart();
}

//Se dibuja la grafica principal
function drawPrimaryChart() {

    var x = parseInt($('#anio').val(), 10);
    var data = new google.visualization.DataTable();

    data.addColumn('string', 'periodo');
    data.addColumn({type: 'string', role: 'annotation'});


    data.addColumn('number', x - 1);
    data.addColumn('number', 'Mayor historico');
    data.addColumn('number', 'promedio');
    data.addColumn('number', 'Menor historico');
    data.addColumn('number', x);

    // Add a new column for your tooltips.
    data.addColumn({
        type: 'string',
        label: 'Tooltip Chart',
        role: 'tooltip',
        'p': {'html': true}
    });

    // Add your data (with the newly added tooltipImg).
    data.addRows(primaryData);
    var visibleDiv = document.getElementById('GraficaPrincipal');
    var primaryChart = new google.visualization.LineChart(visibleDiv);
    primaryChart.draw(data, primaryOptions);

}


//Setear el titulo de la grafica en el jsp de acuerdo a los parametros elegidos, esta funcion se llama en el JSP.
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
        document.getElementById('titulo').innerHTML = "Rotacion de Inventarios por antiguedad - " + GetNombreArticulo(val2);
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

