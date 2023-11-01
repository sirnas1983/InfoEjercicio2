package ejercicio2.servicio.menu;

import ejercicio2.domain.Cliente;
import ejercicio2.domain.Pedido;
import ejercicio2.entrada.InputConsoleService;
import ejercicio2.enums.EstadoPedido;
import ejercicio2.servicio.pedido.PedidoServicio;

import java.util.List;

public class MenuPedidoImpl implements MenuPedido{

    public String MENSAJE_PEDIDOS_VACIO_TEMPLATE = "No tiene pedidos realizados";
    public String MENSAJE_PEDIDOS_FILTRADOS_VACIO_TEMPLATE = "No se encuentran pedidos con ese estado";
    public String MENSAJE_SELECCION_DE_OPCIONES = "Elija el estado de los pedidos que quiera ver:";
    public String MENSAJE_INDICE_FUERA_DE_RANGO = "El indice seleccionado esta fuera de Rango.";
    public String DIVISOR_GUION_MEDIO = "-".repeat(45);
    private Cliente cliente;
    private PedidoServicio pedidoServicio;
    public MenuPedidoImpl(Cliente cliente, PedidoServicio pedidoServicio){
        this.cliente = cliente;
        this.pedidoServicio = pedidoServicio;
    }
    @Override
    public void verPedidos() {
        if(this.cliente.getPedidos().isEmpty()){
            System.out.println(MENSAJE_PEDIDOS_VACIO_TEMPLATE);
            System.out.println(DIVISOR_GUION_MEDIO);
        }
        else {
            System.out.println(MENSAJE_SELECCION_DE_OPCIONES);
            System.out.println(getEstadosMenu());
            Integer opc = InputConsoleService.getScanner().nextInt();
            EstadoPedido estado = null;
            try {
                if (!opc.equals(0)) {
                    estado = EstadoPedido.values()[opc - 1];
                }
            } catch (Exception e) {
                System.out.println(MENSAJE_INDICE_FUERA_DE_RANGO);
            }
            List<Pedido> pedidos = pedidoServicio.getPedidosPorEstado(this.cliente, estado);
            if (pedidos.isEmpty()){
                System.out.println(this.DIVISOR_GUION_MEDIO);
                System.out.println(MENSAJE_PEDIDOS_FILTRADOS_VACIO_TEMPLATE);
                System.out.println(this.DIVISOR_GUION_MEDIO);
            } else {
                for (Pedido pedido : pedidos) {
                    System.out.println(pedido);
                }
            }
        }
    }

    // Metodo que devuelve un String con todos los valores declarados en EstadoPedido
    @Override
    public String getEstadosMenu() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (EstadoPedido estadoPedido : EstadoPedido.values()) {
            stringBuilder.append(String.format("%d. %s\n", i, estadoPedido.toString()));
            i++;
        }
        stringBuilder.append("0. Ver todos");
        return stringBuilder.toString();
    }
}

