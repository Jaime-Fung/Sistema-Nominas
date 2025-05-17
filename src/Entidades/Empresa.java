/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Ryzon
 */
public class Empresa {
    
    private int id;
    private String nombreEmpresa, cedulaJuridica, numeroTelefonico, correoElectronico, nombrePatrono, primerApePatrono, segundoApePatrono, ubicacion, asunto, mensaje;

    private ArrayList<File> archivos = new ArrayList<>();
    private double pagoPatronal;
    private double pagoMensualPatrono;
    private double SalariosBrutos;

    public Empresa(String correoElectronico, String nombrePatrono, String primerApePatrono, String segundoApePatrono, String asunto, String mensaje, String nombreEmpresa, String ubicacion, String cedulaJuridica, String numeroTelefonico) {
        this.correoElectronico = correoElectronico;
        this.nombrePatrono = nombrePatrono;
        this.primerApePatrono = primerApePatrono;
        this.segundoApePatrono = segundoApePatrono;
        this.mensaje = mensaje;
        this.asunto = asunto;
        this.nombreEmpresa = nombreEmpresa;
        this.ubicacion = ubicacion;
        this.cedulaJuridica = cedulaJuridica;
        this.numeroTelefonico = numeroTelefonico;

    }

    public Empresa() {
    }

    public double getPagoMensualPatrono() {
        return pagoMensualPatrono;
    }

    public void setPagoMensualPatrono(double pagoMensualPatrono) {
        this.pagoMensualPatrono = pagoMensualPatrono;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public double getSalariosBrutos() {
        return SalariosBrutos;
    }

    public void setSalariosBrutos(double setSalariosBrutos) {
        this.SalariosBrutos = setSalariosBrutos;
    }
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
    public double getPagoPatronal() {
        return pagoPatronal;
    }

    public void setPagoPatronal(double pagoPatronal) {
        this.pagoPatronal = pagoPatronal;
    }


    public String getCedulaJuridica() {
        return cedulaJuridica;
    }

    public void setCedulaJuridica(String cedulaJuridica) {
        this.cedulaJuridica = cedulaJuridica;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombrePatrono() {
        return nombrePatrono;
    }

    public void setNombrePatrono(String nombrePatrono) {
        this.nombrePatrono = nombrePatrono;
    }

    public String getPrimerApePatrono() {
        return primerApePatrono;
    }

    public void setPrimerApePatrono(String primerApePatrono) {
        this.primerApePatrono = primerApePatrono;
    }

    public String getSegundoApePatrono() {
        return segundoApePatrono;
    }

    public void setSegundoApePatrono(String segundoApePatrono) {
        this.segundoApePatrono = segundoApePatrono;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    
    
    public ArrayList<File> getArchivos() {
        return archivos;
    }

    public void setArchivos(File archivo) {
        this.archivos.add(archivo);
    }
    
}
