#eureka.client.register-with-eureka = true
#eureka.client.fetch-registry = true
#eureka.client.service-url.defaultZone=http://localhost:8761/eureka

The above are commented because all the configuration are done in configserver service and
configserver are pulling all the configuration details from github.


#If we write below property ddl = true that means table will get created if table doesn't exit.
#spring.jpa.generate-ddl=true

#Successfully implemented microservices communication with exchange method (GET & POST)
#Handle Custom error generated from other service by implementing ResponseHandler.
Basically ResponseHandler will intercept any error generated from called service.
