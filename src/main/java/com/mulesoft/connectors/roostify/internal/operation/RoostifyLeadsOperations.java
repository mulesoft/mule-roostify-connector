/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.internal.operation;

import com.mulesoft.connectors.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connectors.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connectors.roostify.internal.error.ErrorProvider;
import com.mulesoft.connectors.roostify.api.response.ResponseStatus;
import com.mulesoft.connectors.roostify.internal.services.LeadsService;
import com.mulesoft.connectors.roostify.internal.services.LeadsServiceImpl;
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

public class RoostifyLeadsOperations extends ConnectorOperations<RoostifyConfiguration, RoostifyConnection, LeadsService> {

	public RoostifyLeadsOperations() { super(LeadsServiceImpl::new); }

	/**
	 * The leads endpoint accepts parameters to create a Lead in Roostify. Each parameter must be whitelisted or it is not allowed within the system.
	 *
	 * @param configuration Roostify configuration object.
	 * @param connection   Roostify connection instance.
	 * @param createLead   The request body for creating a lead.
	 * @return Json response containing a new lead
	 */
	@DisplayName(value = "Create a Lead")
	@OutputJsonType(schema = "metadata/leadResponse")
	@Throws(ErrorProvider.class)
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream, ResponseStatus> creatingLead(@Config RoostifyConfiguration configuration, @Connection RoostifyConnection connection, @Content @InputJsonType(schema = "metadata/createLead") Map<String,Object> createLead) {
		return newExecutionBuilder(configuration, connection)
				.execute(LeadsService::creatingLead).withParam(createLead);
	}

	/**
	 * The leads endpoint accepts parameters to create a Lead in Roostify. Each parameter must be whitelisted or it is not allowed within the system.
	 *
	 * @param configuration Roostify configuration object.
	 * @param connection   Roostify connection  instance.
	 * @param leadId  ID of the lead.
	 * @return Json response containing a lead detail
	 */
	@DisplayName(value = "Retrieve a Lead")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema = "metadata/createLead")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream, ResponseStatus> getLead(@Config RoostifyConfiguration configuration, @Connection RoostifyConnection connection, String leadId) {
		return newExecutionBuilder(configuration, connection)
				.execute(LeadsService::getLead).withParam(leadId);
	}

}
