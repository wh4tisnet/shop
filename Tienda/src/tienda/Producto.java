package tienda;

public class Producto {

    private String nombre;
    private Color color;
    private float precio;
    private TipoProducto tipoProducto;
    private Tallaje tallaje;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Tallaje getTallaje() {
        return tallaje;
    }

    public void setTallaje(Tallaje tallaje) {
        this.tallaje = tallaje;
    }
}
