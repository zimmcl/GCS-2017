package main.java.Model.TemplateMethod;

import main.java.Class.Regulador;

public class Abuelo extends SccModel {

  double factor = 10;

  public Abuelo() {
    initialize();
  }

  protected void crearRegulador() {
    regulador = new Regulador(this, factor);
  }

  public double getCaloriasConsumidas() {
    return getMetros() * factor;
  }

}