package _02_factory


fun main() {
    val galaxy = SmartPhoneFactory.buildSmartPhone("SamsungGalaxy", 1, "120 MP", true)
    val iphone = SmartPhoneFactory.buildSmartPhone("AppleIPhone", 2, "160 MP", false)
    println("Factory Galaxy Specs::$galaxy")
    println("Factory IPhone Specs::$iphone")
}


// Factory Object to build
object SmartPhoneFactory {

    fun buildSmartPhone(type: String, ram: Int, megapixels: String, wifi: Boolean): SmartPhone? {
        when (type){
            "SamsungGalaxy" -> return SamsungGalaxy(ram, megapixels, wifi)
            "AppleIPhone" -> return AppleIPhone(ram, megapixels, wifi)
        }

        return null
    }
}

// Super-Class Definition
abstract class SmartPhone () {

    abstract val ram: Int
    abstract val megapixels: String
    abstract val wifi: Boolean

    override fun toString(): String {
        return "RAM= " + this.ram + ", HDD= " + this.megapixels + ", WIFI= " + this.wifi.toString()
    }
}

// Sub-Class Definitions
class SamsungGalaxy(override val ram: Int, override val megapixels: String, override val wifi: Boolean) : SmartPhone() {
    init {
        println("Built a Galaxy!")
    }
}

class AppleIPhone(override val ram: Int, override val megapixels: String, override val wifi: Boolean) : SmartPhone() {
    init {
        println("Built an IPhone!")
    }
}