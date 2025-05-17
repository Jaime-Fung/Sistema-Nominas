/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author esteban
 */
public class Datos {
    
    private String nombre, primerApellido, segundoApellido, deducciones, correoElectronico, cedula, puesto, salarioNeto, direccion, asunto, archivoAdjunto, nombreArchivoAdjunto, mensajeArchivo, mensaje;
    private ArrayList<File> archivos = new ArrayList<>();
    private String ErrorMSJ = null;
    private double salarioBase;

    public Datos(String correo, String nombre, String asunto, String mensaje, double salarioBase, String primerApellido, String segundoApellido){
        this.correoElectronico = correo;
        this.nombre = nombre;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.salarioBase = salarioBase;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
    }

    
    public Datos(){
        
    }
    
    
    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
    
    
    public String getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(String deducciones) {
        this.deducciones = deducciones;
    }


    public String getErrorMSJ() {
        return ErrorMSJ;
    }

    public void setErrorMSJ(String ErrorMSJ) {
        this.ErrorMSJ = ErrorMSJ;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getSalarioNeto() {
        return salarioNeto;
    }

    public void setSalarioNeto(String salarioNeto) {
        this.salarioNeto = salarioNeto;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(String archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public String getNombreArchivoAdjunto() {
        return nombreArchivoAdjunto;
    }

    public void setNombreArchivoAdjunto(String nombreArchivoAdjunto) {
        this.nombreArchivoAdjunto = nombreArchivoAdjunto;
    }

    public String getMensajeArchivo() {
        return mensajeArchivo;
    }

    public void setMensajeArchivo(String mensajeArchivo) {
        this.mensajeArchivo = mensajeArchivo;
    }

    public ArrayList<File> getArchivos() {
        return archivos;
    }

    public void setArchivos(File archivo) {
        this.archivos.add(archivo);
    }
    
}

