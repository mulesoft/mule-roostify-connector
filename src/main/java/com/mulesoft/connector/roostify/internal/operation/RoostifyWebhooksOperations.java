/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.operation;

import com.mulesoft.connector.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connector.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connector.roostify.internal.error.ErrorProvider;
import com.mulesoft.connector.roostify.api.resultObject.ResponseStatus;
import com.mulesoft.connector.roostify.internal.services.WebhookService;
import com.mulesoft.connector.roostify.internal.services.WebhookServiceImpl;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.InputJsonType;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

public class RoostifyWebhooksOperations extends ConnectorOperations<RoostifyConfiguration, RoostifyConnection, WebhookService> {

	public RoostifyWebhooksOperations() { super(WebhookServiceImpl::new); }

	/**
	 * @param configuration Roostify configuration object.
	 * @param connection   Roostify connection  instance.
	 * @param page    Indicates the page of the index which should be returned. When this parameter is not present and count is, it defaults to 1. When both parameters are not present pagination is disabled.
	 * @param count    Indicates the number of records per page returned. When this parameter is not present and page is, it defaults to 100. When both parameters are not present pagination is disabled.
	 */
	@DisplayName(value = "Listing your webhooks")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema= "metadata/listingWebhooks")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream, ResponseStatus> listingWebhooks(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection, @Optional String page, @Optional String count) {
		return newExecutionBuilder(configuration, connection)
				.execute(WebhookService::listingWebhooks).withParam(page).withParam(count);
	}

	/**
	 * @param configuration Roostify configuration object.
	 * @param connection   Roostify connection  instance.
	 * @param webhookId    ID of the webhook to retrieve.
	 */
	@DisplayName(value = "Retrieve a webhook")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema= "metadata/retrieveWebhook")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> retrievingWebhook(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection, String webhookId) {
		return newExecutionBuilder(configuration, connection)
				.execute(WebhookService::retrievingWebhook).withParam(webhookId);
	}

	/**
	 * @param configuration Roostify configuration object.
	 * @param connection   Roostify connection  instance.
	 */
	@DisplayName(value = "Create a webhook")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema= "metadata/createWebhook_response")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> creatingWebhook(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection, @Content @InputJsonType(schema= "metadata/createWebhook_request") Map<String, Object> webhookBody) {
		return newExecutionBuilder(configuration, connection)
				.execute(WebhookService::creatingWebhook).withParam(webhookBody);
	}

	/**
	 * @param configuration Roostify configuration object.
	 * @param connection   Roostify connection  instance.
	 * @param webhookId    ID of the webhook to destroy.
	 */
	@DisplayName(value = "Delete a webhook")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema= "metadata/deleteWebhook_response")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> deletingWebhook(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection, String webhookId) {
		return newExecutionBuilder(configuration, connection)
				.execute(WebhookService::deletingWebhook).withParam(webhookId);
	}
}
