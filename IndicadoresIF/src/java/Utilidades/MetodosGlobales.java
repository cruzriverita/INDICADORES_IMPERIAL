/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Controladores.I_003_KgProducidos_MRS_Servlet;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

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
    
    
                 public static void Generales(String sql, List ListaValores, JSONObject Obj, JSONObject responseObj, HttpServletResponse response,Modelo.ConexionBD conexion) throws IOException {
         List<Map<String, Object>> resultList = new ArrayList<>();
            resultList = conexion.select(sql);

            Iterator<Map<String, Object>> iterador = resultList.iterator();
            while (iterador.hasNext()) {
                Map<String, Object> mapa = iterador.next();

                String Nplanta = (String) mapa.get("Planta");

                Float Cvalor = Float.parseFloat(mapa.get("anio").toString());
                Float Cvalor2 = Float.parseFloat(mapa.get("anio1").toString());
                Float Cvalor3 = Float.parseFloat(mapa.get("mejor").toString());
                Float Cvalor4 = Float.parseFloat(mapa.get("Acumulado").toString());
                Float Cvalor5 = Float.parseFloat(mapa.get("Acumulado1").toString());
                Float Cvalor6 = Float.parseFloat(mapa.get("PROMEDIO").toString());
                Obj = new JSONObject();

                try {
                    Obj.put("planta", Nplanta);
                    Obj.put("valor1", Cvalor);
                    Obj.put("valor2", Cvalor2);
                    Obj.put("valor3", Cvalor3);
                    Obj.put("valor4", Cvalor4);
                    Obj.put("valor5", Cvalor5);
                    Obj.put("valor6", Cvalor6);
                    ListaValores.add(Obj);
                    responseObj.put("ListaValores", ListaValores);
                } catch (JSONException ex) {
                    Logger.getLogger(I_003_KgProducidos_MRS_Servlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (Obj == null) {
                response.getWriter().write("");
            } else {
                response.getWriter().write(responseObj.toString());
            }
             }

}
