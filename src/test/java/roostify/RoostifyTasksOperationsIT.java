/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package roostify;

import org.junit.Assert;
import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.core.api.event.CoreEvent;

import static org.junit.Assert.assertNotNull;

public class RoostifyTasksOperationsIT extends MuleArtifactFunctionalTestCase {

    @Override
    protected String getConfigFile() {
        return "test-tasks-flow.xml";
    }
    @Test
    public void executeRetrieveaTaskOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("getTaskFlow").run()).getMessage().getPayload();
	    Assert.assertNotNull(payloadValue);
    }

    @Test
    public void executeRetrieveTaskListOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("getTasksFlow").run()).getMessage().getPayload();
	    Assert.assertNotNull(payloadValue);
    }

    @Test
    public void executeUpdateaTaskOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("updateTaskFlow").run()).getMessage().getPayload().getValue();
        assertNotNull(payloadValue);
    }

    @Test
    public void executeCreateApprovalTaskOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("createApprovalTaskFlow").run()).getMessage().getPayload().getValue();
        assertNotNull(payloadValue);
           }

    @Test
    public void executeCreateFormTaskOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("createFormTaskFlow").run()).getMessage().getPayload().getValue();
        assertNotNull(payloadValue);
    }

    @Test
    public void executeCreateViewOnlyTaskOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("createViewOnlyTaskFlow").run()).getMessage().getPayload().getValue();
        assertNotNull(payloadValue);
    }

    @Test
    public void executeCreateSpeedBumpTaskOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("createSpeedBumpTaskFlow").run()).getMessage().getPayload().getValue();
        assertNotNull(payloadValue);
    }

    @Test
    public void executeCreateFileContentTaskOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("createFileContentTaskFlow").run()).getMessage().getPayload().getValue();
        assertNotNull(payloadValue);
    }

    @Test
    public void executeCreateRegularTaskOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("createRegularTaskFlow").run()).getMessage().getPayload().getValue();
        assertNotNull(payloadValue);
    }
}
