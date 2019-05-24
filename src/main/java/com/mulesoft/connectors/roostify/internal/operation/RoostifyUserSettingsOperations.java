/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.internal.operation;

import com.mulesoft.connectors.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connectors.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connectors.roostify.internal.error.ErrorProvider;
import com.mulesoft.connectors.roostify.api.response.ResponseStatus;
import com.mulesoft.connectors.roostify.internal.services.UserSettingsService;
import com.mulesoft.connectors.roostify.internal.services.UserSettingsServiceImpl;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;

public class RoostifyUserSettingsOperations extends ConnectorOperations<RoostifyConfiguration, RoostifyConnection, UserSettingsService> {

    public RoostifyUserSettingsOperations() {
        super(UserSettingsServiceImpl::new);
    }

    /**
     * Retrieving settings for a particular user also returns the account settings for the account they are on. This reduces complexity by allowing you to make a single request for all a particular users settings.

     * @param configuration Roostify Configuration Object.
     * @param connection   Roostify connection  instance.
     * @param id    Id of the user whose settings you would like to retrieve
     * @return Json response containing user settings
     */
    @DisplayName(value = "Retrieve a User Settings")
    @OutputJsonType(schema= "metadata/retrieveUserSettings")
    @Throws(ErrorProvider.class)
    @MediaType(value = javax.ws.rs.core.MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream, ResponseStatus> userSettings(@Config RoostifyConfiguration configuration, @Connection RoostifyConnection connection, String id) {
        return newExecutionBuilder(configuration, connection)
                .execute(UserSettingsService::userSettings).withParam(id);
    }
}
