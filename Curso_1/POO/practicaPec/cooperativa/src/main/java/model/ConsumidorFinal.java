
package model;

import java.util.ArrayList;

/**
 *
 * @author SalvadorMORENOSANCHE
 */
public class ConsumidorFinal extends Consumidor {
    
    private int porcentajeAnadido = 15;
    private int cantidadComprarMaxProducto = 100;

    public ConsumidorFinal(String email, String nombre, String contrasena, ArrayList<Pedido> pedidos) {
        super(email, nombre, contrasena, pedidos);
    }

    @Override
    public String toString() {
        return "ConsumidorFinal{" + "email=" + getEmail() + ", nombre=" + getNombre() + ", contraseña=" + getContrasena() + ", porcentajeAnadido=" + porcentajeAnadido + ", cantidadComprarMaxProducto=" + cantidadComprarMaxProducto + ", pedidos=" + getPedidos() + '}';
    }

}
