package main.java.Model.TemplateMethod;

import main.java.Class.Regulador;

public class Mani extends SccModel {

  double factor = 0.25;

  public Mani() {
    initialize();
  }

  protected void crearRegulador() {
    regulador = new Regulador(this, factor);
  }

  public double getCaloriasConsumidas() {
    return getMetros() * factor;
  }

}