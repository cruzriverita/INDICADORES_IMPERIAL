/* Devuelve los datos para la segunda tabla de los indicadores de produccion, se realizo un servlet
 aparte en donde solo cambia la consulta utilizada, esto por motivos de simplicidad a la hora de 
 cargar los datos y no sobrecargar con codigo el archivo js que procesa la informacion poniendole
 mas decision.*/
package Controladores;

import Modelo.Modelo_001_Indicadores_Produccion;
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
public class C_001_Produccion_Por_Planta_Servlet2 extends HttpServlet {

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
        mes = request.getParameter("mesjs");
        anio = request.getParameter("aniojs");
        String sql;

        sql = Modelo_001_Indicadores_Produccion.C_001_Produccion_Por_Planta2(mes, Integer.parseInt(anio));
        List<Map<String, Object>> resultList;
        resultList = conexion.select(sql);

        Iterator<Map<String, Object>> iterador = resultList.iterator();
        while (iterador.hasNext()) {
            Map<String, Object> mapa = iterador.next();

            String indicador = (String) mapa.get("Indicador");
            Float Cvalor1 = Float.parseFloat(mapa.get("RST").toString());
            Float Cvalor2 = Float.parseFloat(mapa.get("RSTP").toString());
            Float Cvalor3 = Float.parseFloat(mapa.get("RLRS").toString());
            Float Cvalor4 = Float.parseFloat(mapa.get("RLRSP").toString());
            Float Cvalor5 = Float.parseFloat(mapa.get("RSM").toString());
            Float Cvalor6 = Float.parseFloat(mapa.get("RSMP").toString());
            Float Cvalor7 = Float.parseFloat(mapa.get("KNIT").toString());
            Float Cvalor8 = Float.parseFloat(mapa.get("KNITP").toString());
            Float Cvalor9 = Float.parseFloat(mapa.get("FPS").toString());
            Float Cvalor10 = Float.parseFloat(mapa.get("FPSP").toString());
            Float Cvalor11 = Float.parseFloat(mapa.get("DPF").toString());
            Float Cvalor12 = Float.parseFloat(mapa.get("DPFP").toString());

            Cvalor1 = Utilidades.Metodos_Globales.redondear(Cvalor1, 2);
            Cvalor3 = Utilidades.Metodos_Globales.redondear(Cvalor3, 2);
            Cvalor5 = Utilidades.Metodos_Globales.redondear(Cvalor5, 2);
            Cvalor7 = Utilidades.Metodos_Globales.redondear(Cvalor7, 2);
            Cvalor9 = Utilidades.Metodos_Globales.redondear(Cvalor9, 2);
            Cvalor11 = Utilidades.Metodos_Globales.redondear(Cvalor11, 2);

            Cvalor2 = Utilidades.Metodos_Globales.redondear(Cvalor2, 2);
            Cvalor4 = Utilidades.Metodos_Globales.redondear(Cvalor4, 2);
            Cvalor6 = Utilidades.Metodos_Globales.redondear(Cvalor6, 2);
            Cvalor8 = Utilidades.Metodos_Globales.redondear(Cvalor8, 2);
            Cvalor10 = Utilidades.Metodos_Globales.redondear(Cvalor10, 2);
            Cvalor12 = Utilidades.Metodos_Globales.redondear(Cvalor12, 2);

            Obj = new JSONObject();

            try {
                Obj.put("indicador", indicador);
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

}
