package jp.nephy.prototype.controller

import javafx.collections.ObservableList
import jp.nephy.jsonkt.JsonModel
import tornadofx.*

abstract class TimelineController<M: JsonModel>: Controller() {
    abstract val cells: ObservableList<M>
}
