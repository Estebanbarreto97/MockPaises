/*package ar.edu.unahur.obj2.impostoresPaises.api

import ar.edu.unahur.obj2.impostoresPaises.cli.Pais
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.Test
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify

//import net.bytebuddy.asm.Advice

// Estos tests están simplemente como ejemplo de lo que **no** hay que hacer.
// Prueben de ejecutarlos sin internet y van a ver cómo fallan miserablemente.



class RestCountriesAPITest : DescribeSpec({
  describe("API de países") {
    val MockApi = mockk<RestCountriesAPI>()
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
        listOf("ARG", "BRA", "CHL", "PRY", "PER"),
        listOf(Language("Spanish"), Language("Aymara"), Language("Quechua")),
        listOf(RegionalBloc("USAN", "Union of South American Nations")),
        listOf(Currency("BOB"))
      )

    it("buscar por nombre") {
      every { MockApi.buscarPaisesPorNombre(any()) } returns listOf(bolivia)
      MockApi.buscarPaisesPorNombre("bolivia")
      verify { MockApi.buscarPaisesPorNombre("bolivia") }

    }

    it("buscar por código") {
      every { MockApi.paisConCodigo(any()) } returns bolivia
      MockApi.paisConCodigo("BOL")
      verify { MockApi.paisConCodigo("BOL") }
    }

    it("info de todos los países") {
      every { MockApi.todosLosPaises() } returns listOf(bolivia,caboVerde)
      MockApi.todosLosPaises().shouldHaveSize(2)
    }
  }
})


*/