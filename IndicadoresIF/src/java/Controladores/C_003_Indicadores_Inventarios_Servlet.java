/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Modelo_IndicadoresProduccion;
import Modelo.Modelo_Indicadores_Inventarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class C_003_Indicadores_Inventarios_Servlet extends HttpServlet {

     Modelo.ConexionBD conexion = new Modelo.ConexionBD();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet I_000_Produccion_Por_Planta_Mes</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet I_000_Produccion_Por_Planta_Mes at " + request.getContextPath() + "</h1>");
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

        String anio, mes;
        List ListaValores = new LinkedList();
        JSONObject responseObj = new JSONObject();
        JSONObject Obj = null;

        //Recuperar valores enviados desde el javascript.
       // opcion = request.getParameter("opcionjs");
        anio = request.getParameter("aniojs");
        mes=request.getParameter("mesjs");
        String sql;
        sql = Modelo_Indicadores_Inventarios.Inventarios_tabla_general(Integer.parseInt(anio),mes);

        /*if (opcion.equals("1")){
        sql = Modelo_Indicadores_Inventarios.Inventarios_tabla_general(Integer.parseInt(anio),"indice");
        }
        else
        {
            sql = Modelo_Indicadores_Inventarios.Inventarios_tabla_general(Integer.parseInt(anio),"dias");
        }
        */
        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList = conexion.select(sql);

        Iterator<Map<String, Object>> iterador = resultList.iterator();
        while (iterador.hasNext()) {
            Map<String, Object> mapa = iterador.next();

            String tipo = (String) mapa.get("tipo");
            Float Cvalor1 = Float.parseFloat(mapa.get("indicea").toString());
            Float Cvalor2 = Float.parseFloat(mapa.get("indicev").toString());
            Float Cvalor3 = Float.parseFloat(mapa.get("indicep").toString());
            
            Float Cvalor4 = Float.parseFloat(mapa.get("diaa").toString());
            Float Cvalor5 = Float.parseFloat(mapa.get("diav").toString());
            Float Cvalor6 = Float.parseFloat(mapa.get("diap").toString());

            Cvalor1 = Utilidades.MetodosGlobales.redondear(Cvalor1, 2);
            Cvalor2 = Utilidades.MetodosGlobales.redondear(Cvalor2, 2);
            Cvalor3 = Utilidades.MetodosGlobales.redondear(Cvalor3, 2);
            Cvalor4 = Utilidades.MetodosGlobales.redondear(Cvalor4, 2);
            Cvalor5 = Utilidades.MetodosGlobales.redondear(Cvalor5, 2);
            Cvalor6 = Utilidades.MetodosGlobales.redondear(Cvalor6, 2);
            
            Obj = new JSONObject();

            try {
                Obj.put("tipo", tipo);
                Obj.put("valor1", Cvalor1);
                Obj.put("valor2", Cvalor2);
                Obj.put("valor3", Cvalor3);
                Obj.put("valor4", Cvalor4);
                Obj.put("valor5", Cvalor5);
                Obj.put("valor6", Cvalor6);

                ListaValores.add(Obj);
                responseObj.put("ListaValores", ListaValores);
            } catch (JSONException ex) {
                Logger.getLogger(C_003_Indicadores_Inventarios_Servlet.class.getName()).log(Level.SEVERE, null, ex);
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


}
