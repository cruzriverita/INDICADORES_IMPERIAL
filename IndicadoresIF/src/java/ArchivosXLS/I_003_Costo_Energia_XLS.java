/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivosXLS;

import Modelo.ConsultasBD_IndicadoresProduccion;
import Utilidades.Metodos_Generales_Excel;
import java.io.IOException;
import java.io.OutputStream;
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

/**
 *
 * @author rcruz
 */
public class I_003_Costo_Energia_XLS extends HttpServlet {

    Modelo.ConexionBD conexion = new Modelo.ConexionBD();
    ArrayList<String> plantas = new ArrayList<>();
    ArrayList<Double> ValoresEne = new ArrayList<>();
    ArrayList<Double> ValoresFeb = new ArrayList<>();
    String anio = "";
    String opc = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        anio = request.getParameter("anio");
        opc = request.getParameter("opciones");
        String sql = "";
        
        OutputStream out = null;
        try {

            if (!opc.equals("money")) {
                sql = ConsultasBD_IndicadoresProduccion.I_003_Costo_Energia_KWH(anio);
            } else {
                sql = ConsultasBD_IndicadoresProduccion.I_003_Costo_Energia_Q(anio);
            }

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment; filename=ProduccionPorPlanta.xls");

            WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
            WritableSheet s = w.createSheet("Costo Energia", 0);

            List<Map<String, Object>> resultList = new ArrayList<>();
            resultList = conexion.select(sql);

            Iterator<Map<String, Object>> iterador = resultList.iterator();
            while (iterador.hasNext()) {
                Map<String, Object> mapa = iterador.next();

                String Nombre_Planta = (String) mapa.get("Planta");
                Double ene = Double.parseDouble(mapa.get("1").toString());
                Double feb = Double.parseDouble(mapa.get("2").toString());

                plantas.add(Nombre_Planta);
                ValoresEne.add(ene);
                ValoresFeb.add(feb);
            }

            //String mes_actual = Utilidades.MetodosGlobales.get_mes(Integer.parseInt(mes)).toUpperCase() ;
            Label Titulo = new Label(0, 0, "COSTO ENERGIA " + " AÑO " + anio + " EN " + this.convertir(opc), Metodos_Generales_Excel.Titulo());
            s.addCell(Titulo);
            s.mergeCells(0, 0, 5, 0);
            //altura de la fila, en este caso la fila 0
            int heightInPoints = 26 * 20;
            s.setRowView(0, heightInPoints);

            //Encabezados columnas
            String Header[] = new String[3];
            Header[0] = "PLANTA";
            Header[1] = "ENERO";
            Header[2] = "FEBRERO";

            for (int i = 0; i < Header.length; i++) {
                Label label = new Label(i + 1, 1, Header[i]);
                s.addCell(label);
                WritableCell cell = s.getWritableCell(i + 1, 1);
                cell.setCellFormat(Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.OCEAN_BLUE, WritableFont.ARIAL, 12));

            }
            int ultimafila = plantas.size() + 2;
            for (int ix = 0; ix < plantas.size(); ix++) {

                Label ColPlanta = new Label(1, ix + 2, plantas.get(ix), Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.WHITE, WritableFont.ARIAL, 12));
                s.addCell(ColPlanta);
                s.setColumnView(1, 20);

                //Columnas meses
                jxl.write.Number Jan = new jxl.write.Number(2, ix + 2, ValoresEne.get(ix), Metodos_Generales_Excel.FormatoNumerico(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(Jan);
                s.setColumnView(2, 20);

                jxl.write.Number Feb = new jxl.write.Number(3, ix + 2, ValoresFeb.get(ix), Metodos_Generales_Excel.FormatoNumerico(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(Feb);
                s.setColumnView(3, 20);

            }

            Label sumLabel = new Label(1, ultimafila, "TOTAL", Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.OCEAN_BLUE, WritableFont.ARIAL, 10));
            s.addCell(sumLabel);

            //TOTALES
            Formula sum = new Formula(2, ultimafila, "SUMA(c3:c" + ultimafila + ")", Metodos_Generales_Excel.FormatoNumerico(Colour.OCEAN_BLUE, WritableFont.ARIAL, 10));
            s.addCell(sum);

            Formula sum2 = new Formula(3, ultimafila, "SUMA(d3:d" + ultimafila + ")", Metodos_Generales_Excel.FormatoNumerico(Colour.OCEAN_BLUE, WritableFont.ARIAL, 10));
            s.addCell(sum2);

            w.write();
            w.close();
            this.plantas.clear();
            this.ValoresEne.clear();
            this.ValoresFeb.clear();

        } catch (IOException | NumberFormatException | WriteException e) {
            throw new ServletException("Exception in Excel Sample Servlet", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public String convertir(String val) {
        String regreso = "";
        if (val.equals("money")) {
            regreso = "QUETZALES";
        } else {
            return "KWS";
        }
        return regreso;
    }
}
