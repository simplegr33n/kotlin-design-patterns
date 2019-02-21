package creational_patterns._13_prototype

import java.util.HashMap


internal interface TerranUnit : Cloneable {
    public override fun clone(): TerranUnit
}

internal class JimRaynor : TerranUnit {
    private val NAME = "Jim Raynor"
    private val QUOTE = "This is Jimmy"
    private val UNIT_TYPE = "Marine"

    override fun clone(): TerranUnit {
        return JimRaynor()
    }

    override fun toString(): String {
        return "$NAME - $UNIT_TYPE - \"$QUOTE\""
    }
}

internal class EdmundDuke : TerranUnit {
    private val NAME = "Edmund Duke"
    private val QUOTE = "I haven't got all day..."
    private val UNIT_TYPE = "Seige Tank"

    override fun clone(): TerranUnit {
        return EdmundDuke()
    }

    override fun toString(): String {
        return "$NAME - $UNIT_TYPE - \"$QUOTE\""
    }
}

internal class SarahKerrigan : TerranUnit {
    private val NAME = "Sarah Kerrigan"
    private val QUOTE = "I'm waitin' on you!"
    private val UNIT_TYPE = "Ghost"

    override fun clone(): TerranUnit {
        return SarahKerrigan()
    }

    override fun toString(): String {
        return "$NAME - $UNIT_TYPE - \"$QUOTE\""
    }
}

internal object CloneFactory {
    private val prototypes = HashMap<String, TerranUnit>()

    // Could store in HashMap on first call for object, as in Flyweight example,
    // or, as here, give factory a copy of all objects on init {}
    init {
        prototypes["Jim Raynor"] = JimRaynor()
        prototypes["Edmund Duke"] = EdmundDuke()
        prototypes["Sarah Kerrigan"] = SarahKerrigan()
    }


    fun getCloneFromPrototype(type: String): TerranUnit? {

        try {
            println("Returning clone of: $type")
            return prototypes[type]?.clone()
        } catch (ex: NullPointerException) {
            println("Prototype with name: $type, doesn't exist")
            return null
        }
    }
}


fun main() {
    val unitList: ArrayList<TerranUnit?> = ArrayList()

    unitList.add(CloneFactory.getCloneFromPrototype("Jim Raynor"))
    unitList.add(CloneFactory.getCloneFromPrototype("Edmund Duke"))
    unitList.add(CloneFactory.getCloneFromPrototype("Sarah Kerrigan"))

    println("====PROTOTYPE CLONING COMPLETE====")

    for (unit in unitList) {
        println(unit)
    }
}
