package jp.nephy.prototype.view.column.twitter

import jp.nephy.prototype.controller.twitter.HomeTimelineController
import jp.nephy.prototype.core.Config
import jp.nephy.prototype.view.column.ColumnView
import tornadofx.*

class HomeTimelineView: ColumnView() {
    val account by param<Config.Accounts.TwitterAccount>()
    private val controller by inject<HomeTimelineController>(params = mapOf("account" to account))

    override val columnName = "Home @${account.user.screenName}"

    override val root = vbox {
        label(columnName)
        separator()
        listview(controller.cells) {
            cellFormat {
                graphic = hbox {
                    vbox {
                        imageview(it.user.profileImageUrlHttps)
                    }

                    vbox {
                        hyperlink("${it.user.name} @${it.user.screenName}")
                        text(it.fullText())
                        text(it.source.name)
                    }
                }
            }
        }
    }

    init {
        controller.startUserStream()
    }
}
