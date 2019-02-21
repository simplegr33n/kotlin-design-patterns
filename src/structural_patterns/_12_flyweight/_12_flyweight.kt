package structural_patterns._12_flyweight

import java.util.*

val TOTAL_ENEMIES: Int = 999
val RANGE_X: Int = 10000
val RANGE_Y: Int = 10000

var createEnemyCounter = 0
val enemyList = ArrayList<Enemy>()


fun main() {
    var enemyPopulator: EnemyPopulator

    // Flyweight Test (EXTRINSIC: x/y position | INTRINSIC: all other Enemy object variables)
    enemyPopulator = EnemyPopulator("Flyweight")
    enemyPopulator.initPopulator()
    enemyPopulator.populate()

    enemyList.clear()
    createEnemyCounter = 0

    // Non-Flyweight Test
    enemyPopulator = EnemyPopulator("Non-Flyweight")
    enemyPopulator.initPopulator()
    enemyPopulator.populate()

    enemyList.clear()
    createEnemyCounter = 0

    // Flyweight Test (EXTRINSIC: x/y position | INTRINSIC: all other Enemy object variables)
    enemyPopulator = EnemyPopulator("Flyweight")
    enemyPopulator.initPopulator()
    enemyPopulator.populate()

    enemyList.clear()
    createEnemyCounter = 0

}

// Enemy Populator
class EnemyPopulator(var factoryType: String) {
    lateinit var enemyFactory: EnemyFactory

    fun initPopulator() {
        if (factoryType == "Flyweight") {
            enemyFactory = FlyweightFactory
        } else {
            enemyFactory = NonFlyweightFactory
        }
    }

    fun populate() {

        val startTime = System.currentTimeMillis()
        for (i in 0..TOTAL_ENEMIES) {
            enemyFactory.itIsAGoodDayToDie()
        }
        val endTime = System.currentTimeMillis()

        if (enemyList.size >= 4) {
            println("""-----------------------
$factoryType Data:
-----------------------
        data[0]: ${enemyList[0]}
        data[1]: ${enemyList[1]}
        data[2]: ${enemyList[2]}
        data[3]: ${enemyList[3]}
        ...
        """)
        }

        println("""
            ========================================================================
            $factoryType took ${endTime - startTime} milliseconds to create ${enemyList.size} items
            ========================================================================
            """)

    }
}


abstract class EnemyFactory {
    abstract fun itIsAGoodDayToDie(): Enemy
}


// Enemy Non-Flyweight Factory
object NonFlyweightFactory : EnemyFactory() {

    override fun itIsAGoodDayToDie(): Enemy {

        val randomGenerator = Random()
        val randInt = randomGenerator.nextInt(3)

        // Create a new Enemy object for each for loop iteration
        when (randInt) {
            0 -> enemyList.add(FlyingEnemy(randX, randY).createEnemy())
            1 -> enemyList.add(MeleeEnemy(randX, randY).createEnemy())
            2 -> enemyList.add(RangedEnemy(randX, randY).createEnemy())
        }
        return enemyList.last()
    }
}

// Enemy Flyweight Factory
object FlyweightFactory : EnemyFactory() {

    // The HashMap uses an Int index as the key for every Enemy
    private val enemyByIndex = HashMap<Int, Enemy>()

    override fun itIsAGoodDayToDie(): Enemy {

        val randomGenerator = Random()
        val randInt = randomGenerator.nextInt(3)

        // Find appropriate by index in HashMap already built prototype object, else create one
        var protoEnemy: Enemy? = enemyByIndex[randInt]
        if (protoEnemy == null) {
            when (randInt) {
                0 -> protoEnemy = FlyingEnemy(0, 0).createEnemy()
                1 -> protoEnemy = MeleeEnemy(0, 0).createEnemy()
                2 -> protoEnemy = RangedEnemy(0, 0).createEnemy()
            }
            // Add new Enemy to the HashMap
            enemyByIndex[randInt] = protoEnemy as Enemy
        }

        val newEnemy: Enemy = protoEnemy.clone()
        newEnemy.xPos = randX
        newEnemy.yPos = randY

        enemyList.add(newEnemy)
        return newEnemy
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

    fun createEnemy(): Enemy {
        // Demonstration of an expensive init function for an object
        // which theoretically could be Intrinsic, so shouldn't be
        // called for every new object instance
        val demoList: ArrayList<Int> = ArrayList()
        for (i in 0..TOTAL_ENEMIES) {
            var demoValue: Int = 1
            for (x in 0..demoList.size - 1) {
                demoValue += demoList[x]
            }
            demoList.add(demoValue)
        }
        createEnemyCounter++
        println("  createEnemy() called $createEnemyCounter times [item no:${enemyList.size + 1}]")

        return this
    }

    override fun toString(): String {
        return "$name ($xPos x, $yPos y) |"
    }

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

    override fun clone(): Enemy {
        return RangedEnemy(randX, randY)
    }
}

// Picks random x & y coordinates
private val randX: Int
    get() = (Math.random() * RANGE_X).toInt()

private val randY: Int
    get() = (Math.random() * RANGE_Y).toInt()