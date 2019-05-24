/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.internal.operation.handler;

import com.mulesoft.connectors.roostify.internal.connection.RoostifyConnection;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class RequestCallingService {

	public static CompletableFuture<HttpResponse> sendAsyncRequest(HttpRequest request, boolean value, RoostifyConnection connection) {
		return connection.getHttpClient().sendAsync(request, connection.getApiTimeout(), false, connection.getAuthentication());
	}

}
