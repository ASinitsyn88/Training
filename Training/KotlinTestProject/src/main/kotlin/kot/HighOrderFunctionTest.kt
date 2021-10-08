package kot

import java.util.*

var name: String? = "abcdef";
val list2: MutableList<Int>? = mutableListOf();

fun main() {
    // let позволяет работать со значением как с not nullable-версией (позволяет избежать проверок if - else)
    // внутри блока let к переменной можно обращаться как к it
    name?.let {
        if (it.length > 5) {
            println("Name length is ${it.length}")
        }
    }

    // with создаёт "область видимости" внутри которой можно не указывать название указанной переменной для вызова её методов
    val list = mutableListOf<Int>()
    with(list) {
        for (i in 0 until 1000) {
            add((Math.random() * 100).toInt())
            println(sum())
            println(average())
            println(minOrNull())
            println(maxOrNull())
        }
    }

    // list2? - говорит о том что если list2 != null - то будет выполнен код в скобках {}
    list2?.let {
        with(it) {
            for (i in 0 until 1000) {
                add((Math.random() * 1000).toInt())
            }
            val result = filter { it % 2 == 0 }.take(100)
            for (i in result) {
                println(i)
            }
        }
    }

    modifyString("hello world!") { it.uppercase(Locale.getDefault()) }
    println(sumCollectionNumbers(listOf(2, 4, 6)) { l: List<Int> -> l.sum() })
}

// Пример создания функции высшего порядка
// Функция высшего порядка - это функция которая принимает или возвращает другую функцию
// В данном примере метод принимает функцию (String) -> String - принимает строку и возвращает строку
fun modifyString(string: String, modify: (String) -> String) : String {
    return modify(string)
}

// без inline будет создаваться анонимный класс внутри которого будет происходить вызов кода функции
// создание анонимного класса ресурсоёмкая операция и чтобы делать всё тоже самое но без создания анонимного класса
// нужно указывать inline
inline fun sumCollectionNumbers(list: List<Int>, sumFunc: (List<Int>) -> Int) : Int {
    return sumFunc(list)
}