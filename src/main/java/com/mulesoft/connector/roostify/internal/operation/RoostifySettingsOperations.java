/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.operation;

import com.mulesoft.connector.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connector.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connector.roostify.internal.error.ErrorProvider;
import com.mulesoft.connector.roostify.api.resultObject.ResponseStatus;
import com.mulesoft.connector.roostify.internal.services.SettingsService;
import com.mulesoft.connector.roostify.internal.services.SettingsServiceImpl;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;

public class RoostifySettingsOperations extends ConnectorOperations<RoostifyConfiguration, RoostifyConnection, SettingsService> {

    public RoostifySettingsOperations() {
        super(SettingsServiceImpl::new);
    }

    /**
     * @param configuration Roostify Configuration Object.
     * @param connection Roostify connection  instance.
     */
    @DisplayName(value = "Retrieve an integration service setting fields and values")
    @OutputJsonType(schema = "metadata/retrieveSettings")
    @Throws(ErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream, ResponseStatus> retrievingSettings(@Config RoostifyConfiguration configuration, @Connection RoostifyConnection connection) {
        return newExecutionBuilder(configuration, connection)
                .execute(SettingsService::retrievingSettings);
    }
}
