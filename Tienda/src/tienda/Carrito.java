package tienda;

public class Carrito {
    private Producto[] productos;
    private int[] cantidades;
    private int contador;

    public Carrito() {
        this.productos = new Producto[100];
        this.cantidades = new int[100];
        this.contador = 0;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }

    public int[] getCantidades() {
        return cantidades;
    }

    public void setCantidades(int[] cantidades) {
        this.cantidades = cantidades;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    // Métodos para las funcionalidades requeridas
    public void agregarProducto(Producto producto, int cantidad) {
        // Verificar si el producto ya está en el carrito
        for (int i = 0; i < contador; i++) {
            if (productos[i].getNombre().equals(producto.getNombre())) {
                cantidades[i] += cantidad;
                return;
            }
        }
        // Si no está, agregarlo nuevo
        productos[contador] = producto;
        cantidades[contador] = cantidad;
        contador++;
    }

    public void eliminarProducto(Producto producto) {
        for (int i = 0; i < contador; i++) {
            if (productos[i].getNombre().equals(producto.getNombre())) {
                for (int j = i; j < contador - 1; j++) {
                    productos[j] = productos[j + 1];
                    cantidades[j] = cantidades[j + 1];
                }
                contador--;
                productos[contador] = null;
                cantidades[contador] = 0;
                return;
            }
        }
    }

    public void modificarCantidad(Producto producto, int nuevaCantidad) {
        for (int i = 0; i < contador; i++) {
            if (productos[i].getNombre().equals(producto.getNombre())) {
                if (nuevaCantidad <= 0) {
                    eliminarProducto(producto);
                } else {
                    cantidades[i] = nuevaCantidad;
                }
                return;
            }
        }
    }

    public float calcularSubtotal() {
        float subtotal = 0;
        for (int i = 0; i < contador; i++) {
            subtotal += productos[i].getPrecio() * cantidades[i];
        }
        return subtotal;
    }

    public float calcularGastosEnvio() {
        float subtotal = calcularSubtotal();
        if (subtotal <= 30) {
            return 10;
        } else if (subtotal <= 60) {
            return 5;
        } else {
            return 0;
        }
    }

    public float calcularTotal() {
        return calcularSubtotal() + calcularGastosEnvio();
    }

    public void mostrarCarrito() {
        System.out.println("\n=== CARRITO DE COMPRA ===");
        if (contador == 0) {
            System.out.println("El carrito está vacío.");
            return;
        }

        for (int i = 0; i < contador; i++) {
            Producto p = productos[i];
            System.out.printf("%d. %s - %s - Talla: %s - Color: %s\n",
                    i + 1, p.getNombre(), p.getTipoProducto(), p.getTallaje(), p.getColor());
            System.out.printf("   Cantidad: %d - Precio unitario: %.2f€ - Total: %.2f€\n",
                    cantidades[i], p.getPrecio(), p.getPrecio() * cantidades[i]);
        }

        System.out.println("\n=== RESUMEN ===");
        System.out.printf("Subtotal: %.2f€\n", calcularSubtotal());
        System.out.printf("Gastos de envío: %.2f€\n", calcularGastosEnvio());
        System.out.printf("TOTAL A PAGAR: %.2f€\n", calcularTotal());
    }

    public ProductoCarrito[] convertirAProductosCarrito() {
        ProductoCarrito[] productosCarrito = new ProductoCarrito[contador];
        for (int i = 0; i < contador; i++) {
            productosCarrito[i] = new ProductoCarrito(productos[i], cantidades[i]);
        }
        return productosCarrito;
    }
}