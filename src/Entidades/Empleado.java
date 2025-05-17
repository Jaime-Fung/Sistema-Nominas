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
public class Empleado {
    
    
    private int id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String correoElectronico;
    private String cedula;
    private String puesto;
    private double salarioBase;
    private double salarioNeto;
    private String direccion;
    private ArrayList<String[]> listaEmpleados;
    
    
    public Empleado(int id,String nombre, String primerApellido, String segundoApellido, String correoElectronico, String cedula, String puesto, double salarioBase, String direccion, ArrayList<String[]> listaEmpleados)
    {
        this.id = id;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.correoElectronico = correoElectronico;
        this.cedula = cedula;
        this.puesto = puesto;
        this.salarioBase = salarioBase;
        this.direccion = direccion;
        this.listaEmpleados = listaEmpleados;
        
    
    }


    public Empleado() {
        
    }

    
    
    public ArrayList<String[]> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(ArrayList<String[]> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }
    
    public double getSalarioNeto() {
        return salarioNeto;
    }

    public void setSalarioNeto(double salarioNeto) {
        this.salarioNeto = salarioNeto;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
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

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
    
    
}
