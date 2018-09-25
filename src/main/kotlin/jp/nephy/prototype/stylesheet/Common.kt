package jp.nephy.prototype.stylesheet

import javafx.scene.text.FontSmoothingType
import tornadofx.*

class Common: Stylesheet() {
    init {
        //val emojiFont = loadFont("/TwitterColorEmoji.ttf", 32)

        root {
            //font = emojiFont!!
            fontSmoothingType = FontSmoothingType.GRAY
        }

        //importStylesheet("/bootstrapfx.css")
    }
}
