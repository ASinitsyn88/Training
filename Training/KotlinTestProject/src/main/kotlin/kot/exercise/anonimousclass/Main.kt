package kot.exercise.anonimousclass

import kot.exercise.abstractclass.Transport

fun main() {
    // Анонимный класс
    val bus: Transport = object : Transport("Автобус") {
        override fun drive() {
            println("Автобус едет...")
        }
    }
    travel(bus)
}

fun travel(transport: Transport) {
    transport.drive()
}