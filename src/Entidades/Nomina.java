/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author esteban
 */
public class Nomina {
    
    
    private Empleado empleado;
    private double salarioBruto;
    private double salarioNeto;
    
    
    public Nomina(Empleado empleado, double salarioBruto, double salarioNeto)
    {
        this.empleado = empleado;
        this.salarioBruto = salarioBruto;
        this.salarioNeto = salarioNeto;
        
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public double getSalarioNeto() {
        return salarioNeto;
    }

    public void setSalarioNeto(double salarioNeto) {
        this.salarioNeto = salarioNeto;
    }
    
    
}
