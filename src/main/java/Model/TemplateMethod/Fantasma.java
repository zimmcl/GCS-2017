package main.java.Model.TemplateMethod;

import main.java.Class.Regulador;

public class Fantasma extends SccModel {

  double factor = 1;

  public Fantasma() {
    initialize();
  }

  protected void crearRegulador() {
    regulador = new Regulador(this, factor);
  }

  public double getCaloriasConsumidas() {
    return getMetros() * factor;
  }

}