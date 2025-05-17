/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.Empresa;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author esteban
 */
public class AccesoDatos {

    private String nombreArchivo;
    private String linea;
    private boolean eliminar;

    public boolean isEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public void escribirArchivo(String linea) throws IOException {

        try (BufferedWriter objBw = new BufferedWriter(new FileWriter(this.getNombreArchivo(), true))) {
            objBw.write(linea);
            objBw.newLine();
        }
    }

    public ArrayList<String[]> leerArchivo() throws FileNotFoundException, IOException {

        ArrayList<String[]> lista = new ArrayList<>();

        try (BufferedReader objBr = new BufferedReader(new FileReader(this.getNombreArchivo()))) {
            String linea;
            while ((linea = objBr.readLine()) != null) {
                String[] datos = linea.split(",");
                lista.add(datos);
            }
            return lista;
        }
    }

    public void modificarLineas(String id, String lineaNueva) throws IOException {

        File archivoActual = new File(this.nombreArchivo);
        File archivoTemp = new File("temp" + this.nombreArchivo);

        try (BufferedReader objBR = new BufferedReader(new FileReader(archivoActual)); BufferedWriter objBW = new BufferedWriter(new FileWriter(archivoTemp))) {
            String lineaActual;
            while ((lineaActual = objBR.readLine()) != null) {

                String[] datos = lineaActual.split(",");
                if (this.eliminar) {

                    if (datos[0].equals(id)) {
                        continue;

                    }
                    objBW.write(lineaActual);
                    objBW.newLine();

                } else {

                    if (datos[0].equals(id)) {
                        objBW.write(lineaNueva);
                        objBW.newLine();

                    } else {
                        objBW.write(lineaActual);
                        objBW.newLine();

                    }

                }

            }

        }
        if (!archivoActual.delete()) {
            JOptionPane.showMessageDialog(null, "no se puede borrar");
        }

        if (!archivoTemp.renameTo(archivoActual)) {

            JOptionPane.showMessageDialog(null, "no se puede renombrar");

        }
    }

    public void leerArchivoDatosEmpresa(Empresa objEmpresa) throws IOException {
        BufferedReader reader = null;
        try {
            // Asegúrate de que la ruta al archivo sea la correcta
            File file = new File("InformacionEmpresa.txt");
            reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine(); // Leer la primera línea

            if (line != null) {
                // Divide la línea en partes usando coma como delimitador
                String[] datos = line.split(",");

                // Asignar los valores a los atributos de Empresa
                objEmpresa.setNombreEmpresa(datos[1]);
                objEmpresa.setCedulaJuridica(datos[2]);
                objEmpresa.setNumeroTelefonico(datos[3]);
                objEmpresa.setCorreoElectronico(datos[4]);
                objEmpresa.setNombrePatrono(datos[5]);
                objEmpresa.setPrimerApePatrono(datos[6]);
                objEmpresa.setSegundoApePatrono(datos[7]);
                objEmpresa.setUbicacion(datos[8]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Muestra el error en la consola
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

}
