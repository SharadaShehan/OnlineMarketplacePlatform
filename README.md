# MarketplacePlatform

## Microservices with Eureka Server and Spring Cloud Gateway

#### Create configuraion files for each service

1. User Service
    ```
    server:
        port: 9001
        servlet:
            context-path: /api

    spring:
        application:
            name: USER-SERVICE
        security:
            oauth2:
                resourceserver:
                    jwt:
                        issuer-uri: <JWT Issuer URI>

    aws-cognito:
        user-pool-id: <User Pool ID>
        user-pool-client-id: <User Pool Client ID>
        user-pool-client-secret: <User Pool Client Secret>

    s3-bucket:
        name: <S3 Bucket Name>

    aws-role-user:
        access-key: <AWS Account Access Key>
        secret-key: <AWS Account Secret Key>

    eureka:
        client:
            register-with-eureka: true
            fetch-registry: true
            service-url:
                defaultZone: http://localhost:8761/eureka/
            instance:
                hostname: localhost
    ```

2. Product Service
    ```
    server:
        port: 9002
        servlet:
            context-path: /api

    spring:
        application:
            name: PRODUCT-SERVICE
        security:
            oauth2:
                resourceserver:
                    jwt:
                        issuer-uri: <JWT Issuer URI>

    s3-bucket:
        name: <S3 Bucket Name>

    aws-role-user:
        access-key: <AWS Account Access Key>
        secret-key: <AWS Account Secret Key>

    eureka:
        client:
            register-with-eureka: true
            fetch-registry: true
            service-url:
                defaultZone: http://localhost:8761/eureka/
            instance:
                hostname: localhost

    microservices:
        user-service-endpoint: http://USER-SERVICE
    ```

3. Order Service
    ```
    server:
        port: 9003
        servlet:
            context-path: /api

    spring:
        application:
            name: ORDER-SERVICE
        security:
            oauth2:
                resourceserver:
                    jwt:
                        issuer-uri: <JWT Issuer URI>

    aws-role-user:
        access-key: <AWS Account Access Key>
        secret-key: <AWS Account Secret Key>

    eureka:
        client:
            register-with-eureka: true
            fetch-registry: true
            service-url:
                defaultZone: http://localhost:8761/eureka/
            instance:
                hostname: localhost

    microservices:
        user-service-endpoint: http://USER-SERVICE
        product-service-endpoint: http://PRODUCT-SERVICE
    ```

#### Open each service in IntelliJ IDEA and run them. (Run Service Registry first)
