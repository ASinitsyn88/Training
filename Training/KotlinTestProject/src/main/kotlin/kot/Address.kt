package kot

class Address(val city: String, val street: String, val house: Int) {

    fun copy(city: String = this.city, street: String = this.street, house: Int = this.house) : Address {
        return Address(city, street, house)
    }

    operator fun component1(): String = city
    operator fun component2(): String = street
    operator fun component3(): Int = house
}

fun main() {
    val address1 = Address("Жуковский", "Баженова", 41)
    val address2 = address1.copy()
    println("Address1: $address1")
    println("Address2: $address2")
    println("Address1 hashcode: ${address1.hashCode()}")
    println("Address2 hashcode: ${address2.hashCode()}")
    println("Address1 equals Address2: ${address1 == address2}")

    val (city: String, street: String, house: Int) = address1;
    val city1 = city
    val street1 = street
    val house1 = house
}