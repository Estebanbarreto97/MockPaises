package ar.edu.unahur.obj2.impostoresPaises.cli

import ar.edu.unahur.obj2.impostoresPaises.api.Country
import ar.edu.unahur.obj2.impostoresPaises.api.CurrencyConverterAPI
import ar.edu.unahur.obj2.impostoresPaises.api.RegionalBloc
import ar.edu.unahur.obj2.impostoresPaises.api.RestCountriesAPI

object Observatorio {
    val listaDePaises = mutableMapOf<String, Pais>()
    val listaDeContinentes = mutableMapOf<String, List<Pais>>()
    fun sonLimitrofes(pais1 : String, pais2: String) = listaDePaises.getValue(pais1).esLimitrofeCon(listaDePaises.getValue(pais2))
    fun necesitanTraduccion(pais1: String, pais2: String) = listaDePaises.getValue(pais1).necesitaTraduccionPara(
        listaDePaises.getValue(pais2))
    fun sonPotencialesAliados(pais1: String, pais2: String) = listaDePaises.getValue(pais1).esPotencialAliadoCon(
        listaDePaises.getValue(pais2))
    fun convieneIrDeComprasDe_Hacia_(pais1: String, pais2: String) = listaDePaises.getValue(pais1).convieneIrDeComprasA(
        listaDePaises.getValue(pais2))
    fun equivalenciasDe_Hacia_(pais1: String, pais2: String, monto : Int) = listaDePaises.getValue(pais1).cuantoEquivaleCon(
        listaDePaises.getValue(pais2),monto)
    fun codigoIsoDe5PaisesMayorPoblados(): List<String> {
        val listaDeCodigos = mutableListOf<String>()
        listaDePaises.toList().sortedBy{(k,v) -> v.densidadPoblacional()}.subList(0,5).toMap().forEach{(k,v) -> listaDeCodigos.add(v.codigoIso3)}
        return listaDeCodigos
    }
    fun continenteConMasPaisesPlurinacionales() = listaDeContinentes.maxByOrNull{(k,v) -> v.filter{it.esPlurinacional()}.size}!!.key
    fun promedioDensidadPoblacionalEnPaisesIslas() = listaDePaises.filterValues{it.esUnaIsla()}.toList().sumBy{(k,v) -> v.densidadPoblacional()} / listaDePaises.filterValues{it.esUnaIsla()}.size


}
