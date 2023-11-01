package ejercicio2.servicio.carrito;

import ejercicio2.Main;
import ejercicio2.domain.Carrito;
import ejercicio2.domain.Pedido;
import ejercicio2.domain.Producto;
import ejercicio2.servicio.pedido.PedidoServicio;
import ejercicio2.servicio.pedido.PedidoServicioImpl;
import ejercicio2.servicio.stock.StockServicio;
import ejercicio2.servicio.stock.StockServicioImpl;
import java.util.Objects;

public class CarritoServicioImpl implements CarritoServicio{

    public static final String MENSAJE_CANTIDAD_MINIMA_TEMPLATE =  "Debe ingresar una cantidad mayor que 0";
    public static final int CANTIDAD_MINIMA = 1;
    public static final String MENSAJE_ALERTA_CANTIDAD_DISPONIBLE_TEMPLATE = "Solo quedan %d productos en stock";
    public static final String MENSAJE_CANTIDAD_MINIMA_MODIFICADA_TEMPLATE = "No hay tantos productos en el carrito";
    public static final String MENSAJE_CARRITO_VACIO = "El carrito debe tener al menos 1 producto.";
    private StockServicio stockServicio;
    private PedidoServicio pedidoServicio;
    private Carrito carritoEnCurso;

    public CarritoServicioImpl(Carrito carritoEnCurso, PedidoServicio pedidoServicio) {
        this.stockServicio = new StockServicioImpl();
        this.carritoEnCurso = carritoEnCurso;
        this.pedidoServicio = pedidoServicio;
    }

    @Override
    public void agregarProducto(Producto prod, int qty) {
        if (prod.getStock() < qty) {
            System.out.printf((MENSAJE_ALERTA_CANTIDAD_DISPONIBLE_TEMPLATE) + "%n", prod.getStock());
            return;
        } else {
            if (carritoEnCurso.getProducts().containsKey(prod)) {
                if (carritoEnCurso.getProducts().get(prod) + qty < 0) {
                    System.out.println(MENSAJE_CANTIDAD_MINIMA_MODIFICADA_TEMPLATE);
                    return;
                }
            } else {
                if (qty <= CANTIDAD_MINIMA) {
                    System.out.println(MENSAJE_CANTIDAD_MINIMA_TEMPLATE);
                    return;
                }
            }
            if (Objects.isNull(carritoEnCurso.getPedido())){
                Pedido pedido = pedidoServicio.crearPedido(carritoEnCurso);
                carritoEnCurso.setPedido(pedido);
            }
            stockServicio.modificarCantidad(prod.getId(), qty);

            Integer nuevaCantidad = qty + (Objects.isNull(carritoEnCurso.getProducts().get(prod)) ? 0 : carritoEnCurso.getProducts().get(prod));
            if (nuevaCantidad.equals(0)){
                carritoEnCurso.getProducts().remove(prod);
            } else {
                carritoEnCurso.getProducts().put(prod, nuevaCantidad);
            }
        }
    }

    @Override
    public boolean confirmarCarrito() {
        if (this.carritoEnCurso.getProducts().isEmpty()){
            System.out.println(MENSAJE_CARRITO_VACIO);
            return false;
        }else {
            pedidoServicio.actualizarPedidoAPagado(carritoEnCurso);
            carritoEnCurso.getCliente().setCarrito();
            this.carritoEnCurso = this.carritoEnCurso.getCliente().getCarrito();
            return true;
        }
    }
}
