/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.internal.services;

import com.mulesoft.connectors.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connectors.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connectors.roostify.api.response.ResponseStatus;
import com.mulesoft.connectors.roostify.internal.util.RoostifyUtil;
import com.mulesoft.connectors.roostify.internal.error.exception.ExceptionHandler;
import org.mule.connectors.commons.template.service.DefaultConnectorService;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.domain.entity.ByteArrayHttpEntity;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.mulesoft.connectors.roostify.api.response.AttributesUtil.setResponseAttributes;
import static com.mulesoft.connectors.roostify.internal.operation.handler.RequestCallingService.sendAsyncRequest;
import static com.mulesoft.connectors.roostify.internal.util.Constants.CONTENT_TYPE;
import static com.mulesoft.connectors.roostify.internal.util.RoostifyUtil.getByteArrayDataOfMap;
import static com.mulesoft.connectors.roostify.internal.util.Urls.*;

public class LeadsServiceImpl extends DefaultConnectorService<RoostifyConfiguration, RoostifyConnection> implements LeadsService {

	public LeadsServiceImpl(RoostifyConfiguration configuration, RoostifyConnection connection) {
		super(configuration, connection);
	}

	@Override
	public Result<InputStream, ResponseStatus> creatingLead(Map<String,Object> createLead){
		String strUri = getConfig().getAddress() + DRAFT + LEADS;
		byte[] byteArray = getByteArrayDataOfMap(createLead);
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.POST).uri(strUri).addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON).entity(new ByteArrayHttpEntity(byteArray)).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream ,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> getLead(String leadId){
		String strUri = getConfig().getAddress() + DRAFT + LEADS + leadId;
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(strUri).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream ,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

}
