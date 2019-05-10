/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.operation;

import com.mulesoft.connector.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connector.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connector.roostify.internal.error.ErrorProvider;
import com.mulesoft.connector.roostify.api.resultObject.ResponseStatus;
import com.mulesoft.connector.roostify.internal.services.ReferenceIdService;
import com.mulesoft.connector.roostify.internal.services.ReferenceIdServiceImpl;
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

public class RoostifyReferenceIdOperations extends ConnectorOperations<RoostifyConfiguration, RoostifyConnection, ReferenceIdService> {

		public RoostifyReferenceIdOperations() {
			super(ReferenceIdServiceImpl::new);
		}
	/**
	 * Reference IDs allow users to work with an external service or integration. Using this route either creates or updates a reference id.

	 * @param configuration Roostify configuration object.
	 * @param connection   Roostify connection  instance.
	 * @param loan_id    ID of the loan application to update.
	 * @param referenceBody The request body for updating the loan
	 */
	@DisplayName(value = "Creating or updating a reference id")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema= "metadata/outputReferenceId")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream, ResponseStatus> createUpdateReferenceId(@Config RoostifyConfiguration configuration, @Connection RoostifyConnection connection, String loan_id, @Content @InputJsonType(schema= "metadata/createOrUpdateReferenceId") Map<String, Object> referenceBody) {
		return newExecutionBuilder(configuration, connection)
				.execute(ReferenceIdService::createUpdateReferenceId).withParam(loan_id).withParam(referenceBody);
	}
}
