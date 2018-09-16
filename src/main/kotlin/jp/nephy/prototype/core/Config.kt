package jp.nephy.prototype.core

import com.google.gson.JsonObject
import jp.nephy.jsonkt.*
import jp.nephy.penicillin.PenicillinClient
import java.nio.file.Path
import java.nio.file.Paths

data class Config(override val json: JsonObject): JsonModel {
    companion object {
        private val defaultConfigPath = Paths.get("config.json")

        fun load(path: Path? = null): Config {
            return (path ?: defaultConfigPath).parse()
        }
    }

    val accounts by json.byModel<Accounts?>()

    data class Accounts(override val json: JsonObject): JsonModel {
        val twitter by json.byModelList<TwitterAccount>()

        data class TwitterAccount(override val json: JsonObject): JsonModel, ServiceAccount {
            private val ck by json.byString
            private val cs by json.byString
            private val at by json.byString
            private val ats by json.byString

            val client by lazy {
                PenicillinClient {
                    account {
                        application(ck, cs)
                        token(at, ats)
                    }
                }
            }
            val user by lazy {
                client.account.verifyCredentials().complete().result
            }
        }

        interface ServiceAccount
    }
}
