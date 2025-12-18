package tienda;

public class Usuario {
    private Nombre nombreCompleto;
    private Direccion direccion;
    private String DNI;
    private String[] telefonos = new String[5];
    private String correoElectronico;
    private String nombreUsuario;
    private String contrasenya;
    private boolean esAdministrador;
    private Carrito carrito;
    private Pedido[] pedidos = new Pedido[100];
    private int numPedidos = 0;

    public Usuario() {
        this.carrito = new Carrito();
    }
    
    public Nombre getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(Nombre nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String[] getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String[] telefonos) {
        this.telefonos = telefonos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    // GETTERS Y SETTERS NUEVOS
    public boolean isEsAdministrador() {
        return esAdministrador;
    }

    public void setEsAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public Pedido[] getPedidos() {
        return pedidos;
    }

    public int getNumPedidos() {
        return numPedidos;
    }

    public void agregarPedido(Pedido pedido) {
        if (numPedidos < pedidos.length) {
            pedidos[numPedidos] = pedido;
            numPedidos++;
        }
    }
}
