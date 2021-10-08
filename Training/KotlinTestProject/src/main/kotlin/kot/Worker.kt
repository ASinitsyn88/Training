package kot

import java.time.LocalDate

open class Worker(val name: String, val age: Int, val position: String, val startYear: Int) {

    val experience:Int
    get() = LocalDate.now().year - startYear

    open fun work() {
        println("Работаю...")
    }

    // Операторы, определяющие логику деструктора
    // Если класс объявлен как data то эти операторы определять не нужно
    operator fun component1() = name
    operator fun component2() = position
    operator fun component3() = startYear
}

fun Worker.printInfo() {
    println("Имя: $name Должность: $position Год трудоустройства: $startYear Стаж: $experience")
}

fun main() {
    val worker = Worker("Иван", 18, "Разработчик", 2019)
    worker.printInfo()
    // Деструктор
    val (name: String, position: String, startYear: Int) = worker;
}