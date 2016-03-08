/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//Genera un color de manera aleatoria para colocar a cada elemento de la grafica de barras.
function GenerarColorRandom()
{
    return '#' + (0x1000000 + (Math.random()) * 0xffffff).toString(16).substr(1, 6);
}

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

function MesActual()
{
    var d = new Date();
    var n = d.getMonth();
    return n + 1;
}

function AnioActual()
{
    var d = new Date();
    var n = d.getFullYear();
    return n;
}
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

