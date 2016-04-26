//Javascript que contiene funciones utilizadas en diferentes jsp por lo tanto se toman como "globales"

//Genera un color de manera aleatoria para colocar a cada elemento de la grafica de barras.
function GenerarColorRandom()
{
    return '#' + (0x1000000 + (Math.random()) * 0xffffff).toString(16).substr(1, 6);
}

//Recoger parametros enviados por medio de una URL
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
    var regexS = "[\\?&]" + name + "=([^&#]*)",
            regex = new RegExp(regexS),
            results = regex.exec(window.location.href);
    if (results === null) {
        return "";
    } else {
        return decodeURIComponent(results[1].replace(/\+/g, " "));
    }
}

//Obtener el mes actual
function MesActual()
{
    var d = new Date();
    var n = d.getMonth();
    return n + 1;
}

//Obtener el año actual
function AnioActual()
{
    var d = new Date();
    var n = d.getFullYear();
    return n;
}

//Convertir a nombre de mes
function ConvertirMes(mes)
{
    if (mes === "1") {
        return "Ene";
    }
    else if (mes === "2") {
        return "Feb";
    }
    else if (mes === "3") {
        return "Mar";
    }
    else if (mes === "4") {
        return "Abr";
    }
    else if (mes === "5") {
        return "May";
    }
    else if (mes === "6") {
        return "Jun";
    }
    else if (mes === "7") {
        return "Jul";
    }
    else if (mes === "8") {
        return "Ago";
    }
    else if (mes === "9") {
        return "Sep";
    }
    else if (mes === "10") {
        return "Oct";
    }
    else if (mes === "11") {
        return "Nov";
    }
    else if (mes === "12") {
        return "Dic";
    }

}


//Colores elegidos a utilizar en todas las graficas 
//año anterior= amarillo ,mejor=verde, añoactual=negro, promedio= aqua, peor=rojo
function Coloresrrhh()
{
    var e = document.getElementById("indicador").value;
    //Si es uno de los 3 indicadores de costos entonces el menor es color verde (menos costos es mejor) y el mayor es el rojo.
    if (e==="INDICADOR4" || e==="INDICADOR5" || e==="INDICADOR6" ){
    var colors = ["#EAD008", "#FF0000", "#000000", "#01DFD7",  "#40FF00"];
    return colors;
    }
    else
    {
    var colors = ["#EAD008", "#40FF00", "#000000", "#01DFD7", "#FF0000"];
    return colors;
    }
}


/*------------------------------------funciones tablas de indicadores------------------------------*/

var color = ''; //obtiene el color actaul de la celda de la tabla
//Funcion que cambia de color una celda de una tabla 
function CambiarColorCelda(x, element) {
    var rows = document.getElementById(element).getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onmouseover = function () {
            // alert("Columna " + x.cellIndex + "FILA " + this.rowIndex);
            if (x.cellIndex === 1 || x.cellIndex === 0)
            {
            }
            else {
                color = x.style.backgroundColor;
                x.style.backgroundColor = 'black';
                x.style.color = 'yellow';
            }
        };
    }
}

//se utiliza la variable "color" y se regresa la celda a su color original.
function ColorOriginalCelda(x, element) {
    var rows = document.getElementById(element).getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onmouseout = function () {
            x.style.backgroundColor = color;
            x.style.color = 'black';
        };
    }
}

//Esconder los campos de texto relacionados con el mes en las graficas en donde el mes no es parametro.
function hideMes() {
    var e = document.getElementById("opciones");
    var val = e.options[e.selectedIndex].value;
    if (val === "ALL" || val === "FPS MES")
    {
        document.getElementById('mes').style.display = "block";
        document.getElementById('lblmes').style.display = "block";
        document.getElementById('divlblmes').style.display = "block";
        document.getElementById('divselectmes').style.display = "block";
    }
    else
    {
        document.getElementById('mes').style.display = "none";
        document.getElementById('lblmes').style.display = "none";
        document.getElementById('divlblmes').style.display = "none";
        document.getElementById('divselectmes').style.display = "none";
    }
}

function GetTitulo() {
    var e = document.getElementById("indicador").value;
    //var val = e.options[e.selectedIndex].value;
    if (e === "INDICADOR1")
    {
        return "Kilos Producidos / Hora-Hombre ";
    }
    else if (e === "INDICADOR2")
    {
        return "Kilos Producidos / Kilovatio-Hora ";
    }
    else if (e === "INDICADOR3")
    {
        return "Kilos Producidos / MRS ";
    }
    else if (e === "INDICADOR4")
    {
        return "Costo Mo / Kg Producidos ";
    }
    else if (e === "INDICADOR5")
    {
        return "Costo KWH / Kg Producidos ";
    }
    else if (e === "INDICADOR6")
    {
        return "Costo MRS / Kg Producidos ";
    }
}

function GetTituloEje() {
    var e = document.getElementById("indicador").value;
    //var val = e.options[e.selectedIndex].value;
    if (e === "INDICADOR1")
    {
        return "Kilogramos / Hora-Hombre ";
    }
    else if (e === "INDICADOR2")
    {
        return "Kilogramos / Kilovatio-Hora ";
    }
    else if (e === "INDICADOR3")
    {
        return "Kilogramos / MRS ";
    }
    else if (e === "INDICADOR4")
    {
        return "($) Costo Mo / Kg Producido ";
    }
    else if (e === "INDICADOR5")
    {
        return "($) Costo KWH / Kg Producido ";
    }
    else if (e === "INDICADOR6")
    {
        return "($) Costo MRS / Kg Producido ";
    }
}

function GetTituloDPF() {
    var e = document.getElementById("indicador").value;
    //var val = e.options[e.selectedIndex].value;
    if (e === "INDICADOR1")
    {
        return "Docenas Producidas / Hora-Hombre ";
    }
    else if (e === "INDICADOR2")
    {
        return "Docenas Producidas / Kilovatio-Hora ";
    }
    else if (e === "INDICADOR3")
    {
        return "Docenas Producidas / MRS ";
    }
    else if (e === "INDICADOR4")
    {
        return "Costo Mo / Docenas Producidas ";
    }
    else if (e === "INDICADOR5")
    {
        return "Costo KWH / Docenas Producidas ";
    }
    else if (e === "INDICADOR6")
    {
        return "Costo MRS / Docenas Producidas ";
    }
}

function GetTituloDPFEje() {
    var e = document.getElementById("indicador").value;
    //var val = e.options[e.selectedIndex].value;
    if (e === "INDICADOR1")
    {
        return "Docenas / Hora-Hombre ";
    }
    else if (e === "INDICADOR2")
    {
        return "Docenas / Kilovatio-Hora ";
    }
    else if (e === "INDICADOR3")
    {
        return "Docenas / MRS ";
    }
    else if (e === "INDICADOR4")
    {
        return "Costo Mo / Docena ";
    }
    else if (e === "INDICADOR5")
    {
        return "Costo KWH / Docena ";
    }
    else if (e === "INDICADOR6")
    {
        return "Costo MRS / Docena";
    }
}




function GetTituloG() {
    var e = document.getElementById("indicador").value;

    var p = document.getElementById("opciones");
    var val = p.options[p.selectedIndex].value;

    if (val === "PLANTA FPS" || val === "FPS MES")
    {
        if (e === "INDICADOR1")
        {
            document.getElementById('titulo').innerHTML = "Docenas Producidas / Hora-Hombre ";
        }

        else if (e === "INDICADOR2")
        {
            document.getElementById('titulo').innerHTML = "Docenas Producidas / Kilovatio-Hora ";
        }
        else if (e === "INDICADOR3")
        {
            document.getElementById('titulo').innerHTML = "Docenas Producidas / MRS ";
        }
        else if (e === "INDICADOR4")
        {
            document.getElementById('titulo').innerHTML = "Costo Mo / Docenas Producidas ";
        }
        else if (e === "INDICADOR5")
        {
            document.getElementById('titulo').innerHTML = "Costo KWH / Docenas Producidas ";
        }
        else if (e === "INDICADOR6")
        {
            document.getElementById('titulo').innerHTML = "Costo MRS / Docenas Producidas ";
        }

    }
    else
    {
        if (e === "INDICADOR1")
        {
            document.getElementById('titulo').innerHTML = "Kilos Producidos / Hora-Hombre ";
        }
        else if (e === "INDICADOR2")
        {
            document.getElementById('titulo').innerHTML = "Kilos Producidos / Kilovatio-Hora ";
        }
        else if (e === "INDICADOR3")
        {
            document.getElementById('titulo').innerHTML = "Kilos Producidos / MRS ";
        }
        else if (e === "INDICADOR4")
        {
            document.getElementById('titulo').innerHTML = "Costo Mo / Kg Producidos ";
        }
        else if (e === "INDICADOR5")
        {
            document.getElementById('titulo').innerHTML = "Costo KWH / Kg Producidos ";
        }
        else if (e === "INDICADOR6")
        {
            document.getElementById('titulo').innerHTML = "Costo MRS / Kg Producidos ";
        }
    }
}


function GetSubTituloG() {
    var mes = document.getElementById("mes").value;
    var anio = document.getElementById("anio").value;
    document.getElementById('subtitulo').innerHTML = get_nombre_mes(parseInt(mes)) + " " + anio;
}


function get_nombre_mes(mes) {
    var nmes = "";
    if (mes === 1) {
        nmes = "Enero";
    }
    if (mes === 2) {
        nmes = "Febrero";
    }
    if (mes === 3) {
        nmes = "Marzo";
    }
    if (mes === 4) {
        nmes = "Abril";
    }
    if (mes === 5) {
        nmes = "Mayo";
    }
    if (mes === 6) {
        nmes = "Junio";
    }
    if (mes === 7) {
        nmes = "Julio";
    }
    if (mes === 8) {
        nmes = "Agosto";
    }
    if (mes === 9) {
        nmes = "Septiembre";
    }
    if (mes === 10) {
        nmes = "Octubre";
    }
    if (mes === 11) {
        nmes = "Noviembre";
    }
    if (mes === 12) {
        nmes = "Diciembre";
    }
    return nmes;
}

function tamalinea ()
{
    return 5;
}

function tamapunto()
{
    return 9;
}

//Se devuelve null para no dibujar valores que sean "0" en la grafica.
function DevolverNull(valor)
{
    var a1 = valor;
    if (a1 === 0)
    {
        return null;
    }
    else
    {
        return valor;
    }
}


function ShowDef()
			{
			    document.getElementById("EmaliographyDef").style.display="block";
			}
			function HideDef()
			{
				document.getElementById("EmaliographyDef").style.display="none";
			}
                        
                        function ShowDef2()
			{
			    document.getElementById("GraficaPrincipal2").style.display="block";
			}
			function HideDef2()
			{
				document.getElementById("GraficaPrincipal2").style.display="none";
			}
                        
                        
                        
function DibujarChartPrincipal2(mes,anio) {
 $.ajax
            ({
                type: "POST",
                //Nombre del servlet de donde se reciben los datos en formato json.
                url: "I_003_Indicadores_Inventarios_Planta_Servlet",
                //Parametros leidos del jsp. anio y mes, parametros en enviados al servlet aniojs mesjs,opcion.         
                data: {
                    mesjs: mes,
                    aniojs: anio,
                    tipojs: $('#tipo2').val()
                },
                dataType: "json", //Se reciben los datos en formato JSON                
                success: function (data_) {


                    
                        //Colocar en el titulo "RSM" en lugar de "RSM O&M"
                        var planta = $('#opciones option:selected').val();
                        if (planta === "PLANTA RSM O&M" || planta === "PLANTA MRS O&M")
                            planta = "PLANTA RSM";
                        
                      

                        queryObject = eval('(' + JSON.stringify(data_) + ')');
                        queryObjectLen = queryObject.ListaValores.length;
                        var data = new google.visualization.DataTable();



                        data.addColumn('string', 'planta');

                        data.addColumn('number', 'Valor');
        

                        for (var i = 0; i < queryObjectLen; i++)
                        {
                            var planta = queryObject.ListaValores[i].planta;
                            var a1 = queryObject.ListaValores[i].valor;


                            a1=DevolverNull(a1);
                         
                            data.addRows([
                                [planta,parseFloat(a1)
                                ]
                            ]);
                        }

                       
                       var options = {
          title: 'My Daily Activities',
          is3D: true
        };;
                        

                        var chart = new google.visualization.PieChart(document.getElementById('GraficaPrincipal2'));
                        function ClickBarra() {
                            var selectedItem = chart.getSelection()[0];
                            if (selectedItem) {
                                
                            }
                        }
                        google.visualization.events.addListener(chart, 'select', ClickBarra);
                        chart.draw(data, options);
                    
                },
                error: function () {
                    //alert('No existen datos para los parametros elegidos');
                    //document.getElementById("mes").value = 2; //MesActual();
                    //location.reload();
                ShowDef2();
                }
            });
}