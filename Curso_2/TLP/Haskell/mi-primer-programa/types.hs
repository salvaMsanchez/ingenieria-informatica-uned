-- Comentario de una sola línea

{-
Varias líneas 
de comentarios
-}

miNumeroFavorito :: Integer
miNumeroFavorito = 3

miNumeroDecimalFavorito :: Double
miNumeroDecimalFavorito = 2.57

miInicial :: Char
miInicial = 'S'

tuNombre :: String
tuNombre = "Paco"

tuApellido :: [Char]
tuApellido = "Segura"

tuSegundoApellido :: [Char]
tuSegundoApellido = ['M', 'o', 'r', 'e', 'n', 'o']

listaDeNumeros :: [Integer]
listaDeNumeros = [1, 2, 3]

tuplaDeCoordenadas :: (Integer, Integer)
tuplaDeCoordenadas = (3, 2)

anadirElementoALista :: [a] -> a -> [a]
anadirElementoALista l e = e:l

sacarElementoDeUnaLista :: [a] -> a
sacarElementoDeUnaLista (e:l) = e

listaDelUnoAlCien :: [Integer]
listaDelUnoAlCien = [1..100]

listaDeNumerosParesDelUnoAlCien :: [Integer]
listaDeNumerosParesDelUnoAlCien = [0, 2..100]

listaDeNumerosImparesDelUnoAlCien :: [Integer]
listaDeNumerosImparesDelUnoAlCien = [1, 3..100]

listaDeNumerosImparesInfinita :: [Integer]
listaDeNumerosImparesInfinita = [1, 3..]

-- listar por intensión o por comprensión 
-- se define qué condiciones se requieren
multiplosDeTres :: [Integer]
multiplosDeTres = [n | n <- [1..], mod n 3 == 0]