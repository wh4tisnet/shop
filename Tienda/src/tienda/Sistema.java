package tienda;

import java.util.Scanner;

public class Sistema {
    private Scanner sc = new Scanner(System.in);
    private Usuario[] usuarios = new Usuario[10];
    private Producto[] productos = new Producto[100];
    private Pedido[] todosPedidos = new Pedido[1000];
    private int numUsuarios = 0;
    private int numProductos = 0;
    private int numPedidos = 0;
    private Usuario usuarioActual = null;

    public Sistema(){
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("admin");
        usuario.setContrasenya("admin");
        usuario.setEsAdministrador(true);
        this.usuarios[0] = usuario;
        numUsuarios = 1;
    }

    public void init() {
        int opcion = -1;

        while (opcion != 3) {
            System.out.println("\nTIENDA");
            System.out.print("1.Login " + "2.Registrarse " + "3.Salir");
            System.out.print("\nSeleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    this.login();
                    break;
                case 2:
                    this.registrarse();
                    break;
                case 3:
                    break;
            }
        }
    }

    public void login(){
        System.out.println("Vas a hacer el login.\n Dime el usuario");
        String usuario = sc.nextLine();
        System.out.println("Dime la contraseña");
        String contrasenya = sc.nextLine();
        for(Usuario usu: this.usuarios){
            if(usu != null && usu.getNombreUsuario().equals(usuario)){
                if(usu.getContrasenya().equals(contrasenya)){
                    usuarioActual = usu;
                    mostrarProductos();
                }
                else {
                    System.err.println("Contrasenya no valida");
                }
            }
        }
    }

    public void registrarse(){
        System.out.println("Vamos a registrar un usuario");
        Nombre nombreCompleto = new Nombre();
        System.out.println("Dime su nombre");
        nombreCompleto.setNombre(this.sc.nextLine());
        System.out.println("Dime su primer apellido");
        nombreCompleto.setPrimerApellido(this.sc.nextLine());
        System.out.println("Dime su segundo apellido");
        nombreCompleto.setSegundoApelllido(this.sc.nextLine());
        boolean usuarioExiste = false;

        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(nombreCompleto);

        System.out.println("Dime el dni del usuario");
        usuario.setDNI(this.sc.nextLine());


        System.out.println("Teléfono principal de 5 digitos:");
        String telefono1 = this.sc.nextLine();

        while (telefono1.trim().isEmpty()) {
            System.out.println("El telefono principal no puede estar vacío:");
            telefono1 = this.sc.nextLine();
        }
        usuario.getTelefonos()[0] = telefono1;

        System.out.println("Segundo telefono (opcional - presione Enter para omitir):");
        String telefono2 = this.sc.nextLine();
        if (!telefono2.trim().isEmpty()) {
            usuario.getTelefonos()[1] = telefono2;
        }

        this.sc.nextLine();
        System.out.println("Dime el correo electronico");
        usuario.setCorreoElectronico(this.sc.nextLine());

        do {
            System.out.println("Dime el nombre de usuario");
            String nombreUsuario = this.sc.nextLine();
            usuario.setNombreUsuario(nombreUsuario);


            for (int i = 0; i < numUsuarios; i++) {
                if (usuarios[i].getNombreUsuario().equals(nombreUsuario)) {
                    usuarioExiste = true;
                    break;
                }
            }
            if (usuarioExiste) {
                System.out.println("Este nombre de usuario ya existe, elige otro.");
            }

        } while (usuarioExiste);

        String pass1 = "";
        String pass2 = "!";
        while(!pass1.equals(pass2)){
            System.out.println("Dime la contraseña");
            pass1 = this.sc.nextLine();

            System.out.println("Repite la contraseña");
            pass2 = this.sc.nextLine();
        }

        usuario.setContrasenya(pass1);

        Direccion direccion = new Direccion();
        System.out.println("Indica el tipo de via. Los tipos válidos son: ");
        for(int i =0; i<TipoVia.values().length; i++){
            System.out.println((i + 1) + " - " + TipoVia.values()[i]);
        }
        int tipoVia = this.sc.nextInt();
        this.sc.nextLine();

        direccion.setTipoDeVía(TipoVia.values()[tipoVia - 1]);

        System.out.println("Indica el nombre de la dirección");
        direccion.setNombre(this.sc.nextLine());

        System.out.println("Indica el número");
        direccion.setNumero(this.sc.nextInt());
        this.sc.nextLine();

        System.out.println("Indica la puerta");
        direccion.setPuerta(this.sc.nextLine());

        System.out.println("Indica la ciudad");
        direccion.setCiudad(this.sc.nextLine());

        System.out.println("Indica la provincia");
        direccion.setProvincia(this.sc.nextLine());

        System.out.println("Indica el código postal");
        direccion.setCodigoPostal(this.sc.nextInt());
        this.sc.nextLine();

        usuario.setDireccion(direccion);
        usuario.setEsAdministrador(false);

        //añade el usuario al array
        if (numUsuarios < usuarios.length) {
            usuarios[numUsuarios] = usuario;
            numUsuarios++;
            System.out.println("Usuario registrado exitosamente!");
        } else {
            System.out.println("No se pueden registrar más usuarios.");
        }
    }

    public void mostrarProductos(){
        if (usuarioActual == null) return;

        if (usuarioActual.isEsAdministrador()) {
            menuAdministrador();
        } else {
            menuUsuario();
        }
    }

    private void menuAdministrador() {
        int opcion = -1;

        while (opcion != 6) {
            System.out.println("\nMENU ADMINISTRADOR");
            System.out.println(
                            "1. Ver productos" +
                            "2. Agregar producto" +
                            "3. Modificar precio de producto" +
                            "4. Eliminar producto" +
                            "5. Ver todos los pedidos" +
                            "6. Cambiar estado de pedido" +
                            "7. Cerrar sesion"
            );
            System.out.print("Selecciona una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    verProductos();
                    break;
                case 2:
                    agregarProducto();
                    break;
                case 3:
                    modificarPrecioProducto();
                    break;
                case 4:
                    eliminarProducto();
                    break;
                case 5:
                    verTodosPedidos();
                    break;
                case 6:
                    cambiarEstadoPedido();
                    break;
                case 7:
                    usuarioActual = null;
                    return;
            }
        }
    }

    private void menuUsuario() {
        int opcion = -1;

        while (opcion != 6) {
            System.out.println("\nMENU USUARIO");
            System.out.println(
                            "1. Ver productos disponibles " +
                            "2. Ver mi carrito " +
                            "3. Realizar pedido " +
                            "4. Ver mis pedidos " +
                            "5. Marcar pedido como recibido " +
                            "6. Cerrar sesión"
            );
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    verProductosUsuario();
                    break;
                case 2:
                    verCarrito();
                    break;
                case 3:
                    realizarPedido();
                    break;
                case 4:
                    verMisPedidos();
                    break;
                case 5:
                    marcarPedidoRecibido();
                    break;
                case 6:
                    usuarioActual = null;
                    return;
            }
        }
    }

    private void verProductos() {
        System.out.println("\nPRODUCTOS DISPONIBLES");
        if (numProductos == 0) {
            System.out.println("No hay productos.");
            return;
        }

        for (int i = 0; i < numProductos; i++) {
            Producto p = productos[i];
            System.out.printf("%d. %s - %s - %.2f€\n",
                    i+1, p.getNombre(), p.getTipoProducto(), p.getPrecio());
        }
    }

    private void agregarProducto() {
        System.out.println("\nAGREGAR PRODUCTO");

        Producto producto = new Producto();

        System.out.print("Nombre: ");
        producto.setNombre(sc.nextLine());

        System.out.println("Color:");
        for (int i = 0; i < Color.values().length; i++) {
            System.out.println((i+1) + ". " + Color.values()[i]);
        }
        int colorIdx = sc.nextInt();
        sc.nextLine();
        producto.setColor(Color.values()[colorIdx-1]);

        System.out.print("Precio: ");
        producto.setPrecio(sc.nextFloat());
        sc.nextLine();

        System.out.println("Tipo de producto:");
        for (int i = 0; i < TipoProducto.values().length; i++) {
            System.out.println((i+1) + ". " + TipoProducto.values()[i]);
        }
        int tipoIdx = sc.nextInt();
        sc.nextLine();
        producto.setTipoProducto(TipoProducto.values()[tipoIdx-1]);

        System.out.println("Talla:");
        for (int i = 0; i < Tallaje.values().length; i++) {
            System.out.println((i+1) + ". " + Tallaje.values()[i]);
        }
        int tallaIdx = sc.nextInt();
        sc.nextLine();
        producto.setTallaje(Tallaje.values()[tallaIdx-1]);

        if (numProductos < productos.length) {
            productos[numProductos] = producto;
            numProductos++;
            System.out.println("Producto agregado exitosamente.");
        }
    }

    private void modificarPrecioProducto() {
        verProductos();

        if (numProductos == 0) return;

        System.out.print("\nSeleccione el numero del producto: ");
        int idx = sc.nextInt();
        sc.nextLine();

        if (idx < 1 || idx > numProductos) {
            System.out.println("Número inválido.");
            return;
        }

        Producto producto = productos[idx-1];
        System.out.printf("Precio actual: %.2f€\n", producto.getPrecio());
        System.out.print("Nuevo precio: ");
        float nuevoPrecio = sc.nextFloat();
        sc.nextLine();

        producto.setPrecio(nuevoPrecio);
        System.out.println("Precio modificado.");
    }

    private void eliminarProducto() {
        verProductos();

        if (numProductos == 0) return;
        System.out.print("\nSeleccione el numero del producto a eliminar: ");
        int idx = sc.nextInt();
        sc.nextLine();

        if (idx < 1 || idx > numProductos) {
            System.out.println("Número inválido.");
            return;
        }

        //eliminar producto moviendo los demás
        for (int i = idx-1; i < numProductos-1; i++) {
            productos[i] = productos[i+1];
        }
        productos[numProductos-1] = null;
        numProductos--;

        System.out.println("Producto eliminado.");
    }

    private void verProductosUsuario() {
        System.out.println("\nPRODUCTOS DISPONIBLES");
        if (numProductos == 0) {
            System.out.println("No hay productos.");
            return;
        }

        for (int i = 0; i < numProductos; i++) {
            Producto p = productos[i];
            System.out.printf("%d. %s - %s - %.2f€\n",
                    i+1, p.getNombre(), p.getTipoProducto(), p.getPrecio());
        }

        System.out.print("\nDesea agregar un producto al carrito? s/n: ");
        String respuesta = sc.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Seleccione el numero del producto: ");
            int idx = sc.nextInt();
            sc.nextLine();

            if (idx < 1 || idx > numProductos) {
                System.out.println("Numero invalido.");
                return;
            }

            System.out.print("Cantidad: ");
            int cantidad = sc.nextInt();
            sc.nextLine();

            usuarioActual.getCarrito().agregarProducto(productos[idx-1], cantidad);
            System.out.println("Producto agregado al carrito.");
        }
    }

    private void verCarrito() {
        usuarioActual.getCarrito().mostrarCarrito();

        if (usuarioActual.getCarrito().getContador() > 0) {
            System.out.println(
                        "\n1.Modificar cantidad\n" +
                        "2.Eliminar producto\n" +
                        "3.Volver"
            );
            System.out.print("Seleccione: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {
                System.out.print("Número del producto a modificar: ");
                int idx = sc.nextInt();
                sc.nextLine();

                if (idx < 1 || idx > usuarioActual.getCarrito().getContador()) {
                    System.out.println("Número inválido.");
                    return;
                }

                System.out.print("Nueva cantidad: ");
                int nuevaCantidad = sc.nextInt();
                sc.nextLine();

                Producto producto = usuarioActual.getCarrito().getProductos()[idx-1];
                usuarioActual.getCarrito().modificarCantidad(producto, nuevaCantidad);
                System.out.println("Cantidad modificada");

            } else if (opcion == 2) {
                System.out.print("Numero del producto a eliminar: ");
                int indiceProducto = sc.nextInt();
                sc.nextLine();

                if (indiceProducto < 1 || indiceProducto > usuarioActual.getCarrito().getContador()) {
                    System.out.println("Numero inválido");
                    return;
                }

                Producto producto = usuarioActual.getCarrito().getProductos()[indiceProducto-1];
                usuarioActual.getCarrito().eliminarProducto(producto);
                System.out.println("Producto eliminado");
            }
        }
    }

    private void realizarPedido() {
        if (usuarioActual.getCarrito().getContador() == 0) {
            System.out.println("El carrito esta vacío");
            return;
        }

        usuarioActual.getCarrito().mostrarCarrito();

        System.out.print("\nConfirmar pedido? s/n: ");
        String respuesta = sc.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            Pedido pedido = new Pedido();
            pedido.setUsuario(usuarioActual);
            pedido.setProductos(usuarioActual.getCarrito().convertirAProductosCarrito());
            pedido.setPrecioTotal(usuarioActual.getCarrito().calcularTotal());
            pedido.setGastosEnvio(usuarioActual.getCarrito().calcularGastosEnvio());
            pedido.setEstadoPedido(EstadoPedido.CONFIRMADO);
            pedido.setFecha(java.time.LocalDate.now().toString());

            //guardar pedido en usuario
            usuarioActual.agregarPedido(pedido);

            //guardar pedido en lista global
            if (numPedidos < todosPedidos.length) {
                todosPedidos[numPedidos] = pedido;
                numPedidos++;
            }

            //vaciar carrito
            usuarioActual.getCarrito().setContador(0);

            System.out.println("Pedido realizado. Estado: CONFIRMADO");
        }
    }

    private void verMisPedidos() {
        System.out.println("\nMIS PEDIDOS");

        if (usuarioActual.getNumPedidos() == 0) {
            System.out.println("No tienes pedidos.");
            return;
        }

        for (int i = 0; i < usuarioActual.getNumPedidos(); i++) {
            Pedido pedido = usuarioActual.getPedidos()[i];
            System.out.printf("\nPedido %d - Fecha: %s - Estado: %s\n",
                    i+1, pedido.getFecha(), pedido.getEstadoPedido());
            System.out.printf("Total: %.2f€ 'Envío: %.2f€'\n",
                    pedido.getPrecioTotal(), pedido.getGastosEnvio());
        }
    }

    private void verTodosPedidos() {
        System.out.println("\nTODOS LOS PEDIDOS");

        if (numPedidos == 0) {
            System.out.println("No hay pedidos.");
            return;
        }

        for (int i = 0; i < numPedidos; i++) {
            Pedido pedido = todosPedidos[i];
            System.out.printf("\nPedido %d - Usuario: %s - Estado: %s\n",
                    i+1, pedido.getUsuario().getNombreUsuario(), pedido.getEstadoPedido());
            System.out.printf("Fecha: %s - Total: %.2f€\n",
                    pedido.getFecha(), pedido.getPrecioTotal());
        }
    }

    private void cambiarEstadoPedido() {
        verTodosPedidos();

        if (numPedidos == 0) return;

        System.out.print("\nSeleccione el numero del pedido: ");
        int indiceProducto = sc.nextInt();
        sc.nextLine();

        if (indiceProducto < 1 || indiceProducto > numPedidos) {
            System.out.println("Numero inválido.");
            return;
        }

        Pedido pedido = todosPedidos[indiceProducto-1];
        System.out.printf("Estado actual: %s\n", pedido.getEstadoPedido());

        System.out.println("Nuevo estado:");
        System.out.println("1. CONFIRMADO");
        System.out.println("2. ENVIADO");
        System.out.println("3. RECIBIDO");
        System.out.print("Seleccione: ");

        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                pedido.setEstadoPedido(EstadoPedido.CONFIRMADO);
                break;
            case 2:
                pedido.setEstadoPedido(EstadoPedido.ENVIADO);
                break;
            case 3:
                pedido.setEstadoPedido(EstadoPedido.RECIBIDO);
                break;
        }

        System.out.println("Estado actualizado.");
    }

    private void marcarPedidoRecibido() {
        System.out.println("\nMARCAR PEDIDO COMO RECIBIDO");

        if (usuarioActual.getNumPedidos() == 0) {
            System.out.println("No tienes pedidos.");
            return;
        }

        System.out.println("Pedidos enviados:");
        int contador = 0;
        for (int i = 0; i < usuarioActual.getNumPedidos(); i++) {
            Pedido pedido = usuarioActual.getPedidos()[i];
            if (pedido.getEstadoPedido() == EstadoPedido.ENVIADO) {
                System.out.printf("%d. Pedido del %s - Total: %.2f€\n",
                        contador+1, pedido.getFecha(), pedido.getPrecioTotal());
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("No tienes pedidos enviados.");
            return;
        }

        System.out.print("Seleccione el pedido recibido: ");
        int idx = sc.nextInt();
        sc.nextLine();

        contador = 0;
        for (int i = 0; i < usuarioActual.getNumPedidos(); i++) {
            Pedido pedido = usuarioActual.getPedidos()[i];
            if (pedido.getEstadoPedido() == EstadoPedido.ENVIADO) {
                contador++;
                if (contador == idx) {
                    pedido.setEstadoPedido(EstadoPedido.RECIBIDO);
                    System.out.println("Pedido marcado como RECIBIDO.");
                    return;
                }
            }
        }

        System.out.println("Pedido no encontrado.");
    }
}