Microservices are an architectural style where applications are
developed as a collection of small, loosely coupled, independently
deployable services.

inventory service:
http://localhost:9010/api/v1/products    GET

order service:
http://localhost:9020/api/v1/orders GET

Service Discovery
In a microservices architecture, each microservice is a standalone application with
specific business functionality. Since these microservices need to communicate with
each other to function as a complete application, they need to know each other’s
network locations. Service Discovery comes into play here, maintaining a record of
these services’ locations, helping them find each other, and enabling
communication.

Spring Cloud Eureka
Eureka is a REST based service which is  primarily used for acquiring information about
services that you would want to communicate  with. This REST service is also known as
Eureka Server. The Services that register in Eureka Server to obtain information about each
other are called Eureka Clients.

in inventory-service pom.xml, added the following:

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

		<spring-cloud.version>2023.0.3</spring-cloud.version>

in application.properties of inventory-service:
eureka.client.service-url.defaultZone=http://localhost:8761/eureka

in all the services, we have to do the same process.
