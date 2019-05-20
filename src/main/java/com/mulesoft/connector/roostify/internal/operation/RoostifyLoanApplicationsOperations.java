/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.operation;

import com.mulesoft.connector.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connector.roostify.api.resultObject.ResponseStatus;
import com.mulesoft.connector.roostify.internal.services.LoanApplicationService;
import com.mulesoft.connector.roostify.internal.services.LoanApplicationServiceImpl;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.InputJsonType;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.runtime.operation.Result;
import com.mulesoft.connector.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connector.roostify.internal.error.ErrorProvider;

import java.io.InputStream;
import java.util.Map;

public class RoostifyLoanApplicationsOperations extends ConnectorOperations<RoostifyConfiguration, RoostifyConnection, LoanApplicationService> {

	public RoostifyLoanApplicationsOperations() {
		super(LoanApplicationServiceImpl::new);
	}

	/**
	 * This retrieves all loan applications belonging to your account or its descendants. For clients with many loan applications, this may time out if you do not use pagination.
	 *
	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection instance.
	 * @param search    Only lists loan applications which match the given attributes. Param structure is search[attribute]=...
	 * @param page    Indicates the page of the index which should be returned. When this parameter is not present and count is, it defaults to 1. When both parameters are not present pagination is disabled.
	 * @param count    Indicates the number of records per page returned. When this parameter is not present and page is, it defaults to 100. When both parameters are not present pagination is disabled.
	 * @param created    Filters loan applications based on when loan applications are created. The string must be formatted as two ISO-8601 datestamps separated by '...'. If the time zone designator is omitted it defaults to UTC.
	 * @param submitted    Filters loan applications based on when loan applications are submitted. The string must be formatted as two ISO-8601 datestamps separated by '...'. If the time zone designator is omitted it defaults to UTC.
	 * @param updated    Filters loan applications based on when loan applications are updated. The string must be formatted as two ISO-8601 datestamps separated by '...'. If the time zone designator is omitted it defaults to UTC.
	 */
	@DisplayName(value = "List Loan Applications")
	@OutputJsonType(schema= "metadata/listingLoanApplications")
	@Throws({ErrorProvider.class})
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream, ResponseStatus> listingLoanApplications(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection, @Optional @Summary("refernceId") String search, @Optional String page, @Optional String count, @Optional String created, @Optional String submitted, @Optional String updated) {
		return newExecutionBuilder(configuration, connection)
				.execute(LoanApplicationService::listingLoanApplications).withParam(page).withParam(search).withParam(count).withParam(created).withParam(updated).withParam(submitted);
	}

	/**
	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection instance.
	 * @param find_id    ID of the loan application to retrieve.
	 */
	@DisplayName(value = "Retrieve a Loan Application")
	@OutputJsonType(schema = "metadata/retrieveLoanApplication")
	@Throws(ErrorProvider.class)
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> loanApplicationsId(@Config RoostifyConfiguration configuration,@Connection  RoostifyConnection connection, String find_id) {
		return newExecutionBuilder(configuration, connection)
				.execute(LoanApplicationService::loanApplicationsId).withParam(find_id);
		}

	/**
	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection instance.
	 * @param referenceId  ID of the loan application to retrieve.
	 */
	@DisplayName(value = "Retrieve a Loan Application by Reference ID")
	@OutputJsonType(schema = "metadata/retrieveLoanApplication")
	@Throws(ErrorProvider.class)
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> loanApplicationByReferenceId(@Config RoostifyConfiguration configuration,@Connection  RoostifyConnection connection, String referenceId) {
		return newExecutionBuilder(configuration, connection)
				.execute(LoanApplicationService::loanApplicationByReferenceId).withParam(referenceId);
	}

	/**
	 * Unlike most of the API, the loan applications FNM endpoint returns plaintext instead of JSON. The string returned is a Base64-encoded Fannie Mae file.

	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection instance.
	 * @param id    ID of the loan application to retrieve.
	 */
	@DisplayName(value = "Retrieve a Loan Application in FNM Format")
	@Throws(ErrorProvider.class)
	@MediaType(value = MediaType.TEXT_PLAIN, strict = false)
	public  Result<InputStream,ResponseStatus>  loanApplicationsFNM(@Config RoostifyConfiguration configuration,@Connection  RoostifyConnection connection, String id) {
		return newExecutionBuilder(configuration, connection)
				.execute(LoanApplicationService::loanApplicationsFNM).withParam(id);
	}

	/**
	 * The ability to download a loan application in MISMO format must be requested from your Partner or Client Success Manager.

	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection  instance.
	 * @param id    ID of the loan application to retrieve.
	 */
	@DisplayName(value = "Retrieve a Loan Application in MISMO Format")
	@Throws(ErrorProvider.class)
	@MediaType(value = MediaType.APPLICATION_XML, strict = false)
	public  Result<InputStream,ResponseStatus>  loanApplicationsMISMO(@Config RoostifyConfiguration configuration,@Connection  RoostifyConnection connection, String id) {
		return newExecutionBuilder(configuration, connection)
				.execute(LoanApplicationService::loanApplicationsMISMO).withParam(id);
	}

	/**
	 * Creating a loan application must be granted on a service by service basis. Access is denied by default. A Loan Application may be created with any of the attributes listed above as parameters to the API.

	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection instance.
	 * @param createLoanRequest Create loan request body.
	 */
	@DisplayName(value = "Create a Loan Application")
	@OutputJsonType(schema= "metadata/createLoanApplication_response")
	@Throws(ErrorProvider.class)
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> creatingLoanApplication(@Config RoostifyConfiguration configuration,@Connection  RoostifyConnection connection, @Content @InputJsonType(schema= "metadata/createLoanApplication_request") Map<String, Object> createLoanRequest) {
		return newExecutionBuilder(configuration, connection)
				.execute(LoanApplicationService::creatingLoanApplication).withParam(createLoanRequest);
	}

	/**
	 * Updating a loan application must be granted on a service by service basis. Access is denied by default.

	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection  instance.
	 * @param update_id    ID of the loan application to update.
	 * @param updateLoanRequest Request body for updating loan application.
	 */
	@DisplayName(value = "Update a Loan Application")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema= "metadata/updateLoanApplication_response")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> updatingLoanApplication(@Config RoostifyConfiguration configuration,@Connection  RoostifyConnection connection, String update_id, @Content @InputJsonType(schema= "metadata/updateLoanApplication_request") Map<String, Object> updateLoanRequest) {
		return newExecutionBuilder(configuration, connection)
				.execute(LoanApplicationService::updatingLoanApplication).withParam(update_id).withParam(updateLoanRequest);
	}
}
