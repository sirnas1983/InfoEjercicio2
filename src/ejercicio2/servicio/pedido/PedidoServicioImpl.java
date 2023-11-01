package ejercicio2.servicio.pedido;

import ejercicio2.Main;
import ejercicio2.domain.Carrito;
import ejercicio2.domain.Cliente;
import ejercicio2.domain.Pedido;
import ejercicio2.enums.EstadoPedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PedidoServicioImpl implements PedidoServicio {

    @Override
    public void actualizarPedidoAPagado(Carrito carrito) {
        carrito.getPedido().setEstado(EstadoPedido.PAGADO);
    }

    @Override
    public Pedido crearPedido(Carrito carrito) {

        Pedido pedido = new Pedido();

        if (carrito.getCliente().getPedidos().isEmpty()){
            pedido.setId(1L);
        }else {
            pedido.setId((long) carrito.getCliente().getPedidos().size() + 1);
        }
        pedido.setEstado(EstadoPedido.PENDIENTE);
        pedido.setCliente(carrito.getCliente());
        pedido.setCarrito(carrito);
        pedido.getCliente().getPedidos().add(pedido);
        return pedido;
    }

    @Override
    public List<Pedido> getPedidosPorEstado(Cliente cliente, EstadoPedido estado) {
        if(Objects.isNull(estado)){
            return cliente.getPedidos();
        } else {
            List<Pedido> pedidos = new ArrayList<>();
            for (Pedido pedido : cliente.getPedidos()) {
                if (pedido.getEstado().equals(estado)) {
                    pedidos.add(pedido);
                }
            }
            return pedidos;
        }
    }
}
