/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.internal.services;

import com.mulesoft.connectors.roostify.api.response.ResponseStatus;
import org.mule.connectors.commons.template.service.ConnectorService;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

public interface UserManagementService extends ConnectorService {

	Result<InputStream, ResponseStatus> listingUsers(String page,String count,String search);
	Result<InputStream, ResponseStatus> retrievingUser(String userId);
	Result<InputStream, ResponseStatus> createUser(Map<String,Object> userBody);
	Result<InputStream, ResponseStatus> updateUser(Map<String,Object> User, String id);
	Result<InputStream, ResponseStatus> cancelOrder(String id);
	Result<InputStream, ResponseStatus> activateUser(String id);
	}
