
package view;

import controller.BBDD;
import controller.GestorArchivos;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import model.Logistica;

/**
 *
 * @author Salvador Moreno Sánchez
 */
public class Vista {
    
    public int menuInicial() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("++++++++++++++");
        System.out.println("+ HUERTO APP +");
        System.out.println("++++++++++++++");
        System.out.println("");
        System.out.println("¡Hola! ¿Qué deseas hacer?");
        System.out.println("");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarme");
        System.out.println("3. Consultar datos estadísticos de la cooperativa");
        System.out.println("4. Salir");
        System.out.println("");
        System.out.print("Escoge una opción del menú: ");
        int opcionUsuario;
        while (true) {
            String entrada = scanner.nextLine();
            try {
                opcionUsuario = Integer.parseInt(entrada);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Introduce un número válido.");
                System.out.print("Escoge una opción del menú: ");
            }
        }
        
        return opcionUsuario;
        
    }
    
    public String[] menuInicioSesion() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("++++++++++++++");
        System.out.println("+ HUERTO APP +");
        System.out.println("++++++++++++++");
        System.out.println("");
        System.out.print("Introduce tus datos: ");
        System.out.println("");
        System.out.print("Email: ");
        String emailUsuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenaUsuario = scanner.nextLine();
        
        String[] usuarioId = {emailUsuario, contrasenaUsuario};
        return usuarioId;
        
    }
    
    public String[] menuRegistro() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("++++++++++++++");
        System.out.println("+ HUERTO APP +");
        System.out.println("++++++++++++++");
        System.out.println("");
        System.out.print("Introduce tus datos para registrarte: ");
        System.out.println("");
        String funcionUsuario = subMenuRegistro();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenaUsuario = scanner.nextLine();
        
        String[] usuarioRegistro = {email, nombreUsuario, contrasenaUsuario, funcionUsuario};
        return usuarioRegistro;
        
    }
    
    public String subMenuRegistro() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Qué función desempeñas?");
        System.out.println("1. Productor.");
        System.out.println("2. Empresa de logística.");
        System.out.println("3. Consumidor.");
        System.out.print("Elige una opción del menú: ");
        int opcionUsuario = scanner.nextInt();
        scanner.nextLine(); // Descartar el salto de línea después del número
        switch(opcionUsuario) {
            case 1 -> {
                return "productor";
            }  
            case 2 -> {
                return "logistica";
            }   
            case 3 -> {
                return "consumidor";
            }   
            default -> {
                return "Error";
            }
        }
    }
    
    public int eleccionDistribuidorConsumidor() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n¿Qué rol desempeñas?");
        System.out.println("1. Distribuidor - Compra grandes cantidades de producto para posteriormente ponerlo a la venta a unos clientes finales.");
        System.out.println("2. Consumidor final - Adquiere productos a la cooperativa directamente.");
        System.out.print("Escoge una opción: ");
        int opcionUsuario = scanner.nextInt();
        return opcionUsuario;
        
    }
    
    public int menuProductor() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("+++++++++++++++++++++++++");
        System.out.println("+ SECCIÓN DEL PRODUCTOR +");
        System.out.println("+++++++++++++++++++++++++");
        System.out.println("");
        System.out.println("¿Qué deseas hacer?");
        System.out.println("");
        System.out.println("1. Mostrar estadísticas de mis ventas.");
        System.out.println("2. Salir.");
        System.out.print("Escoge una opción del menú: ");
        int opcionUsuario = scanner.nextInt();
        return opcionUsuario;
        
    }
    
    public void subMenuRegistroProducto() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("+++++++++++++++++++++++++");
        System.out.println("+ SECCIÓN DEL PRODUCTOR +");
        System.out.println("+++++++++++++++++++++++++");
        System.out.println("|  Registrar producto   |");
        System.out.println("-------------------------");
        System.out.println("");
        System.out.print("Nombre del producto: ");
        String nombreProducto = scanner.nextLine();
        System.out.print("Extensión del producto en hectáreas (ha): ");
        double extensionHectareas = scanner.nextDouble();
        
    }
    
    public int menuDistribuidor() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("+++++++++++++++++++++++++");
        System.out.println("+ MENÚ DEL DISTRIBUIDOR +");
        System.out.println("+++++++++++++++++++++++++");
        System.out.println("");
        System.out.println("¿Qué deseas hacer?");
        System.out.println("");
        System.out.println("1. Realizar una compra.");
        System.out.println("2. Salir.");
        System.out.println("");
        System.out.print("Escoge una opción del menú: ");
        int opcionUsuario = scanner.nextInt();
        return opcionUsuario;
        
    }
    
    public Object[] menuCompraDistribuidor(ArrayList<Logistica> logistica) {
        
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.print("Ingrese el nombre del producto que desea comprar: ");
        String nombreProducto = scanner.nextLine();
        System.out.print("Ingrese el nombre de la población donde quiere que se le envíe el pedido: ");
        String poblacion = scanner.nextLine();
        int distanciaPedido = random.nextInt(199) + 1; 
        System.out.print("Ingrese la cantidad (en kg) que desea comprar (mínimo 1000): ");
        String cantidadProductoString = scanner.nextLine();
        int cantidadProducto = Integer.parseInt(cantidadProductoString);
        System.out.println("");
        int numeroPedido = random.nextInt(5000) + 1;
        String tipoProducto = GestorArchivos.detectarNombreArchivoYDevolverValor("tablaCorrespondenciaProductos.txt", nombreProducto, 3);
        double valorReferenciaProducto = Double.parseDouble(GestorArchivos.detectarNombreArchivoYDevolverValor("tablaCorrespondenciaProductos.txt", nombreProducto, 2));
        
        
        // Mostrar precios de empresas de logística
        System.out.println("A continuación, te mostramos las diferentes tarifas a elegir para el transporte: ");
        boolean productoPerecedero = tipoProducto.equals("perecedero");
        if (productoPerecedero) {
            int indice = 1;
            for (Logistica empresaLogistica : logistica) {
                System.out.println("");
                System.out.println("+++++++++++++++");
                System.out.printf("%d Empresa: %s\n", indice, empresaLogistica.getNombre());
                System.out.println("+++++++++++++++");
                System.out.printf("- Precio km producto perecedero para gran logística (hasta 100 km): %s €\n", empresaLogistica.getPrecioKmPerecederoGranLogistica());
                System.out.printf("- Precio km producto perecedero para pequeña logística (inferior a 100 km y solo después de emplear gran logística): %s €\n", empresaLogistica.getPrecioKmPerecederoPequenaLogistica());
                System.out.println("");
                indice ++;
            }
        } else {
            int indice = 1;
            for (Logistica empresaLogistica : logistica) {
                System.out.println("");
                System.out.println("+++++++++++++++");
                System.out.printf("Empresa: %s\n", indice, empresaLogistica.getNombre());
                System.out.println("+++++++++++++++");
                System.out.printf("- Precio km producto no perecedero para gran logística (tramos de 50 km): %s €\n", empresaLogistica.getPrecioKmNoPerecederoGranLogistica());
                System.out.printf("- Precio km producto no perecedero para pequeña logística (tramos inferiores a 50 km): %s €\n", empresaLogistica.getPrecioKmNoPerecederoPequenaLogistica());
                System.out.println("");
                indice ++;
            }
        }
        
        System.out.print("Elige la empresa cuya tarifa más te convenga introduciendo su número asociado: ");
        String eleccionEmpresaLogistica = scanner.nextLine();
        if (productoPerecedero) {
            double granLogistica = logistica.get(Integer.parseInt(eleccionEmpresaLogistica) - 1).getPrecioKmPerecederoGranLogistica();
            double pequenaLogistica = logistica.get(Integer.parseInt(eleccionEmpresaLogistica) - 1).getPrecioKmPerecederoPequenaLogistica();
            String nombreEmpresa = logistica.get(Integer.parseInt(eleccionEmpresaLogistica) - 1).getNombre();
            Object[] resultado = {numeroPedido, nombreProducto, tipoProducto, distanciaPedido, cantidadProducto, valorReferenciaProducto, granLogistica, pequenaLogistica, poblacion, nombreEmpresa};
            return resultado;
            
        } else {
            double granLogistica = logistica.get(Integer.parseInt(eleccionEmpresaLogistica) - 1).getPrecioKmNoPerecederoGranLogistica();
            double pequenaLogistica = logistica.get(Integer.parseInt(eleccionEmpresaLogistica) - 1).getPrecioKmNoPerecederoPequenaLogistica();
            String nombreEmpresa = logistica.get(Integer.parseInt(eleccionEmpresaLogistica) - 1).getNombre();
            Object[] resultado = {numeroPedido, nombreProducto, tipoProducto, distanciaPedido, cantidadProducto, valorReferenciaProducto, granLogistica, pequenaLogistica, poblacion, nombreEmpresa};
            return resultado;
        }
        
    }
    
    public int menuConsumidorFinal() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("+++++++++++++++++++++++++++++");
        System.out.println("+ MENÚ DEL CONSUMIDOR FINAL +");
        System.out.println("+++++++++++++++++++++++++++++");
        System.out.println("");
        System.out.println("¿Qué deseas hacer?");
        System.out.println("");
        System.out.println("1. Realizar una compra.");
        System.out.println("2. Salir.");
        System.out.println("");
        System.out.print("Escoge una opción del menú: ");
        int opcionUsuario = scanner.nextInt();
        return opcionUsuario;
        
    }
    
    public Object[] menuCompraConsumidorFinal(ArrayList<Logistica> logistica) {
        
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.print("Ingrese el nombre del producto que desea comprar: ");
        String nombreProducto = scanner.nextLine();
        System.out.print("Ingrese el nombre de la población donde quiere que se le envíe el pedido: ");
        String poblacion = scanner.nextLine();
        int distanciaPedido = random.nextInt(199) + 1; 
        System.out.print("Ingrese la cantidad (en kg) que desea comprar (máximo 100): ");
        String cantidadProductoString = scanner.nextLine();
        int cantidadProducto = Integer.parseInt(cantidadProductoString);
        System.out.println("");
        int numeroPedido = random.nextInt(5000) + 1;
        String tipoProducto = GestorArchivos.detectarNombreArchivoYDevolverValor("tablaCorrespondenciaProductos.txt", nombreProducto, 3);
        String valorReferenciaProducto = GestorArchivos.detectarNombreArchivoYDevolverValor("tablaCorrespondenciaProductos.txt", nombreProducto, 2);
        
        
        // Mostrar precios de empresas de logística
        System.out.println("A continuación, te mostramos las diferentes tarifas a elegir para el transporte: ");
        boolean productoPerecedero = tipoProducto.equals("perecedero");
        if (productoPerecedero) {
            int indice = 1;
            for (Logistica empresaLogistica : logistica) {
                System.out.println("");
                System.out.println("+++++++++++++++");
                System.out.printf("%d - Empresa: %s\n", indice, empresaLogistica.getNombre());
                System.out.println("+++++++++++++++");
                System.out.printf("- Precio km producto perecedero para gran logística (hasta 100 km): %s €\n", empresaLogistica.getPrecioKmPerecederoGranLogistica());
                System.out.printf("- Precio km producto perecedero para pequeña logística (inferior a 100 km y solo después de emplear gran logística): %s €\n", empresaLogistica.getPrecioKmPerecederoPequenaLogistica());
                System.out.println("");
                indice ++;
            }
        } else {
            int indice = 1;
            for (Logistica empresaLogistica : logistica) {
                System.out.println("");
                System.out.println("+++++++++++++++");
                System.out.printf("%d - Empresa: %s\n", indice, empresaLogistica.getNombre());
                System.out.println("+++++++++++++++");
                System.out.printf("- Precio km producto no perecedero para gran logística (tramos de 50 km): %s €\n", empresaLogistica.getPrecioKmNoPerecederoGranLogistica());
                System.out.printf("- Precio km producto no perecedero para pequeña logística (tramos inferiores a 50 km\n): %s €", empresaLogistica.getPrecioKmNoPerecederoPequenaLogistica());
                System.out.println("");
                indice ++;
            }
        }
        
        System.out.print("Elige la empresa cuya tarifa más te convenga introduciendo su número asociado: ");
        String eleccionEmpresaLogistica = scanner.nextLine();
        if (productoPerecedero) {
            double granLogistica = logistica.get(Integer.parseInt(eleccionEmpresaLogistica) - 1).getPrecioKmPerecederoGranLogistica();
            double pequenaLogistica = logistica.get(Integer.parseInt(eleccionEmpresaLogistica) - 1).getPrecioKmPerecederoPequenaLogistica();
            String nombreEmpresa = logistica.get(Integer.parseInt(eleccionEmpresaLogistica) - 1).getNombre();
            Object[] resultado = {numeroPedido, nombreProducto, tipoProducto, distanciaPedido, cantidadProducto, valorReferenciaProducto, granLogistica, pequenaLogistica, poblacion, nombreEmpresa};
            return resultado;
            
        } else {
            double granLogistica = logistica.get(Integer.parseInt(eleccionEmpresaLogistica) - 1).getPrecioKmNoPerecederoGranLogistica();
            double pequenaLogistica = logistica.get(Integer.parseInt(eleccionEmpresaLogistica) - 1).getPrecioKmNoPerecederoPequenaLogistica();
            String nombreEmpresa = logistica.get(Integer.parseInt(eleccionEmpresaLogistica) - 1).getNombre();
            Object[] resultado = {numeroPedido, nombreProducto, tipoProducto, distanciaPedido, cantidadProducto, valorReferenciaProducto, granLogistica, pequenaLogistica, poblacion, nombreEmpresa};
            return resultado;
        }
        
    }
    
    public int menuDatosEstadisticos() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("+++++++++++++++++++++++++++");
        System.out.println("+ MENÚ DATOS ESTADÍSTICOS +");
        System.out.println("+++++++++++++++++++++++++++");
        System.out.println("");
        System.out.println("¿Qué deseas hacer?");
        System.out.println("");
        System.out.println("1. Consultar ventas totales de cada uno de los productos de la cooperativa.");
        System.out.println("2. Consultar importes obtenidos por cada una de las empresas de logística.");
        System.out.println("3. Salir.");
        System.out.println("");
        System.out.print("Escoge una opción del menú: ");
        int opcionUsuario = scanner.nextInt();
        return opcionUsuario;
        
    }
    
    public boolean deAcuerdoUsuario() {
    
        Scanner scanner = new Scanner(System.in);
        
        boolean respuesta;
        System.out.println("¿Estás de acuerdo? (S/N)");
        String deAcuerdoUsuario = scanner.nextLine();
        switch (deAcuerdoUsuario) {
            case "S" -> respuesta = true;
            case "N" -> respuesta = false;
            default -> respuesta = false;
        }
        
        return respuesta;
        
    }
    
}
