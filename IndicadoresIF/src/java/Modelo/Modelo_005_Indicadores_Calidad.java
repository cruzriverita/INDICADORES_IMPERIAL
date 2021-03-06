/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author rcruz
 */
public class Modelo_005_Indicadores_Calidad {

    public static String Consulta_Tabla_Indicadores_Calidad(int anio, String mes) {
        return "Select \n"
                + "ambito, \n"
                + "indicador,\n"
                + "sum(case when planta ='RST'   then valor else 0 end) as 'RST',\n"
                + "sum(case when planta ='RSM'  then valor else 0 end) as 'RSM',\n"
                + "sum(case when planta ='RLRS' then valor else 0 end) as 'RLRS',\n"
                
                + "sum(case when planta ='KNIT' then valor else 0 end) as 'KNIT',\n"
                + "sum(case when planta ='DPF'  then valor else 0 end) as 'DPF',\n"
                + "sum(case when planta ='FPS'  then valor else 0 end) as 'FPS',\n"
                + "\n"
                + "ifnull((select sum(valor)/count(valor) from I_011_Indicadores_Calidad where planta ='RS'\n"
                + "	    and indicador = P.indicador and ambito = P.ambito AND ANIO=" + (anio - 1) + "),0) AS 'PRS',\n"
                + "        \n"
                + "ifnull((select sum(valor)/count(valor) from I_011_Indicadores_Calidad where planta ='RSM'\n"
                + "	    and indicador = P.indicador and ambito = P.ambito AND ANIO=" + (anio - 1) + "),0) AS 'PRSM',\n"
                + "        \n"
                + "ifnull((select sum(valor)/count(valor) from I_011_Indicadores_Calidad where planta ='RLRS'\n"
                + "	    and indicador = P.indicador and ambito = P.ambito AND ANIO=" + (anio - 1) + "),0) AS 'PRLRS',\n"
                + "        \n"
                + "ifnull((select sum(valor)/count(valor) from I_011_Indicadores_Calidad where planta ='FPS'\n"
                + "	    and indicador = P.indicador and ambito = P.ambito AND ANIO=" + (anio - 1) + "),0) AS 'PFPS',\n"
                + "        \n"
                + "ifnull((select sum(valor)/count(valor) from I_011_Indicadores_Calidad where planta ='KNIT'\n"
                + "	    and indicador = P.indicador and ambito = P.ambito AND ANIO=" + (anio - 1) + "),0) AS 'PKNIT',\n"
                + "        \n"
                + "ifnull((select sum(valor)/count(valor) from I_011_Indicadores_Calidad where planta ='DPF'\n"
                + "	    and indicador = P.indicador and ambito = P.ambito AND ANIO=" + (anio - 1) + "),0) AS 'PDPF'\n"
                + "        \n"
                + "from I_011_Indicadores_Calidad P\n"
                + "where anio = " + (anio) + " and mes =" + mes + " and ambito='I' \n"
                + "GROUP BY ambito,indicador \n"
                //+ "ORDER BY ambito desc ";
                + "UNION ALL \n"
                + "Select \n"
                + "ambito, \n"
                + "'PT',\n"
                + "sum(case when planta ='RST'   then valor else 0 end) as 'RST',\n"
                + "sum(case when planta ='RSM'  then valor else 0 end) as 'RSM',\n"
                + "sum(case when planta ='RLRS' then valor else 0 end) as 'RLRS',\n"
                
                + "sum(case when planta ='KNIT' then valor else 0 end) as 'KNIT',\n"
                + "sum(case when planta ='DPF'  then valor else 0 end) as 'DPF',\n"
                + "sum(case when planta ='FPS'  then valor else 0 end) as 'FPS',\n"
                + "0,0,0,0,0,0\n"
                + "from I_011_Indicadores_Calidad P\n"
                + "where anio = " + (anio) + " and mes =" + mes + " and ambito='I' \n"
                + "AND ( indicador='CF' OR indicador='CNF') "
                + "UNION ALL \n"
                + "Select \n"
                + "ambito, \n"
                + "indicador,\n"
                + "sum(case when planta ='RST'   then valor else 0 end) as 'RST',\n"
                + "sum(case when planta ='RSM'  then valor else 0 end) as 'RSM',\n"
                + "sum(case when planta ='RLRS' then valor else 0 end) as 'RLRS',\n"
                
                + "sum(case when planta ='KNIT' then valor else 0 end) as 'KNIT',\n"
                + "sum(case when planta ='DPF'  then valor else 0 end) as 'DPF',\n"
                + "sum(case when planta ='FPS'  then valor else 0 end) as 'FPS',\n"
                + "\n"
                + "ifnull((select sum(valor)/count(valor) from I_011_Indicadores_Calidad where planta ='RS'\n"
                + "	    and indicador = P.indicador and ambito = P.ambito AND ANIO=" + (anio - 1) + "),0) AS 'PRS',\n"
                + "        \n"
                + "ifnull((select sum(valor)/count(valor) from I_011_Indicadores_Calidad where planta ='RSM'\n"
                + "	    and indicador = P.indicador and ambito = P.ambito AND ANIO=" + (anio - 1) + "),0) AS 'PRSM',\n"
                + "        \n"
                + "ifnull((select sum(valor)/count(valor) from I_011_Indicadores_Calidad where planta ='RLRS'\n"
                + "	    and indicador = P.indicador and ambito = P.ambito AND ANIO=" + (anio - 1) + "),0) AS 'PRLRS',\n"
                + "        \n"
                + "ifnull((select sum(valor)/count(valor) from I_011_Indicadores_Calidad where planta ='FPS'\n"
                + "	    and indicador = P.indicador and ambito = P.ambito AND ANIO=" + (anio - 1) + "),0) AS 'PFPS',\n"
                + "        \n"
                + "ifnull((select sum(valor)/count(valor) from I_011_Indicadores_Calidad where planta ='KNIT'\n"
                + "	    and indicador = P.indicador and ambito = P.ambito AND ANIO=" + (anio - 1) + "),0) AS 'PKNIT',\n"
                + "        \n"
                + "ifnull((select sum(valor)/count(valor) from I_011_Indicadores_Calidad where planta ='DPF'\n"
                + "	    and indicador = P.indicador and ambito = P.ambito AND ANIO=" + (anio - 1) + "),0) AS 'PDPF'\n"
                + "        \n"
                + "from I_011_Indicadores_Calidad P\n"
                + "where anio = " + (anio) + " and mes =" + mes + " and ambito='E' \n"
                + "GROUP BY ambito,indicador \n"
                //+ "ORDER BY ambito desc ";
                + "UNION ALL \n"
                + "Select \n"
                + "ambito, \n"
                + "'PT',\n"
                + "sum(case when planta ='RST'   then valor else 0 end) as 'RST',\n"
                + "sum(case when planta ='RSM'  then valor else 0 end) as 'RSM',\n"
                + "sum(case when planta ='RLRS' then valor else 0 end) as 'RLRS',\n"
                
                + "sum(case when planta ='KNIT' then valor else 0 end) as 'KNIT',\n"
                + "sum(case when planta ='DPF'  then valor else 0 end) as 'DPF',\n"
                + "sum(case when planta ='FPS'  then valor else 0 end) as 'FPS',\n"
                + "0,0,0,0,0,0\n"
                + "from I_011_Indicadores_Calidad P\n"
                + "where anio = " + (anio) + " and mes =" + mes + " and ambito='E' \n"
                + "AND ( indicador='CF' OR indicador='CNF') "
                + "ORDER BY ambito desc,indicador ";
    }

    public static String Consulta_Calidad_General(int anio, String mes, String indicador) {

        return "SELECT  planta,\n"
                + "SUM( CASE WHEN P.ANIO=" + (anio - 1) + " THEN P.VALOR ELSE 0 END) AS 'anio',\n"
                + "SUM( CASE WHEN P.ANIO=" + anio + " THEN P.VALOR ELSE 0 END) AS 'anio1',\n"
                + "\n"
                + "(select VALOR from I_011_Indicadores_Calidad\n"
                + "                where VALOR in (select VALOR from I_011_Indicadores_Calidad\n"
                + "                where  planta = P.planta)\n"
                + "                AND planta = P.planta  ORDER BY VALOR DESC LIMIT 1) AS 'mejor',\n"
                + "                \n"
                + "(select VALOR from I_011_Indicadores_Calidad\n"
                + "                where VALOR in (select VALOR from I_011_Indicadores_Calidad\n"
                + "                 where  planta = P.planta)\n"
                + "                AND planta = P.planta ORDER BY VALOR ASC LIMIT 1) AS 'peor',\n"
                + "                \n"
                + "                \n"
                + "ifnull((select sum(valor)/count(valor) from I_011_Indicadores_Calidad where planta = P.planta\n"
                + "AND MES = P.MES AND ANIO=" + (anio - 1) + " ),0) AS 'promedio'\n"
                + "\n"
                + "\n"
                + "FROM   I_011_Indicadores_Calidad P\n"
                + "where P.MES=" + mes + " and P.indicador='" + indicador + "' GROUP BY P.planta order by planta desc";
    }

    public static String Consulta_Calidad_Detalle(Integer anio, String indicador, String planta, String amb) {

        return "SELECT  \n"
                + "P.Mes,\n"
                + "SUM( CASE WHEN P.ANIO=" + (anio - 1) + " THEN P.VALOR ELSE 0 END) AS 'anio',\n"
                + "SUM( CASE WHEN P.ANIO=" + (anio) + "  THEN P.VALOR ELSE 0 END) AS 'anio1',\n"
                + "\n"
                + "(select VALOR from I_011_Indicadores_Calidad\n"
                + "	where VALOR in (select VALOR from I_011_Indicadores_Calidad where  planta = P.planta)\n"
                + "	AND planta = P.planta AND P.indicador=indicador AND ambito=P.ambito ORDER BY VALOR DESC LIMIT 1) AS 'mejor',\n"
                + "                \n"
                + "(select VALOR from I_011_Indicadores_Calidad\n"
                + "	where VALOR in (select VALOR from I_011_Indicadores_Calidad where  planta = P.planta)\n"
                + "	AND planta = P.planta AND P.indicador=indicador AND ambito=P.ambito ORDER BY VALOR ASC LIMIT 1) AS 'peor',\n"
                + "                \n"
                + "                \n"
                + "ifnull(\n"
                + "	(select sum(valor)/count(valor) from I_011_Indicadores_Calidad \n"
                + "    where planta = P.planta AND P.indicador=indicador AND ambito=P.ambito AND  ANIO=" + (anio - 1) + " ) , 0) AS 'promedio',\n"
                + "\n"
                + "(select mes from I_011_Indicadores_Calidad\n"
                + "	where VALOR in (select VALOR from I_011_Indicadores_Calidad where  planta = P.planta)\n"
                + "	AND planta = P.planta AND P.indicador=indicador AND ambito=P.ambito ORDER BY VALOR DESC LIMIT 1) AS 'mejormes',\n"
                + "\n"
                + "(select anio from I_011_Indicadores_Calidad\n"
                + "	where VALOR in (select VALOR from I_011_Indicadores_Calidad where  planta = P.planta)\n"
                + "	AND planta = P.planta AND P.indicador=indicador AND ambito=P.ambito ORDER BY VALOR DESC LIMIT 1) AS 'mejoranio',\n"
                + "                \n"
                + "(select mes from I_011_Indicadores_Calidad\n"
                + "	where VALOR in (select VALOR from I_011_Indicadores_Calidad where  planta = P.planta)\n"
                + "	AND planta = P.planta AND P.indicador=indicador AND ambito=P.ambito ORDER BY VALOR ASC LIMIT 1) AS 'peormes',\n"
                + "\n"
                + "(select anio from I_011_Indicadores_Calidad\n"
                + "	where VALOR in (select VALOR from I_011_Indicadores_Calidad where  planta = P.planta)\n"
                + "	AND planta = P.planta AND P.indicador=indicador AND ambito=P.ambito ORDER BY VALOR ASC LIMIT 1) AS 'peoranio'\n"
                + "\n"
                + "FROM   I_011_Indicadores_Calidad P\n"
                + "where  P.indicador='" + indicador + "'  and    P.planta='" + planta + "' and ambito = '" + amb + "'\n"
                + "GROUP BY P.planta,P.mes";
    }

}
