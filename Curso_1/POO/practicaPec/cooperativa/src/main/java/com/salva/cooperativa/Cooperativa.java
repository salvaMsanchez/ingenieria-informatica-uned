
package com.salva.cooperativa;

import controller.Controlador;

/**
 *
 * @author Salvador Moreno Sánchez
 */
public class Cooperativa {

    public static void main(String[] args) {
        
        Controlador controlador = new Controlador();
        
        controlador.inicializador(); // inicializa txts para persistencia de datos
        
        // PRUEBAS DEL FUNCIONAMIENTO DE LA CARGA EN BBDD PRE-EJECUCIÓN
        //controlador.printearBBDD();
        
        boolean salir = false;
        while(!salir) {
            String decisionUsuario = controlador.controlador();
            switch(decisionUsuario) {
                case "salir" -> {
                    salir = true;
                    System.out.println("H A S T A    P R O N T O !!!");
                }
                case "Error" -> System.out.println("Ha habido un error!!!");
                default -> salir = false;
            }
        }
        
        
        // PRUEBAS DEL FUNCIONAMIENTO DE LA CARGA EN BBDD POST-EJECUCIÓN
        //controlador.printearBBDD();
        
    }
}
