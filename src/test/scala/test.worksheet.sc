import Huffman._
//Prueba Peso
val arbol1 = Nodo(Hoja('a', 1), Hoja('b', 2), List('a', 'b'), 3)
val arbol2 = Nodo(arbol1, Hoja('c', 4), List('a', 'b', 'c'), 7)
peso(arbol1) // debe imprimir 3
peso(arbol2) // debe imprimir 7
//Prueba cars
cars(arbol1) // debería imprimir List('a', 'b')
cars(arbol2) // debería imprimir List('c') 
//Prueba hacerNodoArbolH
val izq = Hoja('a', 2)
val der = Hoja('b', 3)
hacerNodoArbolH(izq, der) // deberia imprimir Nodo = Nodo(Hoja(a,2),Hoja(b,3),List(a, b),5)
//Prueba listaDeHojasOrdenada
val frecuencias = List(('a', 2), ('b', 1), ('c', 3), ('d', 4))
listaDeHojasOrdenadas(frecuencias) // deberia imprimir List[Hoja] = List(Hoja(b,1), Hoja(a,2), Hoja(c,3, Hoja(d,4))
//Prueba ocurrencias
val listaCars = List('a', 'b', 'c', 'a', 'b', 'a')
ocurrencias(listaCars) // deberia imprimir List((a,3), (b,2), (c,1))
//Prueba listaUnitaria
val arbol3 = Nodo(Hoja('a', 1), Hoja('b', 2), List('a', 'b'), 3)
val arboles1 = List(arbol3)
val arboles2 = List(Hoja('a', 1), Hoja('b', 2))
listaUnitaria(arboles1) // debe imprimir true
listaUnitaria(arboles2) // debe imprimir false
//Prueba combinar
val arbol4 = Nodo(Hoja('a', 1), Hoja('b', 2), List('a', 'b'), 3)
val arbol5 = Nodo(Hoja('c', 4), Hoja('d', 5), List('c', 'd'), 9)
val arboles3 = List(arbol4, arbol5)
val arboles4 = List(arbol4, Hoja('c', 4), Hoja('d', 5))
combinar(arboles3)
combinar(arboles4)
//Prueba hastaQue
val arbol6 = Nodo(Hoja('a', 1), Hoja('b', 2), List('a', 'b'), 3)
val arbol7 = Nodo(Hoja('c', 4), Hoja('d', 5), List('c', 'd'), 9)
val arboles = List(arbol6, arbol7)
val condicion = (lista: List[ArbolH]) => lista.length <= 1
val mezclar = (lista: List[ArbolH]) => combinar(arboles)
hastaQue(condicion, mezclar)(arboles)
//Prueba crearArbolDeHuffman
val texto = List('a', 'b', 'c', 'a', 'b', 'a')
crearArbolDeHuffman(texto)
//Prueba profesor
val arbolEjemplo = hacerNodoArbolH (
hacerNodoArbolH (Hoja('x', 1 ) , Hoja('e' ,1)),
Hoja('t',2)
)
val lc=cadenaALista("La_vida_es_dura")
val lho=listaDeHojasOrdenadas(ocurrencias(lc))
listaUnitaria(lho)
crearArbolDeHuffman(lc)
val mensajeCodificado1 = codificar(crearArbolDeHuffman(lc))(lc)
val mensajeCodificado2 = codificarRapido(crearArbolDeHuffman(lc))(lc)
//Prueba decodificar
decodificar(crearArbolDeHuffman(lc), mensajeCodificado1)
decodificar(crearArbolDeHuffman(lc), mensajeCodificado2)

