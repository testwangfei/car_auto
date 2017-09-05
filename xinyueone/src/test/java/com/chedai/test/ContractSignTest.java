package com.chedai.test;


import com.chedai.business.*;
import org.testng.annotations.Test;

import com.chedai.business.ContractSignBus;

@Test(groups = "Init")
public class ContractSignTest  extends InitTest{
	NewPackBus NPB;
	ContractSignBus CSB;

	
	@Test(priority=10)
	public void ContractSign() throws Exception{
		CSB=new ContractSignBus(driver);
		CSB.contractSignBus(custname);
	}

}
