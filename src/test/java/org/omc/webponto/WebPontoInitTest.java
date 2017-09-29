package org.omc.webponto;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebPontoInitTest {

	WebDriver driver = null;



	@Before
	public void setup() throws Exception {		
		
		driver = getChromeDriver();
		
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
				
		driver.get("https://webponto.resource.com.br/default.asp");
//		((JavascriptExecutor) driver).executeScript("window.open('https://webponto.resource.com.br/default.asp');");
		
		try {
			driver.switchTo().alert();			
		} catch (NoAlertPresentException e) {
			
		}
		
//		if (driver instanceof JavascriptExecutor) {
//			((JavascriptExecutor) driver).executeScript("alert('hello world');");
//		}

				
//		WebDriverWait wait = new WebDriverWait(driver, 15, 100);
//		wait.until(ExpectedConditions.alertIsPresent());
		
//		WebElement userID = driver.findElement(By.id("requiredusuario"));
//		if (userID != null) {
//			driver.findElement(By.id("requiredusuario")).sendKeys("re034960");			
//		}
//		WebElement password = driver.findElement(By.id("requiredsenha"));
//		if(password != null) {
//			driver.findElement(By.id("requiredsenha")).sendKeys("cog@cog08");			
//		}
				
		//driver.switchTo().alert().accept();		
		
		//alert.authenticateUsing(new UserAndPassword("re034960", "cog@cog08"));
		//driver.switchTo().defaultContent();


	}

	@Test
	public void loginWebPonto() throws Exception {
		
		WebElement usuario = driver.findElement(By.xpath("//input[@name='Usuario']"));
		
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
					
		String alrt = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		System.out.println(alrt);

	}

	@After
	public void endWebPonto() throws Exception {
		driver.quit();
	}
	
	
	public void getGeckoDriver() {
		System.setProperty("webdriver.gecko.driver", "D:/Users/t803465/git/main/lib/geckodriver.exe");
	}	
	
	public ChromeDriver getChromeDriver() {		
		System.setProperty("webdriver.chrome.driver", "D:/Users/t803465/git/main/lib/chromedriver.exe");
		return new ChromeDriver();
	}
	
	public ChromeDriver getChromeOptionsDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=D:/Users/t803465/AppData/Local/Google/Chrome/User Data/Default");
		
		System.setProperty("webdriver.chrome.driver", "D:/Users/t803465/git/main/lib/chromedriver.exe");
		return new ChromeDriver(options);
	}	

}