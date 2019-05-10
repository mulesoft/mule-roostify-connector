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

public class RoostifyAccessControlOperations extends ConnectorOperations<RoostifyConfiguration, RoostifyConnection, AccessControlService> {

    public RoostifyAccessControlOperations() {
        super(AccessControlServiceImpl::new);
    }

    /**
     * @param configuration Roostify Configuration Object.
     * @param connection   Roostify connection  instance.
     * @param id    ID or system name of the task. If searching for an id matching the string given yields no results, then a lookup against system name is attempted.
     */
    @DisplayName(value = "Update an access control group")
    @OutputJsonType(schema= "metadata/updateAccessControl_response")
    @Throws({ErrorProvider.class})
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream, ResponseStatus> UpdateAccessControl(@Config RoostifyConfiguration configuration, @Connection RoostifyConnection connection, @Content @InputJsonType(schema= "metadata/updateAccessControl_request") Map<String,Object> accessControlGroup, String id) {
        return newExecutionBuilder(configuration, connection)
                .execute(AccessControlService::UpdateAccessControl).withParam(accessControlGroup).withParam(id);
    }

    /**
     * @param configuration Roostify Configuration Object.
     * @param connection  Roostify connection  instance.
     */
    @DisplayName(value = "Create an access control group")
    @OutputJsonType(schema= "metadata/createAccessControl_response")
    @Throws({ErrorProvider.class})
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream,ResponseStatus> CreateAccessControl(@Config RoostifyConfiguration configuration,@Connection RoostifyConnection connection, @Content @InputJsonType(schema= "metadata/createAccessControl_request") Map<String,Object> accessControl) {
        return newExecutionBuilder(configuration, connection)
                .execute(AccessControlService::CreateAccessControl).withParam(accessControl);
    }

}
