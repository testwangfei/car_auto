package com.chedai.test;

import org.testng.annotations.Test;
import com.chedai.business.ContractReviewBus;

public class ContractReviewTest extends Init{
	
	@Test(priority=11)
	public void ContractSign() throws Exception{
		ContractReviewBus CRB=new ContractReviewBus(driver);
		CRB.contractReviewBus(custname);
	}

}
