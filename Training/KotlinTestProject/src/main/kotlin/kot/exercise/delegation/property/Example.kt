package kot.exercise.delegation.property

/**
 * Делегирование свойства
 * Методы get и set из класса Delegate делегируются свойству var p
 * При обращении к этому свойству будут вызываться get и set из класса Delegate
 */
class Example {
    var p: String by Delegate()
}