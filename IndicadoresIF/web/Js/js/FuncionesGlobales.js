
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
//año anterior= amarillo ,mayor/menor=gris, añoactual=negro, promedio= aqua
//
function Colores()
{
    var colors = ["#EAD008", "#848484", "#000000", "#01DFD7"];
    return colors;
}

function Coloresrrhh()
{
    var colors = ["#EAD008", "#848484", "#000000", "#01DFD7", "#FFB116"];
    return colors;
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

function MayorMenor()
{
    var e = document.getElementById("indicador").value;
    if (e === "INDICADOR1" || e === "INDICADOR2" || e === "INDICADOR3")
    {
        return "Mayor historico";
    }
    else
    {
        return "Menor historico";
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

function GetTituloG2() {
    var e = document.getElementById("indicador").value;

    var t = document.getElementById("tipo");
    var val = t.options[t.selectedIndex].value;

    if (val === "1") {
        if (e === "INDICADOR7")
        {
            document.getElementById('titulo').innerHTML = "Cantidad de empleados Nomina";
        }

        else if (e === "INDICADOR8")
        {
            document.getElementById('titulo').innerHTML = "Promedio Devengado por empleado (Nomina) ";
        }
    }
    else
    {

        if (e === "INDICADOR7")
        {
            document.getElementById('titulo').innerHTML = "Cantidad de empleados Planilla";
        }

        else if (e === "INDICADOR8")
        {
            document.getElementById('titulo').innerHTML = "Promedio Devengado por empleado (Planilla) ";
        }
    }
}

function GetSubTituloG2() {

    var anio = document.getElementById("anio").value;
    document.getElementById('subtitulo').innerHTML = anio;
}