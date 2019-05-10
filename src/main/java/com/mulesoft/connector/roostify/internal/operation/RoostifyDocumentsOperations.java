/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.operation;

import com.mulesoft.connector.roostify.internal.config.RoostifyConfiguration;
import com.mulesoft.connector.roostify.internal.connection.RoostifyConnection;
import com.mulesoft.connector.roostify.internal.error.ErrorProvider;
import com.mulesoft.connector.roostify.api.resultObject.ResponseStatus;
import com.mulesoft.connector.roostify.internal.services.DocumentsService;
import com.mulesoft.connector.roostify.internal.services.DocumentsServiceImpl;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.InputJsonType;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

public class RoostifyDocumentsOperations extends ConnectorOperations<RoostifyConfiguration, RoostifyConnection, DocumentsService> {

    public RoostifyDocumentsOperations() {
        super(DocumentsServiceImpl::new);
    }
    /**
     * Retrieve metadata for documents. An example request looks like: GET /documents?filter[reference_id]=true&search[document_name]=Sample&search[loan_application_reference_id]=EFG.

     * @param configuration Roostify Configuration Object.
     * @param connection   Roostify connection  instance.
     * @param page    Indicates the page of the index which should be returned. When this parameter is not present and count is, it defaults to 1. When both parameters are not present pagination is disabled.
     * @param count    Indicates the number of records per page returned. When this parameter is not present and page is, it defaults to 100. When both parameters are not present pagination is disabled.
     * @param search    Terms for searching document metadata. Current searchable terms are: document_name, loan_application_reference_id, and loan_application_id.
     * @param filter    Term for filtering document metadata based presence of reference_id.

     */
    @DisplayName(value = "Listing documents")
    @Throws({ErrorProvider.class})
    @OutputJsonType(schema = "metadata/listingDocuments")
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream, ResponseStatus> listingDocuments(@Config RoostifyConfiguration configuration, @Connection RoostifyConnection connection, @Optional String page,
                                                                @Optional String count , @Optional @Summary("referenceId") String filter,@Optional @Summary("loanApplicationId") String search) {
        return newExecutionBuilder(configuration, connection)
                .execute(DocumentsService::listingDocuments).withParam(page).withParam(count).withParam(filter).withParam(search);
    }

    /**
     * Retrieve the metadata for a document.
     *
     * @param configuration Roostify Configuration Object.
     * @param connection Roostify connection  instance.
     * @param find_id    ID of the document to retrieve.
     */
    @DisplayName(value = "Retrieve a document")
    @OutputJsonType(schema = "metadata/retrieveDocument")
    @Throws({ErrorProvider.class})
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream, ResponseStatus> retrievingDocument(@Config RoostifyConfiguration configuration, @Connection RoostifyConnection connection, String find_id) {

        return newExecutionBuilder(configuration, connection)
                .execute(DocumentsService::retrievingDocument).withParam(find_id);


    }
    /**
     * @param configuration Roostify Configuration Object.
     * @param connection   Roostify connection  instance.
     * @param id    Required. ID of the document to retrieve.
     */
    @DisplayName(value = "Download a document")
    @OutputJsonType(schema= "metadata/downloadDocument")
    @Throws({ErrorProvider.class})
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream,ResponseStatus> downloadingDocument(@Config RoostifyConfiguration configuration,@Connection RoostifyConnection connection, String id) {
        return newExecutionBuilder(configuration, connection)
                .execute(DocumentsService::downloadingDocument).withParam(id);

           }

    /**
     * To upload a document, POST the document as JSON to /documents. The file_content should be the base64 encoded bytes of the document.

     * @param configuration Roostify Configuration Object.
     * @param connection   Roostify connection  instance.
     */
    @DisplayName(value = "Upload or create a document ")
    @Throws({ErrorProvider.class})
    @OutputJsonType(schema= "metadata/uploadingORcreatingDocument_response")
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream,ResponseStatus> creatingDocument(@Config RoostifyConfiguration configuration,@Connection RoostifyConnection connection,@Content @InputJsonType(schema= "metadata/uploadingORcreatingDocument_request") Map<String,Object> document) {
        return newExecutionBuilder(configuration, connection)
                .execute(DocumentsService::creatingDocument).withParam(document);
    }


    /**
     * Updates a documents metadata and returns the documents metadata.

     * @param configuration Roostify Configuration Object.
     * @param connection   Roostify connection  instance.
     * @param id    ID of the document to update.
     */

    @DisplayName(value = "Update a document")
    @Throws({ErrorProvider.class})
    @OutputJsonType(schema= "metadata/updateDocument_response")
    @MediaType(value =MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream,ResponseStatus> updateDocument(@Config RoostifyConfiguration configuration,@Connection RoostifyConnection connection, @Content @InputJsonType(schema= "metadata/updateDocument_request") Map<String,Object> documentBody, String id) {
        return newExecutionBuilder(configuration, connection)
                .execute(DocumentsService::updateDocument).withParam(documentBody).withParam(id);
    }

}
