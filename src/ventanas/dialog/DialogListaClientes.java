package ventanas.dialog;

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
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

public class DialogListaClientes extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JButton BSeleccionar = null;
    private JButton BCerrar = null;
    private JTextField TxtCliente = null;
    private JLabel LblCliente = null;
    private JScrollPane jScrollPane = null;
    private JTable JTable = null;
    Connection conexion;
    editable modelo = new editable();
    public static String codigocli = "";  //  @jve:decl-index=0:
    public static boolean seleccionacli = false;
    private JPanel Panel1 = null;
    private JLabel jLabel = null;

    public DialogListaClientes() {
        super();
        initialize();
    }

    private void initialize() {
        this.setSize(731, 395);
        this.setResizable(false);
        this.setTitle("Lista de Clientes");
        this.setContentPane(getJContentPane());
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        modelo.addColumn("ID");
        modelo.addColumn("CLIENTES");
        TableColumn columna0 = JTable.getColumn("ID");
        columna0.setPreferredWidth(0);
        columna0.setMinWidth(0);
        columna0.setMaxWidth(0);
        JTable.isCellEditable(JTable.getSelectedRow(), 1);
        conectar();
    }

    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jLabel = new JLabel();
            jLabel.setBounds(new Rectangle(39, 307, 61, 50));
            jLabel.setIcon(new ImageIcon("IMAGENES/LUPA.PNG"));
            jLabel.setText("");
            LblCliente = new JLabel();
            LblCliente.setBounds(new Rectangle(12, 15, 64, 21));
            LblCliente.setText("CLIENTE:");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getTxtCliente(), null);
            jContentPane.add(LblCliente, null);
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
            BSeleccionar.setOpaque(true);
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
                    seleccionacli = false;
                    dispose();
                }
            });
        }
        return BCerrar;
    }

    private JTextField getTxtCliente() {
        if (TxtCliente == null) {
            TxtCliente = new JTextField();
            TxtCliente.setBounds(new Rectangle(87, 13, 624, 25));
            TxtCliente.addCaretListener(new javax.swing.event.CaretListener() {

                @Override
                public void caretUpdate(javax.swing.event.CaretEvent e) {
                    actualizalista();
                }
            });
            TxtCliente.addKeyListener(new java.awt.event.KeyAdapter() {

                @Override
                public void keyTyped(java.awt.event.KeyEvent e) {
                    char letra;
                    letra = (e.getKeyChar() + "").toUpperCase().charAt(0);
                    e.setKeyChar(letra);
                }
            });
        }
        return TxtCliente;
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
            JOptionPane.showMessageDialog(null, "�Ha ocurrido un error al intentar acceder los datos del Cliente!", "Error de acceso", 2);
            return;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(getClass());
        conexion.close();
        super.finalize();
    }

    public class editable extends DefaultTableModel {

        private static final long serialVersionUID = 1L;

        @Override
        public boolean isCellEditable(int row, int column) {
            if (column == 1) {
                return false;
            }
            return false;
        }
    }

    private void actualizalista() {
        Statement sentencia = null;
        ResultSet resultado = null;
        try {
            sentencia = conexion.createStatement();

             resultado = sentencia.executeQuery("select id_cli,concat(ape_cli,' ',nom_cli) as elcliente from clientes where concat(ape_cli,' ',nom_cli) like '" + TxtCliente.getText() + "%' order by elcliente");
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
                    Object[] fila = new Object[2]; // Se crea un array con dos columnas para las filas en la tabla
                    fila[0] = resultado.getObject("id_cli");
                    fila[1] = resultado.getObject("elcliente");
                    modelo.addRow(fila);// Se a�ade al modelo la fila completa.
                    resultado.next();
                }
            } else {
                BSeleccionar.setEnabled(false);
            }
        } catch (SQLException m) {
            JOptionPane.showMessageDialog(null, m.getMessage());
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
            JOptionPane.showMessageDialog(null, "�Seleccione un Cliente de la lista!", "Error de selecci�n", 2);
        } else {
            codigocli = JTable.getValueAt(JTable.getSelectedRow(), 0).toString();
            seleccionacli = true;
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
