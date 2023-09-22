package com.multidb.db2.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration // This annotation is used to indicate that the class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.
@EnableTransactionManagement // This annotation enables Spring's annotation-driven transaction management capability.
@EnableJpaRepositories( // This annotation is used to enable JPA repositories. It will scan the package of the annotated configuration class for Spring Data repositories by default.
	entityManagerFactoryRef = "entityManagerFactoryBean2", // This attribute sets the name of the EntityManagerFactory bean definition to be used to create repositories discovered through this annotation.
	basePackages = {"com.multidb.db2.repositories"}, // This attribute sets the base packages to scan for annotated components. It's used with @ComponentScan.
	transactionManagerRef = "transactionManager2" // This attribute sets the name of the PlatformTransactionManager bean definition to be used to create repositories discovered through this annotation.
)
public class DB2Config {
	
	@Autowired // This annotation is used to autowire bean on the setter method, constructor, a property or methods with arbitrary names and/or multiple arguments.
	private Environment environment; // This interface represents the environment in which the current application is running.
	
	@Bean("secondDataSource") // This annotation is a method-level annotation and a direct analog of the XML <bean/> element. The annotation supports most of the attributes offered by <bean/>, such as: init-method, destroy-method, autowiring, etc.
	@Primary // This annotation indicates that a bean should be given preference when multiple candidates are qualified to autowire a single-valued dependency. If exactly one 'primary' bean exists among the candidates, it will be the autowired value.
	public DataSource dataSource() // This method returns a new instance of DriverManagerDataSource which is an implementation of DataSource that configures a plain old JDBC Driver via Bean properties.
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setUrl(environment.getProperty("spring.second.datasource.url")); // Set the JDBC URL to use for connecting through DriverManager.
		dataSource.setDriverClassName(environment.getProperty("spring.second.datasource.driver-class-name")); // Set the JDBC driver class name. This must be set in combination with either url or databaseName.
		dataSource.setUsername(environment.getProperty("spring.second.datasource.username")); // Set the username to use for connecting through DriverManager.
		dataSource.setPassword(environment.getProperty("spring.second.datasource.password")); // Set the password to use for connecting through DriverManager.
		
		return dataSource;
	}

	@Bean(name = "entityManagerFactoryBean2") 
	@Primary 
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() // This method returns a factory that creates application-managed entity manager instances from this factory.
	{
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		
		bean.setDataSource(dataSource()); // Set the DataSource that should be used to create EntityManager instances. 
		
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();	
		bean.setJpaVendorAdapter(adapter); // Set the vendor adapter to use for creating EntityManagers. Allows to plug in vendor-specific behavior.
		
		Map<String, String> props = new HashMap<>();
		
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect"); // Set Hibernate's database dialect. 
		props.put("hibernate.show_sql", "true"); // Enable logging of SQL statements
		props.put("hibernate.hbm2ddl.auto", "update"); // Setting this property makes Hibernate create, drop, update or validate tables automatically according to object mappings.
		
		
		bean.setJpaPropertyMap(props); // Set JPA properties that should be passed through to the JPA provider
		
		bean.setPackagesToScan("com.multidb.db2.models"); // Set packages to scan for finding entities that shall be managed by the EntityManager created by this factory.
		
		return bean;
	}
	
	@Bean(name = "transactionManager2")
	@Primary
	public PlatformTransactionManager transactionManager() // This method returns an instance of JpaTransactionManager which is a PlatformTransactionManager implementation for a single JPA EntityManagerFactory. Binds a JPA EntityManager from the specified factory to the thread, potentially allowing for one thread-bound EntityManager per factory. 
	{
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory((EntityManagerFactory) entityManagerFactoryBean().getObject()); // Set the EntityManagerFactory that this instance should manage transactions for
		
		return manager;
	}
	
}

