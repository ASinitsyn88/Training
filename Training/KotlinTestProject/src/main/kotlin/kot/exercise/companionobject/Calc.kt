package kot.exercise.companionobject

class Calc {

    // Аналог статического метода
    companion object {
        // Константа
        const val PI = 3.14
        fun square(num: Int) = num * num
        fun lengthOfCircle(radius: Float) = 2 * PI * radius
    }
}

fun main() {
    println(Calc.square(4))
    println(Calc.lengthOfCircle(10f))
}