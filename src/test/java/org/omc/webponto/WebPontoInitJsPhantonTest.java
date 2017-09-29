package org.omc.webponto;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebPontoInitJsPhantonTest {

	WebDriver driver = null;



	@Before
	public void setup() throws Exception {		
		
		driver = getPhantonJSDriver();					
		driver.get("https://webponto.resource.com.br/default.asp");		
}

	@Test
	public void loginWebPonto() throws Exception {
		
		long iStart = System.currentTimeMillis();		
		
		WebElement codEmpresa = driver.findElement(By.xpath("//input[@name='CodEmpresa']"));
		if(codEmpresa != null) {
			driver.findElement(By.xpath("//input[@name='CodEmpresa']")).sendKeys("9");
		}
		
		WebElement requiredUsuario = driver.findElement(By.xpath("//input[@name='requiredUsuario']"));
		if(requiredUsuario != null) {
			driver.findElement(By.xpath("//input[@name='requiredusuario']")).sendKeys("034960");
		}
		
		WebElement requiredSenha = driver.findElement(By.xpath("//input[@name='requiredSenha']"));
		if(requiredSenha != null) {
			driver.findElement(By.xpath("//input[@name='requiredsenha']")).sendKeys("cog@cog06");
		}
		
		WebElement submit = driver.findElement(By.xpath("//input[@name='Submit']"));
		if(submit != null) {
			driver.findElement(By.xpath("//input[@name='Submit']")).submit();
		}
					

		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\sample.jpeg"),true);    
		System.out.println("Single Page Time:" + (System.currentTimeMillis() - iStart));

	}

	@After
	public void endWebPonto() throws Exception {
		driver.quit();
	}
	
	public PhantomJSDriver getPhantonJSDriver() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("phantomjs.binary.path", "D:/Users/t803465/git/main/lib/phantomjs.exe");
		caps.setCapability("takesScreenshot", true);
		return  new PhantomJSDriver(caps);
	}	

}