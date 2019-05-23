/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.services;

import com.mulesoft.connector.roostify.api.webhooks.webhookEventsDTO;
import com.mulesoft.connector.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connector.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connector.roostify.api.response.ResponseStatus;
import com.mulesoft.connector.roostify.internal.util.RoostifyUtil;
import org.mule.connectors.commons.template.service.DefaultConnectorService;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.domain.entity.ByteArrayHttpEntity;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.mulesoft.connector.roostify.internal.exception.ExceptionHandler.checkErrorAsync;
import static com.mulesoft.connector.roostify.api.response.AttributesUtil.setResponseAttributes;
import static com.mulesoft.connector.roostify.internal.operation.handler.RequestCallingService.sendAsyncRequest;
import static com.mulesoft.connector.roostify.internal.util.Constants.*;
import static com.mulesoft.connector.roostify.internal.util.RoostifyUtil.getByteArrayDataOfMap;
import static com.mulesoft.connector.roostify.internal.util.RoostifyUtil.getStreamList;
import static com.mulesoft.connector.roostify.internal.util.Urls.WEBHOOKS;

public class WebhookServiceImpl extends DefaultConnectorService<RoostifyConfiguration, RoostifyConnection> implements WebhookService {

		public WebhookServiceImpl(RoostifyConfiguration configuration, RoostifyConnection connection) {
			super(configuration, connection);
		}

		@Override
		public Result<InputStream, ResponseStatus> listingWebhooks(String page, String count){
			String strUri = getConfig().getAddress() + getConfig().getVersion() + WEBHOOKS ;
			HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(strUri)
					.addQueryParam(PAGE, page)
					.addQueryParam(COUNT, count)
					.build();
			CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
			checkErrorAsync(response);
			InputStream str = RoostifyUtil.getContentInputStream(response);
			List<Object> dto = getStreamList(str);
			webhookEventsDTO dto1 = new webhookEventsDTO();
			dto1.setWebhookEvents(dto);
			InputStream outputPayload = RoostifyUtil.getInputStreamData(dto1);

			return Result.<InputStream ,ResponseStatus>builder().output(outputPayload).attributes(setResponseAttributes(response)).build();
		}

	@Override
	public Result<InputStream, ResponseStatus> retrievingWebhook(String find_id){
		String strUri = getConfig().getAddress() + getConfig().getVersion() + WEBHOOKS + find_id;
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(strUri).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream ,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> creatingWebhook(Map<String, Object> webhookBody) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + WEBHOOKS;
		byte[] byteArray = getByteArrayDataOfMap(webhookBody);
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.POST).uri(strUri).addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.entity(new ByteArrayHttpEntity(byteArray)).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream ,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> deletingWebhook(String webhookId){
		String strUri = getConfig().getAddress() + getConfig().getVersion() + WEBHOOKS + webhookId;
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.DELETE).uri(strUri).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream ,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

}
