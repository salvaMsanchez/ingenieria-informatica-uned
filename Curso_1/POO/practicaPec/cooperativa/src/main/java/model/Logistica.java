
package model;

/**
 *
 * @author Salvador Moreno Sánchez
 */
public class Logistica {
    
    private String email;
    private String nombre;
    private String contrasena;
    private double precioKmPerecederoGranLogistica;
    private double precioKmPerecederoPequenaLogistica;
    private double precioKmNoPerecederoGranLogistica;
    private double precioKmNoPerecederoPequenaLogistica;
    private double beneficios;

    public Logistica(String email, String nombre, String contrasena, double precioKmPerecederoGranLogistica, double precioKmPerecederoPequenaLogistica, double precioKmNoPerecederoGranLogistica, double precioKmNoPerecederoPequenaLogistica, double beneficios) {
        this.email = email;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.precioKmPerecederoGranLogistica = precioKmPerecederoGranLogistica;
        this.precioKmPerecederoPequenaLogistica = precioKmPerecederoPequenaLogistica;
        this.precioKmNoPerecederoGranLogistica = precioKmNoPerecederoGranLogistica;
        this.precioKmNoPerecederoPequenaLogistica = precioKmNoPerecederoPequenaLogistica;
        this.beneficios = beneficios;
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

    public double getPrecioKmPerecederoGranLogistica() {
        return precioKmPerecederoGranLogistica;
    }

    public void setPrecioKmPerecederoGranLogistica(double precioKmPerecederoGranLogistica) {
        this.precioKmPerecederoGranLogistica = precioKmPerecederoGranLogistica;
    }

    public double getPrecioKmPerecederoPequenaLogistica() {
        return precioKmPerecederoPequenaLogistica;
    }

    public void setPrecioKmPerecederoPequenaLogistica(double precioKmPerecederoPequenaLogistica) {
        this.precioKmPerecederoPequenaLogistica = precioKmPerecederoPequenaLogistica;
    }

    public double getPrecioKmNoPerecederoGranLogistica() {
        return precioKmNoPerecederoGranLogistica;
    }

    public void setPrecioKmNoPerecederoGranLogistica(double precioKmNoPerecederoGranLogistica) {
        this.precioKmNoPerecederoGranLogistica = precioKmNoPerecederoGranLogistica;
    }

    public double getPrecioKmNoPerecederoPequenaLogistica() {
        return precioKmNoPerecederoPequenaLogistica;
    }

    public void setPrecioKmNoPerecederoPequenaLogistica(double precioKmNoPerecederoPequenaLogistica) {
        this.precioKmNoPerecederoPequenaLogistica = precioKmNoPerecederoPequenaLogistica;
    }
    
    public double getBeneficios() {
        return beneficios;
    }
    
    public void setBeneficios(double beneficios) {
        this.beneficios = beneficios;
    }

    @Override
    public String toString() {
        return "Logistica{" + "email=" + email + ", nombre=" + nombre + ", contrasena=" + contrasena + ", precioKmPerecederoGranLogistica=" + precioKmPerecederoGranLogistica + ", precioKmPerecederoPequenaLogistica=" + precioKmPerecederoPequenaLogistica + ", precioKmNoPerecederoGranLogistica=" + precioKmNoPerecederoGranLogistica + ", precioKmNoPerecederoPequenaLogistica=" + precioKmNoPerecederoPequenaLogistica + ", beneficios=" + beneficios + '}';
    }
    
    
    
}
