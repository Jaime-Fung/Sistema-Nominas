/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Datos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author esteban
 */
public class CorreoElectronicoNominas implements Runnable {
    
    private Properties objProp = null;
    private Session sesionGlobalSMTP = null;
    private ArrayList<BodyPart> Adjuntos = new ArrayList<>();
    private Datos objDatos;

    public CorreoElectronicoNominas(Datos objDatos) {
        this.objDatos = objDatos;
    }

    private void agregarAdjuntos(Datos objDatos) {
        try {
            for (File archivo : objDatos.getArchivos()) {
                BodyPart adjunto = new MimeBodyPart();
                adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo.getAbsolutePath())));
                adjunto.setFileName(archivo.getName());
                Adjuntos.add(adjunto);
            }
        } catch (MessagingException err) {
            System.out.println(err.getMessage());
        }
    }

    private void sesionSMTP() {
        sesionGlobalSMTP = Session.getInstance(objProp,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("curso_progra2@comredcr.com", "u6X1h1p9@"); // usuario, contraseña
                    }
                });
    }

    private void cargarServicioSMTP() {
        objProp = new Properties();
        String archivoConfiguracion = "config.txt";
        String linea;
        String[] atributos;

        try (BufferedReader objBR = new BufferedReader(new FileReader(archivoConfiguracion))) {
            while ((linea = objBR.readLine()) != null) {
                atributos = linea.split(",");
                objProp.put(atributos[0], atributos[1]);
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public void enviarCorreoElectronico() {
        cargarServicioSMTP();
        sesionSMTP();

        try {
            Message mensajeCorreo = new MimeMessage(sesionGlobalSMTP);
            mensajeCorreo.setFrom(new InternetAddress("curso_progra2@comredcr.com")); // usuario
            mensajeCorreo.setRecipients(Message.RecipientType.TO, InternetAddress.parse(objDatos.getCorreoElectronico()));
            mensajeCorreo.setSubject(objDatos.getAsunto());

            BodyPart mensajeCuerpo = new MimeBodyPart();
            mensajeCuerpo.setText(objDatos.getMensaje());

            Multipart objMultiParte = new MimeMultipart();
            objMultiParte.addBodyPart(mensajeCuerpo);

            if (objDatos.getArchivos() != null) {
                agregarAdjuntos(objDatos);
                for (BodyPart adjunto : Adjuntos) {
                    objMultiParte.addBodyPart(adjunto);
                }
            }

            mensajeCorreo.setContent(objMultiParte);
            Transport.send(mensajeCorreo);

            System.out.println("Correo enviado a " + objDatos.getCorreoElectronico());

        } catch (MessagingException err) {
            throw new RuntimeException(err);
        }
    }

    @Override
    public void run() {
        enviarCorreoElectronico();
    }
    
}
