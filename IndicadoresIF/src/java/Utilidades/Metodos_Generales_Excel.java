/*
 *Contiene los metodos utilizados en los servlet que se utilizan para crear y descargar los archivos de excel
 *y los cuales se utilizan para dar formato a las celdas.
 */
package Utilidades;

import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;
import jxl.format.Border;
import jxl.format.BorderLineStyle;

/**
 *
 * @author rcruz
 */
public class Metodos_Generales_Excel {

    //Formato titulos de celda excel.
    public static WritableCellFormat Formato_headers_Excel(Colour colour_background, WritableFont.FontName font, int size) throws WriteException {
        Border border = Border.ALL;
        BorderLineStyle borderLineStyle = BorderLineStyle.THIN;

        WritableFont cellFont = new WritableFont(font, size);
        cellFont.setColour(Colour.WHITE);
        WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
        cellFormat.setBorder(border, borderLineStyle);
        cellFormat.setBackground(colour_background);
        return cellFormat;
    }

    //Formato a las celdas texto. Parametros: Color, fuente y tamaño de fuente 
    public static WritableCellFormat Formato_Cuerpo_Excel(Colour colour_background, WritableFont.FontName font, int size) throws WriteException {
        Border border = Border.ALL;
        BorderLineStyle borderLineStyle = BorderLineStyle.THIN;

        WritableFont cellFont = new WritableFont(font, size);

        WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
        cellFormat.setBorder(border, borderLineStyle);
        cellFormat.setBackground(colour_background);
        return cellFormat;
    }

    //Formato con cuatro decimales
    public static WritableCellFormat FormatoNumericoDecimal(Colour colour_background, WritableFont.FontName font, int size) throws WriteException {
        Border border = Border.ALL;
        BorderLineStyle borderLineStyle = BorderLineStyle.THIN;
        WritableFont fuente = new WritableFont(font, size);
        NumberFormat decimal = new NumberFormat("#,###.####");
        WritableCellFormat FormatoDecimal = new WritableCellFormat(decimal);
        FormatoDecimal.setBorder(border, borderLineStyle);
        FormatoDecimal.setFont(fuente);
        FormatoDecimal.setBackground(colour_background);
        return FormatoDecimal;
    }

    //Formato con dos decimales.
    public static WritableCellFormat FormatoNumericoDecimal2(Colour colour_background, WritableFont.FontName font, int size) throws WriteException {
        Border border = Border.ALL;
        BorderLineStyle borderLineStyle = BorderLineStyle.THIN;
        WritableFont fuente = new WritableFont(font, size);
        NumberFormat decimal = new NumberFormat("#,###.##");
        WritableCellFormat FormatoDecimal = new WritableCellFormat(decimal);
        FormatoDecimal.setBorder(border, borderLineStyle);
        FormatoDecimal.setFont(fuente);
        FormatoDecimal.setBackground(colour_background);
        return FormatoDecimal;
    }

    //Formato para numeros enteros.
    public static WritableCellFormat FormatoNumericoEntero(Colour colour_background, WritableFont.FontName font, int size) throws WriteException {
        Border border = Border.ALL;
        BorderLineStyle borderLineStyle = BorderLineStyle.THIN;
        WritableFont fuente = new WritableFont(font, size);
        NumberFormat entero = new NumberFormat("#,###");
        WritableCellFormat FormatoEntero = new WritableCellFormat(entero);
        FormatoEntero.setBorder(border, borderLineStyle);
        FormatoEntero.setFont(fuente);
        FormatoEntero.setBackground(colour_background);
        return FormatoEntero;
    }

    //Formato % 
    public static WritableCellFormat FormatoNumericoPorcentaje(Colour colour_background, WritableFont.FontName font, int size) throws WriteException {
        Border border = Border.ALL;
        BorderLineStyle borderLineStyle = BorderLineStyle.THIN;
        WritableFont fuente = new WritableFont(font, size);
        NumberFormat entero = new NumberFormat("#,###%");
        WritableCellFormat FormatoEntero = new WritableCellFormat(entero);
        FormatoEntero.setBorder(border, borderLineStyle);
        FormatoEntero.setFont(fuente);
        FormatoEntero.setBackground(colour_background);
        return FormatoEntero;
    }

    //Formato para titulos de hojas.
    public static WritableCellFormat Titulo() throws WriteException {
        WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 16);

        WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
        cellFormat.setAlignment(Alignment.CENTRE);

        return cellFormat;
    }

}
