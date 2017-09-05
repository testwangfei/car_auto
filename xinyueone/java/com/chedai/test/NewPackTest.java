package com.chedai.test;

import org.testng.annotations.Test;
import com.chedai.business.NewPackBus;


@Test(groups = "Init")
public class NewPackTest extends Init {
	NewPackBus NPB;

	@Test(priority=0)
	public void LoginTest() throws Exception{
		NPB= new NewPackBus(driver);	
		NPB.logoutbus();
		NPB.loginBus(new_user, password);
	}
	
	@Test(priority=1)
	public void newPack() throws Exception{	
		NPB.newPackBus();	
	}

}



