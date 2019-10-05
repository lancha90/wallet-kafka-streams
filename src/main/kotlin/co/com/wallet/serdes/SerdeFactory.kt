package co.com.wallet.serdes

import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serdes

import java.util.HashMap

object SerdeFactory {

    fun <T> createSerdeFor(clazz: Class<T>): Serde<T> {
        val serdeProps = HashMap<String, Any>()
        serdeProps["serializedClass"] = clazz

        val ser = Serializer<T>()
        ser.configure(serdeProps, false)

        val de = Deserializer<T>()
        de.configure(serdeProps, false)

        return Serdes.serdeFrom(ser, de)
    }
}
