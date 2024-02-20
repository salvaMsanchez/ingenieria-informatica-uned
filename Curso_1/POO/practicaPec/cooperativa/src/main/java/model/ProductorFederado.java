
package model;

import java.util.ArrayList;

/**
 *
 * @author Salvador Moreno Sánchez
 */
public class ProductorFederado {
    
    private String nombreProducto;
    private double extensionTotal;
    private ArrayList<Productor> pequenosProductores;

    public ProductorFederado(String nombreProducto, double extensionTotal, ArrayList<Productor> pequenosProductores) {
        this.nombreProducto = nombreProducto;
        this.extensionTotal = extensionTotal;
        this.pequenosProductores = pequenosProductores;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getExtensionTotal() {
        return extensionTotal;
    }

    public void setExtensionTotal(double extensionTotal) {
        this.extensionTotal = extensionTotal;
    }

    public ArrayList<Productor> getPequenosProductores() {
        return pequenosProductores;
    }

    public void setPequenosProductores(ArrayList<Productor> pequenosProductores) {
        this.pequenosProductores = pequenosProductores;
    }
    
    public void almacenarPequenoProductor(Productor productor) {
        pequenosProductores.add(productor);
    }

    @Override
    public String toString() {
        return "ProductorFederado{" + "nombreProducto=" + nombreProducto + ", extensionTotal=" + extensionTotal + ", pequenosProductores=" + pequenosProductores + '}';
    }
    
    
}
