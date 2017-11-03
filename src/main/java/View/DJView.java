package main.java.View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import main.java.Class.BeatBar;
import main.java.Controller.ControllerInterface;
import main.java.Model.BeatModelInterface;
import main.java.Observer.BPMObserver;
import main.java.Observer.BeatObserver;

public class DJView implements ActionListener, BeatObserver, BPMObserver {
  BeatModelInterface model;
  ControllerInterface controller;
  JDialog viewFrame;
  JPanel viewPanel;
  BeatBar beatBar;
  JLabel bpmOutputLabel;
  JDialog controlFrame;
  JPanel controlPanel;
  JLabel bpmLabel;
  JTextField bpmTextField;
  JButton setBPmbutton;
  JButton increaseBPmbutton;
  JButton decreaseBPmbutton;
  JMenuBar menuBar;
  JMenu menu;
  JMenuItem startMenuItem;
  JMenuItem stopMenuItem;

  /**
   * Descripcion.
   * 
   * @param controller
   *          Descripcion.
   * @param model
   *          Descripcion.
   */
  public DJView(ControllerInterface controller, BeatModelInterface model) {
    this.controller = controller;
    this.model = model;
    model.registerObserver((BeatObserver) this);
    model.registerObserver((BPMObserver) this);
  }

  public DJView() {
    // TODO Auto-generated constructor stub
  }

  /**
   * Descripcion.
   */
  public void createView() {
    // Create all Swing components here
    viewPanel = new JPanel(new GridLayout(1, 2));
    viewFrame = new JDialog();
    viewFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    viewFrame.setSize(new Dimension(100, 80));
    bpmOutputLabel = new JLabel("offline", SwingConstants.CENTER);
    beatBar = new BeatBar();
    beatBar.setValue(0);
    JPanel bpmPanel = new JPanel(new GridLayout(2, 1));
    bpmPanel.add(beatBar);
    bpmPanel.add(bpmOutputLabel);
    viewPanel.add(bpmPanel);
    viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
    viewFrame.pack();
    viewFrame.setVisible(true);
  }

  /**
   * Descripcion.
   */
  public void createControls() {
    // Create all Swing components here
    JFrame.setDefaultLookAndFeelDecorated(true);
    controlFrame = new JDialog();
    controlFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    controlFrame.setSize(new Dimension(100, 80));

    controlPanel = new JPanel(new GridLayout(1, 2));

    menuBar = new JMenuBar();
    menu = new JMenu("DJ Control");
    startMenuItem = new JMenuItem("Start");
    menu.add(startMenuItem);
    startMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        controller.start();
      }
    });
    stopMenuItem = new JMenuItem("Stop");
    menu.add(stopMenuItem);
    stopMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        controller.stop();
      }
    });
    JMenuItem exit = new JMenuItem("Quit");
    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        System.exit(0);
      }
    });

    menu.add(exit);
    menuBar.add(menu);
    controlFrame.setJMenuBar(menuBar);

    bpmTextField = new JTextField(2);
    bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
    setBPmbutton = new JButton("Set");
    setBPmbutton.setSize(new Dimension(10, 40));
    increaseBPmbutton = new JButton(">>");
    decreaseBPmbutton = new JButton("<<");
    setBPmbutton.addActionListener(this);
    increaseBPmbutton.addActionListener(this);
    decreaseBPmbutton.addActionListener(this);

    JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

    buttonPanel.add(decreaseBPmbutton);
    buttonPanel.add(increaseBPmbutton);

    JPanel enterPanel = new JPanel(new GridLayout(1, 2));
    enterPanel.add(bpmLabel);
    enterPanel.add(bpmTextField);
    JPanel insideControlPanel = new JPanel(new GridLayout(3, 1));
    insideControlPanel.add(enterPanel);
    insideControlPanel.add(setBPmbutton);
    insideControlPanel.add(buttonPanel);
    controlPanel.add(insideControlPanel);

    bpmLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    bpmOutputLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    controlFrame.getRootPane().setDefaultButton(setBPmbutton);
    controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);

    controlFrame.pack();
    controlFrame.setVisible(true);
  }

  public void enableStopMenuItem() {
    stopMenuItem.setEnabled(true);
  }

  public void disableStopMenuItem() {
    stopMenuItem.setEnabled(false);
  }

  public void enableStartMenuItem() {
    startMenuItem.setEnabled(true);
  }

  public void disableStartMenuItem() {
    startMenuItem.setEnabled(false);
  }

  /**
   * Descripcion.
   */
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == setBPmbutton) {
      int bpm = Integer.parseInt(bpmTextField.getText());
      controller.setBPm(bpm);
    } else if (event.getSource() == increaseBPmbutton) {
      controller.increaseBPm();
    } else if (event.getSource() == decreaseBPmbutton) {
      controller.decreaseBPm();
    }
  }

  /**
   * Descripcion.
   */
  public void updateBPm() {
    if (model != null) {
      int bpm = model.getBPm();
      if (bpm == 0) {
        if (bpmOutputLabel != null) {
          bpmOutputLabel.setText("offline");
        }
      } else {
        if (bpmOutputLabel != null) {
          bpmOutputLabel.setText("Current BPM: " + model.getBPm());
        }
      }
    }
  }

  /**
   * Descripcion.
   */
  public void updateBeat() {
    if (beatBar != null) {
      beatBar.setValue(100);
    }
  }
}