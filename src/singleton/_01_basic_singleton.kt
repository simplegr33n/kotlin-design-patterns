package singleton

var singletonInstance: SingletonExample? = null
var testValue: Int = 0

fun main() {
    println("First Try")
    SingletonExample.tryInitSingleton()

    println("Second Try")
    SingletonExample.tryInitSingleton()

    println("Third Try")
    SingletonExample.tryInitSingleton()
}

object SingletonExample {

    private var value: Int? = null

    fun tryInitSingleton() {
        if (singletonInstance == null) {
            singletonInstance = SingletonExample
            testValue++
            value = testValue
        }

        val hashCode = singletonInstance.hashCode()

        println("Singleton Value: $value, HashCode: $hashCode")
    }

}