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
public class Modelo_004_Indicadores_Financieros {

    public static String Consulta_Tabla_Indicadores_Financieros(Integer anio, String mes) {
        return "select Nindicador,\n"
                + "\n"
                + "sum(CASE WHEN Empresa=1 then valor else 0 end) as 'Imperial Fashion',\n"
                + "\n"
                + "ifnull((select sum(valor)/count(valor) from I_010_Financieros where empresa = 1\n"
                + "AND MES = P.MES AND ANIO=" + (anio - 1) + "),0) AS 'pif',\n"
                + "\n"
                + "\n"
                + "sum(CASE WHEN Empresa=2 then valor else 0 end) as 'MT Textil',\n"
                + "\n"
                + "ifnull((select sum(valor)/count(valor) from I_010_Financieros where empresa = 2\n"
                + "AND MES = P.MES AND ANIO=" + (anio - 1) + "),0) AS 'pmt',\n"
                + "\n"
                + "\n"
                + "sum(CASE WHEN Empresa=3 then valor else 0 end) as 'Blake',\n"
                + "\n"
                + "ifnull((select sum(valor)/count(valor) from I_010_Financieros where empresa = 3\n"
                + "AND MES = P.MES AND ANIO=" + (anio - 1) + "),0) AS 'pbl'\n"
                + "\n"
                + " from I_010_Financieros P\n"
                + " where mes=" + mes + "\n"
                + " and anio=" + (anio) + "\n"
                + " group by Nindicador";
    }

    public static String Consulta_Financieros_General(int anio, String mes, String indicador) {

        return "SELECT  Empresa,\n"
                + "SUM( CASE WHEN P.ANIO=" + (anio - 1) + " THEN P.VALOR ELSE 0 END) AS 'anio',\n"
                + "SUM( CASE WHEN P.ANIO=" + anio + " THEN P.VALOR ELSE 0 END) AS 'anio1',\n"
                + "\n"
                + "(select VALOR from I_010_Financieros\n"
                + "                where VALOR in (select VALOR from I_010_Financieros\n"
                + "                where  empresa = P.Empresa)\n"
                + "                AND empresa = P.Empresa ORDER BY VALOR DESC LIMIT 1) AS 'mejor',\n"
                + "                \n"
                + "(select VALOR from I_010_Financieros\n"
                + "                where VALOR in (select VALOR from I_010_Financieros\n"
                + "                 where  empresa = P.Empresa)\n"
                + "                AND empresa = P.Empresa ORDER BY VALOR ASC LIMIT 1) AS 'peor',\n"
                + "                \n"
                + "                \n"
                + "ifnull((select sum(valor)/count(valor) from I_010_Financieros where empresa = P.Empresa\n"
                + "AND MES = P.MES AND ANIO=" + (anio - 1) + "),0) AS 'promedio'\n"
                + "\n"
                + "\n"
                + "FROM   I_010_Financieros P\n"
                + "where P.MES=" + mes + " and P.nindicador=" + indicador + " GROUP BY P.Empresa ";
    }

    public static String Consulta_Financieros_Detalle(Integer anio, String indicador, String empresa) {
        return "SELECT  P.Empresa, P.Mes,\n"
                + "SUM( CASE WHEN P.ANIO=" + (anio - 1) + " THEN P.VALOR ELSE 0 END) AS 'anio',\n"
                + "SUM( CASE WHEN P.ANIO=" + (anio) + "  THEN P.VALOR ELSE 0 END) AS 'anio1',\n"
                + "\n"
                + "(select VALOR from I_010_Financieros\n"
                + "                where VALOR in (select VALOR from I_010_Financieros\n"
                + "                where  empresa = P.Empresa)\n"
                + "                AND empresa = P.Empresa AND P.nindicador=nindicador ORDER BY VALOR DESC LIMIT 1) AS 'mejor',\n"
                + "                \n"
                + "(select VALOR from I_010_Financieros\n"
                + "                where VALOR in (select VALOR from I_010_Financieros\n"
                + "                 where  empresa = P.Empresa)\n"
                + "                AND empresa = P.Empresa AND P.nindicador=nindicador ORDER BY VALOR ASC LIMIT 1) AS 'peor',\n"
                + "                \n"
                + "                \n"
                + "ifnull((select sum(valor)/count(valor) from I_010_Financieros where empresa = P.Empresa\n"
                + "AND P.nindicador=nindicador AND  ANIO=" + (anio - 1) + " ),0) AS 'promedio',\n"
                + "\n"
                + "(select mes from I_010_Financieros\n"
                + "                where VALOR in (select VALOR from I_010_Financieros\n"
                + "                where  empresa = P.Empresa)\n"
                + "                AND empresa = P.Empresa AND P.nindicador=nindicador  ORDER BY VALOR DESC LIMIT 1) AS 'mejormes',\n"
                + "\n"
                + "(select anio from I_010_Financieros\n"
                + "                where VALOR in (select VALOR from I_010_Financieros\n"
                + "                where  empresa = P.Empresa)\n"
                + "                AND empresa = P.Empresa AND P.nindicador=nindicador  ORDER BY VALOR DESC LIMIT 1) AS 'mejoranio',\n"
                + "                \n"
                + "                (select mes from I_010_Financieros\n"
                + "                where VALOR in (select VALOR from I_010_Financieros\n"
                + "                where  empresa = P.Empresa)\n"
                + "                AND empresa = P.Empresa AND P.nindicador=nindicador  ORDER BY VALOR ASC LIMIT 1) AS 'peormes',\n"
                + "\n"
                + "(select anio from I_010_Financieros\n"
                + "                where VALOR in (select VALOR from I_010_Financieros\n"
                + "                where  empresa = P.Empresa)\n"
                + "                AND empresa = P.Empresa AND P.nindicador=nindicador  ORDER BY VALOR ASC LIMIT 1) AS 'peoranio'\n"
                + "\n"
                + "\n"
                + "FROM   I_010_Financieros P\n"
                + "where P.nindicador=" + indicador + " \n"
                + "and P.Empresa=" + empresa + " \n"
                + "GROUP BY P.Empresa,P.mes";
    }
}
