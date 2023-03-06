package com.lolduo.lolduojob.config

import com.mongodb.client.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongoConfig {

    @Bean
    fun mongoTemplate(mongoClient: MongoClient): MongoTemplate {
        return MongoTemplate(mongoClient, "championInfo")
    }
}
