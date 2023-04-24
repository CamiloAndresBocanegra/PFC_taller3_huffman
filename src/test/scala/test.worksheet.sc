import Huffman._
//Prueba Peso
val arbol1 = Nodo(Hoja('a', 1), Hoja('b', 2), List('a', 'b'), 3)
val arbol2 = Nodo(arbol1, Hoja('c', 4), List('a', 'b', 'c'), 7)
peso(arbol1) // debe imprimir 3
peso(arbol2) // debe imprimir 7
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
val mezclar = (lista: List[ArbolH]) => combinar(lista)
hastaQue(condicion, mezclar)(arboles) // debe imprimir List(Nodo(Hoja(a,1),Nodo(Hoja(b,2),Nodo(Hoja(c,4),Hoja(d,5),List(c, d),9),List(a, b, c, d),12)))
