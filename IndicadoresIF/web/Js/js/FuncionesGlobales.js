
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
        return "Enero";
    }
    else if (mes === "2") {
        return "Febrero";
    }
    else if (mes === "3") {
        return "Marzo";
    }
    else if (mes === "4") {
        return "Abril";
    }
    else if (mes === "5") {
        return "Mayo";
    }
    else if (mes === "6") {
        return "Junio";
    }
    else if (mes === "7") {
        return "Julio";
    }
    else if (mes === "8") {
        return "Agosto";
    }
    else if (mes === "9") {
        return "Septiembre";
    }
    else if (mes === "10") {
        return "Octubre";
    }
    else if (mes === "11") {
        return "Noviembre";
    }
    else if (mes === "12") {
        return "Diciembre";
    }

}


//Colores elegidos a utilizar en todas las graficas 
//amarillo,verde,negro,aqua
function Colores()
{
    var colors = ["#FFFF00", "#00FF00", "#000000", "#01DFD7"];
    return colors;
}


/*------------------------------------funciones tablas de indicadores------------------------------*/

var color = ''; //obtiene el color actaul de la celda de la tabla
//Funcion que cambia de color una celda de una tabla 
function CambiarColorCelda(x) {
    var rows = document.getElementById('table').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
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
function ColorOriginalCelda(x) {
    var rows = document.getElementById('table').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
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
    if (val === "ALL")
    {
        document.getElementById('mes').style.display = "block";
        document.getElementById('lblmes').style.display = "block";
    }
    else
    {
        document.getElementById('mes').style.display = "none";
        document.getElementById('lblmes').style.display = "none";
    }
}