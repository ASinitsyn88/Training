package kot.exercise.delegation.`class`

fun main() {
    val b = BaseImpl(10)
    Derived(b).print() // prints 10
}