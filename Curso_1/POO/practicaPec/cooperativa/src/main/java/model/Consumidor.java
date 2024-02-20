
package model;

import java.util.ArrayList;

/**
 *
 * @author Salvador Moreno Sánchez
 */
public class Consumidor {
    
    private String email;
    private String nombre;
    private String contrasena;
    private ArrayList<Pedido> pedidos;

    public Consumidor(String email, String nombre, String contrasena, ArrayList<Pedido> pedidos) {
        this.email = email;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.pedidos = pedidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    public void almacenarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    
    
    
    
}
