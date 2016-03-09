/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.ConsultasBD;
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
public class I_003_Costo_Energia_Q_Servlet extends HttpServlet {

    String anio, mes, opcion;
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

        List ListaValores = new LinkedList();
        JSONObject responseObj = new JSONObject();

        JSONObject Obj = null;

        //Recuperar valores enviados desde el javascript.
        anio = request.getParameter("aniojs");
        opcion = request.getParameter("opcion");
        String sql = "";

        //Se evalua si el indicador es por costo monetario o por cantidad de KWH
        if (opcion.equals("money")) {
            sql = ConsultasBD.I_003_Costo_Energia_Q(anio);
        } else {
            sql = ConsultasBD.I_003_Costo_Energia_KWH(anio);
        }

        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList = conexion.select(sql);

        Iterator<Map<String, Object>> iterador = resultList.iterator();
        while (iterador.hasNext()) {
            Map<String, Object> mapa = iterador.next();

            //Columnas obtenidas del query
            String Nplanta = (String) mapa.get("Planta");
            Float Cvalor = Float.parseFloat(mapa.get("1").toString());
            Float Cvalor2 = Float.parseFloat(mapa.get("2").toString());
            Float Cvalor3 = Float.parseFloat(mapa.get("3").toString());
            Float Cvalor4 = Float.parseFloat(mapa.get("4").toString());
            Float Cvalor5 = Float.parseFloat(mapa.get("5").toString());
            Float Cvalor6 = Float.parseFloat(mapa.get("6").toString());
            Float Cvalor7 = Float.parseFloat(mapa.get("7").toString());
            Float Cvalor8 = Float.parseFloat(mapa.get("8").toString());
            Float Cvalor9 = Float.parseFloat(mapa.get("9").toString());
            Float Cvalor10 = Float.parseFloat(mapa.get("10").toString());
            Float Cvalor11 = Float.parseFloat(mapa.get("11").toString());
            Float Cvalor12 = Float.parseFloat(mapa.get("12").toString());
            Obj = new JSONObject();

            try {

                Obj.put("planta", Nplanta);
                Obj.put("valor1", Cvalor);                
                Obj.put("valor2", Cvalor2);
                Obj.put("valor3", Cvalor3);
                Obj.put("valor4", Cvalor4);
                Obj.put("valor5", Cvalor5);
                Obj.put("valor6", Cvalor6);
                Obj.put("valor7", Cvalor7);
                Obj.put("valor8", Cvalor8);
                Obj.put("valor9", Cvalor9);
                Obj.put("valor10", Cvalor10);
                Obj.put("valor11", Cvalor11);
                Obj.put("valor12", Cvalor12);

                ListaValores.add(Obj);
                responseObj.put("ListaValores", ListaValores);
            } catch (JSONException ex) {
                Logger.getLogger(I_003_Costo_Energia_Q_Servlet.class.getName()).log(Level.SEVERE, null, ex);
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