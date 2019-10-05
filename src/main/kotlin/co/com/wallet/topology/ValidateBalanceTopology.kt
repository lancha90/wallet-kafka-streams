package co.com.wallet.topology

import co.com.wallet.enums.Topics
import co.com.wallet.models.Movement
import co.com.wallet.serdes.SerdeFactory
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.Produced
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ValidateBalanceTopology
@Autowired
constructor(val builder: StreamsBuilder){

    val serde = SerdeFactory.createSerdeFor(Movement::class.java)

    fun start() {
        builder.stream(Topics.VALIDATE_BALANCE_TOPIC.value, Consumed.with(Serdes.String(), serde))
                .foreach{key, movement -> println("key: $key - value: ${movement.toString()}")}

    }

}