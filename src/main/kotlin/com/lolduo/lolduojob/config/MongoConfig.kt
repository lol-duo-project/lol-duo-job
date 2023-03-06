package com.lolduo.lolduojob.config

import com.lolduo.lolduojob.config.secret.AwsSecret
import com.lolduo.lolduojob.config.secret.MongoDB_Secret
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory

@Configuration
class MongoConfig(
    private val secret: AwsSecret
) {

    @Bean
    fun mongoDatabaseFactory(): MongoDatabaseFactory {
        val connectionURL = secret.getSecret("prod/mongoDB") as MongoDB_Secret
        val connectionString = ConnectionString(connectionURL.mongoDB_URL)
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        return SimpleMongoClientDatabaseFactory(
            MongoClients.create(mongoClientSettings),
            "championInfo"
        )
    }

    @Bean
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongoDatabaseFactory())
    }
}
