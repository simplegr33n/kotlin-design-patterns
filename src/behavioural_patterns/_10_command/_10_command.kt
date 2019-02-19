package behavioural_patterns._10_command

import java.util.ArrayList


var counter: Int = 0

interface Command {
    fun execute()
    fun undo()
    fun redo()
}

class AddOne : Command {
    override fun execute() {
        counter++
        println("Add One! counter = $counter")
    }

    override fun undo() {
        counter--
        println("Undo Add One! counter = $counter")
    }

    override fun redo() {
        counter++
        println("Redo Add One! counter = $counter")
    }

}

class AddTwo : Command {
    override fun execute() {
        counter += 2
        println("Add Two! counter = $counter")
    }

    override fun undo() {
        counter -= 2
        println("Undo Add Two! counter = $counter")
    }

    override fun redo() {
        counter += 2
        println("Redo Add Two! counter = $counter")
    }
}

class AddThree : Command {
    override fun execute() {
        counter += 3
        println("Add Three! counter = $counter")
    }

    override fun undo() {
        counter -= 3
        println("Undo Add Three! counter = $counter")
    }

    override fun redo() {
        counter += 3
        println("Redo Add Three! counter = $counter")
    }
}

object Invoker {
    private var commandHistory = ArrayList<Command>()
    private var undoHistory = ArrayList<Command>()

    fun workOffRequests(queue: List<Command>) {
        undoHistory.clear()

        for (command in queue) {
            commandHistory.add(command)
            command.execute()
        }
    }

    fun undoRequest() {
        if (commandHistory.size > 0) {
            commandHistory.last().undo()
            undoHistory.add(commandHistory.last())
            commandHistory.remove(commandHistory.last())
        } else {
            println("Nothing to undo")
        }
    }

    fun redoRequest() {
        if (undoHistory.size > 0) {
            undoHistory.last().redo()
            commandHistory.add(undoHistory.last())
            undoHistory.remove(undoHistory.last())
        } else {
            println("Nothing to redo")
        }
    }
}

fun main() {
    println("Counter = $counter")

    val queue = ArrayList<Command>()
    queue.add(AddThree())
    queue.add(AddOne())
    queue.add(AddTwo())

    Invoker.workOffRequests(queue)

    Invoker.undoRequest()
    Invoker.undoRequest()
    Invoker.undoRequest()
    Invoker.undoRequest()
    Invoker.undoRequest()

    Invoker.redoRequest()

    Invoker.undoRequest()
    Invoker.undoRequest()

    Invoker.redoRequest()
    Invoker.redoRequest()
    Invoker.redoRequest()
    Invoker.redoRequest()

}