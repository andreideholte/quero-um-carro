package com.desafio.api.quc.config;

import java.util.Arrays;

import com.mongodb.MongoClientSettings.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableMongoRepositories
@EnableAutoConfiguration(exclude = { EmbeddedMongoAutoConfiguration.class })
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Autowired
    Environment env;

    @Override
    public String getDatabaseName() {
        return env.getProperty("spring.data.mongodb.database");
    }
    
    @Override
    protected void configureClientSettings(Builder builder) {
        builder
            .credential(MongoCredential.createCredential(
                env.getProperty("spring.data.mongodb.username"), 
                getDatabaseName(), 
                env.getProperty("spring.data.mongodb.password").toCharArray()))
            .applyToClusterSettings(settings -> {
                settings.hosts(Arrays.asList(new ServerAddress(
                    env.getProperty("spring.data.mongodb.uri"), 
                    Integer.parseInt(env.getProperty("spring.data.mongodb.port")))));
            });
    }
}
