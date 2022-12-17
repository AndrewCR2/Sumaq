package com.certus.spring.pdf;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.certus.spring.models.Usuario;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("registro")
public class UsuarioPdfView extends AbstractPdfView{
    

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Usuario usuario = (Usuario)model.get("usuario");

       


        //construir el pdf
        PdfPTable table = new PdfPTable(1);
        table.addCell("Datos del usuario");
        table.addCell(usuario.getNombre());
        table.addCell(usuario.getDireccion());
        table.addCell(usuario.getApellidos());
        table.addCell(usuario.getEmail());
        table.addCell(usuario.getTelefono());
        
        document.add(table);
    }
    
}
