/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.operation;

import com.mulesoft.connector.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connector.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connector.roostify.internal.error.ErrorProvider;
import com.mulesoft.connector.roostify.api.resultObject.ResponseStatus;
import com.mulesoft.connector.roostify.internal.services.*;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;

public class RoostifyServiceOperations extends ConnectorOperations<RoostifyConfiguration, RoostifyConnection, SerivesService> {

    public RoostifyServiceOperations() {
        super(ServicesServiceImpl::new);
    }

        /**
         * This endpoint has no parameters.

         * @param configuration Roostify Configuration Object.
         * @param connection   Roostify connection  instance.
         */
        @DisplayName(value = "Retrieve your service")
        @OutputJsonType(schema= "metadata/retrieveService")
        @Throws({ErrorProvider.class})
        @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
        public Result<InputStream, ResponseStatus> retrieveYurService(@Config RoostifyConfiguration configuration, @Connection RoostifyConnection connection) {
            return newExecutionBuilder(configuration, connection)
                    .execute(SerivesService::retrieveYurService);
        }
    }



