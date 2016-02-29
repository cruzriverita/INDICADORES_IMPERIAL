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
public class I_000_Produccion_Por_Planta_Mes_Servlet extends HttpServlet {

    String anio,mes;
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
         mes = request.getParameter("mesjs");
              
        String sql=ConsultasBD.I_000_Produccion_Por_Planta_Mes_01(anio, mes);
         
        
        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList=conexion.select(sql);
      
        Iterator<Map<String,Object>> iterador = resultList.iterator();
        while(iterador.hasNext()){
             Map<String,Object> mapa = iterador.next();
           
             String Nplanta =(String) mapa.get("PLANTA");
             Float Cvalor = Float.parseFloat(mapa.get("VALOR").toString()); //.parseInt( mapa.get("VALOR").toString());
            
              Obj = new JSONObject();
           
            try {
                Obj.put("planta", Nplanta);
                Obj.put("valor", Cvalor);
              
                 ListaValores.add(Obj);
                  responseObj.put("ListaValores", ListaValores);
            } catch (JSONException ex) {
                Logger.getLogger(I_000_Produccion_Por_Planta_Mes_Servlet.class.getName()).log(Level.SEVERE, null, ex);
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
