/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package roostify;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.core.api.event.CoreEvent;

import static org.junit.Assert.assertNotNull;

public class RoostifyLoanApplicationOperationsIT extends MuleArtifactFunctionalTestCase {

  @Override
  protected String getConfigFile() {
    return "test-loan-application-flows.xml";
  }

  @Test
  public void executeLoanApplicationOperation() throws Exception {
    Object payloadValue = ((CoreEvent) flowRunner("getLoanApplicationFlow").run()).getMessage().getPayload();
    JSONObject jObject  = new JSONObject(payloadValue);
    Assert.assertNotNull(jObject);
  }

  @Test
  public void executeLoanApplicationsOperation() throws Exception {
    Object payloadValue = ((CoreEvent) flowRunner("getLoanApplicationsFlow").run()).getMessage().getPayload();
    JSONObject jObject  = new JSONObject(payloadValue);
    Assert.assertNotNull(jObject);
  }

  @Test
  public void executeCreateLoanApplicationOperation() throws Exception {
    Object payloadValue = ((CoreEvent) flowRunner("createLoanFlow").run()).getMessage().getPayload().getValue();
    assertNotNull(payloadValue);
  }

  @Test
  public void executeUpdateLoanApplicationOperation() throws Exception {
    Object payloadValue = ((CoreEvent) flowRunner("updateLoanApplicationFlow").run()).getMessage().getPayload().getValue();
    assertNotNull(payloadValue);
  }

  @Test
  public void executeLoanFNMOperation() throws Exception {
    Object payloadValue = ((CoreEvent) flowRunner("getLoanFNMFormatFlow").run()).getMessage().getPayload();
    JSONObject jObject  = new JSONObject(payloadValue);
    Assert.assertNotNull(jObject);
  }

  @Test
  public void executeLoanRefIdOperation() throws Exception {
    Object payloadValue = ((CoreEvent) flowRunner("getLoanRefIdFlow").run()).getMessage().getPayload();
    JSONObject jObject  = new JSONObject(payloadValue);
    Assert.assertNotNull(jObject);
  }

  @Test
  public void executeLoanMISMOOperation() throws Exception {
    Object payloadValue = ((CoreEvent) flowRunner("getLoanMISMOFormatFlow").run()).getMessage().getPayload();
    JSONObject jObject  = new JSONObject(payloadValue);
    Assert.assertNotNull(jObject);
  }
  @Test
  public void executeDownloadDocumentOperation() throws Exception {
    Object payloadValue = ((CoreEvent) flowRunner("downloadDocumentFlow").run()).getMessage().getPayload();
    assertNotNull(payloadValue);
  }
  @Test
  public void executeGetDocumentOperation() throws Exception {
    Object payloadValue = ((CoreEvent) flowRunner("getDocumentFlow").run()).getMessage().getPayload();
    JSONObject jObject  = new JSONObject(payloadValue);
    Assert.assertNotNull(jObject);
  }

  @Test
  public void executeCreateDocumentOperation() throws Exception {
    Object payloadValue = ((CoreEvent)flowRunner("createDocumentFlow").run()).getMessage().getPayload().getValue();
    assertNotNull(payloadValue);
  }

  @Test
  public void executeGetDocumentsOperation() throws Exception {
    Object payloadValue = ((CoreEvent) flowRunner("getDocumentsFlow").run()).getMessage().getPayload();
    JSONObject jObject  = new JSONObject(payloadValue);
    Assert.assertNotNull(jObject);
  }

  @Test
  public void executeUpdateDocumentOperation() throws Exception {
    Object payloadValue = ((CoreEvent)flowRunner("updateDocumentFlow").run()).getMessage().getPayload().getValue();
    JSONObject jObject  = new JSONObject(payloadValue);
    assertNotNull(jObject);
  }

}
