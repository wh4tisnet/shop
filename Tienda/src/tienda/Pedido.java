package tienda;

public class Pedido {
    private EstadoPedido estadoPedido;
    private ProductoCarrito[] productos;
    private float precioTotal;
    private float gastosEnvio;
    private String fecha;
    private Usuario usuario;

    public Pedido() {
        this.estadoPedido = EstadoPedido.INICIAL;
    }

    // Getters y Setters
    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public ProductoCarrito[] getProductos() {
        return productos;
    }

    public void setProductos(ProductoCarrito[] productos) {
        this.productos = productos;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public float getGastosEnvio() {
        return gastosEnvio;
    }

    public void setGastosEnvio(float gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}