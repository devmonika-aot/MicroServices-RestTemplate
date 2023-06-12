We use API Gateway so that we will have only one one url for our microservies.
e.g www.flipkart.com and now from here It will dispatch to corresponding microservices.

#Here we have one end ponit for order and product services.

http://localhost:9090/order/36 -> for order
http://localhost:9090/product/3 -> for product

---------------
We have implemented default CircuitBreaker, where If the Product/Order service is down then
It will be caught by filter mentioned in yaml file.
    Here we are using default CircuitBreaker configuration, So information like till what time circuit
       will be open , How many request halfopen circuit will be processed, threeshold etc
       is default.
For Custom configuration, please refer to Orderservice.
