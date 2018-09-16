package jp.nephy.prototype

import jp.nephy.prototype.core.Config
import jp.nephy.prototype.view.MainView
import tornadofx.App

class PrototypeApp: App(MainView::class) {
    companion object {
        val config = Config.load()
    }
}
