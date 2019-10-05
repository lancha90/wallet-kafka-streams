package co.com.wallet.models

data class Movement (

        var type: String? = null,

        var from: String? = null,

        var to: String? = null,

        var amount: Double? = 0.0
)