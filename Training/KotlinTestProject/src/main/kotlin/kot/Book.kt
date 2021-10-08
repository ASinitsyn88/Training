package kot

// Первичный конструктор (primary)
class Book(val name: String, var year: Int? = null, var price: Double? = null) {

    var author: String? = null

    // Вторичный конструктор (secondary)
    // Используется для определения вторичного способа создания класса
    constructor(name: String, year: Int? = null, price: Double? = null, author: String? = null) : this(name, year, price) {
        this.author = author
    }
}

fun main() {
    val book1 = Book("Книга1", 1999, 500.0)
    println("Name:" + book1.name)
    println("Author:" + book1.author)

    val book2 = Book("Книга2", 1999, 500.0, "Пушкин")
    println("Name:" + book2.name)
    println("Author:" + book2.author)
}