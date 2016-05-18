

/*Se manejan los elementos del menu como una variable string en javascritp y se utiliza en los jsp's*/

var lines = '<div id=\'cssmenu\'>';

        lines += '<ul>';
        
            lines += '<li><a href=\'index.jsp\'><span>INICIO</span></a></li>';

/************************************************PRODUCCION********************************************/
            lines += ' <li class=\'active has-sub\'><a href=\'C_001_Produccion_Por_Planta.jsp\'><span>PRODUCCION</span></a>';
               
                lines += '<ul>';
                
                    lines += '<li class=\'has-sub\'><a href=\'I_001_IndicadoresProduccion.jsp?indicador=INDICADOR1\'><span>Kilos Producidos/Hora - Hombre</span></a>  </li>';

                    lines += '<li class=\'has-sub\'><a href=\'I_001_IndicadoresProduccion.jsp?indicador=INDICADOR2\'><span>Kilos Producidos/Kw-h</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'I_001_IndicadoresProduccion.jsp?indicador=INDICADOR3\'><span>Kilos Producidos/$MRS</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'I_001_IndicadoresProduccion.jsp?indicador=INDICADOR4\'><span>Costo Mo/Kg Producido</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'I_001_IndicadoresProduccion.jsp?indicador=INDICADOR5\'><span>Costo Kwh/Kg Producido</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'I_001_IndicadoresProduccion.jsp?indicador=INDICADOR6\'><span>Costo MRS/Kg Producido</span></a></li>';

                lines += '</ul>';
                
            lines += '</li>';

/***************************************************RRHH********************************************/
            lines += '<li class=\'active has-sub\'><a href=\'I_002_Indicadores_RRHH.jsp?indicador=INDICADOR7\'><span>RRHH</span></a>';
                
                lines += '<ul>';

                    lines += '<li class=\'has-sub\'><a href=\'I_002_Indicadores_RRHH.jsp?indicador=INDICADOR7\'><span>Cantidad de Empleados</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'I_002_Indicadores_RRHH.jsp?indicador=INDICADOR8\'><span>Devengado / No. Empleados</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Tabla de reemplazos</span></a></li>';

                lines += '</ul>';
                
            lines += '</li>';

/********************************************Calidad********************************************/

            lines += '<li class=\'active has-sub\'><a href=\'C_005_Indicadores_Calidad.jsp\'><span>CALIDAD</span></a>';

                lines += '<ul>';

                    lines += '<li class=\'has-sub\'><a href=\'I_005_Indicadores_Calidad.jsp?indicador=1\'><span>Calidad Facturable</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'I_005_Indicadores_Calidad.jsp?indicador=2\'><span>Calidad No Facturable</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'I_005_Indicadores_Calidad.jsp?indicador=3\'><span>SubProducto</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Materia Prima Calidad H</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Reprocesos DPF</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Tiempo Reprocesos DPF</span></a></li>';

                lines += '</ul>';
                
            lines += '</li>';

/****************************************VENTAS********************************************/

            lines += '<li class=\'active has-sub\'><a href=\'#\'><span>VENTAS</span></a>';

                lines += '<ul>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Costo total por kilo vendida</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Entrega a tiempo mes, cliente, PO</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Ingresos totales</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Volumen de Ventas total, cliente, cuenta</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Satisfacción de cliente</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Clientes Perdidos</span></a></li>';

                lines += '</ul>';
                
            lines += '</li>';

/************************************************FINANCIEROS********************************************/
            lines += '<li class=\'active has-sub\'><a href=\'C_004_Indicadores_Financieros.jsp\'><span>FINANCIEROS</span></a>';

                lines += '<ul>';
                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Indices De Liquidez</span></a>';                   
                        lines += '<ul>';                       
                            lines += '<li><a href=\'I_004_Indicadores_Financieros.jsp?indicador=1\'><span>Liquidez Corriente</span></a></li>';
                            lines += '<li class=\'last\'><a href=\'I_004_Indicadores_Financieros.jsp?indicador=2\'><span>Prueba Acida</span></a></li>';                          
                        lines += '</ul>';                   
                    lines += '</li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Indices De Endeudamiento</span></a>'; 
                        lines += '<ul>';   
                            lines += '<li><a href=\'I_004_Indicadores_Financieros.jsp?indicador=3\'><span>Apalancamiento Financiero</span></a></li>';
                            lines += '<li class=\'last\'><a href=\'I_004_Indicadores_Financieros.jsp?indicador=4\'><span>Indice de Endeudamiento</span></a></li>';                            
                        lines += '</ul>';
                    lines += '</li>';


                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Indices De Rentabilidad</span></a>';
                        lines += '<ul>';
                            lines += '<li><a href=\'I_004_Indicadores_Financieros.jsp?indicador=5\'><span>Rentabilidad Neta del Activo</span></a></li>';
                            lines += '<li><a href=\'I_004_Indicadores_Financieros.jsp?indicador=6\'><span>Margen Bruto</span></a></li>';
                            lines += '<li><a href=\'I_004_Indicadores_Financieros.jsp?indicador=7\'><span>Margen Operacional</span></a></li>';
                            lines += '<li class=\'last\'><a href=\'I_004_Indicadores_Financieros.jsp?indicador=8\'><span>Rendimiento sobre Activos Totales</span></a></li>';
                        lines += '</ul>';
                    lines += '</li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Indices De Gestion</span></a>';
                        lines += '<ul>';
                            lines += '<li><a href=\'I_004_Indicadores_Financieros.jsp?indicador=9\'><span>Impacto de Gastos Administrativos y Ventas</span></a></li>';
                            lines += '<li class=\'last\'><a href=\'I_004_Indicadores_Financieros.jsp?indicador=10\'><span>Impacto Carga Financiera</span></a></li>';
                        lines += '</ul>';
                    lines += '</li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Monto pagado por multas</span></a>';
                        lines += '<ul>';
                            lines += '<li><a href=\'I_004_Indicadores_Financieros.jsp?indicador=11\'><span>Total pagado por multas y retificaciones</span></a></li>';
                        lines += '</ul>';
                    lines += '</li>';

                lines += '</ul>';
                
            lines += '</li>';

/********************************************INVENTARIOS********************************************/

            lines += '<li class=\'active has-sub\'><a href=\'C_003_Indicadores_Inventarios.jsp\'><span>INVENTARIOS</span></a>';
                
                lines += '<ul>';

                    lines += '<li class=\'has-sub\'><a href=\'I_003_Indicadores_Inventarios.jsp\'><span>Rotacion De Inventarios</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Rotacion de Inventarios financiero</span></a></li>';

                lines += '</ul>';
                
            lines += '</li>';

/********************************************TRANSPORTE********************************************/

            lines += '<li class=\'active has-sub\'><a href=\'#\'><span>TRANSPORTE</span></a>';

                lines += '<ul>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Kilometros por galon</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Mano de obra pagada</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Costo por kilos transportados</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Costo por docenas transportadas</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Costo por viaje</span></a></li>';

                lines += '</ul>';

            lines += '</li>';

/********************************************SEGURIDAD INDUSTRIAL********************************************/

            lines += '<li class=\'active has-sub\'><a href=\'#\'><span>SEGURIDAD INDUSTRIAL</span></a>';

                lines += '<ul>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Incidentes Por Mes</span></a></li>';

                    lines += '<li class=\'has-sub\'><a href=\'#\'><span>Tiempo desde el ultimo incidente</span></a></li>';

                lines += '</ul>';
                    
            lines += '</li>';


        lines += '</ul>'; //FIN LISTADO DE ITEMS DEL MENU
        
lines += ' </div>';

document.write(lines);

