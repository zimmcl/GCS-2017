package main.java.Model.TemplateMethod;

import main.java.Class.Regulador;

public class Soldado extends SccModel {

  double factor = 1.50;

  public Soldado() {
    initialize();
  }

  protected void crearRegulador() {
    regulador = new Regulador(this, factor);
  }

  public double getCaloriasConsumidas() {
    return getMetros() * factor;
  }

}