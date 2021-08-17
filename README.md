# Getting Started
## Databases
* Postgresql
* Redis

## Used dependencies
* Spring WEB
* Spring Data JPA
* Lombok
* Redis(spring-data-redis)
* Postgres
* Hibernate


## Configuration
The configuration is in application.properties
to create username and password

  ```create role payment_user with login createdb password 'payment_pass'; ```
  
to create database in postgres

   ``` create database db_payment; ```

change owner to user

``` ALTER DATABASE db_payment OWNER TO payment_user;``` 

run project to create all database tables 
insert a user with username ```admin``` and password ```1```:

``` insert into t_user(id, c_username, c_password) values(default, 'admin', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b'); ```

## Endpoints
### Authorization
#### http://localhost:8080/login
Post Data:

```json
 {"username": "admin", "password": "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b"}
 ```

Response Data:

```json
 {
"username": "admin",
"token": "b3d8f6a4-9dcd-4393-a570-a9aac0c0d8d4"
}
```
### LOGOUT
#### http://localhost:8080/logout
Post Data:

```json
 { "token": "b3d8f6a4-9dcd-4393-a570-a9aac0c0d8d4"}
 ```

Response Data:
(Empty)

### Make Payment
#### http://localhost:8080/payment
Post Data:

```json
 { "token": "b3d8f6a4-9dcd-4393-a570-a9aac0c0d8d4",
  "amount": 1.1}
 ```

Response Data:
(Empty)



### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

