
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.ConsumoDetalle;

public class ConsumoDetalleDAO {

    private final String url = "jdbc:sqlite:src/main/java/persistencia/utpdblocal.db"; // Ajusta si tu path cambia

    public void insertarDetalles(DefaultTableModel modeloTabla, String idSocio, String fechaConsumo) throws SQLException {
        String sql = "INSERT INTO ConsumosDetalle (id_socio, fecha_consumo, id_producto_servicio, descripcion, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                String idProducto = modeloTabla.getValueAt(i, 0).toString();
                String descripcion = modeloTabla.getValueAt(i, 1).toString();
                int cantidad = Integer.parseInt(modeloTabla.getValueAt(i, 2).toString());
                double precio = Double.parseDouble(modeloTabla.getValueAt(i, 3).toString().replace(",", "."));
                double subtotal = Double.parseDouble(modeloTabla.getValueAt(i, 4).toString().replace(",", "."));

                ps.setString(1, idSocio);
                ps.setString(2, fechaConsumo);
                ps.setString(3, idProducto);
                ps.setString(4, descripcion);
                ps.setInt(5, cantidad);
                ps.setDouble(6, precio);
                ps.setDouble(7, subtotal);

                ps.addBatch();
            }

            ps.executeBatch();
        }
    }
    
    public List<ConsumoDetalle> obtenerTodosLosDetalles() {
        List<ConsumoDetalle> detalles = new ArrayList<>();

        String sql = "SELECT id_socio, fecha_consumo, id_producto_servicio, descripcion, cantidad, precio_unitario FROM ConsumosDetalle";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String idSocio = rs.getString("id_socio");
                String fecha = rs.getString("fecha_consumo");
                String idProducto = rs.getString("id_producto_servicio");
                String descripcion = rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad");
                double precioUnitario = rs.getDouble("precio_unitario");

                ConsumoDetalle detalle = new ConsumoDetalle(idSocio, fecha, idProducto, descripcion, cantidad, precioUnitario);
                detalles.add(detalle);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los consumos detallados: " + e.getMessage());
        }

        return detalles;
    }
}
