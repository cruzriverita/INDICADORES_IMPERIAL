/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivosXLS;

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
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

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
String planta="";
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

            String sql = ConsultasBD.I_000_Produccion_Por_Planta_Mes_02(planta,anio, mes);
            response.setContentType("application/vnd.ms-excel");

            response.setHeader("Content-Disposition",
                    "attachment; filename=ProduccionPorPlanta.xls");

            WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
            WritableSheet s = w.createSheet("DATOS", 0);

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

            Label l1 = new Label(0, 0, "PRODUCCION POR PLANTA DETALLE");
            s.addCell(l1);
            for (int ix = 0; ix < commodity.size(); ix++) {

                s.addCell(new Label(1, ix + 1, commodity.get(ix)));

                NumberFormat fivedps = new NumberFormat("#.####");
                WritableCellFormat fivedpsFormat = new WritableCellFormat(fivedps);
                jxl.write.Number number4 = new jxl.write.Number(2, ix + 1, valores.get(ix), fivedpsFormat);
                s.addCell(number4);

            }

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
