package com.lolduo.lolduojob.config.secret

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("prod")
class AwsSecretProd (
    //private val awsSecretsManager: AWSSecretsManager
    ) : AwsSecret{
    override fun getSecret(secretName: String): Any {
//        val getSecretValueRequest = GetSecretValueRequest()
//                .withSecretId(secretName)
//        val getSecretValueResult = awsSecretsManager.getSecretValue(getSecretValueRequest)
//        return Gson().fromJson(getSecretValueResult.secretString, Any::class.java)
        return when (secretName) {
            "prod/mongoDB" -> MongoDB_Secret(
                "mongodb://prod-mongodb-service-lol-duo:27017",
                "mongodb://prod-mongodb-service-lol-duo:27017"
            )
            else -> throw RuntimeException("Secret not found")
        }
    }
}