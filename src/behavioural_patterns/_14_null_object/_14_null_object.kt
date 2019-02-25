package behavioural_patterns._14_null_object

// Abstract Product Class
abstract class Product {
    var name: String? = null
    var price: Double? = null
    abstract val isNil: Boolean
    abstract fun getCandyInfo(): String?
}

// Concrete "Real" Product
class RealProduct(name: String, price: Double) : Product() {

    override val isNil: Boolean = false

    init {
        this.name = name
        this.price = price
    }

    override fun getCandyInfo(): String? {
        return "$name - $$price"
    }
}

// Concrete "Null" Product
class NullProduct : Product() {

    override val isNil: Boolean = true

    override fun getCandyInfo(): String {
        return "Not Available in Candy Database"
    }
}

object CandyFactory {
    private val names = arrayOf("Starburst", "Skittles", "Smarties")
    private val prices = arrayOf(3.99, 2.99, 1.99)

    fun getCandy(name: String): Product {

        for (i in names.indices) {
            if (names[i].equals(name, ignoreCase = true)) {
                return RealProduct(name, prices[i])
            }
        }
        return NullProduct()
    }
}


fun main() {

    val candy1 = CandyFactory.getCandy("Starburst")
    val candy2 = CandyFactory.getCandy("Sour Keys")
    val candy3 = CandyFactory.getCandy("Skittles")
    val candy4 = CandyFactory.getCandy("M & Ms")

    println("Candies\n========")
    println(candy1.getCandyInfo())
    println(candy2.getCandyInfo())
    println(candy3.getCandyInfo())
    println(candy4.getCandyInfo())
}
