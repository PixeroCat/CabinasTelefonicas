fun main() {

    // Variables
    var cabinas = List(5){Cabina(it + 1)}
    var nCab = 0
    var tLlam:Int
    var dur:Int
    var opcion = 0

    // Mapa de los tipos de llamada
    val llamadaMap = mapOf(

        1 to "Local",
        2 to "Larga distancia",
        3 to "Celular"

    )

    while (true){

        //try catch, al momento de ingresar algo que no sea un número, el programa no se termine
        try {

            // Menu de inicio
            println("""=========================
                |       Bienvenido!
                |=========================
                    |¿Que operación desea realizar?
                    |1- Registrar llamada
                    |2- Mostrar información de la cabina
                    |3- Mostrar información de todas las cabinas
                    |4- Reiniciar cabina
                    |5- Salir
                """.trimMargin())
            opcion = readln().toInt()

            // Opciones
            when (opcion){

                // Registro de llamadas
                1 -> {

                    println("Elija una cabina del 1 al 5")
                    nCab = readln().toInt()

                    if (nCab in 1..5) {

                        println("Has seleccionado la cabina #$nCab")


                    } else {

                        println("Número de cabina inválido. Por favor, elija un número del 1 al 5.")

                    }

                        println("""¿Que tipo de llamada desea realizar?
                            |1- Local
                            |2- Larga distancia
                            |3- Celular
                        """.trimMargin())
                        tLlam = readln().toInt()

                    val tipLlamada = llamadaMap[tLlam]

                    println("Duración de la llamada: ")
                    dur = readln().toInt()
                    cabinas.getOrNull(nCab - 1)?.registrarLlamada(tipLlamada, dur)
                    println("Llamada registrada!")

                }

                // Información de una cabina
                2 -> {

                    println("Elija una cabina del 1 al 5")
                    nCab = readln().toInt()

                    if (nCab in 1..5) {

                        println(cabinas.getOrNull(nCab - 1)?.infoCabina())

                    }else{

                        println("Número invalido.")

                    }

                }

                // Informacion de todas las cabinas
                3 -> println(infoTodasCabinas(cabinas))

                // Reinicio de cabina
                4 -> {

                    println("Elija una cabina del 1 al 5 que desea reiniciar")
                    nCab = readln().toInt()

                    if (nCab in 1..5) {

                        println("""¿Esta seguro de reiniciar la cabina?
                                |1- Si
                                |2- No
                            """.trimMargin())
                        opcion = readln().toInt()

                    }else{

                        println("Opcion invalida.")

                    }

                    if (opcion == 1){

                        println(cabinas.getOrNull(nCab - 1)?.reiniciarCabina())
                        println("La cabina ha sido reiniciada exitosamente")

                    }else{

                        println("No se reinicio la cabina.")

                    }

                }

                // Salir del programa
                5 -> {

                    println("Hasta la proxima!")
                    break

                }

                else -> println("Opcion invalida, intentelo de nuevo")
            }

        }catch (e: NumberFormatException) {
            println("Ingrese solo números.")
        }
    }
}