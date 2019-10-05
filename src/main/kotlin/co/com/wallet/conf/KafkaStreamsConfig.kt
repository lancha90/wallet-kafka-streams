package co.com.wallet.conf

import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.KafkaStreams
import org.springframework.kafka.support.serializer.JsonDeserializer.VALUE_DEFAULT_TYPE
import org.apache.kafka.streams.StreamsConfig
import org.springframework.kafka.support.serializer.JsonSerde
import org.apache.kafka.common.serialization.Serdes
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class KafkaStreamsConfig {

    val builder = StreamsBuilder()

    @Bean
    fun kafkaStreams(kafkaProperties: KafkaProperties): KafkaStreams {
        val props = Properties()

        props.putAll(kafkaProperties.properties)
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.bootstrapServers)

        val kafkaStreams = KafkaStreams(kafkaStreamTopology(), props)
        kafkaStreams?.start()
        Runtime.getRuntime().addShutdownHook(Thread(Runnable { kafkaStreams?.close() }))

        return kafkaStreams
    }

    @Bean
    fun kafkaStreamTopology(): Topology {
        return builder.build()
    }

    @Bean
    fun builder(): StreamsBuilder {
        return builder
    }

}