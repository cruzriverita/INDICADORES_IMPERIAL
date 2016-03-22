
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

 function Colores()
    {
        var cars = ["#000000", "#FFFF00", "#33CCCC","#000000", "#FFFF00", "#33CCCC"];
        return cars;
    }