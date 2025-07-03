
package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Producto;

public class ProductoDAO {
    private final String dbUrl = "jdbc:sqlite:src/main/java/persistencia/utpdblocal.db";
    private List<Producto> listaProductos = new ArrayList<>();

    public ProductoDAO() {
        cargarProductosDesdeBD();
    }

    private void cargarProductosDesdeBD() {
        String query = "SELECT id_producto, descripcion, precio_unitario FROM Productos";
        try (Connection conn = DriverManager.getConnection(dbUrl);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            listaProductos.clear();
            while (rs.next()) {
                String id = rs.getString("id_producto");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio_unitario");
                Producto producto = new Producto(id, descripcion, precio);
                listaProductos.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }
}
