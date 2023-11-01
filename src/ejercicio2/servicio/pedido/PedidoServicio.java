package ejercicio2.servicio.pedido;

import ejercicio2.domain.Carrito;
import ejercicio2.domain.Cliente;
import ejercicio2.domain.Pedido;
import ejercicio2.enums.EstadoPedido;

import java.util.List;

public interface PedidoServicio {

    void actualizarPedidoAPagado(Carrito carrito);

    Pedido crearPedido(Carrito carrito);

    List<Pedido> getPedidosPorEstado(Cliente cliente, EstadoPedido estado);
}
