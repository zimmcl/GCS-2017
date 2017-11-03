package main.java.Class;

import javax.swing.*;

@SuppressWarnings("serial")
public class BeatBar extends JProgressBar implements Runnable {
  JProgressBar progressBar;
  Thread thread;

  /**
   * Descripcion.
   */
  public BeatBar() {
    thread = new Thread(this);
    setMaximum(100);
    thread.start();
  }

  /**
   * Descripcion.
   */
  public void run() {
    for (;;) {
      int value = getValue();
      value = (int) (value * .75);
      setValue(value);
      repaint();
      try {
        Thread.sleep(50);
      } catch (Exception e) {
        System.out.println("ERROR\n");
      }
    }
  }
}
