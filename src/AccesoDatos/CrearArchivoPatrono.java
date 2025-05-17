/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Deduccion;
import Entidades.Empresa;
import LogicaDeNegocio.LogicaSistema;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Element;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import java.io.File;

import java.io.FileOutputStream;
import java.util.Date;

/**
 *
 * @author esteban
 */
public class CrearArchivoPatrono {

    private Empresa objDatosEmpresa;

    public CrearArchivoPatrono(Empresa objDatosEmpresa) {
        this.objDatosEmpresa = objDatosEmpresa;
    }

    // Método para crear el PDF personalizado para el reporte patronal
    public void crearPdf() {

        
        Document documento = new Document(PageSize.A4, 50, 50, 50, 50);
        String nombreArchivo = objDatosEmpresa.getNombreEmpresa() + "_ReportePatronal.pdf"; // Archivo único para cada reporte patronal

        try {
            
            LogicaSistema objLogicaSistema = new LogicaSistema();
            Deduccion objDeduccion = new Deduccion();
            Empresa objEmpresa = new Empresa();

            objLogicaSistema.ListarDeduccionesPatrono(objDeduccion);
            double totalDeducciones = objLogicaSistema.calcularTotalDeduccionesPatrono(objDeduccion);

            objLogicaSistema.calculoCargasPatronales(objEmpresa, objDeduccion, totalDeducciones);
            
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            Paragraph titulo = new Paragraph("Reporte Patronal", new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(Chunk.NEWLINE);

            // Información de la empresa
            documento.add(new Paragraph("Nombre de la Empresa: " + objDatosEmpresa.getNombreEmpresa(), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL)));
            documento.add(new Paragraph("Cédula Jurídica: " + objDatosEmpresa.getCedulaJuridica(), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL)));
            documento.add(new Paragraph("Número Telefónico: " + objDatosEmpresa.getNumeroTelefonico(), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL)));
            documento.add(new Paragraph("Correo Electrónico: " + objDatosEmpresa.getCorreoElectronico(), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL)));
            documento.add(Chunk.NEWLINE);

            // Información del patrono
            documento.add(new Paragraph("Nombre del Patrono: " + objDatosEmpresa.getNombrePatrono() + " " + objDatosEmpresa.getPrimerApePatrono() + " " + objDatosEmpresa.getSegundoApePatrono(), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL)));
            documento.add(new Paragraph("Ubicación: " + objDatosEmpresa.getUbicacion(), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL)));
            documento.add(Chunk.NEWLINE);

            // Resumen económico
            PdfPTable tabla = new PdfPTable(2); // Tabla del resumen económico
            tabla.setWidthPercentage(100);
            tabla.addCell(new PdfPCell(new Phrase("Suma de Salario Brutos", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            tabla.addCell(new PdfPCell(new Phrase(String.valueOf(objEmpresa.getSalariosBrutos()), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL))));
            tabla.addCell(new PdfPCell(new Phrase("Deducciones Patronales", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            tabla.addCell(new PdfPCell(new Phrase(String.valueOf(totalDeducciones + "%"), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL))));
            tabla.addCell(new PdfPCell(new Phrase("Total a pagar de Deducciones Patronales", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            tabla.addCell(new PdfPCell(new Phrase(String.valueOf(objEmpresa.getPagoPatronal()), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL))));
            tabla.addCell(new PdfPCell(new Phrase("Total a pagar por el Patrono", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            tabla.addCell(new PdfPCell(new Phrase(String.valueOf(objEmpresa.getPagoMensualPatrono()), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL))));

            
            documento.add(tabla);

            documento.add(Chunk.NEWLINE);
            documento.add(new Paragraph("Fecha del reporte: " + new Date().toString(), new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL)));
            documento.add(Chunk.NEWLINE);
            documento.add(new Paragraph("_____________________", new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL)));
            documento.add(new Paragraph("Firma del Patrono", new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL)));

            documento.close();
            System.out.println("PDF creado con éxito para " + objDatosEmpresa.getNombreEmpresa());

            // Setear el archivo generado al objeto DatosPatronales
            objDatosEmpresa.setArchivos(new File(nombreArchivo));
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

}
