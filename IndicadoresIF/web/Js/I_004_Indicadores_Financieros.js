/* global google */

function DibujarChartPrincipal() {
    $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "I_004_Indicadores_Financieros_Servlet",
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
                    if ($('#opciones option:selected').val() === "ALL")
                    {
                        queryObject = eval('(' + JSON.stringify(data_) + ')');
                        queryObjectLen = queryObject.ListaValores.length;
                        var data = new google.visualization.DataTable();
                        /*convertir #anio a entero*/
                        var x = parseInt($("#anio").val(), 10);


                        data.addColumn('string', 'Empresa');
                        data.addColumn('number', (x - 1));
                        data.addColumn('number', 'Mayor Historico');
                        data.addColumn('number', x);
                        data.addColumn('number', 'Promedio ' + (x - 1));
                        data.addColumn('number', 'Menor Historico');

                        for (var i = 0; i < queryObjectLen; i++)
                        {
                            var planta = queryObject.ListaValores[i].planta;


                            var a1 = queryObject.ListaValores[i].valor1;
                            var a2 = queryObject.ListaValores[i].valor2;
                            var a3 = queryObject.ListaValores[i].valor3;
                            var a6 = queryObject.ListaValores[i].valor6;
                            var a4 = queryObject.ListaValores[i].valor4;



                            data.addRows([
                                [planta, /*parseFloat(a4)*/ parseFloat(a1), parseFloat(a3),
                                    parseFloat(a2), /*parseFloat(a5)*/parseFloat(a6), parseFloat(a4)
                                ]
                            ]);
                        }


                        var options = {
                            title: '',
                            vAxis: {title: '',
                                titleTextStyle: {color: ColorFuenteGrafica()}, textStyle: {color: ColorFuenteGrafica()}},
                            hAxis: {textStyle: {color: ColorFuenteGrafica()}},
                            is3D: true,
                            colors: Coloresrrhh(),
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
                            },
                            backgroundColor: FondoGrafica(),
                            legend: {
                                textStyle: {
                                    color: ColorFuenteGrafica()
                                }}
                        };

                        var chart = new google.visualization.ColumnChart(document.getElementById('GraficaPrincipal'));

                        //funcion que se ejecuta al dar click sobre la barra
                        function ClickBarra() {

                            var selectedItem = chart.getSelection()[0];
                            if (selectedItem) {
                                var planta = data.getValue(selectedItem.row, 0);
                                click_grafica(planta);


                            }
                        }
                        google.visualization.events.addListener(chart, 'select', ClickBarra);
                        chart.draw(data, options);

                    }

                    /************************************************GRAFICA LINEAL**************************************************************************/
                    //Si en el Select se elige una planta especifica se muestra por medio de grafica lineal
                    else
                    {
                        var tamlinea = tamalinea();


                        queryObject = eval('(' + JSON.stringify(data_) + ')');
                        queryObjectLen = queryObject.ListaValores.length;
                        var data = new google.visualization.DataTable();

                        var x = parseInt($("#anio").val(), 10);

                        data.addColumn('string', 'mes');
                        data.addColumn({type: 'string', role: 'annotation'});
                        data.addColumn('number', (x - 1));
                        //data.addColumn('number', MayorMenor());
                        data.addColumn('number', 'Mayor Historico');
                        data.addColumn('number', x);
                        //data.addColumn({type:'string', role:'annotation'});
                        data.addColumn('number', 'Promedio ' + (x - 1));
                        //data.addColumn({type:'string', role:'annotation'});
                        data.addColumn('number', 'Menor Historico');

                        for (var i = 0; i < queryObjectLen; i++)
                        {
                            var mes = queryObject.ListaValores[i].mes;
                            var a1 = queryObject.ListaValores[i].valor1;
                            var a2 = queryObject.ListaValores[i].valor2;

                            a1 = DevolverNull(a1);
                            a2 = DevolverNull(a2);

                            var a3 = queryObject.ListaValores[i].valor3;
                            var a4 = queryObject.ListaValores[i].valor4;
                            var a6 = queryObject.ListaValores[i].valor6;
                            var m = queryObject.ListaValores[i].mejormes;
                            var a = queryObject.ListaValores[i].mejoranio;
                            var mm = queryObject.ListaValores[i].peormes;
                            var aa = queryObject.ListaValores[i].peoranio;
                            
                            var menor=a3;
                            var mayor=a4;

                            data.addRows([
                                [mes, "", parseFloat(a1), parseFloat(a3),
                                    parseFloat(a2), parseFloat(a6), parseFloat(a4)
                                ]
                            ]);
                        }

                        var options = {
                            title: '',
                            vAxis: {title: '', titleTextStyle: {color: ColorFuenteGrafica()}, textStyle: {color: ColorFuenteGrafica()},gridlines: {count: 30}, viewWindow: {
                    min: (menor - 0.6),
                    max: (mayor + 0.6)}},
                            hAxis: {title: '*El valor "' + "Menor historico" + '" corresponde a ' + ConvertirMes(mm) + ' de ' + aa + '\n'
                                        + '*El valor "' + "Mayor historico" + '" corresponde a ' + ConvertirMes(m) + ' de ' + a

                                , titleTextStyle: {color: ColorFuenteGrafica()}, textStyle: {color: ColorFuenteGrafica()}, format: '#.####'
                            },
                            is3D: true,
                            colors: Coloresrrhh(),
                            annotations: {
                                style: 'line'
                            },
                            series: {
                                0: {pointShape: 'circle', pointSize: tamapunto()},
                                2: {pointShape: 'circle', pointSize: tamapunto()}

                            }
                            , lineWidth: tamlinea,
                            backgroundColor: FondoGrafica(),
                            legend: {
                                textStyle: {
                                    color: ColorFuenteGrafica()
                                }}
                        };


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
                    //document.getElementById("mes").value = 2; //MesActual();
                    location.reload();
                }
            });
}

function click_grafica(empresa)
{
    if (empresa === "IMPERIAL FASHION") {
        document.getElementById('opciones').value = "1";
    }
    else if (empresa === "MT TEXTIL") {
        document.getElementById('opciones').value = "2";
    }
    else if (empresa === "BLAKE") {
        document.getElementById('opciones').value = "3";
    }
    else if (empresa === "IMPERIALTEX") {
        document.getElementById('opciones').value = "4";
    }
    
    else if (empresa === "FABRICA IMPERIAL") {
        document.getElementById('opciones').value = "5";
    }
    else if (empresa === "CONSOLIDADO") {
        document.getElementById('opciones').value = "6";
    }

    //empresas pendientes
    DibujarChartPrincipal();
    hideMes();
}


function GetTituloG4() {
    var e = document.getElementById("indicador").value;

    if (e === "1")
    {
        document.getElementById('titulo').innerHTML = "Liquidez Corriente";
    }

    else if (e === "2")
    {
        document.getElementById('titulo').innerHTML = "Prueba Acida";
    }
    else if (e === "3")
    {
        document.getElementById('titulo').innerHTML = "Apalancamiento Financiero";
    }
    else if (e === "4")
    {
        document.getElementById('titulo').innerHTML = "Indice de Endeudamiento";
    }
    else if (e === "5")
    {
        document.getElementById('titulo').innerHTML = "Rentabilidad Neta del Activo";
    }
    else if (e === "6")
    {
        document.getElementById('titulo').innerHTML = "Margen Bruto";
    }

    else if (e === "7")
    {
        document.getElementById('titulo').innerHTML = "Margen Operacional";
    }
    else if (e === "8")
    {
        document.getElementById('titulo').innerHTML = "Rendimiento sobre Activos Totales";
    }
    else if (e === "9")
    {
        document.getElementById('titulo').innerHTML = "Impacto de Gastos Administrativos y Ventas";
    }
    else if (e === "10")
    {
        document.getElementById('titulo').innerHTML = "Impacto Carga Financiera";
    }
    else if (e === "11")
    {
        document.getElementById('titulo').innerHTML = "Total pagado por multas y retificaciones";
    }
}

function GetEmpresa()
{
     var p = document.getElementById("opciones");
     var val = p.options[p.selectedIndex].value;
    
        if (val === "1")
        {
            
            document.getElementById('titulop').innerHTML = "IMPERIAL FASHION";
        }

        else if (val === "2")
        {
          document.getElementById('titulop').innerHTML = "MT TEXTIL";  
        }
        
         else if (val === "3")
        {
          document.getElementById('titulop').innerHTML = "BLAKE S.A.";  
        }
        
         else if (val === "4")
        {
          document.getElementById('titulop').innerHTML = "IMPERIALTEX";  
        }
        
         else if (val === "5")
        {
          document.getElementById('titulop').innerHTML = "FABRICA IMPERIAL";  
        }
        
         else if (val === "6")
        {
          document.getElementById('titulop').innerHTML = "CONSOLIDADO";  
        }
        
         else 
        {
          document.getElementById('titulop').innerHTML = "";  
        }
        

}