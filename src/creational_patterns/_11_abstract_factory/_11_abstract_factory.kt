package creational_patterns._11_abstract_factory

class Game(var name: String, var size: Float)

abstract class GameFactory {
    abstract fun compileGame(game: Game)
    abstract fun printCover(game: Game)
}

class AndroidPlatformFactory : GameFactory() {
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

class MacPlatformFactory : GameFactory() {
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

class PCPlatformFactory : GameFactory() {
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

class PlaystationPlatformFactory : GameFactory() {
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

    // Create Game Compilers
    val androidPlatformFactory: GameFactory = AndroidPlatformFactory()
    val macPlatformFactory: GameFactory = MacPlatformFactory()
    val pcPlatformFactory: GameFactory = PCPlatformFactory()
    val playstationFactory: GameFactory = PlaystationPlatformFactory()

    playstationFactory.compileGame(halflifeTwo)
    playstationFactory.compileGame(warcraftFour)

    androidPlatformFactory.compileGame(halflifeTwo)

    macPlatformFactory.compileGame(warcraftFour)

    pcPlatformFactory.compileGame(warcraftFour)

    pcPlatformFactory.printCover(warcraftFour)

    macPlatformFactory.printCover(halflifeTwo)
}