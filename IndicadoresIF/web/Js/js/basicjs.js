//ITEMS DEL MENU EN JS

$(document).ready(function () {
    // JS Array implementation, overlap mode
    $('#menu').multilevelpushmenu({
        menu: arrMenu,
        containersToPush: [$('#pushobj')],
        // Just for fun also changing the look of the menu
        wrapperClass: 'mlpm_w',
        menuInactiveClass: 'mlpm_inactive',
        menuWidth: '100%',
        menuHeight: '100%',
        backText: 'Regresar',
        overlapWidth: 28, //tamaño de las pestañas del menu
        preventItemClick: false //Habilitar el click en el menu.
       
    });
   
    $(window).resize(function () {
        $('#menu').multilevelpushmenu('redraw');
    });


});

// JS Aray instead HTML Markup

var arrMenu = [
    {
        /*----------------------------------------PADRE---------------------------------------------*/
        title: 'INDICADORES IMPERIAL',
        icon: 'fa fa-reorder',
        items: [
            /*HIJOS*/
            /*---------------------------------------PRODUCCION-------------------------------------*/
            
            {
                name: 'PRODUCCION',
                icon: 'fa fa-gears',
                link: '#',
                items: [
                    {/*TITULO HIJO*/
                        title: 'PRODUCCION',
                        icon: 'fa fa-gears',
                        items: [
                            /*-----------------------------------NIETOS---------------------------*/

                            {
                                name: 'Produccion Por Planta',
                                icon: '',
                                link: 'C_001_Produccion_Por_Planta.jsp',
                                items: [
                                    {
                                        title: 'Produccion Por Planta',
                                        icon: '',
                                        items: [
                                            
                                             {
                                                name:'Indicadores Produccion',
                                                link:'C_001_Produccion_Por_Planta.jsp'
                                                
                                            },
                                            
                                            {
                                                name: 'Kilos Producidos/Hora - Hombre',
                                                link: 'I_001_Kilos_Producidos_Hora_Hombre.jsp'

                                            },
                                            {
                                                name: 'Kilos Producidos/Kw-h',
                                                link: 'I_002_Kg_Producidos_Kwh.jsp'

                                            },
                                            {
                                                name: 'Kilos Producidos/$MRS',
                                                link: 'I_003_KgProducidos_MRS.jsp'

                                            },
                                            {
                                                name: 'Costo Mo/Kg Producido',
                                                link: 'I_004_CostoMo_KgProducido.jsp'
                                            },
                                            {
                                                name: 'Costo Kwh/Kg Producido',
                                                link: 'I_005_CostoKWH_KgProducido.jsp'
                                            },
                                           {
                                                name: 'Costo MRS/Kg Producido',
                                                link: 'I_006_CostoMRS_KgProducido.jsp'
                                            }



                                        ]
                                    }
                                ]

                            },
                           
                         
                            {
                                name: 'Total Costos Directos',
                                link: '#'
                            },
                            {
                                name: 'Total Costos Indirectos',
                                link: '#'
                            },
                            {
                                name: 'Rendimiento de materia prima',
                                link: '#'
                            },
                            {
                                name: 'Costo mano de obra por kilos producidos calidad A',
                                link: '#'
                            },
                            {
                                name: 'Kilos producidos por hora/hombre',
                                link: '#'
                            },
                            {
                                name: 'personal directo de planta',
                                link: '#'
                            },
                            {
                                name: 'Costo de materia Prima',
                                link: '#'
                            },
                            {
                                name: 'WL kilos',
                                link: '#'
                            },
                            {
                                name: 'Reposiciones',
                                link: '#'
                            },
                            {
                                name: 'Entregas internas en tiempo',
                                link: '#'
                            }
                            //AQUI AGREGAR NUEVO ITEM
                        ]
                    }
                ]
            },
            /*---------------------------------------CALIDAD-------------------------------------*/
            {
                name: 'CALIDAD',
                icon: 'fa fa-pie-chart',
                link: '#',
                items: [
                    {
                        title: 'Calidad',
                        icon: 'fa fa-calendar-check-o',
                        items: [
                            {
                                name: 'Calidad Facturable',
                                link: '#'
                            },
                            {
                                name: 'Calidad No Facturable',
                                link: '#'
                            },
                            {
                                name: 'SubProducto',
                                link: '#'
                            },
                            {
                                name: 'Materia Prima Calidad H',
                                link: '#'
                            },
                            {
                                name: 'Reprocesos DPF',
                                link: '#'
                            },
                            {
                                name: 'Tiempo Reprocesos DPF',
                                link: '#'
                            },
                            {
                                name: 'Defectos más repetitivos',
                                link: '#'
                            }
                        ]
                    }
                ]
            },
            /*-------------------------------------------RRHH-----------------------------------*/
            {
                name: 'RRHH',
                icon: 'fa fa-group',
                link: '#',
                items: [
                    {
                        title: 'RRHH',
                        icon: 'fa fa-group',
                        items: [
                            {
                                name: 'Numero de Empleados',
                                link: 'I_007_Numero_De_Empleados.jsp'
                            },
                            {
                                name: 'Encuesta De Salida',
                                link: '#'
                            },
                            {
                                name: 'Tabla de reemplazos',
                                link: '#'
                            },
                            {
                                name: 'Total del cumplimiento de las actividades descritas en la política del Buen Vecino',
                                link: '#'
                            }
                        ]
                    }
                ]
            },
            /*-------------------------------------------VENTAS-----------------------------------*/
            {
                name: 'VENTAS',
                icon: 'fa fa-shopping-cart',
                link: '#',
                items: [
                    {
                        title: 'VENTAS',
                        icon: 'fa fa-shopping-cart',
                        items: [
                            {
                                name: 'Costo total por kilo vendida',
                                link: '#'
                            },
                            {
                                name: 'Entrega a tiempo mes, cliente, PO',
                                link: '#'
                            },
                            {
                                name: 'Ingresos totales',
                                link: '#'
                            },
                            {
                                name: 'Volumen de Ventas total, cliente, cuenta',
                                link: '#'
                            },
                            {
                                name: 'Satisfacción de cliente',
                                link: '#'
                            },
                            {
                                name: 'Clientes Perdidos',
                                link: '#'
                            }
                        ]
                    }
                ]
            },
            {
                name: 'FINANCIEROS',
                icon: 'fa fa-area-chart',
                link: '#',
                items: [
                    {
                        title: 'FINANCIEROS',
                        icon: 'fa fa-area-chart',
                        items: [
                            {
                                name: 'Indices De Liquidez',
                                icon: 'fa fa-line-chart',
                                link: '#',
                                items: [
                                    {
                                        title: 'Indices De Liquidez',
                                        icon: 'fa fa-line-chart',
                                        items: [
                                            {
                                                name: 'Liquidez Corriente',
                                                link: '#'

                                            },
                                            {
                                                name: 'Prueba Acida',
                                                link: '#'

                                            }



                                        ]
                                    }
                                ]

                            },
                            {
                                name: 'Indices De Endeudamiento',
                                icon: 'fa fa-line-chart',
                                link: '#',
                                items: [
                                    {
                                        title: 'Indices De Endeudamiento',
                                        icon: 'fa fa-line-chart',
                                        items: [
                                            {
                                                name: 'Apalancamiento Financiero',
                                                link: '#'

                                            },
                                            {
                                                name: 'Indice de Endeudamiento',
                                                link: '#'

                                            }



                                        ]
                                    }
                                ]

                            },
                            {
                                name: 'Indices De Rentabilidad',
                                icon: 'fa fa-line-chart',
                                link: '#',
                                items: [
                                    {
                                        title: 'Indices De Rentabilidad',
                                        icon: 'fa fa-line-chart',
                                        items: [
                                            {
                                                name: 'Rentabilidad Neta del Activo',
                                                link: '#'

                                            },
                                            {
                                                name: 'Margen Bruto',
                                                link: '#'

                                            },
                                            {
                                                name: 'Margen Operacional',
                                                link: '#'

                                            },
                                            {
                                                name: 'Rendimiento sobre Activos Totales',
                                                link: '#'

                                            }



                                        ]
                                    }
                                ]

                            },
                            {
                                name: 'Indices De Gestion',
                                icon: 'fa fa-line-chart',
                                link: '#',
                                items: [
                                    {
                                        title: 'Indices De Gestion',
                                        icon: 'fa fa-line-chart',
                                        items: [
                                            {
                                                name: 'Impacto de Gastos Administrativos y Ventas',
                                                link: '#'

                                            },
                                            {
                                                name: 'Impacto Carga Financiera',
                                                link: '#'

                                            }
                                        ]
                                    }
                                ]

                            },
                            {
                                name: 'Monto pagado por multas',
                                icon: 'fa fa-line-chart',
                                link: '#',
                                items: [
                                    {
                                        title: 'Monto pagado por multas',
                                        icon: 'fa fa-line-chart',
                                        items: [
                                            {
                                                name: 'Total pagado por multas y retificaciones',
                                                link: '#'

                                            }
                                        ]
                                    }
                                ]

                            }



                        ]
                    }
                ]
            },
            {
                name: 'INVENTARIOS',
                icon: 'fa fa-file-pdf-o',
                link: '#',
                items: [
                    {
                        title: 'INVENTARIOS',
                        icon: 'fa fa-file-pdf-o',
                        items: [
                            {
                                name: 'Rotacion De Inventarios',
                                link: '#'
                            },
                            {
                                name: 'Dias de Inventario',
                                link: '#'
                            },
                            {
                                name: 'Rotacion de Inventarios financiero',
                                link: '#'
                            }


                        ]
                    }
                ]
            },
            {
                name: 'TRANSPORTE',
                icon: 'fa fa-truck',
                link: '#',
                items: [
                    {
                        title: 'TRANSPORTE',
                        icon: 'fa fa-truck',
                        items: [
                            {
                                name: 'Kilometros por galon',
                                link: '#'

                            },
                            {
                                name: 'Mano de obra pagada',
                                link: '#'

                            },
                            {
                                name: 'Costo por kilos transportados',
                                link: '#'

                            },
                            {
                                name: 'Costo por docenas transportadas',
                                link: '#'

                            },
                            {
                                name: 'Costo por viaje',
                                link: '#'

                            }



                        ]
                    }
                ]

            },
            {
                name: 'SEGURIDAD INDUSTRIAL',
                icon: 'fa fa-industry',
                link: '#',
                items: [
                    {
                        title: 'SEGURIDAD INDUSTRIAL',
                        icon: 'fa fa-industry',
                        items: [
                            {
                                name: 'Incidentes Por Mes',
                                link: '#'

                            },
                            {
                                name: 'Tiempo desde el ultimo incidente',
                                link: '#'

                            }
                        ]
                    }
                ]

            }

        ]
    }
];