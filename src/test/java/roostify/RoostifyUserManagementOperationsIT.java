/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package roostify;

import org.junit.Assert;
import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.core.api.event.CoreEvent;

import java.util.Random;

import static org.junit.Assert.assertNotNull;
public class RoostifyUserManagementOperationsIT extends MuleArtifactFunctionalTestCase {

    @Override
    protected String getConfigFile() {
        return "test-user-management-flow.xml";
    }
    @Test
    public void executeUserCreateOperation() throws Exception {
        Random random = new Random(System.nanoTime());
        int number = random.nextInt(1000000000);
        String email = new StringBuilder("apisero").append(number).append("@gmail.com").toString();
        Object payloadValue = ((CoreEvent) flowRunner("createUserFlow").withVariable("email",email).run()).getMessage().getPayload().getValue();
        Assert.assertNotNull(payloadValue);
    }

    @Test
    public void executeUserActivateOperation() throws Exception {
        Object activatePayload = ((CoreEvent)  flowRunner("activateUserFlow").withVariable("id","1628998681170838").run()).getMessage().getPayload().getValue();
        Object deactivePayload = ((CoreEvent)  flowRunner("deactivateUserFlow").withVariable("id","1628998681170838").run()).getMessage().getPayload().getValue();

        assertNotNull(activatePayload);
    }

    @Test
    public void executeUserDeactivateOperation() throws Exception {
        Object deactivatePayload = ( flowRunner("deactivateUserFlow").withVariable("id","2268793899320043").run().getMessage().getPayload().getValue());
        Object activatePayload = (  flowRunner("activateUserFlow").withVariable("id","2268793899320043").run().getMessage().getPayload().getValue());
        assertNotNull(deactivatePayload);
    }
   @Test
    public void executeUpdateUserOperation() throws Exception {
        Object payloadValue = (flowRunner("updateUserFlow").run().getMessage().getPayload().getValue());

   }

    @Test
    public void executeGetUserListOperation() throws Exception {
        Object payloadValue = ((CoreEvent)flowRunner("getUsersFlow").run()).getMessage().getPayload().getValue();
        assertNotNull(payloadValue);
    }

    @Test
    public void executeGetUserOperation() throws Exception {
        Object payloadValue = ((CoreEvent)flowRunner("getUserFlow").run()).getMessage().getPayload().getValue();
        assertNotNull(payloadValue);
    }
}
