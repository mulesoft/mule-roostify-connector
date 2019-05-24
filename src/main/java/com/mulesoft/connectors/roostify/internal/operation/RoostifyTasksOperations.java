/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.internal.operation;

import com.mulesoft.connectors.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connectors.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connectors.roostify.internal.error.ErrorProvider;
import com.mulesoft.connectors.roostify.api.response.ResponseStatus;
import com.mulesoft.connectors.roostify.internal.services.TaskService;
import com.mulesoft.connectors.roostify.internal.services.TaskServiceImpl;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.InputJsonType;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

public class RoostifyTasksOperations extends ConnectorOperations<RoostifyConfiguration, RoostifyConnection, TaskService> {

	public RoostifyTasksOperations() {
		super(TaskServiceImpl::new);
	}

	/**
	 * Return a list of all tasks and their details.
	 *
	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection instance.
	 * @param loan_application_id  Loan application ID to search for associated tasks.
	 * @param page    Indicates the page number which should be returned. When this parameter is not present and count is, it defaults to 1. When both parameters are not present pagination is disabled.
	 * @param count    Indicates the number of records per page returned. When this parameter is not present and page is, it defaults to 100. When both parameters are not present pagination is disabled.
	 * @return Json response containing list of all tasks
	 */
	@DisplayName(value = "Listing Tasks")
	@Throws({ErrorProvider.class})
	@OutputJsonType(schema = "metadata/listingTasks")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream, ResponseStatus> listingTasks(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection,
	                                                        @Optional String loan_application_id, @Optional String page, @Optional String count){
		return newExecutionBuilder(configuration, connection)
				.execute(TaskService::listingTasks).withParam(loan_application_id).withParam(page).withParam(count);
	}

	/**
	 * Retrieve a single task details.
	 *
	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection instance.
	 * @param taskId    ID of task.
	 * @return Json response containing a tasks
	 */
	@DisplayName(value = "Retrieve a Task")
	@OutputJsonType(schema = "metadata/retrieveTask")
	@Throws({ErrorProvider.class})
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> retrievingATask(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection, String taskId) {
		return newExecutionBuilder(configuration, connection)
				.execute(TaskService::retrievingATask).withParam(taskId);
	}

	/**
	 * Update details of a single task.
	 *
	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection instance.
	 * @param taskId    ID of the task.
	 * @param taskBody Request body for updating task.
	 * @return Json response containing updated task
	 */
	@DisplayName(value = "Update a Task")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema= "metadata/updateTask_response")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> updateTask(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection, @Content @InputJsonType(schema= "metadata/updateTask_request") Map<String,Object> taskBody, String taskId) {
		return newExecutionBuilder(configuration, connection)
				.execute(TaskService::updateTask).withParam(taskBody).withParam(taskId);
	}

	/**
	 * Create a new task from a form task.
	 *
	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection instance.
	 * @param formTask Request body for creating a task from a form task.
	 * @return Json response containing new task
	 */
	@DisplayName(value = "Create a Task from a Form Task")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema= "metadata/formTask")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> creatingTaskFromForm(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection, @Content @InputJsonType(schema = "metadata/formTask") Map<String, Object> formTask) {
		return newExecutionBuilder(configuration, connection)
				.execute(TaskService::creatingTaskFromForm).withParam(formTask);
	}

	/**
	 * Create a new task from an approval task.
	 *
	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection instance.
	 * @param approvalTask Request body for creating a task from an approval task.
	 * @return Json response containing new task
	 */
	@DisplayName(value = "Create a Task from an Approval Task")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema= "metadata/approvalTask")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> creatingTaskFromApproval(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection, @Content @InputJsonType(schema = "metadata/approvalTask") Map<String, Object> approvalTask) {
		return newExecutionBuilder(configuration, connection)
				.execute(TaskService::creatingTaskFromApproval).withParam(approvalTask);
	}

	/**
	 * Create a talk from a view-only task.
	 *
	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection instance.
	 * @param viewOnlyTask Request body for creating a task from a view-only task.
	 * @return Json response containing new task
	 */
	@DisplayName(value = "Create a Task from a View-only Task")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema= "metadata/viewOnlyTask")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> creatingViewOnlyTask(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection, @Content @InputJsonType(schema = "metadata/viewOnlyTask") Map<String, Object> viewOnlyTask) {
		return newExecutionBuilder(configuration, connection)
				.execute(TaskService::creatingViewOnlyTask).withParam(viewOnlyTask);
	}

	/**
	 * Create a task from a regular task.
	 *
	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection instance.
	 * @param regularTask Request body for creating a task from a regular task.
	 * @return Json response containing new task
	 */
	@DisplayName(value = "Create a Task from a Regular Task")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema= "metadata/viewOnlyTask")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> creatingRegularTask(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection, @Content @InputJsonType(schema = "metadata/createTask") Map<String, Object> regularTask) {
		return newExecutionBuilder(configuration, connection)
				.execute(TaskService::creatingRegularTask).withParam(regularTask);
	}

	/**
	 * Create a task from a speed-bump task.
	 *
	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection  instance.
	 * @param speedBumpTask Request body for creating a task from a speed-bump task.
	 * @return Json response containing new task
	 */
	@DisplayName(value = "Create a Task from a Speed-bump Task")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema= "metadata/viewOnlyTask")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> creatingSpeedBumpTask(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection, @Content @InputJsonType(schema = "metadata/createTask") Map<String, Object> speedBumpTask) {
		return newExecutionBuilder(configuration, connection)
				.execute(TaskService::creatingSpeedBumpTask).withParam(speedBumpTask);
	}

	/**
	 * Create a task from a file content task.
	 *
	 * @param configuration Roostify Configuration Object.
	 * @param connection   Roostify connection  instance.
	 * @param fileContentTask Request body for creating a task from a file content task.
	 * @return Json response containing new task
	 */
	@DisplayName(value = "Create a Task from a File Content Task")
	@Throws(ErrorProvider.class)
	@OutputJsonType(schema= "metadata/viewOnlyTask")
	@MediaType(value = MediaType.APPLICATION_JSON, strict = false)
	public Result<InputStream,ResponseStatus> creatingFileContentTask(@Config RoostifyConfiguration configuration, @Connection  RoostifyConnection connection, @Content @InputJsonType(schema = "metadata/fileContentTask") Map<String, Object> fileContentTask) {
		return newExecutionBuilder(configuration, connection)
				.execute(TaskService::creatingFileContentTask).withParam(fileContentTask);
	}
}
