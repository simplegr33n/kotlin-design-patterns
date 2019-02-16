package behavioural_patterns._05_observer

import kotlin.random.Random


var temperatureSensor: TemperatureSensor? = null
var cloudStoreInstance: CloudStore? = null
var localUIInstance: LocalUI? = null

fun main() {
    // Initialize concrete TemperatureSensor
    TemperatureSensor().initializeThermometer()

    // Initialize concrete TemperatureSensor Observers
    cloudStoreInstance = CloudStore()
    localUIInstance = LocalUI()

    runSimulation() // Simulate changing temperature and observer subscribe/unsubscribe events

}


// Abstract TemperatureObservable (temperature observable-side interface)
abstract class TemperatureObservable {
    object TemperatureObserversList: ArrayList<TemperatureObserver>()

    fun registerObserver(newObserver: TemperatureObserver) {
        TemperatureObserversList.add(newObserver)
    }

    fun removeObserver(oldObserver: TemperatureObserver) {
        TemperatureObserversList.remove(oldObserver)
    }

    fun notifyObservers() {
        for (observer in TemperatureObserversList) {
            observer.updateTemperature()
        }
    }

    fun getTotalSubscribers(): Int {
        return TemperatureObserversList.size
    }
}

// Abstract TemperatureObserver (temperature observer-side interface)
abstract class TemperatureObserver {
    abstract fun updateTemperature()

    fun subscribeToTemperature() {
        temperatureSensor?.registerObserver(this)
        println("$this subscribed to temperature!")
    }

    fun unsubFromTemperature() {
        temperatureSensor?.removeObserver(this)
        println("$this unsubscribed from temperature!")
    }
}

// Singleton TemperatureObservable (the actual temperature sensor itself is the observable)
class TemperatureSensor : TemperatureObservable() {
    var SENSOR_TEMPERATURE: Int? = null

    fun initializeThermometer() {
        if (temperatureSensor == null) {
            temperatureSensor = TemperatureSensor()
        } else {
            println("TemperatureSensor: Temp Sensor Already On!")
        }
    }

    fun changeInTemperature(temperature: Int? = null) {
        if (temperature != null) {
            // Temperature parameter set, use it to set temperature
            println("$SENSOR_TEMPERATURE°C -> $temperature°C || Subscribers (${getTotalSubscribers()})")
            if (temperature != SENSOR_TEMPERATURE) {
                SENSOR_TEMPERATURE = temperature
                notifyObservers()
            }
        } else {
            // Temperature parameter not set, find new random temperature
            val randTemp = Random.nextInt(-40, 40)
            println("$SENSOR_TEMPERATURE°C -> $randTemp°C || Subscribers (${getTotalSubscribers()})")
            if (randTemp != SENSOR_TEMPERATURE) {
                SENSOR_TEMPERATURE = randTemp
                notifyObservers()
            }
        }
    }

}

// Concrete CloudStore observer (subscribing application needing updates)
class CloudStore : TemperatureObserver() {
    override fun updateTemperature() {
        println("CloudStore: Uploaded Temperature (${temperatureSensor?.SENSOR_TEMPERATURE}°C) to the cloud!")
    }

    override fun toString(): String {
        return "CloudStore"
    }
}

// Concrete LocalUI observer (subscribing application needing updates)
class LocalUI : TemperatureObserver() {
    override fun updateTemperature() {
        println("LocalUI: Displaying Temperature (${temperatureSensor?.SENSOR_TEMPERATURE}°C) locally!")
    }

    override fun toString(): String {
        return "LocalUI"
    }
}
fun runSimulation() {
    simTemperatureChange() // Simulate temperature changes

    cloudStoreInstance?.subscribeToTemperature()
    localUIInstance?.subscribeToTemperature()
    simTemperatureChange() // Simulate temperature changes; CloudStore and LocalUI Observers now subscribed

    localUIInstance?.unsubFromTemperature()
    simTemperatureChange() // Simulate temperature changes; Local Observer now unsubscribed
}

fun simTemperatureChange() {
    temperatureSensor?.changeInTemperature()
    temperatureSensor?.changeInTemperature()
    temperatureSensor?.changeInTemperature()
    temperatureSensor?.changeInTemperature(7)
    temperatureSensor?.changeInTemperature(7)
    temperatureSensor?.changeInTemperature(7)
    temperatureSensor?.changeInTemperature(7)
    temperatureSensor?.changeInTemperature()
    temperatureSensor?.changeInTemperature()
    temperatureSensor?.changeInTemperature()

}

