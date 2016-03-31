/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
/* global google */

function DibujarTabla() {
    $.ajax
            ({
                type: "POST",
                url: "C_001_Produccion_Por_Planta_Servlet",
                data: {
                    mesjs: $("#mes").val(),
                    aniojs: $("#anio").val()
                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data_) {

                    queryObject = eval('(' + JSON.stringify(data_) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data = new google.visualization.DataTable();

                    data.addColumn('string', 'INDICADOR');
                    data.addColumn('number', 'RST');
                    data.addColumn('number', 'KNIT');
                    data.addColumn('number', 'DPF');
                    data.addColumn('number', 'RLRS');
                    data.addColumn('number', 'FPS');
                    data.addColumn('number', 'RSM O&M');

                    data.addRows(queryObjectLen);
                    for (var i = 0; i < queryObjectLen; i++)
                    {
                        var indicador = queryObject.ListaValores[i].indicador;
                        var a1 = queryObject.ListaValores[i].valor1;
                        var a2 = queryObject.ListaValores[i].valor2;
                        var a3 = queryObject.ListaValores[i].valor3;
                        var a4 = queryObject.ListaValores[i].valor4;
                        var a5 = queryObject.ListaValores[i].valor5;
                        var a6 = queryObject.ListaValores[i].valor6;
                        var a7 = queryObject.ListaValores[i].valor7;
                        var a8 = queryObject.ListaValores[i].valor8;
                        var a9 = queryObject.ListaValores[i].valor9;
                        var a10 = queryObject.ListaValores[i].valor10;
                        var a11 = queryObject.ListaValores[i].valor11;
                        var a12 = queryObject.ListaValores[i].valor12;

                        data.setCell(i, 0, indicador);
                        //Comparar promedio vs valor actual para elegir color de la celda
                        if (a2 > a1) {
                            data.setCell(i, 1, parseFloat(a1), a1, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 1, parseFloat(a1), a1, {'className': 'green-background'});
                        }
                        if (a4 > a3) {
                            data.setCell(i, 2, parseFloat(a3), a3, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 2, parseFloat(a3), a3, {'className': 'green-background'});
                        }
                        if (a6 > a5) {
                            data.setCell(i, 3, parseFloat(a5), a5, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 3, parseFloat(a5), a5, {'className': 'green-background'});
                        }
                        if (a8 > a7) {
                            data.setCell(i, 4, parseFloat(a7), a7, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 4, parseFloat(a7), a7, {'className': 'green-background'});
                        }
                        if (a10 > a9) {
                            data.setCell(i, 5, parseFloat(a9), a9, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 5, parseFloat(a9), a9, {'className': 'green-background'});
                        }
                        if (a12 > a11) {
                            data.setCell(i, 6, parseFloat(a11), a11, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 6, parseFloat(a11), a11, {'className': 'green-background'});
                        }
                    }

                    //Opciones Css de la tabla
                    var cssClassNames = {
                        'headerRow': 'large-font bold-font',
                        'tableRow': '',
                        //'oddTableRow': 'beige-background',
                        //'selectedTableRow': 'orange-background large-font',
                        //'hoverTableRow': '',
                        'headerCell': 'gold-border',
                        'tableCell': '',
                        'rowNumberCell': 'black-font'};

                    var options = {'showRowNumber': true, 'allowHtml': false, 'cssClassNames': cssClassNames};
                    var table = new google.visualization.Table(document.getElementById('table'));
                    table.draw(data, options);

                    //Redirecciona dependiendo de la celda a la que se le da click.
                    $("#table table tbody tr td").click(function () {
                        Redireccionar(this);
                    });

                    //Cambiar color al estar sobre la celda
                    $("#table table tbody tr td").mouseover(function () {
                        CambiarColorCelda(this);
                    });

                    //Regresar al color original la celda
                    $("#table table tbody tr td").mouseout(function () {
                        ColorOriginalCelda(this);
                    });
                },
                error: function () {
                    alert('No existen datos para los parametros elegidos');
                    document.getElementById("mes").value = 2; //MesActual();
                    location.reload();
                }

            });
}

//Se ejecuta al dar click sobre una celda de la tabla.
function Redireccionar(x) {
    var rows = document.getElementById('table').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function () {
            //alert("Columna " + x.cellIndex + "FILA " + this.rowIndex);
            if (this.rowIndex === 1) /*Indicador 1*/
            {
                if (x.cellIndex === 1)
                {
                    location.href = "I_001_Kilos_Producidos_Hora_Hombre.jsp?planta=ALL&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 2)
                {
                    location.href = "I_001_Kilos_Producidos_Hora_Hombre.jsp?planta=PLANTA RST&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 3)
                {
                    location.href = "I_001_Kilos_Producidos_Hora_Hombre.jsp?planta=PLANTA KNIT&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 4)
                {
                    location.href = "I_001_Kilos_Producidos_Hora_Hombre.jsp?planta=PLANTA DPF&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 5)
                {
                    location.href = "I_001_Kilos_Producidos_Hora_Hombre.jsp?planta=PLANTA RLRS&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 6)
                {
                    location.href = "I_001_Kilos_Producidos_Hora_Hombre.jsp?planta=PLANTA FPS&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                if (x.cellIndex === 7)
                {
                    location.href = "I_001_Kilos_Producidos_Hora_Hombre.jsp?planta=PLANTA RSM&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
            }
            else
            if (this.rowIndex === 2) /*Indicador 2*/
            {
                if (x.cellIndex === 1)
                {
                    location.href = "I_002_Kg_Producidos_Kwh.jsp?planta=ALL&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 2)
                {
                    location.href = "I_002_Kg_Producidos_Kwh.jsp?planta=PLANTA RST&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 3)
                {
                    location.href = "I_002_Kg_Producidos_Kwh.jsp?planta=PLANTA KNIT&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 4)
                {
                    location.href = "I_002_Kg_Producidos_Kwh.jsp?planta=PLANTA DPF&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 5)
                {
                    location.href = "I_002_Kg_Producidos_Kwh.jsp?planta=PLANTA RLRS&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 6)
                {
                    location.href = "I_002_Kg_Producidos_Kwh.jsp?planta=PLANTA FPS&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                if (x.cellIndex === 7)
                {
                    location.href = "I_002_Kg_Producidos_Kwh.jsp?planta=PLANTA RSM&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
            }
            
            else
            if (this.rowIndex === 3) /*Indicador 3*/
            {
                if (x.cellIndex === 1)
                {
                    location.href = "I_003_KgProducidos_MRS.jsp?planta=ALL&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 2)
                {
                    location.href = "I_003_KgProducidos_MRS.jsp?planta=PLANTA RST&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 3)
                {
                    location.href = "I_003_KgProducidos_MRS.jsp?planta=PLANTA KNIT&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 4)
                {
                    location.href = "I_003_KgProducidos_MRS.jsp?planta=PLANTA DPF&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 5)
                {
                    location.href = "I_003_KgProducidos_MRS.jsp?planta=PLANTA RLRS&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 6)
                {
                    location.href = "I_003_KgProducidos_MRS.jsp?planta=PLANTA FPS&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                if (x.cellIndex === 7)
                {
                    location.href = "I_003_KgProducidos_MRS.jsp?planta=PLANTA RSM&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
            }
            
            else
            if (this.rowIndex === 4) /*Indicador 4*/
            {
                if (x.cellIndex === 1)
                {
                    location.href = "I_004_CostoMo_KgProducido.jsp?planta=ALL&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 2)
                {
                    location.href = "I_004_CostoMo_KgProducido.jsp?planta=PLANTA RST&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 3)
                {
                    location.href = "I_004_CostoMo_KgProducido.jsp?planta=PLANTA KNIT&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 4)
                {
                    location.href = "I_004_CostoMo_KgProducido.jsp?planta=PLANTA DPF&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 5)
                {
                    location.href = "I_004_CostoMo_KgProducido.jsp?planta=PLANTA RLRS&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 6)
                {
                    location.href = "I_004_CostoMo_KgProducido.jsp?planta=PLANTA FPS&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                if (x.cellIndex === 7)
                {
                    location.href = "I_004_CostoMo_KgProducido.jsp?planta=PLANTA RSM&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
            }
            
                        else
            if (this.rowIndex === 5) /*Indicador 5*/
            {
                if (x.cellIndex === 1)
                {
                    location.href = "I_005_CostoKWH_KgProducido.jsp?planta=ALL&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 2)
                {
                    location.href = "I_005_CostoKWH_KgProducido.jsp?planta=PLANTA RST&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 3)
                {
                    location.href = "I_005_CostoKWH_KgProducido.jsp?planta=PLANTA KNIT&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 4)
                {
                    location.href = "I_005_CostoKWH_KgProducido.jsp?planta=PLANTA DPF&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 5)
                {
                    location.href = "I_005_CostoKWH_KgProducido.jsp?planta=PLANTA RLRS&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 6)
                {
                    location.href = "I_005_CostoKWH_KgProducido.jsp?planta=PLANTA FPS&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                if (x.cellIndex === 7)
                {
                    location.href = "I_005_CostoKWH_KgProducido.jsp?planta=PLANTA RSM&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
            }
            
             else
            if (this.rowIndex === 6) /*Indicador 6*/
            {
                if (x.cellIndex === 1)
                {
                    location.href = "I_006_CostoMRS_KgProducido.jsp?planta=ALL&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 2)
                {
                    location.href = "I_006_CostoMRS_KgProducido.jsp?planta=PLANTA RST&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 3)
                {
                    location.href = "I_006_CostoMRS_KgProducido.jsp?planta=PLANTA KNIT&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 4)
                {
                    location.href = "I_006_CostoMRS_KgProducido.jsp?planta=PLANTA DPF&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 5)
                {
                    location.href = "I_006_CostoMRS_KgProducido.jsp?planta=PLANTA RLRS&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                else
                if (x.cellIndex === 6)
                {
                    location.href = "I_006_CostoMRS_KgProducido.jsp?planta=PLANTA FPS&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
                if (x.cellIndex === 7)
                {
                    location.href = "I_006_CostoMRS_KgProducido.jsp?planta=PLANTA RSM&mes=" + $("#mes").val() + "&anio=" + $("#anio").val() + "";
                }
            }
            
        };
    }
}






