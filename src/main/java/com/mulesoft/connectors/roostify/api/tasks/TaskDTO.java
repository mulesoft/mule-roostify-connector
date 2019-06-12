/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.api.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskDTO {
    List<Object> listingTasks = new ArrayList<Object>();

    public List<Object> getList() {
        return listingTasks;
    }

    public void setList(List<Object> lists) {
        this.listingTasks = lists;
    }
}
