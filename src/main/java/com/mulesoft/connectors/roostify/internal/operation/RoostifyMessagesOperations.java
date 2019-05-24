/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.internal.operation;

import com.mulesoft.connectors.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connectors.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connectors.roostify.internal.error.ErrorProvider;
import com.mulesoft.connectors.roostify.api.response.ResponseStatus;
import com.mulesoft.connectors.roostify.internal.services.MessageService;
import com.mulesoft.connectors.roostify.internal.services.MessageServiceImpl;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.InputJsonType;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

public class RoostifyMessagesOperations extends ConnectorOperations<RoostifyConfiguration, RoostifyConnection, MessageService> {

	public RoostifyMessagesOperations() {
		super(MessageServiceImpl::new);
	}

	/**
	 * @param configuration Roostify configuration object.
	 * @param connection   Roostify connection instance.
	 * @return Json response containing list of all messages
	 */
	@DisplayName(value = "List Messages")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema= "metadata/listingMessages")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream, ResponseStatus> getMessages(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection) {
		return newExecutionBuilder(configuration, connection)
				.execute(MessageService::getMessages);
	}

	/**
	 * @param configuration Roostify configuration object.
	 * @param connection   Roostify connection instance.
	 * @param messageId    ID of the message to retrieve.
	 * @return Json response containing a message
	 */
	@DisplayName(value = "Retrieve a Message")
	@OutputJsonType(schema = "metadata/retrieveMessage")
	@Throws({ErrorProvider.class})
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> retrievingMessage(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection, String messageId) {
		return newExecutionBuilder(configuration, connection)
				.execute(MessageService::retrievingMessage).withParam(messageId);
	}

	/**
	 * When the accounts email and name are filled in, the from address for messages created from this endpoint uses the account name and email address.
	 * Otherwise, emails are sent from an email address similar to no-reply@deliver.roostify.com.

	 * @param configuration Roostify configuration object.
	 * @param connection   Roostify connection instance.
	 * @param message Message request body for creating message.
	 * @return Json response containing a new message
	 */
	@DisplayName(value = "Create a Message")
	@Throws({ErrorProvider.class})
	@OutputJsonType(schema= "metadata/createMessage_response")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> creatingMessage(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection,
	                                                          @Content @InputJsonType(schema= "metadata/createMessage_request") Map<String,Object> message) {
		return newExecutionBuilder(configuration, connection)
				.execute(MessageService::creatingMessage).withParam(message);
	}

}
