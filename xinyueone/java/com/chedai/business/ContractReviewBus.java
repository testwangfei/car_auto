package com.chedai.business;
import org.openqa.selenium.WebDriver;
import com.chedai.page.ContractReviewPage;



public class ContractReviewBus extends ContractReviewPage{
	public ContractReviewBus(WebDriver driver) throws Exception {
		super(driver);
	}
	
	//合同签约
	public void contractReviewBus(String custname) throws Exception {
		this.intoContractReview();
		this.searchname(custname);
		this.skipContractReviewList();
	}

}
