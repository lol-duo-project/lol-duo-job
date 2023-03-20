package com.lolduo.lolduojob.config.secret

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest
import com.google.gson.Gson
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("prod")
class AwsSecretProd : AwsSecret{

    override fun getSecret(secretName: String): Any {
        val secretsManagerClient = AWSSecretsManagerClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(getAwsCredentials()))
            .build()
        val getSecretValueRequest = GetSecretValueRequest()
            .withSecretId(secretName)
        val getSecretValueResult = secretsManagerClient
            .getSecretValue(getSecretValueRequest)
        return Gson().fromJson(getSecretValueResult.secretString, Any::class.java)
    }

    fun getAwsCredentials(): AWSCredentials {
        val accessKeyId = System.getenv("AWS_ACCESS_KEY_ID")
        val secretAccessKey = System.getenv("AWS_SECRET_ACCESS_KEY")
        return BasicAWSCredentials(
            accessKeyId,
            secretAccessKey
        )
    }
}