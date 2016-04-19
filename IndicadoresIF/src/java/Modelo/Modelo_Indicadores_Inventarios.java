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
public class Modelo_Indicadores_Inventarios {

    public static String Inventarios_Promedio_Indice(Integer anio, String tipoinv) {
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
                + "ifnull("+tipoinv+",0) as indice,\n"
                + "ifnull((select "+tipoinv+" from I_009_Rotacion_Inventarios_Promedio where anio="+(anio-1)+" and tipo = P.tipo),0) as 'indice2'\n"
                + "FROM INDICADORES.I_009_Rotacion_Inventarios_Promedio P\n"
                + "WHERE P.anio="+anio+"; ";
    }

}
