import ar.edu.unahur.obj2.impostoresPaises.api.Country
import ar.edu.unahur.obj2.impostoresPaises.api.Currency
import ar.edu.unahur.obj2.impostoresPaises.api.Language
import ar.edu.unahur.obj2.impostoresPaises.api.RegionalBloc
import ar.edu.unahur.obj2.impostoresPaises.cli.TransformadorCountryAPais
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

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
            listOf("ARG", "BRA", "CHL", "PRY", "PER"),
            listOf(Language("Spanish"), Language("Aymara"), Language("Quechua")),
            listOf(RegionalBloc("USAN", "Union of South American Nations")),
            listOf(Currency("BOB"))
        )
    describe("transformar"){
        val pais = TransformadorCountryAPais.transformarAPaís(bolivia,false)
        pais.nombre.shouldBe("Bolivia (Plurinational State of)")
    }
})