package ar.edu.unahur.obj2.impostoresPaises.cli

import java.util.Comparator

class Pais (val nombre : String,
            val codigoIso3 : String,
            var poblacion : Int,
            val superficie : Int,
            val continente : String,
            val codigoMoneda : String,
            var cotizacionDolar : Double,
            val paisesLimitrofes: List<Pais>,
            val bloquesRegionales : List<String>,
            val idiomasOficiales : List<String>){

    fun esPlurinacional() = idiomasOficiales.size > 1
    fun esUnaIsla() = paisesLimitrofes.size == 0
    fun densidadPoblacional() = (poblacion / superficie).toInt()
    fun vecinoMasPoblado() = (paisesLimitrofes + this).maxByOrNull{it.poblacion}
    fun esLimitrofeCon(paisAComparar : Pais) = this.paisesLimitrofes.contains(paisAComparar)
    fun necesitaTraduccionPara(paisAComparar: Pais) = this.idiomasOficiales.any{paisAComparar.idiomasOficiales.contains(it)}
    fun comparteBloqueRegionalCon(paisAComparar: Pais) = this.bloquesRegionales.any{paisAComparar.bloquesRegionales.contains(it)}
    fun esPotencialAliadoCon(paisAComparar: Pais) = !(this.necesitaTraduccionPara(paisAComparar)) and this.comparteBloqueRegionalCon(paisAComparar)
    fun convieneIrDeComprasA(paisAComparar: Pais) = this.cotizacionDolar < paisAComparar.cotizacionDolar
    fun cuantoEquivaleCon(paisAComparar: Pais, monto : Int) = (monto / this.cotizacionDolar) * paisAComparar.cotizacionDolar
}

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
