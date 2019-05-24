/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.internal.operation;

import com.mulesoft.connectors.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connectors.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connectors.roostify.internal.error.ErrorProvider;
import com.mulesoft.connectors.roostify.api.response.ResponseStatus;
import com.mulesoft.connectors.roostify.internal.services.AccessControlService;
import com.mulesoft.connectors.roostify.internal.services.AccessControlServiceImpl;
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
     * @param accessControlGroup Update access control request body
     * @return Json response containing updated access control group
     */
    @DisplayName(value = "Update an Access Control Group")
    @OutputJsonType(schema= "metadata/updateAccessControl_response")
    @Throws({ErrorProvider.class})
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream, ResponseStatus> updateAccessControl(@Config RoostifyConfiguration configuration, @Connection RoostifyConnection connection, @Content @InputJsonType(schema= "metadata/updateAccessControl_request") Map<String,Object> accessControlGroup, String id) {
        return newExecutionBuilder(configuration, connection)
                .execute(AccessControlService::updateAccessControl).withParam(accessControlGroup).withParam(id);
    }

    /**
     * @param configuration Roostify Configuration Object.
     * @param connection  Roostify connection  instance.
     * @param accessControl Create access control request body
     * @return Json response containing new access control group
     */
    @DisplayName(value = "Create an Access Control Group")
    @OutputJsonType(schema= "metadata/createAccessControl_response")
    @Throws({ErrorProvider.class})
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream,ResponseStatus> createAccessControl(@Config RoostifyConfiguration configuration,@Connection RoostifyConnection connection, @Content @InputJsonType(schema= "metadata/createAccessControl_request") Map<String,Object> accessControl) {
        return newExecutionBuilder(configuration, connection)
                .execute(AccessControlService::createAccessControl).withParam(accessControl);
    }

}
