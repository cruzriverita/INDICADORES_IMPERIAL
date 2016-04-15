/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;


import java.math.BigDecimal;

import java.util.Calendar;


/**
 *
 * @author rcruz
 */
public class MetodosGlobales {

    static Calendar c = Calendar.getInstance();

    public static int year_actual = c.get(Calendar.YEAR);
    public static int month_actual = c.get(Calendar.MONTH);

    public static String get_mes(int mes) {
        String nmes = "";
        if (mes == 1) {
            nmes = "Ene";
        }
        if (mes == 2) {
            nmes = "Feb";
        }
        if (mes == 3) {
            nmes = "Mar";
        }
        if (mes == 4) {
            nmes = "Abr";
        }
        if (mes == 5) {
            nmes = "May";
        }
        if (mes == 6) {
            nmes = "Jun";
        }
        if (mes == 7) {
            nmes = "Jul";
        }
        if (mes == 8) {
            nmes = "Ago";
        }
        if (mes == 9) {
            nmes = "Sep";
        }
        if (mes == 10) {
            nmes = "Oct";
        }
        if (mes == 11) {
            nmes = "Nov";
        }
        if (mes == 12) {
            nmes = "Dic";
        }
        return nmes;
    }

    public static float redondear(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    
   
     public static String get_nombre_mes(int mes) {
        String nmes = "";
        if (mes == 1) {
            nmes = "Enero";
        }
        if (mes == 2) {
            nmes = "Febrero";
        }
        if (mes == 3) {
            nmes = "Marzo";
        }
        if (mes == 4) {
            nmes = "Abril";
        }
        if (mes == 5) {
            nmes = "Mayo";
        }
        if (mes == 6) {
            nmes = "Junio";
        }
        if (mes == 7) {
            nmes = "Julio";
        }
        if (mes == 8) {
            nmes = "Agosto";
        }
        if (mes == 9) {
            nmes = "Septiembre";
        }
        if (mes == 10) {
            nmes = "Octubre";
        }
        if (mes == 11) {
            nmes = "Noviembre";
        }
        if (mes == 12) {
            nmes = "Diciembre";
        }
        return nmes;
    }
  
}
