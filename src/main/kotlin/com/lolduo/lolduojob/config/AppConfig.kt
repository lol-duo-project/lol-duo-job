package com.lolduo.lolduojob.config

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.services.secretsmanager.AWSSecretsManager
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun secretsManagerClient(): AWSSecretsManager {
        return AWSSecretsManagerClientBuilder.standard()
            .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
            .build()
    }
}