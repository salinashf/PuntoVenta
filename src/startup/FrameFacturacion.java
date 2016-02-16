package startup;

import com.alee.laf.WebLookAndFeel;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ventanas.dialog.DialogClientes;
import ventanas.dialog.DialogAnular;
import ventanas.dialog.DialogLineaDetalle;
import ventanas.dialog.DialogListaClientes;
import ventanas.dialog.DialogListafacturas;
import ventanas.dialog.DialogListarticulos;
import utilidades.conexion.BaseConexion;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.table.*;
import java.awt.event.KeyEvent;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;



public class FrameFacturacion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JPanel Panel2 = null;
    private JButton BPrimero = null;
    private JButton BAnterior = null;
    private JButton BBuscar = null;
    private JButton BSiguiente = null;
    private JButton BUltimo = null;
    private JButton BClientes = null;
    private JButton BSalir = null;
    private JButton BNuevocli = null;
    private JButton BCalculadora = null;
    private JButton BQuitar = null;
    private JButton BInsertar = null;
    private JButton BNueva = null;
    private JButton BAnular = null;
    private JButton BGuardar = null;
    private JButton BCancelar = null;
    private JPanel Panel1 = null;
    private JLabel LblVenta = null;
    private JPanel Panel3 = null;
    private JPanel Panel4 = null;
    private JScrollPane jScrollPane = null;
    private JTable JTable = null;
    private JPanel Panel5 = null;
    private JPanel Panel7 = null;
    private JPanel Panel6 = null;
    private JLabel LblObserva = null;
    private JTextPane TxtObserva = null;
    private JLabel LblCliente = null;
    private JLabel LblDireccion = null;
    private JLabel LblCedula = null;
    private JLabel LblVendedor = null;
    private JTextField TxtCliente = null;
    private JTextField TxtDireccion = null;
    private JTextField TxtCedula = null;
    private JComboBox CboVendedor = null;
    private JLabel LblTelefono = null;
    private JTextField TxtTelefono = null;
    private JLabel LblForma = null;
    private JComboBox CboForma = null;
    private JLabel LblDescuento = null;
    private JTextField TxtDescuento = null;
    private JLabel LblNumero = null;
    private JLabel LblFecha = null;
    private JTextField TxtFecha = null;
    private JLabel LblPago = null;
    private JLabel LblSubtotal = null;
    private JLabel LblTotdes = null;
    private JLabel LblTotiva = null;
    private JLabel LblTotal = null;
    private JLabel LblSaldo = null;
    private JTextField TxtPago = null;
    private JTextField TxtSaldo = null;
    private JTextField TxtSubtotal = null;
    private JTextField TxtTotdes = null;
    private JTextField TxtTotiva = null;
    private JTextField TxtTotal = null;
    configura modelo = new configura();
    String clientefac = "";
    String vendedorfac = "";
    Connection conexion;
    Statement sentenciacli, sentenciaven, sentenciacab, sentenciadet, sentenciaart, sentenciaxreg, sentenciastock;
    ResultSet resultadocli, resultadoven, resultadocab, resultadodet, resultadoart, resultadoxreg, resultadostock;
    String elarticulo = "";
    boolean nuevafac = false;
    boolean coma = false;
    long xfacturas;
    DecimalFormat redondear = new DecimalFormat("0.00");
    Calendar fecha = Calendar.getInstance();
    String numerofac = "";
    public static String articulo = new String();
    public static String precio = new String();
    public static String cantidad = new String();
    public static String numero = new String();
    private JLabel LblCondicion = null;
    private JButton BModifica = null;

    /**
     * This method initializes Panel2
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPanel2() {
        if (Panel2 == null) {
            Panel2 = new JPanel();
            Panel2.setLayout(null);
            Panel2.setEnabled(false);
            Panel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            Panel2.setLocation(new Point(632, 8));
            Panel2.setSize(new Dimension(181, 45));
            Panel2.add(getBPrimero(), null);
            Panel2.add(getBAnterior(), null);
            Panel2.add(getBBuscar(), null);
            Panel2.add(getBSiguiente(), null);
            Panel2.add(getBUltimo(), null);
        }
        return Panel2;
    }

    /**
     * This method initializes BPrimero
     *
     * @return javax.swing.JButton
     */
    private JButton getBPrimero() {
        if (BPrimero == null) {
            BPrimero = new JButton();
            BPrimero.setBounds(new Rectangle(7, 7, 32, 32));
            BPrimero.setIcon(new ImageIcon("IMAGENES/PRIMERO.JPG"));
            BPrimero.setToolTipText("Primero");
            BPrimero.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        resultadocab.first();
                        cargarfactura();
                    } catch (Exception s) {
                    }
                }
            });
        }
        return BPrimero;
    }

    /**
     * This method initializes BAnterior
     *
     * @return javax.swing.JButton
     */
    private JButton getBAnterior() {
        if (BAnterior == null) {
            BAnterior = new JButton();
            BAnterior.setBounds(new Rectangle(41, 7, 32, 32));
            BAnterior.setIcon(new ImageIcon("IMAGENES/ANTERIOR.JPG"));
            BAnterior.setToolTipText("Anterior");
            BAnterior.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        if (resultadocab.isBeforeFirst() == false) {
                            resultadocab.previous();
                            if (resultadocab.isBeforeFirst() == true) {
                                resultadocab.first();
                            }
                            cargarfactura();
                        }
                    } catch (Exception s) {
                    }
                }
            });
        }
        return BAnterior;
    }

    /**
     * This method initializes BBuscar
     *
     * @return javax.swing.JButton
     */
    private JButton getBBuscar() {
        if (BBuscar == null) {
            BBuscar = new JButton();
            BBuscar.setBounds(new Rectangle(75, 7, 32, 32));
            BBuscar.setIcon(new ImageIcon("IMAGENES/BUSCAR.JPG"));
            BBuscar.setToolTipText("Buscar Factura");
            BBuscar.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    new DialogListafacturas().setVisible(true);
                    if (DialogListafacturas.seleccionafac == true) {
                        numerofac = DialogListafacturas.codigofac;
                        localizafactura();
                        cargarfactura();
                    }

                }
            });
        }
        return BBuscar;
    }

    /**
     * This method initializes BSiguiente
     *
     * @return javax.swing.JButton
     */
    private JButton getBSiguiente() {
        if (BSiguiente == null) {
            BSiguiente = new JButton();
            BSiguiente.setBounds(new Rectangle(109, 7, 32, 32));
            BSiguiente.setIcon(new ImageIcon("IMAGENES/SIGUIENTE.JPG"));
            BSiguiente.setToolTipText("Siguiente");
            BSiguiente.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        if (resultadocab.isAfterLast() == false) {
                            resultadocab.next();
                            if (resultadocab.isAfterLast() == true) {
                                resultadocab.last();
                            }
                            cargarfactura();
                        }
                    } catch (Exception s) {
                    }
                }
            });
        }
        return BSiguiente;
    }

    /**
     * This method initializes BUltimo
     *
     * @return javax.swing.JButton
     */
    private JButton getBUltimo() {
        if (BUltimo == null) {
            BUltimo = new JButton();
            BUltimo.setBounds(new Rectangle(143, 7, 32, 32));
            BUltimo.setIcon(new ImageIcon("IMAGENES/ULTIMO.JPG"));
            BUltimo.setToolTipText("Ultimo");
            BUltimo.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        resultadocab.last();
                        cargarfactura();
                    } catch (Exception s) {
                    }
                }
            });
        }
        return BUltimo;
    }

    /**
     * This method initializes Panel1
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPanel1() {
        if (Panel1 == null) {
            LblFecha = new JLabel();
            LblFecha.setText("Fecha de emisión:");
            LblFecha.setLocation(new Point(376, 12));
            LblFecha.setSize(new Dimension(107, 18));
            LblNumero = new JLabel();
            LblNumero.setBounds(new Rectangle(167, 10, 104, 24));
            LblNumero.setFont(new Font("Arial Narrow", Font.BOLD, 18));
            LblNumero.setHorizontalTextPosition(SwingConstants.LEFT);
            LblNumero.setHorizontalAlignment(SwingConstants.LEFT);
            LblNumero.setForeground(SystemColor.textHighlight);
            LblNumero.setText("0000000000");
            LblVenta = new JLabel();
            LblVenta.setBounds(new Rectangle(11, 12, 153, 21));
            LblVenta.setText("Factura de Venta Número:");
            Panel1 = new JPanel();
            Panel1.setLayout(null);
            Panel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            Panel1.setSize(new Dimension(618, 45));
            Panel1.setLocation(new Point(8, 8));
            Panel1.add(LblVenta, null);
            Panel1.add(LblNumero, null);
            Panel1.add(LblFecha, null);
            Panel1.add(getTxtFecha(), null);
        }
        return Panel1;
    }

    /**
     * This method initializes Panel3
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPanel3() {
        if (Panel3 == null) {
            LblDescuento = new JLabel();
            LblDescuento.setText("Descuento:    %");
            LblDescuento.setLocation(new Point(523, 96));
            LblDescuento.setSize(new Dimension(90, 18));
            LblForma = new JLabel();
            LblForma.setText("Forma de Pago:");
            LblForma.setSize(new Dimension(92, 17));
            LblForma.setLocation(new Point(523, 69));
            LblTelefono = new JLabel();
            LblTelefono.setText("Teléfono:");
            LblTelefono.setLocation(new Point(303, 69));
            LblTelefono.setSize(new Dimension(68, 17));
            LblVendedor = new JLabel();
            LblVendedor.setText("Vendedor:");
            LblVendedor.setLocation(new Point(11, 96));
            LblVendedor.setSize(new Dimension(81, 18));
            LblCedula = new JLabel();
            LblCedula.setText("Cédula/RUC:");
            LblCedula.setLocation(new Point(11, 69));
            LblCedula.setSize(new Dimension(81, 17));
            LblDireccion = new JLabel();
            LblDireccion.setText("Dirección:");
            LblDireccion.setLocation(new Point(11, 38));
            LblDireccion.setSize(new Dimension(81, 22));
            LblCliente = new JLabel();
            LblCliente.setText("Cliente:");
            LblCliente.setLocation(new Point(11, 9));
            LblCliente.setSize(new Dimension(82, 20));
            Panel3 = new JPanel();
            Panel3.setLayout(null);
            Panel3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            Panel3.setLocation(new Point(8, 59));
            Panel3.setSize(new Dimension(726, 125));

            Panel3.add(LblCliente, null);
            Panel3.add(LblDireccion, null);
            Panel3.add(LblCedula, null);
            Panel3.add(LblVendedor, null);
            Panel3.add(getTxtCliente(), null);
            Panel3.add(getTxtDireccion(), null);
            Panel3.add(getTxtCedula(), null);
            Panel3.add(getCboVendedor(), null);
            Panel3.add(LblTelefono, null);
            Panel3.add(getTxtTelefono(), null);
            Panel3.add(LblForma, null);
            Panel3.add(getCboForma(), null);
            Panel3.add(LblDescuento, null);
            Panel3.add(getTxtDescuento(), null);
            Panel3.add(getBClientes(), null);
        }
        return Panel3;
    }

    /**
     * This method initializes Panel4
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPanel4() {
        if (Panel4 == null) {
            Panel4 = new JPanel();
            Panel4.setLayout(null);
            Panel4.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            Panel4.setLocation(new Point(741, 59));
            Panel4.setSize(new Dimension(72, 430));
            Panel4.add(getBSalir(), null);

            Panel4.add(getBNuevocli(), null);
            Panel4.add(getBCalculadora(), null);
            Panel4.add(getBQuitar(), null);
            Panel4.add(getBInsertar(), null);

            Panel4.add(getBModifica(), null);

        }
        return Panel4;
    }

    /**
     * This method initializes BSalir
     *
     * @return javax.swing.JButton
     */
    private JButton getBSalir() {
        if (BSalir == null) {
            BSalir = new JButton();
            BSalir.setPreferredSize(new Dimension(64, 92));
            BSalir.setToolTipText("Salir de Venta de Productos");
            BSalir.setActionCommand("");
            BSalir.setHorizontalAlignment(SwingConstants.CENTER);
            BSalir.setHorizontalTextPosition(SwingConstants.CENTER);
            BSalir.setIcon(new ImageIcon("IMAGENES/SALIR2.JPG"));
            BSalir.setText("Salir");
            BSalir.setVerticalAlignment(SwingConstants.CENTER);
            BSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
            BSalir.setLocation(new Point(9, 351));
            BSalir.setSize(new Dimension(54, 70));
            BSalir.setMnemonic(KeyEvent.VK_S);
            BSalir.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
            BSalir.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea salir del formulario de Ventas?", "Salir del Formulario", 0, 3);
                    if (respuesta == 0) {
                        dispose();
                        System.exit(0);
                    }


                }
            });
        }
        return BSalir;
    }

    /**
     * This method initializes jScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setBounds(new Rectangle(9, 192, 727, 166));
            jScrollPane.setViewportView(getJTable());
        }
        return jScrollPane;
    }

    /**
     * This method initializes JTable
     *
     * @return javax.swing.JTable
     */
    private JTable getJTable() {
        if (JTable == null) {
            JTable = new JTable(modelo);

        }
        return JTable;
    }

    /**
     * This method initializes Panel5
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPanel5() {
        if (Panel5 == null) {
            LblTotal = new JLabel();
            LblTotal.setFont(new Font("Arial Narrow", Font.BOLD, 16));
            LblTotal.setSize(new Dimension(51, 18));
            LblTotal.setLocation(new Point(8, 94));
            LblTotal.setForeground(new Color(47, 141, 235));
            LblTotal.setText("TOTAL:");
            LblTotiva = new JLabel();
            LblTotiva.setText("Total IVA:");
            LblTotiva.setSize(new Dimension(63, 20));
            LblTotiva.setLocation(new Point(8, 64));
            LblTotdes = new JLabel();
            LblTotdes.setText("Total Dcto:");
            LblTotdes.setLocation(new Point(8, 35));
            LblTotdes.setSize(new Dimension(63, 20));
            LblSubtotal = new JLabel();
            LblSubtotal.setText("Subtotal:");
            LblSubtotal.setSize(new Dimension(55, 18));
            LblSubtotal.setLocation(new Point(8, 10));
            Panel5 = new JPanel();
            Panel5.setLayout(null);
            Panel5.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            Panel5.setLocation(new Point(560, 365));
            Panel5.setSize(new Dimension(174, 124));
            Panel5.add(LblSubtotal, null);
            Panel5.add(LblTotdes, null);
            Panel5.add(LblTotiva, null);
            Panel5.add(LblTotal, null);
            Panel5.add(getTxtSubtotal(), null);
            Panel5.add(getTxtTotdes(), null);
            Panel5.add(getTxtTotiva(), null);
            Panel5.add(getTxtTotal(), null);
        }
        return Panel5;
    }

    /**
     * This method initializes Panel7
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPanel7() {
        if (Panel7 == null) {
            Panel7 = new JPanel();
            Panel7.setLayout(null);
            Panel7.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            Panel7.setSize(new Dimension(354, 81));
            Panel7.setLocation(new Point(8, 408));
            Panel7.add(getBNueva(), null);
            Panel7.add(getBAnular(), null);
            Panel7.add(getBGuardar(), null);
            Panel7.add(getBCancelar(), null);
        }
        return Panel7;
    }

    /**
     * This method initializes Panel6
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPanel6() {
        if (Panel6 == null) {
            LblCondicion = new JLabel();
            LblCondicion.setText("FACTURA PROCESADA");
            LblCondicion.setLocation(new Point(8, 61));
            LblCondicion.setFont(new Font("Dialog", Font.BOLD, 12));
            LblCondicion.setHorizontalAlignment(SwingConstants.CENTER);
            LblCondicion.setHorizontalTextPosition(SwingConstants.CENTER);
            LblCondicion.setForeground(new Color(51, 51, 51));
            LblCondicion.setSize(new Dimension(164, 16));
            LblSaldo = new JLabel();
            LblSaldo.setText("Saldo:");
            LblSaldo.setLocation(new Point(8, 35));
            LblSaldo.setSize(new Dimension(70, 16));
            LblPago = new JLabel();
            LblPago.setText("Pago inicial:");
            LblPago.setLocation(new Point(8, 9));
            LblPago.setSize(new Dimension(71, 15));
            Panel6 = new JPanel();
            Panel6.setLayout(null);
            Panel6.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            Panel6.setLocation(new Point(371, 408));
            Panel6.setSize(new Dimension(181, 81));
            Panel6.add(LblPago, null);
            Panel6.add(LblSaldo, null);
            Panel6.add(getTxtPago(), null);
            Panel6.add(getTxtSaldo(), null);
            Panel6.add(LblCondicion, null);
        }
        return Panel6;
    }

    /**
     * This method initializes TxtObserva
     *
     * @return javax.swing.JTextPane
     */
    private JTextPane getTxtObserva() {
        if (TxtObserva == null) {
            TxtObserva = new JTextPane();
            TxtObserva.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            TxtObserva.setSize(new Dimension(442, 36));
            TxtObserva.setEnabled(false);
            TxtObserva.setDisabledTextColor(Color.darkGray);
            TxtObserva.setLocation(new Point(110, 365));
        }
        return TxtObserva;
    }

    /**
     * This method initializes BNuevocli
     *
     * @return javax.swing.JButton
     */
    private JButton getBNuevocli() {
        if (BNuevocli == null) {
            BNuevocli = new JButton();
            BNuevocli.setText("");
            BNuevocli.setHorizontalTextPosition(SwingConstants.CENTER);
            BNuevocli.setVerticalAlignment(SwingConstants.CENTER);
            BNuevocli.setVerticalTextPosition(SwingConstants.BOTTOM);
            BNuevocli.setFont(new Font("Arial Narrow", Font.PLAIN, 8));
            BNuevocli.setActionCommand("Cliente");
            BNuevocli.setHorizontalAlignment(SwingConstants.CENTER);
            BNuevocli.setLocation(new Point(9, 9));
            BNuevocli.setSize(new Dimension(54, 54));
            BNuevocli.setToolTipText("Ingresar nuevo Cliente");
            BNuevocli.setIcon(new ImageIcon("IMAGENES/GENTE.JPG"));
            BNuevocli.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {

                    new DialogClientes().setVisible(true);
                }
            });
        }
        return BNuevocli;
    }

    /**
     * This method initializes BCalculadora
     *
     * @return javax.swing.JButton
     */
    private JButton getBCalculadora() {
        if (BCalculadora == null) {
            BCalculadora = new JButton();
            BCalculadora.setLocation(new Point(9, 71));
            BCalculadora.setIcon(new ImageIcon("IMAGENES/CALCULADORA.JPG"));
            BCalculadora.setToolTipText("Calculadora");
            BCalculadora.setSize(new Dimension(54, 54));
            BCalculadora.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        Process programa = Runtime.getRuntime().exec("CALC.EXE");
                    } catch (Exception ex) {
                    }
                }
            });
        }
        return BCalculadora;
    }

    /**
     * This method initializes BQuitar
     *
     * @return javax.swing.JButton
     */
    private JButton getBQuitar() {
        if (BQuitar == null) {
            BQuitar = new JButton();
            BQuitar.setIcon(new ImageIcon("IMAGENES/CANCELAR2.JPG"));
            BQuitar.setSize(new Dimension(54, 54));
            BQuitar.setToolTipText("Quitar producto de factura");
            BQuitar.setEnabled(false);
            BQuitar.setLocation(new Point(9, 198));
            BQuitar.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (JTable.isCellSelected(JTable.getSelectedRow(), 0) == false) {
                        JOptionPane.showMessageDialog(null, "¡Seleccione el Artículo que desea quitar de la Factura!", "Error de selección", 2);
                    } else {
                        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea quitar de la Factura el Artículo " + JTable.getValueAt(JTable.getSelectedRow(), 1).toString() + "?", "Quitar artículo", 0, 3);
                        if (respuesta == 0) {
                            modelo.removeRow(JTable.getSelectedRow());
                            calculos();
                        }
                    }
                }
            });
        }
        return BQuitar;
    }

    /**
     * This method initializes BInsertar
     *
     * @return javax.swing.JButton
     */
    private JButton getBInsertar() {
        if (BInsertar == null) {
            BInsertar = new JButton();
            BInsertar.setLocation(new Point(9, 135));
            BInsertar.setToolTipText("Insertar producto en factura");
            BInsertar.setIcon(new ImageIcon("IMAGENES/INSERTAR.JPG"));
            BInsertar.setEnabled(false);
            BInsertar.setSize(new Dimension(54, 54));
            BInsertar.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    new DialogListarticulos().setVisible(true);
                    if (DialogListarticulos.seleccionaart == true) {
                        elarticulo = DialogListarticulos.codigoart;
                        ingresarticulo();
                    }
                }
            });
        }
        return BInsertar;
    }

    /**
     * This method initializes BNueva
     *
     * @return javax.swing.JButton
     */
    private JButton getBNueva() {
        if (BNueva == null) {
            BNueva = new JButton();
            BNueva.setLocation(new Point(14, 14));
            BNueva.setIcon(new ImageIcon("IMAGENES/NUEVO.JPG"));
            BNueva.setToolTipText("Nueva Factura");
            BNueva.setSize(new Dimension(54, 54));
            BNueva.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    nuevafac = true;
                    limpiardetalle();
                    limpiar();
                    generanumero();
                    cargarfecha();
                    LblCondicion.setText("FACTURA EN EDICION...");
                    LblCondicion.setForeground(new Color(51, 51, 51));
                    CboForma.setEnabled(true);
                    CboVendedor.setEnabled(true);
                    BClientes.setEnabled(true);
                    BInsertar.setEnabled(true);
                    BQuitar.setEnabled(true);
                    BModifica.setEnabled(true);
                    BGuardar.setEnabled(true);
                    BCancelar.setEnabled(true);
                    TxtDescuento.setEnabled(true);
                    TxtFecha.setEnabled(true);
                    TxtObserva.setEnabled(true);
                    BAnterior.setEnabled(false);
                    BAnular.setEnabled(false);
                    BBuscar.setEnabled(false);
                    BNueva.setEnabled(false);
                    BPrimero.setEnabled(false);
                    BSiguiente.setEnabled(false);
                    BUltimo.setEnabled(false);
                    TxtFecha.requestFocus();
                }
            });
        }
        return BNueva;
    }

    /**
     * This method initializes BAnular
     *
     * @return javax.swing.JButton
     */
    private JButton getBAnular() {
        if (BAnular == null) {
            BAnular = new JButton();
            BAnular.setLocation(new Point(82, 14));
            BAnular.setIcon(new ImageIcon("IMAGENES/ELIMINAR.JPG"));
            BAnular.setToolTipText("Anular Factura");
            BAnular.setSize(new Dimension(54, 54));
            BAnular.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    new DialogAnular().setVisible(true);
                    if (DialogAnular.anulafac == true) {
                        anulacion();
                    }
                }
            });
        }
        return BAnular;
    }

    /**
     * This method initializes BGuardar
     *
     * @return javax.swing.JButton
     */
    private JButton getBGuardar() {
        if (BGuardar == null) {
            BGuardar = new JButton();
            BGuardar.setSize(new Dimension(114, 54));
            BGuardar.setIcon(new ImageIcon("IMAGENES/GUARDARIMP.JPG"));
            BGuardar.setToolTipText("Guardar e Imprimir");
            BGuardar.setEnabled(false);
            BGuardar.setDisabledIcon(new ImageIcon("IMAGENES/GUARDARIMP2.JPG"));
            BGuardar.setLocation(new Point(159, 14));
            BGuardar.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (TxtCliente.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "¡Ingrese el Cliente al que se realiza la venta!", "Datos incompletos", 2);
                        BClientes.requestFocus();
                    } else if (CboVendedor.getSelectedIndex() == -1) {
                        JOptionPane.showMessageDialog(null, "¡Ingrese el Vendedor que realiza la venta!", "Datos incompletos", 2);
                        CboVendedor.requestFocus();
                    } else if (modelo.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(null, "¡No ha ingresado Artículos en esta venta!", "Datos incompletos", 2);
                        BInsertar.requestFocus();
                    } else {
                        guardar();
                    }
                }
            });
        }
        return BGuardar;
    }

    /**
     * This method initializes BCancelar
     *
     * @return javax.swing.JButton
     */
    private JButton getBCancelar() {
        if (BCancelar == null) {
            BCancelar = new JButton();
            BCancelar.setSize(new Dimension(54, 54));
            BCancelar.setIcon(new ImageIcon("IMAGENES/CANCELAR.JPG"));
            BCancelar.setToolTipText("Cancelar");
            BCancelar.setEnabled(false);
            BCancelar.setLocation(new Point(286, 14));
            BCancelar.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea cancelar los datos de esta Venta?", "Cancelar venta", 0, 3);
                        if (respuesta == 0) {
                            guacan();
                            nuevafac = false;
                            //  add
                            sentenciacab = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                            resultadocab = sentenciacab.executeQuery("SELECT *,(sub_gen-tot_des+tot_iva) as total,round((sub_gen-tot_des+tot_iva-pago_ini),2) as saldo FROM FACV_CAB ORDER BY NUM_FACV");

                            if (resultadocab.next() == true) {
                                xfacturas = 1;
                                resultadocab.last();
                                cargarfactura();
                            } else {
                                vacio();
                                limpiar();
                                limpiardetalle();
                                xfacturas = 0;

                            }
                        }
                    } catch (Exception c) {
                    }
                }
            });
        }
        return BCancelar;
    }

    /**
     * This method initializes TxtCliente
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtCliente() {
        if (TxtCliente == null) {
            TxtCliente = new JTextField();
            TxtCliente.setSize(new Dimension(528, 23));
            TxtCliente.setEnabled(false);
            TxtCliente.setLocation(new Point(100, 9));
            TxtCliente.setDisabledTextColor(Color.DARK_GRAY);
        }
        return TxtCliente;
    }

    /**
     * This method initializes TxtDireccion
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtDireccion() {
        if (TxtDireccion == null) {
            TxtDireccion = new JTextField();
            TxtDireccion.setSize(new Dimension(528, 23));
            TxtDireccion.setEnabled(false);
            TxtDireccion.setLocation(new Point(100, 38));
            TxtDireccion.setDisabledTextColor(Color.DARK_GRAY);
        }
        return TxtDireccion;
    }

    /**
     * This method initializes TxtCedula
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtCedula() {
        if (TxtCedula == null) {
            TxtCedula = new JTextField();
            TxtCedula.setLocation(new Point(100, 67));
            TxtCedula.setEnabled(false);
            TxtCedula.setSize(new Dimension(119, 23));
            TxtCedula.setDisabledTextColor(Color.DARK_GRAY);
        }
        return TxtCedula;
    }

    /**
     * This method initializes CboVendedor
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getCboVendedor() {
        if (CboVendedor == null) {
            CboVendedor = new JComboBox();
            CboVendedor.setSize(new Dimension(384, 22));
            CboVendedor.setEnabled(false);
            CboVendedor.setLocation(new Point(100, 96));
        }
        return CboVendedor;
    }

    /**
     * This method initializes TxtTelefono
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtTelefono() {
        if (TxtTelefono == null) {
            TxtTelefono = new JTextField();
            TxtTelefono.setLocation(new Point(379, 67));
            TxtTelefono.setEnabled(false);
            TxtTelefono.setSize(new Dimension(106, 23));
            TxtTelefono.setDisabledTextColor(Color.DARK_GRAY);
        }
        return TxtTelefono;
    }

    /**
     * This method initializes CboForma
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getCboForma() {
        if (CboForma == null) {
            CboForma = new JComboBox();
            CboForma.setLocation(new Point(627, 68));
            CboForma.setEnabled(false);
            CboForma.setEditable(false);
            CboForma.setSize(new Dimension(89, 22));
            CboForma.addItem("CONTADO");
            CboForma.addItem("CREDITO");
            CboForma.addItemListener(new java.awt.event.ItemListener() {

                @Override
                public void itemStateChanged(java.awt.event.ItemEvent e) {
                    if (CboForma.getSelectedIndex() == 0) {
                        TxtPago.setEnabled(false);
                        calculos();
                    } else if (CboForma.getSelectedIndex() == 1) {
                        TxtPago.setEnabled(true);
                        calculos();
                    }
                }
            });

        }
        return CboForma;
    }

    /**
     * This method initializes TxtDescuento
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtDescuento() {
        if (TxtDescuento == null) {
            TxtDescuento = new JTextField();
            TxtDescuento.setLocation(new Point(627, 95));
            TxtDescuento.setEditable(true);
            TxtDescuento.setEnabled(false);
            TxtDescuento.setText("0");
            TxtDescuento.setHorizontalAlignment(JTextField.RIGHT);
            TxtDescuento.setSize(new Dimension(70, 23));
            TxtDescuento.setDisabledTextColor(Color.DARK_GRAY);
            TxtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {

                @Override
                public void keyTyped(java.awt.event.KeyEvent e) {
                    char caracter;
                    caracter = (e.getKeyChar() + "").charAt(0);
                    if (TxtDescuento.getText().length() < 2) {
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
            TxtDescuento.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (nuevafac == true) {
                        if (TxtDescuento.getText().length() == 0) {
                            TxtDescuento.setText("0");
                        }
                        calculos();
                    }
                }
            });
            TxtDescuento.addFocusListener(new java.awt.event.FocusAdapter() {

                @Override
                public void focusGained(java.awt.event.FocusEvent e) {
                    TxtDescuento.select(0, TxtDescuento.getText().length());
                }

                @Override
                public void focusLost(java.awt.event.FocusEvent e) {
                    if (nuevafac == true) {
                        if (TxtDescuento.getText().length() == 0) {
                            TxtDescuento.setText("0");
                        }
                        calculos();
                    }
                }
            });
        }
        return TxtDescuento;
    }

    /**
     * This method initializes BClientes
     *
     * @return javax.swing.JButton
     */
    private JButton getBClientes() {
        if (BClientes == null) {
            BClientes = new JButton();
            BClientes.setBounds(new Rectangle(644, 14, 65, 40));
            BClientes.setToolTipText("Lista de Clientes");
            BClientes.setEnabled(false);
            BClientes.setIcon(new ImageIcon(this.getClass().getResource("/IMAGENES/GENTES.JPG")));
            BClientes.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    new DialogListaClientes().setVisible(true);
                    if (DialogListaClientes.seleccionacli == true) {
                        clientefac = DialogListaClientes.codigocli;
                        localizacliente();
                    }
                }
            });
        }
        return BClientes;
    }

    /**
     * This method initializes TxtFecha
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtFecha() {
        if (TxtFecha == null) {
            TxtFecha = new JTextField();
            TxtFecha.setBounds(new Rectangle(498, 11, 106, 23));
            TxtFecha.setEnabled(false);
            TxtFecha.setDisabledTextColor(Color.DARK_GRAY);
            TxtFecha.addFocusListener(new java.awt.event.FocusAdapter() {

                @Override
                public void focusLost(java.awt.event.FocusEvent e) {
                    if (FechaValida(TxtFecha.getText()) == false || TxtFecha.getText().length() > 10) {
                        JOptionPane.showMessageDialog(null, "¡Formato de fecha incorrecto! Use el formato DIA/MES/anio (25/05/2011)", "Fecha incorrecta", 2);
                        cargarfecha();
                        TxtFecha.requestFocus();
                    }
                }
            });
            TxtFecha.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (FechaValida(TxtFecha.getText()) == false || TxtFecha.getText().length() > 10) {
                        //JOptionPane.showMessageDialog(null,"¡Formato de fecha incorrecto! Use el formato DIA/MES/anio (25/05/2011)","Fecha incorrecta",2);
                        cargarfecha();
                        TxtFecha.requestFocus();
                    }
                }
            });
            TxtFecha.addKeyListener(new java.awt.event.KeyAdapter() {

                @Override
                public void keyTyped(java.awt.event.KeyEvent e) {
                    char caracter;
                    caracter = (e.getKeyChar() + "").charAt(0);
                    if ((caracter + "").matches("[0-9/]")) {
                        e.setKeyChar(caracter);
                    } else {
                        e.consume();
                    }
                }
            });
        }
        return TxtFecha;
    }

    /**
     * This method initializes TxtPago
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtPago() {
        if (TxtPago == null) {
            TxtPago = new JTextField();
            TxtPago.setLocation(new Point(86, 7));
            TxtPago.setEnabled(false);
            TxtPago.setText("0.00");
            TxtPago.setHorizontalAlignment(JTextField.RIGHT);
            TxtPago.setFont(new Font("Dialog", Font.BOLD, 12));
            TxtPago.setSize(new Dimension(87, 22));
            TxtPago.setDisabledTextColor(Color.DARK_GRAY);
            TxtPago.addKeyListener(new java.awt.event.KeyAdapter() {

                @Override
                public void keyTyped(java.awt.event.KeyEvent e) {
                    boolean punto;
                    char caracter;
                    caracter = (e.getKeyChar() + "").charAt(0);
                    if (TxtPago.getText().length() < 10) {
                        if ((caracter + "").matches("[0-9.]")) {
                            if (caracter == '.') {
                                punto = false;
                                for (int x = 0; x < TxtPago.getText().length(); x++) {
                                    if (TxtPago.getText().substring(x, x + 1).equals(".")) {
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
            TxtPago.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    TxtPago.setText(redondear.format(Double.parseDouble(TxtPago.getText())).replace(",", "."));
                    //TxtPago.setText(TxtPago.getText().replace(",","."));
                    //TxtPago.setText(cambiarsep(redondeo.format(Double.parseDouble(TxtPago.getText()))));
                    calculos();
                }
            });
            TxtPago.addFocusListener(new java.awt.event.FocusAdapter() {

                @Override
                public void focusLost(java.awt.event.FocusEvent e) {
                    TxtPago.setText(redondear.format(Double.parseDouble(TxtPago.getText())).replace(",", "."));
                    //TxtPago.setText(TxtPago.getText().replace(",","."));
                    //TxtPago.setText(cambiarsep(redondeo.format(Double.parseDouble(TxtPago.getText()))));
                    calculos();
                }
            });
        }
        return TxtPago;
    }

    /**
     * This method initializes TxtSaldo
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtSaldo() {
        if (TxtSaldo == null) {
            TxtSaldo = new JTextField();
            TxtSaldo.setLocation(new Point(86, 34));
            TxtSaldo.setEnabled(false);
            TxtSaldo.setFont(new Font("Dialog", Font.BOLD, 12));
            TxtSaldo.setHorizontalAlignment(JTextField.RIGHT);
            TxtSaldo.setText("0.00");
            TxtSaldo.setSize(new Dimension(87, 22));
            TxtSaldo.setDisabledTextColor(Color.DARK_GRAY);
        }
        return TxtSaldo;
    }

    /**
     * This method initializes TxtSubtotal
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtSubtotal() {
        if (TxtSubtotal == null) {
            TxtSubtotal = new JTextField();
            TxtSubtotal.setLocation(new Point(80, 8));
            TxtSubtotal.setText("0.00");
            TxtSubtotal.setHorizontalAlignment(JTextField.RIGHT);
            TxtSubtotal.setFont(new Font("Arial Narrow", Font.BOLD, 16));
            TxtSubtotal.setEnabled(false);
            TxtSubtotal.setSize(new Dimension(87, 22));
            TxtSubtotal.setDisabledTextColor(Color.DARK_GRAY);
        }
        return TxtSubtotal;
    }

    /**
     * This method initializes TxtTotdes
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtTotdes() {
        if (TxtTotdes == null) {
            TxtTotdes = new JTextField();
            TxtTotdes.setLocation(new Point(80, 35));
            TxtTotdes.setFont(new Font("Arial Narrow", Font.BOLD, 16));
            TxtTotdes.setText("0.00");
            TxtTotdes.setHorizontalAlignment(JTextField.RIGHT);
            TxtTotdes.setEnabled(false);
            TxtTotdes.setSize(new Dimension(87, 22));
            TxtTotdes.setDisabledTextColor(Color.DARK_GRAY);
        }
        return TxtTotdes;
    }

    /**
     * This method initializes TxtTotiva
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtTotiva() {
        if (TxtTotiva == null) {
            TxtTotiva = new JTextField();
            TxtTotiva.setLocation(new Point(80, 62));
            TxtTotiva.setHorizontalAlignment(JTextField.RIGHT);
            TxtTotiva.setText("0.00");
            TxtTotiva.setFont(new Font("Arial Narrow", Font.BOLD, 16));
            TxtTotiva.setEnabled(false);
            TxtTotiva.setSize(new Dimension(87, 22));
            TxtTotiva.setDisabledTextColor(Color.DARK_GRAY);
        }
        return TxtTotiva;
    }

    /**
     * This method initializes TxtTotal
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtTotal() {
        if (TxtTotal == null) {
            TxtTotal = new JTextField();
            TxtTotal.setHorizontalAlignment(JTextField.RIGHT);
            TxtTotal.setFont(new Font("Dialog", Font.BOLD, 18));
            TxtTotal.setEnabled(false);
            TxtTotal.setText("0.00");
            TxtTotal.setLocation(new Point(58, 91));
            TxtTotal.setSize(new Dimension(109, 25));
            TxtTotal.setDisabledTextColor(new Color(50, 145, 240));
        }
        return TxtTotal;
    }

    /**
     * This method initializes BModifica
     *
     * @return javax.swing.JButton
     */
    private JButton getBModifica() {
        if (BModifica == null) {
            BModifica = new JButton();
            BModifica.setSize(new Dimension(54, 54));
            BModifica.setIcon(new ImageIcon(this.getClass().getResource("/IMAGENES/MODIFICAR.JPG")));
            BModifica.setToolTipText("Modificar");
            BModifica.setEnabled(false);
            BModifica.setLocation(new Point(9, 261));
            BModifica.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        if (JTable.isCellSelected(JTable.getSelectedRow(), 0) == false) {
                            JOptionPane.showMessageDialog(null, "¡Seleccione el Artículo del que desea modificar los datos!", "Error de selección", 2);
                        } else {
                            elarticulo = String.valueOf(JTable.getValueAt(JTable.getSelectedRow(), 0));
                            numero = LblNumero.getText();
                            articulo = String.valueOf(JTable.getValueAt(JTable.getSelectedRow(), 1));
                            precio = String.valueOf(JTable.getValueAt(JTable.getSelectedRow(), 2));
                            cantidad = String.valueOf(JTable.getValueAt(JTable.getSelectedRow(), 3));
                            new DialogLineaDetalle().setVisible(true);
                            if (DialogLineaDetalle.ingresadet == true) {
                                long xcant = Long.parseLong(DialogLineaDetalle.cantidad);
                                actualizarticulos();
                                resultadoart.first();
                                // add
                                sentenciaxreg = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                resultadoxreg = sentenciaxreg.executeQuery("select count(id_art) as cuantos from articulos");

                                resultadoxreg.last();
                                for (int xreg = 1; xreg <= resultadoxreg.getInt("cuantos"); xreg++) {
                                    if (resultadoart.getString("id_art").equals(elarticulo) == true) {
                                        break;
                                    }
                                    resultadoart.next();
                                }
                                if (resultadoart.getLong("stock") < Long.parseLong(DialogLineaDetalle.cantidad)) {
                                    JOptionPane.showMessageDialog(null, "¡La cantidad ingresada excede al Stock actual del Artículo! Se ingresará la cantidad existente de " + resultadoart.getInt("stock") + ".", "Stock insuficiente", 2);
                                    xcant = resultadoart.getLong("stock");
                                }
                                Object[] fila = new Object[5];
                                fila[0] = String.valueOf(JTable.getValueAt(JTable.getSelectedRow(), 0));

                                fila[1] = articulo;
                                fila[2] = DialogLineaDetalle.precio;
                                fila[3] = xcant;
                                fila[4] = redondear.format(Double.parseDouble(DialogLineaDetalle.precio) * xcant).replace(",", ".");
                                modelo.removeRow(JTable.getSelectedRow());
                                modelo.addRow(fila);
                                calculos();
                            }
                        }
                    } catch (Exception d) {
                    }
                }
            });
        }
        return BModifica;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            // Set look and feel
            SwingUtilities.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    // set SeaGlass laf if available
                    try {
                        /*
                        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                        }
                        }*/
                        //UIManager.setLookAndFeel(new WindowsLookAndFeel());

                        WebLookAndFeel.install();

                        FrameFacturacion thisClass = new FrameFacturacion();
                        thisClass.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        thisClass.setVisible(true);
                    } catch (Exception e) {
                    }
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(FrameFacturacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(FrameFacturacion.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    /**
     * This is the default constructor
     */
    public FrameFacturacion() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setResizable(false);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("IMAGENES/VENTAS01.png"));
        this.setSize(827, 528);
        this.setContentPane(getJContentPane());
        this.setTitle("P U N T O  D E  V E N T A S ");
        this.setLocationRelativeTo(null);
        modelo.addColumn("CODIGO");
        modelo.addColumn("D E S C R I P C I O N");
        modelo.addColumn("P.V.P.");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("SUBTOTAL");
        TableColumn columna1 = JTable.getColumn("CODIGO");
        columna1.setPreferredWidth(70);
        columna1.setMinWidth(70);
        columna1.setMaxWidth(70);
        TableColumn columna2 = JTable.getColumn("D E S C R I P C I O N");
        columna2.setPreferredWidth(359);
        columna2.setMinWidth(359);
        columna2.setMaxWidth(359);
        TableColumn columna3 = JTable.getColumn("P.V.P.");
        columna3.setPreferredWidth(100);
        columna3.setMinWidth(100);
        columna3.setMaxWidth(100);
        TableColumn columna4 = JTable.getColumn("CANTIDAD");
        columna4.setPreferredWidth(80);
        columna4.setMinWidth(80);
        columna4.setMaxWidth(80);
        TableColumn columna5 = JTable.getColumn("SUBTOTAL");
        columna5.setPreferredWidth(100);
        columna5.setMinWidth(100);
        columna5.setMaxWidth(100);
        JTable.isCellEditable(JTable.getSelectedRow(), JTable.getSelectedColumn());

        conectar();
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            LblObserva = new JLabel();
            LblObserva.setText("Observaciones:");
            LblObserva.setLocation(new Point(9, 365));
            LblObserva.setSize(new Dimension(98, 21));
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getPanel2(), null);
            jContentPane.add(getPanel1(), null);
            jContentPane.add(getPanel3(), null);
            jContentPane.add(getPanel4(), null);
            jContentPane.add(getJScrollPane(), null);
            jContentPane.add(getPanel5(), null);
            jContentPane.add(getPanel7(), null);
            jContentPane.add(getPanel6(), null);
            jContentPane.add(LblObserva, null);
            jContentPane.add(getTxtObserva(), null);
        }
        return jContentPane;
    }

    public class configura extends DefaultTableModel {

        private static final long serialVersionUID = 1L;

        @Override
        public boolean isCellEditable(int row, int column) {
            if (column == 0) {
                return false;
            }
            if (column == 1) {
                return false;
            }
            if (column == 2) {
                return false;
            }
            if (column == 3) {
                return false;
            }
            if (column == 4) {
                return false;
            }
            return true;
        }

        @Override
        public Class getColumnClass(int columna) {
            if (columna == 2) {
                return Integer.class;
            }
            if (columna == 3) {
                return Integer.class;
            }
            if (columna == 4) {
                return Integer.class;
            }
            return Object.class;
        }
    }

    public void conectar() {
        try {

            conexion = BaseConexion.getConectar();
            sentenciacli = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultadocli = sentenciacli.executeQuery("select * from clientes order by id_cli");
            sentenciaven = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultadoven = sentenciaven.executeQuery("select id_ven,  concat(ape_ven,' ',nom_ven)as elvendedor from vendedores order by elvendedor");
            cargarvendedores();
            sentenciaart = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            actualizarticulos();
            sentenciacab = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultadocab = sentenciacab.executeQuery("SELECT *,(sub_gen-tot_des+tot_iva) as total,round((sub_gen-tot_des+tot_iva-pago_ini),2) as saldo FROM FACV_CAB ORDER BY NUM_FACV");
            if (resultadocab.next() == true) {
                xfacturas = 1;
                resultadocab.last();
                cargarfactura();
            } else {
                xfacturas = 0;
                vacio();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡Ha ocurrido un error al intentar acceder a los datos!", "Error de acceso", 2);
            return;
        }
    }

    public void localizacliente() {
        try {
            sentenciaxreg = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultadoxreg = sentenciaxreg.executeQuery("select count(id_cli) as cuantos from clientes");

            resultadoxreg.last();
            resultadocli.first();
            for (int xreg = 1; xreg <= resultadoxreg.getInt("cuantos"); xreg++) {
                if (resultadocli.getString("id_cli").equals(clientefac) == true) {
                    break;
                }
                resultadocli.next();
            }
            TxtCliente.setText(resultadocli.getString("ape_cli").trim() + " " + resultadocli.getString("nom_cli").trim());
            TxtDireccion.setText(resultadocli.getString("dir_cli"));
            TxtCedula.setText(resultadocli.getString("cer_cli"));
            if (resultadocli.getString("tel_cli").trim().length() == 0) {
                TxtTelefono.setText(resultadocli.getString("cel_cli"));
            } else {
                TxtTelefono.setText(resultadocli.getString("tel_cli"));
            }
        } catch (Exception c) {
        }
    }

    public void cargarvendedores() {
        try {
            sentenciaxreg = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultadoxreg = sentenciaxreg.executeQuery("select count(id_ven) as cuantos from vendedores");

            resultadoxreg.last();
            resultadoven.first();
            for (int xreg = 1; xreg <= resultadoxreg.getInt("cuantos"); xreg++) {
                CboVendedor.addItem(resultadoven.getObject("elvendedor"));
                resultadoven.next();
            }
            resultadoven.first();
            vendedorfac = resultadoven.getString("id_ven");
        } catch (Exception s) {
            JOptionPane.showMessageDialog(this, s.getMessage());
        }
    }

    public void actualizarticulos() {
        try {
            resultadoart = sentenciaart.executeQuery("SELECT ID_ART,DES_ART,STOCK,PVP_ART FROM ARTICULOS ORDER BY ID_ART");

            resultadoart.last();
        } catch (Exception s) {
            JOptionPane.showMessageDialog(null, "Noooooooo");
        }
    }

    public void ingresarticulo() {
        try {
            int numart = modelo.getRowCount();
            boolean existe = false;
            if (numart > 0) {
                for (int x = 0; x < numart; x++) {
                    if (JTable.getValueAt(x, 0).toString().equals(elarticulo) == true) {
                        existe = true;
                    }
                }
            }
            if (existe == false) {
                actualizarticulos();
                sentenciaxreg = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                resultadoxreg = sentenciaxreg.executeQuery("select count(id_art) as cuantos from articulos");

                resultadoxreg.last();
                resultadoart.first();
                for (int xreg = 1; xreg <= resultadoxreg.getInt("cuantos"); xreg++) {
                    if (resultadoart.getString("id_art").equals(elarticulo) == true) {
                        break;
                    }
                    resultadoart.next();
                }
                if (resultadoart.getLong("stock") > 0) {
                    numero = LblNumero.getText();
                    articulo = resultadoart.getString("des_art");
                    precio = resultadoart.getString("pvp_art");
                    cantidad = "1";
                    new DialogLineaDetalle().setVisible(true);
                    if (DialogLineaDetalle.ingresadet == true) {
                        long xcant = Long.parseLong(DialogLineaDetalle.cantidad);
                        if (resultadoart.getLong("stock") < xcant) {
                            JOptionPane.showMessageDialog(null, "¡La cantidad ingresada excede al Stock actual del Artículo! Se ingresará la cantidad existente de " + resultadoart.getInt("stock") + ".", "Stock insuficiente", 2);
                            xcant = resultadoart.getLong("stock");
                        }
                        Object[] fila = new Object[5];
                        fila[0] = resultadoart.getObject("id_art");
                        fila[1] = resultadoart.getObject("des_art");
                        fila[2] = DialogLineaDetalle.precio;
                        fila[3] = xcant;
                        fila[4] = redondear.format(Double.parseDouble(DialogLineaDetalle.precio) * xcant).replace(",", ".");
                        modelo.addRow(fila);
                        calculos();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "¡Actualmente NO existen existencias del Artículo " + resultadoart.getString("des_art") + "!", "Stock agotado", 0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡El Artículo " + resultadoart.getString("des_art") + " ya ha sido ingresado en esta Factura!", "Error de ingreso", 2);
            }
        } catch (Exception s) {
            JOptionPane.showMessageDialog(null, "Error al cargar Articulo");
        }
    }

    public void calculos() {
        int xreg = modelo.getRowCount();
        double total = 0.00;
        double subtotal = 0.00;
        double totaldes = 0.00;
        double totaliva = 0.00;
        if (xreg > 0) {
            for (int x = 1; x <= xreg; x++) {
                subtotal = subtotal + Double.parseDouble(JTable.getValueAt(x - 1, 4).toString());
            }
        }
        totaldes = ((subtotal * Integer.parseInt(TxtDescuento.getText())) / 100);
        totaliva = ((subtotal - totaldes) * (0.12));
        total = (subtotal - totaldes + totaliva);
        TxtSubtotal.setText(redondear.format(subtotal).replace(",", "."));
        TxtTotdes.setText(redondear.format(totaldes).replace(",", "."));
        TxtTotiva.setText(redondear.format(totaliva).replace(",", "."));
        TxtTotal.setText(redondear.format(total).replace(",", "."));
        if (CboForma.getSelectedIndex() == 0) {
            TxtPago.setText(TxtTotal.getText());
            TxtSaldo.setText("0.00");
        } else if (CboForma.getSelectedIndex() == 1) {
            if (Double.parseDouble(TxtPago.getText()) > total) {
                TxtPago.setText(redondear.format(total).replace(",", "."));
            }
            double pago = Double.parseDouble(TxtPago.getText());
            double saldo = total - pago;
            TxtSaldo.setText(redondear.format(saldo).replace(",", "."));
        }
    }

//public String cambiarsep (String numero){
//		String letra=new String("");
//		String numcambiado=new String("");
//		for (int x=1;x<=numero.length();x++){
//			letra=numero.substring(x-1,x);
//           if (letra.equals(",")==true || letra.equals(".")==true)
//            {  letra="";}
//            numcambiado=numcambiado+letra;
//        }
//		numero=numcambiado.substring(0,numcambiado.length()-2)+"."+numcambiado.substring(numcambiado.length()-2);
//		return numero;
//	}
    public void generanumero() {

        try {

            if (xfacturas == 0) {
                LblNumero.setText("0000000001");
            } else {
                resultadocab.last();
                String numerocad = resultadocab.getString("num_facv");
                String nuevonum = String.valueOf(Long.valueOf(numerocad) + 1);
                String ceros = "";
                for (int x = 1; x <= 10 - nuevonum.length(); x++) {
                    ceros = ceros + "0";
                }
                LblNumero.setText(ceros + String.valueOf(nuevonum));
            }
        } catch (Exception k) {
        }
    }

    public void guacan() {
        CboForma.setEnabled(false);
        CboVendedor.setEnabled(false);
        BClientes.setEnabled(false);
        BInsertar.setEnabled(false);
        BQuitar.setEnabled(false);
        BModifica.setEnabled(false);
        BGuardar.setEnabled(false);
        BCancelar.setEnabled(false);
        TxtDescuento.setEnabled(false);
        TxtFecha.setEnabled(false);
        TxtObserva.setEnabled(false);
        BAnterior.setEnabled(true);
        BAnular.setEnabled(true);
        BBuscar.setEnabled(true);
        BNueva.setEnabled(true);
        BNuevocli.setEnabled(true);
        BPrimero.setEnabled(true);
        BSiguiente.setEnabled(true);
        BUltimo.setEnabled(true);
        TxtSaldo.setEnabled(false);
    }

    public void cargarfactura() {
        try {
            clientefac = resultadocab.getString("id_cli");
            localizacliente();
            vendedorfac = resultadocab.getString("id_ven");
            localizavendedor();
            LblNumero.setText(resultadocab.getString("NUM_FACV"));
            CboForma.setSelectedItem(resultadocab.getString("for_pag"));
            TxtDescuento.setText(resultadocab.getString("descu"));
            TxtObserva.setText(resultadocab.getString("observac"));
            TxtPago.setText(resultadocab.getString("pago_ini"));
            TxtSubtotal.setText(resultadocab.getString("sub_gen"));
            TxtTotdes.setText(resultadocab.getString("tot_des"));
            TxtTotiva.setText(resultadocab.getString("tot_iva"));
            TxtTotal.setText(resultadocab.getString("total"));
            TxtSaldo.setText(resultadocab.getString("saldo"));
            String lafecha = "";
            lafecha = resultadocab.getString("fecha").substring(8) + "/" + resultadocab.getString("fecha").substring(5, 7) + "/" + resultadocab.getString("fecha").substring(0, 4);
            TxtFecha.setText(lafecha);
            if (resultadocab.getInt("anulada") == 0) {
                BAnular.setEnabled(true);
                LblCondicion.setText("FACTURA PROCESADA");
                LblCondicion.setForeground(new Color(47, 141, 235));
            } else {
                BAnular.setEnabled(false);
                LblCondicion.setText("FACTURA ANULADA");
                LblCondicion.setForeground(new Color(168, 20, 20));
            }
            cargardetalle();
        } catch (Exception s) {
            JOptionPane.showMessageDialog(null, "error carga");
        }
    }

    public void localizavendedor() {
        try {
            sentenciaxreg = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultadoxreg = sentenciaxreg.executeQuery("select count(id_ven) as cuantos from vendedores");

            resultadoxreg.last();
            resultadoven.first();
            for (int xreg = 1; xreg <= resultadoxreg.getInt("cuantos"); xreg++) {
                if (resultadoven.getString("id_ven").equals(vendedorfac) == true) {
                    break;
                }
                resultadoven.next();
            }
            CboVendedor.setSelectedItem(resultadoven.getString("elvendedor"));
        } catch (Exception a) {
        }
    }

    public void cargardetalle() {
        try {

            limpiardetalle();
            sentenciadet = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultadodet = sentenciadet.executeQuery("select facv_det.id_art,articulos.des_art,facv_det.pvp,facv_det.cant from facv_det inner join articulos on(facv_det.id_art=articulos.id_art) where num_facv='" + resultadocab.getString("num_facv") + "'");

            resultadodet.first();

            while (resultadodet.isAfterLast() == false) {
                Object[] fila = new Object[5];
                fila[0] = resultadodet.getString("id_art");
                fila[1] = resultadodet.getString("des_art");
                fila[2] = redondear.format(resultadodet.getDouble("pvp")).replace(",", ".");
                fila[3] = resultadodet.getObject("cant");
                fila[4] = redondear.format(resultadodet.getDouble("pvp") * resultadodet.getInt("cant")).replace(",", ".");
                modelo.addRow(fila);// Se crea la fila y se pasa los datos del object.
                resultadodet.next();
            }
        } catch (Exception d) {
        }
    }

    public void limpiardetalle() {
        int xreg = modelo.getRowCount();
        if (xreg > 0) {
            for (int x = 1; x <= xreg; x++) {
                modelo.removeRow(0);
            }
        }
    }

    public void limpiar() {
        TxtCedula.setText("");
        TxtCliente.setText("");
        TxtDescuento.setText("0");
        TxtDireccion.setText("");
        TxtFecha.setText("");
        TxtObserva.setText("");
        TxtPago.setText("0.00");
        TxtSaldo.setText("0.00");
        TxtSubtotal.setText("0.00");
        TxtTotdes.setText("0.00");
        TxtTotiva.setText("0.00");
        TxtTotal.setText("0.00");
        TxtTelefono.setText("");
        LblNumero.setText("0000000000");
        CboForma.setSelectedIndex(0);
        CboVendedor.setSelectedIndex(-1);
    }

    private static boolean FechaValida(String fechax) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fechax);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private void cargarfecha() {
        String fechactual = "";
        String dia = String.valueOf(fecha.get(Calendar.DAY_OF_MONTH));
        if (dia.length() == 1) {
            dia = "0" + dia;
        }
        String mes = String.valueOf(fecha.get(Calendar.MONTH) + 1);
        if (mes.length() == 1) {
            mes = "0" + mes;
        }
        String anio = String.valueOf(fecha.get(Calendar.YEAR));
        fechactual = dia + "/" + mes + "/" + anio;
        TxtFecha.setText(fechactual);
    }

    private void anulacion() {
        Statement sentenciaupdate = null;
        try {
            sentenciaupdate = conexion.createStatement();
            String reloj = "";
            String hora = String.valueOf(fecha.get(Calendar.HOUR_OF_DAY));
            if (hora.length() == 1) {
                hora = "0" + hora;
            }
            String minuto = String.valueOf(fecha.get(Calendar.MINUTE));
            if (minuto.length() == 1) {
                minuto = "0" + minuto;
            }
            String segundo = String.valueOf(fecha.get(Calendar.SECOND));
            if (segundo.length() == 1) {
                segundo = "0" + segundo;
            }
            reloj = hora + "H" + minuto + ":" + segundo;
            String fechactual = "";
            String dia = String.valueOf(fecha.get(Calendar.DAY_OF_MONTH));
            if (dia.length() == 1) {
                dia = "0" + dia;
            }
            String mes = String.valueOf(fecha.get(Calendar.MONTH) + 1);
            if (mes.length() == 1) {
                mes = "0" + mes;
            }
            String anio = String.valueOf(fecha.get(Calendar.YEAR));
            fechactual = dia + "/" + mes + "/" + anio;
            String observacion = "FACTURA ANULADA EL DIA " + fechactual + " A LAS " + reloj + ".";
            TxtObserva.setText(observacion);
            numerofac = LblNumero.getText();
            BAnular.setEnabled(false);
            LblCondicion.setText("FACTURA ANULADA");
            LblCondicion.setForeground(new Color(168, 20, 20));
            sentenciaupdate.executeUpdate("UPDATE FACV_CAB SET ANULADA=1,OBSERVAC='" + observacion + "' WHERE NUM_FACV='" + LblNumero.getText() + "'");
            for (int x = 0; x < modelo.getRowCount(); x++) {
                sentenciaupdate.executeUpdate("UPDATE ARTICULOS SET STOCK=STOCK+" + JTable.getValueAt(x, 3) + " WHERE ID_ART='" + JTable.getValueAt(x, 0) + "'");
            }
            localizafactura();
            JOptionPane.showMessageDialog(null, "¡La Factura de Venta Número " + LblNumero.getText() + " ha sido anulada!", "Factura anulada", 1);
        } catch (SQLException m) {
            JOptionPane.showMessageDialog(this, m.getMessage());
        } finally {
            try {
                sentenciaupdate.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }

        }
    }

    private void localizafactura() {
        try {
            //add
            sentenciacab = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultadocab = sentenciacab.executeQuery("SELECT *,(sub_gen-tot_des+tot_iva) as total,round((sub_gen-tot_des+tot_iva-pago_ini),2) as saldo FROM FACV_CAB ORDER BY NUM_FACV");

            resultadocab.first();
            while (resultadocab.isAfterLast() == false) {
                if (resultadocab.getString("num_facv").equals(numerofac) == true) {
                    break;
                }
                resultadocab.next();
            }
        } catch (Exception j) {
        }
    }

    private void guardar() {
        Statement sentenciaupdate = null;
        try {
            int xfac = modelo.getRowCount();
            boolean control = true;
            sentenciastock = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            for (int x = 0; x < xfac; x++) {
                String codlinea = JTable.getValueAt(x, 0).toString();
                resultadostock = sentenciastock.executeQuery("SELECT STOCK FROM ARTICULOS WHERE ID_ART='" + codlinea + "'");
                resultadostock.first();
                int cantiart = Integer.parseInt(modelo.getValueAt(x, 3).toString());
                if (resultadostock.getInt("stock") < cantiart) {
                    control = false;
                    JOptionPane.showMessageDialog(null, "¡La cantidad de " + modelo.getValueAt(x, 3).toString() + ", ingresada para el Artículo " + modelo.getValueAt(x, 1).toString() + " supera su existencia actual que solo llega a " + resultadostock.getString("stock") + "!", "Stock insuficiente", 2);
                }
            }


            if (control == true) {   //ACTUALIZAR VENTAS DEL VENDEDOR
                resultadoxreg = sentenciaxreg.executeQuery("select count(id_ven) as cuantos from vendedores");

                resultadoxreg.last();
                resultadoven.first();
                for (int xreg = 1; xreg <= resultadoxreg.getInt("cuantos"); xreg++) {
                    if (resultadoven.getString("ELVENDEDOR").equals(CboVendedor.getSelectedItem()) == true) {
                        break;
                    }
                    resultadoven.next();
                }





                sentenciaupdate = conexion.createStatement();
                sentenciaupdate.executeUpdate("UPDATE VENDEDORES SET VENTAS=VENTAS+" + Double.valueOf(TxtTotal.getText()) + " WHERE ID_VEN='" + resultadoven.getString("ID_VEN") + "'");
                //ACTUALIZAR SALDO DEL CLIENTE
                if (CboForma.getSelectedItem() == "CREDITO") {
                    sentenciaupdate.executeUpdate("UPDATE CLIENTES SET SAL_CLI=SAL_CLI+" + Double.valueOf(TxtSaldo.getText()) + " WHERE ID_CLI='" + resultadocli.getString("id_cli") + "'");
                }


                //GUARDAR CABECERA
                String fechasql = TxtFecha.getText().substring(6) + "-" + TxtFecha.getText().substring(3, 5) + "-" + TxtFecha.getText().substring(0, 2);
                sentenciaupdate.executeUpdate("INSERT INTO FACV_CAB (NUM_FACV,ID_CLI,ID_VEN,FECHA,FOR_PAG,DESCU,SUB_GEN,TOT_DES,TOT_IVA,pago_ini,OBSERVAC,ANULADA) VALUES('" + LblNumero.getText()
                        + "','" + resultadocli.getString("id_cli") + "','" + resultadoven.getString("ID_VEN") + "','" + fechasql + "','" + CboForma.getSelectedItem()
                        + "'," + TxtDescuento.getText() + "," + TxtSubtotal.getText() + "," + TxtTotdes.getText() + "," + TxtTotiva.getText() + "," + TxtPago.getText() + ",'" + TxtObserva.getText() + "',0)");
                //GUARDAR DETALLE Y ACTUALIZAR STOCK DE ARTICULOS
                for (int i = 0; i < xfac; i++) {
                    sentenciaupdate.executeUpdate("INSERT INTO FACV_DET (NUM_FACV,ID_ART,CANT,PVP) VALUES('" + LblNumero.getText() + "','" + JTable.getValueAt(i, 0) + "'," + JTable.getValueAt(i, 3) + "," + JTable.getValueAt(i, 2) + ")");
                    sentenciaupdate.executeUpdate("UPDATE ARTICULOS SET STOCK=STOCK-" + JTable.getValueAt(i, 3) + " WHERE ID_ART='" + JTable.getValueAt(i, 0) + "'");
                }

                resultadocab = sentenciacab.executeQuery("SELECT *,(sub_gen-tot_des+tot_iva) as total,round((sub_gen-tot_des+tot_iva-pago_ini),2) as saldo FROM FACV_CAB ORDER BY NUM_FACV");
                resultadocab.last();
                guacan();
                nuevafac = false;
                JOptionPane.showMessageDialog(null, "¡La Factura de Venta No. " + LblNumero.getText() + " ha sido almacenada correctamente!", "Venta procesada", 1);
            }
        } catch (SQLException s) {
            JOptionPane.showMessageDialog(null, "Error al guardar " + s.getMessage());
        } finally {
            try {
                sentenciaupdate.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }

        }
    }

    public void vacio() {
        BAnular.setEnabled(false);
        BPrimero.setEnabled(false);
        BAnterior.setEnabled(false);
        BBuscar.setEnabled(false);
        BSiguiente.setEnabled(false);
        BUltimo.setEnabled(false);
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

