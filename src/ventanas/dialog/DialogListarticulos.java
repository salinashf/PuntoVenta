package ventanas.dialog;

import java.sql.SQLException;
import utilidades.conexion.BaseConexion;
import javax.swing.JPanel;
import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Point;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

public class DialogListarticulos extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JButton BSeleccionar = null;
    private JButton BCerrar = null;
    private JTextField TxtArticulo = null;
    private JLabel LblVendedor = null;
    private JScrollPane jScrollPane = null;
    private JTable JTable = null;
    Connection conexion;  

    configura modelo = new configura();
    public static String codigoart = "";  //  @jve:decl-index=0:
    public static boolean seleccionaart = false;
    private JPanel Panel1 = null;
    private JLabel jLabel = null;

    public DialogListarticulos() {
        super();
        initialize();
    }
  @Override
    protected void finalize() throws Throwable {
        System.out.println(getClass()) ;
        conexion.close();
        super.finalize();
    }
    private void initialize() {
        this.setSize(731, 395);
        this.setResizable(false);
        this.setTitle("Lista de Articulos");
        this.setContentPane(getJContentPane());
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        modelo.addColumn("ID");
        modelo.addColumn("ARTICULOS");
        modelo.addColumn("STOCK");
        modelo.addColumn("P.V.P.");
        TableColumn columna0 = JTable.getColumn("ID");
        columna0.setPreferredWidth(0);
        columna0.setMinWidth(0);
        columna0.setMaxWidth(0);
        TableColumn columna1 = JTable.getColumn("ARTICULOS");
        columna1.setPreferredWidth(500);
        columna1.setMinWidth(500);
        columna1.setMaxWidth(500);
        TableColumn columna2 = JTable.getColumn("STOCK");
        columna2.setPreferredWidth(90);
        columna2.setMinWidth(90);
        columna2.setMaxWidth(90);
        JTable.isCellEditable(JTable.getSelectedRow(), JTable.getSelectedColumn());
        conectar();
    }

    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jLabel = new JLabel();
            jLabel.setBounds(new Rectangle(16, 308, 57, 50));
            jLabel.setText("");
            jLabel.setIcon(new ImageIcon("IMAGENES/LUPA.PNG"));
            LblVendedor = new JLabel();
            LblVendedor.setBounds(new Rectangle(12, 15, 64, 21));
            LblVendedor.setText("Art�culo:");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getTxtArticulo(), null);
            jContentPane.add(LblVendedor, null);
            jContentPane.add(getJScrollPane(), null);
            jContentPane.add(getPanel1(), null);
            jContentPane.add(jLabel, null);
        }
        return jContentPane;
    }

    private JButton getBSeleccionar() {
        if (BSeleccionar == null) {
            BSeleccionar = new JButton();
            BSeleccionar.setFont(new Font("Dialog", Font.PLAIN, 11));
            BSeleccionar.setIcon(new ImageIcon("IMAGENES/ok2.JPG"));
            BSeleccionar.setBounds(new Rectangle(7, 7, 130, 39));
            BSeleccionar.setText("Seleccionar");
            BSeleccionar.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    seleccionar();
                }
            });
        }
        return BSeleccionar;
    }

    private JButton getBCerrar() {
        if (BCerrar == null) {
            BCerrar = new JButton();
            BCerrar.setFont(new Font("Dialog", Font.PLAIN, 11));
            BCerrar.setIcon(new ImageIcon("IMAGENES/CANCELAR2.JPG"));
            BCerrar.setBounds(new Rectangle(143, 7, 130, 39));
            BCerrar.setText("Cancelar");
            BCerrar.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    seleccionaart = false;
                    dispose();
                }
            });
        }
        return BCerrar;
    }

    private JTextField getTxtArticulo() {
        if (TxtArticulo == null) {
            TxtArticulo = new JTextField();
            TxtArticulo.setBounds(new Rectangle(87, 13, 624, 25));
            TxtArticulo.addKeyListener(new java.awt.event.KeyAdapter() {

                @Override
                public void keyTyped(java.awt.event.KeyEvent e) {
                    char caracter;
                    caracter = (e.getKeyChar() + "").toUpperCase().charAt(0);
                    e.setKeyChar(caracter);
                }
            });
            TxtArticulo.addCaretListener(new javax.swing.event.CaretListener() {

                @Override
                public void caretUpdate(javax.swing.event.CaretEvent e) {
                    actualizalista();
                }
            });
        }
        return TxtArticulo;
    }

    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setBounds(new Rectangle(15, 48, 696, 251));
            jScrollPane.setViewportView(getJTable());
        }
        return jScrollPane;
    }

    private JTable getJTable() {
        if (JTable == null) {
            JTable = new JTable(modelo);
            JTable.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        seleccionar();
                    }

                }
            });
        }
        return JTable;
    }

    public void conectar() {
        try {
            conexion = BaseConexion.getConectar();


            actualizalista();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "�Ha ocurrido un error al intentar acceder los datos de los Art�culos!", "Error de acceso", 2);
            return;
        }
    }

    public class configura extends DefaultTableModel {

        private static final long serialVersionUID = 1L;

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        @Override
        public Class getColumnClass(int columna) {
            if (columna == 2) {
                return Integer.class;
            }
            if (columna == 3) {
                return Integer.class;
            }
            return Object.class;
        }
    }

    private void actualizalista() {
           Statement sentencia=  null;
           ResultSet resultado= null;
        try {
         
            sentencia = conexion.createStatement();
            resultado= sentencia.executeQuery("SELECT ID_ART,DES_ART,STOCK,PVP_ART FROM ARTICULOS WHERE DES_ART LIKE '" + TxtArticulo.getText() + "%' ORDER BY DES_ART");

            int xreg = modelo.getRowCount();
            if (xreg > 0) {
                for (int x = 1; x <= xreg; x++) {
                    modelo.removeRow(0);
                }
            }
            if (resultado.next() == true) {
                BSeleccionar.setEnabled(true);
                resultado.first();
                while (resultado.isAfterLast() == false) {
                    Object[] fila = new Object[4]; // Se crea un array con 4 columnas para cada filas en la tabla
                    fila[0] = resultado.getObject("id_art");
                    fila[1] = resultado.getObject("des_art");
                    fila[2] = resultado.getObject("stock");
                    fila[3] = resultado.getString("pvp_art");
                    modelo.addRow(fila);
                    resultado.next();
                }
            } else {
                BSeleccionar.setEnabled(false);
            }
        } catch (SQLException m) {
             JOptionPane.showMessageDialog(this, m.getMessage());
        } finally {
            try {
                sentencia.close();
                 resultado.close();
            } catch (SQLException ex) {
                  JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            
        }
    }

    private void seleccionar() {
        if (JTable.isCellSelected(JTable.getSelectedRow(), 0) == false) {
            JOptionPane.showMessageDialog(this, "Seleccione un Art�culo de la lista!", "Error de seleccion", 2);
        } else {
            codigoart = JTable.getValueAt(JTable.getSelectedRow(), 0).toString();
            seleccionaart = true;
            dispose();
        }
    }

    private JPanel getPanel1() {
        if (Panel1 == null) {
            Panel1 = new JPanel();
            Panel1.setLayout(null);
            Panel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            Panel1.setLocation(new Point(430, 306));
            Panel1.setSize(new Dimension(280, 52));
            Panel1.add(getBSeleccionar(), null);
            Panel1.add(getBCerrar(), null);
        }
        return Panel1;
    }
}
