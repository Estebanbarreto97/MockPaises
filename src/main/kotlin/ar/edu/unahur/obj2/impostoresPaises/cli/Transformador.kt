package ar.edu.unahur.obj2.impostoresPaises.cli

import ar.edu.unahur.obj2.impostoresPaises.api.Country
import ar.edu.unahur.obj2.impostoresPaises.api.Currency
import ar.edu.unahur.obj2.impostoresPaises.api.CurrencyConverterAPI
import ar.edu.unahur.obj2.impostoresPaises.api.RestCountriesAPI
import java.util.*


object TransforamadorCountryAPais{
    val apiRest = RestCountriesAPI()
    val apiCurr = CurrencyConverterAPI("d04de9e40952afdda643")
    fun transformarListaMundialAMapObservatorio() : Map<String, Pais>{
        return apiRest.todosLosPaises().map {it.name to transformarAPaís(it,false) }.toMap()
        }

    fun transformarListaMundialAMapDeContinente(): Map<String, List<Pais>>{
        return apiRest.todosLosPaises().map {it.region to listaDePaisesParaContinente(it.region)}.toMap()
    }

    fun listaDePaisesParaContinente(continente : String) = apiRest.todosLosPaises().filter { it.region == continente }.map{ transformarAPaís(it,false)}

    fun transformarAPaís(paisApi : Country, esUnLimitrofe : Boolean) : Pais{

        return Pais(paisApi.name,
                paisApi.alpha3Code,
                paisApi.population,
                this.areaOrNull(paisApi),
                paisApi.region,
                this.currencyCodeOrNull(paisApi.currencies),
                apiCurr.convertirDolarA(paisApi.currencies!!.first().code)!!.toDouble(),
                if (!esUnLimitrofe)
                    paisApi.borders!!.map { this.transformarAPaís(apiRest.paisConCodigo(it),true) }
                else
                    listOf(),
                paisApi.regionalBlocs!!.map { it.acronym },
                paisApi.languages.map { it.name })

    }

    fun areaOrNull(paisApi: Country) : Double {
        if (paisApi.area == null)
            return paisApi.population.toDouble()
        else
            return paisApi.area.toDouble()
    }

    fun currencyCodeOrNull(lista : List<Currency>?) : String{
            if (lista.isNullOrEmpty())
                return "USD"
            else
                return lista.first().code
    }

   /*fun convertirADolarOrNull(lista : List<Currency>?) : Double{
        if (lista.isNullOrEmpty())
            return 1.0
        else
            if (apiCurr.convertirDolarA(lista.first().code) == null)
                return 1.0
            else
                return apiCurr.convertirDolarA(lista.first().code)!!.toDouble()
*/


    //es para probar
    fun devolvertodo() = apiRest.todosLosPaises().map { transformarAPaís(it,false).nombre }

}