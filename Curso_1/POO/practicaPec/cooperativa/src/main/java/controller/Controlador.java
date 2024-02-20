
package controller;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import model.ConsumidorFinal;
import model.Distribuidor;
import model.Logistica;
import model.Pedido;
import model.Producto;
import model.Productor;
import model.ProductorFederado;
import view.Vista;

/**
 *
 * @author Salvador Moreno Sánchez
 */
public class Controlador {
    
    Vista vista = new Vista();
    DatosEstadisticos datosEstadisticos = new DatosEstadisticos();
    ArrayList<Productor> listaVaciaGrandesProductores = new ArrayList<>();
    ArrayList<Productor> listaVaciaPequenosProductores = new ArrayList<>();
    ArrayList<ProductorFederado> listaVaciaFederadosProductores = new ArrayList<>();
    ArrayList<Logistica> listaVaciaLogistica = new ArrayList<>();
    ArrayList<Distribuidor> listaVaciaDistribuidores = new ArrayList<>();
    ArrayList<ConsumidorFinal> listaVaciaConsumidoresFinales = new ArrayList<>();
    BBDD bbdd = new BBDD(listaVaciaGrandesProductores, listaVaciaPequenosProductores, listaVaciaFederadosProductores, listaVaciaLogistica, listaVaciaDistribuidores, listaVaciaConsumidoresFinales);
    
    public void inicializador() {
        if (!GestorArchivos.detectarExistenciaArchivo("usuarios.txt")) {
            GestorArchivos.crearArchivo("usuarios.txt");
            GestorArchivos.escribirArchivo("usuarios.txt", "email,nombreUsuario,contraseña,funcion");
        }
        
        if (!GestorArchivos.detectarExistenciaArchivo("productores.txt")) {
            GestorArchivos.crearArchivo("productores.txt");
            GestorArchivos.escribirArchivo("productores.txt", "email,nombreUsuario,contraseña,funcion,productos,extension");
        } else {
            bbdd.cargaProductorBBDD();
        }
        
        if (!GestorArchivos.detectarExistenciaArchivo("productoresFederados.txt")) {
            GestorArchivos.crearArchivo("productoresFederados.txt");
            GestorArchivos.escribirArchivo("productoresFederados.txt", "producto,emailProductor,extension");
        } else {
            bbdd.cargarProductorFederadoBBDD();
        }
        
        if (!GestorArchivos.detectarExistenciaArchivo("logistica.txt")) {
            GestorArchivos.crearArchivo("logistica.txt");
            GestorArchivos.escribirArchivo("logistica.txt", "email,nombreUsuario,contraseña,funcion,precioKmPerecederoGranLogistica,precioKmPerecederoPequenaLogistica,precioKmNoPerecederoGranLogistica,precioKmNoPerecederoPequenaLogistica");
        } else {
            bbdd.cargaLogisticaBBDD();
        }
        
        if (!GestorArchivos.detectarExistenciaArchivo("consumidores.txt")) {
            GestorArchivos.crearArchivo("consumidores.txt");
            GestorArchivos.escribirArchivo("consumidores.txt", "email,nombreUsuario,contraseña,funcion,grupoEspecifico");
        } else {
            bbdd.cargaDistribuidorBBDD();
            bbdd.cargaConsumidorFinalBBDD();
        }
        
        
    }
    
    public String controlador() {
        
        
        String[] usuario; 
        String[] usuarioRegistro;
        
        
        switch(vista.menuInicial()) {
            
            case 1:
                usuario = inicioSesion();
                if(usuario != null && "productor".equals(usuario[2])) {
                    System.out.println("SECCIÓN DEL PRODUCTOR");
                    //System.out.println("FUNVCIONA");
                } else if(usuario != null && "logistica".equals(usuario[2])) {
                    System.out.println("SECCIÓN DE EMPRESAS DE LOGÍSTICA");
                } else if(usuario != null && "consumidor".equals(usuario[2])) {
                    String grupoEspecificoConsumidor = GestorArchivos.devolverGrupoEspecificoConsumidorConEmail("consumidores.txt", usuario[0]);
                    switch (grupoEspecificoConsumidor) {
                        case "distribuidor" -> {
                            switch (vista.menuDistribuidor()) {
                                case 1 -> {
                                    boolean comprando = true;
                                    while (comprando) {
                                        System.out.println("");
                                        System.out.println("+++++++++++++++++++++++++");
                                        System.out.println("+ COMPRA - DISTRIBUIDOR +");
                                        System.out.println("+++++++++++++++++++++++++");
                                        System.out.println("");
                                        System.out.print("Estos son los productos que tenemos actualmente a la venta: \n");
                                        bbdd.listarProductosAlmacenados();
                                        Object[] datosPedido = vista.menuCompraDistribuidor(bbdd.getLogistica());

                                        // ALMACENAMIENTO PEDIDO
                                        int numeroPedido = (int) datosPedido[0];
                                        String nombreProducto = (String) datosPedido[1];
                                        String tipoProducto = (String) datosPedido[2];
                                        int distanciaPedido = (int) datosPedido[3];
                                        int cantidadProducto = (int) datosPedido[4];
                                        double valorReferenciaProducto = (double) datosPedido[5];
                                        double granLogistica = (double) datosPedido[6];
                                        double pequenaLogistica = (double) datosPedido[7];
                                        String poblacion = (String) datosPedido[8];
                                        String nombreEmpresa = (String) datosPedido[9];
                                        
                                            // Cálculo costes
                                        double[] costeProducto = calcularCosteCompraDistribuidor(datosPedido);

                                        System.out.println("Se va a realizar el siguiente pedido:");
                                        System.out.printf("- Número de pedido: %d\n", numeroPedido);
                                        System.out.printf("- Producto: %s\n", nombreProducto);
                                        System.out.printf("- Tipo: %s\n", tipoProducto);
                                        System.out.printf("- Población: %s\n", poblacion);
                                        System.out.printf("- Distancia: %d\n", distanciaPedido);
                                        System.out.printf("- Empresa encargada del transporte: %s\n", nombreEmpresa);
                                        System.out.printf("- Coste total: %.2f €\n", costeProducto[0]);
                                        System.out.printf("- Coste por kilo: %.2f €\n", costeProducto[1]);
                                        System.out.println("");
                                        comprando = !vista.deAcuerdoUsuario();
                                        
                                        if (comprando) {
                                            System.out.println("Oh! No te preocupes. Volvamos a realizar el pedido de nuevo.");
                                        } else {
                                            Pedido nuevoPedido = new Pedido(numeroPedido, nombreProducto, tipoProducto, distanciaPedido, cantidadProducto, valorReferenciaProducto, granLogistica, pequenaLogistica, poblacion);
                                            bbdd.almacenarPedidoDistribuidorConEmail(nuevoPedido, usuario[0]);
                                        }
                                        // ---
                                    }
                                    
                                    
                                    
                                }
                                case 2 -> System.out.println("Saliendo del menú distribuidor...");
                                default -> System.out.println("ERROR!!!!");
                            }
                        }
                        case "consumidorFinal" -> {
                            switch (vista.menuConsumidorFinal()) {
                                case 1 -> {
                                    boolean comprando = true;
                                    while (comprando) {
                                        System.out.println("");
                                        System.out.println("+++++++++++++++++++++++++++++");
                                        System.out.println("+ COMPRA - CONSUMIDOR FINAL +");
                                        System.out.println("+++++++++++++++++++++++++++++");
                                        System.out.println("");
                                        System.out.print("Estos son los productos que tenemos actualmente a la venta: \n");
                                        bbdd.listarProductosAlmacenados();
                                        Object[] datosPedido = vista.menuCompraDistribuidor(bbdd.getLogistica());

                                        // ALMACENAMIENTO PEDIDO
                                        int numeroPedido = (int) datosPedido[0];
                                        String nombreProducto = (String) datosPedido[1];
                                        String tipoProducto = (String) datosPedido[2];
                                        int distanciaPedido = (int) datosPedido[3];
                                        int cantidadProducto = (int) datosPedido[4];
                                        double valorReferenciaProducto = (double) datosPedido[5];
                                        double granLogistica = (double) datosPedido[6];
                                        double pequenaLogistica = (double) datosPedido[7];
                                        String poblacion = (String) datosPedido[8];
                                        String nombreEmpresa = (String) datosPedido[9];
                                        
                                            // Cálculo costes
                                        double[] costeProducto = calcularCosteCompraDistribuidor(datosPedido);

                                        System.out.println("Se va a realizar el siguiente pedido:");
                                        System.out.printf("- Número de pedido: %d\n", numeroPedido);
                                        System.out.printf("- Producto: %s\n", nombreProducto);
                                        System.out.printf("- Tipo: %s\n", tipoProducto);
                                        System.out.printf("- Población: %s\n", poblacion);
                                        System.out.printf("- Distancia: %d\n", distanciaPedido);
                                        System.out.printf("- Empresa encargada del transporte: %s\n", nombreEmpresa);
                                        System.out.printf("- Coste total: %.2f\n", costeProducto[0]);
                                        System.out.printf("- Coste por kilo: %.2f\n", costeProducto[1]);
                                        System.out.println("");
                                        comprando = !vista.deAcuerdoUsuario();
                                        
                                        if (comprando) {
                                            System.out.println("Oh! No te preocupes. Volvamos a realizar el pedido de nuevo.");
                                        } else {
                                            Pedido nuevoPedido = new Pedido(numeroPedido, nombreProducto, tipoProducto, distanciaPedido, cantidadProducto, valorReferenciaProducto, granLogistica, pequenaLogistica, poblacion);
                                            bbdd.almacenarPedidoDistribuidorConEmail(nuevoPedido, usuario[0]);
                                        }
                                        // ---
                                    }
                                    
                                    
                                    
                                }
                                case 2 -> System.out.println("Saliendo del menú del consumidor final...");
                                default -> System.out.println("ERROR!!!!");
                            }
                            
                        }
                        default -> System.out.println("ERROR!!!!");
            }
                } else {
                    System.out.println("Vuelve a intentar el inicio de sesión");
                }
                
                return "noSalir";
                //break;
            case 2:
                usuarioRegistro = registroUsuario(); // IMPORTANTE: registro del usuario en plataforma
                if(usuarioRegistro != null && usuarioRegistro[3].equals("productor")) {
                    bienvenida(usuarioRegistro); // Bienvenido Usuario!!
                    registroProductor(usuarioRegistro);
                } else if(usuarioRegistro != null && usuarioRegistro[3].equals("logistica")) {
                    bienvenida(usuarioRegistro); // Bienvenido Usuario!!
                    registroLogistica(usuarioRegistro);
                } else if(usuarioRegistro != null && usuarioRegistro[3].equals("consumidor")) {
                    bienvenida(usuarioRegistro); // Bienvenido Usuario!!
                    registroConsumidor(usuarioRegistro);
                } else {
                    //System.out.println("ERROR. NULL EN USUARIO!!!!");
                    System.out.println("Usuario ya resgistrado. Por favor, inicia sesión con estos datos.");
                }
                
                return "noSalir";
                //break;
            case 3:
                switch(vista.menuDatosEstadisticos()) {
                    case 1 -> {
                        datosEstadisticos.ventasTotalesProductos(bbdd.getDistribuidores(), bbdd.getConsumidoresFinales());
                    }
                    case 2 -> {
                        datosEstadisticos.importesEmpresasLogistica(bbdd.getLogistica());
                    }
                    case 3 -> System.out.println("Saliendo...");
                    default -> System.out.println("ERROR!!!!");
                }
                
                return "noSalir";
            case 4:
                return "salir";
            default:
                System.out.println("ERROR!!!!");
                
                return "Error";
                //break;
            
        }
        
        
    }
    
    // Método de gestión de inicio de sesión --> llama a la vista, se introducen datos y devuelve los datos del usuario
    public String[] inicioSesion() {
        String[] usuarioId = vista.menuInicioSesion();
        boolean existeUsuario = GestorArchivos.detectarNombreArchivo("usuarios.txt", usuarioId[0]);
        boolean existeContrasena = GestorArchivos.detectarNombreArchivo("usuarios.txt", usuarioId[1]);
        if (existeUsuario && existeContrasena) {
            System.out.printf("El usuario %s con contraseña %s ha sido encontrado con éxito. Tienes acceso\n", usuarioId[0], usuarioId[1]);
            String nombre = GestorArchivos.devolverNombreArchivoConEmail("usuarios.txt", usuarioId[0]);
            String funcion = GestorArchivos.devolverFuncionArchivoConEmail("usuarios.txt", usuarioId[0]);
            String email = usuarioId[0];
            String[] nombreFuncion = {email, nombre, funcion};
            return nombreFuncion;
        } else {
            System.out.println("Error. Usuario no registrado.");
            return null;
        }
    }
    
    // Método de gestión de registro de usuario --> llama a la vista, se introducen datos y luego se escriben
    public String[] registroUsuario() {
        String[] usuarioRegistro = vista.menuRegistro();
        boolean existeUsuario = GestorArchivos.detectarNombreArchivo("usuarios.txt", usuarioRegistro[0]);
        if (existeUsuario) {
            //System.out.println("Usuario ya resgistrado. Por favor, inicia sesión con estos datos.");
            return null;
        } else {
            String contenido = String.format("%s,%s,%s,%s", usuarioRegistro[0], usuarioRegistro[1], usuarioRegistro[2], usuarioRegistro[3]);
            GestorArchivos.agregarContenidoArchivo("usuarios.txt", contenido);
            GestorArchivos.agregarSaltoDeLinea("usuarios.txt");
            
            if(usuarioRegistro[3].equals("productor")) {
                GestorArchivos.agregarContenidoArchivo("productores.txt", contenido);
            } else if(usuarioRegistro[3].equals("logistica")) {
                GestorArchivos.agregarContenidoArchivo("logistica.txt", contenido);
            } else if(usuarioRegistro[3].equals("consumidor")) {
                GestorArchivos.agregarContenidoArchivo("consumidores.txt", contenido);
            }
            
            System.out.println("Usuario registrado con éxito.");
            return usuarioRegistro;
        }
    }
    
    public void bienvenida(String[] usuarioRegistro) {
        System.out.printf("¡Bienvenidx, %s!", usuarioRegistro[1]);
    }
    
    public void registroProductor(String[] usuarioRegistro) {
    
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        System.out.println("\nNosotros, como cooperativa, tenemos la capacidad de administrar el tratamiento de los siguientes productos: ");
        ArrayList<String> productos = GestorArchivos.devolverArrayArchivoConIndice("tablaCorrespondenciaProductos.txt", 0);
        //for (int i=1; i<productos.size(); i++) {
            
        //    System.out.printf("%d. %s\n", i, productos.get(i));
            
        //}
        
        // --- Registro productos no federados ---
            // Creación del objeto Productor
        ArrayList<Producto> listaVaciaProductos = new ArrayList<>();
        Productor nuevoProductor = new Productor(usuarioRegistro[0], usuarioRegistro[1], usuarioRegistro[2], 0, listaVaciaProductos);
            // ---
        //System.out.print("---");
        //System.out.print("Vayamos con la elección de los productos que quieres aportar y con la cantidad de hectáreas.");
        boolean seguirConRegistro = true;
        ArrayList<Integer> numerosProductosElegidos = new ArrayList<Integer>();
        numerosProductosElegidos.add(0);
        // while gestionar los productos que se van registrando y evitar que se repitan de cara al usuario
        while(seguirConRegistro) {
            
            for (int i=1; i<productos.size(); i++) {
                
                if(!numerosProductosElegidos.contains(i)) {
                    System.out.printf("%d. %s\n", i, productos.get(i));
                } else {
                    System.out.printf("%d. %s (Producto ya elegido)\n", i, productos.get(i));
                }
              
            }
            System.out.print("¿Qué producto quieres aportar a la cooperativa? Elige un número del menú: ");
            int opcionUsuario = scanner.nextInt();
            scanner.nextLine();
            numerosProductosElegidos.add(opcionUsuario);
            System.out.printf("¿Cuantás hectáreas de %s posees? Introduce un número. Ejemplo: 2.5 --> ", productos.get(opcionUsuario));
            double hectareasProducto = scanner.nextDouble();
            //System.out.println(hectareasProducto);
            //System.out.printf("EL decimal es --> %f\n", hectareasProducto);
            scanner.nextLine();
            //System.out.print("\n");
            String contenido = String.format(Locale.ROOT,",%s,%.2f", productos.get(opcionUsuario), hectareasProducto);
            GestorArchivos.agregarContenidoArchivo("productores.txt", contenido); // Registro del producto en txt
                // ALMACENAMIENTO EN OBJETOS Y BBDD
            String[] datosTablaCorrespondeciaProducto = GestorArchivos.extraerDatosTablaCorrespondenciaProductos(productos.get(opcionUsuario));
            Producto nuevoProducto = new Producto(productos.get(opcionUsuario), hectareasProducto, Double.parseDouble(datosTablaCorrespondeciaProducto[1]), Double.parseDouble(datosTablaCorrespondeciaProducto[2]), datosTablaCorrespondeciaProducto[3]);
            nuevoProductor.almacenarProducto(nuevoProducto);
            nuevoProductor.setExtensionTotal(nuevoProductor.getExtensionTotal() + hectareasProducto);
                // ---
            System.out.println("Genial! Producto registrado!");
            System.out.print("¿Quieres registrar otro producto de nuestra lista? (S/N) ");
            String afirmacionUsuario = scanner.nextLine();
            switch(afirmacionUsuario) {
                case "S" -> System.out.println("Sigamos!");
                case "N" -> {
                    seguirConRegistro = false;
                    GestorArchivos.agregarContenidoArchivo("productores.txt", ",fin");
                    GestorArchivos.agregarSaltoDeLinea("productores.txt");
                    if(nuevoProductor.getExtensionTotal() <= 5.0) {
                        bbdd.almacenarPequenoProductor(nuevoProductor);
                    } else if(nuevoProductor.getExtensionTotal() > 5.0) {
                        bbdd.almacenarGranProductor(nuevoProductor);
                    }
                    
                            }
                default -> System.out.println("Error! Introduce S o N para responder a la pregunta formulada");
            }
            
        }
        
        
  
        
        // --- Registro productos federados ---
        if(nuevoProductor.getExtensionTotal() <= 5.0) {
            
            boolean registroFederado = true;
            while(registroFederado) {
                System.out.print("Como se te considera un pequeño productor, tienes la posibilidad de ceder alguno de tus productos a una federación de productores. ¿Te gustaría? Escribe S/N: ");
                String afirmacionUsuarioFederado = scanner.nextLine();
                switch(afirmacionUsuarioFederado) {
                    case "S" -> {
                        boolean seguirConRegistroFederado = true;
                        ArrayList<Integer> numerosProductosElegidosFederado = new ArrayList<Integer>();
                        //numerosProductosElegidosFederado.add(0);
                        while(seguirConRegistroFederado) {

                            //System.out.println("Genial! ");

                            for (int i=0; i<nuevoProductor.getProductos().size(); i++) {

                                if(!numerosProductosElegidosFederado.contains(i)) {
                                    System.out.printf("%d. %s\n", i, nuevoProductor.getProductos().get(i).getNombreProducto());
                                } else {
                                    System.out.printf("%d. %s (Producto ya elegido)\n", i, nuevoProductor.getProductos().get(i).getNombreProducto());
                                }

                            }

                            System.out.print("¿Qué producto quieres aportar a una federación de productores? Elige un número de la lista de productos: ");
                            int opcionUsuarioFederado = scanner.nextInt();
                            scanner.nextLine();
                            String productoFederadoExtension = GestorArchivos.detectarNombreArchivoYDevolverValor("productoresFederados.txt", nuevoProductor.getProductos().get(opcionUsuarioFederado).getNombreProducto(), 2);
                            // SEGUIR EN: UNA VEZ QUE SE HA DETECTADO, EXTRAER LO LAS HECTAREAS TOTALES Y DECIR CUÁNTO PUEDE PONER DE EXTENSIÓN EL USUARIO
                            if(productoFederadoExtension != "100") {
                                double extensionRestanteHectareasFederadas = 5 - Double.parseDouble(productoFederadoExtension);
                                System.out.println("Existe una federación de productores en torno a este producto.");
                                System.out.printf("Queda un máximo de %.2f hectáreas que puedes aportar a esta federación.", extensionRestanteHectareasFederadas);
                                System.out.println("");
                                if(extensionRestanteHectareasFederadas >= nuevoProductor.getProductos().get(opcionUsuarioFederado).getExtensionProducto()) {
                                    System.out.printf("Por tanto, puedes unirte a la federación de productores de %s", nuevoProductor.getProductos().get(opcionUsuarioFederado).getNombreProducto());
                                    // Modificación de línea en archivo productoresFederados.txt
                                    String lineaLecturaArchivo = GestorArchivos.detectarNombreArchivoYDevolverLineaLectura("productoresFederados.txt", nuevoProductor.getProductos().get(opcionUsuarioFederado).getNombreProducto());
                                    ArrayList<String> arrayDatosFraccionados = new ArrayList<String>();
                                    String[] datosFraccionados = lineaLecturaArchivo.split(",");
                                    for (String dato : datosFraccionados) {
                                        arrayDatosFraccionados.add(dato);
                                    }
                                    arrayDatosFraccionados.add(1, usuarioRegistro[0]);
                                    arrayDatosFraccionados.remove(arrayDatosFraccionados.size() - 1);
                                    double actualizacionHectareas = nuevoProductor.getProductos().get(opcionUsuarioFederado).getExtensionProducto() + Double.parseDouble(productoFederadoExtension);
                                    arrayDatosFraccionados.add(Double.toString(actualizacionHectareas));
                                    //String contenido = String.format("", usuarioRegistro);
                                    String contenido = "";
                                    for (String dato : arrayDatosFraccionados) {
                                        if(contenido.equals("")) {
                                            contenido += dato;
                                        } else {
                                            contenido = contenido + "," + dato;
                                        }
                                    }
                                    GestorArchivos.modificarLineaArchivo("productoresFederados.txt", nuevoProductor.getProductos().get(opcionUsuarioFederado).getNombreProducto(), contenido);
                                    // ALMACENAMIENTO EN OBJETOS Y BBDD
                                    for(int i=0;i<bbdd.getFederadosProductores().size();i++) {
                                        if(nuevoProductor.getProductos().get(opcionUsuarioFederado).getNombreProducto().equals(bbdd.getFederadosProductores().get(i).getNombreProducto())) {
                                            bbdd.getFederadosProductores().get(i).setExtensionTotal(actualizacionHectareas);
                                            bbdd.getFederadosProductores().get(i).almacenarPequenoProductor(nuevoProductor);
                                        }
                                    }
                                    // ---
                                } else {
                                    System.out.printf("Lo siento, no puedes unirte a esta federación de productores ya que tus hectáreas a aportar superan las permitidas: %.2f", extensionRestanteHectareasFederadas);
                                }
                                //System.out.println(productoFederadoExtension);
//                                boolean numeroHectareasIncorrectas = true;
//                                while(numeroHectareasIncorrectas) {
//                                    System.out.printf("¿Cuantás hectáreas de %s posees? Introduce un número (Máximo %.2f). Ejemplo: 2.50 --> ", productos.get(opcionUsuarioFederado), extensionRestanteHectareasFederadas);
//                                    double hectareasProductoFederado = scanner.nextDouble();
//                                    if(hectareasProductoFederado > extensionRestanteHectareasFederadas) {
//                                        System.out.printf("Lo siento, no puedes superar el número de hectáreas estipulado: %.2f", extensionRestanteHectareasFederadas);
//                                        System.out.println("");
//                                    } else {
//                                        // Modificación de línea en archivo productoresFederados.txt
//                                        String lineaLecturaArchivo = GestorArchivos.detectarNombreArchivoYDevolverLineaLectura("productoresFederados.txt", productos.get(opcionUsuarioFederado));
//                                        ArrayList<String> arrayDatosFraccionados = new ArrayList<String>();
//                                        String[] datosFraccionados = lineaLecturaArchivo.split(",");
//                                        for (String dato : datosFraccionados) {
//                                            arrayDatosFraccionados.add(dato);
//                                        }
//                                        arrayDatosFraccionados.add(1, usuarioRegistro[0]);
//                                        arrayDatosFraccionados.remove(arrayDatosFraccionados.size() - 1);
//                                        double actualizacionHectareas = hectareasProductoFederado + Double.parseDouble(productoFederadoExtension);
//                                        arrayDatosFraccionados.add(Double.toString(actualizacionHectareas));
//                                        //String contenido = String.format("", usuarioRegistro);
//                                        String contenido = "";
//                                        for (String dato : arrayDatosFraccionados) {
//                                            if(contenido.equals("")) {
//                                                contenido += dato;
//                                            } else {
//                                                contenido = contenido + "," + dato;
//                                            }
//                                        }
//                                        GestorArchivos.modificarLineaArchivo("productoresFederados.txt", productos.get(opcionUsuarioFederado), contenido);
//                                        numeroHectareasIncorrectas = false;
//                                    }
//                                }


                                //scanner.nextLine();

                            } else if(Double.parseDouble(productoFederadoExtension) == 5.00) {
                                System.out.println("Existe una federación de productores en torno a este producto, pero ya no se pueden aportar más héctáreas.");
                            } else if(productoFederadoExtension == "100") {
                                System.out.println("No existe todavía una federación de productores en torno a este producto.");
                                System.out.println("Por tanto, queda registrado!.");
                                //System.out.println("De esta forma, podrás aportar hasta 5 hectáreas de tu producto.");
                                //System.out.printf("¿Cuantás hectáreas de %s posees? Introduce un número. Ejemplo: 2.50 --> ", productos.get(opcionUsuarioFederado));
                                //double hectareasProductoFederado = scanner.nextDouble();
                                //scanner.nextLine();
                                String contenido = String.format(Locale.ROOT,"%s,%s,%.2f", nuevoProductor.getProductos().get(opcionUsuarioFederado).getNombreProducto(), usuarioRegistro[0], nuevoProductor.getProductos().get(opcionUsuarioFederado).getExtensionProducto());
                                GestorArchivos.agregarContenidoArchivo("productoresFederados.txt", contenido);
                                GestorArchivos.agregarSaltoDeLinea("productoresFederados.txt");
                                // ALMACENAMIENTO EN OBJETOS Y BBDD
                                ArrayList<Productor> listaVaciaFederadosProductores = new ArrayList<>();
                                ProductorFederado nuevoProductorFederado = new ProductorFederado(nuevoProductor.getProductos().get(opcionUsuarioFederado).getNombreProducto(), nuevoProductor.getProductos().get(opcionUsuarioFederado).getExtensionProducto(), listaVaciaFederadosProductores);
                                
                                bbdd.almacenarFederadoProductor(nuevoProductorFederado);
                                // ---
                            }
                            numerosProductosElegidosFederado.add(opcionUsuarioFederado);

                            boolean seguirRegistrandoProductosFederados = true;
                            while(seguirRegistrandoProductosFederados) {
                                System.out.print("¿Quieres registrar otro producto en una federación de productores? (S/N) ");
                                String afirmacionUsuarioFderado = scanner.nextLine();
                                switch(afirmacionUsuarioFderado) {
                                    case "S" -> {
                                        System.out.println("Maravilloso, sigamos registrando productos en federaciones!");
                                        seguirRegistrandoProductosFederados = false;
                                    }
                                    case "N" -> {
                                        seguirRegistrandoProductosFederados = false;
                                        seguirConRegistroFederado = false;
                                        registroFederado = false;
                                        System.out.println("Perfecto, pasemos al siguiente paso!");
                                    }
                                    default -> System.out.println("Error! Introduce S o N para responder a la pregunta formulada");

                                }
                            }

                        }

                    }
                    case "N" -> {
                        registroFederado = false;
                        System.out.println("Perfecto, pasemos al siguiente paso!");
                    }
                    default -> System.out.println("Error! Introduce S o N para responder a la pregunta formulada");


                }
            }
            
            
        }
        
        
        
        
        
        //scanner.close();
    
    
    }
    
    // A ELIMINAR - SOLO PRUEBAS
    public void printearBBDD() {
        String baseDatos = bbdd.toString();
        System.out.println(baseDatos);
    }
    
    public void registroLogistica(String[] usuarioRegistro) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        System.out.println("\nComo empresa de logística, nos debes proporcionar una serie de datos en relación al precio del kilometraje.");
        System.out.println("Para el transporte de productos perecederos:");
        System.out.print("\t- ¿Qué precio ofrecéis por kilómetro para transportes de hasta 100km? (envío del producto a la capital de la provincia): ");
        double precioKmPerecederoGranLogistica = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("\t- ¿Qué precio ofrecéis por kilómetro para transportes superiores a los 100km? (reparto desde la capital de la provincia hasta el destino final): ");
        double precioKmPerecederoPequenaLogistica = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Para el transporte de productos no perecederos:");
        System.out.print("\t- ¿Qué precio ofrecéis por kilómetro para transportes de hasta 50km? ");
        double precioKmNoPerecederoGranLogistica = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("\t- ¿Qué precio ofrecéis por kilómetro para transportes menores de 50km? ");
        double precioKmNoPerecederoPequenaLogistica = scanner.nextDouble();
        scanner.nextLine();
        String contenido = String.format(Locale.ROOT,",%.2f,%.2f,%.2f,%.2f", precioKmPerecederoGranLogistica, precioKmPerecederoPequenaLogistica, precioKmNoPerecederoGranLogistica, precioKmNoPerecederoPequenaLogistica);
        GestorArchivos.agregarContenidoArchivo("logistica.txt", contenido); // Registro del precio del kilometraje en txt
        GestorArchivos.agregarSaltoDeLinea("logistica.txt");
        
        // ALMACENAMIENTO EN OBJETOS Y BBDD
        Logistica nuevaEmpresaLogistica = new Logistica(usuarioRegistro[0], usuarioRegistro[1], usuarioRegistro[2], precioKmPerecederoGranLogistica, precioKmPerecederoPequenaLogistica, precioKmNoPerecederoGranLogistica, precioKmNoPerecederoPequenaLogistica, 0);
        bbdd.almacenarLogistica(nuevaEmpresaLogistica);
        // ----
        
        //scanner.close();
    }
    
    public void registroConsumidor(String[] usuarioRegistro) {
        
        int eleccionDistribuidorConsumidor = vista.eleccionDistribuidorConsumidor();
        switch(eleccionDistribuidorConsumidor) {
            case 1 -> {
                GestorArchivos.agregarContenidoArchivo("consumidores.txt", ",distribuidor");
                GestorArchivos.agregarSaltoDeLinea("consumidores.txt");
                // ALMACENAMIENTO EN OBJETOS Y BBDD
                ArrayList<Pedido> listaVaciaPedidos = new ArrayList<>();
                Distribuidor nuevoDistribuidor = new Distribuidor(usuarioRegistro[0], usuarioRegistro[1], usuarioRegistro[2], listaVaciaPedidos);
                bbdd.almacenarDistribuidor(nuevoDistribuidor);
                
            }
            case 2 -> {
                GestorArchivos.agregarContenidoArchivo("consumidores.txt", ",consumidorFinal");
                GestorArchivos.agregarSaltoDeLinea("consumidores.txt");
                // ALMACENAMIENTO EN OBJETOS Y BBDD
                ArrayList<Pedido> listaVaciaPedidos = new ArrayList<>();
                ConsumidorFinal nuevoConsumidorFinal = new ConsumidorFinal(usuarioRegistro[0], usuarioRegistro[1], usuarioRegistro[2], listaVaciaPedidos);
                bbdd.almacenarConsumidorFinal(nuevoConsumidorFinal);
                
            }
            default -> System.out.println("Error!!");
        }
        
        
    }
    
    public void seccionProductor(String[] datosUsuario) {
        System.out.printf("¡Bienvenidx, %s!\n\n", datosUsuario[1]); // email, nombre, función en el Array datosUsuario
        int opcionUsuario = vista.menuProductor();
        switch(opcionUsuario) {
            case 1:
                vista.subMenuRegistroProducto();
                break;
            default:
                System.out.println("ERROR!!!!");
                break;
        }
    }
    
    public double[] calcularCosteCompraDistribuidor(Object[] datosPedido) {
        
        String tipoProducto = (String) datosPedido[2];
        int distanciaPedido = (int) datosPedido[3];
        int cantidadProducto = (int) datosPedido[4];
        double valorReferenciaProducto = (double) datosPedido[5];
        double granLogistica = (double) datosPedido[6];
        double pequenaLogistica = (double) datosPedido[7];
        String nombreEmpresa = (String) datosPedido[9];
        
        // PERECEDERO
        if (tipoProducto.equals("perecedero")) {
            
            double precioCooperativa = 1.15 * valorReferenciaProducto * cantidadProducto;
            
            int tramosGranLogisticaCompletos = distanciaPedido / 100;
            int kmGranLogistica = tramosGranLogisticaCompletos * 100;
            int tramoKmPequenaLogistica = distanciaPedido % 100;
            
            double precioTotalGranLogistica = kmGranLogistica * granLogistica * cantidadProducto;
            
            double precioTotalPequenaLogistica = pequenaLogistica * tramoKmPequenaLogistica * cantidadProducto;
            
            double pagoTotalLogistica = precioTotalGranLogistica + precioTotalPequenaLogistica;
            
            double pagoTotalDistribuidor = precioTotalGranLogistica + precioTotalPequenaLogistica + precioCooperativa;
            double pagoTotalDistribuidorIVA = pagoTotalDistribuidor * 1.10;
            double costePorKilo = pagoTotalDistribuidorIVA / cantidadProducto;
            
            double[] costePerecedero = {pagoTotalDistribuidorIVA, costePorKilo};
            
            // Suma beneficios en empresa de logística
            for (Logistica logistica : bbdd.getLogistica()) {
                if (logistica.getNombre().equals(nombreEmpresa)) {
                    logistica.setBeneficios(pagoTotalLogistica);
                }
            }
            
            return costePerecedero;
            
        } else { // NO PERECEDERO
            
            int toneladasCompletas = cantidadProducto / 1000;
            int kilogramosRestantes = cantidadProducto % 1000;
            double proporcionKilogramosRestantes = kilogramosRestantes / 1000;
            
            double precioTrayecto = 0.5 * valorReferenciaProducto * 1000;
            
            int tramosGranLogisticaCompletos = distanciaPedido / 50;
            int kmGranLogistica = tramosGranLogisticaCompletos * 50;
            int tramoKmPequenaLogistica = distanciaPedido % 50;
            
            double precioToneladaGranLogistica = precioTrayecto * tramosGranLogisticaCompletos;
            double precioTotalToneladaGranLogistica = precioToneladaGranLogistica + kmGranLogistica * granLogistica;
            double precioTotalGranLogistica = (precioTotalToneladaGranLogistica * toneladasCompletas) + (precioTotalToneladaGranLogistica * proporcionKilogramosRestantes);
            
            double precioTotalPequenaLogistica = pequenaLogistica * tramoKmPequenaLogistica * cantidadProducto;
            
            double pagoTotalDistribuidorLogistica = precioTotalGranLogistica + precioTotalPequenaLogistica;
            
            double costeProducto = cantidadProducto * valorReferenciaProducto * 1.05;
            
            double totalAPagarDistribuidor = pagoTotalDistribuidorLogistica + costeProducto;
            double costePorKilo = totalAPagarDistribuidor / cantidadProducto;
            double[] costeNoPerecedero = {totalAPagarDistribuidor, costePorKilo};
            
            // Suma beneficios en empresa de logística
            for (Logistica logistica : bbdd.getLogistica()) {
                if (logistica.getNombre().equals(nombreEmpresa)) {
                    logistica.setBeneficios(pagoTotalDistribuidorLogistica);
                }
            }
            
            return costeNoPerecedero;
            
        }
        
        
    }
    
}
