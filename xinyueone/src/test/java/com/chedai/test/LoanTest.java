package com.chedai.test;


import org.testng.annotations.Test;
import com.chedai.business.LoanBus;

@Test(groups = "Init")
public class LoanTest extends InitTest {
	LoanBus loan;
	
	
	/**
	 *发标申请测试
	 */
	@Test(priority=12)
	public void sendApplicationTest() throws Exception{
		loan = new LoanBus(driver);
		loan.sendApplicationBus(custname);
		
		
	}
	/**
	 *发标审核测试
	 */
	@Test(priority=13)
	public void SendTenderAuditTest() throws Exception{
		loan.SendTenderAuditBus(custname);
	}

}
