package structural_patterns._08_decorator

// Supplies
// Protein inventory
var beef = 6
var chicken = 6
var ham = 6
var tofu = 6
var tuna = 6


// Cheese inventory
var cheddar = 4
var feta = 6
var parmesan = 6

// Veg inventory
var lettuce = 6
var tomato = 6
var onion = 6
var olives = 6
var peppers = 6

fun main() {

    var newBeefSandwich: Sandwich = BeefSandwich()
    newBeefSandwich = AddBeef(newBeefSandwich)
    newBeefSandwich = AddCheddar(newBeefSandwich)
    newBeefSandwich = AddCheddar(newBeefSandwich)
    newBeefSandwich = AddCheddar(newBeefSandwich)
    newBeefSandwich = AddCheddar(newBeefSandwich)
    newBeefSandwich = AddLettuce(newBeefSandwich)

    println(newBeefSandwich)

    var newTunaSandwich: Sandwich = TunaSandwich()
    newTunaSandwich = AddBeef(newTunaSandwich)
    newTunaSandwich = AddCheddar(newTunaSandwich)

    println(newTunaSandwich)

    var newChickenSandwich: Sandwich = ChickenSandwich()
    newChickenSandwich = AddBeef(newChickenSandwich)
    newChickenSandwich = AddCheddar(newChickenSandwich)
    newChickenSandwich = AddCheddar(newChickenSandwich)
    newChickenSandwich = AddCheddar(newChickenSandwich)
    newChickenSandwich = AddCheddar(newChickenSandwich)
    newChickenSandwich = AddCheddar(newChickenSandwich)
    newChickenSandwich = AddCheddar(newChickenSandwich)
    newChickenSandwich = AddCheddar(newChickenSandwich)
    newChickenSandwich = AddCheddar(newChickenSandwich)
    newChickenSandwich = AddCheddar(newChickenSandwich)
    newChickenSandwich = AddCheddar(newChickenSandwich)
    newChickenSandwich = AddCheddar(newChickenSandwich)
    newChickenSandwich = AddCheddar(newChickenSandwich)
    newChickenSandwich = AddBeef(newChickenSandwich)
    newChickenSandwich = AddLettuce(newChickenSandwich)
    newChickenSandwich = AddCheddar(newChickenSandwich)
    newChickenSandwich = AddCheddar(newChickenSandwich)

    println(newChickenSandwich)


}

// Sandwich (Abstract class)
abstract class Sandwich {
    abstract fun info(): String
    abstract fun cost(): Float

    override fun toString(): String {
        return """===SANDWICH===
        |     ${info()}
        |     $${"%.2f".format(cost())}
        |""".trimMargin()
    }
}

// Concrete Sandwich Classes
class BeefSandwich : Sandwich() {
    override fun info(): String {
        return "//BEEF SANDWICH//\n"
    }
    override fun cost(): Float {
        return 8.00f
    }
}
class ChickenSandwich : Sandwich() {
    override fun info(): String {
        return "//CHICKEN SANDWICH//\n"
    }
    override fun cost(): Float {
        return 6.75f
    }
}
class HamSandwich : Sandwich() {
    override fun info(): String {
        return "//HAM SANDWICH//\n"
    }
    override fun cost(): Float {
        return 7.50f
    }
}
class TofuSandwich : Sandwich() {
    override fun info(): String {
        return "//TOFU SANDWICH//\n"
    }
    override fun cost(): Float {
        return 7.00f
    }
}
class TunaSandwich : Sandwich() {
    override fun info(): String {
        return "//TUNA SANDWICH//\n"
    }
    override fun cost(): Float {
        return 12.00f
    }
}

// Abstract Topping Decorator
abstract class ToppingDecorator(var sandwich: Sandwich) : Sandwich() {
    abstract var itemPrice: Float
    var itemAdded: Boolean = false
}

// Concrete Topping Decorators (... only added 3 for demonstration Beef, Cheddar, and Lettuce)
class AddBeef(sandwich: Sandwich) : ToppingDecorator(sandwich) {
    override var itemPrice = 3f
    override fun info(): String {
        return if (beef >= 1) {
            beef -= 1
            itemAdded = true
            sandwich.info() + "+ BEEF\n+ $${"%.2f".format(itemPrice)}\n"
        } else {
            sandwich.info() + "OUT OF BEEF\n"
        }
    }
    override fun cost(): Float {
        return if (itemAdded) {
            sandwich.cost() + itemPrice
        } else {
            sandwich.cost()
        }
    }
}
class AddCheddar(sandwich: Sandwich) : ToppingDecorator(sandwich) {
    override var itemPrice = 2.25f
    override fun info(): String {
        return if (cheddar >= 1) {
            cheddar -= 1
            itemAdded = true
            sandwich.info() + "+ CHEDDAR\n+ $${"%.2f".format(itemPrice)}\n"
        } else {
            sandwich.info() + "OUT OF CHEDDAR\n"
        }
    }
    override fun cost(): Float {
        return if (itemAdded) {
            sandwich.cost() + itemPrice
        } else {
            sandwich.cost()
        }
    }
}
class AddLettuce(sandwich: Sandwich) : ToppingDecorator(sandwich) {
    override var itemPrice = 0.25f
    override fun info(): String {
        return if (lettuce >= 1) {
            lettuce -= 1
            itemAdded = true
            sandwich.info() + "+ LETTUCE\n+ $${"%.2f".format(itemPrice)}\n"
        } else {
            sandwich.info() + "OUT OF LETTUCE\n"
        }
    }
    override fun cost(): Float {
        return if (itemAdded) {
            sandwich.cost() + itemPrice
        } else {
            sandwich.cost()
        }
    }
}


