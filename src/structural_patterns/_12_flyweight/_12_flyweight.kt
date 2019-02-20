package structural_patterns._12_flyweight

import java.util.*

val TOTAL_ENEMIES: Int = 999
val RANGE_X: Int = 10000
val RANGE_Y: Int = 10000

val nonFlyweightEnemyList = ArrayList<Enemy>()
val flyweightEnemyList = ArrayList<Enemy>()


fun main() {
    var enemyPopulator: EnemyPopulator

    // FW Test
    enemyPopulator = EnemyPopulator("Flyweight")
    enemyPopulator.initPopulator()
    enemyPopulator.populate()

    // NFW Test
    enemyPopulator = EnemyPopulator("Non-Flyweight")
    enemyPopulator.initPopulator()
    enemyPopulator.populate()

}

// Enemy Populator
class EnemyPopulator(var factoryType: String) {
    var enemyFactory: EnemyFactory? = null
    var enemyList: ArrayList<Enemy> = ArrayList()

    fun initPopulator() {
        if (factoryType == "Flyweight") {
            enemyList = flyweightEnemyList
            enemyFactory = FlyweightFactory
        } else {
            enemyList = nonFlyweightEnemyList
            enemyFactory = NonFlyweightFactory
        }
    }


    fun populate() {

        val startTime = System.currentTimeMillis()
        for (i in 0..TOTAL_ENEMIES) {
            enemyFactory?.itIsAGoodDayToDie()
        }
        val endTime = System.currentTimeMillis()

        if (enemyList.size > 0) {
            println(""""
        ${enemyList[0]}
        ${enemyList[1]}
        ${enemyList[2]}
        ${enemyList[3]}
        """)
        }

        println("$factoryType took " + (endTime - startTime) + " milliseconds")
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
            0 -> nonFlyweightEnemyList.add(FlyingEnemy(randX, randY).initEnemy())
            1 -> nonFlyweightEnemyList.add(MeleeEnemy(randX, randY).initEnemy())
            2 -> nonFlyweightEnemyList.add(RangedEnemy(randX, randY).initEnemy())
        }
        return nonFlyweightEnemyList.last()
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
                0 -> protoEnemy = FlyingEnemy(0, 0).initEnemy()
                1 -> protoEnemy = MeleeEnemy(0, 0).initEnemy()
                2 -> protoEnemy = RangedEnemy(0, 0).initEnemy()
            }
            // Add new Enemy to the HashMap
            enemyByIndex[randInt] = protoEnemy as Enemy
        }

        val newEnemy: Enemy = protoEnemy.clone()
        newEnemy.xPos = randX
        newEnemy.yPos = randY

        flyweightEnemyList.add(newEnemy)
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

    fun initEnemy(): Enemy {
        // demonstration expensive init function for object
        // is Intrinsic, so shouldn't be called for every  new instance
        val demoList: ArrayList<Int> = ArrayList()
        for (i in 0..TOTAL_ENEMIES) {
            var demoValue: Int = 1
            for (x in 0..demoList.size - 1) {
                demoValue += demoList[x]
            }
            demoList.add(demoValue)
            //println(demoValue)
        }

        return this
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