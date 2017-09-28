package org.omc.webponto;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class WebPontoInit {

	WebDriver driver = null;



	@Before
	public void setup() throws Exception {		
		
		driver = getChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://webponto.resource.com.br/default.asp");	
		
//		WebDriverWait wait = new WebDriverWait(driver, 15, 100);
//		wait.until(ExpectedConditions.alertIsPresent());
			
		
		WebElement userID = driver.findElement(By.id("requiredusuario"));
		if (userID != null) {
			driver.findElement(By.id("requiredusuario")).sendKeys("re034960");			
		}
		WebElement password = driver.findElement(By.id("password"));
		if(password != null) {
			driver.findElement(By.id("requiredsenha")).sendKeys("cog@cog08");			
		}
		
		Thread.sleep(3000);
		
		driver.switchTo().alert();
		driver.switchTo().alert().accept();		
		
		//alert.authenticateUsing(new UserAndPassword("re034960", "cog@cog08"));
		driver.switchTo().defaultContent();


	}

	@Test
	public void loginWebPonto() throws Exception {
		WebElement codEmpresa = driver.findElement(By.xpath("//input[@name='CodEmpresa']"));
		if(codEmpresa != null) {
			driver.findElement(By.xpath("//input[@name='CodEmpresa']")).sendKeys("9");
		}
		
		WebElement requiredUsuario = driver.findElement(By.xpath("//input[@name='CodEmpresa']"));
		if(requiredUsuario != null) {
			driver.findElement(By.xpath("//input[@name='requiredusuario']")).sendKeys("034960");
		}
		
		WebElement requiredSenha = driver.findElement(By.xpath("//input[@name='CodEmpresa']"));
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

	//@After
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

	public void get() {
		ResponseEntity<String> response = null;
		String messageError = "";

		Long nrItseg = 25539891L;
		Long cdAvisoSinis = 1L;
		String dataOcorrencia = "01/09/2017";

		StringBuffer url = new StringBuffer(
				"http://localhost:8086/CorpUtil/rest/condicaoExclusiva/atualizarAvisoSinistro?");
		url.append("nrItseg=" + nrItseg);
		url.append("&cdAvisoSinis=" + cdAvisoSinis);
		url.append("&dataOcorrencia=" + dataOcorrencia);
		url.append("&municipioOcorrencia=");
		url.append("&ufOcorrencia=");

		System.out.println("Inicio da chamada " + url.toString());
		try {
			response = new RestTemplate().getForEntity(url.toString(), String.class);
		} catch (HttpServerErrorException e) {
			messageError = e.getResponseBodyAsString();
		}

		if (messageError.isEmpty()) {
			System.out.println("response " + response.getBody());
		} else {
			System.out.println("response " + messageError);
		}
	}

}