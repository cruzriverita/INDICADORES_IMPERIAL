/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivosXLS;

import Utilidades.Metodos_Generales_Excel;
import Modelo.ConsultasBD;
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
public class I_000_Produccion_Por_Planta_Commodity_XLS extends HttpServlet {

    Modelo.ConexionBD conexion = new Modelo.ConexionBD();
    ArrayList<String> commodity = new ArrayList<>();
    ArrayList<Double> valores = new ArrayList<>();
    String anio = "";
    String mes = "";
    String planta = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        anio = request.getParameter("anio");
        mes = request.getParameter("mes");
        planta = request.getParameter("planta");
        OutputStream out = null;
        try {

            String sql = ConsultasBD.I_000_Produccion_Por_Planta_Mes_02(planta, anio, mes);
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition",
                    "attachment; filename=Produccion_Planta_" + planta + ".xls");

            WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
            WritableSheet s = w.createSheet("Produccion "+planta.toUpperCase(), 0);

            List<Map<String, Object>> resultList = new ArrayList<>();
            resultList = conexion.select(sql);

            Iterator<Map<String, Object>> iterador = resultList.iterator();
            while (iterador.hasNext()) {
                Map<String, Object> mapa = iterador.next();

                String Nombre_Planta = (String) mapa.get("COMMODITY_CODE");
                Double Produccion = Double.parseDouble(mapa.get("VALOR").toString());

                commodity.add(Nombre_Planta);
                valores.add(Produccion);
            }

            Label Titulo = new Label(0, 0, "PRODUCCION PLANTA " + planta.toUpperCase(), Metodos_Generales_Excel.Titulo());
            s.addCell(Titulo);
            s.mergeCells(0, 0, 5, 0);
            //altura de la fila, en este caso la fila 0
            int heightInPoints = 26 * 20;
            s.setRowView(0, heightInPoints);

            //Encabezados columnas
            String Header[] = new String[2];
            Header[0] = "PLANTA";
            Header[1] = "PRODUCCION";

            for (int i = 0; i < Header.length; i++) {
                Label label = new Label(i + 1, 1, Header[i]);
                s.addCell(label);
                WritableCell cell = s.getWritableCell(i + 1, 1);
                cell.setCellFormat(Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.OCEAN_BLUE, WritableFont.ARIAL, 12));

            }

            //Se le suma la fila del titulo y la fila del encabezado del excel.
            int ultimafila = commodity.size() + 2;
            for (int ix = 0; ix < commodity.size(); ix++) {

                //Columna Commodity
                Label ColCommodity = new Label(1, ix + 2, commodity.get(ix), Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(ColCommodity);
                s.setColumnView(1, 20);
                //Columna valores
                jxl.write.Number number4 = new jxl.write.Number(2, ix + 2, valores.get(ix), Metodos_Generales_Excel.FormatoNumerico(Colour.WHITE, WritableFont.ARIAL, 10));
                s.addCell(number4);
                s.setColumnView(2, 20);
            }

            //TOTAL
            Formula sum = new Formula(2, ultimafila , "SUMA(c3:c" + ultimafila + ")", Metodos_Generales_Excel.FormatoNumerico(Colour.OCEAN_BLUE, WritableFont.ARIAL, 10));
            s.addCell(sum);
            Label sumLabel = new Label(1, ultimafila , "TOTAL", Metodos_Generales_Excel.Formato_Cuerpo_Excel(Colour.OCEAN_BLUE, WritableFont.ARIAL, 10));
            s.addCell(sumLabel);
            w.write();
            w.close();
            this.commodity.clear();
            this.valores.clear();

        } catch (IOException | NumberFormatException | WriteException e) {
            throw new ServletException("Exception in Excel Sample Servlet", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
