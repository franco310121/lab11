package model;

public class ConsumoDetalle {
    private String idSocio, fecha, idProducto, descripcion;
    private int cantidad;
    private double precioUnitario;

    public ConsumoDetalle(String idSocio, String fecha, String idProducto, String descripcion, int cantidad, double precioUnitario) {
        this.idSocio = idSocio;
        this.fecha = fecha;
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getIdSocio() { return idSocio; }
    public String getFecha() { return fecha; }
    public String getIdProducto() { return idProducto; }
    public String getDescripcion() { return descripcion; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getSubtotal() { return cantidad * precioUnitario; }
}
