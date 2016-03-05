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
        return "select PLANTA,sum(VALOR) as VALOR FROM I_000_ProduccionPlantaMes  where anio='"+anio+"' and mes='"+mes+"' group by PLANTA";
    }
    
    public static String I_000_Produccion_Por_Planta_Mes_02 (String planta,String anio, String mes)
    {
        return "select COMMODITY_CODE,sum(valor) as VALOR From I_000_ProduccionPlantaCommodity\n"
                + "where mes='" + mes + "'\n"
                + "and anio ='" + anio + "'\n"
                + "and planta='" + planta + "'\n"
                + "group by COMMODITY_CODE,mes,anio";
    }
    /*---------------------------------------------------------------------------------------------*/

        /*INDICADOR 003 - Costo energia Q*/
    /*----------------------------------------------------------------------------------------------*/
    public static String I_003_Costo_Energia_Q(String anio) {
        return "SELECT  P.PLANTA as 'PLANTA',\n"
                + "    SUM(\n"
                + "        CASE \n"
                + "            WHEN P.MES=1\n"
                + "            THEN P.ConsumoQ\n"
                + "            ELSE NULL \n"
                + "        END\n"
                + "    ) AS '1',\n"
                + "    SUM(\n"
                + "        CASE \n"
                + "            WHEN P.MES=2\n"
                + "            THEN P.ConsumoQ\n"
                + "            \n"
                + "        END\n"
                + "    ) AS '2',\n"
                                + "    SUM(\n"
                + "        CASE \n"
                + "            WHEN P.MES=3\n"
                + "            THEN P.ConsumoQ\n"
                + "            ELSE 0 \n"
                + "        END\n"
                + "    ) AS '3'\n"
                
                + "FROM   I_003_CostoEnergia P\n"
                + "where P.ANIO="+anio+" \n"
                + "GROUP BY P.PLANTA";
    }
    
        public static String I_003_Costo_Energia_KWH(String anio) {
        return "SELECT  P.PLANTA as 'PLANTA',\n"
                + "    SUM(\n"
                + "        CASE \n"
                + "            WHEN P.MES=1\n"
                + "            THEN P.Consumo_kwh\n"
                + "            ELSE NULL \n"
                + "        END\n"
                + "    ) AS '1',\n"
                + "    SUM(\n"
                + "        CASE \n"
                + "            WHEN P.MES=2\n"
                + "            THEN P.Consumo_kwh\n"
                + "            \n"
                + "        END\n"
                + "    ) AS '2'\n"
                + "FROM   I_003_CostoEnergia P\n"
                + "where P.ANIO="+anio+" \n"
                + "GROUP BY P.PLANTA";
    }

}
