/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.*;

public class CrearProductosSQLite {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:src/main/java/persistencia/utpdblocal.db"; // Ruta relativa

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Conexión exitosa a SQLite.");

                // Mostrar las tablas disponibles
                DatabaseMetaData meta = conn.getMetaData();
                ResultSet tables = meta.getTables(null, null, "%", new String[]{"TABLE"});

                System.out.println("Tablas encontradas en la base de datos:");
                while (tables.next()) {
                    System.out.println(" - " + tables.getString("TABLE_NAME"));
                }

            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
