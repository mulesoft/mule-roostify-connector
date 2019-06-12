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
import org.mule.runtime.extension.api.annotation.param.*;
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
import static com.mulesoft.connectors.roostify.internal.util.Constants.*;
import static com.mulesoft.connectors.roostify.internal.util.RoostifyUtil.getByteArrayDataOfMap;
import static com.mulesoft.connectors.roostify.internal.util.Urls.*;

public class UserManagementServiceImpl extends DefaultConnectorService<RoostifyConfiguration, RoostifyConnection> implements UserManagementService {

	public UserManagementServiceImpl(RoostifyConfiguration config, RoostifyConnection connection) {
		super(config, connection);
	}

	@Override
	public Result<InputStream, ResponseStatus> listingUsers(String page,String count,String search) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + USERS;
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(strUri)
				.addQueryParam(PAGE, page)
				.addQueryParam(COUNT, count)
				.addQueryParam(SEARCH, search)
				.build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream , ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> retrievingUser(String userId) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + USERS + userId;

		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(strUri).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream , ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> createUser(Map<String,Object> userBody){
		String strUri = getConfig().getAddress() + getConfig().getVersion() + USERS;
		byte[] byteArray = getByteArrayDataOfMap(userBody);

		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.POST).uri(strUri).addHeader(CONTENT_TYPE,MediaType.APPLICATION_JSON).entity(new ByteArrayHttpEntity(byteArray)).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream ,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> updateUser(Map<String,Object> User, String id){
		String strUri = getConfig().getAddress() + getConfig().getVersion() + USERS + id;
		byte[] byteArray = getByteArrayDataOfMap(User);
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.PATCH).uri(strUri).addHeader(CONTENT_TYPE,MediaType.APPLICATION_JSON).entity(new ByteArrayHttpEntity(byteArray)).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream ,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> cancelOrder(String id){
		String strUri = getConfig().getAddress() + getConfig().getVersion() + USERS + id +LOCK;
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.DELETE).uri(strUri).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream ,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> activateUser(String id){
		String strUri = getConfig().getAddress() + getConfig().getVersion() + USERS + id + UNLOCK;
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.POST).uri(strUri).addHeader(CONTENT_TYPE,MediaType.APPLICATION_JSON).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream ,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}






}
