package ejercicio2.domain;

import ejercicio2.enums.EstadoPedido;
import ejercicio2.servicio.menu.MenuPedidoImpl;

public class Pedido {
    private Long id;
    private Cliente cliente;
    private Carrito carrito;
    private String DIVISOR_GUION_MEDIO = "-".repeat(35);
    private EstadoPedido estado;

    public Pedido() {

    }

    public Pedido(Long id, Cliente cliente, Carrito carrito, EstadoPedido estado) {
        this.id = id;
        this.cliente = cliente;
        this.carrito = carrito;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.DIVISOR_GUION_MEDIO + "\n");
        stringBuilder.append(String.format("PEDIDO N° %d\n", this.getId()));
        stringBuilder.append(String.format("ESTADO: %s\n", this.getEstado().toString()));
        stringBuilder.append(this.carrito);
        return stringBuilder.toString();
    }
}
