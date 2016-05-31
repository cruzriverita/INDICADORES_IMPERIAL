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
public class C_005_Indicadores_Calidad_Servlet extends HttpServlet {

   
    Modelo.ConexionBD conexion = new Modelo.ConexionBD();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet C_004_Indicadores_Financieros</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet C_004_Indicadores_Financieros at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
        mes = request.getParameter("mesjs");
        String sql;
        sql = Modelo_005_Indicadores_Calidad.Consulta_Tabla_Indicadores_Calidad(Integer.parseInt(anio), mes);

        List<Map<String, Object>> resultList;
        resultList = conexion.select(sql);

        Iterator<Map<String, Object>> iterador = resultList.iterator();
        while (iterador.hasNext()) {
            Map<String, Object> mapa = iterador.next();

            String ambito = String.valueOf(mapa.get("ambito"));
            String indicador = String.valueOf(mapa.get("indicador"));

            Float Cvalor1 = Float.parseFloat(mapa.get("RST").toString());
            Float Cvalor2 = Float.parseFloat(mapa.get("RSM").toString());
            Float Cvalor3 = Float.parseFloat(mapa.get("RLRS").toString());         
            Float Cvalor4 = Float.parseFloat(mapa.get("DPF").toString());
            Float Cvalor5 = Float.parseFloat(mapa.get("KNIT").toString());
            Float Cvalor6 = Float.parseFloat(mapa.get("FPS").toString());
            
            Float Cvalor7 = Float.parseFloat(mapa.get("PRS").toString());
            Float Cvalor8 = Float.parseFloat(mapa.get("PRSM").toString());
            Float Cvalor9 = Float.parseFloat(mapa.get("PRLRS").toString());         
            Float Cvalor10 = Float.parseFloat(mapa.get("PDPF").toString());
            Float Cvalor11 = Float.parseFloat(mapa.get("PKNIT").toString());
            Float Cvalor12 = Float.parseFloat(mapa.get("PFPS").toString());


            Cvalor1 = Utilidades.Metodos_Globales.redondear(Cvalor1, 2);
            Cvalor2 = Utilidades.Metodos_Globales.redondear(Cvalor2, 2);
            Cvalor3 = Utilidades.Metodos_Globales.redondear(Cvalor3, 2);         
            Cvalor4 = Utilidades.Metodos_Globales.redondear(Cvalor4, 2);
            Cvalor5 = Utilidades.Metodos_Globales.redondear(Cvalor5, 2);
            Cvalor6 = Utilidades.Metodos_Globales.redondear(Cvalor6, 2);
            
            Cvalor7 = Utilidades.Metodos_Globales.redondear(Cvalor7, 2);
            Cvalor8 = Utilidades.Metodos_Globales.redondear(Cvalor8, 2);
            Cvalor9 = Utilidades.Metodos_Globales.redondear(Cvalor9, 2);         
            Cvalor10 = Utilidades.Metodos_Globales.redondear(Cvalor10, 2);
            Cvalor11 = Utilidades.Metodos_Globales.redondear(Cvalor11, 2);
            Cvalor12 = Utilidades.Metodos_Globales.redondear(Cvalor12, 2);
          
           String  indicador2 = GetIndicador(indicador);
           String ambito2 = GetAmbito(ambito);

            Obj = new JSONObject();

            try {
                Obj.put("ambito", ambito2);
                Obj.put("indicador", indicador2);
                
                Obj.put("valor1", Cvalor1);
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
                Logger.getLogger(C_003_Indicadores_Inventarios_Servlet.class.getName()).log(Level.SEVERE, null, ex);
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

    private String GetIndicador(String val) {
        String name;

        switch (val) {
            case "CF":
                name = "Calidad Facturable";
                break;

            case "CNF":
                name = "Calidad No Facturable";
                break;
            case "SUB":
                name = "Subproducto";
                break;

            case "PT":
                name = "Produccion Total";
                break;

            default:
                name = "";
                break;
        }
        return name;
    }

        private String GetAmbito(String val) {
        String name;

        switch (val) {
            case "I":
                name = "Interna";
                break;

            case "E":
                name = "Externa";
                break;


            default:
                name = "";
                break;
        }
        return name;
    }
}
