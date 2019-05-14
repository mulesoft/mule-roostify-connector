/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.services;

import com.mulesoft.connector.roostify.api.resultObject.ResponseStatus;
import org.mule.connectors.commons.template.service.ConnectorService;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

public interface TaskService extends ConnectorService{

	Result<InputStream, ResponseStatus> listingTasks( String loan_application_id, String page, String count);
	Result<InputStream,ResponseStatus> retrievingATask(String taskId);
	Result<InputStream,ResponseStatus> updateTask(Map<String,Object> taskBody, String taskId);
	Result<InputStream,ResponseStatus> creatingTaskFromForm(Map<String, Object> formTask);
	Result<InputStream,ResponseStatus> creatingTaskFromApproval(Map<String, Object> approvalTask);
	Result<InputStream,ResponseStatus> creatingViewOnlyTask(Map<String, Object> viewOnlyTask);
	Result<InputStream,ResponseStatus> creatingRegularTask(Map<String, Object> regularTask);
	Result<InputStream,ResponseStatus> creatingSpeedBumpTask(Map<String, Object> speedBumpTask);
	Result<InputStream,ResponseStatus> creatingFileContentTask(Map<String, Object> fileContentTask);
}
