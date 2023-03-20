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
        val connectionURL = secret.getSecret("prod/mongoDB") as Map<String, String>
        val mongoDBSecret = MongoDB_Secret(
                mongoDB_URL = connectionURL["mongoDB_URL"] ?: "",
                mongoDB_Read_URL = connectionURL["mongoDB_Read_URL"] ?: ""
        )
        val connectionString = ConnectionString(mongoDBSecret.mongoDB_URL)
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        return SimpleMongoClientDatabaseFactory(
            MongoClients.create(mongoClientSettings),
            "riotDB"
        )
    }

    @Bean
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongoDatabaseFactory())
    }
}
