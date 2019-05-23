/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.operation;

import com.mulesoft.connector.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connector.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connector.roostify.internal.error.ErrorProvider;
import com.mulesoft.connector.roostify.api.response.ResponseStatus;
import com.mulesoft.connector.roostify.internal.services.WebhookEventsService;
import com.mulesoft.connector.roostify.internal.services.WebhookEventsServiceImpl;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;

public class RoostifyWebhookEventsOperations extends ConnectorOperations<RoostifyConfiguration, RoostifyConnection, WebhookEventsService> {

	public RoostifyWebhookEventsOperations() { super(WebhookEventsServiceImpl::new); }

	/**
	 * Webhook events record every instance in which a webhook was fired for a particular event, and can act as an audit log for all events that occur that are trackable via webhooks.
	 *
	 * @param configuration Roostify configuration object.
	 * @param connection   Roostify connection instance.
	 * @param page    Indicates the page of the index which should be returned. When this parameter is not present and count is, it defaults to 1. When both parameters are not present pagination is disabled.
	 * @param count    Indicates the number of records per page returned. When this parameter is not present and page is, it defaults to 100. When both parameters are not present pagination is disabled.
	 * @param created    ISO 8601 DateTime range. Scope the returned results to only webhook events that were created this range of dates.
	 */
	@DisplayName(value = "Listing Webhook Events")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema= "metadata/listingWebhookEvents")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream, ResponseStatus> listingWebhookEvents(@Config RoostifyConfiguration configuration, @Connection RoostifyConnection connection, @Optional String page, @Optional String count, @Optional String created) {
		return newExecutionBuilder(configuration, connection)
				.execute(WebhookEventsService::listingWebhookEvents).withParam(page).withParam(count).withParam(created);
	}

}
