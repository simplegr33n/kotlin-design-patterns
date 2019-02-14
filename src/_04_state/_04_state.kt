package _04_state

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


class SleepSubject {
    var state: SleepState

    val awake = Awake(this)
    val stageOne = StageOne(this)
    val stageTwo = StageTwo(this)
    val stageThree = StageThree(this)
    val remStage = REMStage(this)

    init {
        println("\"Wake Up!\"")
        state = awake
    }

    fun progressSleepStage() = state.progressSleepStage()
    fun wakeUp() = state.wakeUp()

    override fun toString(): String {
        return """SLEEP SUBJECT → ${state::class.simpleName}
        |   eyes open : ${state.eyesOpen}
        |   in bed : ${state.inBed}
        |   isDreaming : ${state.isDreaming}
        |""".trimMargin()
    }
}

// Abstract State Interface for SleepState management
abstract class SleepState(val sleepSubject: SleepSubject) {
    open var eyesOpen: Boolean? = null
    open var inBed: Boolean? = null
    open var isDreaming: Boolean? = null
    open fun progressSleepStage(): Unit = throw UnsupportedOperationException("Operation not supported")
    open fun wakeUp(): Unit = throw UnsupportedOperationException("Operation not supported")
}

// Concrete Implementations of SleepState (Awake|StageOne|StageTwo|StageThree|REMStage)
class Awake(sleepSubject: SleepSubject) : SleepState(sleepSubject) {
    init {
        println("Init Awake Stage")
        eyesOpen = true
        inBed = false
        isDreaming = false
    }

    override fun progressSleepStage() {
        println("Beginning Sleep Stage One")
        eyesOpen = false
        inBed = true
        isDreaming = false
        sleepSubject.state = sleepSubject.stageOne
    }

    override fun wakeUp() {
        println("Already awake!")
    }
}

class StageOne(sleepSubject: SleepSubject) : SleepState(sleepSubject) {
    init {
        println("Init Stage One")
        eyesOpen = false
        inBed = true
        isDreaming = false
    }

    override fun progressSleepStage() {
        println("Advanced to Sleep Stage Two")
        eyesOpen = false
        inBed = true
        isDreaming = false
        sleepSubject.state = sleepSubject.stageTwo
    }

    override fun wakeUp() {
        println("Stage One Interrupted! Woken Up!")
        eyesOpen = true
        inBed = false
        isDreaming = false
        sleepSubject.state = sleepSubject.awake
    }
}

class StageTwo(sleepSubject: SleepSubject) : SleepState(sleepSubject) {
    init {
        println("Init Stage Two")
        eyesOpen = false
        inBed = true
        isDreaming = false
    }

    override fun progressSleepStage() {
        println("Advanced to Sleep Stage Three")
        eyesOpen = false
        inBed = true
        isDreaming = false
        sleepSubject.state = sleepSubject.stageThree
    }

    override fun wakeUp() {
        println("Stage Two Interrupted! Woken Up!")
        eyesOpen = true
        inBed = false
        isDreaming = false
        sleepSubject.state = sleepSubject.awake
    }
}

class StageThree(sleepSubject: SleepSubject) : SleepState(sleepSubject) {
    init {
        println("Init Stage Three")
        eyesOpen = false
        inBed = true
        isDreaming = false
    }

    override fun progressSleepStage() {
        println("Advanced to REM Stage")
        eyesOpen = false
        inBed = true
        isDreaming = true
        sleepSubject.state = sleepSubject.remStage
    }

    override fun wakeUp() {
        println("Stage Three Interrupted! Woken Up!")
        eyesOpen = true
        inBed = false
        isDreaming = false
        sleepSubject.state = sleepSubject.awake
    }
}

class REMStage(sleepSubject: SleepSubject) : SleepState(sleepSubject) {
    init {
        println("Init REM Stage")
        eyesOpen = false
        inBed = true
        isDreaming = true
    }

    override fun progressSleepStage() {
        println("Return to Awake Stage!")
        eyesOpen = true
        inBed = false
        isDreaming = false
        sleepSubject.state = sleepSubject.awake
    }

    override fun wakeUp() {
        println("REM Interrupted! Woken Up!")
        eyesOpen = true
        inBed = false
        isDreaming = false
        sleepSubject.state = sleepSubject.awake
    }
}


