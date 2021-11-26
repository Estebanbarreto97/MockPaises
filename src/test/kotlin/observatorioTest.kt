import ar.edu.unahur.obj2.impostoresPaises.api.*
import ar.edu.unahur.obj2.impostoresPaises.cli.Observatorio
import ar.edu.unahur.obj2.impostoresPaises.cli.Pais
import ar.edu.unahur.obj2.impostoresPaises.cli.TransformadorCountryAPais
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.file.beLarger
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class observatorioTest : DescribeSpec ({
    val arg = Country(
        "Argentina",
        "ARS",
        "Buenos Aires",
        "America",
        405988,
        403033.0,
        emptyList(),
        listOf(Language("Español")),
        listOf(RegionalBloc("bloque1", "bloquesito")),
        listOf(Currency("ARS"))
    )

    val chl = Country(
        "Chile",
        "CHL",
        "Santiago de Chile",
        "America",
        55988,
        4033.0,
        listOf("Argentina"),
        listOf(Language("Español")),
        listOf(RegionalBloc("bloque1", "bloquesito")),
        listOf(Currency("CLP"))
    )

    val usa = Country(
        "Estados Unidos",
        "USA",
        "New York",
        "America",
        555988,
        4033.0,
        emptyList(),
        listOf(Language("English")),
        listOf(RegionalBloc("bloque2", "bloquesito2")),
        listOf(Currency("USD"))
    )

    val tai = Country(
        "Tailandia",
        "TAI",
        "Tailandia",
        "Asia",
        555988,
        4033.0,
        emptyList(),
        listOf(Language("Tailandes")),
        listOf(RegionalBloc("bloque4", "bloquesito4")),
        listOf(Currency("BAT")))

    val per = Country(
        "Peru",
        "PER",
        "lima",
        "America",
        555988,
        4033.0,
        emptyList(),
        listOf(Language("Español")),
        listOf(RegionalBloc("bloque2", "bloquesito2")),
        listOf(Currency("PEN")))

    val apimock = mockk<RestCountriesAPI>()
    TransformadorCountryAPais.apiRest = apimock

    every { apimock.todosLosPaises() } returns listOf(arg,chl,usa,tai,per)
    every { apimock.paisConCodigo(any()) } returns arg


    describe("son limitrofes 2 paises"){
        it("son"){
            Observatorio.sonLimitrofes("Chile","Argentina").shouldBeTrue()
        }
        it("no son"){
            Observatorio.sonLimitrofes("Tailandia","Chile").shouldBeFalse()
        }
    }
    describe("necesitan traduccion"){
        it("necesitan"){
            Observatorio.necesitanTraduccion("Chile","Estados Unidos").shouldBeTrue()
        }
        it("no necesitan"){
            Observatorio.necesitanTraduccion("Chile","Argentina").shouldBeFalse()
        }
    }
    describe("potenciales aliados"){
        it("son potenciales"){
            Observatorio.sonPotencialesAliados("Chile","Argentina").shouldBeTrue()
        }
        it("no son potenciales"){
            Observatorio.sonPotencialesAliados("Argentina","Estados Unidos").shouldBeFalse()
        }
    }
    describe("conviene ir de compras hacia") {
        it("conviene ir ") {
            Observatorio.convieneIrDeComprasDe_Hacia_("Argentina", "Chile").shouldBeFalse()
        }
      /*  it("no vonciene") {
            Observatorio.convieneIrDeComprasDe_Hacia_("Chile", "Argentina").shouldBeFalse()
        }*/
    }
    describe("equivalencias monetarias"){
        Observatorio.equivalenciasDe_Hacia_("Argentina","Chile",1).shouldBe(1.0)
    }
    describe("codigo iso de continentes"){
        Observatorio.codigoIsoDe5PaisesMayorPoblados().shouldBe(listOf("ARS","CHL","USA","TAI","PER"))
    }
    describe("continente mas plurinacional"){
        Observatorio.continenteConMasPaisesPlurinacionales().shouldBe("America")
    }
    describe("poblacion en islas"){
        Observatorio.promedioDensidadPoblacionalEnPaisesIslas().shouldBe(103)
    }
})