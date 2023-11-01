package ejercicio2.servicio.producto;

import ejercicio2.domain.Producto;

import java.util.Optional;

public interface ProductoServicio {
    Optional<Producto> getProductById(Long id);
}
