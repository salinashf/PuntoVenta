package ventanas.dialog;

import startup.FrameFacturacion;
import javax.swing.JPanel;
import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.WindowConstants;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JLabel;
import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.JTextField;

public class DialogLineaDetalle extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JPanel jPanel = null;
    private JButton BAnular = null;
    private JButton BCancelar = null;
    private JLabel LblClave = null;
    private JLabel LblInforma = null;
    private JLabel Lbl2 = null;
    private JTextField TxtArticulo = null;
    private JTextField TxtCantidad = null;
    private JTextField TxtPrecio = null;
    public static String articulo = new String();
    public static String precio = new String();
    public static String cantidad = new String();
    public static boolean ingresadet = false;

    public DialogLineaDetalle() {
        super();
        initialize();
    }

    private void initialize() {
        this.setSize(561, 178);
        this.setResizable(false);
        this.setModal(true);
        this.setTitle("Linea de Venta de la Factura No. " + FrameFacturacion.numero);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setContentPane(getJContentPane());
        this.setLocationRelativeTo(null);
        cargar();
        TxtCantidad.requestFocus();
    }

    private JPanel getJContentPane() {
        if (jContentPane == null) {
            LblClave = new JLabel();
            LblClave.setText("PRECIO DE VENTA:");
            LblClave.setSize(new Dimension(109, 23));
            LblClave.setLocation(new Point(313, 43));
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJPanel(), null);
            jContentPane.add(getBAnular(), null);
            jContentPane.add(getBCancelar(), null);
        }
        return jContentPane;
    }

    private JPanel getJPanel() {
        if (jPanel == null) {
            Lbl2 = new JLabel();
            Lbl2.setText("CANTIDAD:");
            Lbl2.setLocation(new Point(15, 43));
            Lbl2.setSize(new Dimension(69, 23));
            LblInforma = new JLabel();
            LblInforma.setHorizontalAlignment(SwingConstants.LEFT);
            LblInforma.setAutoscrolls(false);
            LblInforma.setHorizontalTextPosition(SwingConstants.LEFT);
            LblInforma.setLocation(new Point(15, 11));
            LblInforma.setSize(new Dimension(69, 23));
            LblInforma.setText("ARTICULO:");
            jPanel = new JPanel();
            jPanel.setLayout(null);
            jPanel.setBounds(new Rectangle(12, 10, 533, 76));
            jPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            jPanel.add(LblInforma, null);
            jPanel.add(Lbl2, null);
            jPanel.add(getTxtArticulo(), null);
            jPanel.add(LblClave, null);
            jPanel.add(getTxtPrecio(), null);
            jPanel.add(getTxtCantidad(), null);
        }
        return jPanel;
    }

    private JButton getBAnular() {
        if (BAnular == null) {
            BAnular = new JButton();
            BAnular.setText("Aceptar");
            BAnular.setToolTipText("Aceptar datos de l�nea de venta");
            BAnular.setHorizontalTextPosition(SwingConstants.RIGHT);
            BAnular.setHorizontalAlignment(SwingConstants.LEFT);
            BAnular.setLocation(new Point(302, 97));
            BAnular.setSize(new Dimension(116, 41));
            BAnular.setFont(new Font("Dialog", Font.PLAIN, 12));
            BAnular.setIcon(new ImageIcon("IMAGENES/MODIFICAR.JPG"));
            BAnular.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    cantidad = TxtCantidad.getText();
                    precio = TxtPrecio.getText();
                    ingresadet = true;
                    dispose();
                }
            });
        }
        return BAnular;
    }

    private JButton getBCancelar() {
        if (BCancelar == null) {
            BCancelar = new JButton();
            BCancelar.setToolTipText("Cancelar datos y salir");
            BCancelar.setIcon(new ImageIcon("IMAGENES/CANCELAR2.JPG"));
            BCancelar.setHorizontalTextPosition(SwingConstants.RIGHT);
            BCancelar.setHorizontalAlignment(SwingConstants.LEFT);
            BCancelar.setFont(new Font("Dialog", Font.PLAIN, 12));
            BCancelar.setLocation(new Point(429, 97));
            BCancelar.setSize(new Dimension(116, 41));
            BCancelar.setText("Cancelar");
            BCancelar.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    ingresadet = false;
                    dispose();
                }
            });
        }
        return BCancelar;
    }

    private JTextField getTxtArticulo() {
        if (TxtArticulo == null) {
            TxtArticulo = new JTextField();
            TxtArticulo.setLocation(new Point(94, 11));
            TxtArticulo.setDisabledTextColor(Color.darkGray);
            TxtArticulo.setEnabled(false);
            TxtArticulo.setFont(new Font("Arial Narrow", Font.BOLD, 16));
            TxtArticulo.setBackground(new Color(243, 243, 243));
            TxtArticulo.setSize(new Dimension(423, 23));
        }
        return TxtArticulo;
    }

    private JTextField getTxtCantidad() {
        if (TxtCantidad == null) {
            TxtCantidad = new JTextField();
            TxtCantidad.setSize(new Dimension(71, 23));
            TxtCantidad.setText("1");
            TxtCantidad.setHorizontalAlignment(JTextField.RIGHT);
            TxtCantidad.setFont(new Font("Dialog", Font.BOLD, 14));
            TxtCantidad.setLocation(new Point(94, 43));
            TxtCantidad.addFocusListener(new java.awt.event.FocusAdapter() {

                public void focusLost(java.awt.event.FocusEvent e) {
                    if (TxtCantidad.getText().equals("0") == true || TxtCantidad.getText().length() == 0) {
                        TxtCantidad.setText("1");
                    }
                }

                public void focusGained(java.awt.event.FocusEvent e) {
                    TxtCantidad.select(0, TxtCantidad.getText().length());
                }
            });
            TxtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {

                public void keyTyped(java.awt.event.KeyEvent e) {
                    char caracter;
                    caracter = (e.getKeyChar() + "").charAt(0);
                    if (TxtCantidad.getText().length() < 9) {
                        if ((caracter + "").matches("[0-9]")) {
                            e.setKeyChar(caracter);
                        } else {
                            e.consume();
                        }
                    } else {
                        e.consume();
                    }
                }
            });
        }
        return TxtCantidad;
    }

    private JTextField getTxtPrecio() {
        if (TxtPrecio == null) {
            TxtPrecio = new JTextField();
            TxtPrecio.setSize(new Dimension(88, 23));
            TxtPrecio.setText("0.00");
            TxtPrecio.setHorizontalAlignment(JTextField.RIGHT);
            TxtPrecio.setFont(new Font("Dialog", Font.BOLD, 14));
            TxtPrecio.setLocation(new Point(430, 43));
            TxtPrecio.addFocusListener(new java.awt.event.FocusAdapter() {

                public void focusLost(java.awt.event.FocusEvent e) {
                    if (TxtPrecio.getText().equals("0") == true || TxtPrecio.getText().length() == 0) {
                        TxtPrecio.setText("0.00");
                    }
                    DecimalFormat redondear = new DecimalFormat("0.00");
                    TxtPrecio.setText(redondear.format(Double.parseDouble(TxtPrecio.getText())).replace(",", "."));
                    //TxtPrecio.setText(TxtPrecio.getText().replace(",","."));
                }

                public void focusGained(java.awt.event.FocusEvent e) {
                    TxtPrecio.select(0, TxtPrecio.getText().length());
                }
            });
            TxtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {

                public void keyTyped(java.awt.event.KeyEvent e) {
                    boolean punto;
                    char caracter;
                    caracter = e.getKeyChar();
                    if (TxtPrecio.getText().length() < 10) {
                        if ((caracter + "").matches("[0-9.]")) {
                            if (caracter == '.') {
                                punto = false;
                                for (int x = 0; x < TxtPrecio.getText().length(); x++) {
                                    if (TxtPrecio.getText().substring(x, x + 1).equals(".")) {
                                        punto = true;
                                    }
                                }
                                if (punto == false) {
                                    e.setKeyChar(caracter);
                                } else {
                                    e.consume();
                                }
                            } else {
                                e.setKeyChar(caracter);
                            }
                        } else {
                            e.consume();
                        }
                    } else {
                        e.consume();
                    }
                }
            });
        }
        return TxtPrecio;
    }

    private void cargar() {
        TxtArticulo.setText(FrameFacturacion.articulo);
        TxtPrecio.setText(FrameFacturacion.precio);
        TxtCantidad.setText(FrameFacturacion.cantidad);
        TxtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                boolean punto;
                char caracter;
                caracter = (e.getKeyChar() + "").toUpperCase().charAt(0);
                if (TxtCantidad.getText().length() < 10) {
                    if ((caracter + "").matches("[0-9.]")) {
                        if (caracter == '.') {
                            punto = false;
                            for (int x = 0; x < TxtCantidad.getText().length(); x++) {
                                if (TxtCantidad.getText().substring(x, x + 1).equals(".")) {
                                    punto = true;
                                }
                            }
                            if (punto == false) {
                                e.setKeyChar(caracter);
                            } else {
                                e.consume();
                            }
                        } else {
                            e.setKeyChar(caracter);
                        }
                    } else {
                        e.consume();
                    }
                } else {
                    e.consume();
                }
            }
        });
    }
}
