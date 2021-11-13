package ar.edu.unahur.obj2.impostoresPaises.cli

import ar.edu.unahur.obj2.impostoresPaises.api.Country
import ar.edu.unahur.obj2.impostoresPaises.api.CurrencyConverterAPI
import ar.edu.unahur.obj2.impostoresPaises.api.RestCountriesAPI


object TransforamadorCountryAPais{
    val apiRest = RestCountriesAPI()
    val apiCurr = CurrencyConverterAPI("492aab070de58ba9e5bb")
    fun transformarListaMundialAListaObservatorio() {
        apiRest.todosLosPaises().forEach {
            Observatorio.listaDePaises.put(it.name, transformarAPaís(it))
        }
    }
    fun aListaDeContinentes(){
        apiRest.todosLosPaises().map {it.region}.forEach{
            Observatorio.listaDeContinentes.put(it, listaDePaisesParaContinente(it))}
    }
    fun listaDePaisesParaContinente(continente : String) = apiRest.todosLosPaises().filter { it.region == continente }.map{ transformarAPaís(it)}


    fun transformarCodigoAPais(nombre : String) : Pais {
        return transformarAPaís(apiRest.paisConCodigo(apiRest.buscarPaisesPorNombre(nombre).first().alpha3Code))
    }
    fun transformarAPaís(paisApi : Country) =
        Pais(paisApi.name,
            paisApi.alpha3Code,
            paisApi.population,
            (paisApi.area)!!.toDouble(),
            paisApi.region,
            paisApi.currencies!!.first().code,
            apiCurr.convertirDolarA(paisApi.currencies!!.first().code)?:0.0,
            paisApi.borders!!.map{ transformarCodigoAPais(it)},
            paisApi.regionalBlocs!!.map {it.acronym},
            paisApi.languages.map { it.name })

    //es para probar
    fun devolvertodo() = apiRest.todosLosPaises().map { transformarAPaís(it) }
}