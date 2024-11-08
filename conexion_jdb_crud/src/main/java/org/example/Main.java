package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JFrame{

    public ArrayList<JButton> botones;

    public Main() {
        botones = new ArrayList<JButton>();
        /*
        * ESTABLECEMOS LA CONEXION CON LA BBDD
        * */
        ConexionMySQL mysql = new ConexionMySQL();

        ConsultasSQL instancia_consultas = new ConsultasSQL();

        Thread realizar_consultas = new Thread(instancia_consultas);

        realizar_consultas.start();

        /*
        * VAMOS CON LA INTERFAZ
        * */
        JFrame frame = new JFrame("CRUD USUARIOS");

        String[] columnas = {"ID", "Nombre", "Constraseña"};

        // Crear los datos de la tabla
        Object[][] datos = null;

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);


        JTable tabla = new JTable(modelo);

        JPanel panel = new JPanel(new GridLayout(1,4));

        JScrollPane scrollPane = new JScrollPane(tabla);

        JButton btnMostrarDatosEnTabla = new JButton("Mostrar Datos");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnSalir = new JButton("Salir");

        btnMostrarDatosEnTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Limpiamos la tabla en primer lugar
                modelo.setRowCount(0);
                //vamos a insertar cada uno de los datos
                int contador_filas = 0;
                for(Usuario usuario : instancia_consultas.usuarios) {
                    Object[] fila = {usuario.getId(),usuario.getNombre(),usuario.getContrasena()};
                    modelo.addRow(fila);
                }
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valor_fila = tabla.getSelectedRow();

                if(valor_fila!= -1) {
                    int id_usuario_a_eliminar = (int) tabla.getValueAt(valor_fila,0); //hacemos esto para saber el contenido de el id
                    instancia_consultas.eliminarUsuario(id_usuario_a_eliminar);
                    modelo.removeRow(valor_fila);
                }
            }
        });
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instancia_consultas.salir();
            }
        });



        botones.add(btnMostrarDatosEnTabla);
        botones.add(btnEliminar);
        botones.add(btnActualizar);
        botones.add(btnSalir);

        for(JButton btn : botones) {
            panel.add(btn);
        }


        frame.add(panel, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }


}