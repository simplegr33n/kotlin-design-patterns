package _01_singleton

var singletonInstance: SingletonExample? = null
var timesInitialized: Int = 0

fun main() {
    println(SingletonExample)

    println("First Try")
    SingletonExample.tryInitSingleton()

    println("Second Try")
    SingletonExample.tryInitSingleton()

    println("Third Try")
    SingletonExample.tryInitSingleton()

    println(singletonInstance)
}

object SingletonExample {

    private var singletonValue: Int? = null

    fun tryInitSingleton() {
        if (singletonInstance == null) {
            singletonInstance = SingletonExample
            timesInitialized++
        }

        singletonValue = timesInitialized
        val hashCode = singletonInstance.hashCode()

        println("Singleton Value: $singletonValue, HashCode: $hashCode")
    }

    override fun toString(): String {
        when (singletonValue) {
            0 -> return "Impossible! SingletonExample object #$singletonValue, oh no..."
            1 -> return "SingletonExample object #$singletonValue, great!"
            // below is a range trick using Int.MAX_VALUE basically equating to if (singletonValue >= 2)
            in 2..Int.MAX_VALUE -> return "Impossible! SingletonExample object #$singletonValue, oh no..."
            // else is null (uninitialized state)
            else -> return "$singletonValue object"
        }
    }

}