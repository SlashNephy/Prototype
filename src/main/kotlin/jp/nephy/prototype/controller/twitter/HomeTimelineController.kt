package jp.nephy.prototype.controller.twitter

import com.google.gson.JsonObject
import jp.nephy.penicillin.core.streaming.UserStreamListener
import jp.nephy.penicillin.models.Status
import jp.nephy.prototype.controller.TimelineController
import jp.nephy.prototype.core.Config
import kotlinx.coroutines.experimental.*
import mu.KotlinLogging
import tornadofx.*
import java.io.Closeable
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger("Prototype.controller.HomeTimeline")

class HomeTimelineController: TimelineController<Status>(), Closeable {
    private val job = Job()
    override val cells = observableList<Status>()

    val account by param<Config.Accounts.TwitterAccount>()

    fun startUserStream() {
        launch(parent = job) {
            @Suppress("KotlinDeprecation")
            account.client.stream.user().await().listen(object: UserStreamListener {
                override suspend fun onStatus(status: Status) {
                    runLater {
                        cells.add(0, status)
                    }
                }

                override suspend fun onConnect() {
                    logger.info { "UserStreamに接続しました: @${account.user.screenName}" }
                }

                override suspend fun onDisconnect() {
                    logger.warn { "UserStreamから切断されました: @${account.user.screenName}" }
                }

                override suspend fun onAnyJson(json: JsonObject) {
                    logger.debug { "Json (@${account.user.screenName}) -> $json" }
                }
            }).start(wait = false, autoReconnect = true).use {
                while (isActive) {
                    try {
                        delay(3, TimeUnit.SECONDS)
                    } catch (e: CancellationException) {
                        break
                    }
                }
            }
        }
    }

    override fun close() {
        runBlocking(CommonPool) {
            job.cancelAndJoin()
        }
    }
}
