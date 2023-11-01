package ejercicio2.servicio.stock;

import ejercicio2.basededatos.BdProductos;
import ejercicio2.domain.Producto;

public class StockServicioImpl implements StockServicio{
    @Override
    public void modificarCantidad(Long idProducto, int qty) {
        int cantidadActual = BdProductos.getProductById(idProducto).getStock();
        BdProductos.getProductById(idProducto).setStock(cantidadActual - qty);
    }
}
