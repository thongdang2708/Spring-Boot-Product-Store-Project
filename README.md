# Spring-Boot-Product-Store-Project
Product Store with Authorization and Authentication with Spring Boot + Spring Data JPA + Spring Security + MySQL

Warning: Due to high charge costs for AWS services, database is no longer hosted on AWS RDS. I built on Docker instead.

Image in Postman testing:

<img width="277" alt="image" src="https://user-images.githubusercontent.com/89829761/211303549-7c572d68-1654-423d-953f-3f5e32c07570.png">

Configuration with MySQL deployed to AWS:


<img width="744" alt="image" src="https://user-images.githubusercontent.com/89829761/212752963-a292de7f-d7b8-481f-92f1-049a4e44510c.png">


Security config on AWS is set open with all IPv4 addresses

In application.properties on GitHub, there are some hidden information for database connection:

spring.datasource.username=*****(Hidden for security)

spring.datasource.password=************(Hidden for security)

```bash
To start, use "mvn clean spring-boot:run"
```

or

```bash
docker-compose up
```

When stopping the app, close with the below command in backend directory:

```bash
docker-compose down
```

Checking the running containers, use the command in backend directory:

```bash
docker-compose ps
```

