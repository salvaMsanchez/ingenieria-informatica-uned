# TLP

---

**Defina los siguientes conceptos, incluyendo ejemplos de cada uno:**

a) **Transparencia referencial:** si una expresión es referencialmente transparente, siempre devolverá el mismo resultado dado el mismo entorno (mismos valores de variables y funciones involucradas), sin importar dónde y cuándo se evalúe. Esta propiedad es fundamental en la programación funcional. 

Ejemplo en Haskell:

	square x = x * x
	
Aquí, la función square es referencialmente transparente. Si llamas a square 4, siempre devolverá 16, sin importar el contexto o cuántas veces se evalúe.

La transparencia referencial es importante porque facilita el razonamiento sobre programas y la optimización. Por ejemplo, los compiladores pueden hacer optimizaciones como almacenar en caché resultados de funciones o reordenar expresiones sin preocuparse por efectos secundarios. Además, permite propiedades como la memoización (almacenamiento en caché de resultados de funciones) y es esencial para las pruebas y la refactorización del código, ya que facilita predecir y asegurar el comportamiento del programa.

b) **Léxico, Sintaxis y Semántica como elementos fundamentales para la definición de un lenguaje de programación:** ya definido a lo largo del documento.

---

**Sea la siguiente función escrita en Haskell:**

	myMap f (x:y:zs) = (f x y):(myMap f ((f x y):zs))

a) **Deduzca, razonadamente, el tipo de la función myMap.**

La función `myMap` se define como:

```haskell
myMap f (x:y:zs) = (f x y):(myMap f ((f x y):zs))
```

Para deducir el tipo de `myMap`, examinemos los elementos y las operaciones involucradas:

1. **Patrón `(x:y:zs)`**:
   - Esto implica que la función `myMap` opera sobre una lista que tiene al menos dos elementos, donde `x` es el primer elemento, `y` es el segundo elemento, y `zs` es el resto de la lista.

2. **`f x y`**:
   - Aquí, `f` es una función que toma dos argumentos (`x` y `y`) y produce un resultado.
   - Entonces, si `x` tiene el tipo `a` e `y` tiene el tipo `a`, entonces `f` debe tener el tipo `a -> a -> b`, donde `b` es el tipo del resultado.

3. **Expresión `(f x y):(myMap f ((f x y):zs))`**:
   - `(f x y)` es un valor de tipo `b`.
   - `myMap f ((f x y):zs)` es una llamada recursiva a `myMap` con la lista `((f x y):zs)`.
   - Esto significa que `myMap f` debe devolver una lista de elementos de tipo `b`.

4. **Conclusión**:
   - El tipo de `myMap` debe ser una función que toma un `f` (de tipo `a -> a -> b`) y una lista de tipo `[a]`, y devuelve una lista de tipo `[b]`.

Entonces, el tipo de la función `myMap` es:

```haskell
myMap :: (a -> a -> b) -> [a] -> [b]
```

b) **¿La función myMap puede operar sobre cualquier lista? Justifique su respuesta.**

**No**, la función `myMap` **no** puede operar sobre cualquier lista. 

**Justificación:**

- `myMap` requiere que la lista de entrada tenga al menos dos elementos. Esto se debe a que en la definición se utiliza el patrón `(x:y:zs)`, que empareja la lista con al menos dos elementos (`x` y `y`). Si se le proporciona una lista con menos de dos elementos, la función no tendrá una coincidencia de patrón válida y generará un error en tiempo de ejecución debido a la falta de un caso base para manejar listas vacías o listas con un solo elemento.

c) **Defina los siguientes conceptos propios de la programación funcional e indique justificadamente si puede usarse la función myMap como ejemplo de los mismos:**

* **Evaluación Perezosa:** es una característica de algunos lenguajes de programación funcionales (como Haskell) que retrasa la evaluación de una expresión hasta que su valor es necesario. Esto permite la definición de estructuras de datos infinitas y optimiza el rendimiento al evitar cálculos innecesarios.

	**¿Puede `myMap` usarse como ejemplo?**

	- **Sí**, `myMap` puede usarse como ejemplo de evaluación perezosa. Aunque `myMap` genera recursivamente una nueva lista, Haskell no evaluará cada elemento de esta lista hasta que sea necesario. Esto es útil si solo se necesita una parte de la lista resultante, ya que se evitará calcular el resto de los elementos.

* **Currificación:** es el proceso de transformar una función que toma múltiples argumentos en una secuencia de funciones que toman un solo argumento. En Haskell, todas las funciones son curriables por defecto, lo que permite aplicar parcialmente funciones con menos argumentos de los esperados.

	**¿Puede `myMap` usarse como ejemplo?**

	- **No directamente**, `myMap` no se usa específicamente como ejemplo de currificación. Aunque `myMap` toma dos argumentos, el primero es una función binaria `f`, y el segundo es una lista. La currificación se refiere más a la capacidad de aplicar parcialmente una función, lo cual no es el foco principal de `myMap`. Sin embargo, si quisiéramos aplicar parcialmente la función `f` que se pasa a `myMap`, podríamos hacerlo, pero eso sería un ejemplo de currificación aplicada a `f`, no a `myMap` en sí.

---

**Dada la siguiente gramática:**

	<exp> ::= <exp> + <exp> | <exp> - <exp> | <exp> * <exp> | <num>
	<num> := <num><digito> | <digito>
	<digito> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

a) **Defina el concepto de gramática ambigua y explique cómo puede comprobarse si una gramática ol es. ¿Es ambigua la gramática dada? Justifique su respuesta.**

**Definición de Gramática Ambigua:**

- Una **gramática ambigua** es aquella en la que existe al menos una cadena de símbolos terminales (una sentencia) que puede tener más de un árbol sintáctico o más de una derivación izquierda o derecha. Esto significa que la gramática puede generar la misma expresión de manera diferente, lo cual puede dar lugar a diferentes interpretaciones semánticas.

**¿Cómo comprobar si una gramática es ambigua?**

- Para verificar si una gramática es ambigua, se deben generar las derivaciones y árboles sintácticos para diferentes expresiones. Si para una misma expresión existen dos (o más) árboles sintácticos diferentes, entonces la gramática es ambigua.

**¿Es la gramática dada ambigua?**

- Sí, la gramática dada es ambigua. Esto se debe a que una expresión como `1 + 2 * 3` puede interpretarse de diferentes maneras según la forma en que se agrupan las operaciones:

1. **Primera interpretación:** `1 + (2 * 3)` con el árbol sintáctico correspondiente:
   ```
   <exp>
   /   |    \
 1   +   <exp>
        /  |  \
     2  *  3
   ```

2. **Segunda interpretación:** `(1 + 2) * 3` con el árbol sintáctico correspondiente:
   ```
   <exp>
   /  |  \
<exp>  *  3
 /  |  \
1  +  2
   ```
Dado que `1 + 2 * 3` tiene más de un árbol sintáctico, la gramática es ambigua.

b) **¿En qué notación está escrita esta gramática? Justifique su respuesta y utilice la gramática para explicar el significado de los metasímbolos utilizados por dicha notación para describir una gramática.**

**Notación de la gramática:**

- La gramática está escrita en **Forma de Backus-Naur** o **Notación BNF** (Backus-Naur Form). Esta notación es una forma común para describir la sintaxis de los lenguajes de programación.

**Explicación de los metasímbolos utilizados:**

- `<exp>`, `<num>`, y `<digito>` son **símbolos no terminales** que representan diferentes categorías gramaticales.
- `::=`, el operador de definición, se utiliza para indicar que lo que está a la izquierda puede ser reemplazado por lo que está a la derecha. Define cómo se pueden expandir los símbolos no terminales.
- `|` es el operador de elección, que permite que una producción tenga múltiples opciones.
  
**Ejemplo:**

- La producción `<exp> ::= <exp> + <exp> | <exp> - <exp> | <exp> * <exp> | <num>` indica que una expresión (`<exp>`) puede ser una suma, una resta, una multiplicación de otras dos expresiones, o un número (`<num>`).

c) **¿Puede dibujarse directamente esa gramática en forma de diagramas sintácticos? Justifique su respuesta (si e s afirmativa, dibuje la gramática en forma de diagramas sintácticos, si no lo es, explique los motivos).**

**¿Puede dibujarse directamente esa gramática en forma de diagramas sintácticos?**

- No se puede dibujar directamente como diagramas sintácticos debido a su ambigüedad. Los diagramas sintácticos (también conocidos como diagramas de sintaxis de Nassi-Shneiderman o diagramas de flujo de sintaxis) representan de forma gráfica las reglas de una gramática y requieren una estructura clara y no ambigua.

**Justificación:**

- Dado que la gramática es ambigua, el diagrama resultante sería complejo y podría mostrar caminos redundantes o conflictivos, lo que haría difícil la interpretación unívoca del flujo del diagrama.

**Sin embargo**, si la gramática se modificara para ser no ambigua (por ejemplo, usando reglas de precedencia y asociatividad de operadores), sería posible representarla gráficamente en forma de diagramas sintácticos.

**Ejemplo de modificación para evitar ambigüedad:**

- Podríamos desambiguar la gramática dividiéndola en diferentes niveles de precedencia, como se muestra a continuación:

```
<exp> ::= <exp> + <term> | <exp> - <term> | <term>
<term> ::= <term> * <factor> | <factor>
<factor> ::= <num>
<num> ::= <num> <digito> | <digito>
<digito> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
```

- Con esta gramática, `1 + 2 * 3` se evaluaría siempre como `1 + (2 * 3)`, respetando la precedencia usual de los operadores. Ahora sí se podría representar de forma directa con diagramas sintácticos.

---

**Defina los siguientes conceptos, aportando un ejemplo en algún lenguaje de programación que los pueda ilustrar:**

a) **Azúcar sintáctico:** término acuñado por Peter Landin en 1964 para describir aquellas estructuras sintácticas que no añaden expresividad al lenguaje, pero facilitan la escritura de los programas ofreciendo alternativas para que el programador pueda escoger. Un ejemplo se puede encontrar en las diferentes versiones de bucles que ofrecen los lenguajes de programación.

b) **Error Sintáctico:** ocurre cuando el código fuente de un programa viola las reglas gramaticales del lenguaje de programación. Estos errores son detectados por el compilador o intérprete durante la fase de análisis sintáctico y normalmente impiden que el programa sea ejecutado.

```python
if x > 10
    print("x is greater than 10")
```

En el ejemplo anterior, falta un símbolo de dos puntos (`:`) al final de la línea `if x > 10`. Esto es un error sintáctico, ya que en Python, cada declaración `if` debe terminar con dos puntos antes de proceder con el bloque de código. El intérprete de Python arrojará un error indicando que hay un problema de sintaxis.

c) **Error Semántico:** ocurre cuando el código fuente, aunque sea sintácticamente correcto, produce un resultado incorrecto o no deseado debido a un problema en la lógica del programa. Estos errores no son detectados por el compilador, ya que el código es gramaticalmente válido, pero el programa no hace lo que se esperaba.

```python
x = 10
y = 0
z = x / y
print(z)
```

Aunque este código es sintácticamente correcto, intentará dividir un número entre cero (`x / y` donde `y = 0`), lo cual es un error semántico. En este caso, se trata de un error de lógica aritmética. En muchos lenguajes, esto resultará en un error de ejecución (por ejemplo, una excepción de división por cero en Python).

d) **Estado del cómputo:** se refiere a la colección de valores de todas las variables, el contenido de la memoria, y la posición en el código (instrucción actual o próxima a ejecutar) en un momento dado durante la ejecución de un programa. En otras palabras, es una instantánea del estado de todas las partes relevantes del programa en un punto específico durante su ejecución.

```c
int a = 5;
int b = 10;
int sum = a + b;
```

- Supongamos que el programa ha ejecutado la primera y segunda línea, pero aún no ha ejecutado la tercera. En este punto, el **estado del cómputo** sería:
  - `a = 5`
  - `b = 10`
  - `sum` no tiene valor asignado aún.
  
  Si luego se ejecuta la tercera línea, el **estado del cómputo** cambia a:
  
  - `a = 5`
  - `b = 10`
  - `sum = 15`
  
  Este concepto es importante para entender cómo un programa avanza y cómo las variables y la memoria cambian a lo largo de la ejecución.

---

**Dado el siguiente código en Pascal:**

	function f1(x:integer):integer; 
		function f2():integer; 
			function f3():integer; 
				begin
					f3:=x; 
				end; 
			begin
				f2:=f3; 
			end; 
		begin
			f1:=f2;
		end;


a) **Rellene la siguiente tabla que indica la visibilidad de las declaraciones de las funciones f1, f2 y f3:**

Vamos a completar la tabla de visibilidad basándonos en el ámbito de cada función en el código proporcionado. Recuerda que en Pascal, una función es visible dentro de la función en la que está declarada y en las funciones anidadas dentro de ella, pero no fuera de su ámbito.

|                        | ...dentro de `f1` | ...dentro de `f2` | ...dentro de `f3` |
|------------------------|-------------------|-------------------|-------------------|
| **`f1` es visible...** | Sí                | No                | No                |
| **`f2` es visible...** | Sí                | Sí                | No                |
| **`f3` es visible...** | Sí                | Sí                | Sí                |

**Explicación:**

- `f1` es la función más externa y, por lo tanto, es visible solo en su propio ámbito.
- `f2` está declarada dentro de `f1`, por lo que es visible dentro de `f1` y dentro de `f2`, pero no dentro de `f3` porque `f3` está más interna.
- `f3` está declarada dentro de `f2`, por lo que es visible en su propio ámbito, en el ámbito de `f2` (que lo contiene), y también en `f1`, ya que `f1` contiene a `f2` y, por ende, a `f3`.

b) **¿Existe alguna referencia no local dentro del código? En caso afirmativo describa cómo puede resolverse.**

Sí, existe una referencia no local. La función `f3` accede a la variable `x`, que no está declarada dentro de `f3`, sino en `f1`. 

**Resolución de la referencia no local:**

- Esta referencia no local se resuelve utilizando la regla de alcance estático o léxico. En este caso, `x` es capturada en el entorno donde `f3` fue definida, es decir, dentro de `f1`. Esto significa que cuando `f3` se ejecuta, accede al valor de `x` que está en el ámbito de `f1`, el cual es accesible porque `f3` está anidada dentro de `f2`, que a su vez está anidada dentro de `f1`.

c) **Defina el concepto de subprograma en forma cerrada e indique si f3 lo es o no. Explique el porqué.**

**Concepto de Subprograma en Forma Cerrada:**

- Un **subprograma en forma cerrada** es aquel que no accede a ninguna variable que no esté definida en su propio ámbito, es decir, no realiza referencias no locales. Todas las variables que usa están declaradas dentro del subprograma mismo o pasadas como parámetros.

**¿`f3` es un subprograma en forma cerrada?**

- **No**, `f3` no es un subprograma en forma cerrada porque accede a la variable `x`, que no está declarada dentro de `f3`. La variable `x` pertenece al ámbito de `f1`, lo que significa que `f3` depende de una variable no local, lo cual rompe la característica de ser un subprograma en forma cerrada.

---

**Defina los conceptos relacionados con la semántica de los lenguajes de programación siguientes y ponga un ejemplo para cada uno de ellos:**

a) **Declaración:** instrucción que introduce un identificador (como una variable, constante, función o tipo) y asocia ese identificador con un valor, tipo o estructura de datos. La declaración informa al compilador o intérprete sobre la existencia de ese identificador y, en algunos casos, puede también inicializarlo con un valor.

	int x;         // Declara una variable 'x' de tipo entero sin inicializar.
	float y = 3.14; // Declara una variable 'y' de tipo flotante e inicializa con 3.14.


b) **Bloque:** un bloque es una secuencia de declaraciones seguidas por una secuencia de sentencias y rodeado de marcadores, como llaves o pares de inicio y fin. En C, los bloques se denominan sentencias compuestas y aparecen en el cuerpo de las definiciones de una función y en cualquier otra parte de un programa en que podría aparecer una sentencia ordinaria.

	void p (void) {
		double r, z; /* bloque de p hasta la última llave */
		...
		{ int x, y; /* bloque anidado entre llaves */
			x = 2; y = 0; x += 1;
		}
		...
	}

c) **Alcance (o ámbito) de un vínculo:** región del programa en la que se conserva un vínculo en concreto. En la mayoría de los lenguajes actuales estructurados en bloques (como C), en los que los bloques pueden anidarse, el alcance de un vínculo queda limitado al bloque en que aparece su declaración. Esta regla se conoce por alcance léxico. En el ejemplo siguiente de código C, las dos declaraciones del identificador x tienen significados y alcances diferentes:

	void p (void) {
		int x;
		...
	}
	void q(void) {
		char x;
		...
	}
	

d) **Tabla de símbolos:** estructura de datos utilizada por los compiladores e intérpretes para almacenar información sobre las declaraciones en un programa. Esta tabla asocia los nombres de variables, funciones, tipos, etc., con la información relevante sobre ellos, como su tipo, alcance, dirección de memoria, valor actual, y otros atributos. Durante la compilación o interpretación de un programa, la tabla de símbolos se utiliza para verificar la existencia de variables, funciones u otros identificadores y para realizar la comprobación de tipos. Ayuda a gestionar el alcance de los identificadores y facilita la generación de código.

```c
int a = 5;
float b = 3.14;
int c = a + 10;
```

La tabla de símbolos podría ser algo como:

| Nombre | Tipo   | Valor | Alcance |
|--------|--------|-------|---------|
| a      | int    | 5     | global  |
| b      | float  | 3.14  | global  |
| c      | int    | 15    | global  |


---

**Programe en Haskell, utilizando listas por comprensión, las siguientes funciones:**

a) **La función filter (cuyo tipo es filter :: (a -> Bool) -> [a] -> [a]) que elimina todos los elementos de una lista (2° parámetro) que no cumplen una condición representada mediante una función (1° parámetro).**

La función filter elimina todos los elementos de una lista que no cumplen con una condición especificada por una función pasada como argumento.

	myFilter :: (a -> Bool) -> [a] -> [a]
	myFilter f xs = [x | x <- xs, f x]
	
Explicación:

* f x es la condición que debe cumplir cada elemento x de la lista xs.
* La lista por comprensión [x | x <- xs, f x] genera una nueva lista con los elementos x de xs que cumplen la condición f x.
	
b) **La función map (cuyo tipo es map :: (a -> b) -> [a] -> [b]) que aplica una
función (1° parámetro) a todos los elementos de una lista (2° parámetro) para crear una nueva lista.**

La función map aplica una función a todos los elementos de una lista para crear una nueva lista con los resultados.

	myMap :: (a -> b) -> [a] -> [b]
	myMap f xs = [f x | x <- xs]

Explicación:

* f x aplica la función f a cada elemento x de la lista xs.
* La lista por comprensión [f x | x <- xs] genera una nueva lista con los resultados de aplicar f a cada elemento de xs.

c) **La función concat (cuyo tipo es concat :: [[a]] -> [al) que concatena una lista de listas de elementos en una única lista.**

La función concat toma una lista de listas y las concatena en una única lista.

	myConcat :: [[a]] -> [a]
	myConcat xss = [x | xs <- xss, x <- xs]

Explicación:

* xs <- xss itera sobre cada lista xs dentro de la lista de listas xss.
* x <- xs itera sobre cada elemento x dentro de cada sublista xs.
* La lista por comprensión [x | xs <- xss, x <- xs] genera una lista que contiene todos los elementos de las sublistas concatenados en una sola lista.

d) **Una función parejas (cuyo tipo sea parejas :: [a] -> [(a,a)]) que devuelva la
lista de todas las parejas diferentes que se pueden formar con los elementos de la lista de entrada. Por ejemplo:**

	> parejas [1,2,3]
	[(1,1), (1,2), (1,3), (2,1), (2,2), (2,3), 13,1), (3,2), (3,3)]
	
La función parejas devuelve la lista de todas las parejas posibles que se pueden formar con los elementos de la lista de entrada, incluyendo las combinaciones con elementos repetidos.

	parejas :: [a] -> [(a, a)]
	parejas xs = [(x, y) | x <- xs, y <- xs]

Explicación:

* x <- xs itera sobre cada elemento x de la lista xs.
* y <- xs itera nuevamente sobre cada elemento y de la lista xs.
* La lista por comprensión [(x, y) | x <- xs, y <- xs] genera todas las combinaciones posibles de parejas (x, y).

---

**Responda las siguientes preguntas:**

* **Compare las listas de Haskell con las listas de Prolog. Describa su funcionamiento, semejanzas y diferencias.**

	1. Funcionamiento de las Listas:

	**Haskell:**

	* Definición y Sintaxis: 

		En Haskell, una lista es una secuencia ordenada de elementos del mismo tipo. Se denotan entre corchetes y separados por comas, por ejemplo: [1, 2, 3].

	* Operaciones Básicas:

		* Concatenación: Se utiliza el operador ++ para concatenar listas.
		* Acceso: El operador !! se usa para acceder a un elemento en una posición específica.
		* Cons (:): El operador : se usa para agregar un elemento al principio de una lista. Por ejemplo, 1 : [2, 3] resulta en [1, 2, 3].
	* Inmutabilidad: Las listas en Haskell son inmutables, lo que significa que una vez que se crea una lista, no se puede modificar.
	* Tipado: Haskell es un lenguaje fuertemente tipado y tiene inferencia de tipos, por lo que cada lista tiene un tipo específico, como [Int] para una lista de enteros.

	**Prolog:**

	* Definición y Sintaxis: En Prolog, una lista es también una secuencia ordenada de elementos, pero su sintaxis es diferente. Se escribe entre corchetes y los elementos están separados por comas, por ejemplo: [1, 2, 3].
	* Operaciones Básicas:
		* Concatenación: La concatenación de listas se realiza a menudo mediante recursión y patrones.
		* Unificación: Las listas en Prolog utilizan unificación para patrones. Por ejemplo, [H|T] = [1, 2, 3] unifica H con 1 y T con [2, 3].
		* Operador de Cabeza/Tail (|): El operador | divide una lista en su cabeza (primer elemento) y su cola (resto de la lista).
	* Mutabilidad: En Prolog, aunque las listas pueden parecer mutables en ciertas construcciones, la operación realmente genera nuevas listas en el proceso de la unificación.
	* Sin Tipado Estricto: Prolog no es un lenguaje fuertemente tipado, lo que significa que las listas pueden contener elementos de diferentes tipos, como [1, 'a', X].

	2. Semejanzas:

		* Ambas utilizan una notación similar para listas ([1, 2, 3]).
		* Tanto Haskell como Prolog utilizan el concepto de cabeza y cola para operar sobre listas ([H|T] en Prolog y x:xs en Haskell).
		* Las listas en ambos lenguajes pueden ser procesadas de manera recursiva.

	3. Diferencias:

		* Tipado: Haskell es fuertemente tipado, por lo que una lista debe contener elementos del mismo tipo, mientras que Prolog permite listas con elementos de tipos mixtos.
		* Inmutabilidad: En Haskell, las listas son inmutables, lo que significa que cualquier operación sobre una lista genera una nueva lista, mientras que en Prolog, el concepto de mutabilidad es más flexible debido a la unificación.
		* Operaciones: En Haskell, las operaciones de listas como la concatenación y la indexación están integradas como funciones básicas del lenguaje, mientras que en Prolog, estas operaciones suelen requerir la definición de reglas adicionales y el uso de recursión.

* **Defina ycompare los lenguajes fuertemente tipados ylos débilmente tipados. Ponga un ejemplo de cada uno de ellos y comente las ventajas e inconvenientes de ambas aproximaciones.**

	1. Lenguajes Fuertemente Tipados:

		* Definición: En un lenguaje fuertemente tipado, el tipo de una variable es estrictamente respetado. No se permiten operaciones entre tipos incompatibles sin conversión explícita.
		* Ejemplo: Haskell.
			* En Haskell, si se tiene una variable de tipo Int, no se puede realizar una operación que espera un tipo String sin hacer una conversión explícita.
			* Ventajas:
				* Seguridad: Previene errores de tipo, lo que resulta en un código más robusto y predecible.
				* Legibilidad: Facilita la comprensión del código, ya que el tipo de cada variable es claro y consistente.
			* Inconvenientes:
				* Flexibilidad: Puede ser menos flexible para operaciones donde se desearía una conversión automática.
				* Verbosidad: A veces se requiere escribir código adicional para manejar conversiones entre tipos.

	2. Lenguajes Débilmente Tipados:

		* Definición: En un lenguaje débilmente tipado, el tipo de una variable puede ser fácilmente cambiado o interpretado de manera flexible, lo que permite operaciones entre tipos diferentes sin conversiones explícitas.
		* Ejemplo: JavaScript.
			* En JavaScript, se pueden sumar un número y una cadena sin conversión explícita, y el lenguaje manejará la conversión automáticamente, como 5 + '5' que resultará en '55'.
			* Ventajas:
				* Flexibilidad: Facilita la escritura de código más rápido y permite operar de manera más directa con diferentes tipos.
				* Menor Verbosidad: Reduce la necesidad de conversiones explícitas, lo que puede simplificar el código en ciertos contextos.
			* Inconvenientes:
				* Seguridad: Puede conducir a errores sutiles y difíciles de detectar, ya que las conversiones automáticas pueden no siempre producir el resultado esperado.
				* Mantenimiento: El código puede ser más difícil de mantener y depurar, ya que el tipo de las variables no siempre es claro o predecible.

---

* **Defina en qué consiste el léxico, la sintaxis y la semántica de un lenguaje de programación. Ilustre las definiciones con algún ejemplo**

	* El léxico o conjunto de las "palabras" o unidades léxicas que son las cadenas de caracteres significativas del lenguaje, también denominados tokens. También son unidades léxicas los identificadores, los símbolos especiales de operadores y los símbolos de puntuación. Por ejemplo, una sentencia condicional en C tendría como tokens las cadenas if y else.
	* La sintaxis conlleva la descripción de los diferentes componentes del lenguaje y de sus combinaciones posibles. Para ello se utilizan las gramáticas libres de contexto. Por ejemplo, la sentencia if en C se define por:

			<sentencia if> ::= if ( <expresion> ) <sentencia> [else < sentencia>]
	
	* La semántica expresa los efectos de la ejecución en un contexto determinado. Siguiendo con el ejemplo del if en C, y según Kernighan y Richie:

		"Una sentencia if es ejecutada, primero, evaluando su expresión, que debe ser de tipo aritmético o apuntador, y si el resultado de la comparación de la expresión es cierta, entonces se ejecuta la sentencia que sigue a la expresión. Si existe una parte else y el resultado de la expresión no es cierto, entonces se ejecuta la sentencia que sigue al else."

* **Indique de qué se ocupa cada una de las fases de análisis que llevan a cabo los intérpretes y compiladores y relaciónelas con los conceptos definidos en la pregunta anterior**

	Las fases que tanto un intérprete como un compilador deben llevar a cabo son:  	1. Primero, un analizador léxico debe identificar los tokens del programa, ya que inicialmente el programa se entiende como una secuencia de caracteres.
	2. A continuacion, un analizador sintáctico o gramatical identifica las estructuras correctas que definen las secuencias de tokens.
	3. Finalmente, un analizador semántico asigna el significado de forma suficinete para su ejecución o la obtención del programa objetivo.

---

**Imaginemos un lenguaje que admita definición de subprogramas,
pero todos ellos al mismo nivel (como en el caso de C), es decir, que no puedan anidarse subprogramas unos dentro de otros (como sí permite Pascal).**

* **Describa qué información deberá almacenarse en los registros de activación de los subprogramas, de forma que pueda gestionarse correctamente la ejecución.**

	Debemos estrucutrar los registros de activación en una pila de registros de activación. Cada vez que se llama a un subprograma, se apila a un nuevo registro de activación que será liberado cuando se termine la ejecución del subprograma.
	
	En cada uno de estos registros de activación será necesario almacenar la misma información ya almacenada en un registro de activación para un ambiente estático, esto es, variables locales, dirección de retorno del subprograma, y parámetros.
	
	Sin embargo esto no basta, ya que como ahora los registros de activación son creados de forma dinámica también es necesario almacenar la información sobre dónde comienza el registro de activación del subprograma que actualmente se está ejecutando. Esta información se debe alamcenar externamente a la pila de registros de activación, normalmente en un registro que se conoce con el nombre de puntero de ambiente (o environment pointer, ep).
	
	Además, cada registro de activación deberá almacenar adicionalmente la dirección de inicio del registro de activiación del subprograma invocador, de forma que se pueda restaurar el valor del puntero de ambiente al salir del subprograma y volver al invocador. Esta información debe estar presente en todos los registros de activación y se conoce con el nom,bre de enlace de control (o enlace dinámico), pues se encarga de devolver el conrtol al subprograma que llamó al actual.

* **Describa cómo se organizan esos registros de activación para una correcta ejecución de los programas. Ponga un ejemplo en el que se vea esta organización.**

	Pág. 272, 273, 274, 275

---

**Para las siguientes expresiones:**

a) 23 * (2 + 11) - 1

b) ( 4 - 1 ) / ( 7 - 5 )

**Detalle:**

1. **El árbol sintáctico abstracto de la expresión.**

a)

		  -
	     / \
	   *    1
	  / \
	 23  +
	    / \
	   2   11

b)

		  /
	     / \
	   -    -
	  / \  / \
	 4   1 7  5


2. **A partir del árbol sintáctico, construya la expresión postfija.**

a)

		23 2 11 + * 1 -
		
b)

		4 1 - 7 5 - /

3. **A partir de la expresión postfija, indique al lista de instrucciones de una máquina pila para poder evaluar la expresión.**

	Para evaluar expresiones en notación postfija utilizando una máquina de pila, la máquina sigue una serie de instrucciones que operan sobre una pila de operandos. Las instrucciones básicas son:

* PUSH X: Coloca el valor X en la pila.
* ADD: Suma los dos valores en la parte superior de la pila y empuja el resultado en la pila.
* SUB: Resta los dos valores en la parte superior de la pila (resta el segundo valor al primero).
* MUL: Multiplica los dos valores en la parte superior de la pila y empuja el resultado en la pila.
* DIV: Divide los dos valores en la parte superior de la pila (divide el segundo valor por el primero) y empuja el resultado en la pila.

**Expresión 1: 23 2 11 + * 1 -**

* PUSH 23: Coloca 23 en la pila.
* PUSH 2: Coloca 2 en la pila.
* PUSH 11: Coloca 11 en la pila.
* ADD: Suma los dos valores superiores de la pila (2 + 11), coloca el resultado (13) en la pila.
* MUL: Multiplica los dos valores superiores de la pila (23 * 13), coloca el resultado (299) en la pila.
* PUSH 1: Coloca 1 en la pila.
* SUB: Resta los dos valores superiores de la pila (299 - 1), coloca el resultado (298) en la pila.

**Expresión 2: 4 1 - 7 5 - /**

* PUSH 4: Coloca 4 en la pila.
* PUSH 1: Coloca 1 en la pila.
* SUB: Resta los dos valores superiores de la pila (4 - 1), coloca el resultado (3) en la pila.
* PUSH 7: Coloca 7 en la pila.
* PUSH 5: Coloca 5 en la pila.
* SUB: Resta los dos valores superiores de la pila (7 - 5), coloca el resultado (2) en la pila.
* DIV: Divide los dos valores superiores de la pila (3 / 2), coloca el resultado (1.5) en la pila.

c) **Responda las siguientes preguntas: ¿con qué recorrido del árbol sintáctico abstracto se corresponde la expresión postfija? ¿Y la infija?**

**Recorrido del Árbol Sintáctico para la Expresión Postfija**

* Postfija (Postorden): La expresión postfija se corresponde con un recorrido postorden del árbol sintáctico abstracto.
En un recorrido postorden (postorder traversal):

	Se visita primero el subárbol izquierdo. Luego se visita el subárbol derecho. Finalmente, se procesa el nodo raíz. Este orden de recorrido genera una secuencia donde los operandos se colocan antes de sus respectivos operadores, lo cual es la característica fundamental de la notación postfija.
	
**Recorrido del Árbol Sintáctico para la Expresión Infija**

* Infija (Inorden): La expresión infija se corresponde con un recorrido inorden del árbol sintáctico abstracto. En un recorrido inorden (inorder traversal):

	Se visita primero el subárbol izquierdo. Luego se procesa el nodo raíz. Finalmente, se visita el subárbol derecho. Este recorrido genera la notación estándar o infija, donde los operadores están colocados entre sus operandos, lo cual es lo que estamos acostumbrados a ver en las expresiones aritméticas tradicionales.

---

* **¿Cuál es el objetivo de un análisis léxico?**
	
	El análisis léxico identifica las secuencias de tokens "correctas" en el lenguaje, y tras el análisis sintáctico, son las que forman la estructura sintáctica de un programa (combinaciones correctas" de tokens).

* **Defina el principio de subcadena más larga**

	En el caso de los identificadores (cadenas deifinidas normalmente por el programador y que no pueden ser palabras reservadas), su longitud puede estar predefinida en algunos lenguajes, ser arbitraria o solo ser significativos los primeros caracteres. Para evitar esta ambigüedad, se utiliza el principio de subcadena de mayor longitud que determina que un token es la cadena más larga posible de caracteres hasta que haya un delimitador de nuevo token: un caracter en blanco, o algún símbolo especial que indique sin ambigüedad su final.

* **¿Se adhiere el lenguaje C a dicho principio? Razone su respuesta**

	Sí y lo vamos a analizar a través de una comparación con el lenguaje Fortran. En la sentencia Fortran siguiente, se muestra la complejidad inherente a la no utilización de la regla de subcadena de mayor longitud:
	
	`DO 99 I = 1.10`

	En ella se asigna el valor `1.10` a la variable `DO99I` porque Fortran no reconoce los blancos, ni tiene palabras reservadas absolutas, por lo que pueden ser identificadores y solo la sintaxis desambiguará. En concreto es una sentencia que contiene tres tokens, `DO99I`, la signación y la constante real `1.10` y es equivalente a la sentencia en C, `DO99I = 1.10`.

* **¿En qué consiste una gramática libre de contexto?**

	Una **gramática libre de contexto** está formada por un alfabeto de **símbolos no terminales** o vocabulario de nombres de estructura, *V_NT*, otro alfabeto de **símbolos terminales**, *VT* (denominados tokens), un símbolo no terminal distinguido denominado **símbolo inicial**, *S*, y un **conjunto de reglas**, las cuales están formadas por un único símbolo no terminal que forma la parte izquierda de la regla, a continuación el matasímbolo ->, y finalmente la parte derecha de la regla, formada por símbolos terminales o no terminales. Támbien podemos destacar que el lenguaje que define una gramática es el conjunto de todas las cadenas de símbolos terminales para las cuales existe una derivación aplicando las reglas de la gramática.

	Un ejemplo de gramática sería:

	* VT = {Amelia, Manolo, es , la, el, niña, niño, buena, bueno}
	* V_NT = {FRASE, GN, GV, ART, NC, NPROPIO, ATRIB, ADJ}
	* S = FRASE
	* Reglas:
	
		* R1: FRASE -> GN GV
		* R2: GN -> ART NC
		* R3: GN -> NPROPIO
		* R4: GV -> es ATRIB
		* R5: ATRIB -> ADJ
		* R6: ART -> el
		* R7: ART -> la
		* R8: NC -> niña
		* R9: NC -> niño
		* R10: NPROPIO -> Amelia
		* R11: NPROPIO -> Manolo
		* R12: ADJ -> buena
		* R13: ADJ -> bueno

* **Describa el uso de metasímbolos permitidos en la descripción de gramáticas BNF**

	Los metasímbolos permitidos en la notación BNF son:
	
	* `::=`: de separación entre la parte izquierda y derecha de una regla.
	* `|`: de alternativa (se puede elegir únicamente uno de los elementos que separa). 

---

* **Simplicidad:**

	Es un principio que se refiere sintácticamente a que cada concepto del lenguaje se presente de forma única y legible y semánticamente que contiene el menor número posible de conceptos y estructuras con reglas de combinación sencillas. La simplicidad es una característica del diseño del lenguaje C, aunque en realidad es consecuencia de otro principio prioritario en C: conseguir código objetivo eficiente y compiladores pequeños.

* **Expresividad:**

	Es la facilidad con la que el lenguaje de programación permite expresar procesos y estructuras complejas. Uno de los mecanismos más expresivos es la recursividad.

* **Extensibilidad:**

	Es la propiedad relacionada con la posibilidad de añadir nuevas características a un lenguaje, como nuevos tipos de datos o nuevas funciones a la biblioteca. O añadir palabras clave y constructores al traductor como en los lenguajes declarativos, que tienen pocas primitivas y en los que se van incorporando constructores en el ambiente de ejecución, lo que es difícil de realizar en un legnuaje imperativo. Un ejemplo sería la definición de operadores en Haskell como en el siguiente caso, en el que se indica que un nuevo operador `+++` debe usarse como infijo y con asociatividad a la derecha y un nivel de precedencia 6:
	
	`infixr 6 +++`

## Conceptos

* **Lenguaje Imperativo:** Un lenguaje imperativo es un tipo de lenguaje de programación que utiliza declaraciones que cambian el estado del programa. Este paradigma de programación se basa en dar órdenes explícitas a la computadora sobre cómo realizar una tarea, a través de una secuencia de instrucciones que modifican el estado del programa paso a paso. Los lenguajes imperativos son los más tradicionales y están basados en el modelo de ejecución secuencial. Ejemplo: Un ejemplo de lenguaje imperativo es C. En C, las instrucciones se ejecutan en el orden en que se escriben, y los estados se modifican a través de variables y estructuras de control como bucles y condicionales.
* **Paradigma de Computación que no sea Von Neumann:** Un paradigma de computación que no se basa en el modelo de Von Neumann es el paradigma funcional. En el paradigma funcional, la computación se lleva a cabo mediante la evaluación de funciones matemáticas y evita cambiar estados y datos mutables. Este paradigma se basa en funciones puras y la aplicación de funciones a argumentos. Ejemplo: Haskell es un lenguaje funcional puro que sigue este paradigma.
* **Paradigma de Programación Orientada a Objetos:** El paradigma de programación orientada a objetos (POO) se basa en el concepto de "objetos", que son instancias de clases. Las clases definen estructuras de datos y métodos para manipular esos datos. La POO se centra en la encapsulación, la herencia y el polimorfismo. Ejemplo: Java es un lenguaje ampliamente utilizado que sigue el paradigma de POO.
* **Programación Funcional:** La programación funcional es un paradigma de programación que trata la computación como la evaluación de funciones matemáticas y evita el uso de estados mutables y efectos secundarios. Se basa en funciones puras, inmutabilidad y composición de funciones. Ejemplo: Scala es un lenguaje que soporta programación funcional.
* **Compilador:** Un compilador es un programa que traduce código fuente escrito en un lenguaje de programación de alto nivel a un lenguaje de bajo nivel, típicamente código máquina, que puede ser ejecutado directamente por una computadora. El proceso de compilación generalmente incluye varias etapas, como análisis léxico, análisis sintáctico, análisis semántico, optimización de código y generación de código. Ejemplo: El compilador GCC (GNU Compiler Collection) es un compilador ampliamente utilizado para C, C++, y otros lenguajes.
* **Referencia no local:** n el contexto de los subprogramas, una referencia no local se refiere a una referencia a una variable que no está definida dentro del alcance léxico del subprograma, pero que es utilizada dentro de él. Esto significa que la variable no está declarada como local en el subprograma ni se pasa como parámetro. Por ejemplo, en un lenguaje que permite el anidamiento de subprogramas, si un subprograma interno hace referencia a una variable definida en un subprograma externo, esa variable se considera una referencia no local dentro del subprograma interno.
* **Subprograma en Forma Cerrada:** Un subprograma en forma cerrada, también conocido como clausura (closure) en algunos lenguajes de programación, es un concepto relacionado con el ámbito léxico de las variables en un subprograma. Una clausura es una combinación de un subprograma y el entorno léxico en el que se define, lo que permite que el subprograma acceda y haga referencia a las variables definidas en su alcance léxico, incluso después de que ese alcance léxico haya terminado. ==En resumen, un subprograma en forma cerrada es un subprograma que tiene acceso a variables locales de otro ámbito léxico en el momento de su definición==.
* **Parámetros Formales:** Los parámetros formales son los nombres de las variables que se declaran en la definición de un subprograma para representar los valores que se pasarán a ese subprograma cuando sea llamado. Estos parámetros actúan como marcadores de posición para los valores reales que se pasarán al subprograma durante la llamada. Por ejemplo, en la definición de una función en muchos lenguajes de programación, los parámetros formales se especifican entre paréntesis después del nombre de la función, como en funcion(parametro1, parametro2).
* **Parámetros Actuales:** Los parámetros actuales son los valores reales que se pasan a un subprograma cuando se realiza una llamada a ese subprograma. Estos valores reales se asignarán a los parámetros formales correspondientes en la definición del subprograma durante la ejecución. Por ejemplo, si tenemos una función suma(a, b), donde a y b son parámetros formales, y llamamos a esta función con suma(3, 5), entonces 3 y 5 son los parámetros actuales que se pasan a la función para los parámetros formales a y b, respectivamente.
* **Ambigüedad sintáctica:** la ambigüedad sintáctica ocurre cuando una cadena de entrada puede tener más de una interpretación bajo la gramática de un lenguaje. Esto puede causar problemas en la comprensión y en el análisis de programas, ya que una misma cadena podría ser interpretada de diferentes maneras. En general, se prefiere evitar la ambigüedad en las gramáticas para simplificar el proceso de análisis sintáctico y evitar resultados no deseados.
* **Derivación por la izquierda:** La derivación por la izquierda es un concepto utilizado en el análisis sintáctico de lenguajes formales, como los definidos por gramáticas libres de contexto. En una derivación por la izquierda, se aplican las reglas de producción de una gramática a una cadena de entrada comenzando por el símbolo no terminal más a la izquierda y reemplazando ese símbolo por los símbolos terminales y no terminales correspondientes en una secuencia de pasos hacia la izquierda. La derivación por la izquierda se utiliza en el análisis sintáctico ascendente, como en el método de análisis sintáctico LR, y puede ser útil para construir árboles sintácticos y realizar otras tareas de análisis sintáctico.
* **Alcance léxico:** El alcance léxico, también conocido como ámbito léxico o scope en inglés, es un concepto importante en la teoría de lenguajes de programación que describe el conjunto de lugares en un programa donde se puede hacer referencia a una variable y dónde esa referencia se refiere a la misma variable.
* **Visibilidad:** La visibilidad en programación se refiere a la accesibilidad de variables, funciones, o cualquier otro identificador en un programa desde diferentes partes del código. Es decir, determina desde qué partes del programa se puede acceder a un identificador y utilizarlo.
* **Ocultamiento:** El ocultamiento, también conocido como encapsulamiento o hiding en inglés, es un principio de la programación orientada a objetos que se refiere a la idea de restringir el acceso directo a ciertos componentes internos de un objeto y permitir el acceso solo a través de métodos específicos proporcionados por la interfaz pública del objeto. En el contexto de la programación orientada a objetos, el ocultamiento implica que los detalles de implementación de un objeto, como sus variables internas o métodos privados, están ocultos y no son accesibles desde fuera del objeto. En su lugar, se proporciona una interfaz pública que define cómo interactuar con el objeto.
* **Verificación de tipos:** La verificación de tipos es un proceso que se lleva a cabo durante la compilación o ejecución de un programa para garantizar que las operaciones y asignaciones se realicen con tipos de datos compatibles. Esto implica verificar que cada expresión y operación en el programa esté correctamente tipada de acuerdo con las reglas del lenguaje de programación. Si una expresión viola estas reglas, se genera un error de tipo.
* **Inferencia de tipos:** La inferencia de tipos es un mecanismo utilizado en algunos lenguajes de programación para deducir automáticamente los tipos de datos de las expresiones en lugar de requerir que el programador los especifique explícitamente. En la inferencia de tipos, el compilador o intérprete analiza el código y deduce los tipos de datos de las variables y expresiones en función de cómo se utilizan en el programa.
* **Cómo la inferencia de tipos puede ayudar a resolver la sobrecarga de operadores:** La sobrecarga de operadores es una característica que permite definir múltiples versiones de un operador para diferentes tipos de datos o combinaciones de tipos. Sin embargo, esto puede llevar a una ambigüedad en algunos casos si el compilador no puede determinar cuál versión del operador utilizar en una expresión. La inferencia de tipos puede ayudar a resolver este problema al deducir automáticamente los tipos de datos de las expresiones. Por ejemplo, en un lenguaje con inferencia de tipos, si una expresión utiliza un operador aritmético como +, el compilador puede inferir los tipos de los operandos y seleccionar la versión apropiada del operador para esos tipos. Esto elimina la necesidad de que el programador especifique los tipos de manera explícita y reduce la posibilidad de ambigüedad en la selección de operadores.
* **Evaluación Estricta:** En la evaluación estricta, también conocida como evaluación ansiosa o eager evaluation en inglés, las expresiones se evalúan completamente antes de pasar a la siguiente operación. Esto significa que todos los operandos de una expresión se evalúan antes de que la expresión en sí misma sea evaluada. Esto puede llevar a la evaluación de expresiones que pueden no ser necesarias para el resultado final.

	```python
	# En evaluación estricta, se evalúan ambos operandos
	# de la suma antes de realizar la operación.
	resultado = 3 + (5 / 0)  # Esto generará un error
	# de división por cero durante la evaluación de la división.
	```
	
* **Evaluación no Estricta:** En la evaluación no estricta, también conocida como evaluación perezosa o lazy evaluation en inglés, las expresiones no se evalúan completamente a menos que sea necesario. Esto significa que los operandos se evalúan solo cuando son necesarios para producir el resultado final. Esto puede conducir a una evaluación más eficiente en términos de recursos computacionales.

	```haskell
	-- En Haskell, la evaluación no estricta permite definir 
	-- listas infinitas que se evalúan solo cuando se necesitan.
	-- La siguiente función define una lista infinita de números naturales.
	naturales = [1..]
	
	-- Solo se evalúan los primeros tres elementos de la lista infinita.
	primeros_tres = take 3 naturales
	```

* **Evaluación Perezosa:** La evaluación perezosa es una forma particular de evaluación no estricta en la que las expresiones se evalúan solo cuando el valor resultante es necesario para realizar una operación. Esto significa que las expresiones se evalúan solo a medida que se necesitan, lo que puede mejorar la eficiencia en términos de consumo de memoria y recursos computacionales.

	```python
	# En Python, la evaluación perezosa se puede lograr utilizando generadores.
	# La siguiente función genera números pares infinitos solo cuando se solicitan.
	def numeros_pares():
	    n = 0
	    while True:
	        yield n
	        n += 2
	
	# Solo se evalúan los primeros cinco números pares.
	pares = numeros_pares()
	primeros_cinco = [next(pares) for _ in range(5)]
	```

* **Evaluación normal = Evaluación perezosa:** La evaluación normal es una estrategia de evaluación en la cual las expresiones se evalúan solo cuando su valor es necesario. Esta estrategia se utiliza en lenguajes que soportan evaluación diferida o perezosa. En la evaluación normal, las funciones se llaman sin evaluar primero sus argumentos, y estos se evalúan solo cuando son utilizados dentro de la función.
* **Evaluación en Cortocircuito:** La evaluación en cortocircuito, comúnmente utilizada en lenguajes de programación con operadores lógicos como and y or, es una forma de evaluación no estricta en la que la evaluación se detiene tan pronto como se determina el resultado final. En el caso de and, si el primer operando es False, la expresión completa será False, por lo que el segundo operando no se evaluará. Para or, si el primer operando es True, la expresión completa será True y el segundo operando no se evaluará.

	```python
	# En Python, la evaluación en cortocircuito 
	# se utiliza con operadores lógicos como `and` y `or`.
	# En este ejemplo, solo se evalúa la primera parte de la expresión `and`.
	# Si la primera parte es False, la segunda parte 
	# no se evaluará.
	resultado = (5 > 3) and (2 / 0)  # Esto no generará un 
	# error de división por cero porque la segunda parte no se evalúa.
	```
	
* **Transparencia referencial:** La transparencia referencial es una propiedad de las expresiones en un lenguaje de programación que implica que una expresión puede ser reemplazada por su valor sin cambiar el comportamiento del programa. Esto significa que la expresión siempre produce el mismo resultado dado el mismo conjunto de entradas, y no tiene efectos secundarios. Ejemplo: En Haskell, las funciones puras son transparentes referencialmente.	
* **Tabla de Símbolos:** Una tabla de símbolos es una estructura de datos utilizada por un compilador o intérprete para mantener un registro de los símbolos definidos en un programa y su información asociada, como el tipo, el alcance y la ubicación en la memoria. Los símbolos incluyen identificadores como nombres de variables, nombres de funciones, nombres de tipos y cualquier otro identificador definido por el usuario en el código fuente. La tabla de símbolos es esencial para varias etapas del proceso de compilación o interpretación, como el análisis léxico, el análisis sintáctico y la generación de código. Proporciona información crucial sobre los símbolos definidos en el programa y facilita la resolución de referencias, el análisis semántico y la generación de código eficiente.
* **Regla de Alcance Léxico en un Lenguaje Estructurado en Bloques:** La regla de alcance léxico en un lenguaje estructurado en bloques establece que el alcance de una variable está determinado por la estructura de bloques en el código fuente. En otras palabras, una variable está disponible para su uso dentro del bloque donde se declara y dentro de cualquier bloque anidado dentro de ese bloque, pero no está disponible fuera de ese bloque. Esta regla se basa en la estructura del código fuente y no en el flujo de ejecución del programa. Cada bloque delimitado por llaves {} crea un nuevo ámbito, y las variables declaradas dentro de ese bloque solo son visibles dentro de ese ámbito y en ámbitos anidados.

	```java
	public class Ejemplo {
	    public static void main(String[] args) {
	        int x = 10;
	        if (x > 5) {
	            int y = 20;
	            System.out.println(x); // Se puede acceder a x
	            System.out.println(y); // Se puede acceder a y
	        }
	        // System.out.println(y); // Esto generaría 
	        // un error, ya que y está fuera de alcance.
	    }
	}
	```
	
* **Polimorfismo y Funciones de Orden Superior:**
	* **Polimorfismo:** El polimorfismo es un concepto que permite que una misma función o método pueda actuar de diferentes maneras según el tipo de datos sobre el que se aplica. Puede tomar diferentes formas, como el polimorfismo de subtipos, el polimorfismo paramétrico y el polimorfismo de inclusión. En el contexto de la programación funcional, el polimorfismo paramétrico es particularmente relevante, donde las funciones pueden ser genéricas y operar sobre diferentes tipos de datos de manera uniforme.
	* **Funciones de Orden Superior:** Las funciones de orden superior son aquellas funciones que pueden tomar otras funciones como argumentos y/o devolver funciones como resultado. En la programación funcional, las funciones de orden superior son una característica fundamental que permite la composición de funciones y la abstracción de patrones de computación.

	**Relación:** Las funciones de orden superior pueden proporcionar una forma de implementar el polimorfismo paramétrico en la programación funcional. Al tomar funciones como argumentos, estas funciones pueden operar de manera uniforme sobre diferentes tipos de datos sin necesidad de definir múltiples versiones de la misma función para cada tipo de dato.
	
	**Ejemplo:** En Haskell, la función map es un ejemplo de función de orden superior que toma una función y una lista como argumentos y devuelve una lista donde la función dada se ha aplicado a cada elemento de la lista.
	
* **Currificación y Aplicación Parcial:**
	* **Currificación:** La currificación es el proceso de transformar una función que toma múltiples argumentos en una secuencia de funciones que toman un solo argumento. Esto permite la aplicación parcial de la función, es decir, la aplicación de la función a menos argumentos de los que especifica su aridad original.
	* **Aplicación Parcial:** La aplicación parcial es un concepto que implica la aplicación de una función a algunos, pero no a todos, sus argumentos. Esto resulta en una nueva función que toma menos argumentos que la función original. La aplicación parcial es una consecuencia directa de la currificación.

	**Relación:** La currificación y la aplicación parcial están estrechamente relacionadas en el sentido de que la currificación permite la aplicación parcial de funciones. La currificación transforma una función de múltiples argumentos en una serie de funciones de un solo argumento, lo que facilita la aplicación parcial, donde se suministran solo algunos de los argumentos de la función original.
	
	**Ejemplo:** En Haskell, la función add toma dos argumentos y devuelve su suma. La currificación permite aplicarla parcialmente suministrando solo uno de los dos argumentos.
	
* **Sentencia if-then-else:** La sentencia if-then-else es una estructura de control utilizada en muchos lenguajes de programación para tomar decisiones basadas en condiciones booleanas. Consiste en la palabra clave if, seguida de una expresión booleana entre paréntesis, seguida de un bloque de código que se ejecutará si la expresión es verdadera (el then), seguida de la palabra clave else y otro bloque de código que se ejecutará si la expresión es falsa.
* **Expresión if-then-else:** La expresión if-then-else es una construcción que se encuentra en algunos lenguajes de programación que permite devolver un valor basado en una condición booleana. En lugar de ser una estructura de control, como la sentencia if-then-else, es una expresión que evalúa una condición y devuelve un valor dependiendo de si la condición es verdadera o falsa.
	* **Similitudes:**
		* Ambas están diseñadas para tomar decisiones basadas en una condición booleana.
		* Tanto la sentencia if-then-else como la expresión if-then-else permiten ejecutar diferentes bloques de código o devolver diferentes valores dependiendo del resultado de la condición.
	* **Diferencias:**
		* La sentencia if-then-else es una estructura de control que permite ejecutar bloques de código dependiendo de una condición, mientras que la expresión if-then-else es una expresión que devuelve un valor dependiendo de una condición.
		* La sentencia if-then-else se utiliza cuando se necesita ejecutar diferentes bloques de código, mientras que la expresión if-then-else se utiliza cuando se necesita devolver diferentes valores.
		* La sentencia if-then-else puede tener múltiples cláusulas elif (else if) para manejar múltiples condiciones, mientras que la expresión if-then-else solo maneja una condición verdadera y una falsa.

* **Expresividad:** La expresividad en el diseño de un lenguaje de programación se refiere a la capacidad del lenguaje para permitir que los programadores expresen sus ideas y soluciones de manera clara, concisa y natural. Un lenguaje expresivo facilita la escritura de código legible y comprensible, lo que puede mejorar la productividad y la calidad del software. Esto se logra mediante la inclusión de características y constructos que reflejen la semántica del problema que se está resolviendo, minimizando la necesidad de código boilerplate o redundante.
	* Ejemplo: Un ejemplo de lenguaje de programación que se valora por su expresividad es Python. Python está diseñado para ser legible y conciso, lo que lo hace especialmente expresivo. Por ejemplo, su sintaxis limpia y su uso extensivo de palabras clave como if, for, y while hacen que el código sea fácil de entender incluso para aquellos que no están familiarizados con el lenguaje. Además, la flexibilidad del lenguaje y la inclusión de estructuras de datos de alto nivel como listas, diccionarios y conjuntos permiten a los programadores expresar soluciones de manera clara y directa.
* **Extensibilidad:** La extensibilidad en el diseño de un lenguaje de programación se refiere a la capacidad del lenguaje para adaptarse y crecer con el tiempo, permitiendo a los usuarios agregar nuevas funcionalidades o modificar las existentes de manera sencilla y eficiente. Un lenguaje extensible fomenta la innovación y la experimentación al ofrecer mecanismos para la creación de bibliotecas, módulos, y extensiones que pueden ser fácilmente integrados en el ecosistema del lenguaje.
	* Ejemplo: Un ejemplo de lenguaje de programación conocido por su extensibilidad es Lisp. Lisp es famoso por su sintaxis mínima y su capacidad para manipular código como datos. Esta característica permite que los usuarios definan nuevas funciones y macros que extienden el lenguaje de maneras poderosas y a menudo sorprendentes. Los macros Lisp, en particular, proporcionan una forma de transformar el código fuente del programa en tiempo de compilación, lo que permite a los programadores agregar nuevas características y abstracciones de manera eficiente.
* **Interoperabilidad:** La interoperabilidad en el diseño de un lenguaje de programación se refiere a su capacidad para interactuar y comunicarse de manera efectiva con otros lenguajes y sistemas. Un lenguaje interoperable facilita la integración de diferentes componentes de software y la reutilización de código existente al proporcionar mecanismos para intercambiar datos, invocar funciones y compartir recursos de manera transparente y eficiente.
	* Ejemplo: Un ejemplo de lenguaje de programación que prioriza la interoperabilidad es Java. Java se diseñó desde el principio con el objetivo de ser un lenguaje interoperable, y se ha ganado una reputación por su capacidad para integrarse con una amplia variedad de tecnologías y sistemas. La máquina virtual de Java (JVM) proporciona un entorno de ejecución uniforme que permite a los programas Java ejecutarse en cualquier plataforma que admita la JVM, lo que facilita la interoperabilidad entre sistemas heterogéneos. Además, Java ofrece soporte para la invocación de código nativo a través de JNI (Java Native Interface), lo que permite a los programadores acceder a bibliotecas y funciones escritas en otros lenguajes como C y C++.
	
## Ejercicios

---

1. Estamos diseñando un lenguaje fuertemente tipado y queremos que no sea necesario incluir explícitamente las declaraciones de las variables. Entonces:

a) ¿Qué regla no cumpliría este lenguaje? Justifique su respuesta.
b) ¿Qué mecanismo relacionado con los tipos nos permitiría saber cuál es el tipo de una variable utilizada?
c) ¿Cómo podríamos detectar que el uso de una variable provoca un error semántico de tipos?

**¿Qué regla no cumpliría este lenguaje? Justifique su respuesta.**

La regla que no cumpliría este lenguaje es la regla de declaración explícita de variables. En los lenguajes fuertemente tipados que requieren declaraciones explícitas de variables, los programadores deben especificar el tipo de cada variable antes de su uso. Esto permite al compilador verificar que las operaciones realizadas sobre las variables son válidas para su tipo. Al omitir la declaración explícita, el lenguaje no sigue la convención de que el tipo de una variable debe ser conocido y definido antes de su uso.

La omisión de la declaración explícita puede complicar el análisis estático de tipos, ya que el compilador debe deducir los tipos de las variables basándose únicamente en su uso y contexto, en lugar de confiar en declaraciones explícitas proporcionadas por el programador.

**¿Qué mecanismo relacionado con los tipos nos permitiría saber cuál es el tipo de una variable utilizada?**

El mecanismo que nos permitiría conocer el tipo de una variable utilizada sin declaración explícita es la inferencia de tipos. La inferencia de tipos es una característica de algunos lenguajes de programación donde el compilador deduce automáticamente el tipo de una variable basándose en cómo se utiliza en el código.

Ejemplo:

En Haskell, un lenguaje funcional con inferencia de tipos, el compilador puede deducir el tipo de una variable sin necesidad de una declaración explícita:

**¿Cómo podríamos detectar que el uso de una variable provoca un error semántico de tipos?**

Para detectar que el uso de una variable provoca un error semántico de tipos en un lenguaje fuertemente tipado con inferencia de tipos, el compilador puede realizar un análisis de tipos durante la compilación. Este análisis incluye:

* Recopilación de información de tipos: Durante el análisis sintáctico y semántico del código, el compilador recopila información sobre el tipo de cada expresión basándose en las operaciones realizadas y las funciones invocadas.
* Verificación de consistencia de tipos: El compilador verifica que las operaciones realizadas sobre las variables sean consistentes con sus tipos deducidos. Si una operación no es válida para el tipo deducido de una variable, se genera un error de tipo.
* Generación de errores de tipos: Si el compilador encuentra una inconsistencia de tipos, genera un error de compilación detallado, indicando la ubicación y la naturaleza del error.

---

1. Dada la siguiente declaración de tipo: 

```
bool b;
```
que declara la variable c como de tipo Booleano, indique razonadamente cuál podría ser la semántica de la sentencia:

```
b : = b + 1;
```

a) En un lenguaje fuertemente tipado como Pascal. 
b) En un lenguaje débilmente tipado como C.

En un lenguaje de programación fuertemente tipado como Pascal, la semántica de la sentencia b := b + 1; sería incorrecta y probablemente resultaría en un error de tipo. Esto se debe a que en Pascal, las conversiones automáticas de tipo no están permitidas y se espera que los tipos de datos se manejen de manera estricta. La variable b se declara como un booleano, lo que significa que solo puede contener los valores true o false. Sumar 1 a un booleano no tiene sentido en Pascal y violaría las reglas de tipos del lenguaje. Por lo tanto, esta sentencia probablemente generaría un error de tipo durante la compilación.

Por otro lado, en un lenguaje de programación débilmente tipado como C, la semántica de la sentencia b := b + 1; sería diferente. En C, las conversiones implícitas de tipo son comunes y permitidas. En este caso, b es una variable booleana, pero en C, un booleano se puede representar como un entero, donde true es equivalente a 1 y false es equivalente a 0. Por lo tanto, esta sentencia podría interpretarse como incrementar el valor de b en 1. Sin embargo, es importante destacar que esta interpretación podría depender del compilador específico y de las configuraciones de compilación, ya que la semántica exacta puede variar entre diferentes implementaciones de C.

---

1. Responda a las siguientes cuestiones:

	a) ¿Cuáles son los **elementos que forman una gramática libre de contexto**? Indique un ejemplo sencillo de gramática, pero que le permita poner los ejemplos de esta pregunta.
	
	b) Defina **derivación de una cadena del lenguaje**. Escriba una derivación de una cadena (no vacía) a partir de la gramática ejemplo del apartado anterior.
	
	c) Defina "aspecto sensible al contexto" y ponga un ejemplo.
	
**A)**

Una **gramática libre de contexto** está formada por un alfabeto de **símbolos no terminales** o vocabulario de nombres de estructura, *V_NT*, otro alfabeto de **símbolos terminales**, *VT* (denominados tokens), un símbolo no terminal distinguido denominado **símbolo inicial**, *S*, y un **conjunto de reglas**, las cuales están formadas por un único símbolo no terminal que forma la parte izquierda de la regla, a continuación el matasímbolo ->, y finalmente la parte derecha de la regla, formada por símbolos terminales o no terminales. Támbien podemos destacar que el lenguaje que define una gramática es el conjunto de todas las cadenas de símbolos terminales para las cuales existe una derivación aplicando las reglas de la gramática.

Un ejemplo de gramática sería:

* VT = {Amelia, Manolo, es , la, el, niña, niño, buena, bueno}
* V_NT = {FRASE, GN, GV, ART, NC, NPROPIO, ATRIB, ADJ}
* S = FRASE
* Reglas:

	* R1: FRASE -> GN GV
	* R2: GN -> ART NC
	* R3: GN -> NPROPIO
	* R4: GV -> es ATRIB
	* R5: ATRIB -> ADJ
	* R6: ART -> el
	* R7: ART -> la
	* R8: NC -> niña
	* R9: NC -> niño
	* R10: NPROPIO -> Amelia
	* R11: NPROPIO -> Manolo
	* R12: ADJ -> buena
	* R13: ADJ -> bueno

**B)**

Una derivación es una secuencia que comienza con el símbolo inicial y en la que en cada paso se sutituye un símbolo no terminal de la cadena en curso por la parte derecha de una regla. Se dice que se tiene una derivación de una cadena del lenguaje cuando todos los símbolos de la cadena en curso son terminales.

* FRASE => (R1) GN GV
* => (R3) NPROPIO GV
* => (R10) Amelia GV
* => (R4) Amelia es ATRIB
* => (R5) Amelia es ADJ
* => (R12) Amelia es buena

**C)**

Con las gramáticas libres de contexto, un símbolo no terminal de la cadena de dericvación en curso, puede sustituirse siempre por cualquier regla de la gramática qe lo contenga en su parte izquierda. Esto es, no hay ninguna restricción para realizar la sustitución, y por lo tanto tampoco restricciones dependientes del contexto. Estos aspectos se denominan **sensibles al contexto** y en el ejemplo anterior, al ser una gramática libre de contexto, no podemos tratar la concordancia de género (ni el número) propias del español.

---

2. Conteste las siguientes preguntas.

	a) Defina el **paso de parámetros por valor-resultado** y el **paso de parámetro por referencia**. Compárelos, indicando similitudes y diferencias y ponga un ejemplo de uso de cada uno de ellos.
	
	b) Imaginemos que un lenguaje de programación imperativo (como C o Pascal) en el que las variables representan una dirección de memoria que puede ser modificada, implementa el **paso de parámetros por nombre**. Ponga un ejemplo de subprograma y de llamada al mismo que muestre que el orden en el que se evalúan los parámetros influye en el resultado de la ejecución.
	
**A)**

**Paso de Parámetros por Valor-Resultado**

El paso de parámetros por valor-resultado, también conocido como "call by value-result" o "copy-in, copy-out", es una estrategia de paso de parámetros utilizada en lenguajes de programación. Bajo este mecanismo, cuando se invoca una función, se realiza una copia del valor del argumento en el parámetro formal de la función (copy-in). Durante la ejecución de la función, se trabaja con esta copia local. Al finalizar la ejecución de la función, el valor del parámetro formal (posiblemente modificado) se copia de vuelta al argumento original (copy-out).

**Paso de Parámetros por Referencia**

El paso de parámetros por referencia, también conocido como "call by reference", es una estrategia de paso de parámetros en la que el argumento pasado a la función es una referencia al valor original, en lugar de una copia del valor. Esto significa que cualquier modificación realizada al parámetro dentro de la función afectará directamente al argumento original.

**Similitudes:**

* **Modificación de Argumentos:** Ambos métodos permiten que una función modifique el valor de los argumentos pasados.
* **Complejidad de Implementación:** Ambos requieren mecanismos en el tiempo de ejecución del lenguaje de programación para gestionar las referencias o copias de los valores de los parámetros.

**Diferencias:**

* **Copia de Valores:** En el paso por valor-resultado, se realizan copias del argumento al inicio y al final de la función, mientras que en el paso por referencia no se realiza ninguna copia.
* **Efectos Temporales:** En el paso por valor-resultado, los cambios en los parámetros formales no afectan al argumento original hasta que la función finaliza. En el paso por referencia, los cambios se reflejan inmediatamente en el argumento original.
* **Eficiencia:** El paso por referencia puede ser más eficiente ya que evita las copias, especialmente para grandes estructuras de datos.

**Ejemplos:**

**Paso de Parámetros por Valor-Resultado (Pseudocódigo):**

```
procedure exampleValueResult(param value_result x)
    x := x + 1

var a := 10
exampleValueResult(a)
print(a)  // Imprimirá 11
```

En este ejemplo, a se copia a x al inicio de la función, x se incrementa en 1 dentro de la función, y el valor de x se copia de vuelta a a al final de la función.

**Paso de Parámetros por Referencia (Pseudocódigo):**

```
procedure exampleReference(ref x)
    x := x + 1

var a := 10
exampleReference(a)
print(a)  // Imprimirá 11
```

En este ejemplo, x es una referencia a a. Cuando x se incrementa dentro de la función, a se actualiza inmediatamente.

---

2. Defina las tres propiedades en que se subdivide el principio de regularidad de un lenguaje de programación y ponga un ejemplo explicativo de cada una.

El principio de regularidad en un lenguaje de programación se refiere a la consistencia y uniformidad en el diseño y la sintaxis del lenguaje. Este principio se puede subdividir en tres propiedades principales: **sintaxis regular, semántica regular y ortogonalidad**. A continuación, se definen estas propiedades y se proporcionan ejemplos explicativos para cada una.

**Sintaxis Regular**

**Definición:** La sintaxis regular implica que las reglas sintácticas del lenguaje son consistentes y predecibles, minimizando las excepciones y las irregularidades. Esto facilita el aprendizaje y el uso del lenguaje, ya que los programadores pueden aplicar un conjunto pequeño de reglas sintácticas a una variedad de contextos sin encontrar excepciones frecuentes.

**Ejemplo:** En Python, las estructuras de control como los bucles y las condiciones siguen una sintaxis muy regular:

```python
# Bucle for
for i in range(10):
    print(i)

# Bucle while
x = 0
while x < 10:
    print(x)
    x += 1
```

Ambos bucles usan una estructura consistente: palabra clave, condición, dos puntos y un bloque indentado. Esta regularidad en la sintaxis facilita el aprendizaje y la legibilidad del código.

**Semántica Regular**

**Definición:** La semántica regular se refiere a la consistencia en el significado y el comportamiento de las construcciones del lenguaje. Las reglas semánticas deben aplicarse de manera uniforme, evitando ambigüedades y excepciones. Esto ayuda a los programadores a predecir cómo se comportará el código y facilita el razonamiento sobre el mismo.

**Ejemplo:** En Java, la semántica de la sobrecarga de operadores es regular y predecible. Si se define una clase que sobrecarga el operador +, se espera que este operador tenga un comportamiento consistente con su uso en otros contextos:

```java
public class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imaginary + other.imaginary);
    }
}
```

El método add define una semántica clara para la suma de números complejos que es consistente con la semántica de la suma de números reales, lo que ayuda a los programadores a entender y usar la clase de manera intuitiva.

**Ortogonalidad**

**Definición:** La ortogonalidad en un lenguaje de programación se refiere a la independencia de las características del lenguaje entre sí. Idealmente, las construcciones y características del lenguaje pueden combinarse libremente sin restricciones o interacciones inesperadas. Esto permite una mayor flexibilidad y expresividad en la programación.

**Ejemplo:** En C, la capacidad de usar cualquier tipo de expresión en la mayoría de los contextos muestra un alto grado de ortogonalidad:

```c
int a = 5;
int b = 10;
int c = (a + b) * 2; // Expresiones aritméticas en cualquier lugar
int array[5] = {1, 2, 3, 4, 5};
int *p = &array[0];
int d = *(p + 2); // Uso de punteros y aritmética de punteros en expresiones
```

En este ejemplo, se pueden utilizar expresiones aritméticas, inicialización de arrays y aritmética de punteros en combinaciones libres, sin restricciones impuestas por el lenguaje, demostrando la ortogonalidad de C.

---

1. Defina y aporte un ejemplo de cada uno de los tres principios en que se divide la eficiencia de un lenguaje de programación.

La eficiencia se organiza en tres principios: eficiencia de trtaducción, de implementación y de programación.

* La **eficiencia de traducción** estipula que el diseño del lenguaje debe permitir el desarrollo de un traductor eficinete y de tamaño razonable. Por ejemplo, Pascal o en C exigen, por diseño, que se declaren las variables antes de su uso, permitiendo un compilador de una sola pasada.
* La **eficiencia de implementación**, es la eficinecia con que se puede escribir un traductor, que a su vez depende de la complejidad del lenguaje. A modo de ejemplo, un caso de fracaso fue el tamaño y complejidad de Ada, el cual era un obstáculo para el desarrollo de compiladores, dificultando de esta forma su disponibilidad y sus uso.
* La **eficiencia de la programación** está relacionada con la rapidez y la facilidad para escribir programas o capacidad expresiva del lenguaje. Un ejemplo sería Prolog, el cual posee una sintaxis concisa, ausencia de declaración de variables e independencia con el mecanismo de ejecución.

---

1. Para cada uno de los siguientes constructores de tipos de datos, se pide:

* Indicar si se trata de un tipo atómico o de un tipo estructurado.
* Definir el funcionamiento del constructor de tipo.
* Dar un ejemplo de implementación del tipo en Haskell, si es posible. Si no es posible, explicar por qué.

	a) Tipo Enumerado.

	b) Rango.

	c) Array.

	d) Unión de tipos.
	
**A) Tipo Enumerado**

* Tipo: Estructurado
* Definición y Funcionamiento:
	
	Un tipo enumerado es un tipo de datos que consiste en un conjunto finito de valores posibles. Cada valor del tipo enumerado es una constante simbólica. Los tipos enumerados son útiles para representar variables que pueden tomar uno de varios valores predefinidos.
	
* Ejemplo en Haskell:

	En Haskell, los tipos enumerados se pueden definir utilizando la sintaxis data.
	
	```haskell
	data Color = Red | Green | Blue
	```
	
	En este ejemplo, Color es un tipo enumerado con tres posibles valores: Red, Green, y Blue.
	
**B) Rango**

* Tipo: Estructurado
* Definición y Funcionamiento:

	Un rango define un intervalo de valores dentro de un tipo ordenado (como números o caracteres). Se utiliza para restringir los valores que una variable puede tomar a un subconjunto específico de un tipo base.

* Ejemplo en Haskell:

	Haskell no tiene un tipo de rango explícito, pero se puede simular utilizando guardas o validaciones.

	```haskell
	isValidAge :: Int -> Bool
	isValidAge age = age >= 0 && age <= 120
	```
	
	En este ejemplo, isValidAge define un rango válido para la edad, aunque no crea un nuevo tipo de datos, ilustra cómo restringir valores dentro de un rango.
	
**C) Array**	

* Tipo: Estructurado
* Definición y Funcionamiento:

	Un array es una estructura de datos que contiene una colección de elementos del mismo tipo, accesibles mediante índices. Los arrays permiten el acceso rápido a elementos por su posición y tienen un tamaño fijo definido en el momento de su creación.
	
* Ejemplo en Haskell:

	En Haskell, los arrays se pueden implementar utilizando el módulo Data.Array.

	```haskell
	import Data.Array

	myArray :: Array Int Int
	myArray = array (0, 4) [(0, 10), (1, 20), (2, 30), (3, 40), (4, 50)]
	```
	
	Este ejemplo crea un array de enteros con índices del 0 al 4 y valores asignados a cada índice.
	
**D) Unión de tipos**

* Tipo: Estructurado
* Definición y Funcionamiento:

	Una unión de tipos es una estructura de datos que puede contener valores de diferentes tipos en diferentes momentos. Esto permite que una variable tome distintos tipos de valores en diferentes contextos, aunque solo puede contener un valor a la vez.
	
* Ejemplo en Haskell:

	Haskell no soporta uniones de tipos directamente, pero se puede simular utilizando tipos algebraicos, específicamente los tipos de datos sumatorios.
	
	```haskell
	data Value = IntValue Int | StringValue String

	example :: Value -> String
	example (IntValue i)    = "Integer: " ++ show i
	example (StringValue s) = "String: " ++ s
	```
	
	En este ejemplo, Value puede contener un Int o un String. La función example muestra cómo manejar cada caso.
	
---

1. Supongamos tres lenguajes A, B y C con las siguientes características:

A: Las definiciones de subprogramas no pueden anidarse y no se admite recursión.
B: Las definiciones de subprogramas no pueden anidarse, pero sí se admite recursión. 
C: Las definiciones de subprogramas pueden anidarse y se admite recursión.

a) Defina en qué consiste un registro de activación y qué información básica (con independencia del tipo de ambiente de ejecución) se almacena en ellos.

b) Explique qué tipo de ambiente de ejecución se necesita para gestionar cada uno de estos lenguajes y cómo gestiona cada uno de ellos los registros de activación. Justifique su respuesta.

c) Indique qué información adicional requieren los registros de activación del lenguaje B con respecto a los del lenguaje A y los del lenguaje C con respecto a los del lenguaje B. Justifique su respuesta.

**A) Registro de Activación**

**Definición:** Un registro de activación, también conocido como marco de pila o "stack frame", es una estructura de datos que se utiliza en la pila de llamadas de un programa para gestionar la información necesaria durante la invocación de subprogramas (funciones o procedimientos).

**Información Básica en un Registro de Activación:**

1. Dirección de Retorno: La dirección en el código a la que debe regresar el control del programa después de que la función termine.
2. Enlace Dinámico (Dynamic Link): Un puntero al registro de activación del subprograma que llamó al subprograma actual (el "caller").
3. Enlace Estático (Static Link): Un puntero al registro de activación del entorno léxico que contiene el subprograma actual (esto es especialmente relevante para lenguajes que permiten la anidación de subprogramas).
4. Parámetros: Los valores de los argumentos que se pasan al subprograma.
5. Variables Locales: Espacio para las variables locales declaradas dentro del subprograma.
6. Espacio para el Resultado de la Función (si es necesario): Lugar para almacenar el valor de retorno de la función.

**B) Ambiente de Ejecución para Lenguajes A, B y C**

**Lenguaje A:** (No permite anidación de subprogramas, no permite recursión)

* Ambiente de Ejecución Necesario: Un ambiente de ejecución simple con una pila de llamadas lineal.
* Gestión de Registros de Activación: Dado que no hay anidación ni recursión, cada subprograma puede tener un único registro de activación en la pila. Los registros se gestionan de manera secuencial, sin necesidad de enlaces estáticos ni gestión compleja de la pila.

**Lenguaje B:** (No permite anidación de subprogramas, pero permite recursión)

* Ambiente de Ejecución Necesario: Un ambiente de ejecución con una pila de llamadas dinámica.
* Gestión de Registros de Activación: Al permitir la recursión, un subprograma puede invocarse a sí mismo, lo que requiere múltiples registros de activación en la pila para cada instancia de la llamada. No se necesitan enlaces estáticos, pero sí enlaces dinámicos para mantener la secuencia de llamadas.

**Lenguaje C:** (Permite anidación de subprogramas y recursión)

* Ambiente de Ejecución Necesario: Un ambiente de ejecución complejo con una pila de llamadas dinámica y soporte para enlaces estáticos.
* Gestión de Registros de Activación: Cada llamada a subprograma, ya sea recursiva o anidada, requiere un nuevo registro de activación en la pila. Además de los enlaces dinámicos, se necesitan enlaces estáticos para acceder correctamente a las variables de los entornos anidados.

**c) Información Adicional en los Registros de Activación**

**Lenguaje B respecto a A:**

* Espacio para múltiples registros de activación: Dado que B permite recursión, es necesario gestionar múltiples registros de activación en la pila, uno para cada instancia de una llamada recursiva.
* Enlaces Dinámicos: Se necesita un enlace dinámico para cada registro de activación para mantener la secuencia de llamadas y permitir el retorno correcto al "caller".

**Lenguaje C respecto a B:**

* Enlaces Estáticos: Se necesita un enlace estático para cada registro de activación para acceder a los entornos léxicos que contienen subprogramas anidados. Esto permite que los subprogramas anidados accedan a las variables de sus contenedores.
* Gestión de Ambientes Léxicos: Los registros de activación deben almacenar información para gestionar el alcance léxico de las variables debido a la anidación de subprogramas.

**Justificación**

**Lenguaje A:** No necesita enlaces estáticos ni múltiples registros de activación, ya que no hay anidación ni recursión. Solo se requiere una pila lineal sencilla.

**Lenguaje B:** La recursión introduce la necesidad de múltiples registros de activación y enlaces dinámicos para mantener la correcta secuencia de llamadas y retornos.

**Lenguaje C:** La anidación de subprogramas requiere enlaces estáticos para permitir el acceso correcto a las variables de los entornos contenedores. Esto añade complejidad adicional a la gestión de registros de activación.

---

1. Describa los cuatro tipos de errores que se pueden dar durante el proceso de traducción y ponga dos ejemplos de cada uno de ellos: uno detectable en tiempo de compilación y otro detectable en tiempo de ejecución. Si alguno de esos ejemplos no fuera posible, explique los motivos.

**Errores Léxicos**

Definición: Los errores léxicos ocurren cuando el analizador léxico (lexer) encuentra un símbolo o una secuencia de caracteres que no se puede emparejar con ningún token válido del lenguaje.

* Ejemplo de Error Léxico Detectable en Tiempo de Compilación:

	```python
	# Código en Python con un error léxico
	x = 10
	y = 20
	z = x @ y  # El símbolo '@' no es un operador válido
	```

	El compilador detectará este error al encontrar el símbolo '@', que no es un token válido en Python.

* Ejemplo de Error Léxico Detectable en Tiempo de Ejecución:

	No es posible tener errores léxicos en tiempo de ejecución, ya que estos errores se detectan durante la fase de análisis léxico antes de la ejecución del programa.
	
**Errores Sintácticos**

Definición: Los errores sintácticos ocurren cuando la estructura del código no cumple con las reglas de la gramática del lenguaje de programación. Estos errores son detectados por el analizador sintáctico (parser).

* Ejemplo de Error Sintáctico Detectable en Tiempo de Compilación:

	```java
	// Código en Java con un error sintáctico
	public class Main {
    	public static void main(String[] args) {
        	int x = 10
        	System.out.println(x);
    	}
	}
	```
	
	El compilador detectará la falta del punto y coma al final de la declaración int x = 10, generando un error sintáctico.
	
* Ejemplo de Error Sintáctico Detectable en Tiempo de Ejecución:

	No es posible tener errores sintácticos en tiempo de ejecución, ya que estos errores se detectan durante la fase de análisis sintáctico antes de la ejecución del programa.
	
**Errores Semánticos**

Definición: Los errores semánticos ocurren cuando el código, aunque sintácticamente correcto, no tiene un significado lógico correcto o viola las reglas semánticas del lenguaje.

* Ejemplo de Error Semántico Detectable en Tiempo de Compilación:

	```c
	// Código en C con un error semántico
	int main() {
	    int x = "Hello";  // Asignación de una cadena a una variable entera
	    return 0;
	}
	```
	
	El compilador detectará este error porque no es semánticamente correcto asignar una cadena a una variable de tipo entero.
	
* Ejemplo de Error Semántico Detectable en Tiempo de Ejecución:

	```python
	# Código en Python con un error semántico
	def divide(a, b):
	    return a / b
	
	result = divide(10, 0)  # División por cero
	```
	
	Este error no será detectado en tiempo de compilación, pero en tiempo de ejecución, intentará dividir por cero, resultando en un error de ejecución.
	
**Errores Lógicos**

Definición: Los errores lógicos ocurren cuando el programa compila y se ejecuta correctamente, pero produce resultados incorrectos debido a una falla en la lógica del código.

* Ejemplo de Error Lógico Detectable en Tiempo de Compilación:

	No es posible detectar errores lógicos en tiempo de compilación, ya que estos errores se relacionan con la lógica del programa y no con la sintaxis o la semántica que pueden ser validadas por el compilador.
	
* Ejemplo de Error Lógico Detectable en Tiempo de Ejecución:

	```java
	// Código en Java con un error lógico
	public class Main {
	    public static void main(String[] args) {
	        int[] numbers = {1, 2, 3, 4, 5};
	        int sum = 0;
	        for (int i = 0; i <= numbers.length; i++) {  // Error lógico: debe ser i < numbers.length
	            sum += numbers[i];
	        }
	        System.out.println("Sum: " + sum);
	    }
	}
	```
	
	El programa se compilará y ejecutará, pero producirá una excepción de índice fuera de los límites en tiempo de ejecución debido a un error lógico en la condición del bucle.
	
---

1. Responda las siguientes cuestiones:

	a) Defina la sintaxis y la semántica de la forma general de un bucle descrita por Dijkstra.

	b) Obviando el problema del indeterminismo, indique cómo utilizando un bucle while y sentencias condicionales if podría simular el funcionamiento de un bucle general descrito por Dijkstra.
	
**A) Sintaxis y Semántica de la Forma General de un Bucle Descrita por Dijkstra**

**Sintaxis del Bucle General (Guarded Commands):**

Dijkstra describió una forma de bucle conocida como bucle general o bucle con comandos guardados (guarded commands), que tiene la siguiente sintaxis:

```rust
do G1 -> S1
   []
   G2 -> S2
   []
   ...
   []
   Gn -> Sn
od
```

Donde:

* `G1, G2, ..., Gn` son guardas, que son expresiones booleanas.
* `S1, S2, ..., Sn` son comandos (o bloques de código) asociados con sus respectivas guardas.

**Semántica del Bucle General:**

La semántica del bucle general de Dijkstra es la siguiente:

* El bucle `do ... od` se ejecuta mientras al menos una de las guardas `G1, G2, ..., Gn` sea verdadera.
* Durante cada iteración del bucle:
	* Se evalúan todas las guardas.
	* Si ninguna guarda es verdadera, el bucle termina.
	* Si una o más guardas son verdaderas, se selecciona (de manera indeterminada) una de las guardas verdaderas Gi.
	* El comando correspondiente Si se ejecuta.
* Después de ejecutar Si, se vuelve a evaluar todas las guardas y el proceso se repite.

El bucle continúa hasta que ninguna guarda sea verdadera.

**B) Simulación del Bucle General con un while y if (Obviando el Indeterminismo)**

Para simular el funcionamiento de un bucle general descrito por Dijkstra utilizando un bucle while y sentencias condicionales if, podemos proceder de la siguiente manera:

Suposiciones:

* Se asume que el problema del indeterminismo se puede ignorar. En la práctica, esto significa que se evaluarán las guardas en un orden fijo y se ejecutará la primera guarda que sea verdadera.

Explicación:

* Definimos funciones G1, G2, ..., Gn para representar las guardas. Cada función devuelve un valor booleano.
* Definimos funciones S1, S2, ..., Sn para representar los comandos asociados a las guardas.
* Utilizamos un bucle while True que se ejecuta indefinidamente.
* Dentro del bucle, evaluamos cada guarda en orden usando sentencias if, elif.
* Si se encuentra una guarda verdadera, se ejecuta el comando asociado.
* Si ninguna guarda es verdadera (else), el bucle se rompe (break), terminando la ejecución del bucle.

---

a) Defina EBNF (Forma Bakus-Naur Extendida) e indique sus diferencias con BNF.

b) Sea la siguiente gramática expresada en notación EBNF:

```
R1 ‹expresion> := ‹termino> { OR ‹termino> } 
R2 <termino> := ‹factor> { AND ‹ factor > }
R3 ‹ factor> := '(' ‹expresion> ')' | ‹valor › 
R4 <valor> ::= v | f
```

siendo <expresion> el símbolo inicial y v y f constantes. Para cada una de las siguientes cadenas de la gramática, dibuje su árbol sintáctico:

```
b1) v AND f OR V
b2) ( ( V AND V ) OR ( f AND V ) )
```

**A) Definición de EBNF (Extended Backus-Naur Form)**

Definición:

EBNF (Extended Backus-Naur Form) es una notación para expresar gramáticas libres de contexto. Es una extensión de la notación BNF (Backus-Naur Form) que introduce una sintaxis más compacta y poderosa, permitiendo describir las reglas gramaticales de manera más clara y legible.

Diferencias entre EBNF y BNF:

* Repetición:
	* BNF: Usa recursión para especificar repetición.
	* EBNF: Usa operadores { ... } para indicar que una secuencia puede repetirse cero o más veces.
* Opcionalidad:
	* BNF: Usa reglas alternativas para manejar elementos opcionales.
	* EBNF: Usa operadores [ ... ] para indicar que una secuencia es opcional.
* Agrupación:
	* BNF: Usa reglas intermedias para agrupar elementos.
	* EBNF: Usa paréntesis ( ... ) para agrupar elementos.
* Sintaxis Simplificada:
	* BNF: Puede ser más verbosa y menos intuitiva.
	* EBNF: Permite una representación más concisa y legible de las reglas gramaticales.

**B) Gramática EBNF y Árboles Sintácticos**

**b1) v AND f OR v**

Derivación:

```
<expresion>
-> <termino> { OR <termino> }
-> <termino> OR <termino>
-> <factor> { AND <factor> } OR <termino>
-> <factor> AND <factor> OR <termino>
-> <valor> AND <valor> OR <termino>
-> v AND f OR <termino>
-> v AND f OR <factor> { AND <factor> }
-> v AND f OR <factor>
-> v AND f OR <valor>
-> v AND f OR v
```

Árbol Sintáctico:

```
<expresion>
|
+-- <termino>
|   |
|   +-- <factor>
|   |   |
|   |   +-- <valor> (v)
|   |
|   +-- AND
|   |
|   +-- <factor>
|       |
|       +-- <valor> (f)
|
+-- OR
|
+-- <termino>
    |
    +-- <factor>
        |
        +-- <valor> (v)
```

**b2) ((v AND v) OR (f AND v))**

Derivación:

```
<expresion>
-> <termino> { OR <termino> }
-> <factor> { AND <factor> } { OR <termino> }
-> '(' <expresion> ')' { OR <termino> }
-> '(' <expresion> ')' OR <termino>
-> '(' <termino> { OR <termino> } ')' OR <termino>
-> '(' <termino> OR <termino> ')' OR <termino>
-> '(' <factor> { AND <factor> } OR <termino> ')' OR <termino>
-> '(' <factor> AND <factor> OR <termino> ')' OR <termino>
-> '(' <valor> AND <valor> OR <termino> ')' OR <termino>
-> '(' v AND v OR <termino> ')' OR <termino>
-> '(' v AND v ')' OR '(' <termino> { AND <factor> } ')'
-> '(' v AND v ')' OR '(' <factor> AND <factor> ')'
-> '(' v AND v ')' OR '(' <valor> AND <valor> ')'
-> '(' v AND v ')' OR '(' f AND v ')'
```

Árbol Sintáctico:

```
<expresion>
|
+-- <termino>
|   |
|   +-- <factor>
|       |
|       +-- (
|       |
|       +-- <expresion>
|       |   |
|       |   +-- <termino>
|       |       |
|       |       +-- <factor>
|       |       |   |
|       |       |   +-- <valor> (v)
|       |       |
|       |       +-- AND
|       |       |
|       |       +-- <factor>
|       |           |
|       |           +-- <valor> (v)
|       |
|       +-- )
|
+-- OR
|
+-- <termino>
    |
    +-- <factor>
        |
        +-- (
        |
        +-- <expresion>
        |   |
        |   +-- <termino>
        |       |
        |       +-- <factor>
        |       |   |
        |       |   +-- <valor> (f)
        |       |
        |       +-- AND
        |       |
        |       +-- <factor>
        |           |
        |           +-- <valor> (v)
        |
        +-- )
```

---

