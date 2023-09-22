package com.multidb.db3.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration // This annotation is used to indicate that the class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.
@EnableMongoRepositories(basePackages = "com.multidb.db3.repositories") // This annotation is used to enable MongoDB repositories. It will scan the package of the annotated configuration class for Spring Data repositories by default.
public class DB3Config {
	
	@Autowired // This annotation is used to autowire bean on the setter method, constructor, a property or methods with arbitrary names and/or multiple arguments.
	private Environment environment; // This interface represents the environment in which the current application is running.

    @Bean // This annotation is a method-level annotation and a direct analog of the XML <bean/> element. The annotation supports most of the attributes offered by <bean/>, such as: init-method, destroy-method, autowiring, etc.
    public MongoClient mongoClient() { // This method returns a new instance of MongoClient which represents a pool of connections to a MongoDB server cluster.
        return MongoClients.create("mongodb://" + environment.getProperty("spring.third.data.mongodb.host") + ":" + environment.getProperty("spring.third.data.mongodb.port")); // Create a new client with the given connection string.
    }

    @Bean // This annotation is a method-level annotation and a direct analog of the XML <bean/> element. The annotation supports most of the attributes offered by <bean/>, such as: init-method, destroy-method, autowiring, etc.
    public MongoTemplate mongoTemplate() { // This method returns a new instance of MongoTemplate which is an implementation of ApplicationListener<ContextRefreshedEvent>, hence it will get a callback when the ApplicationContext gets initialized or refreshed.
        return new MongoTemplate(mongoClient(), environment.getProperty("spring.third.data.mongodb.database")); // Create an instance of SimpleMongoClientDatabaseFactory which takes MongoClient instance and database name as arguments.
    }
}


