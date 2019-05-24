/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.internal.services;

import com.mulesoft.connectors.roostify.api.tasks.TaskDTO;
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
import static com.mulesoft.connectors.roostify.internal.util.Urls.TASKS;

public class TaskServiceImpl extends DefaultConnectorService<RoostifyConfiguration, RoostifyConnection> implements TaskService {

	public TaskServiceImpl(RoostifyConfiguration configuration, RoostifyConnection connection) {
		super(configuration, connection);
	}

	@Override
	public Result<InputStream, ResponseStatus> listingTasks(String loan_application_id, String page, String count) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + TASKS;
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(strUri)
				.addQueryParam(LOAN_APPLICATION_ID, loan_application_id)
				.addQueryParam(PAGE, page)
				.addQueryParam(COUNT, count)
				.build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request, true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		List<Object> dtos = getStreamList(str);
		TaskDTO dto1 = new TaskDTO();
		dto1.setList(dtos);
		InputStream outputPayload = RoostifyUtil.getInputStreamData(dto1);

		return Result.<InputStream, ResponseStatus>builder().output(outputPayload).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> retrievingATask(String taskId) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + TASKS + taskId;
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(strUri).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request, true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream, ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> updateTask(Map<String, Object> taskBody, String taskId) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + TASKS + taskId;
		byte[] byteArray = getByteArrayDataOfMap(taskBody);
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.PATCH).uri(strUri).addHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON).entity(new ByteArrayHttpEntity(byteArray)).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request, true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream, ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> creatingTaskFromForm(Map<String, Object> formTask) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + TASKS;
		byte[] byteArray = getByteArrayDataOfMap(formTask);
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.POST).uri(strUri).addHeader(CONTENT_TYPE,MediaType.APPLICATION_JSON).entity(new ByteArrayHttpEntity(byteArray)).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request, true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream, ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> creatingTaskFromApproval(Map<String, Object> approvalTask) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + TASKS;
		byte[] byteArray = getByteArrayDataOfMap(approvalTask);
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.POST).uri(strUri).addHeader(CONTENT_TYPE,MediaType.APPLICATION_JSON).entity(new ByteArrayHttpEntity(byteArray)).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request, true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream, ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> creatingViewOnlyTask(Map<String, Object> viewOnlyTask) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + TASKS;
		byte[] byteArray = getByteArrayDataOfMap(viewOnlyTask);
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.POST).uri(strUri).addHeader(CONTENT_TYPE,MediaType.APPLICATION_JSON).entity(new ByteArrayHttpEntity(byteArray)).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request, true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream, ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

	@Override
	public Result<InputStream, ResponseStatus> creatingRegularTask(Map<String, Object> regularTask) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + TASKS;
		byte[] byteArray = getByteArrayDataOfMap(regularTask);
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.POST).uri(strUri).addHeader(CONTENT_TYPE,MediaType.APPLICATION_JSON).entity(new ByteArrayHttpEntity(byteArray)).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request, true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream, ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}
	@Override
	public Result<InputStream, ResponseStatus> creatingSpeedBumpTask(Map<String, Object> speedBumpTask) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + TASKS;
		byte[] byteArray = getByteArrayDataOfMap(speedBumpTask);
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.POST).uri(strUri).addHeader(CONTENT_TYPE,MediaType.APPLICATION_JSON).entity(new ByteArrayHttpEntity(byteArray)).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request, true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream, ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}
	@Override
	public Result<InputStream, ResponseStatus> creatingFileContentTask(Map<String, Object> fileContentTask) {
		String strUri = getConfig().getAddress() + getConfig().getVersion() + TASKS;
		byte[] byteArray = getByteArrayDataOfMap(fileContentTask);
		HttpRequest request = getConnection().getHttpRequestBuilder().method(HttpConstants.Method.POST).uri(strUri).addHeader(CONTENT_TYPE,MediaType.APPLICATION_JSON).entity(new ByteArrayHttpEntity(byteArray)).build();
		CompletableFuture<HttpResponse> response = sendAsyncRequest(request, true, getConnection());
		ExceptionHandler.checkErrorAsync(response);
		InputStream str = RoostifyUtil.getContentInputStream(response);
		return Result.<InputStream, ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
	}

}
