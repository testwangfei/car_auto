package com.chedai.business;
import org.openqa.selenium.WebDriver;
import com.chedai.page.contractSignPage;



public class ContractSignBus extends contractSignPage{
	public ContractSignBus(WebDriver driver) throws Exception {
		super(driver);
	}
	
	//合同签约
	public void contractSignBus(String custname) throws Exception{
		this.intoContractSign();
		this.searchname(custname);
		this.signContractListPage();
	}

}
