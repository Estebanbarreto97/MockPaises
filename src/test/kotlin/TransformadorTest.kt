import ar.edu.unahur.obj2.impostoresPaises.api.*
import ar.edu.unahur.obj2.impostoresPaises.cli.TransformadorCountryAPais
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class TransformadorTest : DescribeSpec ({
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
            listOf(),
            listOf(Language("Spanish"), Language("Aymara"), Language("Quechua")),
            listOf(RegionalBloc("USAN", "Union of South American Nations")),
            listOf(Currency("BOB"))
        )
    describe("transformar"){
        val apimock = mockk<RestCountriesAPI>()
        TransformadorCountryAPais.apiRest = apimock
        every { apimock.paisConCodigo("BOL") } returns bolivia
        every { apimock.paisConCodigo("CPV") } returns caboVerde

        val pais = TransformadorCountryAPais.transformarAPaís(bolivia,false)
        pais.nombre.shouldBe("Bolivia (Plurinational State of)")
    }
})