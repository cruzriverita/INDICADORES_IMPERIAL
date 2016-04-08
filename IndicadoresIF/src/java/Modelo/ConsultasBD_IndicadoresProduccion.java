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
public class ConsultasBD_IndicadoresProduccion {

    /*INDICADOR 000 - PRODUCCION POR PLANTA 
     ----------------------------------------------------------------------------------------------
     */
    public static String I_000_Produccion_Por_Planta_Mes_01(String anio, String mes) {
        return "select PLANTA,sum(VALOR) as VALOR FROM I_000_ProduccionPlantaMes  where anio='" + anio + "' and mes='" + mes + "' group by PLANTA";
    }

    public static String I_000_Produccion_Por_Planta_Mes_02(String planta, String anio, String mes) {
        return "select COMMODITY_CODE,sum(valor) as VALOR From I_000_ProduccionPlantaCommodity\n"
                + "where mes='" + mes + "'\n"
                + "and anio ='" + anio + "'\n"
                + "and planta='" + planta + "'\n"
                + "group by COMMODITY_CODE,mes,anio";
    }
    /*---------------------------------------------------------------------------------------------*/


 
    /*-------------------------------------I_0001-----------------------------------------------*/
    public static String I_001_Kilos_Producidos_Hora_Hombre_General(String mes, Integer anio, String simbolo) {
        return "SELECT  P.PLANTA,\n"
                + "SUM( CASE WHEN P.ANIO=" + (anio - 1) + " THEN P.VALOR ELSE 0 END) AS 'anio',\n"
                + "SUM( CASE WHEN P.ANIO=" + anio + " THEN P.VALOR ELSE 0 END) AS 'anio1',\n"
                + "(select max (VALOR) from I_001_KilosProducidosHoraHombre WHERE PLANTA=P.PLANTA) AS 'mejor',\n"
                + "ifnull((SELECT CASE \n"
                + "WHEN MES=1 THEN (SELECT sum(ProduccionTotal)/SUM(HorasHombreTotales)\n"
                + "from I_001_KilosProducidosHoraHombre WHERE MES = 1 AND PLANTA=P.PLANTA AND ANIO=" + (anio - 1) + ")\n"
                + "WHEN MES=2 THEN (SELECT sum(ProduccionTotal)/SUM(HorasHombreTotales)\n"
                + "from I_001_KilosProducidosHoraHombre WHERE MES BETWEEN 1 AND 2 AND PLANTA=P.PLANTA AND ANIO=" + (anio - 1) + ")\n"
                + "WHEN MES=3 THEN (SELECT sum(ProduccionTotal)/SUM(HorasHombreTotales) \n"
                + "from I_001_KilosProducidosHoraHombre WHERE MES BETWEEN 1 AND 3 AND PLANTA=P.PLANTA AND ANIO=" + (anio - 1) + ")\n"
                + "ELSE 0 END as 'TOTAL 2015'\n"
                + "from I_001_KilosProducidosHoraHombre PP\n"
                + "WHERE MES=P.MES group by P.PLANTA),0) AS 'Acumulado',\n"
                + "ifnull ((SELECT CASE \n"
                + "WHEN MES=1 THEN (SELECT sum(ProduccionTotal)/SUM(HorasHombreTotales)\n"
                + "from I_001_KilosProducidosHoraHombre WHERE MES = 1 AND PLANTA=P.PLANTA AND ANIO=" + (anio) + ")\n"
                + "WHEN MES=2 THEN (SELECT sum(ProduccionTotal)/SUM(HorasHombreTotales)\n"
                + "from I_001_KilosProducidosHoraHombre WHERE MES BETWEEN 1 AND 2 AND PLANTA=P.PLANTA AND ANIO=" + anio + ")\n"
                + "WHEN MES=3 THEN (SELECT sum(ProduccionTotal)/SUM(HorasHombreTotales)\n"
                + "from I_001_KilosProducidosHoraHombre WHERE MES BETWEEN 1 AND 3 AND PLANTA=P.PLANTA AND ANIO=" + anio + ")\n"
                + "ELSE 0 END as 'TOTAL 2015' from I_001_KilosProducidosHoraHombre PP\n"
                + "WHERE MES=P.MES group by P.PLANTA),0) AS 'Acumulado1',\n"
                + "ifnull((select Promedio from I_001_KilosProducidosHoraHombreP where Planta=P.Planta\n"
                + "AND MES = P.MES AND ANIO=" + (anio - 1) + "),0) AS 'PROMEDIO',"
                + "(select MES from I_001_KilosProducidosHoraHombre \n"
                + "where VALOR\n"
                + "in (select max(VALOR) from I_001_KilosProducidosHoraHombre WHERE PLANTA=P.PLANTA)\n"
                + "AND PLANTA=P.PLANTA) AS 'MEJORMES',\n"
                + "\n"
                + "(select ANIO from I_001_KilosProducidosHoraHombre \n"
                + "where VALOR\n"
                + "in (select max(VALOR) from I_001_KilosProducidosHoraHombre WHERE PLANTA=P.PLANTA)\n"
                + "AND PLANTA=P.PLANTA) AS 'MEJORANIO'"
                + "FROM   I_001_KilosProducidosHoraHombre P\n"
                + "where P.MES=" + mes + " and P.PLANTA" + simbolo + "'PLANTA FPS' GROUP BY P.PLANTA";
    }

    public static String I_001_Kilos_Producidos_Hora_Hombre_Planta(String planta, Integer anio) {
        return "SELECT P.MES as MES,P.PLANTA,\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + (anio - 1) + "\n"
                + "THEN P.VALOR\n"
                + "ELSE 0\n"
                + "END) AS '2015',\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + anio + "\n"
                + "THEN P.VALOR\n"
                + "ELSE 0\n"
                + "END) AS '2016',\n"
                + "(select max (VALOR) from I_001_KilosProducidosHoraHombre where PLANTA=P.PLANTA) AS 'mejor',\n"
                + "ifnull((select Promedio from I_001_KilosProducidosHoraHombreP where Planta=P.Planta\n"
                + "AND ANIO=" + (anio - 1) + " ),0) AS 'PROMEDIO',\n"
                + "(select MES from I_001_KilosProducidosHoraHombre \n"
                + "where VALOR\n"
                + "in (select max(VALOR) from I_001_KilosProducidosHoraHombre WHERE PLANTA=P.PLANTA)\n"
                + "AND PLANTA=P.PLANTA) AS 'MEJORMES',\n"
                + "\n"
                + "(select ANIO from I_001_KilosProducidosHoraHombre \n"
                + "where VALOR\n"
                + "in (select max(VALOR) from I_001_KilosProducidosHoraHombre WHERE PLANTA=P.PLANTA)\n"
                + "AND PLANTA=P.PLANTA) AS 'MEJORANIO'"
                + "\n"
                + "FROM   I_001_KilosProducidosHoraHombre P\n"
                + "where \n"
                + "P.PLANTA='" + planta + "'\n"
                + "GROUP BY P.PLANTA,P.MES/*,P.ANIO ORDER BY P.ANIO,P.MES*/";
    }

    /*-------------------------------------I_0002-----------------------------------------------*/
    public static String I_002_Kg_Producidos_Kwh_General(String mes, Integer anio, String simbolo) {
        return "SELECT  P.PLANTA,\n"
                + "SUM( CASE WHEN P.ANIO=" + (anio - 1) + " THEN P.Kg/P.Kwh ELSE 0 END) AS 'anio',\n"
                + "SUM( CASE WHEN P.ANIO=" + anio + " THEN P.Kg/P.Kwh ELSE 0 END) AS 'anio1',\n"
                + "(select max (Kg/Kwh) from I_002_KgProducidosKwh WHERE PLANTA=P.PLANTA) AS 'mejor',\n"
                + "ifnull((SELECT CASE \n"
                + "WHEN MES=1 THEN (SELECT sum(kg)/SUM(kwh)\n"
                + "from I_002_KgProducidosKwh WHERE MES = 1 AND PLANTA=P.PLANTA AND ANIO=" + (anio - 1) + ")\n"
                + "WHEN MES=2 THEN (SELECT sum(kg)/SUM(kwh)\n"
                + "from I_002_KgProducidosKwh WHERE MES BETWEEN 1 AND 2 AND PLANTA=P.PLANTA AND ANIO=" + (anio - 1) + ")\n"
                + " WHEN MES=3 THEN (SELECT sum(kg)/SUM(kwh) \n"
                + "from I_002_KgProducidosKwh WHERE MES BETWEEN 1 AND 3 AND PLANTA=P.PLANTA AND ANIO=" + (anio - 1) + ")\n"
                + " ELSE 0 END as 'TOTAL 2015'\n"
                + " from I_002_KgProducidosKwh PP\n"
                + "WHERE MES=P.MES group by P.PLANTA),0) AS 'Acumulado',\n"
                + "ifnull((SELECT CASE \n"
                + "WHEN MES=1 THEN (SELECT sum(kg)/SUM(kwh)\n"
                + "from I_002_KgProducidosKwh WHERE MES = 1 AND PLANTA=P.PLANTA AND ANIO=" + anio + ")\n"
                + "WHEN MES=2 THEN (SELECT sum(kg)/SUM(kwh)\n"
                + "from I_002_KgProducidosKwh WHERE MES BETWEEN 1 AND 2 AND PLANTA=P.PLANTA AND ANIO=" + anio + ")\n"
                + " WHEN MES=3 THEN (SELECT sum(kg)/SUM(kwh) \n"
                + "from I_002_KgProducidosKwh WHERE MES BETWEEN 1 AND 3 AND PLANTA=P.PLANTA AND ANIO=" + anio + ")\n"
                + " ELSE 0 END as 'TOTAL 2015' from I_002_KgProducidosKwh PP WHERE MES=P.MES\n"
                + "group by P.PLANTA),0) AS 'Acumulado1',\n"
                + "ifnull((select Promedio from I_002_KgProducidosKwhP where Planta=P.Planta\n"
                + "AND MES = P.MES AND ANIO=" + (anio - 1) + "),0) AS 'PROMEDIO'"
                + "FROM   I_002_KgProducidosKwh P where P.MES=" + mes + " and P.PLANTA" + simbolo + "'PLANTA FPS'\n"
                + "GROUP BY P.PLANTA";
    }

    public static String I_002_Kg_Producidos_Kwh_Planta(String planta, Integer anio) {
        return "SELECT P.MES,P.PLANTA,\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + (anio - 1) + "\n"
                + "THEN ifnull(P.Kg/P.Kwh,0)\n"
                + "ELSE 0\n"
                + "END) AS '2015',\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + anio + "\n"
                + "THEN ifnull(P.Kg/P.Kwh,0)\n"
                + "ELSE 0\n"
                + "END) AS '2016',\n"
                + "\n"
                + "(select max (Kg/Kwh) from I_002_KgProducidosKwh where PLANTA=P.PLANTA) AS 'mejor',\n"
                + "\n"
                + "ifnull((select Promedio from I_002_KgProducidosKwhP where Planta=P.Planta\n"
                + "AND ANIO=" + (anio - 1) + "),0) AS 'PROMEDIO',\n"
                + "(select MES from I_002_KgProducidosKwh \n"
                + "where Kg/Kwh\n"
                + "in (select max(Kg/Kwh) from I_002_KgProducidosKwh WHERE PLANTA=P.PLANTA)\n"
                + "AND PLANTA=P.PLANTA) AS 'MEJORMES',\n"
                + "\n"
                + "(select ANIO from I_002_KgProducidosKwh \n"
                + "where Kg/Kwh\n"
                + "in (select max(Kg/Kwh) from I_002_KgProducidosKwh WHERE PLANTA=P.PLANTA)\n"
                + "AND PLANTA=P.PLANTA) AS 'MEJORANIO'"
                + "\n"
                + "FROM   I_002_KgProducidosKwh P\n"
                + "where P.PLANTA='" + planta + "'\n"
                + "GROUP BY P.PLANTA,P.MES";
    }

    /*-------------------------------------I_0003-----------------------------------------------*/
    public static String I_003_Kg_Producidos_Mrs_General(String mes, Integer anio, String simbolo) {
        return "SELECT  P.PLANTA,\n"
                + "SUM( CASE WHEN P.ANIO=" + (anio - 1) + " THEN P.Kg/P.Mrs ELSE 0 END) AS 'anio',\n"
                + "SUM( CASE WHEN P.ANIO=" + anio + " THEN P.Kg/P.Mrs ELSE 0 END) AS 'anio1',\n"
                + "(select max (kg/Mrs) from I_003_Kgproducidos_MRS WHERE PLANTA=P.PLANTA) AS 'mejor',\n"
                + "\n"
                + "ifnull(( SELECT CASE \n"
                + "WHEN MES=1 THEN (SELECT sum(Kg)/SUM(Mrs)\n"
                + "from I_003_Kgproducidos_MRS WHERE MES = 1 AND PLANTA=P.PLANTA AND ANIO= " + (anio - 1) + " ) \n"
                + "\n"
                + "WHEN MES=2 THEN (SELECT sum(Kg)/SUM(Mrs)\n"
                + "from I_003_Kgproducidos_MRS WHERE MES BETWEEN 1 AND 2 AND PLANTA=P.PLANTA AND ANIO=" + (anio - 1) + ")  \n"
                + "\n"
                + "WHEN MES=3 THEN (SELECT sum(Kg)/SUM(Mrs)\n"
                + "from I_003_Kgproducidos_MRS WHERE MES BETWEEN 1 AND 3 AND PLANTA=P.PLANTA AND ANIO=" + (anio - 1) + ")  \n"
                + "\n"
                + "ELSE 0 END as 'TOTAL 2015'\n"
                + "from I_003_Kgproducidos_MRS PP WHERE MES=P.MES group by P.PLANTA),0) AS 'Acumulado',\n"
                + "\n"
                + "ifnull((SELECT CASE \n"
                + "WHEN MES=1 THEN (SELECT sum(Kg)/SUM(Mrs)\n"
                + "from I_003_Kgproducidos_MRS WHERE MES = 1 AND PLANTA=P.PLANTA AND ANIO=" + anio + ") \n"
                + "\n"
                + "WHEN MES=2 THEN (SELECT sum(Kg)/SUM(Mrs)\n"
                + "from I_003_Kgproducidos_MRS WHERE MES BETWEEN 1 AND 2 AND PLANTA=P.PLANTA AND ANIO=" + anio + ")  \n"
                + "\n"
                + "WHEN MES=3 THEN(SELECT sum(Kg)/SUM(Mrs)\n"
                + "from I_003_Kgproducidos_MRS WHERE MES BETWEEN 1 AND 3 AND PLANTA=P.PLANTA AND ANIO=" + anio + ")  \n"
                + "ELSE 0 END as 'TOTAL 2015'\n"
                + "from I_003_Kgproducidos_MRS PP\n"
                + "WHERE MES=P.MES group by P.PLANTA),0) AS 'Acumulado1',\n"
                + "ifnull((select Promedio from I_003_Kgproducidos_MRSP where Planta=P.Planta\n"
                + "AND MES = P.MES AND ANIO=" + (anio - 1) + "),0) AS 'PROMEDIO'"
                + "FROM I_003_Kgproducidos_MRS P where P.MES=" + mes + " and P.PLANTA" + simbolo + "'PLANTA FPS' GROUP BY P.PLANTA";
    }

    public static String I_003_Kg_Producidos_Mrs_Planta(String planta, Integer anio) {
        return "SELECT P.MES,P.PLANTA,\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + (anio - 1) + "\n"
                + "THEN P.Kg/P.Mrs\n"
                + "ELSE 0\n"
                + "END) AS '2015',\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + anio + "\n"
                + "THEN P.Kg/P.Mrs\n"
                + "ELSE 0\n"
                + "END) AS '2016',\n"
                + "\n"
                + "(select max (Kg/Mrs) from I_003_Kgproducidos_MRS where PLANTA=P.PLANTA) AS 'mejor',\n"
                + "         \n"
                + "ifnull((select Promedio from I_003_Kgproducidos_MRSP where Planta=P.Planta\n"
                + "AND ANIO=" + (anio - 1) + "),0) AS 'PROMEDIO',\n"
                + "(select MES from I_003_Kgproducidos_MRS \n"
                + "where Kg/Mrs\n"
                + "in (select max(Kg/Mrs) from I_003_Kgproducidos_MRS WHERE PLANTA=P.PLANTA)\n"
                + "AND PLANTA=P.PLANTA) AS 'MEJORMES',\n"
                + "\n"
                + "(select ANIO from I_003_Kgproducidos_MRS \n"
                + "where Kg/Mrs\n"
                + "in (select max(Kg/Mrs) from I_003_Kgproducidos_MRS WHERE PLANTA=P.PLANTA)\n"
                + "AND PLANTA=P.PLANTA) AS 'MEJORANIO'"
                + "FROM   I_003_Kgproducidos_MRS P\n"
                + "where P.PLANTA='" + planta + "'\n"
                + "GROUP BY P.PLANTA,P.MES";
    }

    /*----------------------------------------------------------------------------------------------------*/
    public static String I_004_CostoMO_Kg_Producido_General(String mes, Integer anio, String simbolo) {
        return "SELECT  P.PLANTA,\n"
                + "SUM( CASE WHEN P.ANIO=" + (anio - 1) + " THEN P.VALOR ELSE 0 END) AS 'anio',\n"
                + "SUM( CASE WHEN P.ANIO=" + (anio) + " THEN P.VALOR ELSE 0 END) AS 'anio1',\n"
                + "(select min (VALOR) from I_004_CostoMoKgProducido WHERE PLANTA=P.PLANTA) AS 'mejor',\n"
                + "ifnull((SELECT CASE \n"
                + "WHEN MES=1 THEN (SELECT sum(TotalMO)/SUM(Produccion)\n"
                + "from I_004_CostoMoKgProducido WHERE MES = 1 AND PLANTA=P.PLANTA AND ANIO=" + (anio - 1) + ")\n"
                + "\n"
                + "WHEN MES=2 THEN (SELECT sum(TotalMO)/SUM(Produccion)\n"
                + "from I_004_CostoMoKgProducido WHERE MES BETWEEN 1 AND 2 AND PLANTA=P.PLANTA AND ANIO=" + (anio - 1) + ")\n"
                + "\n"
                + " WHEN MES=3 THEN (SELECT sum(TotalMO)/SUM(Produccion)\n"
                + "from I_004_CostoMoKgProducido WHERE MES BETWEEN 1 AND 3 AND PLANTA=P.PLANTA AND ANIO=" + (anio - 1) + ")\n"
                + "\n"
                + " ELSE 0 END as 'TOTAL 2015' from I_004_CostoMoKgProducido PP WHERE MES=P.MES group by P.PLANTA),0) AS 'Acumulado',\n"
                + "IFNULL((SELECT CASE \n"
                + "WHEN MES=1 THEN (SELECT sum(TotalMO)/SUM(Produccion)\n"
                + "from I_004_CostoMoKgProducido WHERE MES = 1 AND PLANTA=P.PLANTA AND ANIO=" + (anio) + ")\n"
                + "WHEN MES=2 THEN (SELECT sum(TotalMO)/SUM(Produccion)\n"
                + "from I_004_CostoMoKgProducido WHERE MES BETWEEN 1 AND 2 AND PLANTA=P.PLANTA AND ANIO=" + (anio) + ")\n"
                + " WHEN MES=3 THEN(SELECT sum(TotalMO)/SUM(Produccion)\n"
                + "from I_004_CostoMoKgProducido WHERE MES BETWEEN 1 AND 3 AND PLANTA=P.PLANTA AND ANIO=" + (anio) + ")\n"
                + "ELSE 0 END as 'TOTAL 2015' from I_004_CostoMoKgProducido PP WHERE MES=P.MES group by P.PLANTA),0) AS 'Acumulado1',\n"
                + "ifnull((select Promedio from I_004_CostoMoKgProducidoP where Planta=P.Planta\n"
                + "AND MES = P.MES AND ANIO=" + (anio - 1) + "),0) AS 'PROMEDIO'"
                + "FROM   I_004_CostoMoKgProducido P where P.MES=" + mes + " and P.PLANTA" + simbolo + "'PLANTA FPS'\n"
                + "GROUP BY P.PLANTA";
    }

    public static String I_004_CostoMO_Kg_Producido_Planta(String planta, Integer anio) {
        return "SELECT P.MES,P.PLANTA,\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + (anio - 1) + "\n"
                + "THEN P.VALOR\n"
                + "ELSE 0\n"
                + "END) AS '2015',\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + anio + "\n"
                + "THEN P.VALOR\n"
                + "ELSE 0\n"
                + "END) AS '2016',\n"
                + "\n"
                + "(select VALOR from I_004_CostoMoKgProducido\n"
                + "where VALOR in (select VALOR from I_004_CostoMoKgProducido \n"
                + "where VALOR>0 AND PLANTA=P.PLANTA)\n"
                + "AND PLANTA=P.PLANTA ORDER BY VALOR LIMIT 1) AS 'mejor',\n"
                + "         \n"
                + "ifnull((select Promedio from I_004_CostoMoKgProducidoP where Planta=P.Planta\n"
                + "AND ANIO=" + (anio - 1) + "),0) AS 'PROMEDIO',\n"
                + "(select MES from I_004_CostoMoKgProducido\n"
                + "where VALOR in (select VALOR from I_004_CostoMoKgProducido \n"
                + "where VALOR>0 AND PLANTA=P.PLANTA)\n"
                + "AND PLANTA=P.PLANTA ORDER BY VALOR LIMIT 1) AS 'MEJORMES',\n"
                + "\n"
                + "(select ANIO from I_004_CostoMoKgProducido\n"
                + "where VALOR in (select VALOR from I_004_CostoMoKgProducido \n"
                + "where VALOR>0 AND PLANTA=P.PLANTA)\n"
                + "AND PLANTA=P.PLANTA ORDER BY VALOR LIMIT 1) AS 'MEJORANIO'\n"
                + "       \n"
                + "FROM   I_004_CostoMoKgProducido P\n"
                + "where P.PLANTA='" + planta + "'\n"
                + "GROUP BY P.PLANTA,P.MES";
    }

    public static String I_005_CostoKWH_Kg_Producido_General(String mes, Integer anio, String simbolo) {
        return "select  P.PLANTA,\n"
                + "sum(case when P.ANIO=" + (anio - 1) + " then P.QKwh/P.Kg else 0 end) as 'anio',\n"
                + "sum(case when P.ANIO=" + anio + "   then P.QKwh/P.Kg else 0 end) as 'anio1',\n"
                + "(select min (QKwh/Kg) from I_002_KgProducidosKwh where PLANTA=P.Planta) as 'mejor',\n"
                + "ifnull((select case \n"
                + "when MES=1 then (select sum(QKwh)/sum(kg)\n"
                + "from I_002_KgProducidosKwh where MES = 1 and PLANTA=P.PLANTA and ANIO=" + (anio - 1) + ") \n"
                + "\n"
                + "when MES=2 then (select sum(QKwh)/sum(kg)\n"
                + "from I_002_KgProducidosKwh where MES between 1 and 2 and PLANTA=P.PLANTA and ANIO=" + (anio - 1) + ")  \n"
                + "\n"
                + "when MES=3 then (select sum(QKwh)/sum(kg) \n"
                + "from I_002_KgProducidosKwh where MES between 1 and 3 and PLANTA=P.PLANTA and ANIO=" + (anio - 1) + ")  \n"
                + "else 0 end as 'TOTAL 2015'\n"
                + "\n"
                + "from I_002_KgProducidosKwh PP\n"
                + "where MES=P.MES\n"
                + "group by P.PLANTA),0) as 'Acumulado',\n"
                + "\n"
                + "ifnull((select case \n"
                + "when MES=1 then (select sum(QKwh)/sum(kg)\n"
                + "from I_002_KgProducidosKwh where MES = 1 and PLANTA=P.PLANTA and ANIO=" + anio + ") \n"
                + "\n"
                + "when MES=2 then (select sum(QKwh)/sum(kg)\n"
                + "from I_002_KgProducidosKwh where MES between 1 and 2 and PLANTA=P.PLANTA and ANIO=" + anio + ")  \n"
                + "\n"
                + "when MES=3 then (select sum(QKwh)/sum(kg)\n"
                + "from I_002_KgProducidosKwh where MES between 1 and 3 and PLANTA=P.PLANTA and ANIO=" + anio + ")  \n"
                + "else 0 end as 'TOTAL 2015'\n"
                + "\n"
                + "from I_002_KgProducidosKwh PP\n"
                + "where MES=P.MES\n"
                + "group by P.PLANTA),0) as 'Acumulado1',\n"
                + "ifnull((select Promedio from I_005_CostoKwh_KgProducidoP where Planta=P.Planta\n"
                + "AND MES = P.MES AND ANIO=" + (anio - 1) + "),0) AS 'PROMEDIO'"
                + "from   I_002_KgProducidosKwh P\n"
                + "where P.MES=" + mes + "\n"
                + "and P.PLANTA" + simbolo + "'PLANTA FPS'\n"
                + "group by P.PLANTA";
    }

    public static String I_005_CostoKWH_Kg_Producido_Planta(String planta, Integer anio) {
        return "SELECT P.MES,P.PLANTA,\n" +
"SUM( CASE \n" +
"WHEN P.ANIO=2014 \n" +
"THEN IFNULL(P.QKwh/P.Kg,0)\n" +
"ELSE 0\n" +
"END) AS '2015',\n" +
"SUM( CASE \n" +
"WHEN P.ANIO=2015\n" +
"THEN P.QKwh/P.Kg\n" +
"ELSE 0\n" +
"END) AS '2016',\n" +
"\n" +
"(select QKwh/Kg from I_002_KgProducidosKwh\n" +
"where QKwh/Kg in (select QKwh/Kg from I_002_KgProducidosKwh \n" +
"where QKwh/Kg>0 AND PLANTA=P.PLANTA) ORDER BY QKwh/Kg LIMIT 1) AS 'mejor',\n" +
"         \n" +
"ifnull((select Promedio from I_005_CostoKwh_KgProducidoP where Planta=P.Planta\n" +
"AND ANIO=2015),0) AS 'PROMEDIO',\n" +
"\n" +
"(select MES from I_002_KgProducidosKwh\n" +
"where QKwh/Kg in (select QKwh/Kg from I_002_KgProducidosKwh \n" +
"where QKwh/Kg>0 AND PLANTA=P.PLANTA) ORDER BY QKwh/Kg LIMIT 1) AS 'MEJORMES',\n" +
"(select ANIO from I_002_KgProducidosKwh\n" +
"where QKwh/Kg in (select QKwh/Kg from I_002_KgProducidosKwh \n" +
"where QKwh/Kg>0 AND PLANTA=P.PLANTA) ORDER BY QKwh/Kg LIMIT 1) AS 'MEJORANIO'\n" +
"       \n" +
"FROM   I_002_KgProducidosKwh P\n" +
"where P.PLANTA='PLANTA FPS'\n" +
"GROUP BY P.PLANTA,P.MES\n" +
"\n" +
"union all\n" +
"\n" +
"SELECT P.MES,P.PLANTA,\n" +
"SUM( CASE \n" +
"WHEN P.ANIO=2015 \n" +
"THEN IFNULL(P.QKwh/P.Kg,0)\n" +
"ELSE 0\n" +
"END) AS '2015',\n" +
"SUM( CASE \n" +
"WHEN P.ANIO=2016\n" +
"THEN P.QKwh/P.Kg\n" +
"ELSE 0\n" +
"END) AS '2016',\n" +
"\n" +
"(select QKwh/Kg from I_002_KgProducidosKwh\n" +
"where QKwh/Kg in (select QKwh/Kg from I_002_KgProducidosKwh \n" +
"where QKwh/Kg>0 AND PLANTA=P.PLANTA) ORDER BY QKwh/Kg LIMIT 1) AS 'mejor',\n" +
"         \n" +
"ifnull((select Promedio from I_005_CostoKwh_KgProducidoP where Planta=P.Planta\n" +
"AND ANIO=2015),0) AS 'PROMEDIO',\n" +
"\n" +
"(select MES from I_002_KgProducidosKwh\n" +
"where QKwh/Kg in (select QKwh/Kg from I_002_KgProducidosKwh \n" +
"where QKwh/Kg>0 AND PLANTA=P.PLANTA) ORDER BY QKwh/Kg LIMIT 1) AS 'MEJORMES',\n" +
"\n" +
"(select ANIO from I_002_KgProducidosKwh\n" +
"where QKwh/Kg in (select QKwh/Kg from I_002_KgProducidosKwh \n" +
"where QKwh/Kg>0 AND PLANTA=P.PLANTA) ORDER BY QKwh/Kg LIMIT 1) AS 'MEJORANIO'\n" +
"       \n" +
"FROM   I_002_KgProducidosKwh P\n" +
"where P.PLANTA='PLANTA FPS'\n" +
"GROUP BY P.PLANTA,P.MES";
                
                /*"SELECT P.MES,P.PLANTA,\n"
                + "SUM( CASE \n"               
                + "WHEN P.ANIO=" + (anio - 1) + "\n"
                + "THEN IFNULL(P.QKwh/P.Kg,0)\n"
                + "ELSE 0\n"
                + "END) AS '2015',\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + anio + "\n"             
                + "THEN IFNULL(P.QKwh/P.Kg,0)\n"
                + "ELSE 0\n"
                + "END) AS '2016',\n"
                + "(select QKwh/Kg from I_002_KgProducidosKwh\n"
                + "where QKwh/Kg in (select QKwh/Kg from I_002_KgProducidosKwh \n"
                + "where QKwh/Kg>0 AND PLANTA=P.PLANTA) ORDER BY QKwh/Kg LIMIT 1) AS 'mejor',\n"
                + "ifnull((select Promedio from I_005_CostoKwh_KgProducidoP where Planta=P.Planta\n"
                + "AND ANIO=" + (anio - 1) + "),0) AS 'PROMEDIO',\n"
                + "(select MES from I_002_KgProducidosKwh\n"
                + "where QKwh/Kg in (select QKwh/Kg from I_002_KgProducidosKwh \n"
                + "where QKwh/Kg>0 AND PLANTA=P.PLANTA) ORDER BY QKwh/Kg LIMIT 1) AS 'MEJORMES',"
                + "(select ANIO from I_002_KgProducidosKwh\n"
                + "where QKwh/Kg in (select QKwh/Kg from I_002_KgProducidosKwh \n"
                + "where QKwh/Kg>0 AND PLANTA=P.PLANTA) ORDER BY QKwh/Kg LIMIT 1) AS 'MEJORANIO'"
                + "FROM   I_002_KgProducidosKwh P\n"
                + "where P.PLANTA='" + planta + "'\n"
                + "GROUP BY P.PLANTA,P.MES,P.ANIO ORDER BY P.ANIO,P.MES";*/
    }

    public static String I_006_Mrs_Kg_Producidos_General(String mes, Integer anio, String simbolo) {
        return "SELECT  P.PLANTA,\n"
                + "SUM( CASE WHEN P.ANIO=" + (anio - 1) + " THEN P.Mrs/P.Kg ELSE 0 END) AS 'anio',\n"
                + "SUM( CASE WHEN P.ANIO=" + anio + " THEN P.Mrs/P.Kg ELSE 0 END) AS 'anio1',\n"
                + "(select min (Mrs/Kg) from I_003_Kgproducidos_MRS WHERE PLANTA=P.PLANTA) AS 'mejor',\n"
                + "\n"
                + "ifnull(( SELECT CASE \n"
                + "WHEN MES=1 THEN (SELECT sum(Mrs)/SUM(Kg)\n"
                + "from I_003_Kgproducidos_MRS WHERE MES = 1 AND PLANTA=P.PLANTA AND ANIO= " + (anio - 1) + " ) \n"
                + "\n"
                + "WHEN MES=2 THEN (SELECT sum(Mrs)/SUM(Kg)\n"
                + "from I_003_Kgproducidos_MRS WHERE MES BETWEEN 1 AND 2 AND PLANTA=P.PLANTA AND ANIO=" + (anio - 1) + ")  \n"
                + "\n"
                + "WHEN MES=3 THEN (SELECT sum(Mrs)/SUM(Kg)\n"
                + "from I_003_Kgproducidos_MRS WHERE MES BETWEEN 1 AND 3 AND PLANTA=P.PLANTA AND ANIO=" + (anio - 1) + ")  \n"
                + "\n"
                + "ELSE 0 END as 'TOTAL 2015'\n"
                + "from I_003_Kgproducidos_MRS PP WHERE MES=P.MES group by P.PLANTA),0) AS 'Acumulado',\n"
                + "\n"
                + "ifnull((SELECT CASE \n"
                + "WHEN MES=1 THEN (SELECT sum(Mrs)/SUM(Kg)\n"
                + "from I_003_Kgproducidos_MRS WHERE MES = 1 AND PLANTA=P.PLANTA AND ANIO=" + anio + ") \n"
                + "\n"
                + "WHEN MES=2 THEN (SELECT sum(Mrs)/SUM(Kg)\n"
                + "from I_003_Kgproducidos_MRS WHERE MES BETWEEN 1 AND 2 AND PLANTA=P.PLANTA AND ANIO=" + anio + ")  \n"
                + "\n"
                + "WHEN MES=3 THEN(SELECT sum(Mrs)/SUM(Kg)\n"
                + "from I_003_Kgproducidos_MRS WHERE MES BETWEEN 1 AND 3 AND PLANTA=P.PLANTA AND ANIO=" + anio + ")  \n"
                + "ELSE 0 END as 'TOTAL 2015'\n"
                + "from I_003_Kgproducidos_MRS PP\n"
                + "WHERE MES=P.MES group by P.PLANTA),0) AS 'Acumulado1',\n"
                + "ifnull((select Promedio from I_006_MRS_KgproducidosP where Planta=P.Planta\n"
                + "AND MES = P.MES AND ANIO=" + (anio - 1) + "),0) AS 'PROMEDIO'"
                + "FROM I_003_Kgproducidos_MRS P where P.MES=" + mes + " and P.PLANTA" + simbolo + "'PLANTA FPS' GROUP BY P.PLANTA";
    }

    public static String I_006_Mrs_Kg_Producidos_Planta(String planta, Integer anio) {
        return "SELECT P.MES,P.PLANTA,\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + (anio - 1) + "\n"
                + "THEN P.Mrs/P.Kg\n"
                + "ELSE 0\n"
                + "END) AS '2015',\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + anio + "\n"
                + "THEN P.Mrs/P.Kg\n"
                + "ELSE 0\n"
                + "END) AS '2016',\n"
                + "\n"
                + "(select Mrs/Kg from I_003_Kgproducidos_MRS\n"
                + "where Mrs/Kg in (select Mrs/Kg from I_003_Kgproducidos_MRS \n"
                + "where Mrs/Kg>0 AND PLANTA=P.PLANTA)\n"
                + "AND PLANTA=P.PLANTA ORDER BY Mrs/Kg LIMIT 1) AS 'mejor',\n"
                + "ifnull((select Promedio from I_006_MRS_KgproducidosP where Planta=P.Planta\n"
                + "AND ANIO=" + (anio - 1) + "),0) AS 'PROMEDIO',\n"
                + "(select MES from I_003_Kgproducidos_MRS\n"
                + "where Mrs/Kg in (select Mrs/Kg from I_003_Kgproducidos_MRS \n"
                + "where Mrs/Kg>0 AND PLANTA=P.PLANTA) ORDER BY Mrs/Kg LIMIT 1) AS 'MEJORMES',"
                + "(select ANIO from I_003_Kgproducidos_MRS\n"
                + "where Mrs/Kg in (select Mrs/Kg from I_003_Kgproducidos_MRS \n"
                + "where Mrs/Kg>0 AND PLANTA=P.PLANTA) ORDER BY Mrs/Kg LIMIT 1) AS 'MEJORANIO'"
                + "FROM   I_003_Kgproducidos_MRS P\n"
                + "where P.PLANTA='" + planta + "'\n"
                + "GROUP BY P.PLANTA,P.MES";
    }

    public static String C_001_Produccion_Por_Planta(String mes, Integer anio) {
        return "SELECT  'Kg Producidos/Hora-Hombre' as Indicador,\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RST' THEN P.ProduccionTotal/P.HorasHombreTotales ELSE 0 END) AS 'RST',\n"
                + "ifnull((SELECT PROMEDIO from I_001_KilosProducidosHoraHombreP where PLANTA='PLANTA RST' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RSTP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA KNIT' THEN P.ProduccionTotal/P.HorasHombreTotales  ELSE 0 END) AS 'KNIT',\n"
                + "ifnull((SELECT PROMEDIO from I_001_KilosProducidosHoraHombreP where PLANTA='PLANTA KNIT' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'KNITP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA DPF' THEN P.ProduccionTotal/P.HorasHombreTotales ELSE 0 END) AS 'DPF',\n"
                + "ifnull((SELECT PROMEDIO from I_001_KilosProducidosHoraHombreP where PLANTA='PLANTA DPF' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'DPFP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RLRS' THEN P.ProduccionTotal/P.HorasHombreTotales  ELSE 0 END) AS 'RLRS',\n"
                + "ifnull((SELECT PROMEDIO from I_001_KilosProducidosHoraHombreP where PLANTA='PLANTA RLRS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RLRSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA FPS' THEN P.ProduccionTotal/P.HorasHombreTotales ELSE 0 END) AS 'FPS',\n"
                + "ifnull((SELECT PROMEDIO from I_001_KilosProducidosHoraHombreP where PLANTA='PLANTA FPS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'FPSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RSM O&M' THEN P.ProduccionTotal/P.HorasHombreTotales  ELSE 0 END) AS 'RSM',\n"
                + "ifnull((SELECT IFNULL(PROMEDIO,0) from I_001_KilosProducidosHoraHombreP where PLANTA='PLANTA RSM O&M' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RSMP'\n"
                + "from I_001_KilosProducidosHoraHombre P where P.MES=" + mes + " AND P.ANIO=" + anio + "\n"
                + "\n"
                + "UNION\n"
                + "SELECT  'Kg Producidos/KwH' as Indicador,\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RST' THEN P.Kg/P.Kwh ELSE 0 END) AS 'RST',\n"
                + "ifnull((SELECT PROMEDIO from I_002_KgProducidosKwhP where PLANTA='PLANTA RST' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RSTP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA KNIT' THEN P.Kg/P.Kwh ELSE 0 END) AS 'KNIT',\n"
                + "ifnull((SELECT PROMEDIO from I_002_KgProducidosKwhP where PLANTA='PLANTA KNIT' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'KNITP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA DPF' THEN P.Kg/P.Kwh ELSE 0 END) AS 'DPF',\n"
                + "ifnull((SELECT PROMEDIO from I_002_KgProducidosKwhP where PLANTA='PLANTA DPF' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'DPFP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RLRS' THEN P.Kg/P.Kwh  ELSE 0 END) AS 'RLRS',\n"
                + "ifnull((SELECT PROMEDIO from I_002_KgProducidosKwhP where PLANTA='PLANTA RLRS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RLRSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA FPS' THEN P.Kg/P.Kwh ELSE 0 END) AS 'FPS',\n"
                + "ifnull((SELECT PROMEDIO from I_002_KgProducidosKwhP where PLANTA='PLANTA FPS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'FPSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RSM O&M' THEN P.Kg/P.Kwh  ELSE 0 END) AS 'RSM',\n"
                + "ifnull((SELECT IFNULL(PROMEDIO,0) from I_002_KgProducidosKwhP where PLANTA='PLANTA RSM O&M' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RSMP'\n"
                + "from I_002_KgProducidosKwh P where P.MES=" + mes + " AND P.ANIO=" + anio + "\n"
                + "\n"
                + "UNION\n"
                + "SELECT  'Kg Producidos/$MRS' as Indicador,\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RST' THEN P.Kg/P.Mrs ELSE 0 END) AS 'RST',\n"
                + "ifnull((SELECT PROMEDIO from I_003_Kgproducidos_MRSP where PLANTA='PLANTA RST' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RSTP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA KNIT' THEN P.Kg/P.Mrs ELSE 0 END) AS 'KNIT',\n"
                + "ifnull((SELECT PROMEDIO from I_003_Kgproducidos_MRSP where PLANTA='PLANTA KNIT' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'KNITP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA DPF' THEN P.Kg/P.Mrs ELSE 0 END) AS 'DPF',\n"
                + "ifnull((SELECT PROMEDIO from I_003_Kgproducidos_MRSP where PLANTA='PLANTA DPF' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'DPFP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA LRS' THEN P.Kg/P.Mrs  ELSE 0 END) AS 'RLRS',\n"
                + "ifnull((SELECT PROMEDIO from I_003_Kgproducidos_MRSP where PLANTA='PLANTA LRS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RLRSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA FPS' THEN P.Kg/P.Mrs ELSE 0 END) AS 'FPS',\n"
                + "ifnull((SELECT PROMEDIO from I_003_Kgproducidos_MRSP where PLANTA='PLANTA FPS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'FPSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA MRS O&M' THEN P.Kg/P.Mrs ELSE 0 END) AS 'RSM',\n"
                + "ifnull((SELECT IFNULL(PROMEDIO,0) from I_003_Kgproducidos_MRSP where PLANTA='PLANTA MRS O&M' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RSMP'\n"
                + "from I_003_Kgproducidos_MRS P where P.MES=" + mes + " AND P.ANIO=" + anio + "\n"
                + "\n";

    }

    public static String C_001_Produccion_Por_Planta2(String mes, Integer anio) {
        return "SELECT  'Costo Mo/Kg Producido' as Indicador,\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RST' THEN P.TotalMO/P.Produccion ELSE 0 END) AS 'RST',\n"
                + "ifnull((SELECT PROMEDIO from I_004_CostoMoKgProducidoP where PLANTA='PLANTA RST' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RSTP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA KNIT' THEN P.TotalMO/P.Produccion ELSE 0 END) AS 'KNIT',\n"
                + "ifnull((SELECT PROMEDIO from I_004_CostoMoKgProducidoP where PLANTA='PLANTA KNIT' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'KNITP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA DPF' THEN P.TotalMO/P.Produccion ELSE 0 END) AS 'DPF',\n"
                + "ifnull((SELECT PROMEDIO from I_004_CostoMoKgProducidoP where PLANTA='PLANTA DPF' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'DPFP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RLRS' THEN P.TotalMO/P.Produccion  ELSE 0 END) AS 'RLRS',\n"
                + "ifnull((SELECT PROMEDIO from I_004_CostoMoKgProducidoP where PLANTA='PLANTA RLRS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RLRSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA FPS' THEN P.TotalMO/P.Produccion ELSE 0 END) AS 'FPS',\n"
                + "ifnull((SELECT PROMEDIO from I_004_CostoMoKgProducidoP where PLANTA='PLANTA FPS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'FPSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RSM O&M' THEN P.TotalMO/P.Produccion ELSE 0 END) AS 'RSM',\n"
                + "ifnull((SELECT IFNULL(PROMEDIO,0) from I_004_CostoMoKgProducidoP where PLANTA='PLANTA RSM O&M' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RSMP'\n"
                + "from I_004_CostoMoKgProducido P where P.MES=" + mes + " AND P.ANIO=" + anio + "\n"
                + "\n"
                + "UNION\n"
                + "SELECT  'Costo KWH/Kg Producido' as Indicador,\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RST' THEN P.QKwh/P.Kg ELSE 0 END) AS 'RST',\n"
                + "ifnull((SELECT PROMEDIO from I_005_CostoKwh_KgProducidoP where PLANTA='PLANTA RST' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RSTP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA KNIT' THEN P.QKwh/P.Kg ELSE 0 END) AS 'KNIT',\n"
                + "ifnull((SELECT PROMEDIO from I_005_CostoKwh_KgProducidoP where PLANTA='PLANTA KNIT' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'KNITP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA DPF' THEN P.QKwh/P.Kg ELSE 0 END) AS 'DPF',\n"
                + "ifnull((SELECT PROMEDIO from I_005_CostoKwh_KgProducidoP where PLANTA='PLANTA DPF' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'DPFP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RLRS' THEN P.QKwh/P.Kg  ELSE 0 END) AS 'RLRS',\n"
                + "ifnull((SELECT PROMEDIO from I_005_CostoKwh_KgProducidoP where PLANTA='PLANTA RLRS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RLRSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA FPS' THEN P.QKwh/P.Kg ELSE 0 END) AS 'FPS',\n"
                + "ifnull((SELECT PROMEDIO from I_005_CostoKwh_KgProducidoP where PLANTA='PLANTA FPS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'FPSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RSM O&M' THEN P.QKwh/P.Kg ELSE 0 END) AS 'RSM',\n"
                + "ifnull((SELECT IFNULL(PROMEDIO,0) from I_005_CostoKwh_KgProducidoP where PLANTA='PLANTA RSM O&M' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RSMP'\n"
                + "from I_002_KgProducidosKwh P where P.MES=" + mes + " AND P.ANIO=" + anio + "\n"
                + "UNION\n"
                + "SELECT  'Costo MRS/Kg Producido' as Indicador,\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RST' THEN P.Mrs/P.Kg ELSE 0 END) AS 'RST',\n"
                + "ifnull((SELECT PROMEDIO from I_006_MRS_KgproducidosP where PLANTA='PLANTA RST' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RSTP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA KNIT' THEN P.Mrs/P.Kg ELSE 0 END) AS 'KNIT',\n"
                + "ifnull((SELECT PROMEDIO from I_006_MRS_KgproducidosP where PLANTA='PLANTA KNIT' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'KNITP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA DPF' THEN P.Mrs/P.Kg ELSE 0 END) AS 'DPF',\n"
                + "ifnull((SELECT PROMEDIO from I_006_MRS_KgproducidosP where PLANTA='PLANTA DPF' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'DPFP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA LRS' THEN P.Mrs/P.Kg  ELSE 0 END) AS 'RLRS',\n"
                + "ifnull((SELECT PROMEDIO from I_006_MRS_KgproducidosP where PLANTA='PLANTA LRS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RLRSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA FPS' THEN P.Mrs/P.Kg ELSE 0 END) AS 'FPS',\n"
                + "ifnull((SELECT PROMEDIO from I_006_MRS_KgproducidosP where PLANTA='PLANTA FPS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'FPSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA MRS O&M' THEN P.Mrs/P.Kg ELSE 0 END) AS 'RSM',\n"
                + "ifnull((SELECT IFNULL(PROMEDIO,0) from I_006_MRS_KgproducidosP where PLANTA='PLANTA MRS O&M' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RSMP'\n"
                + "from I_003_Kgproducidos_MRS P where P.MES=" + mes + " AND P.ANIO=" + anio + "\n";

    }
}
