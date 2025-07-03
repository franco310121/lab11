
package persistencia;


import java.sql.*;
import model.ConsumoDetalle;

public class LocalDBService {
    private Connection conn;

    public LocalDBService(String url, String user, String pass) throws SQLException {
        conn = DriverManager.getConnection(url, user, pass);
    }

    public void registrarDetalle(ConsumoDetalle detalle) throws SQLException {
        String sql = "INSERT INTO ConsumosDetalle (id_socio, fecha_consumo, id_producto_servicio, descripcion, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, detalle.getIdSocio());
        stmt.setString(2, detalle.getFecha());
        stmt.setString(3, detalle.getIdProducto());
        stmt.setString(4, detalle.getDescripcion());
        stmt.setInt(5, detalle.getCantidad());
        stmt.setDouble(6, detalle.getPrecioUnitario());
        stmt.setDouble(7, detalle.getSubtotal());
        stmt.executeUpdate();
        stmt.close();
    }

    public double calcularTotalPorFecha(String idSocio, String fecha) throws SQLException {
        String sql = "SELECT SUM(subtotal) FROM ConsumosDetalle WHERE id_socio = ? AND fecha_consumo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, idSocio);
        stmt.setString(2, fecha);
        ResultSet rs = stmt.executeQuery();
        double total = rs.next() ? rs.getDouble(1) : 0.0;
        rs.close();
        stmt.close();
        return total;
    }
}
