import ar.edu.unahur.obj2.impostoresPaises.api.CurrencyConverterAPI
import ar.edu.unahur.obj2.impostoresPaises.api.RestCountriesAPI
import ar.edu.unahur.obj2.impostoresPaises.cli.TransformadorCountryAPais
import javax.xml.crypto.dsig.Transform

// Algunos ejemplos para que jueguen un poco
// con lo que devuelve la API

val arg = TransformadorCountryAPais.transformarAPaís(RestCountriesAPI().buscarPaisesPorNombre("argentina").first(),false)

val chl = TransformadorCountryAPais.transformarAPaís(RestCountriesAPI().buscarPaisesPorNombre("chile").first(),false)
arg.paisesLimitrofes[2].nombre

arg.esLimitrofeCon(chl)