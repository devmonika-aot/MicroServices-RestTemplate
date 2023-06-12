package com.microservices.order.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

public class CustomResponseErrorHandler implements ResponseErrorHandler {
  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return (response.getStatusCode().is1xxInformational()
        || response.getStatusCode().is3xxRedirection()
        || response.getStatusCode().is4xxClientError()
        || response.getStatusCode().is5xxServerError());
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
      if(response.getStatusCode().series() == HttpStatus.Series.REDIRECTION){
          //For 3** error Response
          throw new RedirectionException(response.getStatusCode(), response.getStatusText());
      }
      else if(response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR){
      throw new HttpClientErrorException(
          response.getStatusCode(),
          "{\"errorCode\":403,\"errorMessage\":\"Product does not have sufficient Quantity\",\"shortDescription\":\"INSUFFICIENT_QUANTITY\",\"timestamp\":\"2023-06-07T13:31:45.6715924\"}");

      }
      else if(response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR){
          throw  new HttpServerErrorException(response.getStatusCode(),response.getStatusText());
      }
      else {
            throw new RestClientException("An error occurred during the REST request.");
      }
  }
}
