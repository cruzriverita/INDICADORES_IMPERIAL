/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.ConsultasBD_IndicadoresProduccion;
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
public class I_000_Produccion_Por_Planta_Commodity_Servlet extends HttpServlet {

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
            out.println("<title>Servlet I_000_Produccion_Por_Planta_Commodity_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet I_000_Produccion_Por_Planta_Commodity_Servlet at " + request.getContextPath() + "</h1>");
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
               
        Modelo.ConexionBD conexion = new Modelo.ConexionBD();
        List ListaValores = new LinkedList();
        JSONObject responseObj = new JSONObject();
        
        JSONObject Obj = null;

        //Recuperar valores enviados desde el javascript.
         String anio = request.getParameter("anio");
         String mes = request.getParameter("mes");
         String planta = request.getParameter("planta");
        String sql=ConsultasBD_IndicadoresProduccion.I_000_Produccion_Por_Planta_Mes_02(planta,anio, mes);
         
        
        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList=conexion.select(sql);
      
        Iterator<Map<String,Object>> iterador = resultList.iterator();
        while(iterador.hasNext()){
             Map<String,Object> mapa = iterador.next();
           
             String Nplanta =(String) mapa.get("COMMODITY_CODE");
             Float Cvalor = Float.parseFloat(mapa.get("VALOR").toString()); 
            
              Obj = new JSONObject();
           
            try {
                Obj.put("commodity", Nplanta);
                Obj.put("valor", Cvalor);
              
                 ListaValores.add(Obj);
                  responseObj.put("ListaValores", ListaValores);
            } catch (JSONException ex) {
                Logger.getLogger(I_000_Produccion_Por_Planta_Commodity_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }  
        response.getWriter().write(responseObj.toString());
        
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
