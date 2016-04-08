/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivosXLS;

import Utilidades.Metodos_Generales_Excel;
import Modelo.ConsultasBD_IndicadoresProduccion;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.format.Colour;
import jxl.write.Formula;
import jxl.write.WritableCell;
import jxl.write.WritableFont;

/**
 *
 * @author rcruz
 */
public class C_001_Produccion_Por_Planta_XLS extends HttpServlet {

    Modelo.ConexionBD conexion = new Modelo.ConexionBD();
    ArrayList<String> indicador = new ArrayList<>();
    ArrayList<Double> valoresRST = new ArrayList<>();
    ArrayList<Double> valoresKNIT = new ArrayList<>();
    ArrayList<Double> valoresDPF = new ArrayList<>();
    ArrayList<Double> valoresRLRS = new ArrayList<>();
    ArrayList<Double> valoresFPS = new ArrayList<>();
    ArrayList<Double> valoresRSM = new ArrayList<>();
    String anio = "";
    String mes = "";
    String planta = "";

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
            out.println("<title>Servlet C_001_Produccion_Por_Planta_XLS</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet C_001_Produccion_Por_Planta_XLS at " + request.getContextPath() + "</h1>");
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
        anio = request.getParameter("anio");
        mes = request.getParameter("mes");
        planta = request.getParameter("planta");
        OutputStream out = null;

        try {
            String sql = ConsultasBD_IndicadoresProduccion.C_001_Produccion_Por_Planta(mes, Integer.parseInt(anio));
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition",
                    "attachment; filename=Indicadores Produccion" + ".xls");

            WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
            WritableSheet s = w.createSheet("Indicadores Produccion", 0);

            List<Map<String, Object>> resultList = new ArrayList<>();
            resultList = conexion.select(sql);

            Iterator<Map<String, Object>> iterador = resultList.iterator();
            while (iterador.hasNext()) {
                Map<String, Object> mapa = iterador.next();
                String nindicador = (String) mapa.get("Indicador");
                Double Cvalor1 = Double.parseDouble(mapa.get("RST").toString());
                Double Cvalor2 = Double.parseDouble(mapa.get("KNIT").toString());
                Double Cvalor3 = Double.parseDouble(mapa.get("DPF").toString());
                Double Cvalor4 = Double.parseDouble(mapa.get("RLRS").toString());
                Double Cvalor5 = Double.parseDouble(mapa.get("FPS").toString());
                Double Cvalor6 = Double.parseDouble(mapa.get("RSM").toString());

                indicador.add(nindicador);
                valoresRST.add(Cvalor1);
                valoresKNIT.add(Cvalor2);
                valoresDPF.add(Cvalor3);
                valoresRLRS.add(Cvalor4);
                valoresFPS.add(Cvalor5);
                valoresRSM.add(Cvalor6);

            }

            Label Titulo = new Label(0, 0, "INDICADORES PRODUCCION", Metodos_Generales_Excel.Titulo());
            s.addCell(Titulo);
            s.mergeCells(0, 0, 7, 0);
            //altura de la fila, en este caso la fila 0
            int heightInPoints = 26 * 20;
            s.setRowView(0, heightInPoints);

            //Encabezados columnas
            String Header[] = new String[7];
            Header[0] = "Nombre Indicador";
            Header[1] = "RST";
            Header[2] = "KNIT";
            Header[3] = "DPF";
            Header[4] = "RLRS";
            Header[5] = "FPS";
            Header[6] = "RSM O&M";

            for (int i = 0; i < Header.length; i++) {
                Label label = new Label(i + 1, 1, Header[i]);
                s.addCell(label);
                WritableCell cell = s.getWritableCell(i + 1, 1);
                cell.setCellFormat(Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.OCEAN_BLUE, WritableFont.ARIAL, 12));

            }

            //int ultimafila = indicador.size() + 2;
            for (int ix = 0; ix < indicador.size(); ix++) {

                //Columna Indicador
                Label ColIndicador = new Label(1, ix + 2, indicador.get(ix), Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(ColIndicador);
                s.setColumnView(1, 30);
                //Columna valores RST
                jxl.write.Number rst = new jxl.write.Number(2, ix + 2, valoresRST.get(ix), Metodos_Generales_Excel.FormatoNumerico(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(rst);
                s.setColumnView(2, 20);
                
                //Columna valores RST
                jxl.write.Number knit = new jxl.write.Number(3, ix + 2, valoresKNIT.get(ix), Metodos_Generales_Excel.FormatoNumerico(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(knit);
                s.setColumnView(3, 20);
                
                jxl.write.Number dpf = new jxl.write.Number(4, ix + 2, valoresDPF.get(ix), Metodos_Generales_Excel.FormatoNumerico(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(dpf);
                s.setColumnView(4, 20);
                
                 jxl.write.Number rlrs = new jxl.write.Number(5, ix + 2, valoresRLRS.get(ix), Metodos_Generales_Excel.FormatoNumerico(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(rlrs);
                s.setColumnView(5, 20);
                
                 jxl.write.Number fps = new jxl.write.Number(6, ix + 2, valoresFPS.get(ix), Metodos_Generales_Excel.FormatoNumerico(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(fps);
                s.setColumnView(6, 20);
                
                 jxl.write.Number rsm = new jxl.write.Number(7, ix + 2, valoresRSM.get(ix), Metodos_Generales_Excel.FormatoNumerico(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(rsm);
                s.setColumnView(7, 20);
                
            }

            w.write();
            w.close();
            this.indicador.clear();
            this.valoresRST.clear();
            this.valoresDPF.clear();
            this.valoresRLRS.clear();
            this.valoresFPS.clear();
            this.valoresKNIT.clear();
            this.valoresRSM.clear();
            
            
            
        } catch (IOException | NumberFormatException | WriteException e) {
            throw new ServletException("Exception in Excel Sample Servlet", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
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
        processRequest(request, response);
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
