package ar.edu.unahur.obj2.impostoresPaises.cli

import java.util.Comparator

class Pais (val nombre : String,
            val codigoIso3 : String,
            var poblacion : Long,
            val superficie : Double,
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
    fun esLimitrofeCon(paisAComparar : Pais) = this.paisesLimitrofes.any{it.nombre == paisAComparar.nombre}
    fun necesitaTraduccionPara(paisAComparar: Pais) = !this.idiomasOficiales.any{paisAComparar.idiomasOficiales.contains(it)}
    fun comparteBloqueRegionalCon(paisAComparar: Pais) = this.bloquesRegionales.any{paisAComparar.bloquesRegionales.contains(it)}
    fun esPotencialAliadoCon(paisAComparar: Pais) = !(this.necesitaTraduccionPara(paisAComparar)) and this.comparteBloqueRegionalCon(paisAComparar)
    fun convieneIrDeComprasA(paisAComparar: Pais) = this.cotizacionDolar < paisAComparar.cotizacionDolar
    fun cuantoEquivaleCon(paisAComparar: Pais, monto : Int) = (monto / this.cotizacionDolar) * paisAComparar.cotizacionDolar
}

