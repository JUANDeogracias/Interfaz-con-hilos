package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultasSQL extends ConexionMySQL implements Runnable{
    ArrayList<Usuario> usuarios;
    PreparedStatement ps = null;
    Connection conectar = null;
    public Usuario usuario;


    public ConsultasSQL() {
        usuarios  = new ArrayList<Usuario>();
        super.conectar();
    }


    public void obtenerTodosUsuarios() {
        String query = "SELECT * FROM usuario";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("contrasena");
                String usuario = "id:" + id + ",nombre:" + nombre + ",contrasena:" + correo;

                //Añadimos el usuario a la lista
                usuarios.add(new Usuario(id,nombre,correo));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(int id) {

            String eliminarUsuario = "DELETE FROM usuario WHERE id = ?";


            try (PreparedStatement ps = conn.prepareStatement(eliminarUsuario)){
                ps.setInt(1,id);

                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Usuario eliminado correctamente.");
                } else {
                    System.out.println("No se encontró un usuario con el ID especificado.");
                }

            }catch (SQLException e) {
                System.out.println("Error al eliminar el usuario");
                e.printStackTrace();
            }

    }

    public void insertarUsuario(String nombre, String email) {
        if (usuarios.isEmpty()) {  // Solo insertamos si la lista de usuarios está vacía
            String insertQuery = "INSERT INTO usuario (nombre, contrasena) VALUES (?, ?)";
            try (PreparedStatement insertPs = conn.prepareStatement(insertQuery)) {
                insertPs.setString(1, nombre);
                insertPs.setString(2, email);

                int filasAfectadas = insertPs.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Usuario insertado correctamente.");
                }

            } catch (SQLException e) {
                System.err.println("Error al insertar el usuario.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Ya existen usuarios registrados. No se insertará un nuevo usuario.");
        }
    }

    public void salir() {
        super.cerrarConexion();
        System.exit(0);
    }

    @Override
    public void run() {
        //iniciamos las consultas que tengamos registradas

        obtenerTodosUsuarios();
        insertarUsuario("juan", "jdeomoya@gmail,com");

    }
}
