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
public class Modelo_002_Indicadores_RRHH {

    public static String I_002_IndicadoresRRHH_Consulta(Integer anio, String tipo, String valor, String valmaxmin) {
        return "SELECT P.periodo,\n"
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
                + "(select max(" + valmaxmin + ") from I_007_Nomina_Planilla WHERE Tipo=P.TIPO) as 'MAYOR',\n"
                + "(SELECT periodo from I_007_Nomina_Planilla\n"
                + "where " + valmaxmin + " in (select max(" + valmaxmin + ") from I_007_Nomina_Planilla WHERE Tipo=P.TIPO) \n"
                + "ORDER BY 1 DESC LIMIT 1) as 'MAYORMES',\n"
                + "\n"
                + "(SELECT ANIO from I_007_Nomina_Planilla\n"
                + "where " + valmaxmin + " in (select max(" + valmaxmin + ") from I_007_Nomina_Planilla WHERE Tipo=P.TIPO ) \n"
                + "ORDER BY 1 DESC LIMIT 1) as 'MAYORANIO',\n"
                + "\n"
                + "(select min(" + valmaxmin + ") from I_007_Nomina_Planilla WHERE Tipo=P.TIPO) as 'MENOR',\n"
                + "\n"
                + "(SELECT periodo from I_007_Nomina_Planilla\n"
                + "where " + valmaxmin + " in (select min(" + valmaxmin + ") from I_007_Nomina_Planilla WHERE Tipo=P.TIPO ) \n"
                + "ORDER BY 1 ASC LIMIT 1) as 'MENORMES',\n"
                + "\n"
                + "(SELECT ANIO from I_007_Nomina_Planilla\n"
                + "where " + valmaxmin + " in (select min(" + valmaxmin + ") from I_007_Nomina_Planilla WHERE Tipo=P.TIPO) \n"
                + "ORDER BY 1 ASC LIMIT 1) as 'MENORANIO',\n"
                + "\n"
                + "ifnull((SELECT SUM(" + valmaxmin + ")/COUNT(*) FROM I_007_Nomina_Planilla WHERE tipo=P.Tipo\n"
                + "AND ANIO=" + (anio - 1) + "),0) as 'PROMEDIO'\n"
                + "\n"
                + "FROM   I_007_Nomina_Planilla P\n"
                + "where tipo='" + tipo + "'\n"
                + "group by P.periodo";
    }

    public static String Consulta_Excel_Indicadores_RRHH(Integer anio, String tipo) {
        return "select *, \n"
                + "t.anio2-t.anio1 as 'NoEmpleados' ,\n"
                + "t.devengado2-t.devengado1 as 'Devengado',\n"
                + "(t.devengado2-t.devengado1)/t.devengado1 as 'Porcentaje'\n"
                + "\n"
                + "\n"
                + "from (\n"
                + "SELECT P.periodo,\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + (anio - 1) + "\n"
                + "THEN ifnull(P.No_Empleados,0)\n"
                + "ELSE 0\n"
                + "END) AS 'anio1',\n"
                + "\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + (anio - 1) + "\n"
                + "THEN ifnull(P.Devengado,0)\n"
                + "ELSE 0\n"
                + "END) AS 'Devengado1',\n"
                + "\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + anio + "\n"
                + "THEN ifnull(P.No_Empleados,0)\n"
                + "ELSE 0\n"
                + "END) AS 'anio2',\n"
                + "\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO=" + anio + "\n"
                + "THEN ifnull(P.Devengado,0)\n"
                + "ELSE 0\n"
                + "END) AS 'Devengado2'\n"
                + "\n"
                + "FROM   I_007_Nomina_Planilla P\n"
                + "where tipo='" + tipo + "'\n"
                + "group by P.periodo\n"
                + ") as t";
    }
}
