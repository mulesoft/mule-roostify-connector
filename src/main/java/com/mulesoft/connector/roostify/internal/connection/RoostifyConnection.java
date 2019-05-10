/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.connection;

import org.mule.connectors.commons.template.connection.ConnectorConnection;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.auth.HttpAuthentication;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.request.HttpRequestBuilder;


public final class RoostifyConnection implements ConnectorConnection {

  private HttpAuthentication authentication;
  private HttpClient httpClient;
  private HttpRequestBuilder httpRequestBuilder;
  private int apiTimeout;
  private String username;
  private String password;

  public RoostifyConnection(HttpClient httpClient, int timeout, HttpAuthentication authentication, String username, String password) {
    this.authentication = authentication;
    this.httpClient = httpClient;
    this.apiTimeout = timeout;
    this.httpRequestBuilder = HttpRequest.builder();
    this.username = username;
    this.password = password;
  }

  public HttpAuthentication getAuthentication() {
    return authentication;
  }

  public HttpClient getHttpClient() {
    return httpClient;
  }

  public HttpRequestBuilder getHttpRequestBuilder() {
    return httpRequestBuilder;
  }

  public int getApiTimeout() {
    return apiTimeout;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void invalidate() {
    httpClient.stop();
  }

  @Override
  public void disconnect() {
    httpClient.stop();
  }

  @Override
  public void validate() {

  }

}
