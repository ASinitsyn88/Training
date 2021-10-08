package kot.exercise.delegation.`class`

class BaseImpl(val x: Int) : Base {
    override fun print() {
        println(x)
    }
}