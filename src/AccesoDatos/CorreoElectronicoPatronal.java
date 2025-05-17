/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Empresa;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author esteban
 */
public class CorreoElectronicoPatronal implements Runnable{
    
    
    private Properties objProp = null;
    private Session sesionGlobalSMTP = null;
    private ArrayList<BodyPart> Adjuntos = new ArrayList<>();
    private Empresa objEmpresa;

    public CorreoElectronicoPatronal(Empresa objEmpresa) {
        this.objEmpresa = objEmpresa;
    }

    private void agregarAdjuntos(Empresa objEmpresa) {
        try {
            for (File archivo : objEmpresa.getArchivos()) {
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
            mensajeCorreo.setRecipients(Message.RecipientType.TO, InternetAddress.parse(objEmpresa.getCorreoElectronico()));
            mensajeCorreo.setSubject(objEmpresa.getAsunto());

            BodyPart mensajeCuerpo = new MimeBodyPart();
            mensajeCuerpo.setText(objEmpresa.getMensaje());

            Multipart objMultiParte = new MimeMultipart();
            objMultiParte.addBodyPart(mensajeCuerpo);

            if (objEmpresa.getArchivos() != null) {
                agregarAdjuntos(objEmpresa);
                for (BodyPart adjunto : Adjuntos) {
                    objMultiParte.addBodyPart(adjunto);
                }
            }

            mensajeCorreo.setContent(objMultiParte);
            Transport.send(mensajeCorreo);

            System.out.println("Correo enviado a " + objEmpresa.getCorreoElectronico());

        } catch (MessagingException err) {
            throw new RuntimeException(err);
        }
    }

    @Override
    public void run() {
        enviarCorreoElectronico();
    }
    
}
