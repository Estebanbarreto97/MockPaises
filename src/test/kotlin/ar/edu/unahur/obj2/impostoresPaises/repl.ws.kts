import ar.edu.unahur.obj2.impostoresPaises.api.CurrencyConverterAPI
import ar.edu.unahur.obj2.impostoresPaises.api.RestCountriesAPI
import ar.edu.unahur.obj2.impostoresPaises.cli.TransforamadorCountryAPais
import javax.xml.crypto.dsig.Transform

// Algunos ejemplos para que jueguen un poco
// con lo que devuelve la API

/*
val api = RestCountriesAPI()

api.buscarPaisesPorNombre("guay")
api.paisConCodigo("CHL")
*/
/*
val currencyApi = CurrencyConverterAPI("d04de9e40952afdda643")
//PEN es el código del sol peruano
CurrencyConverterAPI("d04de9e40952afdda643").convertirDolarA("PEN")
*/
//RestCountriesAPI().todosLosPaises()
//TransforamadorCountryAPais.devolvertodo()

//CurrencyConverterAPI("d04de9e40952afdda643").convertirDolarA("feg")

object test{
    fun devolvererror(num : Int) {
        if (num == 2)
            error("holis")
    }

    fun averquepasa(num : Int) : Int {
        devolvererror(num)
        return 5
    }

}

test.averquepasa(2)