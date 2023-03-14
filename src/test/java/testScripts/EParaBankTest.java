package testScripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EParaBankTest {
	WebDriver driver;
	
	@BeforeTest	
	public void setup(){
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://parabank.parasoft.com/");
		
	}
	@Test
	public void register() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[contains(text(),'Register')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("customer.firstName")).sendKeys("Aarthi");
		driver.findElement(By.id("customer.lastName")).sendKeys("Y");
		driver.findElement(By.id("customer.address.street")).sendKeys("34, Nellai Road");
		driver.findElement(By.id("customer.address.city")).sendKeys("Erode");
		driver.findElement(By.id("customer.address.state")).sendKeys("TamilNadu");
		driver.findElement(By.id("customer.address.zipCode")).sendKeys("786543");
		driver.findElement(By.id("customer.phoneNumber")).sendKeys("9874563312");
		driver.findElement(By.id("customer.ssn")).sendKeys("5487695");
		driver.findElement(By.id("customer.username")).sendKeys("abc@aarthi");
		driver.findElement(By.id("customer.password")).sendKeys("148/*lojkA");
		driver.findElement(By.cssSelector("input#repeatedPassword")).sendKeys("148/*lojkA");
		driver.findElement(By.xpath("//td/input[@class='button']")).click();	
	}

  
  @Test(priority=1)
  public void openaccount() throws InterruptedException
  {
	  driver.findElement(By.xpath("//a[contains(text(),'Open')]")).click();
	  driver.findElement(By.cssSelector("select#type"));
	  driver.findElement(By.xpath("//input[@class='button']")).submit();
	  Assert.assertEquals("Account Opened!", driver.findElement(By.xpath("//h1")).getText());  
  }
  
  
  
 @Test(priority=2)
  public void Transferfund() throws InterruptedException {
	  driver.findElement(By.xpath("//a[contains(text(),'Fund')]")).click();
	  driver.findElement(By.cssSelector("input#amount")).sendKeys("2500");
	  Select from=new Select(driver.findElement(By.id("fromAccountId")));
	  from.selectByIndex(0);
	  Select to=new Select(driver.findElement(By.id("fromAccountId")));
	  to.selectByIndex(1);
	  driver.findElement(By.xpath("//input[@class='button']")).submit();
	  Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "Transfer Complete!");
  }
  
  
 // @AfterMethod
  public void teardown()
  {
	  driver.close();
  }
}

