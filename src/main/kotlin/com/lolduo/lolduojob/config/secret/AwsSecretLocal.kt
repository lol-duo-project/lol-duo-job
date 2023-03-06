package com.lolduo.lolduojob.config.secret

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("local")
class AwsSecretLocal : AwsSecret {
    override fun getSecret(secretName: String): Any {
        return when (secretName) {
            "prod/mongoDB" -> MongoDB_Secret(
                "mongodb://localhost:27017",
                "mongodb://localhost:27017"
            )
            else -> throw RuntimeException("Secret not found")
        }
    }
}