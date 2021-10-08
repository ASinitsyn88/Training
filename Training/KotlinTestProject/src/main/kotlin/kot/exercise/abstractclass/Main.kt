package kot.exercise.abstractclass

/**
 * 1. В Kotlin переопределять можно не только методы, но и поля
 */
fun main() {
    val car: Transport = Car()
    val bicycle: Transport = Bicycle()

    car.drive()
    bicycle.drive()

    // Smart cast
    if (car is Car) {
        car.name = "Машина2"
    }

    println(car.name)
}