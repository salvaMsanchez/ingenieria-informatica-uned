
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import model.ConsumidorFinal;
import model.Distribuidor;
import model.Logistica;
import model.Pedido;
import model.Producto;
import model.Productor;
import model.ProductorFederado;

/**
 *
 * @author Salvador Moreno Sánchez
 */
public class BBDD {
    
    private ArrayList<Productor> grandesProductores;
    private ArrayList<Productor> pequenosProductores;
    private ArrayList<ProductorFederado> federadosProductores;
    private ArrayList<Logistica> logistica;
    private ArrayList<Distribuidor> distribuidores;
    private ArrayList<ConsumidorFinal> consumidoresFinales;

    public BBDD(ArrayList<Productor> grandesProductores, ArrayList<Productor> pequenosProductores, ArrayList<ProductorFederado> federadosProductores, ArrayList<Logistica> logistica, ArrayList<Distribuidor> distribuidores, ArrayList<ConsumidorFinal> consumidoresFinales) {
        this.grandesProductores = grandesProductores;
        this.pequenosProductores = pequenosProductores;
        this.federadosProductores = federadosProductores;
        this.logistica = logistica;
        this.distribuidores = distribuidores;
        this.consumidoresFinales = consumidoresFinales;
    }

    public ArrayList<Productor> getGrandesProductores() {
        return grandesProductores;
    }

    public void setGrandesProductores(ArrayList<Productor> grandesProductores) {
        this.grandesProductores = grandesProductores;
    }

    public ArrayList<Productor> getPequenosProductores() {
        return pequenosProductores;
    }

    public void setPequenosProductores(ArrayList<Productor> pequenosProductores) {
        this.pequenosProductores = pequenosProductores;
    }

    public ArrayList<ProductorFederado> getFederadosProductores() {
        return federadosProductores;
    }

    public void setFederadosProductores(ArrayList<ProductorFederado> federadosProductores) {
        this.federadosProductores = federadosProductores;
    }
    
    public ArrayList<Logistica> getLogistica() {
        return logistica;
    }
    
    public void setLogistica(ArrayList<Logistica> logistica) {
        this.logistica = logistica;
    }

    public ArrayList<Distribuidor> getDistribuidores() {
        return distribuidores;
    }

    public void setDistribuidores(ArrayList<Distribuidor> distribuidores) {
        this.distribuidores = distribuidores;
    }

    public ArrayList<ConsumidorFinal> getConsumidoresFinales() {
        return consumidoresFinales;
    }

    public void setConsumidoresFinales(ArrayList<ConsumidorFinal> consumidoresFinales) {
        this.consumidoresFinales = consumidoresFinales;
    }
    
    
    
    
    // MÉTODOS PARA CARGA DE DATOS PREVIOS - PERSISTENCIA DE DATOS
    
    public void cargaProductorBBDD() {
    
        File archivo = new File("productores.txt");
        
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); // Java infiere el tipo
            var lectura = entrada.readLine();
            
            while(lectura != null) {
                ArrayList<String> datosProductor = new ArrayList<>();
                boolean hayProductor = false;
                //System.out.println("lectura = " + lectura);
                lectura = entrada.readLine();
                if (lectura == null) {
                    break; // Salir del ciclo si es null para evitar NullPointerException
                }
                String[] datosFraccionados = lectura.split(",");
                for (String dato : datosFraccionados) {
                    if(dato.equals("productor")) {
                        hayProductor = true;
                        for(int i=0; i<datosFraccionados.length; i++) {
                            datosProductor.add(datosFraccionados[i]);
                        }
                    }
                }
                if(hayProductor) {
                    // Creación del objeto Productor
                    ArrayList<Producto> listaVaciaProductos = new ArrayList<>();
                    Productor cargaProductor = new Productor(datosProductor.get(0), datosProductor.get(1), datosProductor.get(2), 0, listaVaciaProductos);
                    // ---
                    
                    boolean fin = false;
                    int indiceDatosProductos = 4;
                    while(!fin) {
                        if(datosProductor.get(indiceDatosProductos).equals("fin")) {
                            //System.out.println("No hay más productos que cargar en este productor en BBDD");
                            fin = true;
                        } else {
                            String[] datosTablaCorrespondeciaProducto = GestorArchivos.extraerDatosTablaCorrespondenciaProductos(datosProductor.get(indiceDatosProductos));
                            Producto cargaProducto = new Producto(datosProductor.get(indiceDatosProductos), Double.parseDouble(datosProductor.get(indiceDatosProductos + 1)), Double.parseDouble(datosTablaCorrespondeciaProducto[1]), Double.parseDouble(datosTablaCorrespondeciaProducto[2]), datosTablaCorrespondeciaProducto[3]);
                            cargaProductor.almacenarProducto(cargaProducto);
                            cargaProductor.setExtensionTotal(cargaProductor.getExtensionTotal() + Double.parseDouble(datosProductor.get(indiceDatosProductos + 1)));
                            
                        }
                        indiceDatosProductos += 2;
                    }
                    
                    // IF EXTENSION TOTAL -> ALMACENARGRANPRODUCTOR O PEQUENOPRODUCTOR
                    if(cargaProductor.getExtensionTotal() <= 5.0) {
                        almacenarPequenoProductor(cargaProductor);
                    } else if(cargaProductor.getExtensionTotal() > 5.0) {
                        almacenarGranProductor(cargaProductor);
                    }
                    
                }

                
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
    public void cargarProductorFederadoBBDD() {
        
        File archivo = new File("productoresFederados.txt");
        
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); // Java infiere el tipo
            var lectura = entrada.readLine();
            
            while(lectura != null) {
                ArrayList<String> datosProductorFederado = new ArrayList<>();
                boolean hayProductorFederado = false;
                //System.out.println("lectura = " + lectura);
                lectura = entrada.readLine();
                if (lectura == null) {
                    break; // Salir del ciclo si es null para evitar NullPointerException
                }
                String[] datosFraccionados = lectura.split(",");
                for (String dato : datosFraccionados) {
                    if(!dato.equals("producto")) {
                        hayProductorFederado = true;
                        datosProductorFederado.add(datosFraccionados[0]);
                        datosProductorFederado.add(datosFraccionados[datosFraccionados.length - 1]);
                    }
                }
                if(hayProductorFederado) {
                    
                    ArrayList<String> emailsProductorFederado = GestorArchivos.devolverEmailsEnLineaLectura(lectura);
                    
                    ArrayList<Productor> listaVaciaProductoresFederados = new ArrayList<>();
                    ProductorFederado cargaProductorFederado = new ProductorFederado(datosProductorFederado.get(0), Double.parseDouble(datosProductorFederado.get(datosFraccionados.length - 1)), listaVaciaProductoresFederados);
                    
                    // Recorre el ArrayList pequenosProductores
                    for (Productor productor : pequenosProductores) {
                        // Verifica si el email del productor está en el ArrayList de emails
                        for(String email : emailsProductorFederado) {
                            if(email.equals(productor.getEmail())) {
                                cargaProductorFederado.almacenarPequenoProductor(productor);
                            }
                        }
                        
                    }
                    
                    almacenarFederadoProductor(cargaProductorFederado);
                }
                
                
                
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
    public void cargaLogisticaBBDD() {
        
        File archivo = new File("logistica.txt");
        
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); // Java infiere el tipo
            var lectura = entrada.readLine();
            
            while(lectura != null) {
                ArrayList<String> datosLogistica = new ArrayList<>();
                boolean hayLogistica = false;
                //System.out.println("lectura = " + lectura);
                lectura = entrada.readLine();
                if (lectura == null) {
                    break; // Salir del ciclo si es null para evitar NullPointerException
                }
                String[] datosFraccionados = lectura.split(",");
                for (String dato : datosFraccionados) {
                    if(dato.equals("logistica")) {
                        hayLogistica = true;
                        for(int i=0; i<8; i++) {
                            datosLogistica.add(datosFraccionados[i]);
                        }
                    }
                }
                if(hayLogistica) {
                    Logistica cargaLogistica = new Logistica(datosLogistica.get(0), datosLogistica.get(1), datosLogistica.get(2), Double.parseDouble(datosLogistica.get(4)), Double.parseDouble(datosLogistica.get(5)), Double.parseDouble(datosLogistica.get(6)), Double.parseDouble(datosLogistica.get(7)), 0);
                    almacenarLogistica(cargaLogistica);
                }
                
                
                
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
    public void cargaDistribuidorBBDD() {
        
        File archivo = new File("consumidores.txt");
        
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); // Java infiere el tipo
            var lectura = entrada.readLine();
            
            while(lectura != null) {
                ArrayList<String> datosDistribuidor = new ArrayList<>();
                boolean hayDistribuidor = false;
                //System.out.println("lectura = " + lectura);
                lectura = entrada.readLine();
                if (lectura == null) {
                    break; // Salir del ciclo si es null para evitar NullPointerException
                }
                String[] datosFraccionados = lectura.split(",");
                for (String dato : datosFraccionados) {
                    if(dato.equals("distribuidor")) {
                        hayDistribuidor = true;
                        for(int i=0; i<3; i++) {
                            datosDistribuidor.add(datosFraccionados[i]);
                        }
                    }
                }
                if(hayDistribuidor) {
                    ArrayList<Pedido> listaVaciaPedidos = new ArrayList<>();
                    Distribuidor cargaDistribuidor = new Distribuidor(datosDistribuidor.get(0), datosDistribuidor.get(1), datosDistribuidor.get(2), listaVaciaPedidos);
                    almacenarDistribuidor(cargaDistribuidor);
                }
                
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
    public void cargaConsumidorFinalBBDD() {
        
        File archivo = new File("consumidores.txt");
        
        try {
            var entrada = new BufferedReader(new FileReader(archivo)); // Java infiere el tipo
            var lectura = entrada.readLine();
            
            while(lectura != null) {
                ArrayList<String> datosConsumidorFinal = new ArrayList<>();
                boolean hayConsumidorFinal = false;
                //System.out.println("lectura = " + lectura);
                lectura = entrada.readLine();
                if (lectura == null) {
                    break; // Salir del ciclo si es null para evitar NullPointerException
                }
                String[] datosFraccionados = lectura.split(",");
                for (String dato : datosFraccionados) {
                    if(dato.equals("consumidorFinal")) {
                        hayConsumidorFinal = true;
                        for(int i=0; i<3; i++) {
                            datosConsumidorFinal.add(datosFraccionados[i]);
                        }
                    }
                }
                if(hayConsumidorFinal) {
                    ArrayList<Pedido> listaVaciaPedidos = new ArrayList<>();
                    ConsumidorFinal cargaConsumidorFinal = new ConsumidorFinal(datosConsumidorFinal.get(0), datosConsumidorFinal.get(1), datosConsumidorFinal.get(2), listaVaciaPedidos);
                    almacenarConsumidorFinal(cargaConsumidorFinal);
                }
                
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
    }

    
    // MÉTODOS PARA EL ALMACENAJE DE OBJETOS EN BASE DE DATOS
    
    public void almacenarGranProductor(Productor productor) {
        grandesProductores.add(productor);
    }
    
    public void almacenarPequenoProductor(Productor productor) {
        pequenosProductores.add(productor);
    }
    
    public void almacenarFederadoProductor(ProductorFederado federadoProductor) {
        federadosProductores.add(federadoProductor);
    }
    
    public void almacenarLogistica(Logistica empresaLogistica) {
        logistica.add(empresaLogistica);
    }
    
    public void almacenarDistribuidor(Distribuidor distribuidor) {
        distribuidores.add(distribuidor);
    }
    
    public void almacenarPedidoDistribuidorConEmail(Pedido pedido, String email) {
        
        for (Distribuidor distribuidor : distribuidores) {
            // Recorre la lista de Producto de cada Productor
            if(distribuidor.getEmail().equals(email)) {
                distribuidor.almacenarPedido(pedido);
            }
            
        }
        
    }
    
    public void almacenarConsumidorFinal(ConsumidorFinal consumidorFinal) {
        consumidoresFinales.add(consumidorFinal);
    }
    
    public void listarProductosAlmacenados() {
        
        ArrayList<String> listaProductosPrinteados = new ArrayList<>();
        
        for (ProductorFederado productorFederado : federadosProductores) {
            // Recorre la lista de Producto de cada Productor
            System.out.println(productorFederado.getNombreProducto());
            listaProductosPrinteados.add(productorFederado.getNombreProducto());
            
        }
        
        // Recorre la lista de Productor
        for (Productor productor : grandesProductores) {
            // Recorre la lista de Producto de cada Productor
            for (Producto producto : productor.getProductos()) {
                // Imprime el nombre del Producto
                if(!listaProductosPrinteados.contains(producto.getNombreProducto())) {
                    listaProductosPrinteados.add(producto.getNombreProducto());
                    System.out.println(producto.getNombreProducto());
                }
            }
        }
        
        for (Productor productor : pequenosProductores) {
            // Recorre la lista de Producto de cada Productor
            for (Producto producto : productor.getProductos()) {
                // Imprime el nombre del Producto
                if(!listaProductosPrinteados.contains(producto.getNombreProducto())) {
                    listaProductosPrinteados.add(producto.getNombreProducto());
                    System.out.println(producto.getNombreProducto());
                }
            }
        }
        
        
        
    }

    @Override
    public String toString() {
        return "BBDD{" + "grandesProductores=" + grandesProductores + ", \npequenosProductores=" + pequenosProductores + ", \nfederadosProductores=" + federadosProductores + ", \nlogistica=" + logistica + ", \ndistribuidores=" + distribuidores + ", \nconsumidoresFinales=" + consumidoresFinales + '}';
    }

    

    
    
    
    
}
