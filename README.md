<h1 align="center">🛒 Marketplace Platform 🛍️</h1>

<h2 align="center">🏢 System Architecture (Current) 🛠️</h2>

![sysArchi](resources/system_architecture_v1.png)

Marketplace Platform is a web application that allows users to either buy or sell products. The platform is developed using the Spring Boot and Angular frameworks, integrated with multiple AWS services, ensuring scalability, reliability, and security of the system.

<h2 align="center">📚 Table of Contents 📚</h2>

- [System Design](#system-design)
- [Development Methodology](#development-methodology)
- [System Attributes](#system-attributes)

<h2 align="center" id="system-design">🔧 System Design 🔧</h2>
The system architecture is composed of five core components: the front-end, back-end, database, authentication service, and storage service, all working in collaboration to meet the system's functional requirements.
The implementation of these components, uses the following frameworks and services:

- Back-End Services (Spring Boot)
- Front-End Web (Angular)
- Database (Amazon DynamoDB)
- Authentication Service (Amazon Cognito)
- Storage Service (Amazon S3)

### Back-End Services
The back-end is built using the Spring Boot framework, adhering to a microservices architecture. The back-end logic is divided into three distinct microservices—`User`, `Product`, and `Order`—each responsible for isolating specific functionalities. These services interact with one another as needed. <br/>
Microservices are deployed on `AWS Elastic Kubernetes Service` (EKS), offering robust scalability and high reliability. Each microservice is exposed internally within the EKS cluster via ClusterIP services. An Ingress service, managed by an Ingress controller integrated with the cluster, is used to route external traffic to the appropriate microservice based on URL path mapping. <br/>

<p align="center">
<img src="resources/backend_microservices.png" width="70%"/>
</p>

#### Communication of Microservices
- Inter-pod communication occurs over the Kubernetes network, ensuring secure and reliable data exchange. The pods are designed to be stateless, with persistent data stored in the database, enabling horizontal scaling without risk of data loss. Pods communicate with each other through services using DNS names provided by the Kubernetes DNS service. 
- Communication with external services like Amazon DynamoDB and Amazon S3 is facilitated via a NAT Gateway, allowing pods within private subnets to access the internet. Applications running in the pods utilize the AWS SDK for Java, which offers a high-level API that abstracts the underlying HTTP API interactions with AWS services. The SDK includes built-in retry logic and error handling, enhancing the system's resilience to transient failures.

#### Design Patterns
- The back-end services are designed using the `Repository` pattern, which abstracts the data access logic from the business logic. This pattern allows for easy unit testing and code maintainability.
- `Builder` pattern is used with AWS SDK clients to create service clients with default configurations. This pattern simplifies the client creation process, making it easier to manage configurations across the application and improving code readability.
- Security Filter Chain uses `Chain of Responsibility` pattern to process incoming requests and apply security checks in a sequential manner. This pattern allows for easy addition or removal of security filters without modifying the existing codebase.
- Trasfering data between components is done using `Data Transfer Objects (DTOs)`, which are lightweight objects that contain only the necessary data to transfer between components. This pattern helps in reducing the amount of data transferred, improving performance.
- Spring Security transforms the JWT token into an Authentication object using the `Adapter` pattern. This Authentication object is subsequently employed throughout the microservices to authorize requests and verify user identity.
- The `Facade` pattern is employed to abstract the logic of user information retrieval by encapsulating the functionalities of Amazon DynamoDB within a single class. This approach minimizes the need for direct network resource access, thereby improving performance and maintainability.

### Front-End Web
The front-end is developed using the Angular framework, providing a responsive and interactive user interface. The Angular application is hosted on an `Amazon S3` bucket, with static website hosting enabled. The front-end communicates with the back-end services via RESTful APIs exposed by the microservices. <br/>

### Database
The database is implemented using `Amazon DynamoDB`, a fully managed NoSQL (key-value store) database service provided by AWS. DynamoDB offers seamless scalability, high availability, and low latency, making it an ideal choice for the Marketplace Platform. The database is designed to store user information, product details, and order data.

### Authentication Service
The authentication service is implemented using `Amazon Cognito`, a fully managed identity and access management service provided by AWS. Amazon Cognito offers user authentication, authorization, and user management functionalities, enabling secure access to the Marketplace Platform. The service is integrated with the user microservice to authenticate and authorize user requests.
System makes use of Custom Attributes, provided by Amazon Cognito, to implement Role-Based Access Control (RBAC) for users.

### Storage Service
The storage service is implemented using `Amazon S3`, a scalable object storage service provided by AWS. Amazon S3 is used to store product images uploaded by sellers on the Marketplace Platform. The service offers high durability, availability, and scalability, making it an ideal choice for storing static assets. Backend uses the AWS SDK to generate pre-signed URLs for uploading images to Amazon S3 bucket. Frontend uses these pre-signed URLs to directly upload (PUT) images to the S3 bucket. Uploaded images are publicly accessible (GET) via the object URL.

<h2 align="center" id="development-methodology">🔨 Development Methodology 🔨</h2>

<h2 align="center" id="system-attributes">📈 System Attributes 📈</h2>

### Reliability


### Security

#### Application Security
Environment variables containing sensitive information, such as database credentials and API keys, are stored securely in Kubernetes Secrets. <br/>
Integration with Amazon Cognito, ensures only users with verified Email accounts can access protected resources within the system. <br/>

#### Network Security
Pods running Application Services are deployed in a private subnet (with ClusterIP services) and are not directly accessible from the internet. The Ingress controller is deployed in a public subnet, providing a single entry point to the cluster from the public internet.<br/>
