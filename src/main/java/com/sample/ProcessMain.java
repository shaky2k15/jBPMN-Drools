package com.sample;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class ProcessMain {

	public static void main(String[] args) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession = kContainer.newKieSession("ksession-samples");
		
				
		Map<String, Object> params = new HashMap<String, Object>();
		Purchase purchase = new Purchase();
		purchase.setCustomerName("sekhar");
		purchase.setPaymentMethod(PaymentMethod.CREDIT);
		purchase.setSubtotal(0);
		params.put("purchase", purchase);
		
		ksession.startProcess("approveorder",params);
		ksession.fireAllRules();
		ksession.dispose();
		
	
	}


}