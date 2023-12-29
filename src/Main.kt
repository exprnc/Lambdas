//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
typealias DoubleConversion = (Double) -> Double

fun convert(x: Double, converter: DoubleConversion) : Double {
    val result = converter(x)
    println("$x is converted to $result")
    return result
}

fun getConversionLambda(str: String) : DoubleConversion {
    if(str == "CentigradeToFahrenheit") {
        return { it * 1.8 * 32 }
    } else if (str == "KgsToPounds") {
        return { it * 2.204623 }
    } else if (str == "PoundsToUSTons") {
        return { it / 2000.0 }
    } else {
        return { it }
    }
}

fun combine(lambda1: DoubleConversion, lambda2: DoubleConversion) : DoubleConversion {
    return { x: Double -> lambda2(lambda1(x)) }
}

fun main() {
    //Преобразовать 2.5 кг в фунты
    println("Convert 2.5kg to Pounds: ${getConversionLambda("KgsToPounds") (2.5)}")

    //Определить два лямбда-выражения для преобразований
    val kgsToPoundsLambda = getConversionLambda("KgsToPounds")
    val poundsToUSTonsLambda = getConversionLambda("PoundsToUSTons")

    //Два лямбда-выражения преобразуются в одно новое
    val kgsToUSTonsLambda = combine(kgsToPoundsLambda, poundsToUSTonsLambda)

    //Использовать новое лямбд-выражение для преобразования
    // 17,4 кг в американские тонны
    val value = 17.4
    println("$value kgs is ${convert(value, kgsToUSTonsLambda)} US tons")
}