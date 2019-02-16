package structural_patterns._03_adapter


fun main() {
    val doorway = Doorway(3,6)
    var mattressAdapter: MattressAdapter
    for (i in 3..7) {
        println("Creating Mattress (" + i + ", " + i * 1.5 + ", 1.0 )")
        mattressAdapter = MattressAdapter(i.toDouble(),i.toDouble() * 1.5, 1.0)
        // The client uses (is coupled to) the new interface
        mattressAdapter.makeFit(doorway)
    }
}

// Wrapper class to can "impedance match" the adapted class to the receiving class
internal class MattressAdapter(width: Double, length: Double, depth: Double) {
    // The adapter/wrapper class holds an instance of the legacy class
    private val mattress: Mattress = Mattress(width, length, depth)

    // Identify the desired interface
    fun makeFit(doorway: Doorway) {
        // The adapter/wrapper class delegates to the legacy object
        while (mattress.width > doorway.height) {
            foldMattressWidthwise(mattress)
        }

        while (mattress.length > doorway.height) {
            foldMattressLengthwise(mattress)
        }

        if (mattress.width > doorway.height || mattress.length > doorway.height || mattress.depth > doorway.width) {
            println("Mattress (" + mattress.width + ", " + mattress.length + ", " + mattress.depth + ") cannot be adapted to the doorway!")
        } else {
            println("Mattress (" + mattress.width + ", " + mattress.length + ", " + mattress.depth + ") successfully fit doorway!")
        }
    }

    private fun foldMattressWidthwise (mattress: Mattress) : Mattress {
        println("Folding Mattress (" + mattress.width + ", " + mattress.length + ", " + mattress.depth + ") width-wise")
        mattress.width /= 2
        mattress.depth *= 2
        println("Result Mattress (" + mattress.width + ", " + mattress.length + ", " + mattress.depth + ")")
        return mattress
    }

    private fun foldMattressLengthwise (mattress: Mattress) : Mattress {
        println("Folding Mattress (" + mattress.width + ", " + mattress.length + ", " + mattress.depth + ") length-wise")
        mattress.length /= 2
        mattress.depth *= 2
        println("Result Mattress (" + mattress.width + ", " + mattress.length + ", " + mattress.depth + ")")
        return mattress
    }

}

/* Adapted class */
internal class Mattress(var width: Double, var length: Double, var depth: Double)

/* Receiving class */
internal class Doorway(val width: Int, val height: Int) {
    init {
        println("Doorway(width: $width, height: $height)")
    }
}


