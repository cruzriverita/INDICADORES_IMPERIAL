/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Modelo_Indicadores_Financieros;
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
public class I_004_Indicadores_Financieros_Servlet extends HttpServlet {

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
        String anio, mes, opcion, indicador;
        List ListaValores = new LinkedList();
        JSONObject responseObj = new JSONObject();
        JSONObject Obj = null;

        //Recuperar valores enviados desde el javascript.
        mes = request.getParameter("mesjs");
        anio = request.getParameter("aniojs");
        opcion = request.getParameter("opcion");
        indicador = request.getParameter("indicador");

        String sql;

        if (opcion.equals("ALL")) {
            sql = Modelo_Indicadores_Financieros.Consulta_Financieros_General(Integer.parseInt(anio), mes, indicador);
            this.Generales(sql, ListaValores, Obj, responseObj, response);
        } else {

            sql = Modelo_Indicadores_Financieros.Consulta_Financieros_Detalle(Integer.parseInt(anio), indicador, opcion);
            this.Especifico(sql, ListaValores, Obj, responseObj, response);
        }

    }

    public void Generales(String sql, List ListaValores, JSONObject Obj, JSONObject responseObj, HttpServletResponse response) throws IOException {

        List<Map<String, Object>> resultList;
        resultList = conexion.select(sql);

        Iterator<Map<String, Object>> iterador = resultList.iterator();
        while (iterador.hasNext()) {
            Map<String, Object> mapa = iterador.next();

            String Empresa = String.valueOf(mapa.get("Empresa"));
            Float Cvalor = Float.parseFloat(mapa.get("anio").toString());
            Float Cvalor2 = Float.parseFloat(mapa.get("anio1").toString());
            Float Cvalor3 = Float.parseFloat(mapa.get("mejor").toString());
            Float Cvalor4 = Float.parseFloat(mapa.get("peor").toString());
            Float Cvalor6 = Float.parseFloat(mapa.get("promedio").toString());

            //Convertir a nombre de empresa
            Empresa = GetEmpresaNombre(Empresa);

            Obj = new JSONObject();

            try {
                Obj.put("planta", Empresa);
                Obj.put("valor1", Cvalor);
                Obj.put("valor2", Cvalor2);
                Obj.put("valor3", Cvalor3);
                Obj.put("valor4", Cvalor4);
                Obj.put("valor6", Cvalor6);

                ListaValores.add(Obj);
                responseObj.put("ListaValores", ListaValores);
            } catch (JSONException ex) {
                Logger.getLogger(I_001_Indicadores_Produccion_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (Obj == null) {
            response.getWriter().write("");
        } else {
            response.getWriter().write(responseObj.toString());
        }
    }

    public void Especifico(String sql, List ListaValores, JSONObject Obj, JSONObject responseObj, HttpServletResponse response) throws IOException {

        List<Map<String, Object>> resultList;
        resultList = conexion.select(sql);

        Iterator<Map<String, Object>> iterador = resultList.iterator();
        while (iterador.hasNext()) {
            Map<String, Object> mapa = iterador.next();

            String Nmes = Utilidades.MetodosGlobales.get_mes((Integer) mapa.get("Mes"));

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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String GetEmpresaNombre(String val) {
        String nombre;
        switch (val) {
            case "1":
                nombre = "IMPERIAL FASHION";
                break;
            case "2":
                nombre = "MT TEXTIL";
                break;

            default:
                nombre = "";
                break;

        }

        return nombre;
    }

}
