/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.config;

import com.mulesoft.connector.roostify.internal.operation.*;
import org.mule.connectors.commons.template.config.ConnectorConfig;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import com.mulesoft.connector.roostify.internal.connection.RoostifyConnectionForAllServices;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

@Operations({RoostifyAccessControlOperations.class, RoostifyAccountsOperations.class, RoostifyDocumentsOperations.class,
		RoostifyLoanApplicationsOperations.class, RoostifyMessagesOperations.class, RoostifyServiceOperations.class,
		RoostifySettingsOperations.class, RoostifyStatusUpdateOperations.class, RoostifyTasksOperations.class,
		RoostifyUserManagementOperations.class, RoostifyUserSettingsOperations.class, RoostifyReferenceIdOperations.class,
		RoostifyWebhookEventsOperations.class, RoostifyWebhooksOperations.class, RoostifyLeadsOperations.class})
@ConnectionProviders(RoostifyConnectionForAllServices.class)
public class RoostifyConfiguration implements ConnectorConfig {

	@Parameter
	protected static String address;
	@Parameter
	protected static String version;

	public String getAddress() {
		return address;
	}

	public String getVersion() {
		return version;
	}

	public static String getAddressValue(){
		return address;
	}

	public static String getVersionValue(){
		return version;
	}
}
