
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import model.ConsumidorFinal;
import model.Distribuidor;
import model.Logistica;
import model.Pedido;

/**
 *
 * @author Salvador Moreno Sanchez
 */
public class DatosEstadisticos {
    
    public void ventasTotalesProductos(ArrayList<Distribuidor> distribuidores, ArrayList<ConsumidorFinal> consumidoresFinales) {
    
        HashMap<String, Integer> ventasProductos = new HashMap<String, Integer>();
        
        for (Distribuidor distribuidor : distribuidores) {
            ArrayList<Pedido> pedidos = distribuidor.getPedidos();
            for (Pedido pedido : pedidos) {
                String nombreProducto = pedido.getNombreProducto();
                if (ventasProductos.containsKey(nombreProducto)) {
                    int valorKey = ventasProductos.get(nombreProducto);
                    valorKey = valorKey + 1;
                    ventasProductos.replace(nombreProducto, valorKey);
                } else {
                    ventasProductos.put(nombreProducto, 1);
                }
                
            }
        }
        
        for (ConsumidorFinal consumidorFinal : consumidoresFinales) {
            ArrayList<Pedido> pedidos = consumidorFinal.getPedidos();
            for (Pedido pedido : pedidos) {
                String nombreProducto = pedido.getNombreProducto();
                if (ventasProductos.containsKey(nombreProducto)) {
                    int valorKey = ventasProductos.get(nombreProducto);
                    valorKey = valorKey + 1;
                    ventasProductos.replace(nombreProducto, valorKey);
                } else {
                    ventasProductos.put(nombreProducto, 1);
                }
                
            }
        }
        
        System.out.println("++++++++++++++++++++++++++++");
        System.out.println("+ VENTAS TOTALES PRODUCTOS +");
        System.out.println("++++++++++++++++++++++++++++");
        
        ventasProductos.forEach((producto, numVentas) -> {
            System.out.printf("- %s: %d veces vendido\n", producto, numVentas);
        });
    
    }
    
    public void importesEmpresasLogistica(ArrayList<Logistica> logistica) {
        
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+ IMPORTES TOTALES EMPRESAS DE LOGÍSTICA +");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        
        int indice = 1;
        for (Logistica empresaLogistica : logistica) {
            System.out.println("");
            System.out.println("+++++++++++++++");
            System.out.printf("%d. Empresa: %s\n", indice, empresaLogistica.getNombre());
            System.out.println("+++++++++++++++");
            System.out.printf("- Beneficios actuales: %.2f €\n", empresaLogistica.getBeneficios());
            System.out.println("");
            indice ++;
        }
        
    }
    
}
