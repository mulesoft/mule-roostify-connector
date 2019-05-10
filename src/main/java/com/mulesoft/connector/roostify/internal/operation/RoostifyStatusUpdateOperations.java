/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.operation;

import com.mulesoft.connector.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connector.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connector.roostify.internal.error.ErrorProvider;
import com.mulesoft.connector.roostify.api.resultObject.ResponseStatus;
import com.mulesoft.connector.roostify.internal.services.StatusUpdateService;
import com.mulesoft.connector.roostify.internal.services.StatusUpdateServiceImpl;
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

public class RoostifyStatusUpdateOperations extends ConnectorOperations<RoostifyConfiguration, RoostifyConnection, StatusUpdateService> {

    public RoostifyStatusUpdateOperations() {
        super(StatusUpdateServiceImpl::new);
    }

    /**
     * @param configuration Roostify Configuration Object.
     * @param connection   Roostify connection  instance.
     */
    @DisplayName(value = "Create a status update")
    @Throws(ErrorProvider.class)
    @OutputJsonType(schema= "metadata/createStatusUpdate_response")
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream, ResponseStatus> creatingStatusUpdate(@Config RoostifyConfiguration configuration, @Connection RoostifyConnection connection, @Content @InputJsonType(schema= "metadata/createStatusUpdate_request") Map<String,Object> status) {
        return newExecutionBuilder(configuration, connection)
                .execute(StatusUpdateService::creatingStatusUpdate).withParam(status);
    }


}
