
package model;

/**
 *
 * @author Salvador Moreno Sanchez
 */
public class Pedido {
    
    private int numeroPedido;
    private String nombreProducto;
    private String tipoProducto;
    private int distanciaPedido;
    private int cantidadProducto;
    private double valorReferenciaProducto;
    private double granLogistica;
    private double pequenaLogistica;
    private String poblacion;

    public Pedido(int numeroPedido, String nombreProducto, String tipoProducto, int distanciaPedido, int cantidadProducto, double valorReferenciaProducto, double granLogistica, double pequenaLogistica, String poblacion) {
        this.numeroPedido = numeroPedido;
        this.nombreProducto = nombreProducto;
        this.tipoProducto = tipoProducto;
        this.distanciaPedido = distanciaPedido;
        this.cantidadProducto = cantidadProducto;
        this.valorReferenciaProducto = valorReferenciaProducto;
        this.granLogistica = granLogistica;
        this.pequenaLogistica = pequenaLogistica;
        this.poblacion = poblacion;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public int getDistanciaPedido() {
        return distanciaPedido;
    }

    public void setDistanciaPedido(int distanciaPedido) {
        this.distanciaPedido = distanciaPedido;
    }

    public double getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public double getValorReferenciaProducto() {
        return valorReferenciaProducto;
    }

    public void setValorReferenciaProducto(double valorReferenciaProducto) {
        this.valorReferenciaProducto = valorReferenciaProducto;
    }

    public double getGranLogistica() {
        return granLogistica;
    }

    public void setGranLogistica(double granLogistica) {
        this.granLogistica = granLogistica;
    }

    public double getPequenaLogistica() {
        return pequenaLogistica;
    }

    public void setPequenaLogistica(double pequenaLogistica) {
        this.pequenaLogistica = pequenaLogistica;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    

    

    @Override
    public String toString() {
        return "Pedido{" + "numeroPedido=" + numeroPedido + ", nombreProducto=" + nombreProducto + ", tipoProducto=" + tipoProducto + ", distanciaPedido=" + distanciaPedido + ", cantidadProducto=" + cantidadProducto + '}';
    }
    
    
    
}
