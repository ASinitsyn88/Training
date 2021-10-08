package kot.exercise.abstractclass

class Bicycle(override var name: String = "Велосипед") : Transport(name) {

    override fun drive() {
        println("Велосипед едет...")
    }
}