package com.chedai.test;

import org.testng.annotations.Test;
import com.chedai.business.NewPackBus;

@Test(groups = "Init")
public class CreditEvaluation extends Init{
	NewPackBus NPB;
	
	@Test(priority=2)
	public void newCredit() throws Exception{
		NPB= new NewPackBus(driver);
		NPB.newCreditBus(custname);
	}
	
	@Test(priority=3)
	public void newEvaluation() throws Exception{
		NPB.newEvaluationBus(custname);
	}

}
