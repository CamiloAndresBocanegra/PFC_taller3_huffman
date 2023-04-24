package object Huffman {
	abstract class ArbolH
	case class Nodo(izq: ArbolH, der: ArbolH, cars: List[Char], peso: Int) extends ArbolH
	case class Hoja(car: Char, peso: Int) extends ArbolH

	def peso(arbol: ArbolH): Int = arbol match{
		case Nodo(i, d, c, peso) => peso
		case Hoja(car, peso) => peso
	}

	def cars(arbol: ArbolH): List[Char] = arbol match{
		case Nodo(i,d,cars,p) => cars
		case Hoja(car, peso) => List(car)
	}

	def hacerNodoArbolH(izq: ArbolH, der: ArbolH) =
		Nodo(izq, der, cars(izq) ::: cars(der), peso(izq) + peso(der))

	def ocurrencias(cars: List[Char]): List[(Char, Int)] = {
		def contar_car_en_lista(c:Char, l:List[Char]):Int = {
			def contar_car_en_lista_aux(c:Char, l:List[Char], result:Int):Int = {
				if(l.isEmpty)
					result
				else
					if(c == l.head)
						contar_car_en_lista_aux(c, l.tail, result+1)
					else
						contar_car_en_lista_aux(c, l.tail, result)
			}
			contar_car_en_lista_aux(c, l, 0)
		}

		def erradicar_car_de_lista(c:Char, l:List[Char]): List[Char] = {
			def erradicar_car_de_lista_aux(c:Char, l:List[Char], result:List[Char]): List[Char] = {
				if(l.isEmpty)
				result
				else
					if(l.head == c)
					erradicar_car_de_lista_aux(c, l.tail, result)
					else
						erradicar_car_de_lista_aux(c, l.tail, result ++ List(l.head))
			}
			erradicar_car_de_lista_aux(c, l, List())
		}

		def ocurrencias_aux(text: List[Char], result:List[(Char, Int)]): List[(Char,Int)] = {
			if(text.isEmpty)
				result
			else
			{
				val car = text.head
				val ocurrencias = contar_car_en_lista(car, text)
				val text_sin_car = erradicar_car_de_lista(car, text)
				val result_n = result ++ List((car, ocurrencias))
				ocurrencias_aux(text_sin_car, result_n)
			}
		} 
		ocurrencias_aux(cars, List())
	}

	def listaDeHojasOrdenadas(frecs: List[(Char, Int)]): List[Hoja] = {
		def eliminar_car_de_lista(l:List[(Char,Int)], c:Char): List[(Char,Int)] = {
			def aux(l:List[(Char,Int)], c:Char, result:List[(Char,Int)]):List[(Char,Int)] = {
				if(l.isEmpty)
					result
				else
					if(l.head(0) == c)
						aux(l.tail, c, result)
					else
						aux(l.tail, c, result ++ List(l.head))
			}
			aux(l, c, List())
		}
		def encontrar_car_menor(l:List[(Char,Int)]):(Char,Int) = {
			def aux(l:List[(Char,Int)],result:(Char,Int)):(Char,Int) = {
				if(l.isEmpty)
					result
				else
					if(l.head(1) < result(1))
						aux(l.tail, l.head)
					else
						aux(l.tail, result)
			}
			aux(l, l.head)
		}
		
		def listaDeHojasOrdenadas_aux(f:List[(Char,Int)], min:Int, result:List[Hoja]):List[Hoja] = {
			if(f.isEmpty)
				result
			else
			{
				val tupla_menor = encontrar_car_menor(f)
				val nueva_hoja = Hoja(tupla_menor(0), tupla_menor(1))
				val lista_sin_tupla_menor = eliminar_car_de_lista(f, tupla_menor(0))
				listaDeHojasOrdenadas_aux(lista_sin_tupla_menor, tupla_menor(1), result ++ List(nueva_hoja))	
			}
		}

		listaDeHojasOrdenadas_aux(frecs, 0, List())
	}

	def listaUnitaria(arboles:List[ArbolH]):Boolean = {
		!arboles.isEmpty && arboles.tail.isEmpty 
	}

	def combinar(arboles: List[ArbolH]):List[ArbolH] = {
		if(arboles.isEmpty || listaUnitaria(arboles))
			arboles
		else
		{
			val arbol1 = arboles.head
			val arbol2 = arboles.tail.head
			def combinar_arboles_en_lista(l:List[ArbolH], arbol_mayor:ArbolH, arbol_menor:ArbolH):List[ArbolH] = {
				def obtener_lista_de_cars_de_arbol(arbol:ArbolH):List[Char] = arbol match{
					case Nodo(i, d, cars, p) => cars
					case Hoja(car, peso) => List(car)
				}
				def filtrar_lista(l:List[ArbolH], filtro:ArbolH=>Boolean):List[ArbolH] = {
					def filtrar_lista_aux(l:List[ArbolH], filtro:ArbolH=>Boolean, result:List[ArbolH]):List[ArbolH] = {
						if(l.isEmpty)
							result
						else
							if(filtro(l.head))
								filtrar_lista_aux(l.tail, filtro, result ++ List(l.head))
							else
								filtrar_lista_aux(l.tail,filtro,result)
					}
					filtrar_lista_aux(l, filtro, List())
				}
				val lista_de_cars = obtener_lista_de_cars_de_arbol(arbol_mayor) ++ obtener_lista_de_cars_de_arbol(arbol_menor)
				val combinacion = Nodo(arbol_mayor, arbol_menor, lista_de_cars, peso(arbol_mayor) + peso(arbol_menor))

				def peso_menor_igual_que(n:Int):ArbolH=>Boolean = {
					(arbol:ArbolH)=> ( peso(arbol) <= n )
				}
				def peso_mayor_que(n:Int):ArbolH=>Boolean = {
					(arbol:ArbolH)=> ( peso(arbol) > n)
				}
				filtrar_lista(l, peso_menor_igual_que(peso(combinacion))) ++ List(combinacion) ++ filtrar_lista(l, peso_mayor_que(peso(combinacion)))
			}
			if(peso(arbol1) > peso(arbol2))
				combinar_arboles_en_lista(arboles.tail.tail, arbol1, arbol2)
			else
				combinar_arboles_en_lista(arboles.tail.tail, arbol2, arbol1)
		}
	}

	def hastaQue(cond:List[ArbolH]=>Boolean, mezclar:List[ArbolH]=>List[ArbolH] )(listaOrdenadaArboles:List[ArbolH]): List[ArbolH] = {
		def aux(cond:List[ArbolH]=>Boolean, mezclar:List[ArbolH]=>List[ArbolH], result:List[ArbolH]):List[ArbolH] = {
			if(cond(result))
				result
			else
				aux(cond, mezclar, mezclar(result))
		}
		aux(cond, mezclar, listaOrdenadaArboles)
	}

	def crearArbolDeHuffman(cars:List[Char]):ArbolH = {
		val frecuencias = ocurrencias(cars)
		val lista_de_hojas = listaDeHojasOrdenadas(frecuencias)
		hastaQue(listaUnitaria, combinar)(lista_de_hojas).head
	}
}