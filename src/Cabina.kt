data class Cabina(var numCabina:Int, val llamadas: MutableList<Llamada> = mutableListOf(), var duracionTotal:Int = 0, var costoTotal:Int = 0) {

    // Registro de llamadas
    fun registrarLlamada(tipoLlamadas: String?, duracion:Int){

        val costo = when (tipoLlamadas){

            "Local" -> duracion * 50 // Costo llamada local
            "Larga distancia" -> duracion * 350 // Costo llamada a distancia
            "Celular" -> duracion * 150 // Costo llamada a celular
            else -> 0

        }
        llamadas.add(Llamada(tipoLlamadas, duracion, costo)) //Agrega la llamada a la lista
        duracionTotal += duracion // Suma la duracion de las llamadas realizadas
        costoTotal += costo // Suma el costo de las llamadas

    }

    // Mostrar la informacion de la cabina
    fun infoCabina():String{

        // Formato de la información
        val header = String.format("%-20s | %-10s | %-10s", "Tipo", "Duración", "Costo")
        val detallesLlamadas = llamadas.joinToString("\n") {
            String.format("%-20s | %-10d | %-10d", it.tipo, it.duracion, it.costo)// it es cada número de la lista - joinToString da un formato
        }
        return """========================
            |CABINA #$numCabina
            |========================
            |N° llamadas: ${llamadas.size}
            |Tiempo total (minutos): $duracionTotal
            |Costo total: $$costoTotal
            |Lista de llamadas:            
            |$header
            |$detallesLlamadas
        """.trimMargin()

    }

    // Reiniciar los datos de la cabina
    fun reiniciarCabina(){

            llamadas.clear()
            duracionTotal = 0
            costoTotal = 0

    }

}

// Mostrar informacion de todas las cabinas
fun infoTodasCabinas(cabinas: List<Cabina>):String{

    var costoTotal = 0
    var llamadasTotales = 0
    var duracionTotal = 0
    var costoPromedio:Int

    cabinas.forEach{

        costoTotal += it.costoTotal
        llamadasTotales += it.llamadas.size
        duracionTotal += it.duracionTotal

    }

    if (duracionTotal > 0){

        costoPromedio = costoTotal / duracionTotal

    }else {

        return "Debe haber almenos 1 minuto de llamada."

    }

    //Formato cabecera
    val header = "**************************************\n" +
            "*         DATOS TOTALES              *\n" +
            "**************************************"

    // Formato del contenido
    val contenido = String.format(
        "* %-10s | %-9s | %-10s *",
        "Llamadas", "Costo", "Duración"
    ) + "\n" +
            "*-----------------------------------*\n" +
            String.format(
                "* %-10d | $%-8d | %-10d *",
                llamadasTotales, costoTotal, duracionTotal
            )

    // Formato del costo promedio
    val footer = "*************************************\n" +
            String.format("* Costo promedio por minuto: %-5d    *", costoPromedio) +
            "\n*************************************"

    // Combinar el formato
    return "$header\n$contenido\n$footer"

}