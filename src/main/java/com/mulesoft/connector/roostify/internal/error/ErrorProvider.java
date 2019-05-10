/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.error;

import org.mule.runtime.extension.api.annotation.error.ErrorTypeProvider;
import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

import java.util.HashSet;
import java.util.Set;

public class ErrorProvider implements ErrorTypeProvider {

	@Override
	public Set<ErrorTypeDefinition> getErrorTypes() {
		HashSet<ErrorTypeDefinition> errors = new HashSet<>();
		errors.add(RoostifyErrorTypes.BAD_REQUEST);
		errors.add(RoostifyErrorTypes.UNAUTHORIZED);
		errors.add(RoostifyErrorTypes.FORBIDDEN);
		errors.add(RoostifyErrorTypes.NOT_FOUND);
		errors.add(RoostifyErrorTypes.METHOD_NOT_ALLOWED);
		errors.add(RoostifyErrorTypes.NOT_ACCEPTABLE);
		errors.add(RoostifyErrorTypes.REQUEST_TIMEOUT);
		errors.add(RoostifyErrorTypes.CONFLICT);
		errors.add(RoostifyErrorTypes.INTERNAL_SERVER_ERROR);
		errors.add(RoostifyErrorTypes.NOT_IMPLEMENTED);
		errors.add(RoostifyErrorTypes.BAD_GATEWAY);
		errors.add(RoostifyErrorTypes.SERVICE_UNAVAILABLE);
		errors.add(RoostifyErrorTypes.EMPTY_HEAD_COUNT);
		errors.add(RoostifyErrorTypes.DEAD_TOKEN);
		errors.add(RoostifyErrorTypes.GENERIC_EXCEPTION);
		errors.add(RoostifyErrorTypes.UN_PROCESSABLE_ENTITY);
		return errors;
	}
}
