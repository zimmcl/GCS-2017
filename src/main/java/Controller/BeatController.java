package main.java.Controller;

import main.java.Model.BeatModel;
import main.java.Model.BeatModelInterface;
import main.java.View.DJView;
import main.java.View.MultiplesView;

public class BeatController implements ControllerInterface {
  BeatModelInterface model;
  public DJView view;

  /**
   * Descripcion.
   * 
   * @param model
   *          Descripcion.
   */
  public BeatController(BeatModelInterface model) {
    this.model = model;
    view = new DJView(this, model);
    view.createView();
    view.createControls();
    view.disableStopMenuItem();
    view.enableStartMenuItem();
    model.initialize();
  }

  /**
   * Constructor creado para el uso de MultiplesView.
   */
  public BeatController(BeatModel model, MultiplesView view) {

    this.view = view;
    this.view.disableStopMenuItem();
    this.view.enableStartMenuItem();
    this.model = model;
    this.model.initialize();
  }

  /**
   * Descripcion.
   */
  public void start() {
    model.on();
    view.disableStartMenuItem();
    view.enableStopMenuItem();
  }

  /**
   * Descripcion.
   */
  public void stop() {
    model.off();
    view.disableStopMenuItem();
    view.enableStartMenuItem();
  }

  /**
   * Descripcion.
   */
  public void increaseBPm() {
    int bpm = model.getBPm();
    model.setBPm(bpm + 1);
  }

  public void decreaseBPm() {
    int bpm = model.getBPm();
    model.setBPm(bpm - 1);
  }

  public void setBPm(int bpm) {
    model.setBPm(bpm);
  }
}
