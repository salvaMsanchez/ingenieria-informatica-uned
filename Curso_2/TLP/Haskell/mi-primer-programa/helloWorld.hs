helloWorld :: String
helloWorld = "Hello, World!"

tuNombre :: String
tuNombre = "Paco"

tuFechaDeNacimiento :: Integer
tuFechaDeNacimiento = 1900

doble :: Num a => a -> a
doble x = 2 * x

dobleDeXMasUno :: Num a => a -> a
dobleDeXMasUno x = doble x + 1