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
public class Modelo_003_Indicadores_Inventarios {

    public static String Inventarios_tabla_general(Integer anio, String mes) {
        return "SELECT anio, \n"
                + "( CASE \n"
                + "WHEN P.tipo='A'\n"
                + "THEN 'ALGODON'\n"
                + "\n"
                + "WHEN P.tipo='PF'\n"
                + "THEN 'POLIESTER Y FIBRA'\n"
                + "\n"
                + "WHEN P.tipo='HP'\n"
                + "THEN 'HILO PRODUCIDO'\n"
                + "\n"
                + "WHEN P.tipo='HC'\n"
                + "THEN 'HILO COMPRADO'\n"
                + "\n"
                + "WHEN P.tipo='TC'\n"
                + "THEN 'TELA CRUDA'\n"
                + "\n"
                + "WHEN P.tipo='TT'\n"
                + "THEN 'TELA TERMINADA'\n"
                + "\n"
                + "ELSE ''\n"
                + "END) AS 'tipo'\n"
                + ",\n"
                + "ifnull(indice,0) as indicea,\n"
                + "\n"
                + "ifnull((select indice from I_009_Rotacion_Inventarios_General where anio=" + (anio - 1) + " and tipo = P.tipo and mes=P.mes),0) as 'indicev',\n"
                + "\n"
                + "ifnull((select indice from I_009_Rotacion_Inventarios_Promedio where anio=" + (anio - 1) + " and tipo = P.tipo),0) as 'indicep',\n"
                + "\n"
                + "\n"
                + "ifnull(dias,0) as diaa,\n"
                + "ifnull((select dias from I_009_Rotacion_Inventarios_General where anio=" + (anio - 1) + " and tipo = P.tipo and mes=P.mes),0) as 'diav',\n"
                + "ifnull((select dias from I_009_Rotacion_Inventarios_Promedio where anio=" + (anio - 1) + " and tipo = P.tipo),0) as 'diap'\n"
                + "\n"
                + "\n"
                + "FROM INDICADORES.I_009_Rotacion_Inventarios_General P\n"
                + "WHERE P.anio=" + (anio) + " And P.mes=" + (mes) + "; ";
    }

    public static String I_003_Indicadores_Inventarios_General(Integer anio, String tipo, String valor, String valmaxmin) {
        return "SELECT P.mes,\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + (anio - 1) + "\n"
                + "THEN ifnull(" + valor + ",0)\n"
                + "ELSE 0\n"
                + "END) AS 'anio1',\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + anio + "\n"
                + "THEN ifnull(" + valor + ",0)\n"
                + "ELSE 0\n"
                + "END) AS 'anio2',\n"
                + "\n"
                + "(select max(" + valmaxmin + ") from I_009_Rotacion_Inventarios_General WHERE Tipo=P.TIPO) as 'MAYOR',\n"
                + "\n"
                + "\n"
                + "(SELECT mes from I_009_Rotacion_Inventarios_General\n"
                + "where " + valmaxmin + " in (select max(" + valmaxmin + ") from I_009_Rotacion_Inventarios_General WHERE Tipo=P.TIPO) \n"
                + "AND Tipo = P.TIPO ORDER BY 1 DESC LIMIT 1) as 'MAYORMES',\n"
                + "\n"
                + "(SELECT ANIO from I_009_Rotacion_Inventarios_General\n"
                + "where " + valmaxmin + " in (select max(" + valmaxmin + ") from I_009_Rotacion_Inventarios_General WHERE Tipo=P.TIPO ) \n"
                + "AND Tipo = P.TIPO ORDER BY 1 DESC LIMIT 1) as 'MAYORANIO',\n"
                + "\n"
                + "(select min(" + valmaxmin + ") from I_009_Rotacion_Inventarios_General WHERE Tipo=P.TIPO) as 'MENOR',\n"
                + "\n"
                + "(SELECT mes from I_009_Rotacion_Inventarios_General\n"
                + "where " + valmaxmin + " in (select min(" + valmaxmin + ") from I_009_Rotacion_Inventarios_General WHERE Tipo=P.TIPO ) \n"
                + "AND Tipo = P.TIPO ORDER BY 1 ASC LIMIT 1) as 'MENORMES',\n"
                + "\n"
                + "(SELECT ANIO from I_009_Rotacion_Inventarios_General\n"
                + "where " + valmaxmin + " in (select min(" + valmaxmin + ") from I_009_Rotacion_Inventarios_General WHERE Tipo=P.TIPO) \n"
                + "AND Tipo = P.TIPO ORDER BY 1 ASC LIMIT 1) as 'MENORANIO',\n"
                + "\n"
                + "ifnull((SELECT SUM(" + valmaxmin + ")/COUNT(*) FROM I_009_Rotacion_Inventarios_General WHERE tipo=P.Tipo\n"
                + "AND ANIO=" + (anio - 1) + "),0) as 'PROMEDIO'\n"
                + "\n"
                + "FROM   I_009_Rotacion_Inventarios_General P\n"
                + "where tipo='" + tipo + "'\n"
                + "group by P.mes ";

    }

    public static String I_003_Indicadores_Inventarios_Planta(Integer anio, String tipo, String valor/*, String mes*/ /*String valmaxmin,String planta*/) {
        return "select planta,\n"
                + "sum(case \n"
                + "when mes=1 then ifnull(" + valor + ",0)\n"
                + "else 0 end) as 'ene',\n"
                + "sum(case \n"
                + "when mes=2 then ifnull(" + valor + ",0)\n"
                + "else 0 end) as 'feb',\n"
                + "sum(case \n"
                + "when mes=3 then ifnull(" + valor + ",0)\n"
                + "else 0 end) as 'mar',\n"
                + "sum(case \n"
                + "when mes=4 then ifnull(" + valor + ",0)\n"
                + "else 0 end) as 'abr',\n"
                + "sum(case \n"
                + "when mes=5 then ifnull(" + valor + ",0)\n"
                + "else 0 end) as 'may',\n"
                + "sum(case \n"
                + "when mes=6 then ifnull(" + valor + ",0)\n"
                + "else 0 end) as 'jun',\n"
                + "sum(case \n"
                + "when mes=7 then ifnull(" + valor + ",0)\n"
                + "else 0 end) as 'jul',\n"
                + "sum(case \n"
                + "when mes=8 then ifnull(" + valor + ",0)\n"
                + "else 0 end) as 'ago',\n"
                + "sum(case \n"
                + "when mes=9 then ifnull(" + valor + ",0)\n"
                + "else 0 end) as 'sep',\n"
                + "sum(case \n"
                + "when mes=10 then ifnull(" + valor + ",0)\n"
                + "else 0 end) as 'oct',\n"
                + "sum(case \n"
                + "when mes=11 then ifnull(" + valor + ",0)\n"
                + "else 0 end) as 'nov',\n"
                + "sum(case \n"
                + "when mes=12 then ifnull(" + valor + ",0)\n"
                + "else 0 end) as 'dic'\n"
                + "\n"
                + "from I_009_Rotacion_Inventarios_Planta\n"
                + "where tipo='" + tipo + "' AND ANIO=" + anio + "\n"
                + "GROUP BY planta,anio";
    }

    public static String Consulta_Excel_Resumen(Integer anio) {
        return "SELECT tipo,indice,dias FROM INDICADORES.I_009_Rotacion_Inventarios_Promedio\n"
                + "WHERE ANIO=" + anio;
    }

    public static String Consulta_Excel_Detalle1(Integer anio, String tipo) {
        return "SELECT mes,indice,dias FROM INDICADORES.I_009_Rotacion_Inventarios_General where anio=" + anio + " and tipo = '" + tipo + "';";
    }

    public static String Consulta_Excel_Detalle2(Integer anio, String tipo, String planta) {
        return "SELECT mes,indice,dias,planta FROM INDICADORES.I_009_Rotacion_Inventarios_Planta\n"
                + "where anio=" + anio + "\n"
                + "and tipo='" + tipo + "' and planta = '" + planta + "' order by mes;";
    }
}
