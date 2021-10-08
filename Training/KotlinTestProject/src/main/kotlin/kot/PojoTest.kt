package kot

import java.util.*

class PojoTest {
    // Объявление переменной name
    var name: String? = null
        // Геттер
        get() {
            field?.let {
                with(field) {
                    return this?.lowercase(Locale.getDefault())?.replaceFirstChar { char -> char.uppercaseChar() }
                }
            }
            return ""
        }

    // Объявление переменной age
    var age: Int = 0
        // Сеттер
        set(value) {
            field = if (value < 0) 0 else value
        }

    // Объявление переменной weight
    var weight: Double = 0.0
        // Сеттер
        set(value) {
            field = if (value < 0) 0.0 else value
        }

}

fun main() {
    val pojo = PojoTest()
    pojo.name = "вася"
    println(pojo.name)
}