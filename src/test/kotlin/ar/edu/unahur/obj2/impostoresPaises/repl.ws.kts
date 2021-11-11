import ar.edu.unahur.obj2.impostoresPaises.api.CurrencyConverterAPI
import ar.edu.unahur.obj2.impostoresPaises.api.RestCountriesAPI

// Algunos ejemplos para que jueguen un poco
// con lo que devuelve la API
/*
val api = RestCountriesAPI()
api.buscarPaisesPorNombre("guay")
api.paisConCodigo("CHL")

val currencyApi = CurrencyConverterAPI("492aab070de58ba9e5bb")
// PEN es el código del sol peruano
currencyApi.convertirDolarA("PEN")
*/

val lista1 = mutableListOf("azul", "amarillo")
val lista2 = mutableListOf("rojo", "amarillo")


lista1.any{lista2.contains(it)}