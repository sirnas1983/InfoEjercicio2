package ejercicio2.servicio.stock;

import ejercicio2.domain.Producto;

public interface StockServicio {
    void modificarCantidad(Long idProducto, int qty);
}
