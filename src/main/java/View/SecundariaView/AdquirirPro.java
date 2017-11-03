package main.java.View.SecundariaView;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

public class AdquirirPro implements ActionListener {

  private JDialog frmAdquirirVersinPro;
  private JTextPane txtpnLaValentaQue;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          AdquirirPro window = new AdquirirPro();
          window.frmAdquirirVersinPro.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public AdquirirPro() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmAdquirirVersinPro = new JDialog();
    frmAdquirirVersinPro.setModal(true);
    frmAdquirirVersinPro.setResizable(false);
    frmAdquirirVersinPro.setForeground(Color.WHITE);
    frmAdquirirVersinPro.setBackground(Color.WHITE);
    frmAdquirirVersinPro.setTitle("Adquirir Version PRO");
    frmAdquirirVersinPro.setBounds(100, 100, 500, 500);
    SpringLayout springLayout = new SpringLayout();
    frmAdquirirVersinPro.getContentPane().setLayout(springLayout);

    Panel panel = new Panel();
    springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH,
        frmAdquirirVersinPro.getContentPane());
    springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST,
        frmAdquirirVersinPro.getContentPane());
    springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH,
        frmAdquirirVersinPro.getContentPane());
    springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST,
        frmAdquirirVersinPro.getContentPane());
    panel.setBackground(Color.WHITE);
    panel.setForeground(Color.WHITE);
    frmAdquirirVersinPro.getContentPane().add(panel);

    JTextPane txtpnUnaPersonaAtletica = new JTextPane();
    txtpnUnaPersonaAtletica.setEditable(false);
    txtpnUnaPersonaAtletica.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 13));
    txtpnUnaPersonaAtletica.setText("Una persona atletica y activa necesita un software potente; "
        + "una herramienta de gestion de actividades expansible, dotada de todas las herramientas"
        + " decisorias que requiera la persona para poder realizar las elecciones acertadas y "
        + "mantener firme el rumbo de la activad fisica.\r\n\r\nEl software SDA CintaMatrix "
        + "version PRO es la herramienta mas potente de la galeria de soluciones para la"
        + " gestion de la actividad fisica. No solo cubre todos los requisitos del"
        + " circuito deportivo, sino que le brinda mecanismos informativos en"
        + " tiempo real, indispensables para estar al tanto con el desempeño "
        + "de su actividad fisica. Pero, ademas, SDA CintaMatrix version PRO"
        + " marca la diferencia con productos similares de la competencia,"
        + " al tener un costo accesible y disponer de flexibles planes de pago."
        + "\r\n\r\nCon la adquisicion de SDA CintaMatrix version PRO, fomenta"
        + " el desarrollo de este tipo de software.\r\nDejenos ayudarlo y adquiera"
        + " la licencia por solo 22.329,90 VND(Dong Vietnamita)");

    JButton btnComprarLicenciaPro = new JButton("COMPRAR LICENCIA PRO");
    btnComprarLicenciaPro.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        ComprarLicenciaPerformed(evt);
      }

    });
    btnComprarLicenciaPro.setForeground(SystemColor.textHighlight);
    btnComprarLicenciaPro.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 17));

    txtpnLaValentaQue = new JTextPane();
    txtpnLaValentaQue.setForeground(Color.WHITE);
    txtpnLaValentaQue.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 13));
    txtpnLaValentaQue.setEditable(false);
    txtpnLaValentaQue.setText("La valentia que posee al haber confiado en nosotros,"
        + " le hace atribuidor de una licencia SDA CintaMatrix PRO"
        + " sin cargo alguno:\r\n                                          "
        + "X7LL-45T8-10ÑP-4R44\r\n\r\nDe todas maneras, seguro que el crack ya esta en la web ");
    GroupLayout groupLayout = new GroupLayout(panel);
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        .addComponent(txtpnUnaPersonaAtletica, GroupLayout.PREFERRED_SIZE, 484,
            GroupLayout.PREFERRED_SIZE)
        .addGroup(
            groupLayout.createSequentialGroup().addGap(126).addComponent(btnComprarLicenciaPro))
        .addComponent(txtpnLaValentaQue, GroupLayout.PREFERRED_SIZE, 484,
            GroupLayout.PREFERRED_SIZE));
    groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
            .addComponent(txtpnUnaPersonaAtletica, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGap(6).addComponent(btnComprarLicenciaPro).addGap(6).addComponent(txtpnLaValentaQue,
                GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)));
    panel.setLayout(groupLayout);
  }

  /**
   * Descripcion.
   * 
   * @param evt
   *          Descripcion.
   */
  private void ComprarLicenciaPerformed(ActionEvent evt) {
    txtpnLaValentaQue.setForeground(Color.BLACK);

  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub

  }
}
