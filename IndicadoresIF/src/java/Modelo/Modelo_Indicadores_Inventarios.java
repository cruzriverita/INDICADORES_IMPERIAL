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
                + "ifnull(" + "indice" + ",0) as indice,\n"
                + "ifnull((select " + "indice" + " from I_009_Rotacion_Inventarios_Promedio where anio=" + (anio - 1) + " and tipo = P.tipo),0) as 'indice2',\n"
               
                + "ifnull(" + "dias" + ",0) as dia,\n"
                + "ifnull((select " + "dias" + " from I_009_Rotacion_Inventarios_Promedio where anio=" + (anio - 1) + " and tipo = P.tipo),0) as 'dia2'\n"
                + "FROM INDICADORES.I_009_Rotacion_Inventarios_Promedio P\n"
                + "WHERE P.anio=" + anio + "; ";
    }

    public static String I_003_Indicadores_Inventarios_General(Integer anio, String tipo, String valor, String valmaxmin) {
         return "SELECT P.mes,\n"
         + "SUM( CASE \n"
         + "WHEN P.ANIO="+(anio-1)+"\n"
         + "THEN ifnull("+valor+",0)\n"
         + "ELSE 0\n"
         + "END) AS 'anio1',\n"
         + "SUM( CASE \n"
         + "WHEN P.ANIO="+anio+"\n"
         + "THEN ifnull("+valor+",0)\n"
         + "ELSE 0\n"
         + "END) AS 'anio2',\n"
         + "\n"
         + "(select max("+valmaxmin+") from I_009_Rotacion_Inventarios_General WHERE Tipo=P.TIPO) as 'MAYOR',\n"
         + "\n"
         + "\n"
         + "(SELECT mes from I_009_Rotacion_Inventarios_General\n"
         + "where "+valmaxmin+" in (select max("+valmaxmin+") from I_009_Rotacion_Inventarios_General WHERE Tipo=P.TIPO) \n"
         + "ORDER BY 1 DESC LIMIT 1) as 'MAYORMES',\n"
         + "\n"
         + "(SELECT ANIO from I_009_Rotacion_Inventarios_General\n"
         + "where "+valmaxmin+" in (select max("+valmaxmin+") from I_009_Rotacion_Inventarios_General WHERE Tipo=P.TIPO ) \n"
         + "ORDER BY 1 DESC LIMIT 1) as 'MAYORANIO',\n"
         + "\n"
         + "(select min("+valmaxmin+") from I_009_Rotacion_Inventarios_General WHERE Tipo=P.TIPO) as 'MENOR',\n"
         + "\n"
         + "(SELECT mes from I_009_Rotacion_Inventarios_General\n"
         + "where "+valmaxmin+" in (select min("+valmaxmin+") from I_009_Rotacion_Inventarios_General WHERE Tipo=P.TIPO ) \n"
         + "ORDER BY 1 ASC LIMIT 1) as 'MENORMES',\n"
         + "\n"
         + "(SELECT ANIO from I_009_Rotacion_Inventarios_General\n"
         + "where "+valmaxmin+" in (select min("+valmaxmin+") from I_009_Rotacion_Inventarios_General WHERE Tipo=P.TIPO) \n"
         + "ORDER BY 1 ASC LIMIT 1) as 'MENORANIO',\n"
         + "\n"
         + "ifnull((SELECT SUM("+valmaxmin+")/COUNT(*) FROM I_009_Rotacion_Inventarios_General WHERE tipo=P.Tipo\n"
         + "AND ANIO="+(anio-1)+"),0) as 'PROMEDIO'\n"
         + "\n"
         + "FROM   I_009_Rotacion_Inventarios_General P\n"
         + "where tipo='"+tipo+"'\n"
         + "group by P.mes";

   
    }
      
    
    
        public static String I_003_Indicadores_Inventarios_Planta(Integer anio, String tipo,String valor, String mes /*String valmaxmin,String planta*/) {
       /* return "SELECT P.mes,\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO="+(anio-1)+"\n"
                + "THEN ifnull("+valor+",0)\n"
                + "ELSE 0\n"
                + "END) AS 'anio1',\n"
                + "SUM( CASE \n"
                + "WHEN P.ANIO="+anio+"\n"
                + "THEN ifnull("+valor+",0)\n"
                + "ELSE 0\n"
                + "END) AS 'anio2',\n"
                + "\n"
                + "(select max("+valmaxmin+") from I_009_Rotacion_Inventarios_Planta WHERE Tipo=P.TIPO  and planta=P.Planta) as 'MAYOR',\n"
                + "\n"
                + "\n"
                + "(SELECT mes from I_009_Rotacion_Inventarios_Planta\n"
                + "where "+valmaxmin+" in (select max("+valmaxmin+") from I_009_Rotacion_Inventarios_Planta WHERE Tipo=P.TIPO  and planta=P.Planta) \n"
                + "LIMIT 1) as 'MAYORMES',\n"
                + "\n"
                + "(SELECT ANIO from I_009_Rotacion_Inventarios_Planta\n"
                + "where "+valmaxmin+" in (select max("+valmaxmin+") from I_009_Rotacion_Inventarios_Planta WHERE Tipo=P.TIPO  and planta=P.Planta ) \n"
                + "LIMIT 1) as 'MAYORANIO',\n"
                + "\n"
                + "(select min("+valmaxmin+") from I_009_Rotacion_Inventarios_Planta WHERE Tipo=P.TIPO  and planta=P.Planta) as 'MENOR',\n"
                + "\n"
                + "(SELECT mes from I_009_Rotacion_Inventarios_Planta\n"
                + "where "+valmaxmin+" in (select min("+valmaxmin+") from I_009_Rotacion_Inventarios_Planta WHERE Tipo=P.TIPO  and planta=P.Planta) \n"
                + "LIMIT 1) as 'MENORMES',\n"
                + "\n"
                + "(SELECT ANIO from I_009_Rotacion_Inventarios_Planta\n"
                + "where "+valmaxmin+" in (select min("+valmaxmin+") from I_009_Rotacion_Inventarios_Planta WHERE Tipo=P.TIPO  and planta=P.Planta) \n"
                + "LIMIT 1) as 'MENORANIO',\n"
                + "\n"
                + "ifnull((SELECT SUM("+valmaxmin+")/COUNT(*) FROM I_009_Rotacion_Inventarios_Planta WHERE tipo=P.Tipo  and planta=P.Planta \n"
                + "AND ANIO="+(anio-1)+"),0) as 'PROMEDIO'\n"
                + "\n"
                + "FROM   I_009_Rotacion_Inventarios_Planta P\n"
                + "where tipo='"+tipo+"'  AND P.planta='"+planta+"'\n"
                + "group by P.mes, P.planta";]*/
            return "select planta,sum("+valor+") as 'valor'\n"
                    + "from I_009_Rotacion_Inventarios_Planta\n"
                    + "where tipo='"+tipo+"'\n"
                    + "and MES="+mes+" and anio="+anio+"\n"
                    + "GROUP BY planta,anio";
    }


}
