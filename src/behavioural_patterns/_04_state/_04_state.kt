package behavioural_patterns._04_state

fun main() {
    val sleepSubject = SleepSubject()
    println(sleepSubject)

    sleepSubject.progressSleepStage()
    println(sleepSubject)

    sleepSubject.progressSleepStage()
    println(sleepSubject)

    sleepSubject.progressSleepStage()
    println(sleepSubject)

    sleepSubject.progressSleepStage()
    println(sleepSubject)

    sleepSubject.progressSleepStage()
    println(sleepSubject)

    sleepSubject.progressSleepStage()
    println(sleepSubject)

    sleepSubject.progressSleepStage()
    println(sleepSubject)

    sleepSubject.wakeUp()
    println(sleepSubject)

    sleepSubject.progressSleepStage()
    println(sleepSubject)

    sleepSubject.wakeUp()
    println(sleepSubject)

    sleepSubject.wakeUp()
    println(sleepSubject)
}

// Class whose state we want to manage
class SleepSubject {
    var state: SleepState

    var eyesOpen: Boolean? = null
    var inBed: Boolean? = null
    var isDreaming: Boolean? = null

    val stateAwake = Awake(this)
    val stateStageOne = StageOne(this)
    val stateStageTwo = StageTwo(this)
    val stateStageThree = StageThree(this)
    val stateRem = REMStage(this)

    init {
        println("\"Wake Up!\"")
        eyesOpen = true
        inBed = false
        isDreaming = false
        state = stateAwake
    }

    fun progressSleepStage() = state.progressSleepStage()
    fun wakeUp() = state.wakeUp()

    override fun toString(): String {
        return """SLEEP SUBJECT → ${state::class.simpleName}
        |   eyes open : $eyesOpen
        |   in bed : $inBed
        |   isDreaming : $isDreaming
        |""".trimMargin()
    }
}

// Abstract State Interface for SleepState management
abstract class SleepState(val sleepSubject: SleepSubject) {
    open fun progressSleepStage(): Unit = throw UnsupportedOperationException("Operation not supported")
    open fun wakeUp(): Unit = throw UnsupportedOperationException("Operation not supported")
}

// Concrete Implementations of SleepState (Awake|StageOne|StageTwo|StageThree|REMStage)
class Awake(sleepSubject: SleepSubject) : SleepState(sleepSubject) {

    override fun progressSleepStage() {
        println("Beginning Sleep Stage One")
        sleepSubject.eyesOpen = false
        sleepSubject.inBed = true
        sleepSubject.isDreaming = false
        sleepSubject.state = sleepSubject.stateStageOne
    }

    override fun wakeUp() {
        println("Already awake!")
    }
}

class StageOne(sleepSubject: SleepSubject) : SleepState(sleepSubject) {

    override fun progressSleepStage() {
        println("Advanced to Sleep Stage Two")
        sleepSubject.eyesOpen = false
        sleepSubject.inBed = true
        sleepSubject.isDreaming = false
        sleepSubject.state = sleepSubject.stateStageTwo
    }

    override fun wakeUp() {
        println("Stage One Interrupted! Woken Up!")
        sleepSubject.eyesOpen = true
        sleepSubject.inBed = false
        sleepSubject.isDreaming = false
        sleepSubject.state = sleepSubject.stateAwake
    }
}

class StageTwo(sleepSubject: SleepSubject) : SleepState(sleepSubject) {

    override fun progressSleepStage() {
        println("Advanced to Sleep Stage Three")
        sleepSubject.eyesOpen = false
        sleepSubject.inBed = true
        sleepSubject.isDreaming = false
        sleepSubject.state = sleepSubject.stateStageThree
    }

    override fun wakeUp() {
        println("Stage Two Interrupted! Woken Up!")
        sleepSubject.eyesOpen = true
        sleepSubject.inBed = false
        sleepSubject.isDreaming = false
        sleepSubject.state = sleepSubject.stateAwake
    }
}

class StageThree(sleepSubject: SleepSubject) : SleepState(sleepSubject) {

    override fun progressSleepStage() {
        println("Advanced to REM Stage")
        sleepSubject.eyesOpen = false
        sleepSubject.inBed = true
        sleepSubject.isDreaming = true
        sleepSubject.state = sleepSubject.stateRem
    }

    override fun wakeUp() {
        println("Stage Three Interrupted! Woken Up!")
        sleepSubject.eyesOpen = true
        sleepSubject.inBed = false
        sleepSubject.isDreaming = false
        sleepSubject.state = sleepSubject.stateAwake
    }
}

class REMStage(sleepSubject: SleepSubject) : SleepState(sleepSubject) {

    override fun progressSleepStage() {
        println("Return to Awake Stage!")
        sleepSubject.eyesOpen = true
        sleepSubject.inBed = false
        sleepSubject.isDreaming = false
        sleepSubject.state = sleepSubject.stateAwake
    }

    override fun wakeUp() {
        println("REM Interrupted! Woken Up!")
        sleepSubject.eyesOpen = true
        sleepSubject.inBed = false
        sleepSubject.isDreaming = false
        sleepSubject.state = sleepSubject.stateAwake
    }
}


