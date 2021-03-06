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
public class Modelo_001_Indicadores_Produccion {

    /*-------------------------------Consulta utilizada para la grafica general de barras----------------------------*/
    public static String I_001_Kilos_Producidos_Hora_Hombre_General(String mes, Integer anio, String simbolo, String tabla, String tablap, String valor, String valmax, String maxmin, String orden) {
        return "SELECT  P.PLANTA,\n"
                + "SUM( CASE WHEN P.ANIO=" + (anio - 1) + " THEN " + valor + " ELSE 0 END) AS 'anio',\n"
                + "SUM( CASE WHEN P.ANIO=" + anio + " THEN " + valor + " ELSE 0 END) AS 'anio1',\n"
                + "(select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + " in (select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + ">0 AND PLANTA=P.PLANTA)\n"
                + "                AND PLANTA=P.PLANTA ORDER BY " + valmax + " " + "DESC" + " LIMIT 1) AS 'mejor',\n"
                + "(select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + " in (select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + ">0 AND PLANTA=P.PLANTA)\n"
                + "                AND PLANTA=P.PLANTA ORDER BY " + valmax + " " + "ASC" + " LIMIT 1) AS 'peor',\n"
                + "ifnull((select Promedio from " + tablap + " where Planta=P.Planta\n"
                + "AND MES = P.MES AND ANIO=" + (anio - 1) + "),0) AS 'PROMEDIO',"
                + "(select MES from " + tabla + "\n"
                + "                where " + valmax + " in (select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + ">0 AND PLANTA=P.PLANTA)\n"
                + "                AND PLANTA=P.PLANTA ORDER BY " + valmax + " " + "DESC" + " LIMIT 1) AS 'MEJORMES',\n"
                + "\n"
                + "(select ANIO from " + tabla + "\n"
                + "                where " + valmax + " in (select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + ">0 AND PLANTA=P.PLANTA)\n"
                + "                AND PLANTA=P.PLANTA ORDER BY " + valmax + " " + "DESC" + " LIMIT 1) AS 'MEJORANIO',"
                + "(select MES from " + tabla + "\n"
                + "                where " + valmax + " in (select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + ">0 AND PLANTA=P.PLANTA)\n"
                + "                AND PLANTA=P.PLANTA ORDER BY " + valmax + " " + "ASC" + " LIMIT 1) AS 'PEORMES',\n"
                + "\n"
                + "(select ANIO from " + tabla + "\n"
                + "                where " + valmax + " in (select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + ">0 AND PLANTA=P.PLANTA)\n"
                + "                AND PLANTA=P.PLANTA ORDER BY " + valmax + " " + "ASC" + " LIMIT 1) AS 'PEORANIO'"
                + "\n"
                + "FROM   " + tabla + " P\n"
                + "where P.MES=" + mes + " and P.PLANTA" + simbolo + "'PLANTA FPS' GROUP BY P.PLANTA "
                + "ORDER BY P.PLANTA DESC ";
    }

    /*-------------------------------Consulta utilizada para la grafica lineal----------------------------*/
    public static String IndicadoresProduccion_Consulta_Por_Planta_Lineal(String planta, Integer anio, String tabla, String tablap, String valor, String valmax, String orden) {

        return "SELECT P.MES as MES,P.PLANTA,\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + (anio - 1) + "\n"
                + "THEN ifnull(" + valor + ",0)\n"
                + "ELSE 0\n"
                + "END) AS '2015',\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + (anio) + "\n"
                + "THEN ifnull(" + valor + ",0)\n"
                + "ELSE 0\n"
                + "END) AS '2016',\n"
                + "(select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + " in (select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + ">0 AND PLANTA=P.PLANTA)\n"
                + "                AND PLANTA=P.PLANTA ORDER BY " + valmax + " " + "DESC" + " LIMIT 1) AS 'mejor',\n"
                + "(select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + " in (select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + ">0 AND PLANTA=P.PLANTA)\n"
                + "                AND PLANTA=P.PLANTA ORDER BY " + valmax + " " + "ASC" + " LIMIT 1) AS 'peor',\n"
                + "ifnull((select Promedio from " + tablap + " where Planta=P.Planta\n"
                + "AND ANIO=" + (anio - 1) + " ),0) AS 'PROMEDIO',\n"
                + "(select MES from " + tabla + "\n"
                + "                where " + valmax + " in (select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + ">0 AND PLANTA=P.PLANTA)\n"
                + "                AND PLANTA=P.PLANTA ORDER BY " + valmax + " " + "DESC" + " LIMIT 1) AS 'MEJORMES',\n"
                + "\n"
                + "(select ANIO from " + tabla + "\n"
                + "                where " + valmax + " in (select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + ">0 AND PLANTA=P.PLANTA)\n"
                + "                AND PLANTA=P.PLANTA ORDER BY " + valmax + " " + "DESC" + " LIMIT 1) AS 'MEJORANIO',"
                + "(select MES from " + tabla + "\n"
                + "                where " + valmax + " in (select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + ">0 AND PLANTA=P.PLANTA)\n"
                + "                AND PLANTA=P.PLANTA ORDER BY " + valmax + " " + "ASC" + " LIMIT 1) AS 'PEORMES',\n"
                + "\n"
                + "(select ANIO from " + tabla + "\n"
                + "                where " + valmax + " in (select " + valmax + " from " + tabla + "\n"
                + "                where " + valmax + ">0 AND PLANTA=P.PLANTA)\n"
                + "                AND PLANTA=P.PLANTA ORDER BY " + valmax + " " + "ASC" + " LIMIT 1) AS 'PEORANIO'"
                + "\n"
                + "FROM   " + tabla + " P\n"
                + "where \n"
                + "P.PLANTA='" + planta + "'\n"
                + "GROUP BY P.PLANTA,P.MES /*,P.ANIO ORDER BY P.ANIO,P.MES*/";

    }

    /*-------------------------------Consulta utilizada para la tabla de indicadores de produccion(KG)-----------------------------*/
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
                + "SUM( CASE WHEN P.PLANTA='PLANTA RLRS' THEN P.Kg/P.Mrs  ELSE 0 END) AS 'RLRS',\n"
                + "ifnull((SELECT PROMEDIO from I_003_Kgproducidos_MRSP where PLANTA='PLANTA RLRS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RLRSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA FPS' THEN P.Kg/P.Mrs ELSE 0 END) AS 'FPS',\n"
                + "ifnull((SELECT PROMEDIO from I_003_Kgproducidos_MRSP where PLANTA='PLANTA FPS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'FPSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RSM O&M' THEN P.Kg/P.Mrs ELSE 0 END) AS 'RSM',\n"
                + "ifnull((SELECT IFNULL(PROMEDIO,0) from I_003_Kgproducidos_MRSP where PLANTA='PLANTA RSM O&M' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RSMP'\n"
                + "from I_003_Kgproducidos_MRS P where P.MES=" + mes + " AND P.ANIO=" + anio + "\n"
                + "\n";

    }

    /*-------------------------------Consulta utilizada para la tabla de indicadores de produccion (COSTOS)-----------------------------*/
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
                + "SUM( CASE WHEN P.PLANTA='PLANTA RLRS' THEN P.Mrs/P.Kg  ELSE 0 END) AS 'RLRS',\n"
                + "ifnull((SELECT PROMEDIO from I_006_MRS_KgproducidosP where PLANTA='PLANTA RLRS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RLRSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA FPS' THEN P.Mrs/P.Kg ELSE 0 END) AS 'FPS',\n"
                + "ifnull((SELECT PROMEDIO from I_006_MRS_KgproducidosP where PLANTA='PLANTA FPS' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'FPSP',\n"
                + "SUM( CASE WHEN P.PLANTA='PLANTA RSM O&M' THEN P.Mrs/P.Kg ELSE 0 END) AS 'RSM',\n"
                + "ifnull((SELECT IFNULL(PROMEDIO,0) from I_006_MRS_KgproducidosP where PLANTA='PLANTA RSM O&M' AND ANIO=" + (anio - 1) + "\n"
                + "),0) AS 'RSMP'\n"
                + "from I_003_Kgproducidos_MRS P where P.MES=" + mes + " AND P.ANIO=" + anio + "\n";

    }
}
