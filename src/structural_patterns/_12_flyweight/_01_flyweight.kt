package structural_patterns._12_flyweight

import java.util.*

val TOTAL_ENEMIES: Int = 999999
val RANGE_X: Int = 10000
val RANGE_Y: Int = 10000

val nonFlyweightEnemyList = ArrayList<Enemy>()
val flyweightEnemyList = ArrayList<Enemy>()



fun main() {
    // NonFlyweight Test
    NonFlyweightFactory.itIsAGoodDayToDie()
    // Check first 8 items
    println(""""       ${nonFlyweightEnemyList[0]}
        ${nonFlyweightEnemyList[1]}
        ${nonFlyweightEnemyList[2]}
        ${nonFlyweightEnemyList[3]}
        """)
    // Clear list to free memory
    nonFlyweightEnemyList.clear()

    // Flyweight Test
    FlyweightFactory.itIsAGoodDayToDie()
    // Check first 8 items
    println(""""       ${flyweightEnemyList[0]}
        ${flyweightEnemyList[1]}
        ${flyweightEnemyList[2]}
        ${flyweightEnemyList[3]}
        """)
    // Clear list to free memory
    flyweightEnemyList.clear()

}

// Enemy Non-Flyweight Factory
object NonFlyweightFactory {
    fun itIsAGoodDayToDie() {

        val startTime = System.currentTimeMillis()
        for (i in 0..TOTAL_ENEMIES) {
            val randomGenerator = Random()
            val randInt = randomGenerator.nextInt(3)

            // Create a new Enemy object for each for loop iteration
            when (randInt) {
                0 -> nonFlyweightEnemyList.add(FlyingEnemy(randX, randY))
                1 -> nonFlyweightEnemyList.add(MeleeEnemy(randX, randY))
                2 -> nonFlyweightEnemyList.add(RangedEnemy(randX, randY))
            }
        }
        val endTime = System.currentTimeMillis()
        println("Non-Flyweight took " + (endTime - startTime) + " milliseconds")

    }
}

// Enemy Flyweight Factory
object FlyweightFactory {
    fun itIsAGoodDayToDie() {

        val startTime = System.currentTimeMillis()

        // The HashMap uses an Int index as the key for every Enemy
        val enemyByIndex = HashMap<Int, Enemy>()
        for (i in 0..TOTAL_ENEMIES) {

            val randomGenerator = Random()
            val randInt = randomGenerator.nextInt(3)

            // Check HashMap for existing Enemy object
            var protoEnemy: Enemy? = enemyByIndex[randInt]

            if (protoEnemy == null) {

                when (randInt) {
                    0 -> protoEnemy = FlyingEnemy(0, 0)
                    1 -> protoEnemy = MeleeEnemy(0, 0)
                    2 -> protoEnemy = RangedEnemy(0, 0)
                }

                // Add new Enemy to the HashMap
                enemyByIndex[randInt] = protoEnemy as Enemy
            }

            flyweightEnemyList.add(protoEnemy.clone())
        }
        val endTime = System.currentTimeMillis()
        println("Flyweight took " + (endTime - startTime) + " milliseconds")

    }
}

// Abstract Enemy Interface
abstract class Enemy : Cloneable {
    abstract var name: String
    abstract var hp: Int
    abstract var xPos: Int
    abstract var yPos: Int
    abstract var attackDamage: Int
    abstract var movementRange: Int
    abstract fun attack()
    public abstract override fun clone(): Enemy

}


// Concrete Enemies
class FlyingEnemy(xPos: Int?, yPos: Int?) : Enemy() {
    override var name = "Fighter Pigeon"
    override var hp = 45
    override var attackDamage = 7
    override var movementRange = 9
    override var xPos = xPos as Int
    override var yPos = yPos as Int

    override fun attack() {

    }
    override fun toString(): String {
        return "$name x: $xPos y: $yPos |"
    }

    override fun clone(): Enemy {
        return FlyingEnemy(randX, randY)
    }
}
class MeleeEnemy(xPos: Int?, yPos: Int?) : Enemy() {
    override var name = "Biter Turtle"
    override var hp = 112
    override var attackDamage = 28
    override var movementRange = 5
    override var xPos = xPos as Int
    override var yPos = yPos as Int

    override fun attack() {

    }

    override fun toString(): String {
        return "$name x: $xPos y: $yPos |"
    }

    override fun clone(): Enemy {
        return MeleeEnemy(randX, randY)
    }
}
class RangedEnemy(xPos: Int?, yPos: Int?) : Enemy() {
    override var name = "Spite Llama"
    override var hp = 68
    override var attackDamage = 14
    override var movementRange = 7
    override var xPos = xPos as Int
    override var yPos = yPos as Int

    override fun attack() {

    }

    override fun toString(): String {
        return "$name x: $xPos y: $yPos |"
    }

    override fun clone(): Enemy {
        return RangedEnemy(randX, randY)
    }
}

// Picks random x & y coordinates
private val randX: Int
    get() = (Math.random() * RANGE_X).toInt()

private val randY: Int
    get() = (Math.random() * RANGE_Y).toInt()