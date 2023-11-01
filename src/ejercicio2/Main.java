package ejercicio2;

import ejercicio2.basededatos.BdProductos;
import ejercicio2.domain.Carrito;
import ejercicio2.domain.Cliente;
import ejercicio2.domain.Producto;
import ejercicio2.entrada.InputConsoleService;
import ejercicio2.servicio.carrito.CarritoServicio;
import ejercicio2.servicio.carrito.CarritoServicioImpl;
import ejercicio2.servicio.menu.MenuCompra;
import ejercicio2.servicio.menu.MenuCompraImpl;
import ejercicio2.servicio.menu.MenuPedido;
import ejercicio2.servicio.menu.MenuPedidoImpl;
import ejercicio2.servicio.pedido.PedidoServicio;
import ejercicio2.servicio.pedido.PedidoServicioImpl;
import ejercicio2.servicio.producto.ProductoServicioImpl;

import java.util.Optional;

public class Main {

    private static Carrito carritoEnCurso;

    public static void main(String[] args) {
        BdProductos.initProducts();

        
        //Creacion de scanner
        InputConsoleService.createScanner();
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Ian");
        cliente.setDireccion("Calle falsa 123");
        cliente.setEmail("CorreoFalso@gmail.com");
        cliente.setCarrito();
        carritoEnCurso = cliente.getCarrito();

        PedidoServicio pedidoServicio = new PedidoServicioImpl();
        CarritoServicio carritoServicio = new CarritoServicioImpl(
                cliente.getCarrito(), pedidoServicio);
        MenuCompra menuCompra = new MenuCompraImpl(new ProductoServicioImpl());
        MenuPedido menuPedido = new MenuPedidoImpl(cliente, pedidoServicio);


        int opc;
        do {
            System.out.println("1. Ver productos");
            System.out.println("2. Agregar producto al carrito");
            System.out.println("3. Comprar productos");
            System.out.println("4. Ver pedidos");
            System.out.println("0. Salir");

            System.out.println("Ingrese una opcion");
            opc = InputConsoleService.getScanner().nextInt();

            switch (opc){
                case 1:
                    getAllProducts();
                    System.out.println("Ver productos");
                    break;
                case 2:
                    Optional<Producto> productoOptional = menuCompra.seleccionarProducto();
                    if (productoOptional.isPresent()){
                        int cantidad = menuCompra.seleccionarCantidad();
                        carritoServicio.agregarProducto(productoOptional.get(),cantidad);
                    }
                    break;
                case 3:
                    boolean resultado = carritoServicio.confirmarCarrito();
                    if (Boolean.TRUE.equals(resultado)){
                        System.out.println("Carrito cerrado");
                        carritoEnCurso = cliente.getCarrito();
                    }
                    break;
                case 4:
                    menuPedido.verPedidos();
                    break;
                case 0:
                    System.out.println("Salir");
                    break;
                default:
                    System.out.println("Error! Opcion invalida");
            }

        }while (opc != 0);
        InputConsoleService.closeScanner();
    }

    public static void getAllProducts() {
        StringBuilder listaProductos = new StringBuilder();
        for (Producto p : BdProductos.productos) {
            listaProductos.append(String.format("ID[%d] %s: %s, %.2f USD | stock %d.\n", p.getId(), p.getNombre(), p.getDescription(), p.getPrecio(), p.getStock()));
        }
        System.out.println(listaProductos);
    }
}
