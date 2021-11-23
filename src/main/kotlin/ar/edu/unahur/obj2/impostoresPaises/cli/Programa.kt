package ar.edu.unahur.obj2.impostoresPaises.cli

import ar.edu.unahur.obj2.impostoresPaises.api.Country
import ar.edu.unahur.obj2.impostoresPaises.api.RestCountriesAPI

// El código de nuestro programa, que (eventualmente) interactuará con una persona real
object Programa {
  // Ambas son variables para poder cambiarlas en los tests
  var entradaSalida = Consola
  var api = RestCountriesAPI()

  fun iniciar() {
    entradaSalida.escribirLinea(AsciiArt.mundo)
    entradaSalida.escribirLinea("Holis,ingresa el nombre de un país :)")
    val pais = entradaSalida.leerLinea()
    checkNotNull(pais) { "Sin nombre no puedo hacer nada :(" }
    menu(SeleccionarPaisDeLista(api.buscarPaisesPorNombre(pais)))
  }

  fun SeleccionarPaisDeLista(lista : List<Country>) : Pais{
    check(lista.isNotEmpty()) { "No encontramos nada, fijate si lo escribiste bien" }
    if (lista.size > 1){
      entradaSalida.escribirLinea("se encontró mas de 1 país...\n Estos son los Paises Encontrados: ")
      lista.forEach{ entradaSalida.escribirLinea(it.name)}
      entradaSalida.escribirLinea("elegí del 1 al ${lista.size} para seleccionar un solo país")
      val valorelegido = entradaSalida.leerLinea()!!.toInt()
      check(valorelegido is Number &&(valorelegido > lista.size || valorelegido < lista.size)){
        "ingresaste algo mal :("
      }
      return TransforamadorCountryAPais.transformarAPaís(lista[valorelegido],false)
    }
    else
      return TransforamadorCountryAPais.transformarAPaís(lista.first(),false)
  }

  fun menu(pais : Pais){
    entradaSalida.escribirLinea("elegiste ${pais.nombre} :D, que deseas saber? \n" +
            "1 si es plurinacional \n" +
            "2 si es una isla \n" +
            "3 su densidad poblacional\n" +
            "4 su vecino mas poblado\n" +
            "5 - si es limítrofe con otro país\n" +
            "6 - si necesita traducción para dialogar con otro país\n" +
            "7 - si es potencial aliado con otro país\n" +
            "8 - si conviene ir de compras a otro país\n" +
            "9 - cuanto equivale un determinado monto en otro país\n")
    val eleccion = entradaSalida.leerLinea()
    lateinit var eleccionfutura : String
    when(eleccion){
      "1" -> {if (pais.esPlurinacional())
                entradaSalida.escribirLinea("el país es plurinacional")
              else
                entradaSalida.escribirLinea("el país no es plurinacional") }
      "2" -> { if (pais.esUnaIsla())
                entradaSalida.escribirLinea("el país es una isla")
              else
                entradaSalida.escribirLinea("el país no es una isla") }
      "3" -> entradaSalida.escribirLinea("su densidad poblacional es: ${pais.densidadPoblacional()}")
      "4" -> entradaSalida.escribirLinea("su vecino mas poblado es: ${pais.vecinoMasPoblado()}")
      "5" -> { entradaSalida.escribirLinea("ingrese el país a comparar")
                eleccionfutura = entradaSalida.leerLinea().toString()
                checkNotNull(eleccionfutura){"no se ingresó nada :("}
                checkLimitrofe(pais,SeleccionarPaisDeLista(api.buscarPaisesPorNombre(eleccionfutura))) }
      "6" -> { entradaSalida.escribirLinea("ingrese el país a comparar")
                eleccionfutura = entradaSalida.leerLinea().toString()
                checkNotNull(eleccionfutura){"no se ingresó nada :("}
                checkTraduccion(pais,SeleccionarPaisDeLista(api.buscarPaisesPorNombre(eleccionfutura))) }
      "7" -> {entradaSalida.escribirLinea("ingrese el país a comparar")
               eleccionfutura = entradaSalida.leerLinea().toString()
               checkNotNull(eleccionfutura){"no se ingresó nada :("}
               checkPotencialAliado(pais,SeleccionarPaisDeLista(api.buscarPaisesPorNombre(eleccionfutura))) }
      "8" -> {entradaSalida.escribirLinea("ingrese el país a comparar")
              eleccionfutura = entradaSalida.leerLinea().toString()
              checkNotNull(eleccionfutura){"no se ingresó nada :("}
              checkConvieneIrDeCompras(pais,SeleccionarPaisDeLista(api.buscarPaisesPorNombre(eleccionfutura))) }
      "9" -> {entradaSalida.escribirLinea("ingrese el país a comparar")
              eleccionfutura = entradaSalida.leerLinea().toString()
              checkNotNull(eleccionfutura){"no se ingresó nada :("}
              checkEquivalencia(pais,SeleccionarPaisDeLista(api.buscarPaisesPorNombre(eleccionfutura))) }

    }
  }


  fun checkLimitrofe(pais1 : Pais, pais2: Pais){
    if(Observatorio.sonLimitrofes(pais1.nombre,pais2.nombre))
      entradaSalida.escribirLinea("son limitrofes c:")
    else
      entradaSalida.escribirLinea("no son limitrofes :(")
  }

  fun checkTraduccion(pais1: Pais, pais2: Pais){
    if(Observatorio.necesitanTraduccion(pais1.nombre,pais2.nombre))
      entradaSalida.escribirLinea("necesitan traduccion :c")
    else
      entradaSalida.escribirLinea("no son limitrofes")
  }

  fun checkPotencialAliado(pais1: Pais, pais2: Pais){
    if(Observatorio.sonPotencialesAliados(pais1.nombre,pais2.nombre))
      entradaSalida.escribirLinea("son potenciales aliados D:")
    else
      entradaSalida.escribirLinea("son como el agua y el aceite ._.")
  }

  fun checkConvieneIrDeCompras(pais1: Pais, pais2: Pais){
    if(Observatorio.convieneIrDeComprasDe_Hacia_(pais1.nombre,pais2.nombre))
      entradaSalida.escribirLinea("conviene ir de compras hacia ${pais2.nombre}")
    else
      entradaSalida.escribirLinea("no conviene ir de compras hacia ${pais2.nombre}")
  }

  fun checkEquivalencia(pais: Pais, pais2: Pais){
    entradaSalida.escribirLinea("el valor del pais destino es de 1 : ${Observatorio.equivalenciasDe_Hacia_(pais.nombre,pais2.nombre,1)}")
  }



}


