package kot

class Programmer(val lang: String, workerName: String, workerAge: Int, workerStartYear: Int) : Worker(workerName, workerAge, "Программист", workerStartYear) {

    override fun work() {
        println("Пишу код на $lang")
    }
}

fun main() {
    val worker1 = Programmer("java", "Иванов Иван", 18, 2019)
    val worker2 = Worker("Петров Сергей", 18, "Слесарь", 2015)

    val workerList = arrayListOf(worker1, worker2)
    for (i in workerList) {
        i.work()
    }
}