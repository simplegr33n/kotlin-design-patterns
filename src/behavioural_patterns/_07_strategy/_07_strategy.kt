package behavioural_patterns._07_strategy


fun main() {
    val clayPerson01 = ClayPerson("Clay Johnson","Square","Fat","Super","Normal","Long","Short")

    val clayPerson02 = ClayPerson("Frienderman","Blue","Slim","Super","Super","Long","Long")

    println(clayPerson01)
    println(clayPerson02)

}

class ClayPerson(var name: String, head: String, torso: String, leftArm: String,
                 rightArm: String, leftLeg: String, rightLeg: String) {
    var body: FullBody = FullBody(listOf(head,torso,leftArm,rightArm,leftLeg,rightLeg)).buildBody()

    override fun toString(): String {
        return """ClayPerson: ${name}
        |HEAD (eyes: ${body.head?.eyes} | ears: ${body.head?.ears} | mouth: ${body.head?.mouth} )
        |TORSO (shape: ${body.torso?.shape} | isHairy: ${body.torso?.isHairy} )
        |LEFT-ARM (hand: ${body.leftArm?.hand} | pitching speed: ${body.leftArm?.pitchingSpeed} | side: ${body.leftArm?.side} )
        |RIGHT-ARM (hand: ${body.rightArm?.hand} | pitching speed: ${body.rightArm?.pitchingSpeed} | side: ${body.rightArm?.side} )
        |LEFT-LEG (foot: ${body.leftLeg?.foot} | side: ${body.leftLeg?.side} )
        |RIGHT-LEG (foot: ${body.rightLeg?.foot} | side: ${body.rightLeg?.side} )
        |""".trimMargin()
    }
}

//TODO: fix
// Interface...Class...? to implement concrete body objects for ClayPerson
class FullBody(var partsList: List<String>) {
    var head: IHead? = null
    var torso: ITorso? = null
    var leftArm: IArm? = null
    var rightArm: IArm? = null
    var leftLeg: Leg? = null
    var rightLeg: Leg? = null

    fun buildBody(): FullBody {
        when (partsList[0]) {
            "Round" -> head = RoundHead()
            "Square" -> head = SquareHead()
            "Blue" -> head = BlueHead()
            else -> head = RoundHead()
        }
        when (partsList[1]) {
            "Fat" -> torso = FatTorso()
            "Slim" -> torso = SlimTorso()
            "Medium" -> torso = MediumTorso()
            else -> torso = MediumTorso()
        }
        when (partsList[2]) {
            "Super" -> leftArm = SuperArm("Left")
            "Normal" -> leftArm = NormalArm("Left")
            else -> leftArm = NormalArm("Left")
        }
        when (partsList[3]) {
            "Super" -> rightArm = SuperArm("Right")
            "Normal" -> rightArm = NormalArm("Right")
            else -> rightArm = NormalArm("Right")
        }
        when (partsList[4]) {
            "Long" -> leftLeg = LongLeg("Left")
            "Short" -> leftLeg = ShortLeg("Left")
            else -> leftLeg = ShortLeg("Left")
        }
        when (partsList[5]) {
            "Long" -> rightLeg = LongLeg("Right")
            "Short" -> rightLeg = ShortLeg("Right")
            else -> rightLeg = ShortLeg("Right")
        }

        return this
    }
}

// Interfaces for body parts
interface IHead {
    var eyes: String
    var mouth: String
    var ears: String
}
interface ITorso {
    var shape: String
    var isHairy: Boolean
}
interface IArm {
    var side: String
    var hand: String
    var pitchingSpeed: Int
}
interface Leg {
    var side: String
    var foot: String
}


// Concrete IHead Classes
class RoundHead() : IHead {
    override var eyes ="Round"
    override var mouth = "0val"
    override var ears = "Circular"
}
class SquareHead() : IHead {
    override var eyes ="Cubic"
    override var mouth = "Blocky"
    override var ears = "Squared"
}
class BlueHead() : IHead {
    override var eyes ="Blue"
    override var mouth = "Azul"
    override var ears = "Bleues"
}

// Concrete ITorso Classes
class FatTorso() : ITorso {
    override var shape = "Medium"
    override var isHairy = true
}
class SlimTorso() : ITorso {
    override var shape = "Medium"
    override var isHairy = false
}
class MediumTorso() : ITorso {
    override var shape = "Medium"
    override var isHairy = true
}

// Concrete Leg Classes
class SuperArm(side: String) : IArm {
    override var side = side
    override var hand = "SuperGrip"
    override var pitchingSpeed = 300
}
class NormalArm(side: String) : IArm {
    override var side = side
    override var hand = "Usable"
    override var pitchingSpeed = 3
}

// Concrete Leg Classes
class LongLeg(side: String) : Leg {
    override var side = side
    override var foot = "Large"
}
class ShortLeg(side: String) : Leg {
    override var side = side
    override var foot = "Small"
}
