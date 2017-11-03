package main.java.Class;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import main.java.Controller.SccController;
import main.java.Model.TemplateMethod.SccModel;

/**
 * La clase "Archivador" es un modulo que se encarga de guardar a la persona en
 * un archivo de texto.
 **/

public class Archivador implements Serializable {

  private static final long serialVersionUID = 1L;
  static Persona p;
  SccController controller;
  SccModel modelo;

  /**
   * Descripcion.
   * 
   * @param persona
   *          "descripcion"
   * @param controller
   *          "descripcion"
   * @param modelo
   *          "descripcion"
   */
  public Archivador(Persona persona, SccController controller, SccModel modelo) {
    p = persona;
    this.controller = controller;
    this.modelo = modelo;
  }

  public Archivador() {

  }

  public void guardar() {
  }

  /**
   * Descripcion.
   * 
   * @return "descripcion"
   */
  public String[] cargar() {
    String[] cadena = new String[3];
    try {
      /* Llamamos el metodo que permite cargar la ventana */
      JFileChooser file = new JFileChooser();
      file.setCurrentDirectory(new File("C:\\"));
      file.showOpenDialog(null);
      /* Abrimos el archivo seleccionado */
      File abre = file.getSelectedFile();

      /**
       * Recorremos el archivo, lo leemos para plasmarlo en el area de texto
       */
      if (abre != null) {
        boolean fin = false;
        int i = 0;
        String linea;
        FileReader fichero = new FileReader(abre);
        BufferedReader buffer = new BufferedReader(fichero);
        StringBuffer sbf = new StringBuffer();
        while (fin == false) {
          linea = buffer.readLine();
          if (linea != null) {
            cadena[i] = linea;
            sbf.append(linea + "\n");
            i++;
          } else {
            fin = true;
          }
        }
        buffer.close();

      }
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, ex + "" + "\nNo se ha encontrado el archivo",
          "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
    }
    return cadena;

  }
}