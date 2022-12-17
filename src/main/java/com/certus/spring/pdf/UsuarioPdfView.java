package com.certus.spring.pdf;

import java.util.Map;
import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.certus.spring.models.Usuario;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("registro")
public class UsuarioPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

       
        Usuario usuario = (Usuario) model.get("usuario");
        PdfPTable tablaTitulo =  new PdfPTable(1);
        PdfPCell celda = null;

        /* Marggenes */
        document.setMargins(-20, -20, 40, 20);
        document.open();

        Font fuenteTitulo = FontFactory.getFont("Helvetica", 16, Color.WHITE);
        celda = new PdfPCell(new Phrase("Listado de Usuario", fuenteTitulo));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(254, 152, 0));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(30);

        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(30);

        // construir el pdf
        PdfPTable table = new PdfPTable(1);
        table.addCell("Datos del usuario");
        table.addCell(usuario.getNombre());
        table.addCell(usuario.getDireccion());
        table.addCell(usuario.getApellidos());
        table.addCell(usuario.getEmail());
        table.addCell(usuario.getTelefono());
        
        document.add(tablaTitulo);
        document.add(table);
    }

}
