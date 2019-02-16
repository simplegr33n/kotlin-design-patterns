package behavioural_patterns._05_observer

import kotlin.random.Random


var temperatureSensor: TemperatureSensor? = null
var cloudStoreInstance: CloudStore? = null
var localUIInstance: LocalUI? = null

fun main() {
    TemperatureSensor().initializeThermometer()
    cloudStoreInstance = CloudStore()
    cloudStoreInstance?.subscribeToTemperature()
    localUIInstance = LocalUI()
    localUIInstance?.subscribeToTemperature()


    temperatureSensor?.changeInTemperature(10)
    temperatureSensor?.changeInTemperature(9)
    temperatureSensor?.changeInTemperature(8)
    temperatureSensor?.changeInTemperature(7)
    temperatureSensor?.changeInTemperature(7)
    temperatureSensor?.changeInTemperature(7)
    temperatureSensor?.changeInTemperature(7)
    temperatureSensor?.changeInTemperature(7)
    temperatureSensor?.changeInTemperature(7)
    temperatureSensor?.changeInTemperature(-31)

    localUIInstance?.unsubFromTemperature()

    temperatureSensor?.changeInTemperature(300)
    temperatureSensor?.changeInTemperature(400)
    temperatureSensor?.changeInTemperature(800)
    temperatureSensor?.changeInTemperature(7786)

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
}

// Abstract TemperatureObserver (temperature observer-side interface)
abstract class TemperatureObserver {
    abstract fun updateTemperature()
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
            if (temperature != SENSOR_TEMPERATURE) {
                println("TemperatureSensor: Temp change from $SENSOR_TEMPERATURE°C to $temperature°C!")
                // Temperature paramater set, updateTemperature sensor temp and updateTemperature observers!
                SENSOR_TEMPERATURE = temperature
                notifyObservers()
            } else {
                println("TemperatureSensor: Temp the same as before, $SENSOR_TEMPERATURE!")
            }
        } else {
            // Temperature parameter not set, find new random temperature
            val randTemp = Random.nextInt(-40, 40)
            if (randTemp != SENSOR_TEMPERATURE) {
                println("TemperatureSensor: Temp change from $SENSOR_TEMPERATURE°C to $randTemp°C!")
                // Temperature different from before, updateTemperature sensor temp and updateTemperature observers!
                SENSOR_TEMPERATURE = randTemp
                notifyObservers()
            } else {
                println("TemperatureSensor: Temp the same as before, $SENSOR_TEMPERATURE!")
            }
        }
    }

}

// Concrete CloudStore observer (subscribing application needing updates)
class CloudStore : TemperatureObserver() {
    override fun updateTemperature() {
        println("CloudStore: Uploaded Temperature (${temperatureSensor?.SENSOR_TEMPERATURE}°C) to the cloud!")
    }

    fun subscribeToTemperature() {
        temperatureSensor?.registerObserver(this)
    }
    fun unsubFromTemperature() {
        temperatureSensor?.removeObserver(this)
    }
}

// Concrete LocalUI observer (subscribing application needing updates)
class LocalUI : TemperatureObserver() {
    override fun updateTemperature() {
        println("LocalUI: Displaying Temperature (${temperatureSensor?.SENSOR_TEMPERATURE}°C) locally!")
    }

    fun subscribeToTemperature() {
        temperatureSensor?.registerObserver(this)
    }

    fun unsubFromTemperature() {
        temperatureSensor?.removeObserver(this)
    }
}

