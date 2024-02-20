
package model;

/**
 *
 * @author Salvador Moreno Sánchez
 */
public class Producto {
    
    private String nombreProducto;
    private double extensionProducto;
    private double rendimientoHectarea;
    private double valorReferencia; // cada producto tiene un valor de referencia por 
                                    // kilogramo libre de impuestos (sin IVA). 
                                    // Por ejemplo, el precio del algodón podría fijarse
                                    // en torno a los 80 céntimos de euro por kilogramo.
    private String grupoEspecifico; // Perecedero / No perecedero
    
    
    public Producto(String nombreProducto, double extensionProducto, double rendimientoHectarea, double valorReferencia, String grupoEspecifico) {
        this.nombreProducto = nombreProducto;
        this.extensionProducto = extensionProducto;
        this.rendimientoHectarea = rendimientoHectarea; // coger dato de tabla de correspondencia
        this.valorReferencia = valorReferencia; // coger dato de tabla de correspondencia
        this.grupoEspecifico = grupoEspecifico; // coger dato de tabla de correspondencia
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getExtensionProducto() {
        return extensionProducto;
    }

    public void setExtensionProducto(double extensionProducto) {
        this.extensionProducto = extensionProducto;
    }

    public double getRendimientoHectarea() {
        return rendimientoHectarea;
    }

    public void setRendimientoHectarea(double rendimientoHectarea) {
        this.rendimientoHectarea = rendimientoHectarea;
    }

    public double getValorReferencia() {
        return valorReferencia;
    }

    public void setValorReferencia(double valorReferencia) {
        this.valorReferencia = valorReferencia;
    }

    public String getGrupoEspecifico() {
        return grupoEspecifico;
    }

    public void setGrupoEspecifico(String grupoEspecifico) {
        this.grupoEspecifico = grupoEspecifico;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombreProducto=" + nombreProducto + ", extensionProducto=" + extensionProducto + ", rendimientoHectarea=" + rendimientoHectarea + ", valorReferencia=" + valorReferencia + ", grupoEspecifico=" + grupoEspecifico + '}';
    }
    
    
    
}
