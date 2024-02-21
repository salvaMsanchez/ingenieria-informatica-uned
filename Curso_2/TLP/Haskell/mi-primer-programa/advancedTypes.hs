funcWithError :: a
funcWithError = undefined

-- tipo alias
type Dinero = Integer
type Complex = (Double, Double)

-- Enum
data DiaSemana = Lunes | Martes | Miercoles | Jueves | Viernes deriving (Eq, Ord, Show, Read)

-- Tipo Unión
data CadenaONumero = String | Num

-- Producto Cartesiano
data MenuRestaurante = Menu Integer Integer Integer

menuDelDia = Menu 1 2 3