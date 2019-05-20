/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.connection;

import com.mulesoft.connector.roostify.api.proxy.DefaultHttpProxyConfig;
import com.mulesoft.connector.roostify.internal.auth.CreateAuthentication;
import com.mulesoft.connector.roostify.internal.connection.provider.param.ConnectionParameterGroup;
import com.mulesoft.connector.roostify.internal.exception.ExceptionHandler;
import com.mulesoft.connector.roostify.internal.exception.RoostifyConnectorException;
import org.mule.runtime.api.connection.*;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.*;
import com.mulesoft.connector.roostify.internal.connection.provider.RoostifyConnectionProvider;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;
import org.mule.runtime.http.api.client.auth.HttpAuthentication;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.mule.runtime.http.api.tcp.TcpClientSocketProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import java.util.concurrent.CompletableFuture;

import static com.mulesoft.connector.roostify.internal.config.RoostifyConfiguration.getAddressValue;
import static com.mulesoft.connector.roostify.internal.config.RoostifyConfiguration.getVersionValue;
import static com.mulesoft.connector.roostify.internal.exception.ExceptionHandler.getError;
import static com.mulesoft.connector.roostify.internal.operation.handler.RequestCallingService.sendAsyncRequest;
import static com.mulesoft.connector.roostify.internal.util.Urls.LOAN_APPLICATIONS;
import static org.mule.runtime.api.meta.ExpressionSupport.NOT_SUPPORTED;
import static org.mule.runtime.extension.api.annotation.param.ParameterGroup.CONNECTION;

@Alias("RoostifyConnectionForAllServices")
public class RoostifyConnectionForAllServices extends RoostifyConnectionProvider {
    private final static Logger logger = LoggerFactory.getLogger(RoostifyConnectionForAllServices.class);

    @ParameterGroup(name = CONNECTION)
    @Placement(order = 1)
    private ConnectionParameterGroup connectionParams;

    @Parameter
    private String username;
    @Parameter
    private String password;

    private HttpAuthentication authGen;

    @Parameter
    @Optional
    @Placement(tab = "Proxy", order = 3)
    @Expression(NOT_SUPPORTED)
    @DisplayName("Proxy Configuration")
    private DefaultHttpProxyConfig proxyConfig;

    @Inject
    private HttpService httpService;

    @Override
    public RoostifyConnection connect() throws ConnectionException {
        authGen = CreateAuthentication.createAuth(username, password);
        HttpClient httpClient = httpService.getClientFactory().create(new HttpClientConfiguration.Builder()
                .setTlsContextFactory(connectionParams.getTlsContext())
                .setProxyConfig(proxyConfig)
                .setClientSocketProperties(TcpClientSocketProperties.builder()
                        .connectionTimeout(connectionParams.getConnectionTimeout())
                        .build())
                .setMaxConnections(connectionParams.getMaxConnections())
                .setUsePersistentConnections(connectionParams.getUsePersistentConnections())
                .setConnectionIdleTimeout(connectionParams.getConnectionIdleTimeout())
                .setStreaming(connectionParams.isStreamResponse())
                .setResponseBufferSize(connectionParams.getResponseBufferSize())
                .setName("RoostifyCofiguration")
                .build());
        httpClient.start();
        return new RoostifyConnection(httpClient,connectionParams.getConnectionTimeout(), authGen,username, password);
    }

    @Override
    public ConnectionValidationResult validate(RoostifyConnection connection) {
        String address = getAddressValue();
        String version = getVersionValue();
        String strUri = address + version  + LOAN_APPLICATIONS;
        HttpRequest request = connection.getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(strUri).build();
        CompletableFuture<HttpResponse> response = sendAsyncRequest(request, true, connection);
        try {
            if (response.get().getStatusCode() != 200) {
                String str = response.get().getStatusCode() + "";
                return ConnectionValidationResult.failure(str, new RoostifyConnectorException(response.get().getReasonPhrase(),getError(response.get().getStatusCode())));
            }
        } catch (Exception e) {
            logger.info("Error happened while validating the connection : " + e);
        }
       return ConnectionValidationResult.success();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

