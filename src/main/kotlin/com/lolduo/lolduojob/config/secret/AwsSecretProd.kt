package com.lolduo.lolduojob.config.secret

import com.amazonaws.services.secretsmanager.AWSSecretsManager
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest
import com.google.gson.Gson
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("prod")
class AwsSecretProd (private val awsSecretsManager: AWSSecretsManager) : AwsSecret{
    override fun getSecret(secretName: String): Any {

        val getSecretValueRequest = GetSecretValueRequest()
            .withSecretId(secretName)
        val getSecretValueResult = awsSecretsManager.getSecretValue(getSecretValueRequest)
        return Gson().fromJson(getSecretValueResult.secretString, Any::class.java)
    }
}