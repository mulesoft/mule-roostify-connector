/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.internal.operation;

import com.mulesoft.connectors.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connectors.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connectors.roostify.internal.error.ErrorProvider;
import com.mulesoft.connectors.roostify.api.response.ResponseStatus;
import com.mulesoft.connectors.roostify.internal.services.UserManagementService;
import com.mulesoft.connectors.roostify.internal.services.UserManagementServiceImpl;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.InputJsonType;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class RoostifyUserManagementOperations extends ConnectorOperations<RoostifyConfiguration, RoostifyConnection, UserManagementService> {

    public RoostifyUserManagementOperations() {
        super(UserManagementServiceImpl::new);
    }

    /**
     * List all users with their details.
     *
     * @param configuration Roostify Configuration Object.
     * @param connection   Roostify connection instance.
     * @param search    Only lists users that match the given attributes. Param structure is search[attribute]=... Takes a url encoded hash of search parameters.
     * @param page    Indicates the page of the index which should be returned. When this parameter is not present and count is, it defaults to 1. When both parameters are not present pagination is disabled.
     * @param count    Indicates the number of records per page returned. When this parameter is not present and page is, it defaults to 100. When both parameters are not present pagination is disabled.
     * @return Json response containing a list of all users
     */
    @DisplayName(value = "List Users")
    @OutputJsonType(schema= "metadata/listingUsers")
    @Throws({ErrorProvider.class})
    @MediaType(value = javax.ws.rs.core.MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream, ResponseStatus> listingUsers(@Config RoostifyConfiguration configuration, @Connection RoostifyConnection connection,
                                                            @Optional String page,
                                                            @Optional String count,
                                                            @Optional String search) {
        return newExecutionBuilder(configuration, connection)
                .execute(UserManagementService::listingUsers).withParam(page).withParam(count).withParam(search);
    }

    /**
     * Get details of a single user.
     *
     * @param configuration Roostify Configuration Object.
     * @param connection   Roostify connection  instance.
     * @param userId    ID of the user
     * @return Json response containing a user
     */
    @DisplayName(value = "Retrieve a User")
    @OutputJsonType(schema = "metadata/retrieveUser")
    @Throws({ErrorProvider.class})
    @MediaType(value = javax.ws.rs.core.MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream, ResponseStatus> retrievingUser(@Config RoostifyConfiguration configuration,@Connection RoostifyConnection connection, String userId) {
        return newExecutionBuilder(configuration, connection)
                .execute(UserManagementService::retrievingUser).withParam(userId);
    }

    /**
     * Create a user.
     *
     * @param connection   Roostify connection instance.
     * @param configuration Roostify configuration object.
     * @param userBody Roostify create user request body.
     * @return Json response containing new user
     */
    @DisplayName(value = "Create a User")
    @OutputJsonType(schema = "metadata/userDetails")
    @Throws({ErrorProvider.class})
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream, ResponseStatus> createUser(@Config RoostifyConfiguration configuration,@Connection RoostifyConnection connection, @Content @InputJsonType(schema = "metadata/createUser") Map<String,Object> userBody) {
        return newExecutionBuilder(configuration, connection)
                .execute(UserManagementService::createUser).withParam(userBody);
    }

    /**
     * @param configuration Roostify Configuration Object.
     * @param connection   Roostify connection instance.
     * @param id    User ID.
     * @param user Request body for update user
     * @return Json response containing updated user
     */
    @DisplayName(value = "Update a User")
    @Throws({ErrorProvider.class})
    @MediaType(value = ANY, strict = false)
    public Result<InputStream,ResponseStatus> updateUser(@Config RoostifyConfiguration configuration,@Connection RoostifyConnection connection, @Content @InputJsonType(schema= "metadata/updateUser_request") Map<String,Object> user, String id ) {
        return newExecutionBuilder(configuration, connection)
                .execute(UserManagementService::updateUser).withParam(user).withParam(id);
    }

    /**
     * @param configuration Roostify Configuration Object.
     * @param connection   Roostify connection instance.
     * @param id    User ID (Required).
     * @return Status code
     */
    @DisplayName(value = "Deactivate a User")
    @Throws({ErrorProvider.class})
    @MediaType(value = ANY, strict = false)
    public Result<InputStream,ResponseStatus> cancelOrder(@Config RoostifyConfiguration configuration,@Connection RoostifyConnection connection, String id) {
        return newExecutionBuilder(configuration, connection)
                .execute(UserManagementService::cancelOrder).withParam(id);
    }

    /**
     * @param configuration Roostify Configuration Object.
     * @param connection   Roostify connection instance.
     * @param id    User ID (Required).
     * @return Status code
     */
    @DisplayName(value = "Activate a User")
    @Throws({ErrorProvider.class})
    @MediaType(value = ANY, strict = false)
    public Result<InputStream,ResponseStatus> activateUser(@Config RoostifyConfiguration configuration,@Connection RoostifyConnection connection, String id) {
        return newExecutionBuilder(configuration, connection)
                .execute(UserManagementService::activateUser).withParam(id);
    }



}
