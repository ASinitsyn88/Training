package kot.exercise.generics

fun main() {
    // Получение параметризованного списка
    val myListOf = MyArrayList.myListOf(1, 2, 3, 4, 5)

    val list: MyList<String> = MyArrayList()
    for (i in 0..100) {
        list.add("$i")
    }
    for (i in 0..90) {
        list.removeAt(0)
    }
    for (i in 0 until list.size()) {
        println(list.get(i))
    }
}