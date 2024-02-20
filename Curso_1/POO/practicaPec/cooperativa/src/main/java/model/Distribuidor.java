
package model;

import java.util.ArrayList;

/**
 *
 * @author Salvador Moreno Sánchez
 */
public class Distribuidor extends Consumidor {
    
    private int porcentajeAnadido = 5;
    private int cantidadComprarMaxProducto = 1000;

    public Distribuidor(String email, String nombre, String contrasena, ArrayList<Pedido> pedidos) {
        super(email, nombre, contrasena, pedidos);
    }

    @Override
    public String toString() {
        return "Distribuidor{" + "email=" + getEmail() + ", nombre=" + getNombre() + ", contraseña=" + getContrasena() + ", porcentajeAnadido=" + porcentajeAnadido + ", cantidadComprarMaxProducto=" + cantidadComprarMaxProducto + ", pedidos=" + getPedidos() + '}';
    }

    
    
    
    
}
