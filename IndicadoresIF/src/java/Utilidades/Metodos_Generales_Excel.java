/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    
    //Color, fuente y tamaño de fuente 
    public static WritableCellFormat Formato_Cuerpo_Excel(Colour colour_background, WritableFont.FontName font, int size) throws WriteException {
        Border border = Border.ALL;
        BorderLineStyle borderLineStyle = BorderLineStyle.THIN;

        WritableFont cellFont = new WritableFont(font, size);
        WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
        cellFormat.setBorder(border, borderLineStyle);
        cellFormat.setBackground(colour_background);
        return cellFormat;
    }

    public static WritableCellFormat FormatoNumerico(Colour colour_background, WritableFont.FontName font, int size) throws WriteException {
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
    
     public static WritableCellFormat Titulo () throws WriteException {
        WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 16);
        WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
        cellFormat.setAlignment(Alignment.CENTRE);
        return cellFormat;
    }

}
  



