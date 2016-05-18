/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Modelo_004_Indicadores_Financieros;
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
public class C_004_Indicadores_Financieros_Servlet extends HttpServlet {

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
        anio = request.getParameter("aniojs");
        mes = request.getParameter("mesjs");
        String sql;
        sql = Modelo_004_Indicadores_Financieros.Consulta_Tabla_Indicadores_Financieros(Integer.parseInt(anio), mes);

        List<Map<String, Object>> resultList;
        resultList = conexion.select(sql);

        Iterator<Map<String, Object>> iterador = resultList.iterator();
        while (iterador.hasNext()) {
            Map<String, Object> mapa = iterador.next();

            String tipo = String.valueOf(mapa.get("Nindicador"));

            Float Cvalor1 = Float.parseFloat(mapa.get("Imperial Fashion").toString());
            Float Cvalor2 = Float.parseFloat(mapa.get("MT Textil").toString());
            Float Cvalor3 = Float.parseFloat(mapa.get("Blake").toString());

            Float Cvalor4 = Float.parseFloat(mapa.get("pif").toString());
            Float Cvalor5 = Float.parseFloat(mapa.get("pmt").toString());
            Float Cvalor6 = Float.parseFloat(mapa.get("pbl").toString());
            Cvalor1 = Utilidades.Metodos_Globales.redondear(Cvalor1, 2);
            Cvalor2 = Utilidades.Metodos_Globales.redondear(Cvalor2, 2);
            Cvalor3 = Utilidades.Metodos_Globales.redondear(Cvalor3, 2);
            Cvalor4 = Utilidades.Metodos_Globales.redondear(Cvalor4, 2);
            Cvalor5 = Utilidades.Metodos_Globales.redondear(Cvalor5, 2);
            Cvalor6 = Utilidades.Metodos_Globales.redondear(Cvalor6, 2);

            tipo = GetNombreIndicador(tipo);

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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public String GetNombreIndicador(String indicador) {
        String name;
        switch (indicador) {

            case "1":
                name = "Liquides Corriente";
                break;

            case "2":
                name = "Prueba Acida";
                break;

            case "3":
                name = "Apalancamiento Financiero";
                break;

            case "4":
                name = "Indice de endeudamiento";
                break;

            case "5":
                name = "Rentabilidad Neta Del Activo";
                break;

            case "6":
                name = "Margen Bruto";
                break;

            case "7":
                name = "Margen Operacional";
                break;

            case "8":
                name = "Rendimiento sobre Activos Totales";
                break;

            case "9":
                name = "Impacto de Gastos Administrativos y Ventas";
                break;

            case "10":
                name = "Impacto Carga Financiera";
                break;

            case "11":
                name = "Total pagado por multas y retificaciones";
                break;

            default:
                name = "";
                break;

        }

        return name;
    }

}
