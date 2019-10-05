package co.com.wallet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WalletKafkaStreamsApplication

fun main(args: Array<String>) {
	runApplication<WalletKafkaStreamsApplication>(*args)
}
