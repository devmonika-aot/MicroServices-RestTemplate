
1) Read carefully where I have added @Service annotation and in the controller what I am injecting to
call service class method.

2) If we write THIS property below
spring.jpa.generate-ddl=true
that means table will get created if table doesn't exit.

3) hibernate converts the camel case lettering to underscores by default. so you either change your columns
in your table to reflect that or change hibernate naming strategy.
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

4) Hanndled GlobalException Handler like real projectGlobalExceptionHandling

5) CRUD along with put and also handled exception for put as well.
-------------------------------
#eureka.client.register-with-eureka = true
#eureka.client.fetch-registry = true
#eureka.client.service-url.defaultZone=http://localhost:8761/eureka

The above are commented because all the configuration are done in configserver service and
configserver are pulling all the configuration details from github.
-------------------------------


