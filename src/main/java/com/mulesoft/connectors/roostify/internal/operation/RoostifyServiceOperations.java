/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.internal.operation;

import com.mulesoft.connectors.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connectors.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connectors.roostify.internal.error.ErrorProvider;
import com.mulesoft.connectors.roostify.api.response.ResponseStatus;
import com.mulesoft.connectors.roostify.internal.services.SerivesService;
import com.mulesoft.connectors.roostify.internal.services.ServicesServiceImpl;
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
     * Applications built on Roostify often need to have configuration or other kinds of data tied to a particular user or account. For instance, if building a product to synchronize loan applications from Roostify to a
     * loan origination system, you may want to have lenders provide their username for the loan origination system so you can properly assign them. These are considered User Settings.
     * Account Settings are for data that is shared across a collection of users on an account. Lenders and account admins may set the values for these settings through the Roostify UI.
     *
     * @param configuration Roostify Configuration Object.
     * @param connection   Roostify connection  instance.
     * @return Json response containing a service
     */
    @DisplayName(value = "Retrieve a Service")
        @OutputJsonType(schema= "metadata/retrieveService")
        @Throws({ErrorProvider.class})
        @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
        public Result<InputStream, ResponseStatus> retrieveYourService(@Config RoostifyConfiguration configuration, @Connection RoostifyConnection connection) {
            return newExecutionBuilder(configuration, connection)
                    .execute(SerivesService::retrieveYourService);
        }
    }



