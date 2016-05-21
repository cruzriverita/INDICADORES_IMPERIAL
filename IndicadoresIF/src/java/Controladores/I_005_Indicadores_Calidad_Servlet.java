/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Modelo_005_Indicadores_Calidad;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author rcruz
 */
public class I_005_Indicadores_Calidad_Servlet extends HttpServlet {

    Modelo.ConexionBD conexion = new Modelo.ConexionBD();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet General_Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet General_Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String anio, mes, opcion, indicador, amb;
        List ListaValores = new LinkedList();
        JSONObject responseObj = new JSONObject();
        JSONObject Obj = null;

        //Recuperar valores enviados desde el javascript.
        mes = request.getParameter("mesjs");
        anio = request.getParameter("aniojs");
        opcion = request.getParameter("opcion");
        indicador = request.getParameter("indicador");
        amb = request.getParameter("amb");

        String sql;

        if (opcion.equals("ALL")) {
            sql = Modelo_005_Indicadores_Calidad.Consulta_Calidad_General(Integer.parseInt(anio), mes, GetIndicador(indicador));
            this.Generales(sql, ListaValores, Obj, responseObj, response);
        } else {
            sql = Modelo_005_Indicadores_Calidad.Consulta_Calidad_Detalle(Integer.parseInt(anio), GetIndicador(indicador), GetPlanta(opcion),amb);
            this.Especifico(sql, ListaValores, Obj, responseObj, response);
        }

    }

    //Devuelve todas las empresas.
    public void Generales(String sql, List ListaValores, JSONObject Obj, JSONObject responseObj, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> resultList;
        resultList = conexion.select(sql);
        Iterator<Map<String, Object>> iterador = resultList.iterator();
        while (iterador.hasNext()) {
            Map<String, Object> mapa = iterador.next();

            String planta = String.valueOf(mapa.get("planta"));
            Float Cvalor = Float.parseFloat(mapa.get("anio").toString());
            Float Cvalor2 = Float.parseFloat(mapa.get("anio1").toString());
            Float Cvalor3 = Float.parseFloat(mapa.get("mejor").toString());
            Float Cvalor4 = Float.parseFloat(mapa.get("peor").toString());
            Float Cvalor6 = Float.parseFloat(mapa.get("promedio").toString());

            Obj = new JSONObject();

            try {
                Obj.put("planta", planta);
                Obj.put("valor1", Cvalor);
                Obj.put("valor2", Cvalor2);
                Obj.put("valor3", Cvalor3);
                Obj.put("valor4", Cvalor4);
                Obj.put("valor6", Cvalor6);

                ListaValores.add(Obj);
                responseObj.put("ListaValores", ListaValores);
            } catch (JSONException ex) {
                Logger.getLogger(I_005_Indicadores_Calidad_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (Obj == null) {
            response.getWriter().write("");
        } else {
            response.getWriter().write(responseObj.toString());
        }
    }

    //Devuelve los valores de un año para una empresa especifica.
    
    public void Especifico(String sql, List ListaValores, JSONObject Obj, JSONObject responseObj, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> resultList;
        resultList = conexion.select(sql);

        Iterator<Map<String, Object>> iterador = resultList.iterator();
        while (iterador.hasNext()) {
            Map<String, Object> mapa = iterador.next();

            String Nmes = Utilidades.Metodos_Globales.get_mes((Integer) mapa.get("mes"));

            Float Cvalor = Float.parseFloat(mapa.get("anio").toString());
            Float Cvalor2 = Float.parseFloat(mapa.get("anio1").toString());
            Float Cvalor3 = Float.parseFloat(mapa.get("mejor").toString());
            Float Cvalor4 = Float.parseFloat(mapa.get("peor").toString());
            Float Cvalor6 = Float.parseFloat(mapa.get("promedio").toString());
            String mejormes = String.valueOf(mapa.get("mejormes"));
            String mejoranio = String.valueOf(mapa.get("mejoranio"));
            String peormes = String.valueOf(mapa.get("peormes"));
            String peoranio = String.valueOf(mapa.get("peoranio"));
            Obj = new JSONObject();

            try {
                Obj.put("mes", Nmes);
                Obj.put("valor1", Cvalor);
                Obj.put("valor2", Cvalor2);
                Obj.put("valor3", Cvalor3);
                Obj.put("valor4", Cvalor4);
                Obj.put("valor6", Cvalor6);
                Obj.put("mejormes", mejormes);
                Obj.put("mejoranio", mejoranio);
                Obj.put("peormes", peormes);
                Obj.put("peoranio", peoranio);

                ListaValores.add(Obj);
                responseObj.put("ListaValores", ListaValores);
            } catch (JSONException ex) {
                Logger.getLogger(C_001_Produccion_Por_Planta_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (Obj == null) {
            response.getWriter().write("");
        } else {
            response.getWriter().write(responseObj.toString());
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";  
    }// </editor-fold>

    /*Devuelve el valor String del indicador segun el valor obtenido del select html*/
    public String GetIndicador(String value) {
        String nombre;
        switch (value) {
            case "1":
                nombre = "CF";
                break;

            case "2":
                nombre = "CNF";
                break;

            case "3":
                nombre = "SUB";
                break;

            default:
                nombre = "";
                break;
        }
        return nombre;
    }

    /*Devuelve el nombre de la planta de acuerdo al valor elegido en el select html*/
    public String GetPlanta(String value) {
        String nombre;
        switch (value) {
            case "1":
                nombre = "RST";
                break;

            case "2":
                nombre = "RSM";
                break;

            case "3":
                nombre = "RLRS";
                break;

            case "4":
                nombre = "FPS";
                break;

            case "5":
                nombre = "KNIT";
                break;

            case "6":
                nombre = "DPF";
                break;

            default:
                nombre = "";
                break;
        }
        return nombre;
    }

}
