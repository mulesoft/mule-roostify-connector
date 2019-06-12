/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.internal.services;

import com.mulesoft.connectors.roostify.api.accounts.AccountsDTO;
import com.mulesoft.connectors.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connectors.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connectors.roostify.api.response.ResponseStatus;
import com.mulesoft.connectors.roostify.internal.util.RoostifyUtil;
import com.mulesoft.connectors.roostify.internal.error.exception.ExceptionHandler;
import org.mule.connectors.commons.template.service.DefaultConnectorService;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.mulesoft.connectors.roostify.api.response.AttributesUtil.setResponseAttributes;
import static com.mulesoft.connectors.roostify.internal.operation.handler.RequestCallingService.sendAsyncRequest;
import static com.mulesoft.connectors.roostify.internal.util.Constants.*;
import static com.mulesoft.connectors.roostify.internal.util.RoostifyUtil.getStreamList;
import static com.mulesoft.connectors.roostify.internal.util.Urls.*;

public class AccountsServiceImpl extends DefaultConnectorService<RoostifyConfiguration, RoostifyConnection> implements AccountsService {

	public AccountsServiceImpl(RoostifyConfiguration config, RoostifyConnection connection) {
		super(config, connection);
	}

	@Override
	public Result<InputStream, ResponseStatus> listingAccounts(String page,String count) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + ACCOUNTS;

		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(strUri)
				.addQueryParam(PAGE, page)
				.addQueryParam(COUNT, count)
				.build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		List<Object> dtos = getStreamList(str);
		AccountsDTO dto1 = new AccountsDTO();
		dto1.setAccounts(dtos);
		InputStream outputPayload = RoostifyUtil.getInputStreamData(dto1);
		return Result.<InputStream , ResponseStatus>builder().output(outputPayload).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> retrievingAccount(String id) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + ACCOUNTS + id;

		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(strUri).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream , ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}



}
