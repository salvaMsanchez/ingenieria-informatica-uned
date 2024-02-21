doble :: Num a => a -> a
doble x = 2 * x

suma :: Num a => a -> a -> a
suma x y = x + y

addElementToList :: a -> [a] -> [a]
addElementToList e l = e:l

sizeOfList :: Foldable t => t a -> Int
sizeOfList l = length l

-- pattern matching
isEmptyList :: [a] -> Bool
isEmptyList [] = True
isEmptyList l = False

joinTwoLists :: [a] -> [a] -> [a]
joinTwoLists l1 l2 = l1 ++ l2

listaNoAplanada = [[2, 3], [4, 5, 6]]
listaAplanada = concat listaNoAplanada

textoLargo = "abcdefg"
tresPrimeraLetras = take 3 textoLargo

nombres = ["Antonio", "Emilia", "Carmen", "Sara", "Francisco"]
numerosDeTelefono = [123, 234, 222, 111, 667]
listasCombinadas = zip nombres numerosDeTelefono