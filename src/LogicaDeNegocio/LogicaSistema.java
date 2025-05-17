/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaDeNegocio;

import AccesoDatos.AccesoDatos;
import Entidades.Deduccion;
import Entidades.Empleado;
import Entidades.Empresa;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author esteban
 */
public class LogicaSistema {

    private static AccesoDatos objAccesoDatos;
    private static IdControl idControl;

    public LogicaSistema() throws IOException {
        idControl = new IdControl("IdControl.txt");
    }

    public void listarEmpleados(Empleado objEmpleado) throws IOException {
        objAccesoDatos = new AccesoDatos();
        objAccesoDatos.setNombreArchivo("Empleados.txt");
        objEmpleado.setListaEmpleados(objAccesoDatos.leerArchivo());

    }

    public void agregarEmpleado(Empleado objEmpleado) throws IOException {

        objAccesoDatos = new AccesoDatos();
        objAccesoDatos.setNombreArchivo("Empleados.txt");
        int id = idControl.getSiguienteId(objAccesoDatos.getNombreArchivo());
        objEmpleado.setId(id);
        objAccesoDatos.setLinea(objEmpleado.getId() + "," + objEmpleado.getNombre() + "," + objEmpleado.getPrimerApellido() + "," + objEmpleado.getSegundoApellido() + "," + objEmpleado.getCorreoElectronico() + "," + objEmpleado.getCedula() + "," + objEmpleado.getPuesto() + "," + objEmpleado.getSalarioBase() + "," + objEmpleado.getDireccion());
        objAccesoDatos.escribirArchivo(objAccesoDatos.getLinea());

    }

    public void editarEmpleado(Empleado objEmpleado) throws IOException {

        objAccesoDatos = new AccesoDatos();
        objAccesoDatos.setEliminar(false);
        objAccesoDatos.setNombreArchivo("Empleados.txt");
        String lineaNueva = objEmpleado.getId() + "," + objEmpleado.getNombre() + "," + objEmpleado.getPrimerApellido() + "," + objEmpleado.getSegundoApellido() + "," + objEmpleado.getCorreoElectronico() + "," + objEmpleado.getCedula() + "," + objEmpleado.getPuesto() + "," + objEmpleado.getSalarioBase() + "," + objEmpleado.getDireccion();
        objAccesoDatos.modificarLineas(String.valueOf(objEmpleado.getId()), lineaNueva);

    }

    public void eliminarEmpleado(Empleado objEmpleado) throws IOException {

        objAccesoDatos = new AccesoDatos();
        objAccesoDatos.setNombreArchivo("Empleados.txt");
        objAccesoDatos.setEliminar(true);
        objAccesoDatos.modificarLineas(String.valueOf(objEmpleado.getId()), "");

    }

    public void ListarDeduccionesEmpleado(Deduccion objDeduccion) throws FileNotFoundException, IOException {
        objAccesoDatos = new AccesoDatos();
        objAccesoDatos.setNombreArchivo("DeduccionesEmpleado.txt");
        objDeduccion.setDeduccionesEmpleado(objAccesoDatos.leerArchivo());

    }

    public double calcularTotalDeduccionesEmpleado(Deduccion objDeducciones) {

        ArrayList<String[]> deduccionesEmpleado = objDeducciones.getDeduccionesEmpleado();

        double total = objDeducciones.getPorcentajeDeduccion();
        for (String[] deduccion : deduccionesEmpleado) {
            try {
                total += Double.parseDouble(deduccion[1]);

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(null, "Error al convertir el valor de la deducción: " + deduccion[1]);
            }
        }
        return total;

    }

    public void ListarDeduccionesPatrono(Deduccion objDeduccion) throws FileNotFoundException, IOException {
        objAccesoDatos = new AccesoDatos();
        objAccesoDatos.setNombreArchivo("DeduccionesPatrono.txt");
        objDeduccion.setDeduccionesEmpleado(objAccesoDatos.leerArchivo());

    }

    public double calcularTotalDeduccionesPatrono(Deduccion objDeducciones) {

        ArrayList<String[]> deduccionesPatrono = objDeducciones.getDeduccionesEmpleado();

        double total = objDeducciones.getPorcentajeDeduccion();
        for (String[] deduccion : deduccionesPatrono) {
            try {
                total += Double.parseDouble(deduccion[1]);

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(null, "Error al convertir el valor de la deducción: " + deduccion[1]);
            }
        }

        return total;

    }

    public void calculoSalarioNeto(Empleado objEmpleado, Deduccion objDeducciones, double totalDeducciones) throws IOException {
        objAccesoDatos = new AccesoDatos();

        try {
            objAccesoDatos.setNombreArchivo("Empleados.txt");
            objAccesoDatos.leerArchivo();
            objEmpleado.setSalarioNeto(objEmpleado.getSalarioBase() - ((totalDeducciones / 100) * objEmpleado.getSalarioBase()));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void guardarInformacionEmpresa(Empresa objEmpresa) throws IOException {

        objAccesoDatos = new AccesoDatos();
        objAccesoDatos.setEliminar(false);
        objAccesoDatos.setNombreArchivo("InformacionEmpresa.txt");
        String lineaNueva = objEmpresa.getId() + "," + objEmpresa.getNombreEmpresa() + "," + objEmpresa.getCedulaJuridica() + "," + objEmpresa.getNumeroTelefonico() + "," + objEmpresa.getCorreoElectronico() + "," + objEmpresa.getNombrePatrono() + "," + objEmpresa.getPrimerApePatrono() + "," + objEmpresa.getSegundoApePatrono() + "," + objEmpresa.getUbicacion();
        objAccesoDatos.modificarLineas(String.valueOf(objEmpresa.getId()), lineaNueva);

    }

    public void calculoNomina() {

    }

    public void calculoCargasPatronales(Empresa objEmpresa, Deduccion objDeducciones, double totalDeducciones) {
        AccesoDatos objAccesoDatos = new AccesoDatos();
        double totalSalariosBase = 0.0;

        try {
            objAccesoDatos.setNombreArchivo("Empleados.txt"); // Cambia a la ruta correcta de tu archivo
            ArrayList<String[]> lineas = objAccesoDatos.leerArchivo();

            for (String[] datos : lineas) {
                try {
                    // Asegurarse de que hay suficientes datos en la línea

                    // Convertir el valor en el índice 6 (salario base) a double y sumarlo al total
                    double salarioBase = Double.parseDouble(datos[7].trim());
                    totalSalariosBase += salarioBase;

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error al convertir el salario base: " + datos[6]);
                }
            }

            objEmpresa.setPagoPatronal((totalDeducciones / 100) * totalSalariosBase);
            objEmpresa.setSalariosBrutos(totalSalariosBase);
            objEmpresa.setPagoMensualPatrono(totalSalariosBase + objEmpresa.getPagoPatronal());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
