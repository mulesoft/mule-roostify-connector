/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.services;

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
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.mulesoft.connector.roostify.internal.exception.ExceptionHandler.checkErrorAsync;
import static com.mulesoft.connector.roostify.api.response.AttributesUtil.setResponseAttributes;
import static com.mulesoft.connector.roostify.internal.operation.handler.RequestCallingService.sendAsyncRequest;
import static com.mulesoft.connector.roostify.internal.util.Constants.*;
import static com.mulesoft.connector.roostify.internal.util.RoostifyUtil.getByteArrayDataOfMap;
import static com.mulesoft.connector.roostify.internal.util.Urls.ACCESS_CONTROL_GROUPS;

public class AccessControlServiceImpl extends DefaultConnectorService<RoostifyConfiguration, RoostifyConnection> implements AccessControlService {

	public AccessControlServiceImpl(RoostifyConfiguration config, RoostifyConnection connection) {
		super(config, connection);
	}

	@Override
	public Result<InputStream, ResponseStatus> updateAccessControl(Map<String,Object> accessControlGroup, String id) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + ACCESS_CONTROL_GROUPS +id;
		byte[] byteArray = getByteArrayDataOfMap(accessControlGroup);
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.PUT).uri(strUri).addHeader(CONTENT_TYPE,MediaType.APPLICATION_JSON).entity(new ByteArrayHttpEntity(byteArray))
				.build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream , ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}


	@Override
	public Result<InputStream, ResponseStatus> createAccessControl(Map<String,Object> accessControl) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + ACCESS_CONTROL_GROUPS;
		byte[] byteArray = getByteArrayDataOfMap(accessControl);

		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.POST).uri(strUri).addHeader(CONTENT_TYPE,MediaType.APPLICATION_JSON).entity(new ByteArrayHttpEntity(byteArray))
				.build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream , ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}


}
