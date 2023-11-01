package ejercicio2.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente {
    private Long id;
    private String nombre;
    private String email;
    private String direccion;
    private List<Pedido> pedidos = new ArrayList<>();
    private Carrito carrito;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String email, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito() {
        long id;
        if (Objects.isNull(this.carrito)){
            id = 1L;
        } else {
            id = this.getCarrito().getId() + 1L;
        }
        this.carrito = new Carrito(id,this);
    }
}
