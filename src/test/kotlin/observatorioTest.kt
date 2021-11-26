import ar.edu.unahur.obj2.impostoresPaises.cli.Observatorio
import ar.edu.unahur.obj2.impostoresPaises.cli.Pais
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class observatorioTest : DescribeSpec ({
    val arg = Pais("Argentina",
        "ARG",
        40000,
        90.000,
        "America",
        "ARS",
        100.0,
        listOf(),
        listOf("bloque1"),
        listOf("Español"))

    val chl = Pais("Chile",
        "CHL",
        10000,
        40.000,
        "America",
        "CLP",
        500.0,
        listOf(arg),
        listOf("bloque1"),
        listOf("Español"))

    val usa = Pais("Estados Unidos",
        "USA",
        90000,
        200.000,
        "America",
        "USD",
        1.0,
        listOf(),
        listOf("bloque2"),
        listOf("English","Español"))

    val tai = Pais("Tailandia",
        "TAI",
        20000,
        70.000,
        "Asia",
        "BAT",
        1.0,
        listOf(),
        listOf("bloque3"),
        listOf("tailandes"))

    val per = Pais("Perú",
        "PER",
        30000,
        50.000,
        "America",
        "PEN",
        50.0,
        listOf(),
        listOf("bloque1"),
        listOf("Español"))


    Observatorio.listaDePaises = mutableMapOf(Pair(arg.nombre,arg),Pair(chl.nombre,chl),Pair(usa.nombre,usa),Pair(tai.nombre,tai),
        Pair(per.nombre,per)
    )
    Observatorio.listaDeContinentes = mutableMapOf(Pair("America",listOf(arg,chl,usa,per)), Pair("Asia", listOf(tai)))

    describe("son limitrofes 2 paises"){
        it("son"){
            Observatorio.sonLimitrofes(chl.nombre,arg.nombre).shouldBeTrue()
        }
        it("no son"){
            Observatorio.sonLimitrofes(tai.nombre,chl.nombre).shouldBeFalse()
        }
    }
    describe("necesitan traduccion"){
        it("necesitan"){
            Observatorio.necesitanTraduccion(chl.nombre,tai.nombre).shouldBeTrue()
        }
        it("no necesitan"){
            Observatorio.necesitanTraduccion(chl.nombre,arg.nombre).shouldBeFalse()
        }
    }
    describe("potenciales aliados"){
        it("son potenciales"){
            Observatorio.sonPotencialesAliados(chl.nombre,arg.nombre).shouldBeTrue()
        }
        it("no son potenciales"){
            Observatorio.sonPotencialesAliados(arg.nombre,usa.nombre).shouldBeFalse()
        }
    }
    describe("conviene ir de compras hacia") {
        it("conviene ir ") {
            Observatorio.convieneIrDeComprasDe_Hacia_(arg.nombre, chl.nombre).shouldBeTrue()
        }
        it("no vonciene") {
            Observatorio.convieneIrDeComprasDe_Hacia_(chl.nombre, arg.nombre).shouldBeFalse()
        }
    }
    describe("equivalencias monetarias"){
        Observatorio.equivalenciasDe_Hacia_(arg.nombre,chl.nombre,1).shouldBe(5.0)
    }
    describe("codigo iso de continentes"){
        Observatorio.codigoIsoDe5PaisesMayorPoblados().shouldBe(listOf(chl.codigoIso3,tai.codigoIso3,arg.codigoIso3,usa.codigoIso3,per.codigoIso3))
    }
    describe("continente mas plurinacional"){
        Observatorio.continenteConMasPaisesPlurinacionales().shouldBe("America")
    }
    describe("poblacion en islas"){
        Observatorio.promedioDensidadPoblacionalEnPaisesIslas().shouldBe(444)
    }
})