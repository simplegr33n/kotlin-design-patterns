package structural_patterns._06_facade


fun main() {
    // Initialize UI objects
    val uiBackground = Background()
    val uiText = Text()
    val uiMenuBar = MenuBar()

    // Composite UI Object
    val uiComposite = CompositeUI(uiMenuBar, uiBackground, uiText)

    // Facade Interface for setting UI Theme
    val themeFacade = ThemeFacade(uiComposite)

    // Set theme "Standard"
    themeFacade.setTheme("Standard")
    println(uiComposite)

    // Set theme "Pink"
    themeFacade.setTheme("Pink")
    println(uiComposite)

    // Set theme "Dark"
    themeFacade.setTheme("Dark")
    println(uiComposite)

    // Set theme with non-theme name parameter
    themeFacade.setTheme("hmm")
    println(uiComposite)

}

// ThemeFacade
class ThemeFacade(private var uiComposite: CompositeUI) {

    fun setTheme(theme: String?) {
        println("ThemeFacade: setTheme($theme)")

        when (theme) {
            "Standard" -> {
                uiComposite.menuBar.backgroundColor = "BLUE"
                uiComposite.menuBar.textColor = "LT_GREY"
                uiComposite.background.backgroundColor = "WHITE"
                uiComposite.background.borderColor = "LT_GREY"
                uiComposite.text.textColor = "BLACK"
                uiComposite.text.shadowColor = "DK_GREY"
            }
            "Pink" -> {
                uiComposite.menuBar.backgroundColor = "PINK"
                uiComposite.menuBar.textColor = "PINK"
                uiComposite.background.backgroundColor = "PINK"
                uiComposite.background.borderColor = "PINK"
                uiComposite.text.textColor = "PINK"
                uiComposite.text.shadowColor = "PINK"
            }
            "Dark" -> {
                uiComposite.menuBar.backgroundColor = "DK_BLUE"
                uiComposite.menuBar.textColor = "WHITE"
                uiComposite.background.backgroundColor = "BLACK"
                uiComposite.background.borderColor = "DK_GREY"
                uiComposite.text.textColor = "WHITE"
                uiComposite.text.shadowColor = "LT_GREY"
            }
            else -> {
                setTheme("Standard")
            }
        }
    }

}

// Composite UI Object
class CompositeUI (var menuBar: MenuBar, var background: Background, var text: Text) {

    override fun toString(): String {
        return """Composite UI Object:
        |MENUBAR
        |   menuBar background→ ${menuBar.backgroundColor}
        |   menuBar text → ${menuBar.textColor}
        |BACKGROUND
        |   main background → ${background.backgroundColor}
        |   main border → ${background.borderColor}
        |TEXT
        |   text → ${text.textColor}
        |   shadow → ${text.shadowColor}
        |""".trimMargin()
    }
}

// Background UI Object
class Background {
    var backgroundColor: String? = null
    var borderColor: String? = null
}

// Text UI Object
class Text {
    var textColor: String? = null
    var shadowColor: String? = null
}

// MenuBar UI Object
class MenuBar {
    var backgroundColor: String? = null
    var textColor: String? = null
}