/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivosXLS;

import Modelo.Modelo_IndicadoresProduccion;
import Modelo.Modelo_IndicadoresRRHH;
import Utilidades.Metodos_Generales_Excel;
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
public class I_002_Indicadores_RRHH_XLS extends HttpServlet {

 Modelo.ConexionBD conexion = new Modelo.ConexionBD();
 
    ArrayList<String> periodo = new ArrayList<>();
    ArrayList<Double> anio1 = new ArrayList<>();
    ArrayList<Double> devengado1 = new ArrayList<>();
    ArrayList<Double> anio2 = new ArrayList<>();
    ArrayList<Double> devengado2 = new ArrayList<>();
    ArrayList<Double> Noempleados = new ArrayList<>();
    ArrayList<Double> DifDevengado = new ArrayList<>();
    ArrayList<Double> porcentaje = new ArrayList<>();
    
    String anio = "";
    String tipo = "";
    String indicador="";
    
    

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
        tipo = request.getParameter("tipo");
        indicador= request.getParameter("indicador");
        
        OutputStream out = null;

        try {
            if (tipo.equals("1")){
                tipo="N";
            }else{
                tipo="P";
            }
            String sql = Modelo_IndicadoresRRHH.Consulta_Excel_Indicadores_RRHH(Integer.parseInt(anio),tipo);
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition",
                    "attachment; filename=Indicadores RRHH" + ".xls");

            WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
            WritableSheet s = w.createSheet("Indicadores RRHH", 0);

            List<Map<String, Object>> resultList = new ArrayList<>();
            resultList = conexion.select(sql);

            Iterator<Map<String, Object>> iterador = resultList.iterator();
            while (iterador.hasNext()) {
                Map<String, Object> mapa = iterador.next();
                
           String periodov = String.valueOf(mapa.get("periodo"));
               
           if (tipo.equals("N")){
               periodov = Utilidades.MetodosGlobales.get_mes((Integer)mapa.get("periodo"));
                
            }else{
               periodov = String.valueOf(mapa.get("periodo"));
            }
                
                Double Cvalor1 = Double.parseDouble(mapa.get("anio1").toString());
                Double Cvalor2 = Double.parseDouble(mapa.get("Devengado1").toString());
                Double Cvalor3 = Double.parseDouble(mapa.get("anio2").toString());
                Double Cvalor4 = Double.parseDouble(mapa.get("Devengado2").toString());
                Double Cvalor5 = Double.parseDouble(mapa.get("NoEmpleados").toString());
                Double Cvalor6 = Double.parseDouble(mapa.get("Devengado").toString());
                Double Cvalor7 = Double.parseDouble(mapa.get("Porcentaje").toString());

                
                
                
                periodo.add(periodov);
                anio1.add(Cvalor1);
                devengado1.add(Cvalor2);
                anio2.add(Cvalor3);
                devengado2.add(Cvalor4);
                
                Noempleados.add(existe(Cvalor5));
                DifDevengado.add(existe(Cvalor6));
                porcentaje.add(existe(Cvalor7));

            }

            Label Titulo = new Label(0, 0, GetTituloHoja(tipo), Metodos_Generales_Excel.Titulo());
            s.addCell(Titulo);
            s.mergeCells(0, 0, 8, 0);
            //altura de la fila, en este caso la fila 0
            int heightInPoints = 26 * 20;
            s.setRowView(0, heightInPoints);

            //Encabezados columnas
            String Header[] = new String[8];
            Header[0] = this.GetPeriodo(tipo);
            Header[1] = "No. Empleados "+String.valueOf(Integer.parseInt(anio)-1);
            Header[2] = "Devengado "+String.valueOf(Integer.parseInt(anio)-1);
            Header[3] = "No. Empleados " + anio;
            Header[4] = "Devengado "+anio;
            Header[5] = "Diferencia No.Empleados";
            Header[6] = "Diferencia Devengado";
            Header[7] = "Porcentaje Incremento";
            

            for (int i = 0; i < Header.length; i++) {
                Label label = new Label(i + 1, 1, Header[i]);
                s.addCell(label);
                WritableCell cell = s.getWritableCell(i + 1, 1);
                cell.setCellFormat(Metodos_Generales_Excel.Formato_headers_Excel(Colour.OCEAN_BLUE, WritableFont.ARIAL, 12));

            }

            //int ultimafila = periodo.size() + 2;
            for (int ix = 0; ix < periodo.size(); ix++) {

                
                //Columna periodo
                Label COL1 = new Label(1, ix + 2, periodo.get(ix), Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(COL1);
                s.setColumnView(1, 15);
                
                //Empleados ANIO1
                jxl.write.Number COL2 = new jxl.write.Number(2, ix + 2, anio1.get(ix), Metodos_Generales_Excel.FormatoNumericoEntero(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(COL2);
                s.setColumnView(2,25);
                
                //Devengado ANIO1
                jxl.write.Number COL3 = new jxl.write.Number(3, ix + 2, devengado1.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(COL3);
                s.setColumnView(3, 22);
                
                //Empleados anio2
                jxl.write.Number COL4 = new jxl.write.Number(4, ix + 2, anio2.get(ix), Metodos_Generales_Excel.FormatoNumericoEntero(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(COL4);
                s.setColumnView(4, 25);
                
                 jxl.write.Number COL5 = new jxl.write.Number(5, ix + 2, devengado2.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(COL5);
                s.setColumnView(5, 22);
                
                 jxl.write.Number COL6 = new jxl.write.Number(6, ix + 2, Noempleados.get(ix), Metodos_Generales_Excel.FormatoNumericoEntero(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(COL6);
                s.setColumnView(6, 27);
                
                 jxl.write.Number COL7 = new jxl.write.Number(7, ix + 2, DifDevengado.get(ix), Metodos_Generales_Excel.FormatoNumericoDecimal(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(COL7);
                s.setColumnView(7, 25);
                
                jxl.write.Number COL8 = new jxl.write.Number(8, ix + 2, (porcentaje.get(ix)), Metodos_Generales_Excel.FormatoNumericoPorcentaje(Colour.WHITE, WritableFont.ARIAL, 10));               
                s.addCell(COL8);
                s.setColumnView(8, 25);
                
            }

            w.write();
            w.close();
            this.periodo.clear();
            this.anio1.clear();
            this.anio2.clear();
            this.devengado2.clear();
            this.Noempleados.clear();
            this.devengado1.clear();
            this.DifDevengado.clear();
            this.porcentaje.clear();
            
            
            
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

    public Double existe(Double valor)
    {
     if (valor>0)
         return valor;
     else
         return 0.0;
    }
    
    public String GetPeriodo(String tipo) {
        if (tipo == "N") {
            return "Mes";
        } else {
            return "Catorcena";
        }
    }
    
    public String GetTituloHoja(String tipo) {
        if (tipo.equals("N")) {
            /*if (indicador.equals("INDICADOR7")) {
                return "Cantidad Empleados Nomina";
            } else {
                return "Promedio Devengado Nomina";
            }*/
            return "Comparativo Nominas";
        } else {
            return "Comparativo Planillas";
        }
    }

}
