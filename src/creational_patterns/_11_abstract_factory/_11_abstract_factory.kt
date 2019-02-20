package creational_patterns._11_abstract_factory


val ANDROID_FACTORY = AndroidPlatformFactory()
val MAC_FACTORY = MacPlatformFactory()
val PC_FACTORY = PCPlatformFactory()
val PLAYSTATION_FACTORY = PlaystationPlatformFactory()

class Game(var name: String, var size: Float)

enum class Platform {
    ANDROID, MAC, PC, PLAYSTATION
}

fun getFactoryPlatform(platform: Platform) : AbstractGameFactory {
    when (platform) {
        Platform.ANDROID -> return ANDROID_FACTORY
        Platform.MAC -> return MAC_FACTORY
        Platform.PC -> return PC_FACTORY
        Platform.PLAYSTATION -> return PLAYSTATION_FACTORY
    }
}

abstract class AbstractGameFactory {
    abstract fun compileGame(game: Game)
    abstract fun printCover(game: Game)
}

class AndroidPlatformFactory : AbstractGameFactory() {
    override fun compileGame(game: Game) {
        println("${game.name.replace("\\s".toRegex(), "")}.apk compiled for Android...")
        println("| APK FILE-SIZE:  ${game.size * 1.3}MB")
    }

    override fun printCover(game: Game) {
        println("""----------------
        |   ${game.name.toUpperCase()}
        |
        |     -    -
        |       0
        |
        | platform : ANDROID
        |----------------""".trimMargin())
    }
}

class MacPlatformFactory : AbstractGameFactory() {
    override fun compileGame(game: Game) {
        println("${game.name.replace("\\s".toRegex(), "")}.dmg compiled for MacOS...")
        println("| DMG FILE-SIZE:  ${game.size * 2.6}MB")
    }

    override fun printCover(game: Game) {
        println("""----------------
        |   ${game.name.toUpperCase()}
        |
        |     -    -
        |       0
        |
        | platform : MAC
        |----------------""".trimMargin())
    }
}

class PCPlatformFactory : AbstractGameFactory() {
    override fun compileGame(game: Game) {
        println("${game.name.replace("\\s".toRegex(), "")}.exe compiled for Windows...")
        println("| EXE FILE-SIZE:  ${game.size * 1.6}MB")
    }

    override fun printCover(game: Game) {
        println("""----------------
        |   ${game.name.toUpperCase()}
        |
        |     -    -
        |       0
        |
        | platform : PC
        |----------------""".trimMargin())
    }
}

class PlaystationPlatformFactory : AbstractGameFactory() {
    override fun compileGame(game: Game) {
        println("${game.name.replace("\\s".toRegex(), "")}.ps4 compiled for Playstation...")
        println("| PS4 FILE-SIZE:  ${game.size * 3}MB")
    }

    override fun printCover(game: Game) {
        println("""----------------
        |   ${game.name.toUpperCase()}
        |
        |     -    -
        |       0
        |
        | platform : PLAYSTATION
        |----------------""".trimMargin())
    }
}




fun main() {
    // Create Games
    val halflifeTwo = Game("Halflife 2", 1448f)
    val warcraftFour = Game("Warcraft IV", 2862f)

    // Create Android Game Factory
    var gameFactory: AbstractGameFactory = getFactoryPlatform(Platform.ANDROID)
    // call factory methods
    gameFactory.compileGame(halflifeTwo)
    gameFactory.printCover(halflifeTwo)

    // Change Game Factory to PC
    gameFactory = getFactoryPlatform(Platform.PC)
    // call factory methods
    gameFactory.compileGame(warcraftFour)
    gameFactory.printCover(warcraftFour)


}