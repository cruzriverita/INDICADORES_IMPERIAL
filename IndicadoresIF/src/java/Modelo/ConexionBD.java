/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConexionBD {

    String driver = "com.mysql.jdbc.Driver";
    String server = "192.168.1.107";
    String BaseDatos="BD_INDICES";
    String connectString = "jdbc:mysql://"+server+"/"+BaseDatos;
    String user = "root";
    String pass = "root";

    public Statement conectar() {
        Connection conn;
        Statement st = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Class.forName(driver);
            conn = DriverManager.getConnection(connectString, user, pass);
            if (conn != null) {
                System.out.println("Conexion: " + connectString + "Exitosa !");
                st = conn.createStatement();
            }
        } catch (SQLException ex) {
            System.out.println("NO SE HA PODIDO CONECTAR CON LA BASE DE DATOS, " + ex.getMessage() + ex.getErrorCode());
         
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getCause());
        }
        return st;
    }

    //select a las tablas 
    public <T> List<Map<String, Object>> select(String query) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        ConexionBD conectar = new ConexionBD();
        Statement st = conectar.conectar();
        ResultSet rs;
        try {
            rs = st.executeQuery(query);
            Map<String, Object> row ;

            ResultSetMetaData metaData = rs.getMetaData();
            Integer columnCount = metaData.getColumnCount();

            while (rs.next()) {
                row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                resultList.add(row);
            }
        } catch (Exception e) {
            out.println(e.getMessage());
        }
        return resultList;
    }

    public boolean insert_update_delete(String query) {
        try {
            ConexionBD conectar = new ConexionBD();
            try (Statement comando = conectar.conectar()) {
                comando.executeUpdate(query);
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

  
    
}
