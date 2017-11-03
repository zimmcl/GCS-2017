package main.java.Model.TemplateMethod;

import main.java.Class.Regulador;

public class Estandar extends SccModel {

  double factor = 1;

  public Estandar() {
    initialize();
  }

  protected void crearRegulador() {
    regulador = new Regulador(this, factor);
  }

  public double getCaloriasConsumidas() {
    return getMetros() * factor;
  }

}