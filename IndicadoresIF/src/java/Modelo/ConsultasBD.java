/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/*
 * @author rcruz
 */

//Consultas que traen informacion de la BD para los indicadores.

public class ConsultasBD {
    
    /*INDICADOR 000 - PRODUCCION POR PLANTA 
    ----------------------------------------------------------------------------------------------
    */
   
    public static String I_000_Produccion_Por_Planta_Mes_01 (String anio, String mes)
    { 
        return "select PLANTA,sum(VALOR) as VALOR FROM ProduccionPlantaMes  where anio='"+anio+"' and mes='"+mes+"' group by PLANTA";
    }
    
    public static String I_000_Produccion_Por_Planta_Mes_02 (String planta,String anio, String mes)
    {
        return "select COMMODITY_CODE,sum(valor) as VALOR From ProduccionPlantaCommodity\n"
                + "where mes='" + mes + "'\n"
                + "and anio ='" + anio + "'\n"
                + "and planta='" + planta + "'\n"
                + "group by COMMODITY_CODE,mes,anio";
    }
    /*---------------------------------------------------------------------------------------------*/
}
