package co.com.wallet.services

import co.com.wallet.topology.ValidateBalanceTopology
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class KafkaStreamsService @Autowired
constructor(var validateBalanceTopology: ValidateBalanceTopology) {

    @PostConstruct
    fun startApp() {
        validateBalanceTopology.start()
    }

}
