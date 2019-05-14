/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.services;

import com.mulesoft.connector.roostify.api.resultObject.ResponseStatus;
import org.mule.connectors.commons.template.service.ConnectorService;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

public interface LoanApplicationService extends ConnectorService {

	Result<InputStream, ResponseStatus> listingLoanApplications(String search, String page,  String count, String created, String submitted, String updated);
	Result<InputStream,ResponseStatus> loanApplicationsId(String find_id);
	Result<InputStream,ResponseStatus> loanApplicationsFNM(String id);
	Result<InputStream,ResponseStatus> loanApplicationsMISMO(String id);
	Result<InputStream,ResponseStatus> creatingLoanApplication(Map<String, Object> createLoanRequest);
	Result<InputStream,ResponseStatus> updatingLoanApplication(String update_id, Map<String, Object> updateLoanRequest);
	Result<InputStream,ResponseStatus> loanApplicationByReferenceId(String referenceId);
}
