/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.ArrayList;

/**
 *
 * @author esteban
 */
public class Deduccion {
    
    private String tipoDeduccion;
    private ArrayList<String[]> deduccionesEmpleado;
    private double porcentajeDeduccion;

  
    public double getPorcentajeDeduccion() {
        return porcentajeDeduccion;
    }

    public ArrayList<String[]> getDeduccionesEmpleado() {
        return deduccionesEmpleado;
    }

    public void setDeduccionesEmpleado(ArrayList<String[]> deduccionesEmpleado) {
        this.deduccionesEmpleado = deduccionesEmpleado;
    }
   

    public void setTipoDeduccion(String tipoDeduccion) {
        this.tipoDeduccion = tipoDeduccion;
    }

    public void setPorcentajeDeduccion(Double porcentajeDeduccion) {
        this.porcentajeDeduccion = porcentajeDeduccion;
    }

    public String getTipoDeduccion() {
        return tipoDeduccion;
    }
    
}
