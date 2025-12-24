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

now run the discovery-server and hit the url
http://localhost:8761/

Till now, in discovery service...both order service and inventory service is running

WORKING OF EUREKA
• Service Registration: A service registers itself with the Eureka Server upon startup.
• Heartbeat: The service sends heartbeats periodically to renew its lease with the Eureka Server.
• Service Discovery: Other services can query Eureka to discover the location (IP and port) of the registered service.
• Health Check: Eureka performs health checks to ensure that registered services are still healthy.
• Eviction: If a service stops sending heartbeats and its lease expires, the Eureka Server evicts it from the registry.

Now, we can see in this whether we can fetchOrders from the inventory-service.
http://localhost:9010/api/v1/products/fetchOrders GET 
it will return Hello from Orders Service

SPRING CLOUD API GATEWAY
Spring Cloud API Gateway is a powerful, flexible solution for routing and 
proxying requests to downstream services in a microservices architecture. It
handles several important tasks like routing, filtering, authentication, and load balancing.

Spring Cloud Gateway consists of 3 main building blocks:
1. Route
2. Predicate
3. Filters

Route: Think of this as the destination that we want a particular request to
route to. It comprises of destination URI, a condition that has to satisfy — Or in
terms of technical terms, Predicates, and one or more filters.

Predicate: This is literally a condition to match. i.e. kind of “if”
condition..if requests has something — e.g. path=blah or request header contains
foo-bar etc.
Predicates with path: - Path=/api/v1/orders/**
Predicates with Method: - Method=GET
Predicates with Header: - Header=User-Agent, Mozilla/*

3. Filter: These are instances of Spring Framework WebFilter. This is where you can
   apply your magic of modifying request or response. There are quite a lot of out of
   box WebFilter that framework provides.
   filters:
- AddRequestHeader=X-Request-Id, 12345
- AddResponseHeader=X-Response-id, abcd
- RedirectTo=302, https://youtube.com
- StripPrefix=1
- RemoveRequestHeader=Cookie

Now start the servers(run the codes):
discovery-service
order-service
inventory-service
api-gateway

http://localhost:8761/ to see all
if we hit localhost:9010/inventory/products/fetchOrders
it will return Hello from Orders Service

some code changes in application.yml
if we hit localhost:9010/api/v1/inventory/products/fetchOrders  ::api/v1 daalna padega as it is not there in order-service but it is there in api-gateway application.yml
it will return Hello from Orders Service

Open Feign Microservices  communication
Spring Cloud OpenFeign integrates seamlessly with Spring Cloud and simplifies the
development of HTTP clients by allowing you to create interfaces that resemble the  API of the target service.

<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>


Now , if we order anything from order-service, it will reduce the stock from the inventory-service.
after teh code 
POST http://localhost:8080/api/v1/orders/core/create-order
{
   "items":[
      {
      "productId":14,
      "quantity":2
      },
      {
      "productId":15,
      "quantity":3
      }
   ]
}

it will show changes in products table in id 14 and 15

Resilience4j
Resilience4j is a lightweight, standalone library designed for implementing resilience patterns in Java applications, particularly those using
microservices architecture. It provides mechanisms to handle failures gracefully and ensures that services remain responsive under failure
conditions. It’s Key features are:
• Retry
• Rate Limiter
• Circuit Breaker
• Integration with Spring Boot

Retry
Resilience4J can help write logic for retry when a service call fails. This
retries failed operations a certain number of times before giving up. 

RateLimiter
Controls the rate of requests to a service by limiting the number of calls allowed during a specific time period

we can check the rate limiter and retry in the post function above.

