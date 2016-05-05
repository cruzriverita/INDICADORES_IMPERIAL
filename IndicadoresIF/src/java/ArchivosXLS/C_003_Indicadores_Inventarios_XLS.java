/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivosXLS;


import Modelo.Modelo_Indicadores_Inventarios;
import Utilidades.MetodosGlobales;
import Utilidades.Metodos_Generales_Excel;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 *
 * @author rcruz
 */
public class C_003_Indicadores_Inventarios_XLS extends HttpServlet {

    Modelo.ConexionBD conexion = new Modelo.ConexionBD();

    String anio = "";

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
        OutputStream out = null;

        try {

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment; filename=Rotacion De Inventarios" + ".xls");

            //archivo excel
            WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());

            //Hojas del archivo
            WritableSheet s = w.createSheet("Resumen", 0);
            WritableSheet s2 = w.createSheet("Algodon", 1);
            WritableSheet s3 = w.createSheet("Poliester y Fibra", 2);
            WritableSheet s4 = w.createSheet("Hilo Producido", 3);
            WritableSheet s5 = w.createSheet("Hilo Comprado", 4);
            WritableSheet s6 = w.createSheet("Tela Cruda", 5);
            WritableSheet s7 = w.createSheet("Tela Terminada", 6);

            //Se crean las tablas por cada tipo de inventario para escribir en el archivo. 
            String sql = Modelo_Indicadores_Inventarios.Consulta_Excel_Resumen(Integer.parseInt(anio));
            this.EscribirResumen(s, w, sql, "Inventario Grupo Imperial " + anio);

            String sql2 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle1(Integer.parseInt(anio), "A");
            this.EscribirDetalle(s2, w, sql2, "Algodon");

            String sql3 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle1(Integer.parseInt(anio), "PF");
            this.EscribirDetalle(s3, w, sql3, "Poliester y Fibra");

            String sql4 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle1(Integer.parseInt(anio), "HP");
            this.EscribirDetalle(s4, w, sql4, "Hilo Producido");

            String sql5 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle1(Integer.parseInt(anio), "HC");
            this.EscribirDetalle(s5, w, sql5, "Hilo Comprado");

            String sql6 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle1(Integer.parseInt(anio), "TC");
            this.EscribirDetalle(s6, w, sql6, "Tela Cruda");

            /**
             * ************************************HILO PRODUCIDO**********************************************
             */
            String sql4_1 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle2(Integer.parseInt(anio), "HP", "RLRS");
            this.EscribirDetalle2(s4, w, sql4_1, 1, "C", "D");

            String sql4_2 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle2(Integer.parseInt(anio), "HP", "RS");
            this.EscribirDetalle2(s4, w, sql4_2, 5, "G", "H");

            String sql4_3 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle2(Integer.parseInt(anio), "HP", "RSM");
            this.EscribirDetalle2(s4, w, sql4_3, 9, "K", "L");

            /**
             * ***********************************TELA CRUDA************************************************
             */
            String sql6_2 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle2(Integer.parseInt(anio), "TC", "KNIT");
            this.EscribirDetalle2(s6, w, sql6_2, 5, "G", "H");

            String sql6_3 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle2(Integer.parseInt(anio), "TC", "FPS");
            this.EscribirDetalle2(s6, w, sql6_3, 9, "K", "L");

            String sql6_1 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle2(Integer.parseInt(anio), "TC", "DPF");
            this.EscribirDetalle2(s6, w, sql6_1, 1, "C", "D");

            /**
             * ***********************************TELA TERMINADA************************************************
             */
            String sql7 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle1(Integer.parseInt(anio), "TT");
            this.EscribirDetalle(s7, w, sql7, "Tela Terminada");

            String sql7_1 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle2(Integer.parseInt(anio), "TT", "DPF");
            this.EscribirDetalle2(s7, w, sql7_1, 1, "C", "D");

            String sql7_2 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle2(Integer.parseInt(anio), "TT", "FPS ANEXO");
            this.EscribirDetalle2(s7, w, sql7_2, 5, "G", "H");

            String sql7_3 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle2(Integer.parseInt(anio), "TT", "Maquilas");
            this.EscribirDetalle2(s7, w, sql7_3, 9, "K", "L");

            String sql7_4 = Modelo_Indicadores_Inventarios.Consulta_Excel_Detalle2(Integer.parseInt(anio), "TT", "Telamarket");
            this.EscribirDetalle2(s7, w, sql7_4, 13, "O", "P");

            //Escribir el archivo
            w.write();
            w.close();

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

    //Crear hoja resumen
    public void EscribirResumen(WritableSheet s, WritableWorkbook w, String sql, String name) throws WriteException, MalformedURLException, IOException {
        ArrayList<String> indicador = new ArrayList<>();
        ArrayList<Double> ValoresIndice = new ArrayList<>();
        ArrayList<Double> ValoresDias = new ArrayList<>();

        List<Map<String, Object>> resultList;
        resultList = conexion.select(sql);

        Iterator<Map<String, Object>> iterador = resultList.iterator();
        while (iterador.hasNext()) {
            Map<String, Object> mapa = iterador.next();

            String nindicador = (String) mapa.get("tipo");
            Double Cvalor1 = Double.parseDouble(mapa.get("indice").toString());
            Double Cvalor2 = Double.parseDouble(mapa.get("dias").toString());

            indicador.add(nindicador);
            ValoresIndice.add(Cvalor1);
            ValoresDias.add(Cvalor2);

        }

        Label Titulo = new Label(0, 0, name, Metodos_Generales_Excel.Titulo());
        s.addCell(Titulo);
        s.mergeCells(0, 0, 3, 0);
        //altura de la fila, en este caso la fila 0
        int heightInPoints = 26 * 20;
        s.setRowView(0, heightInPoints);

        //Encabezados columnas
        String Header[] = new String[3];
        Header[0] = "Tipo Inventario";
        Header[1] = "Indice Promedio";
        Header[2] = "Dias Promedio";

        for (int i = 0; i < Header.length; i++) {
            Label label = new Label(i + 1, 1, Header[i]);
            s.addCell(label);
            WritableCell cell = s.getWritableCell(i + 1, 1);
            cell.setCellFormat(Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.OCEAN_BLUE, WritableFont.ARIAL, 12));

        }

        for (int ix = 0; ix < indicador.size(); ix++) {

            Label ColIndicador = new Label(1, ix + 2, GetArticulo(indicador.get(ix)), Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.WHITE, WritableFont.ARIAL, 10));

            s.addCell(ColIndicador);

            s.setColumnView(1, 30);

            jxl.write.Number rst = new jxl.write.Number(2, ix + 2, ValoresIndice.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal2(Colour.WHITE, WritableFont.ARIAL, 10));
            s.addCell(rst);
            s.setColumnView(2, 20);

            jxl.write.Number knit = new jxl.write.Number(3, ix + 2, ValoresDias.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal2(Colour.WHITE, WritableFont.ARIAL, 10));
            s.addCell(knit);
            s.setColumnView(3, 20);

        }
        indicador.clear();
        ValoresIndice.clear();
        ValoresDias.clear();

    }

    //Escribir detalle por tipo de inventario.
    public void EscribirDetalle(WritableSheet s2, WritableWorkbook w, String sql, String name) throws WriteException, IOException {

        ArrayList<String> Mes_A = new ArrayList<>();
        ArrayList<Double> Indice_A = new ArrayList<>();
        ArrayList<Double> Dias_A = new ArrayList<>();
        int heightInPoints = 26 * 20;

        //s2 = w.createSheet(name, 0);
        List<Map<String, Object>> resultList2;// = new ArrayList<>();
        resultList2 = conexion.select(sql);

        Iterator<Map<String, Object>> iterador2 = resultList2.iterator();
        while (iterador2.hasNext()) {
            Map<String, Object> mapa2 = iterador2.next();

            String nindicador = String.valueOf(mapa2.get("mes"));

            Double Cvalor1 = Double.parseDouble(mapa2.get("indice").toString());
            Double Cvalor2 = Double.parseDouble(mapa2.get("dias").toString());

            Mes_A.add(nindicador);
            Indice_A.add(Cvalor1);
            Dias_A.add(Cvalor2);

        }

        Label Titulo2 = new Label(0, 0, name, Metodos_Generales_Excel.Titulo());
        s2.addCell(Titulo2);
        s2.mergeCells(0, 0, 3, 0);
        s2.setRowView(0, heightInPoints);

        //Encabezados columnas
        String Header2[] = new String[3];
        Header2[0] = "Mes";
        Header2[1] = "Indice";
        Header2[2] = "Dias Inventario";

        for (int i = 0; i < Header2.length; i++) {
            Label label = new Label(i + 1, 1, Header2[i]);
            s2.addCell(label);
            WritableCell cell = s2.getWritableCell(i + 1, 1);
            cell.setCellFormat(Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.OCEAN_BLUE, WritableFont.ARIAL, 12));

        }

        //int ultimafila = indicador.size() + 2;
        for (int ix = 0; ix < Mes_A.size(); ix++) {

            Label ColIndicador = new Label(1, ix + 2, MetodosGlobales.get_nombre_mes(Integer.parseInt(Mes_A.get(ix))), Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.WHITE, WritableFont.ARIAL, 10));
            s2.addCell(ColIndicador);
            s2.setColumnView(1, 30);

            jxl.write.Number rst = new jxl.write.Number(2, ix + 2, Indice_A.get(ix), Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.WHITE, WritableFont.ARIAL, 10));
            s2.addCell(rst);
            s2.setColumnView(2, 20);

            jxl.write.Number knit = new jxl.write.Number(3, ix + 2, Dias_A.get(ix), Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.WHITE, WritableFont.ARIAL, 10));
            s2.addCell(knit);
            s2.setColumnView(3, 20);

        }

        addAvgCells(s2, "AVERAGE(C1:C14)", 14, 2, 1);
        addAvgCells(s2, "AVERAGE(D1:D14)", 14, 3, 1);

    }

    public void EscribirDetalle2(WritableSheet s2, WritableWorkbook w, String sql, Integer col_inicial, String l1, String l2) throws WriteException {

        try {
            String planta ;
            ArrayList<String> Mes_A = new ArrayList<>();
            ArrayList<Double> Indice_A = new ArrayList<>();
            ArrayList<Double> Dias_A = new ArrayList<>();

            ArrayList<String> Vplanta = new ArrayList<>();
            int xx = 0;

            //s2 = w.createSheet(name, 0);
            List<Map<String, Object>> resultList2;
            resultList2 = conexion.select(sql);

            Iterator<Map<String, Object>> iterador2 = resultList2.iterator();
            while (iterador2.hasNext()) {
                Map<String, Object> mapa2 = iterador2.next();

                String nindicador = String.valueOf(mapa2.get("mes"));
                Double Cvalor1 = Double.parseDouble(mapa2.get("indice").toString());
                Double Cvalor2 = Double.parseDouble(mapa2.get("dias").toString());
                planta = String.valueOf(mapa2.get("planta"));

                Mes_A.add(nindicador);
                Indice_A.add(Cvalor1);
                Dias_A.add(Cvalor2);
                Vplanta.add(planta);

            }

            Label Titulo2 = new Label((col_inicial), 17, Vplanta.get(0), Metodos_Generales_Excel.Titulo());
            s2.addCell(Titulo2);

            //Encabezados columnas
            String Header2[] = new String[3];
            Header2[0] = "Mes";
            Header2[1] = "Indice";
            Header2[2] = "Dias Inventario";

            for (int i = 0; i < Header2.length; i++) {
                Label label = new Label(col_inicial + i, 18, Header2[i]);
                s2.addCell(label);
                //columna,fila                               
                WritableCell cell = s2.getWritableCell(col_inicial + i, 18);
                cell.setCellFormat(Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.OCEAN_BLUE, WritableFont.ARIAL, 12));

            }

            for (int ix = 0; ix < Mes_A.size(); ix++) {

                //col,fila
                Label ColIndicador = new Label(col_inicial, xx + 19, MetodosGlobales.get_nombre_mes(Integer.parseInt(Mes_A.get(ix))), Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.WHITE, WritableFont.ARIAL, 10));
                s2.addCell(ColIndicador);
                s2.setColumnView(col_inicial, 30);

                jxl.write.Number rst = new jxl.write.Number(col_inicial + 1, xx + 19, Indice_A.get(ix), Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.WHITE, WritableFont.ARIAL, 10));
                s2.addCell(rst);
                s2.setColumnView(col_inicial + 1, 20);

                jxl.write.Number knit = new jxl.write.Number(col_inicial + 2, xx + 19, Dias_A.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal2(Colour.WHITE, WritableFont.ARIAL, 10));
                s2.addCell(knit);
                s2.setColumnView(col_inicial + 2, 20);
                xx++;

            }
       // }

            addAvgCells(s2, "AVERAGE(" + l1 + "20:" + l1 + "31)", 31, col_inicial + 1, col_inicial);
            addAvgCells(s2, "AVERAGE(" + l2 + "20:" + l2 + "31)", 31, col_inicial + 2, col_inicial);

            Mes_A.clear();
            Indice_A.clear();
            Dias_A.clear();
            Vplanta.clear();
        } catch (NumberFormatException | WriteException a) {
            System.out.println(a.toString());
        }
    }

    private static void addAvgCells(
            WritableSheet sheet,
            String formula, int row, int col, int col_label) throws RowsExceededException, WriteException {

        //Create a formula for average
        Formula formulaCell = new Formula(col, row, formula, Metodos_Generales_Excel.FormatoNumericoDecimal2(Colour.WHITE, WritableFont.ARIAL, 10));
        sheet.addCell(formulaCell);

        //Create label for average
        Label formulaLabel = new Label(col_label, row, "Promedio", Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.WHITE, WritableFont.ARIAL, 10));
        sheet.addCell(formulaLabel);
    }

    public String GetArticulo(String valor_Select) {
        switch (valor_Select) {
            case "A":
                return "Algodon";
            case "PF":
                return "Poliester y Fibra";
            case "HP":
                return "Hilo Producido";
            case "HC":
                return "Hilo Comprado";
            case "TC":
                return "Tela Cruda";
            case "TT":
                return "Tela Terminada";
            default:
                return "";
        }

    }

}
