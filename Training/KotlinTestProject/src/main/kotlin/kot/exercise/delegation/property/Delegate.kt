package kot.exercise.delegation.property

import kotlin.reflect.KProperty

class Delegate {

    operator fun getValue(thisRef: Any?, property: KProperty<*>) : String {
        return "$thisRef, спасибо за делегирование мне '${property.name}'!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value было присвоено значению '${property.name} в $thisRef.'")
    }
}