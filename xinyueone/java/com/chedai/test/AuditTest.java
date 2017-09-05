package com.chedai.test;

import org.testng.annotations.Test;
import com.chedai.business.AuditBus;

@Test(groups = "Init")
public class AuditTest extends Init {
	AuditBus AB;

	/**
	 * 质检审核测试
	 */
	@Test(priority=4)
	public void qualityAuditTest() throws Exception {
		AB =new AuditBus(driver);
		AB.qualityAuditBus(custname);
	}

	/**
	 * 初审审核测试
	 */
	@Test(priority=5)
	public void PreliminaryAuditTest() throws Exception {
		AB.PreliminaryAuditBus(custname);
	}

	/**
	 * 终审审核测试
	 */
	@Test(priority=6)
	public void FinalAuditTest() throws Exception {
		AB.FinalAuditBus(custname);
	}

	/**
	 * 评估终审测试
	 */
	@Test(priority=7)
	public void assessAuditTest() throws Exception {
		AB.sleep(1500);
		AB.assessAuditBus(custname);
	}

}
