/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.internal.services;

import com.mulesoft.connectors.roostify.api.loans.LoanDTO;
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
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.mulesoft.connectors.roostify.api.response.AttributesUtil.setResponseAttributes;
import static com.mulesoft.connectors.roostify.internal.operation.handler.RequestCallingService.sendAsyncRequest;
import static com.mulesoft.connectors.roostify.internal.util.Constants.*;
import static com.mulesoft.connectors.roostify.internal.util.RoostifyUtil.getByteArrayDataOfMap;
import static com.mulesoft.connectors.roostify.internal.util.RoostifyUtil.getStreamList;
import static com.mulesoft.connectors.roostify.internal.util.Urls.*;

public class LoanApplicationServiceImpl extends DefaultConnectorService< RoostifyConfiguration, RoostifyConnection> implements LoanApplicationService {

	public LoanApplicationServiceImpl(RoostifyConfiguration config, RoostifyConnection connection) {
		super(config, connection);
	}

	@Override
	public Result<InputStream, ResponseStatus> listingLoanApplications(String search, String page, String count, String created, String submitted, String updated) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + LOAN_APPLICATIONS;
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(strUri)
				.addQueryParam(PAGE, page)
				.addQueryParam(COUNT, count)
				.addQueryParam(CREATED, created)
				.addQueryParam(SUBMITTED, submitted)
				.addQueryParam(UPDATED, updated).addQueryParam(SEARCHLOAN,search)
				.build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		List<Object> dtos = getStreamList(str);
		LoanDTO dto1 = new LoanDTO();
		dto1.setLoanApplications(dtos);
		InputStream inputPayload = RoostifyUtil.getInputStreamData(dto1);
		return Result.<InputStream ,ResponseStatus>builder().output(inputPayload).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> loanApplicationsId(String find_id) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + LOAN_APPLICATIONS + find_id;
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(strUri).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream ,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> loanApplicationByReferenceId(String referenceId) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + LOAN_APPLICATIONS;
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(strUri)
				.addQueryParam(SEARCH_REFERENCE_ID, referenceId).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream ,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> loanApplicationsFNM(String id){
		String strUri = getConfig().getAddress() + getConfig().getVersion() + LOAN_APPLICATIONS + id + FNM;
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(strUri).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream ,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> loanApplicationsMISMO(String id){
		String strUri = getConfig().getAddress() + getConfig().getVersion() + LOAN_APPLICATIONS + id + MISMO;
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(strUri).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream ,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> creatingLoanApplication(Map<String, Object> createLoanRequest){
		String strUri = getConfig().getAddress() + getConfig().getVersion() + LOAN_APPLICATIONS;
		byte[] byteArray = getByteArrayDataOfMap(createLoanRequest);
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.POST).uri(strUri).addHeader(CONTENT_TYPE,MediaType.APPLICATION_JSON)
				.entity(new ByteArrayHttpEntity(byteArray)).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,false, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream ,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> updatingLoanApplication(String update_id, Map<String, Object> updateLoanRequest){
		String strUri = getConfig().getAddress() + getConfig().getVersion() + LOAN_APPLICATIONS + update_id;
		byte[] byteArray = getByteArrayDataOfMap(updateLoanRequest);
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.PATCH).uri(strUri).addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON).entity(new ByteArrayHttpEntity(byteArray)).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request,true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream ,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}


}
