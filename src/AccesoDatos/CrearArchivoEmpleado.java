/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Datos;
import Entidades.Deduccion;
import Entidades.Empleado;
import Entidades.Empresa;
import LogicaDeNegocio.LogicaSistema;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author esteban
 */
public class CrearArchivoEmpleado {

    private Datos objDatos;

    public CrearArchivoEmpleado(Datos objDatos) {
        this.objDatos = objDatos;
    }

    // Método para crear el PDF personalizado para cada empleado
    public void crearPdf() {
        Document documento = new Document(PageSize.A4, 50, 50, 50, 50);
        String nombreArchivo = objDatos.getNombre() + "_" + objDatos.getPrimerApellido() + "_" + objDatos.getSegundoApellido() + "_Nomina.pdf"; // Archivo único para cada empleado

        try {
            LogicaSistema objLogicaSistema = new LogicaSistema();
            Empleado objEmpleado = new Empleado();
            Deduccion objDeduccion = new Deduccion();
            objEmpleado.setSalarioBase(objDatos.getSalarioBase());

            objLogicaSistema.ListarDeduccionesEmpleado(objDeduccion);
            double totalDeducciones = objLogicaSistema.calcularTotalDeduccionesEmpleado(objDeduccion);

            objLogicaSistema.calculoSalarioNeto(objEmpleado, objDeduccion, totalDeducciones);

            Empresa objEmpresa = new Empresa();
            AccesoDatos objAccessoDatos = new AccesoDatos();
            try {
                objAccessoDatos.leerArchivoDatosEmpresa(objEmpresa);
            } catch (IOException ex) {
                System.out.println(ex);;
            }

            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            Paragraph titulo = new Paragraph("Nómina de Pago", new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(Chunk.NEWLINE);

            // Información de la empresa
            String infoEmpresa = "Nombre de la Empresa: " + objEmpresa.getNombreEmpresa() + "\n"
                    + "Patrono" + "\n"
                    + "Cédula Jurídica: " + objEmpresa.getCedulaJuridica() + "\n"
                    + "Número Telefónico: " + objEmpresa.getNumeroTelefonico() + "\n"
                    + "Correo Electrónico: " + objEmpresa.getCorreoElectronico();
            documento.add(new Paragraph(infoEmpresa, new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL)));
            documento.add(Chunk.NEWLINE);

            PdfPTable tabla = new PdfPTable(4); // Tabla de la nómina
            tabla.setWidthPercentage(100);
            tabla.addCell(new PdfPCell(new Phrase("Empleado", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            tabla.addCell(new PdfPCell(new Phrase("Salario Base", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            tabla.addCell(new PdfPCell(new Phrase("Deducciones", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
            tabla.addCell(new PdfPCell(new Phrase("Salario Neto", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));

            // Agregar datos específicos del empleado
            tabla.addCell(new Phrase(objDatos.getNombre() + " " + objDatos.getPrimerApellido() + " " + objDatos.getSegundoApellido(), new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL)));
            tabla.addCell(new Phrase(String.valueOf(objDatos.getSalarioBase()), new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL)));
            tabla.addCell(new Phrase(String.valueOf(totalDeducciones + "%"), new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL)));
            tabla.addCell(new Phrase(String.valueOf(objEmpleado.getSalarioNeto()), new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL)));

            documento.add(tabla);

            documento.add(Chunk.NEWLINE);

            // Información sobre la moneda utilizada
            documento.add(new Paragraph("Nota: Todos los montos están expresados en Colones Costarricenses (CRC).", new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC)));

            documento.add(Chunk.NEWLINE);
            documento.add(new Paragraph("Fecha: " + new Date().toString(), new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL)));
            documento.add(Chunk.NEWLINE);
            documento.add(new Paragraph("Gracias por su colaboración", new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL)));
            
            documento.close();
            System.out.println("PDF creado con éxito para " + objDatos.getNombre());

            // Setear el archivo generado al objeto Datos
            objDatos.setArchivos(new File(nombreArchivo));
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

}
