package ejercicio2.servicio.carrito;

import ejercicio2.domain.Producto;

public interface CarritoServicio {
    void agregarProducto(Producto prod, int qty);

    boolean confirmarCarrito();
}
