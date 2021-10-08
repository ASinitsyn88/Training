package kot.exercise.singleton

// Для создания singleton достаточно просто добавить object
object Database {
    val data = mutableListOf<String>()
    fun insertData(str: String) {
        data.add(str)
    }
}

fun main() {
    Database.insertData("123")
}