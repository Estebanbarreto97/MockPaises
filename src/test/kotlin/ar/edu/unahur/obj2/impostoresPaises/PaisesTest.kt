package ar.edu.unahur.obj2.impostoresPaises

import ar.edu.unahur.obj2.impostoresPaises.cli.Observatorio
import ar.edu.unahur.obj2.impostoresPaises.cli.Pais
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PaisesTest : DescribeSpec({
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
        listOf("Tailandes"))

    describe("test Para la clase país"){
        describe("aliades D:"){
            it("son aliados?"){
                chl.esLimitrofeCon(arg).shouldBeTrue()
            }
            it("no son aliados"){
                //por el orden de creación de los objetos mas arriba, no pude poner a chile como limitrofe de arg D:
                arg.esLimitrofeCon(chl).shouldBeFalse()
            }
            describe("plurinacionalidad") {
                it("es plurinacional") {
                    usa.esPlurinacional().shouldBeTrue()
                }
                it("no es plurinacional") {
                    arg.esPlurinacional().shouldBeFalse()
                }
            }
            describe("esUnaIsla"){
                it("es una isla"){
                    arg.esUnaIsla().shouldBeTrue()
                    //no tiene limitrofe
                }
                it("no es una isla"){
                    chl.esUnaIsla().shouldBeFalse()
                }
            }
            describe("densidad poblacional"){
                it("tiene esta cantidad"){
                    arg.densidadPoblacional().shouldBe(444)
                }
            }
            describe("el vecino mas poblado"){
                chl.vecinoMasPoblado()!!.nombre.shouldBe("Argentina")
            }
            describe("necesita traduccion"){
                it("necesita"){
                    chl.necesitaTraduccionPara(tai).shouldBeTrue()
                }
                it("no necesita"){
                    chl.necesitaTraduccionPara(arg).shouldBeFalse()
                }
            }
            describe("comparten bloque regional"){
                it("comparte"){
                    arg.comparteBloqueRegionalCon(chl).shouldBeTrue()
                }
                it("no comparte"){
                    usa.comparteBloqueRegionalCon(arg).shouldBeFalse()
                }
            }
            describe("potenciales aliados"){
                it("son potenciales"){
                    chl.esPotencialAliadoCon(arg)
                }
                it("no son potenciales"){
                    usa.esPotencialAliadoCon(arg)
                }
            }
            describe("convieneIrDeComprasA"){
                it("conviene") {
                    arg.convieneIrDeComprasA(chl).shouldBeTrue()
                }
                it("no conviene"){
                    chl.convieneIrDeComprasA(arg).shouldBeFalse()
                }
            }
            describe("cotizacion"){
                arg.cuantoEquivaleCon(chl,1).shouldBe(5.0)
            }

        }
    }
})
