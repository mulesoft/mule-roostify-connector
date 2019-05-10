/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.api.documents;

import java.util.ArrayList;
import java.util.List;

public class documentsDTO {
    List<Object> documents = new ArrayList<Object>();

    public List<Object> getdocuments() {
        return documents;
    }

    public void setdocuments(List<Object> documents) {
        this.documents = documents;
    }
}
