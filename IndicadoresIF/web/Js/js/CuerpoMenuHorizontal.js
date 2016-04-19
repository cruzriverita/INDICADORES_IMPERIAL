/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var lines = '<div id=\'cssmenu\'>';
lines += '        <ul>';
lines += '            <li><a href=\'index.jsp\'><span>INICIO</span></a></li>';

/************************************************PRODUCCION********************************************/
lines += '            <li class=\'active has-sub\'><a href=\'C_001_Produccion_Por_Planta.jsp\'><span>PRODUCCION</span></a>';

lines += '              <ul>';
lines += '              <li class=\'has-sub\'><a href=\'I_001_IndicadoresProduccion.jsp?indicador=INDICADOR1\'><span>Kilos Producidos/Hora - Hombre</span></a>';
//lines += '              <ul>';
//lines += '                    <li><a href=\'#\'><span>Sub Product</span></a></li>';
//lines += '                    <li class=\'last\'><a href=\'#\'><span>Sub Product</span></a></li>';
//lines += '              </ul>';

lines += '            </li>';
lines += '            <li class=\'has-sub\'><a href=\'I_001_IndicadoresProduccion.jsp?indicador=INDICADOR2\'><span>Kilos Producidos/Kw-h</span></a>';
//lines += '                <ul>';
//lines += '                    <li><a href=\'#\'><span>Sub Product</span></a></li>';
//lines += '                    <li class=\'last\'><a href=\'#\'><span>Sub Product</span></a></li>';
//lines += '                </ul>';
lines += '            </li>';

lines += '            <li class=\'has-sub\'><a href=\'I_001_IndicadoresProduccion.jsp?indicador=INDICADOR3\'><span>Kilos Producidos/$MRS</span></a>';
lines += '            </li>';
lines += '            <li class=\'has-sub\'><a href=\'I_001_IndicadoresProduccion.jsp?indicador=INDICADOR4\'><span>Costo Mo/Kg Producido</span></a>';
lines += '            </li>';
lines += '            <li class=\'has-sub\'><a href=\'I_001_IndicadoresProduccion.jsp?indicador=INDICADOR5\'><span>Costo Kwh/Kg Producido</span></a>';
lines += '            </li>';
lines += '            <li class=\'has-sub\'><a href=\'I_001_IndicadoresProduccion.jsp?indicador=INDICADOR6\'><span>Costo MRS/Kg Producido</span></a>';
lines += '            </li>';
lines += '        </ul>';
lines += '            </li>';

/***************************************************RRHH********************************************/
lines += '            <li class=\'active has-sub\'><a href=\'#\'><span>RRHH</span></a>';
lines += '              <ul>';

lines += '            <li class=\'has-sub\'><a href=\'I_002_Indicadores_RRHH.jsp?indicador=INDICADOR7\'><span>Cantidad de Empleados</span></a>';
lines += '            </li>';

lines += '            <li class=\'has-sub\'><a href=\'I_002_Indicadores_RRHH.jsp?indicador=INDICADOR8\'><span>Devengado / No. Empleados</span></a>';
lines += '            </li>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Tabla de reemplazos</span></a>';
lines += '            </li>';

lines += '        </ul>';
lines += '            </li>';

/********************************************Calidad********************************************/

lines += '            <li class=\'active has-sub\'><a href=\'#\'><span>CALIDAD</span></a>';
lines += '              <ul>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Calidad Facturable</span></a>';
lines += '            </li>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Calidad No Facturable</span></a>';
lines += '            </li>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>SubProducto</span></a>';
lines += '            </li>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Materia Prima Calidad H</span></a>';
lines += '            </li>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Reprocesos DPF</span></a>';
lines += '            </li>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Tiempo Reprocesos DPF</span></a>';
lines += '            </li>';

lines += '        </ul>';
lines += '            </li>';

/****************************************VENTAS********************************************/

lines += '            <li class=\'active has-sub\'><a href=\'#\'><span>VENTAS</span></a>';
lines += '              <ul>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Costo total por kilo vendida</span></a>';
lines += '            </li>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Entrega a tiempo mes, cliente, PO</span></a>';
lines += '            </li>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Ingresos totales</span></a>';
lines += '            </li>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Volumen de Ventas total, cliente, cuenta</span></a>';
lines += '            </li>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Satisfacción de cliente</span></a>';
lines += '            </li>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Clientes Perdidos</span></a>';
lines += '            </li>';

lines += '        </ul>';
lines += '            </li>';

/************************************************FINANCIEROS********************************************/
lines += '            <li class=\'active has-sub\'><a href=\'#\'><span>FINANCIEROS</span></a>';

lines += '              <ul>';

lines += '              <li class=\'has-sub\'><a href=\'#\'><span>Indices De Liquidez</span></a>';
lines += '              <ul>';
lines += '                    <li><a href=\'#\'><span>Liquidez Corriente</span></a></li>';
lines += '                    <li class=\'last\'><a href=\'#\'><span>Prueba Acida</span></a></li>';
lines += '              </ul>';
lines += '            </li>';


lines += '              <li class=\'has-sub\'><a href=\'#\'><span>Indices De Endeudamiento</span></a>';
lines += '              <ul>';
lines += '                    <li><a href=\'#\'><span>Apalancamiento Financiero</span></a></li>';
lines += '                    <li class=\'last\'><a href=\'#\'><span>Indice de Endeudamiento</span></a></li>';
lines += '              </ul>';
lines += '            </li>';


lines += '              <li class=\'has-sub\'><a href=\'#\'><span>Indices De Rentabilidad</span></a>';
lines += '              <ul>';
lines += '                    <li><a href=\'#\'><span>Rentabilidad Neta del Activo</span></a></li>';
lines += '                    <li class=\'last\'><a href=\'#\'><span>Margen Bruto</span></a></li>';
lines += '                    <li><a href=\'#\'><span>Margen Operacional</span></a></li>';
lines += '                    <li class=\'last\'><a href=\'#\'><span>Rendimiento sobre Activos Totales</span></a></li>';
lines += '              </ul>';
lines += '            </li>';

lines += '              <li class=\'has-sub\'><a href=\'#\'><span>Indices De Gestion</span></a>';
lines += '              <ul>';
lines += '                    <li><a href=\'#\'><span>Impacto de Gastos Administrativos y Ventas</span></a></li>';
lines += '                    <li class=\'last\'><a href=\'#\'><span>Impacto Carga Financiera</span></a></li>';
lines += '              </ul>';
lines += '            </li>';

lines += '              <li class=\'has-sub\'><a href=\'#\'><span>Monto pagado por multas</span></a>';
lines += '              <ul>';
lines += '                    <li><a href=\'#\'><span>Total pagado por multas y retificaciones</span></a></li>';
lines += '              </ul>';
lines += '            </li>';

lines += '        </ul>';
lines += '            </li>';

/********************************************INVENTARIOS********************************************/

lines += '            <li class=\'active has-sub\'><a href=\'#\'><span>INVENTARIOS</span></a>';
lines += '              <ul>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Rotacion De Inventarios</span></a>';
lines += '            </li>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Rotacion de Inventarios financiero</span></a>';
lines += '            </li>';

lines += '        </ul>';
lines += '            </li>';

/********************************************TRANSPORTE********************************************/

lines += '            <li class=\'active has-sub\'><a href=\'#\'><span>TRANSPORTE</span></a>';
lines += '              <ul>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Kilometros por galon</span></a>';
lines += '            </li>';
lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Mano de obra pagada</span></a>';
lines += '            </li>';
lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Costo por kilos transportados</span></a>';
lines += '            </li>';
lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Costo por docenas transportadas</span></a>';
lines += '            </li>';
lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Costo por viaje</span></a>';
lines += '            </li>';

lines += '        </ul>';
lines += '            </li>';

/********************************************SEGURIDAD INDUSTRIAL********************************************/

lines += '            <li class=\'active has-sub\'><a href=\'#\'><span>SEGURIDAD INDUSTRIAL</span></a>';
lines += '              <ul>';

lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Incidentes Por Mes</span></a>';
lines += '            </li>';
lines += '            <li class=\'has-sub\'><a href=\'#\'><span>Tiempo desde el ultimo incidente</span></a>';
lines += '            </li>';


lines += '        </ul>';
lines += '            </li>';


lines += '</ul>'; //FIN LISTADO DE ITEMS DEL MENU
lines += ' </div>';
document.write(lines);

