package jp.nephy.prototype.view

import jp.nephy.prototype.PrototypeApp
import jp.nephy.prototype.view.column.twitter.HomeTimelineView
import tornadofx.*

class MainView: View() {
    override val root = hbox {
        this += find<HomeTimelineView>(Scope(), params = mapOf(HomeTimelineView::account to PrototypeApp.config.accounts?.twitter?.firstOrNull()))
//        PrototypeApp.config.accounts?.twitter.orEmpty().forEach {
//            this += find<HomeTimelineView>(Scope(), params = mapOf(HomeTimelineView::account to it))
//        }
    }
}
