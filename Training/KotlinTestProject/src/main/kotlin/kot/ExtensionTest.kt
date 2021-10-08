package kot

// Extension функции позволяют расширять какими-либо методами указанные классы
fun main() {
    val x = 5;
    println(x.isPositive())

    val list = listOf<Int>(1, 2, 3)

    // Умеет работать с объектами типа List
    myWith(list) {
        sum()
        average()
    }

    // Умеет работать с объектами любого типа
    myWith2(x) {
        dec()
    }
}

fun Int.isPositive() = this >= 0

fun myWith(list: List<Int>, operation: List<Int>.() -> Unit) {
    list.operation()
}

inline fun<T> myWith2(list: T, operation: T.() -> Unit) {
    list.operation()
}