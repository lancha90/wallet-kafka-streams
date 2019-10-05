package co.com.wallet.serdes

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.juli.logging.LogFactory
import org.apache.kafka.common.serialization.Deserializer

/**
 * Default constructor needed by Kafka
 */
class Deserializer<T> : Deserializer<T> {
    private val log = LogFactory.getLog(javaClass)
    private val objectMapper = ObjectMapper()

    private var tClass: Class<T>? = null

    override fun configure(props: Map<String, *>, isKey: Boolean) {
        tClass = props["serializedClass"] as Class<T>
    }

    override fun deserialize(topic: String, bytes: ByteArray?): T? {
        if (bytes == null)
            return null

        var data: T? = null
        try {
            data = objectMapper.readValue(bytes, tClass)
        } catch (e: Exception) {
            log.error("search-kafka-streams: Error deserializing JSON message " + e.message)
        }

        return data
    }

    override fun close() {}
}
