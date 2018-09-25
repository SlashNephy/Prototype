package jp.nephy.prototype.view

import javafx.geometry.Pos
import tornadofx.*

class ErrorWindow: Fragment() {
    private val errorMessage: String by param()

    override val root = form {
        title = "ハンドルされていない例外が発生しました"

        borderpane {
            center {
                textarea(errorMessage) {
                    setPrefSize(600.0, 500.0)
                    isEditable = false
                    isWrapText = true
                    usePrefHeight = true
                }
            }
            bottom {
                hbox(10) {
                    alignment = Pos.BOTTOM_RIGHT

                    button("OK") {
                        action {
                            this@ErrorWindow.close()
                        }
                    }
                }
            }
        }
    }
}
