/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivosXLS;

import Modelo.Modelo_001_Indicadores_Produccion;
import Modelo.Modelo_005_Indicadores_Calidad;
import Utilidades.Metodos_Generales_Excel;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author rcruz
 */
@WebServlet(name = "C_005_Indicadores_Calidad_XLS", urlPatterns = {"/C_005_Indicadores_Calidad_XLS"})
public class C_005_Indicadores_Calidad_XLS extends HttpServlet {

    Modelo.ConexionBD conexion = new Modelo.ConexionBD();
    ArrayList<String> indicador = new ArrayList<>();
    ArrayList<String> valoresambito = new ArrayList<>();
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
        //planta = request.getParameter("planta");
        OutputStream out = null;

        try {
            String sql = Modelo_005_Indicadores_Calidad.Consulta_Tabla_Indicadores_Calidad(Integer.parseInt(anio), mes);
            response.setContentType("application/vnd.ms-excel");

             response.setHeader("Content-Disposition",
                    "attachment; filename=Indicadores Calidad - " + Utilidades.Metodos_Globales.get_nombre_mes(Integer.parseInt(mes))+" "+ anio +".xls");

            WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
            
            
            WritableSheet s = w.createSheet("Indicadores Calidad", 0);

            List<Map<String, Object>> resultList;
            resultList = conexion.select(sql);

            Iterator<Map<String, Object>> iterador = resultList.iterator();
            while (iterador.hasNext()) {
                Map<String, Object> mapa = iterador.next();

                /* String nindicador = (String) mapa.get("Indicador");
                 Double Cvalor1 = Double.parseDouble(mapa.get("RST").toString());
                 Double Cvalor2 = Double.parseDouble(mapa.get("KNIT").toString());
                 Double Cvalor3 = Double.parseDouble(mapa.get("DPF").toString());
                 Double Cvalor4 = Double.parseDouble(mapa.get("RLRS").toString());
                 Double Cvalor5 = Double.parseDouble(mapa.get("FPS").toString());
                 Double Cvalor6 = Double.parseDouble(mapa.get("RSM").toString());*/
                
                String ambito = String.valueOf(mapa.get("ambito"));
                String nindicador = String.valueOf(mapa.get("indicador"));

                Double Cvalor1 = Double.parseDouble(mapa.get("RST").toString());
                Double Cvalor2 = Double.parseDouble(mapa.get("RSM").toString());
                Double Cvalor3 = Double.parseDouble(mapa.get("RLRS").toString());
                Double Cvalor4 = Double.parseDouble(mapa.get("DPF").toString());
                Double Cvalor5 = Double.parseDouble(mapa.get("KNIT").toString());
                Double Cvalor6 = Double.parseDouble(mapa.get("FPS").toString());

                indicador.add(this.GetIndicador(nindicador));
                valoresambito.add(this.GetAmbito(ambito));
                valoresRST.add(Cvalor1);
                valoresKNIT.add(Cvalor5);
                valoresDPF.add(Cvalor4);
                valoresRLRS.add(Cvalor3);
                valoresFPS.add(Cvalor6);
                valoresRSM.add(Cvalor2);

            }

            Label Titulo = new Label(0, 0, "INDICADORES CALIDAD - " + Utilidades.Metodos_Globales.get_nombre_mes(Integer.parseInt(mes)).toUpperCase()+" "+ anio, Metodos_Generales_Excel.Titulo());
            s.addCell(Titulo);

            s.mergeCells(0, 0, 8, 0);
            s.mergeCells(1, 2, 1, 5);
            s.mergeCells(1, 6, 1, 9);

            //altura de la fila, en este caso la fila 0
            int heightInPoints = 26 * 20;
            s.setRowView(0, heightInPoints);

            //Encabezados columnas
            String Header[] = new String[8];
            Header[0] = "Produccion";
            Header[1] = "Indicador";
            Header[2] = "RST";
            Header[6] = "KNIT";
            Header[5] = "DPF";
            Header[4] = "RLRS";
            Header[7] = "FPS";
            Header[3] = "RSM";

            for (int i = 0; i < Header.length; i++) {
                Label label = new Label(i + 1, 1, Header[i]);
                s.addCell(label);
                WritableCell cell = s.getWritableCell(i + 1, 1);
                cell.setCellFormat(Metodos_Generales_Excel.Formato_headers_Excel(Colour.OCEAN_BLUE, WritableFont.ARIAL, 12));

            }

           
            
            //si es una fila de Total, se le pone un fondo amarillo 
            for (int ix = 0; ix < indicador.size(); ix++) {

                if (ix == 2 || ix == 6) {                   //Columna ambito
                    Label ColAmbito = new Label(1, ix + 2, valoresambito.get(ix), Metodos_Generales_Excel.Formato_Cuerpo_merge(Colour.WHITE, WritableFont.ARIAL, 10));
                    s.addCell(ColAmbito);
                    s.setColumnView(1, 30);

                    //Columna Indicador
                    Label ColIndicador = new Label(2, ix + 2, indicador.get(ix), Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.YELLOW, WritableFont.ARIAL, 10));
                    s.addCell(ColIndicador);
                    s.setColumnView(2, 30);

                    //Columna valores RST
                    jxl.write.Number rst = new jxl.write.Number(3, ix + 2, valoresRST.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal(Colour.YELLOW, WritableFont.ARIAL, 10));
                    s.addCell(rst);
                    s.setColumnView(3, 20);

                    jxl.write.Number rsm = new jxl.write.Number(4, ix + 2, valoresRSM.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal(Colour.YELLOW, WritableFont.ARIAL, 10));
                    s.addCell(rsm);
                    s.setColumnView(4, 20);

                    jxl.write.Number rlrs = new jxl.write.Number(5, ix + 2, valoresRLRS.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal(Colour.YELLOW, WritableFont.ARIAL, 10));
                    s.addCell(rlrs);
                    s.setColumnView(5, 20);

                    jxl.write.Number dpf = new jxl.write.Number(6, ix + 2, valoresDPF.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal(Colour.YELLOW, WritableFont.ARIAL, 10));
                    s.addCell(dpf);
                    s.setColumnView(6, 20);

                    jxl.write.Number knit = new jxl.write.Number(7, ix + 2, valoresKNIT.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal(Colour.YELLOW, WritableFont.ARIAL, 10));
                    s.addCell(knit);
                    s.setColumnView(7, 20);

                    jxl.write.Number fps = new jxl.write.Number(8, ix + 2, valoresFPS.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal(Colour.YELLOW, WritableFont.ARIAL, 10));
                    s.addCell(fps);
                    s.setColumnView(8, 20);
               
                } 
                //si no es una fila que contega totales entonces su fondo es blanco.
                else {

                    //Columna ambito
                    Label ColAmbito = new Label(1, ix + 2, valoresambito.get(ix), Metodos_Generales_Excel.Formato_Cuerpo_merge(Colour.WHITE, WritableFont.ARIAL, 10));
                    s.addCell(ColAmbito);
                    s.setColumnView(1, 30);
                    //Columna Indicador
                    Label ColIndicador = new Label(2, ix + 2, indicador.get(ix), Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.WHITE, WritableFont.ARIAL, 10));
                    s.addCell(ColIndicador);
                    s.setColumnView(2, 30);
                    //Columna valores RST
                    jxl.write.Number rst = new jxl.write.Number(3, ix + 2, valoresRST.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal(Colour.WHITE, WritableFont.ARIAL, 10));
                    s.addCell(rst);
                    s.setColumnView(3, 20);
                    //Columna valores RSM
                    jxl.write.Number rsm = new jxl.write.Number(4, ix + 2, valoresRSM.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal(Colour.WHITE, WritableFont.ARIAL, 10));
                    s.addCell(rsm);
                    s.setColumnView(4, 20);
                    //Columna valores RLRS
                    jxl.write.Number rlrs = new jxl.write.Number(5, ix + 2, valoresRLRS.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal(Colour.WHITE, WritableFont.ARIAL, 10));
                    s.addCell(rlrs);
                    s.setColumnView(5, 20);
                    //Columna valores DPF
                    jxl.write.Number dpf = new jxl.write.Number(6, ix + 2, valoresDPF.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal(Colour.WHITE, WritableFont.ARIAL, 10));
                    s.addCell(dpf);
                    s.setColumnView(6, 20);
                    //Columna valores KNIT
                    jxl.write.Number knit = new jxl.write.Number(7, ix + 2, valoresKNIT.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal(Colour.WHITE, WritableFont.ARIAL, 10));
                    s.addCell(knit);
                    s.setColumnView(7, 20);
                    //Columna valores FPS
                    jxl.write.Number fps = new jxl.write.Number(8, ix + 2, valoresFPS.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal(Colour.WHITE, WritableFont.ARIAL, 10));
                    s.addCell(fps);
                    s.setColumnView(8, 20);

                }

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
