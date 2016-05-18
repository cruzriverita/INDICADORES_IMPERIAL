
/* global google */

//TABLA DE INDICADORES DE CALIDAD.

function DibujarTabla() {
    $.ajax
            ({
                type: "POST",
                url: "C_005_Indicadores_Calidad_Servlet",
                data: {
                    aniojs: $("#anio").val(),
                    mesjs: $("#mes").val()

                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data_) {

                    queryObject = eval('(' + JSON.stringify(data_) + ')');
                    queryObjectLen = queryObject.ListaValores.length;
                    var data = new google.visualization.DataTable();


                    data.addColumn('string', 'Ambito');
                    data.addColumn('string', 'Indicador');
                    data.addColumn('number', 'RS');
                    data.addColumn('number', 'RSM');
                    data.addColumn('number', 'RLRS');
                    data.addColumn('number', 'FPS');
                    data.addColumn('number', 'KNIT');
                    data.addColumn('number', 'DPF');

                    data.addRows(queryObjectLen);
                    for (var i = 0; i < queryObjectLen; i++)
                    {
                        
                        
                        var ambito = queryObject.ListaValores[i].ambito;
                        var indicador = queryObject.ListaValores[i].indicador;

                        //PLANTAS
                        var a1 = queryObject.ListaValores[i].valor1; //RS
                        var a2 = queryObject.ListaValores[i].valor2; //RSM
                        var a3 = queryObject.ListaValores[i].valor3; //RLRS
                        var a4 = queryObject.ListaValores[i].valor4; //FPS
                        var a5 = queryObject.ListaValores[i].valor5; //KNIT
                        var a6 = queryObject.ListaValores[i].valor6; //DPF
                        
                        //Promedio Plantas
                        var a7 = queryObject.ListaValores[i].valor7;    //Promedio RS
                        var a8 = queryObject.ListaValores[i].valor8;    //Promedio RSM
                        var a9 = queryObject.ListaValores[i].valor9;    //Promedio RLRS
                        var a10 = queryObject.ListaValores[i].valor10;  //promedio FPS
                        var a11 = queryObject.ListaValores[i].valor11;  //promedio KNIT
                        var a12 = queryObject.ListaValores[i].valor12;  //Promedio DPF
                        
                        //1ra columna de la tabla, (interno/externo)
                        data.setCell(i, 0, ambito);
                        //2da columna de la tabla, el nombre del indicador
                        data.setCell(i, 1, indicador);
                        
                        

                        //Comparar promedio vs valor actual para elegir color de la celda
                        if (a7 > a1) {
                            data.setCell(i, 2, parseFloat(a1), a1, {'className': 'red-background'});
                        } else {
                            if (a1===0)
                            data.setCell(i, 2, parseFloat(a1), a1, {'className': 'black-background'});
                            else
                            data.setCell(i, 2, parseFloat(a1), a1, {'className': 'green-background'});
                        }

                        if (a8 > a2) {
                            data.setCell(i, 3, parseFloat(a2), a2, {'className': 'red-background'});
                        } else {
                            if (a2===0)
                            data.setCell(i, 3, parseFloat(a2), a2, {'className': 'black-background'});
                            else
                            data.setCell(i, 3, parseFloat(a2), a2, {'className': 'green-background'});
                        }

                        if (a9 > a3) {
                            data.setCell(i, 4, parseFloat(a3), a3, {'className': 'red-background'});
                        } else {
                            if (a3===0)
                            data.setCell(i, 4, parseFloat(a3), a3, {'className': 'black-background'});
                            else
                            data.setCell(i, 4, parseFloat(a3), a3, {'className': 'green-background'});
                        }
                        
                         if (a10 > a4) {
                            data.setCell(i, 5, parseFloat(a4), a4, {'className': 'red-background'});
                        } else {
                            if (a4===0)
                            data.setCell(i, 5, parseFloat(a4), a4, {'className': 'black-background'});
                            else
                            data.setCell(i, 5, parseFloat(a4), a4, {'className': 'green-background'});
                        }
                        
                        if (a11 > a5) {
                            data.setCell(i, 6, parseFloat(a5), a5, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 6, parseFloat(a5), a5, {'className': 'green-background'});
                        }
                        
                         if (a12 > a6) {
                            data.setCell(i, 7, parseFloat(a6), a6, {'className': 'red-background'});
                        } else {
                            data.setCell(i, 7, parseFloat(a6), a6, {'className': 'green-background'});
                        }
                    }

                    /*Opciones Css de la tabla, los valores asignados a las propiedades estan declarados como
                     clases en el archivo Estilo.css*/
                    var cssClassNames = {
                        'headerRow': 'large-font bold-font',
                        'tableRow': '',
                        //'oddTableRow': 'beige-background',
                        //'selectedTableRow': 'orange-background large-font',
                        //'hoverTableRow': '',
                        'headerCell': 'gold-border',
                        'tableCell': '',
                        'rowNumberCell': 'black-font'};

                    var options = {'showRowNumber': false, 'allowHtml': false, 'cssClassNames': cssClassNames, height: '100%'};

                    var table = new google.visualization.Table(document.getElementById('table'));

                    google.visualization.events.addListener(table, 'ready', function () {
                    AddRowspan($('table'));
                    });
                  
                    table.draw(data, options);
                 

                    //Redirecciona dependiendo de la celda a la que se le da click.
                    $("#table table tbody tr td").click(function () {
                        //Redireccionar_inv(this, 'table');
                        Redireccionar_calidad(this, 'table');
                         // CambiarColorCelda1(this, 'table');
                    });

                    //Cambiar color al estar sobre la celda
                    $("#table table tbody tr td").mouseover(function () {
                        CambiarColorCelda1(this, 'table');
                    });

                    //Regresar al color original la celda
                    $("#table table tbody tr td").mouseout(function () {
                        ColorOriginalCelda(this, 'table');
                    });
                    
                   

                },
                error: function () {
                    alert('No existen datos para los parametros elegidos');
                    //document.getElementById("anio").value = (AnioActual() - 1);
                    location.reload();
                }

            });
}

/*funcion para agregar rowspan a la tabla, tiene que ser una funcion que manipule la tabla luego de haberla creado
debido a que se debe de modificar la tabla creada por google charts*/

function AddRowspan(selector) {
    selector.each(function () {
        var values = $('table').find("tr>td:first-of-type");
        var run = 1;
        for (var i=values.length-1;i>-1;i--){
            if ( values.eq(i).text()=== values.eq(i-1).text()){
                values.eq(i).remove();
                run++;
            }else{
                values.eq(i).attr("rowspan",run);
                run = 1;
            }
        }
    });
}


//Se ejecuta al dar click sobre una celda de la tabla de produccion en kilogramos.
function Redireccionar_calidad(x, element) {

    var rows = document.getElementById(element).getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function () {

            if (this.rowIndex === 1)
            {
                if (x.cellIndex === 1)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=1";
                }
                else if (x.cellIndex === 2)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=1&planta=1";
                }
                else if (x.cellIndex === 3)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=1&planta=2";
                }
                else if (x.cellIndex === 4)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=1&planta=3";
                }
                else if (x.cellIndex === 5)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=1&planta=4";
                }
                else if (x.cellIndex === 6)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=1&planta=5";
                }
                else if (x.cellIndex === 7)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=1&planta=6";
                }
            }


            else if (this.rowIndex === 2)
            {
                if (x.cellIndex === 0)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=2";
                }
                else if (x.cellIndex === 1)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=2&planta=1";
                }
                else if (x.cellIndex === 2)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=2&planta=2";
                }
                else if (x.cellIndex === 3)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=2&planta=3";
                }
                 else if (x.cellIndex === 4)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=2&planta=4";
                }
                 else if (x.cellIndex === 5)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=2&planta=5";
                }
                 else if (x.cellIndex === 6)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=2&planta=6";
                }
            }


            else if (this.rowIndex === 3) {
                
                if (x.cellIndex === 0)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=3";
                }
                else if (x.cellIndex === 1)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=3&planta=1";
                }
                else if (x.cellIndex === 2)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=3&planta=2";
                }
                else if (x.cellIndex === 3)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=3&planta=3";
                }
                 else if (x.cellIndex === 4)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=3&planta=4";
                }
                 else if (x.cellIndex === 5)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=3&planta=5";
                }
                 else if (x.cellIndex === 6)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=3&planta=6";
                }
                
                
            }
            else if (this.rowIndex === 4)
            {
            
                if (x.cellIndex === 1)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=4";
                }
                else if (x.cellIndex === 6)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=1&planta=5";
                }
                else if (x.cellIndex === 7)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=1&planta=6";
                }
            }
            
            

            else if (this.rowIndex === 5)
            {
                
                  if (x.cellIndex === 0)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=4";
                }
                else if (x.cellIndex === 5)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=1&planta=5";
                }
                else if (x.cellIndex === 6)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=1&planta=6";
                }
                
            }

            else if (this.rowIndex === 6)
            {
                if (x.cellIndex === 0)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=4";
                }
                else if (x.cellIndex === 5)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=1&planta=5";
                }
                else if (x.cellIndex === 6)
                {
                    location.href = "I_005_Indicadores_Calidad.jsp?indicador=1&planta=6";
                }
            }

            
        };
    }
}


function CambiarColorCelda1(x, element) {
    var rows = document.getElementById(element).getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function () {
       alert("Columna " + x.cellIndex + "FILA " + this.rowIndex);
           /* if (x.cellIndex === 0)
            {
            }
            else {
             */   
            color = x.style.backgroundColor;
            x.style.backgroundColor = 'black';
            x.style.color = 'yellow';
            // }
        };
    }
}


