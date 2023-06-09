package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTwoTest {
  @Test(retryAnalyzer = RetrySampleTest.class)
	  public void cypressSearchTest() {
		  //System.setProperty("webdriver.chrome.driver","E:\\WebDrivers\\chromedriver.exe");
	      WebDriverManager.chromedriver().setup();
	      WebDriver driver = new ChromeDriver();
	      
	      //WebDriverManager.edgedriver().setup();
	      //WebDriver driver = new EdgeDriver();
	      
	      driver.manage().window().maximize();
	      driver.get("https://www.google.com/");
	      WebElement searchBox = driver.findElement(By.name("q"));
	      searchBox.sendKeys("Cypress Tutorial");
	      searchBox.submit();
	      Assert.assertEquals(driver.getTitle(),"Cypress Tutorial - Google Search");
	      driver.close();
	  }
  }
