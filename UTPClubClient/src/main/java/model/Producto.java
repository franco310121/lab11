
package model;


public class Producto {
    private String id;
    private String descripcion;
    private double precioUnitario;

    public Producto(String id, String descripcion, double precioUnitario) {
        this.id = id;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
    }

    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public double getPrecioUnitario() { return precioUnitario; }

    @Override
    public String toString() {
        return descripcion + " (S/ " + precioUnitario + ")";
    }
}
