package com.certus.spring.pdf;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.certus.spring.models.Producto;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("form-producto")
public class ProductoPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

            //     @SuppressWarnings("unchecked")
            //     List<Producto> listadoProducto =(List<Producto>)model.get("producto");
      
            //    PdfPTable tablaProducto=  new PdfPTable(3);
      
            //   listadoProducto.forEach(producto ->{
            //       tablaProducto.addCell(producto.getNombre());
            //       tablaProducto.addCell(producto.getDescripcion());
            //       tablaProducto.addCell(producto.getPrecio());
            //   });
      
            
        
            //     document.add(tablaProducto);
               
            Producto producto =(Producto)model.get("producto");

            PdfPTable tablaTitulo =  new PdfPTable(1);
            PdfPCell celda = null;
   
           /*Marggenes */
            document.setMargins(-20,-20,40,20);
            document.open();
   
           /*Titulo */
            Font  fuenteTitulo = FontFactory.getFont("Helvetica",16,Color.WHITE);
            celda = new PdfPCell(new Phrase("Listado de Productos",fuenteTitulo));
            celda.setBorder(0);
            celda.setBackgroundColor(new  Color(254, 152, 0));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_CENTER);
            celda.setPadding(30);
   
            tablaTitulo.addCell(celda);
            tablaTitulo.setSpacingAfter(30);
   
            PdfPTable table = new PdfPTable(1);
           table.addCell("Datos del Producto");
  
           table.addCell("Nombre: "+producto.getNombre());
           table.addCell("Descripcion: "+producto.getDescripcion());
           table.addCell("Nombre: "+producto.getPrecio());
   
           
            /*Imagen */
   
           // Image image = Image.getInstance("D:/Documentos/Sumaq/src/main/resources/static/img/LogoSumaq.png");
           // image.scaleAbsoluteWidth(80f);
           // image.scaleAbsoluteHeight(80f);
   
           // celda = new PdfPCell(image);
           // table.addCell(image);
   
   
           document.add(tablaTitulo);
           document.add(table);
           
           
               

    }

}
