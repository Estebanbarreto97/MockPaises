package ar.edu.unahur.obj2.impostoresPaises.cli

import ar.edu.unahur.obj2.impostoresPaises.api.Country
import ar.edu.unahur.obj2.impostoresPaises.api.Currency
import ar.edu.unahur.obj2.impostoresPaises.api.CurrencyConverterAPI
import ar.edu.unahur.obj2.impostoresPaises.api.RestCountriesAPI
import java.util.*


object TransformadorCountryAPais{
    var apiRest = RestCountriesAPI()
    var apiCurr = CurrencyConverterAPI("d04de9e40952afdda643")

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
                paisApi.area ?:paisApi.population.toDouble(),
                paisApi.region,
                this.currencyCodeOrNull(paisApi.currencies),
                /*apiCurr.convertirDolarA(currencyCodeOrNull(paisApi.currencies))*/ 1.0,
                if (!esUnLimitrofe)
                    paisApi.borders!!.map { this.transformarAPaís(apiRest.paisConCodigo(it),true) }
                else
                    listOf(),
                paisApi.regionalBlocs!!.map { it.acronym },
                paisApi.languages.map { it.name })

    }

    fun currencyCodeOrNull(lista : List<Currency>?) : String{
            if (lista.isNullOrEmpty())
                return "USD"
            else
                return lista.first().code
    }

}