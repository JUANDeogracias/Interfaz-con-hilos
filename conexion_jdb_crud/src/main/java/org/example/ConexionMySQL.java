package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    /*
    * CONEXION PARA EL CONTENEDOR DE DOCKER
    * */
    public String driver = "com.mysql.cj.jdbc.Driver";
    public String database = "miapp";
    public String hostname = "localhost";
    private String port = "3310";
    private String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false&allowPublicKeyRetrieval=true";
    private String username = "root";
    private String password = "root";
    public Connection conn;

    public void conectar() {
        try {
            Class.forName(driver);  // Cargar el controlador JDBC
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexión a la base de datos establecida correctamente.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se pudo cargar el controlador JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: No se pudo establecer la conexión con la base de datos.");
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
