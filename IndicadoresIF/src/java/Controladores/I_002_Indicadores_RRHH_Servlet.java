/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


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
public class I_002_Indicadores_RRHH_Servlet extends HttpServlet {
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
            out.println("<title>Servlet IndicadoresRRHH_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IndicadoresRRHH_Servlet at " + request.getContextPath() + "</h1>");
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
       
        String anio, mes, tipo, indicador;
        List ListaValores = new LinkedList();
        JSONObject responseObj = new JSONObject();
        JSONObject Obj = null;

        //Recuperar valores enviados desde el javascript.
        mes = request.getParameter("mesjs");
        anio = request.getParameter("aniojs");
        tipo = request.getParameter("tipojs");
        indicador = request.getParameter("indicador");

        String sql = "";
        
         if ("INDICADOR7".equals(indicador)) {
            if (tipo.equals("1")) {
                sql = Modelo.Modelo_IndicadoresRRHH.I_002_IndicadoresRRHH_Consulta(Integer.parseInt(anio), "N","P.No_Empleados","No_Empleados");
             this.Generales(sql, ListaValores, Obj, responseObj, response);
            }
            else {
                //sql = Modelo_IndicadoresProduccion.I_001_Kilos_Producidos_Hora_Hombre_Detalle(mes, Integer.parseInt(anio), opcion);
                sql = sql = Modelo.Modelo_IndicadoresRRHH.I_002_IndicadoresRRHH_Consulta(Integer.parseInt(anio), "P","P.No_Empleados","No_Empleados");
                 this.Generales(sql, ListaValores, Obj, responseObj, response);
            }
        } 
        else if ("INDICADOR8".equals(indicador)) {
            if (tipo.equals("1")) {
                sql = Modelo.Modelo_IndicadoresRRHH.I_002_IndicadoresRRHH_Consulta(Integer.parseInt(anio), "N", "P.Devengado/P.No_Empleados", "Devengado/No_Empleados");
                this.Generales(sql, ListaValores, Obj, responseObj, response);
            } else {
                
                sql = Modelo.Modelo_IndicadoresRRHH.I_002_IndicadoresRRHH_Consulta(Integer.parseInt(anio), "P", "P.Devengado/P.No_Empleados", "Devengado/No_Empleados");
                this.Generales(sql, ListaValores, Obj, responseObj, response);
            }
        }

    }
    
     public void Generales(String sql, List ListaValores, JSONObject Obj, JSONObject responseObj, HttpServletResponse response) throws IOException {
        //sql = Modelo_IndicadoresProduccion.I_001_Kilos_Producidos_Hora_Hombre_General(mes, Integer.parseInt(anio));

        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList = conexion.select(sql);

        Iterator<Map<String, Object>> iterador = resultList.iterator();
        while (iterador.hasNext()) {
            Map<String, Object> mapa = iterador.next();

            String periodo = String.valueOf(mapa.get("periodo"));

            Float Cvalor = Float.parseFloat(mapa.get("anio1").toString());
            Float Cvalor2 = Float.parseFloat(mapa.get("anio2").toString());
            Float Cvalor3 = Float.parseFloat(mapa.get("MAYOR").toString());
             String Cvalor4 = String.valueOf(mapa.get("MAYORMES"));
            Integer Cvalor5 = Integer.parseInt(mapa.get("MAYORANIO").toString());
            Float Cvalor6 = Float.parseFloat(mapa.get("MENOR").toString());
            String Cvalor7 = String.valueOf(mapa.get("MENORMES"));
            Integer Cvalor8 = Integer.parseInt(mapa.get("MENORANIO").toString());           
            Float Cvalor9 = Float.parseFloat(mapa.get("PROMEDIO").toString());

            Obj = new JSONObject();

            try {
                Obj.put("periodo", periodo);
                Obj.put("valor1", Cvalor);
                Obj.put("valor2", Cvalor2);
                Obj.put("valor3", Cvalor3);
                Obj.put("valor4", Cvalor4);
                Obj.put("valor5", Cvalor5);
                Obj.put("valor6", Cvalor6);
                Obj.put("valor7", Cvalor7);
                Obj.put("valor8", Cvalor8);
                Obj.put("valor9", Cvalor9);

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
