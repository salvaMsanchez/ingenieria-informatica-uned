module Main where

    import System.IO
    import StackMachine

    -- "BUCLE" PRINCIPAL --

    mainLoop :: IO ()
    mainLoop = do { putStr "\nEscriba una expresión en notación prefija para ser evaluada\nexpresión: ";
                    hFlush stdout;
                    e <- getLine;
                    if ( e /= "")
                        then let st = (createSynTree (words e)) in do {
                            putStr ("El árbol de sintaxis abstracta es:\n" ++ (show st) ++ "\n");
                            putStr ("\nEl resultado de la evaluación es:\n" ++ (show (evalSynTree st)) ++ "\n");
                            mainLoop
                        }
                        else putStr "\nFin de la ejecución\n"
                  }

    -- FUNCIÓN PRINCIPAL --
    main :: IO ()
    main = do { hSetBuffering stdin LineBuffering; -- Permite que la entrada pueda ser editada
                mainLoop;                          -- Ejecutamos el "bucle" principal
              }