package com.microservices.order.external.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.order.exception.CustomResponseErrorHandler;
import com.microservices.order.external.response.ProductResponse;
import com.microservices.order.model.ErrorResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ProductClient {

  @Autowired RestTemplate restTemplate;

  private ObjectMapper mapper = new ObjectMapper();
  ResponseEntity<ProductResponse> response = null;
  ErrorResponse errorResponse = null;

  private static final String url = "http://localhost:8080/product/reduceQuantity";
  public ResponseEntity<?> reduceProduct(long id, long quantity) {

    // restTemplate.setErrorHandler(new CustomResponseErrorHandler());
    // Adding Header
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    // Adding Entity i.e headers values
    HttpEntity<?> entity = new HttpEntity<>(headers);
    /* ---POST REQUEST----
    String requestBody = "{\"key\": \"value\"}";
    HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
    */

    String urlTemplate =
        UriComponentsBuilder.fromHttpUrl(url)
            .path("/{id}") // .path("/{path1}/{path2}")
            .queryParam("quantity", quantity)
            .buildAndExpand(id) //  .buildAndExpand(path1, path2);
            .encode()
            .toUriString();
    try {

      response = restTemplate.exchange(urlTemplate, HttpMethod.PUT, entity, ProductResponse.class);
    } catch (HttpClientErrorException e) {
      System.out.println("HttpClientErrorException" + e);
      String body = e.getResponseBodyAsString();
      /*
      restTemplate.setErrorHandler(new CustomResponseErrorHandler()) has been commented as
      the response which we are getting is "".
      Ideally the flow will be like
      1) Interceptor i.e CustomResponseErrorHandler which implements ReponseHandler will intercept
      the any error from Product Service
      2) If It satisfies any condition inside overridden method hasError(),The error will be
      thrown from overriden method from handleError().
      3) The thrown error will be handle by try-catch block where exchange method is being called.
       */
      try {
        mapper.findAndRegisterModules();
        errorResponse = mapper.readValue(body, ErrorResponse.class);
      } catch (JsonProcessingException jsonProcessingException) {
        jsonProcessingException.printStackTrace();
      }
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    } catch (HttpServerErrorException e) {
      // Handling FallBack
      return fallback(e);

    } catch (HttpStatusCodeException e) {
      System.out.println("HttpStatusCodeException" + e.getResponseBodyAsString());

    } catch (RestClientException e) {
      System.out.println("RestClientException" + e.getRootCause());
      return fallback(e);

    } catch (Exception e) {
      System.out.println("Exception" + e.getMessage());
    }

    return response;
  }

  public ResponseEntity<?> fallback(Exception e) {
    // Provide a fallback response when the circuit is open or an error occurs
    System.out.println("HIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
    return new ResponseEntity<>("Fallback response", HttpStatus.BAD_REQUEST);
  }
}
