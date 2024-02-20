
package model;

import java.util.ArrayList;

/**
 *
 * @author Salvador Moreno Sánchez
 */
public class Productor {
    
    private String email;
    private String nombre;
    private String contrasena;
    private double extensionTotal;
    private ArrayList<Producto> productos;

    public Productor(String email, String nombre, String contrasena, double extensionTotal, ArrayList<Producto> productos) {
        this.email = email;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.extensionTotal = extensionTotal;
        this.productos = productos;
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

    public double getExtensionTotal() {
        return extensionTotal;
    }

    public void setExtensionTotal(double extensionTotal) {
        this.extensionTotal = extensionTotal;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    
    public void almacenarProducto(Producto producto) {
        productos.add(producto);
    }

    @Override
    public String toString() {
        return "Productor{" + "email=" + email + ", nombre=" + nombre + ", contrasena=" + contrasena + ", extensionTotal=" + extensionTotal + ", productos=" + productos + '}';
    }
    
    
    
}
