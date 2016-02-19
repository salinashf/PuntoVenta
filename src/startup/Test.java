/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package startup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import utilidades.conexion.BaseConexion;

/**
 *
 * @author Henry
 */
public class Test {
 public static void main(String[] args) throws SQLException {
     Connection   cd =   BaseConexion.getConectar();
     Statement   st  =  cd.createStatement() ;
    ResultSet  rs =  st.executeQuery(
            "SELECT ID_ART,DES_ART,STOCK,PVP_ART FROM ARTICULOS ORDER BY ID_ART");

    while(rs.next()){
        System.out.print(
                 rs.getObject(1)  +"---"+
                 rs.getObject(2)  +"---"+
                 rs.getObject(3)  +"---"+
                 rs.getObject(4)  +"---"
                )
        ;
    }

    rs.first();

 while(rs.next()){
        System.out.print(
                 rs.getObject(1)  +"---"+
                 rs.getObject(2)  +"---"+
                 rs.getObject(3)  +"---"+
                 rs.getObject(4)  +"---"
                )
        ;
    }


 }
}
