package co.com.wallet.serdes

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.juli.logging.LogFactory
import org.apache.kafka.common.serialization.Serializer

class Serializer<T> : Serializer<T> {
    private val log = LogFactory.getLog(javaClass)

    override fun configure(props: Map<String, *>?, isKey: Boolean) {}

    override fun serialize(topic: String, data: T?): ByteArray? {

        if (data == null)
            return null

        try {
            return OBJECT_MAPPER.writeValueAsBytes(data)
        } catch (e: Exception) {
            log.error("search-kafka-streams: Error serializing JSON message " + e.message)

            return null
        }

    }

    override fun close() {}

    companion object {
        protected val OBJECT_MAPPER = ObjectMapper()
    }

}
