package kot.exercise.abstractclass

class Car(override var name: String = "Машина") : Transport(name) {

    override fun drive() {
        println("Машина едет...")
    }

    fun startEngine(): Boolean {
        println("Запускаю двигатель...")
        return true
    }
}