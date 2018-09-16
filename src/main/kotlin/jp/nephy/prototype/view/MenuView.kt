package jp.nephy.prototype.view

import tornadofx.*

class MenuView: Fragment() {
    override val root = menubar {
        menu("File") {
            item("Quit")
        }

        menu("About") {
            item("Version")
        }
    }
}
