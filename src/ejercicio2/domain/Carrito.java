package ejercicio2.domain;

import java.util.HashMap;

public class Carrito {
    private Long id;
    private Cliente cliente;
    private HashMap<Producto, Integer> products;
    private Pedido pedido;
    public Carrito() {
    }

    public Carrito(Long id, Cliente cliente, HashMap<Producto, Integer> products, Pedido pedido) {
        this.id = id;
        this.cliente = cliente;
        this.products = products;
        this.pedido = pedido;
    }

    public Carrito(Long id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.pedido = null;
        this.products = new HashMap<>();
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

    public HashMap<Producto, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Producto, Integer> products) {
        this.products = products;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Double montoCarrito(){
        double total = 0d;
        if(!this.getProducts().isEmpty()){
            for (Producto prod : this.getProducts().keySet()) {
                total += prod.getPrecio() * this.getProducts().get(prod);
            }
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        if(this.getProducts().isEmpty()){
            texto.append("Carrito vacio");
        } else {
            for (Producto prod : this.getProducts().keySet()) {
                texto.append(String.format("Prod: %s - Cant: %d\n", prod.getNombre(), this.getProducts().get(prod)));
            }
            texto.append("-".repeat(35));
            texto.append("\n");
            texto.append(String.format("Total de compra: %.2f\n", this.montoCarrito()));
            texto.append("-".repeat(35));
            texto.append("\n");
        }
        return texto.toString();
    }
}
