package Controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author rcruz
 */
public class I_003_Indicadores_Inventarios_Servlet extends HttpServlet {

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

        String anio, articulo, tipo, indicador;

        //Recuperar valores enviados desde el javascript.
        articulo = request.getParameter("artjs");
        anio = request.getParameter("aniojs");
        tipo = request.getParameter("tipojs");
        indicador = request.getParameter("indicador");

        String sql;

        if (null != indicador) {
            switch (indicador) {

                //Si es el indicador de Rotacion de inventarios
                case "INDICADOR9":
                    if (tipo.equals("1")) {
                        sql = Modelo.Modelo_003_Indicadores_Inventarios.I_003_Indicadores_Inventarios_General(Integer.parseInt(anio), this.GetArticulo(articulo), "P.indice", "indice");
                        this.Generales(sql, response);

                    } else {
                        sql = Modelo.Modelo_003_Indicadores_Inventarios.I_003_Indicadores_Inventarios_General(Integer.parseInt(anio), this.GetArticulo(articulo), "P.dias", "dias");
                        this.Generales(sql, response);
                    }
                    break;

                //si es el indicador de "promedio devengado por empleado"
                case "INDICADOR10":
                    if (tipo.equals("1")) {
                        sql = Modelo.Modelo_002_Indicadores_RRHH.I_002_IndicadoresRRHH_Consulta(Integer.parseInt(anio), "N", "P.Devengado/P.No_Empleados", "Devengado/No_Empleados");
                        this.Generales(sql, response);
                    } else {

                        sql = Modelo.Modelo_002_Indicadores_RRHH.I_002_IndicadoresRRHH_Consulta(Integer.parseInt(anio), "P", "P.Devengado/P.No_Empleados", "Devengado/No_Empleados");
                        this.Generales(sql, response);
                    }
                    break;
            }
        }

    }

    public void Generales(String sql, HttpServletResponse response) throws IOException {

        String cadena = "";
        String mayormes = "";
        String menormes = "";
        Integer mayoranio = 0;
        Integer menoranio = 0;
        List<Map<String, Object>> resultList;
        resultList = conexion.select(sql);

        Iterator<Map<String, Object>> iterador = resultList.iterator();
        while (iterador.hasNext()) {
            Map<String, Object> mapa = iterador.next();

            String periodo = String.valueOf(mapa.get("mes"));

            Float Cvalor = Float.parseFloat(mapa.get("anio1").toString());
            Float Cvalor2 = Float.parseFloat(mapa.get("anio2").toString());
            Float Cvalor3 = Float.parseFloat(mapa.get("MAYOR").toString());
            String Cvalor4 = String.valueOf(mapa.get("MAYORMES"));
            Integer Cvalor5 = Integer.parseInt(mapa.get("MAYORANIO").toString());
            Float Cvalor6 = Float.parseFloat(mapa.get("MENOR").toString());
            String Cvalor7 = String.valueOf(mapa.get("MENORMES"));
            Integer Cvalor8 = Integer.parseInt(mapa.get("MENORANIO").toString());
            Float Cvalor9 = Float.parseFloat(mapa.get("PROMEDIO").toString());

            if (cadena.equals("")) {
                cadena = periodo + "," + Cvalor + "," + Cvalor3 + "," + Cvalor9 + "," + Cvalor6 + "," + Cvalor2;
            } else {
                cadena = cadena + "," + periodo + "," + Cvalor + "," + Cvalor3 + "," + Cvalor9 + "," + Cvalor6 + "," + Cvalor2;
            }
            mayormes = Cvalor4;
            menormes = Cvalor7;
            mayoranio = Cvalor5;
            menoranio = Cvalor8;

        }

        /*Se devuelve una cadena separada por comas la cual se procesa en el javascript correspondiente y 
         a partir de tales datos se crean los vectores para realizar las graficas*/
        response.getWriter().write(cadena + "," + mayormes + "," + menormes + "," + mayoranio + "," + menoranio);

    }

    public String GetArticulo(String valor_Select) {
        switch (valor_Select) {
            case "1":
                return "A";
            case "2":
                return "PF";
            case "3":
                return "HP";
            case "4":
                return "HC";
            case "5":
                return "TC";
            case "6":
                return "TT";
            default:
                return "";
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
