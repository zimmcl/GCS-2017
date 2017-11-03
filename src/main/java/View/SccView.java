package main.java.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import main.java.Controller.ControllerInterface;
import main.java.Controller.SccController;
import main.java.Model.SccModelInterface;
import main.java.Model.TemplateMethod.Mani;
import main.java.Model.TemplateMethod.SccModel;
import main.java.Model.TemplateMethod.Soldado;
import main.java.Observer.BPMObserver;
import main.java.Observer.BeatObserver;

@SuppressWarnings("serial")
public class SccView extends JPanel implements BPMObserver, BeatObserver, ActionListener {

  /**
   * Declaraci�n de las variables locales.
   *
   **/

  SccModelInterface model;
  ControllerInterface controller;
  JLabel vel; // Label para mostrar la velocidad acutal
  JLabel metr; // Label para mostrar los metros recorridos
  JLabel cal; // Label para mostrar las calorias consumidas
  JPanel animacion; // Panel de la animacion
  JPanel botones; // Panel de los botones
  JPanel velocidad; // Panel donde se muestra la velocidad
  JPanel metros; // Panel que muestra metros recorridos;
  JPanel valores; // Panel para mostrar informacion de varios valores
  // (velocidad, metros, calorias, etc)
  JPanel barras;
  JPanel calorias;
  JPanel progreso;
  JPanel velocidadMax;
  JPanel tamanioVuelta;
  JButton incrementa; // Boton para incrementar la velocidad
  JButton decrementa; // Boton para decrementar la velocidad
  JButton pausa; // Boton para pausar el modelo
  JDialog app; // Contenedor de la animacion
  JProgressBar barra;
  JMenuBar barraMenu;
  JButton on; // Boton para encender el modelo
  JButton off; // Boton para apagar el modelo
  JMenuItem manisito; // NO SE BORRA?
  JMenuItem soldadito; // NO SE BORRA?
  JMenuItem salir; // NO SE BORRA?
  TextField campo;
  TextField campoMetros;
  static URL sDirectorio;
  File[] archivos; // NO SE BORRA?
  Soldado soldado; // NO SE BORRA?
  Mani mani; // NO SE BORRA?
  Image incre;
  Image decre;
  Image pau;
  Image play;
  JMenu skin;
  String nombreClase;
  // Lista donde se almacenan las imagenes para la animacion
  protected ArrayList<ImageIcon> images;
  // Cantidad de imagenes a usar en la animacion
  protected int totalImages = 0;
  // Indice de la imagen actual (inicia en 0 por defecto)
  protected int currentImage = 0;
  // Retraso entre cuadro y cuadro, en milisegundos
  protected int animationDelay = 1000;
  // Timer que se encarga de alternar entre los cuadros de la animacion
  protected Timer animationTimer;
  // TRUE: La animacion esta en funcionamiento.
  // FALSE: La animacion esta pausada
  protected boolean corriendo;

  private int aux = 0;

  /**
   * Constructor de la clase. Al ejecutarse, registra a los observadores
   **/

  public SccView(ControllerInterface controller, SccModelInterface model) {
    this.controller = controller;
    this.model = model;
    model.registerObserver((BeatObserver) this);
    model.registerObserver((BPMObserver) this);

    setSize(getPreferredSize()); // Dimensiona el tama�o de la ventana
    images = new ArrayList<ImageIcon>();
    // Carga las imagenes de la animacion a la lista
    cargar();
    // Realiza todas las instrucciones necesarias para crear la vista
    inicializa();

  }

  /*---------------------------------------------------------------------*/
  /*----------------METODOS DE CLASE-----------------*/
  /*---------------------------------------------------------------------*/

  /**
   * Carga al arreglo "images" con las imagenes correspondientes a cada
   * caso.Primero estableze la cantidad de cuadros para cada animacion.
   **/

  public void cargar() {
    nombreClase = model.getClass().getSimpleName();
    switch (nombreClase) {
      case "Abuelo":
        totalImages = 16;
        break;
      case "Cazador":
        totalImages = 8;
        break;
      case "Duo":
        totalImages = 13;
        break;
      case "Estandar":
        totalImages = 16;
        break;
      case "Fantasma":
        totalImages = 7;
        break;
      case "Mani":
        totalImages = 10;
        break;
      case "Soldado":
        totalImages = 16;
        break;
      default:
        break;
    }

    images.clear(); // Limpia la lista antes de cargarla

    for (int i = 0; i < totalImages; ++i) { // Carga la lista con la animacion
      images.add(new ImageIcon(
          getClass().getResource("/imagenes/" + nombreClase + "/" + nombreClase + i + ".jpg")));
    }
    if (this.app != null) {
      this.app.dispose();
    } // Si ya existia una instancia de "app", la elimina

  }

  /**
   * M�todo a llamarse desde el constructor. Se encarga de generar la ventana en
   * si, y de establecer los comportamientos al producirse los eventos
   * 
   **/
  public void inicializa() {
    incrementa = new JButton(); // Crea boton "incrementa"
    incrementa.setPreferredSize(new Dimension(22, 22));
    try {
      incre = ImageIO.read(getClass().getResource("/imagenes/botones/incrementa.jpg"));
      incrementa.setIcon(new ImageIcon(incre));
    } catch (IOException p) {
      p.printStackTrace();
    }

    decrementa = new JButton(); // Crea boton "decrementa"
    decrementa.setPreferredSize(new Dimension(22, 22));
    try {
      decre = ImageIO.read(getClass().getResource("/imagenes/botones/decrementa.jpg"));
      decrementa.setIcon(new ImageIcon(decre));
    } catch (IOException p) {
      p.printStackTrace();
    }

    pausa = new JButton(); // Crea boton "pausa"
    pausa.setPreferredSize(new Dimension(22, 22));
    try {
      pau = ImageIO.read(getClass().getResource("/imagenes/botones/pausa.jpg"));
      pausa.setIcon(new ImageIcon(pau));
    } catch (IOException p) {
      p.printStackTrace();
    }

    try {
      // Crea el icono para el boton "play"
      play = ImageIO.read(getClass().getResource("/imagenes/botones/play.jpg"));
    } catch (IOException p) {
      p.printStackTrace();
    }

    on = new JButton("On");
    off = new JButton("Off");

    animacion = new JPanel(); // Panel de la animacion
    botones = new JPanel(); // Panel de los botones
    valores = new JPanel();

    barraMenu = new JMenuBar();
    // Crea las cajas de texto para la velocidad maxima y el tama�o de vuelta
    campo = new TextField(15);
    campoMetros = new TextField(15);
    // Crea paneles donde se agregaran las cajas de texto
    tamanioVuelta = new JPanel();
    velocidadMax = new JPanel();

    /*
     * Agrega las cajas de texto en los paneles, para luego agregar dichos
     * paneles al contenedor. De esta forma, se tiene mejor control sobre la
     * distribucion de los elementos
     */
    tamanioVuelta.add(new JLabel("Tama�o vuelta:"));
    tamanioVuelta.add(campoMetros);
    velocidadMax.add(new JLabel("Velocidad maxima"));
    velocidadMax.add(campo); //

    corriendo = true;
    /*
     * Crea la barra de progreso, y estableze que se muestre un texto de
     * progreso.
     */
    barra = new JProgressBar(0, 100);
    barra.setStringPainted(true);

    salir = new JMenuItem("Salir");
    manisito = new JMenuItem("Mani");
    soldadito = new JMenuItem("Soldado");

    vel = new JLabel();
    vel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
    vel.setText(model.getSpeed() + " m/s");

    metr = new JLabel();
    metr.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
    metr.setText(((SccModel) model).getMetros() + " metros");

    cal = new JLabel();
    cal.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
    cal.setText(((SccModel) model).getCaloriasConsumidas() + " cal");

    /*
     * Genera un contenedor que simplifica el agregado de multiples paneles.
     * Cada panel se agregra sobre el contenedor. Luego, se formatea el
     * contenedor.
     */
    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

    app = new JDialog();
    animacion.add(this);
    app.getContentPane().add(animacion, BorderLayout.EAST);
    app.add(animacion, BorderLayout.EAST);

    creaActionListeners(); // Define todos los action listeners

    pausa.setEnabled(false); // Se inicia con el boton "pausa" deshabilitado
    progreso = new JPanel();
    progreso.add(new JLabel("Progreso:"));
    progreso.add(barra);

    botones.add(decrementa); // Se agregan los componentes al panel de botones
    botones.add(pausa);
    botones.add(incrementa);
    botones.add(on);
    botones.add(off);

    valores.add(vel); // Se agregan los componentes al panel de valores
    valores.add(metr);
    valores.add(cal);

    container.add(animacion); // Se agregan los componentes al contenedor
    container.add(botones);
    container.add(valores);
    container.add(tamanioVuelta);
    container.add(velocidadMax);
    container.add(progreso);
    // Se estableze el valor maximo de metros que mostrara la barra
    app.add(container);

    startAnimation();
    updateBPm();

    app.pack();
    app.setVisible(true);
  }

  @Override
  public void updateBeat() {
    cal.setText((double) ((SccModel) model).getCaloriasConsumidas() + " cal");
    metr.setText((int) ((SccModel) model).getMetros() + " metros");
    aux++;
    barra.setValue(aux);
    if (barra.getValue() >= barra.getMaximum()) {
      barra.setValue(0);
      barra.repaint();
      aux = 0;
    }

  }

  @Override
  public void updateBPm() {
    double v = (double) model.getSpeed(); // Velocidad en metros por minuto

    if (v == 0) {
      stopAnimation();
    } else if (!animationTimer.isRunning()) {
      animationTimer.restart();
    }

    v = 1 / v; // Tiempo en recorrer un metro, expresado en minutos;
    v = v * 60 * 100; // Tiempo en milisegundos

    animationDelay = (int) v;
    animationTimer.setDelay(animationDelay);
    vel.setText(model.getSpeed() + " mtr/min");
  }

  /**
   * Declara las acciones a realizar por todos los action listeners.
   **/

  public void creaActionListeners() {

    salir.addActionListener(new ActionListener() {
      // Aplicacion
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    // Inicia el controlador.
    on.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.start();
      }
    });
    // Apaga el controlador
    off.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.stop();
      }
    });

    manisito.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        model = new Mani();
        set(model);
      }
    });
    // Habilita la funcion del boton incrementar
    soldadito.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        model = new Soldado();
        set(model);
      }
    });
    // Define funcion del boton "incrementa"
    incrementa.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.increaseBPm();
      }
    });
    /*
     * Define el comportamiento del boton pausa. Si la animacion esta corriendo,
     * debe mostrar la doble barra vertical paralela (simbolo de pausa). Si esta
     * pausada, debe mostrar una flecha (simbolo de play).
     */
    pausa.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        ((SccController) controller).setPause();
        if (corriendo) {
          pausa.setIcon(new ImageIcon(play));
        } else {
          pausa.setIcon(new ImageIcon(pau));
        }
        corriendo = !corriendo;
      }
    });
    // Define el comportamiento del boton decrementar
    decrementa.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.decreaseBPm();
      }
    });
    // Cambia el valor de completado de la barra de estado
    campoMetros.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        barra.setMaximum(Integer.parseInt(campoMetros.getText()));
      }
    });
    // Cambia el valor de velocidad maxima
    campo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          controller.setBPm(Integer.parseInt(campo.getText()));
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "No se puede reconocer el valor ingresado.", "Error",
              0);
        } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);

        }
      }
    });
    // Implementa la accion a realizar al apretar el boton "salir"
    app.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        app.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        controller.stop();
      }
    });
  }

  /**
   * Devuelve las dimensiones del componente.
   **/
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  public Dimension getPreferredSize() {
    return new Dimension(300, 300); // Modifica la dimension de la imagen
  }

  /**
   * Descripcion.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (images.get(currentImage).getImageLoadStatus() == MediaTracker.COMPLETE) {
      images.get(currentImage).paintIcon(this, g, 0, 0);
      currentImage = (currentImage + 1) % totalImages;
    }
  }

  public void actionPerformed(ActionEvent e) {
    repaint();
  }

  /**
   * Declara el timer a usar en la animacion, y lo arranca.
   **/
  public void startAnimation() {

    if (animationTimer == null || animationTimer.getDelay() > 1000) {
      currentImage = 0;
      animationTimer = new Timer(animationDelay, this);
      animationTimer.start();
      corriendo = true;
      // continua desde la ultima imagen mostrada
    } else if (!animationTimer.isRunning()) {
      animationTimer.restart();
    }
  }

  /**
   * Detiene al temporizador de la animacion, previniendo que la animacion
   * continue.
   **/

  public void stopAnimation() {
    animationTimer.stop();

  }

  /**
   * Habilita al boton "off".
   **/

  public void enableStopMenuItem() {
    off.setEnabled(true);
  }

  /**
   * Deshabilita al boton "off".
   **/

  public void disableStopMenuItem() {
    off.setEnabled(false);
  }

  /**
   * Habilita al boton "on".
   **/

  public void enableStartMenuItem() {
    on.setEnabled(true);
  }

  /**
   * Deshabilita al boton "off".
   **/

  public void disableStartMenuItem() {
    on.setEnabled(false);
  }

  /**
   * Habilita al boton "pausa".
   **/

  public void enablePauseButtonItem() {
    pausa.setEnabled(true);
  }

  /**
   * DeshHabilita al boton "pausa".
   **/

  public void disablePauseButtonItem() {
    pausa.setEnabled(false);
  }

  /**
   * Setea el modelo a utilizar, y carga su animacion correspondiente.
   **/
  public void set(SccModelInterface model) {
    cargar();
    model.registerObserver((BeatObserver) this);
    model.registerObserver((BPMObserver) this);
  }

  /**
   * �ESTO NO HAY QUE BORRARLO???.
   **/

  public void setSkin(boolean valor) {
    skin.setEnabled(valor);
  }

}
