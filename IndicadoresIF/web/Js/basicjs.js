/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
        preventItemClick: false //Habilitar el click en el menu.
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
                name: 'Produccion',
                icon: 'fa fa-adjust',
                link: '#',
                items: [
                    {/*TITULO HIJO*/
                        title: 'Produccion',
                        icon: 'fa fa-adjust',
                        items: [
                            /*-----------------------------------NIETOS---------------------------*/
                            {
                                name: 'Produccion Por Planta',
                                link: 'I_000_Produccion_Por_Planta_Mes.jsp'
                            },
                            {
                                name: 'Costo Mano de obra',
                                link: '#'
                            },
                            {
                                name: 'Costo Energia',
                                link: '#'
                            },
                            {
                                name: 'Costo MRS',
                                link: '#'
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
                name: 'Calidad',
                icon: 'fa fa-calendar-check-o',
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
            {
                name: 'RRHH',
                icon: 'fa fa-shopping-cart',
                link: '#',
                items: [
                    {
                        title: 'RRHH',
                        icon: 'fa fa-shopping-cart',
                        items: [
                            {
                                name: 'Rotacion De Personal',
                                link: '#'
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
                icon: 'fa fa-shopping-cart',
                link: '#',
                items: [
                    {
                        title: 'FINANCIEROS',
                        icon: 'fa fa-shopping-cart',
                        items: [
                            {
                                name: 'Indices De Liquidez',
                                icon: 'fa fa-shopping-cart',
                                link: '#',
                                items: [
                                    {
                                        title: 'Indices De Liquidez',
                                        icon: 'fa fa-shopping-cart',
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
                                icon: 'fa fa-shopping-cart',
                                link: '#',
                                items: [
                                    {
                                        title: 'Indices De Endeudamiento',
                                        icon: 'fa fa-shopping-cart',
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
                                icon: 'fa fa-shopping-cart',
                                link: '#',
                                items: [
                                    {
                                        title: 'Indices De Rentabilidad',
                                        icon: 'fa fa-shopping-cart',
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
                                icon: 'fa fa-shopping-cart',
                                link: '#',
                                items: [
                                    {
                                        title: 'Indices De Gestion',
                                        icon: 'fa fa-shopping-cart',
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
                                icon: 'fa fa-shopping-cart',
                                link: '#',
                                items: [
                                    {
                                        title: 'Monto pagado por multas',
                                        icon: 'fa fa-shopping-cart',
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
                icon: 'fa fa-shopping-cart',
                link: '#',
                items: [
                    {
                        title: 'INVENTARIOS',
                        icon: 'fa fa-shopping-cart',
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
                                icon: 'fa fa-shopping-cart',
                                link: '#',
                                items: [
                                    {
                                        title: 'TRANSPORTE',
                                        icon: 'fa fa-shopping-cart',
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
                                icon: 'fa fa-shopping-cart',
                                link: '#',
                                items: [
                                    {
                                        title: 'SEGURIDAD INDUSTRIAL',
                                        icon: 'fa fa-shopping-cart',
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