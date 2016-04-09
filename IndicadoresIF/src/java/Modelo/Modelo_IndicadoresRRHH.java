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
public class Modelo_IndicadoresRRHH {

    //valor, valmaxmin
    public static String I_007_NumeroEmpleados(Integer anio, String tipo) {
        return "SELECT P.periodo,\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO="+(anio-1)+"\n"
                + "THEN ifnull(P.No_Empleados,0)\n"
                + "ELSE 0\n"
                + "END) AS 'anio1',\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO="+anio+"\n"
                + "THEN ifnull(P.No_Empleados,0)\n"
                + "ELSE 0\n"
                + "END) AS 'anio2',\n"
                + "\n"
                + "(select max(No_Empleados) from I_007_Nomina_Planilla WHERE Tipo=P.TIPO) as 'MAYOR',\n"
                + "\n"
                + "\n"
                + "(SELECT periodo from I_007_Nomina_Planilla\n"
                + "where No_Empleados in (select max(No_Empleados) from I_007_Nomina_Planilla WHERE Tipo=P.TIPO) \n"
                + "LIMIT 1) as 'MAYORMES',\n"
                + "\n"
                + "(SELECT ANIO from I_007_Nomina_Planilla\n"
                + "where No_Empleados in (select max(No_Empleados) from I_007_Nomina_Planilla WHERE Tipo=P.TIPO ) \n"
                + "LIMIT 1) as 'MAYORANIO',\n"
                + "\n"
                + "(select min(No_Empleados) from I_007_Nomina_Planilla WHERE Tipo=P.TIPO) as 'MENOR',\n"
                + "\n"
                + "(SELECT periodo from I_007_Nomina_Planilla\n"
                + "where No_Empleados in (select min(No_Empleados) from I_007_Nomina_Planilla WHERE Tipo=P.TIPO ) \n"
                + "LIMIT 1) as 'MENORMES',\n"
                + "\n"
                + "(SELECT ANIO from I_007_Nomina_Planilla\n"
                + "where No_Empleados in (select min(No_Empleados) from I_007_Nomina_Planilla WHERE Tipo=P.TIPO) \n"
                + "LIMIT 1) as 'MENORANIO',\n"
                + "\n"
                + "ifnull((SELECT SUM(No_Empleados)/COUNT(*) FROM I_007_Nomina_Planilla WHERE tipo=P.Tipo\n"
                + "AND ANIO="+(anio-1)+"),0) as 'PROMEDIO'\n"
                + "\n"
                + "FROM   I_007_Nomina_Planilla P\n"
                + "where tipo='"+tipo+"'\n"
                + "group by P.periodo";
    }
}
