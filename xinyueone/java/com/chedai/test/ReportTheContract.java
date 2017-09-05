package com.chedai.test;

import org.testng.annotations.Test;

import com.chedai.business.NewPackBus;

@Test(groups = "Init")
public class ReportTheContract extends Init {
	NewPackBus NPB;
	
	@Test(priority=8)
	public void AgreeReview() throws Exception{
		NPB= new NewPackBus(driver);	
		NPB.AgreeReviewBus(custname);
	}
	
	@Test(priority=9)
	public void SignContract() throws Exception{
		NPB.SignContractBus(custname);
	}

}
