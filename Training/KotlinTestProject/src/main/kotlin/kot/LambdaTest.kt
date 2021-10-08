package kot

/**
 * (input) -> output = {function}
 */
fun main() {
    // Вычисляет квадрат для переданного числа
    val square: (Int) -> Int = {it * it}
    println(square(5))

    // Вычисляет периметр прямоугольника
    val rectanglePerimeter: (Int, Int) -> Int = {a:Int, b:Int -> 2 * (a + b)}
    println(rectanglePerimeter(2, 4))

    // Выводит указанное название
    val showValue: (String) -> Unit = { println("Привет, $it")}
    showValue("Вася")

    // Сортирует массив по убыванию
    val arrayDescOrder: (Array<Int>) -> Array<Int> = { it.sortDescending(); it }
    arrayDescOrder(arrayOf(1, 2, 5, 3, 4)).forEach { print(it) }
    println()

    // На основе исходного массива создаёт новый массив который содержит только чётные числа
    val listOfNumbers = IntArray(100) { it }
    val listOfEvenNumbers = listOfNumbers.filter { it % 2 == 0 }
    println(listOfEvenNumbers)

    // На основе исходного массива создаёт новый массив который содержит только имена начинающиеся с А
    val listOfNames = arrayListOf("Аня", "Алиса", "Ваня", "Наталья")
    val listStartsFromA = listOfNames.filter { it.startsWith("А") }
    println(listStartsFromA)

    // Умножение на 2 для каждого элемента коллекции
    val numbers = (0..100).toList()
    val doubledNumbers = numbers.map{ it * 2 }
    println(doubledNumbers)

    // Преобразование числовой коллекции в строковую
    val stringNumbers = numbers.map{ it.toString() }
    println(stringNumbers)

    // Получить первые 30 элементов из списка
    val employeeNumbers = (0..1000).toList()
    val employees = employeeNumbers.map{ "Employee №$it" }
    println(employees.take(30))

    // Сформировать бесконечную последовательность чётных чисел и получить первые 5 элементов
    val sequenceArray = generateSequence(0, {it + 2})
    val sequenceElements = sequenceArray.take(5)
    for (i in sequenceElements) {
        println("Sequence number $i")
    }

    // Сформировать бесконечную последовательность строк и получить первые 100 элементов
    val empList = generateSequence(1) { it + 1 }.take(100).map{ "Сотрудник №$it" }
    for (i in empList) {
        println(i)
    }

    // Объединить 2 списка в список Pair
    val names = mutableListOf<String>()
    val phones = mutableListOf<Long>()
    for (i in 0..1000) {
        names.add("Имя$i")
        phones.add(79_000_000_000 + (Math.random() * 1_000_000_000).toLong())
    }
    val users = names.zip(phones)
    for (user in users) {
        println("Имя: ${user.first} Телефон: ${user.second}")
    }

    // Преобразование списка строк в список Pair
    val fullNameList = mutableListOf<String>("Синицын Александр", "Барсуков Сергей", "Елагин Илья")
    val lastnameAndNamePairList = fullNameList.map{ Pair(it.substringBefore(" "), it.substringAfter(" ")) }
    for (i in lastnameAndNamePairList) {
        println("Фамилия: ${i.first} Имя: ${i.second}")
    }

    // Пример flatMap
    val revenueByWeek = listOf(
        listOf(8, 5, 6, 1, 2),
        listOf(4, 7, 5, 8, 2),
        listOf(8, 7, 5, 1, 2),
        listOf(8, 5, 5, 1, 2)
    )
    println(revenueByWeek.flatten().average())

    // Пример инициализации Map 1
    val data = mapOf<String, List<Int>>(
        "file1" to listOf(15, 20, 45, 15, 12),
        "file2" to listOf(35, 20, 45, 15, 12),
        "file3" to listOf(15, 40, 45, 15, 72)
    )

    // Пример инициализации Map 2
    val data2 = mapOf<String, List<Int>>(
        Pair("file1", listOf(15, 20, 45, 15, 12)),
        Pair("file2", listOf(35, 20, 45, 15, 12)),
        Pair("file3", listOf(15, 40, 45, 15, 72))
    )

    val data3 = mapOf(
        "Январь" to listOf(100, 100, 100, 100),
        "Февраль" to listOf(200, 200, -190, 200),
        "Март" to listOf(300, 180, 300, 100),
        "Апрель" to listOf(250, -250, 100, 300),
        "Май" to listOf(200, 100, 400, 300),
        "Июнь" to listOf(200, 100, 300, 300),
    )
    printInfo(data3);
}

fun printInfo(data: Map<String, List<Int>>) {
    val validData = data.filterNot { it.value.any{it < 0} }
    val averageWeek = validData.flatMap { it.value }.average()
    println("Средняя выручка в неделю: $averageWeek")

    val listOfSum = validData.map { it.value.sum() }
    val max = listOfSum.maxOrNull()
    val min = listOfSum.minOrNull()
    val averageMonth = listOfSum.average();

    val maxMonths = validData.filter { it.value.sum() == max }.keys
    val minMonths = validData.filter { it.value.sum() == min }.keys

    println("Средняя выручка в месяц: $averageMonth")
    println("Максимальная выручка в месяц: $max")
    println("Была в следующих месяцах: ")
    for (month in maxMonths) {
        println("$month")
    }

    println("\nМинимальная выручка в месяц: $min")
    print("Была в следующих месяцах: ")
    for (month in minMonths) {
        print("$month")
    }

    val invalidData = data.filter { it.value.any{it < 0} }
    val errorMonths = invalidData.keys
    println("\nОшибки произошли в следующих месяцах:")
    for (month in errorMonths) {
        print("$month ")
    }
}