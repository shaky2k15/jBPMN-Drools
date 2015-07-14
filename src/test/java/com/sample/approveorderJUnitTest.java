package com.sample;

import org.jbpm.test.JbpmJUnitTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;

@SuppressWarnings("deprecation")
public class approveorderJUnitTest extends JbpmJUnitTestCase {

	@Test
    public void testImplicit() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession = kContainer.newKieSession("ksession-samples");
	    ProcessInstance processInstance = ksession.startProcess("approveorder");
      
        Purchase purchase = new Purchase();
		purchase.setCustomerName("sekhar");
		purchase.setPaymentMethod(PaymentMethod.DEBIT);
		purchase.setSubtotal(0);
        ksession.insert(purchase);
        int rulesFired = ksession.fireAllRules();
        System.out.println("facthandles count -->"+ksession.getFactHandles().size());
        //Check the count
        Assert.assertEquals( 1, rulesFired );
        // Check the node exists
        //assertNodeExists(processInstance, "Discount","printout");
        //Check the node names
        //assertNodeTriggered(processInstance.getId(),"Discount","printout");
        //Assert completed
        assertProcessInstanceCompleted(processInstance.getId(), ksession);
    }



}