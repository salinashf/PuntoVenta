package utilidades.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class BaseConexion {

    private static Connection cns = null;
    private static String host = "localhost";
    private static String dataBase = "facturacion";
    private static String user = "root";
    private static String pass = "";

    public static Connection getConectar() {
        conectar();
        return cns;
    }

    public static void conectar() {
        try {
            if (cns == null) {
               //conexionSQLite();
                
                 conexion();
            } else {
                if (cns.isClosed()) {
                  // conexionSQLite();
                     conexion();
                }

            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error Interno! ", "Registro de Coneccion falló "+ ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Acceso denegado!!", "Usuario NO Autorizado"+ ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }

    }

    private static void conexion() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://" + host + ":3306/" + dataBase;
        cns = DriverManager.getConnection(url, user, pass);
    }

    private static void conexionSQLite() throws ClassNotFoundException, SQLException {
       Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:./bd/facturacion.db3" ;
        cns = DriverManager.getConnection(url);
    }


    private static void cerrarConexion() throws SQLException {
        if (cns != null) {
            if (!cns.isClosed()) {
                cns.close();
            }
        }
    }
}
