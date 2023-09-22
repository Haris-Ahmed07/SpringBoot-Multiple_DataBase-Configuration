# Multi-Database Spring Boot Application

This Spring Boot project demonstrates the configuration and usage of multiple databases within a single application. It leverages Spring Data JPA and Hibernate for two databases and Spring Data MongoDB for the third one. Each database has its own dedicated set of configurations, models, repositories, and controllers.

## Project Structure

The project is organized into different packages, each dedicated to a specific database:

1. **Database 1 (`com.multidb.db1`):**
   - [Configuration (`DB1Config`)](src/main/java/com/multidb/db1/DB1Config.java): Configures the DataSource, EntityManagerFactoryBean, and TransactionManager for the first database.
   - [Models (`User1`)](src/main/java/com/multidb/db1/User1.java): Represents entities stored in the first database.
   - [Repositories (`User1Repository`)](src/main/java/com/multidb/db1/User1Repository.java): Provides CRUD operations for User objects in the first database.
   - [Controllers (`User1Controller`)](src/main/java/com/multidb/db1/User1Controller.java): Handles HTTP requests related to User entities in the first database.

2. **Database 2 (`com.multidb.db2`):**
   - [Configuration (`DB2Config`)](src/main/java/com/multidb/db2/DB2Config.java): Sets up the necessary configurations for the second database.
   - [Models (`User2`)](src/main/java/com/multidb/db2/User2.java): Defines the structure of entities stored in the second database.
   - [Repositories (`User2Repository`)](src/main/java/com/multidb/db2/User2Repository.java): Extends Spring Data repositories for User objects in the second database.
   - [Controllers (`User2Controller`)](src/main/java/com/multidb/db2/User2Controller.java): Manages HTTP requests for User entities in the second database.

3. **Database 3 (`com.multidb.db3`):**
   - [Configuration (`DB3Config`)](src/main/java/com/multidb/db3/DB3Config.java): Configures Spring Data MongoDB for the third database.
   - [Models (`User3`)](src/main/java/com/multidb/db3/User3.java): Represents documents stored in the MongoDB database.
   - [Repositories (`User3Repository`)](src/main/java/com/multidb/db3/User3Repository.java): Provides MongoDB-specific repository features for User objects.
   - [Controllers (`User3Controller`)](src/main/java/com/multidb/db3/User3Controller.java): Handles HTTP requests for User entities stored in MongoDB.

## Configuration

Each database has its own configuration class (`DB1Config`, `DB2Config`, `DB3Config`) that sets up the DataSource, EntityManagerFactoryBean, and TransactionManager. These configurations leverage Spring annotations such as `@Configuration`, `@EnableTransactionManagement`, and `@EnableJpaRepositories` or `@EnableMongoRepositories` to configure the respective database technologies.

## Models

Model classes (`User1`, `User2`, `User3`) are used to define the structure of entities/documents that will be stored in their respective databases.

## Repositories

Repository interfaces (`User1Repository`, `User2Repository`, `User3Repository`) extend Spring Data Commons repository interfaces and enable operations like CRUD (Create, Read, Update, Delete) for User objects in each database.

## Controllers

Controller classes (`User1Controller`, `User2Controller`, `User3Controller`) manage HTTP requests related to User objects. They interact with the corresponding UserRepository to perform operations on the databases, such as saving and retrieving User entities.

## Usage

To interact with the application, you can use the following endpoints:

- **Saving a User:**
  - Send a POST request to `/savedb1` to save a User in the first database (`db1`).
  - Send a POST request to `/savedb2` to save a User in the second database (`db2`).
  - Send a POST request to `/savedb3` to save a User in the third database (MongoDB).

- **Retrieving All Users:**
  - Send a GET request to `/getalldb1` to retrieve all User objects from the first database (`db1`).
  - Send a GET request to `/getalldb2` to retrieve all User objects from the second database (`db2`).
  - Send a GET request to `/getalldb3` to retrieve all User objects from the third database (MongoDB).

The application will respond with the appropriate User objects or a confirmation message based on the operation performed.

## Note

This project serves as a basic example of how to configure a Spring Boot application to work with multiple databases. Depending on your specific requirements, you may need to customize the configurations and extend functionality to meet your application's needs.

## Getting Started

Getting started with your Spring Boot application is a breeze:

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/Haris-Ahmed07/SpringBoot-Multiple_DataBases-Configuration

Follow the step-by-step instructions provided in the README file for configuring and running your project.
Contributions
While this repository primarily focuses on configuration, we welcome contributions to improve and expand upon the existing setup. If you have suggestions or improvements, please follow our contribution guidelines.

License
This project is open-source and licensed under the MIT License.

GitHub Repository

Feel free to use this structured description for your GitHub repository. It provides a clear and organized overview of your project's purpose and features.
