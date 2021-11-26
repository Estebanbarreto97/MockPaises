package ar.edu.unahur.obj2.impostoresPaises.cli

import ar.edu.unahur.obj2.impostoresPaises.api.*
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.*

class ProgramaTest : DescribeSpec({
  val caboVerde =
    Country(
      "Cabo Verde",
      "CPV",
      "Praia",
      "Africa",
      555988,
      4033.0,
      emptyList(),
      listOf(Language("Portuguese")),
      listOf(RegionalBloc("AU", "African Union")),
      listOf(Currency("CVE"))
    )

  val bolivia =
    Country(
      "Bolivia (Plurinational State of)",
      "BOL",
      "Sucre",
      "Americas",
      11673029,
      1098581.0,
      listOf("ARG", "BRA","PER"),
      listOf(Language("Spanish"), Language("Aymara"), Language("Quechua")),
      listOf(RegionalBloc("USAN", "Union of South American Nations")),
      listOf(Currency("BOB"))
    )
  val argentina =
    Country(
      "Argentina",
      "ARG",
      "Buenos Aires",
      "Americas",
      40673029,
      2098581.0,
      listOf("BOL", "BRA", "PER"),
      listOf(Language("Spanish")),
      listOf(RegionalBloc("USAN", "Union of South American Nations")),
      listOf(Currency("ARS"))
    )
  val brasil =
  Country(
    "Brazil",
    "BRA",
    "Brazil",
    "Americas",
    60673029,
    8098581.0,
    listOf("BOL", "ARG","PER"),
    listOf(Language("Portuguese")),
    listOf(RegionalBloc("USAN", "Union of South American Nations")),
    listOf(Currency("SOL"))
  )
  val peru =
    Country(
      "Peru",
      "PER",
      "Lima",
      "Americas",
      10673029,
      1598581.0,
      listOf("BOL", "BRA","PER"),
      listOf(Language("Portuguese")),
      listOf(RegionalBloc("USAN", "Union of South American Nations")),
      listOf(Currency("PEN"))
    )

  val transformadorMock = mockk<RestCountriesAPI>()
  TransformadorCountryAPais.apiRest = transformadorMock
  every { transformadorMock.paisConCodigo("BOL") } returns bolivia
  every { transformadorMock.paisConCodigo("BRA") } returns brasil
  every { transformadorMock.paisConCodigo("ARG") } returns argentina
  every { transformadorMock.paisConCodigo("PER") } returns peru
  every { transformadorMock.paisConCodigo("CPV") } returns caboVerde
  every { transformadorMock.buscarPaisesPorNombre("argentina")} returns listOf(argentina)
  every { transformadorMock.buscarPaisesPorNombre("bolivia") } returns listOf(bolivia)
  every { transformadorMock.buscarPaisesPorNombre("caboverde") } returns listOf(caboVerde)
  every { transformadorMock.todosLosPaises() } returns listOf(argentina,peru,brasil,bolivia,caboVerde)

  /*
  val arg = TransformadorCountryAPais.transformarAPaís(argentina,false)
  val per = TransformadorCountryAPais.transformarAPaís(peru,false)
  val bra = TransformadorCountryAPais.transformarAPaís(brasil,false)
  val bol = TransformadorCountryAPais.transformarAPaís(bolivia,false)
  val cvp = TransformadorCountryAPais.transformarAPaís(caboVerde,false)

  Observatorio.listaDePaises = mutableMapOf(Pair(arg.nombre,arg),Pair(bol.nombre,bol),Pair(cvp.nombre,cvp),Pair(bra.nombre,bra), Pair(per.nombre,per))
  Observatorio.listaDeContinentes = mutableMapOf(Pair("America",listOf(arg,bol,bra,per)), Pair("Europa", listOf(cvp)))
  */
  describe("Programa") {
    val consolaMock = mockk<Consola>()
    Programa.entradaSalida = consolaMock
    val apiMock = mockk<RestCountriesAPI>()
    Programa.api = apiMock

    every { apiMock.buscarPaisesPorNombre("argentina")} returns listOf(argentina)
    every { apiMock.buscarPaisesPorNombre("bolivia") } returns listOf(bolivia)
    every { apiMock.buscarPaisesPorNombre("caboverde") } returns listOf(caboVerde)
    every { consolaMock.escribirLinea(any()) } just Runs


    it("es plurinacional") {
      every {consolaMock.leerLinea()} returnsMany listOf("argentina","1")
      Programa.iniciar()
      verify {  consolaMock.escribirLinea("el país no es plurinacional") }
    }
    it("es una isla?"){
      every {consolaMock.leerLinea()} returnsMany listOf("argentina","2")
      Programa.iniciar()
      verify {  consolaMock.escribirLinea("el país no es una isla") }
    }
    it("su densidad poblacional es"){
      every {consolaMock.leerLinea()} returnsMany listOf("argentina","3")
      Programa.iniciar()
      verify {  consolaMock.escribirLinea("su densidad poblacional es: 19") }
    }
    it("el vecino mas poblado es"){
      every {consolaMock.leerLinea()} returnsMany listOf("argentina","4")
      Programa.iniciar()
      verify {  consolaMock.escribirLinea("su vecino mas poblado es: Brazil") }
    }
    it("es limitrofe con otro país"){
      every {consolaMock.leerLinea()} returnsMany listOf("argentina","5","bolivia")
      Programa.iniciar()
      verify {  consolaMock.escribirLinea("son limitrofes c:") }
    }
    it("traduccion para dialogar"){
      every {consolaMock.leerLinea()} returnsMany listOf("argentina","6","bolivia")
      Programa.iniciar()
      verify {  consolaMock.escribirLinea("no necesitan traduccion") }
    }
    it("potenciales aliados"){
      every {consolaMock.leerLinea()} returnsMany listOf("argentina","7","bolivia")
      Programa.iniciar()
      verify {  consolaMock.escribirLinea("son potenciales aliados D:") }
    }
    it("conviene ir de compras"){
      every {consolaMock.leerLinea()} returnsMany listOf("argentina","8","bolivia")
      Programa.iniciar()
      verify {  consolaMock.escribirLinea("no conviene ir de compras hacia Bolivia (Plurinational State of)") }
    }
    it("equivalencia de la moneda"){
      every {consolaMock.leerLinea()} returnsMany listOf("argentina","9","bolivia")
      Programa.iniciar()
      verify {  consolaMock.escribirLinea("el valor del pais destino es de 1 : 1.0") }
    }/*
    it("5 codigo iso"){
      every {consolaMock.leerLinea()} returnsMany listOf("argentina","10")
      Programa.iniciar()
      verify { consolaMock.escribirLinea("el valor del pais destino es de 1 : 1.0") }
    }*/
    it("continente mas plurinacional"){
      every {consolaMock.leerLinea()} returnsMany listOf("argentina","11")
      Programa.iniciar()
      verify { consolaMock.escribirLinea("el continente con mas paises plurinacionales es : Americas") }
    }
    it("promedio poblacion islas"){
      every {consolaMock.leerLinea()} returnsMany listOf("argentina","12")
      Programa.iniciar()
      verify { consolaMock.escribirLinea("el promedio de densidad poblacional de las islas es de : 137") }
    }

  }
})
