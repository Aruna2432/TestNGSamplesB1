package testScripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleOneTest {
	
	WebDriver driver;
	Properties prop;
	
	@BeforeTest
	public void readFromProp() throws IOException {
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\ConfigFiles\\config.properties";
		prop=new Properties ();
		FileInputStream obtained = new FileInputStream(path);
		prop.load(obtained);
	}
	@Parameters("browser")
	@BeforeMethod
	public void setup(String strBrowser) {
		  //System.setProperty("webdriver.chrome.driver","E:\\WebDrivers\\chromedriver.exe");
		if(strBrowser.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
	      driver = new ChromeDriver();}
	      driver.manage().window().maximize();
	}
  //@Test(priority=1)
  public void javaSearchTest() {
	  
      driver.get("https://www.google.com/");
      WebElement searchBox = driver.findElement(By.name("q"));
      //Assert.assertEquals(driver.getTitle(),"Google Page");
      SoftAssert softAssert = new SoftAssert();
      softAssert.assertEquals(driver.getTitle(),"Google Page");
      
      searchBox.sendKeys("Java Tutorial");
      searchBox.submit();
      //Assert.assertEquals(driver.getTitle(),"Java Tutorial - Google Search");
      softAssert.assertEquals(driver.getTitle(),"Java Tutorial - Google Search");
      softAssert.assertAll();
      
  }
 // @Test(enabled=false)
  public void cypressSearchTest() {
      driver.get("https://www.google.com/");
      WebElement searchBox = driver.findElement(By.name("q"));
      searchBox.sendKeys("Cypress Tutorial");
      searchBox.submit();
      Assert.assertEquals(driver.getTitle(),"Cypress Tutorial - Google Search");
      
  }
  @Test
  public void seleniumSearchTest() {
	  
      driver.get("https://www.google.com/");
      WebElement searchBox = driver.findElement(By.name("q"));
      searchBox.sendKeys("Selenium Tutorial");
      searchBox.submit();
      Assert.assertEquals(driver.getTitle(),"Selenium Tutorial - Google Search Page");
  }
  @Test(alwaysRun=true,dependsOnMethods = "seleniumSearchTest")
  public void cucumberSearchTest() {
	  
      driver.get("https://www.google.com/");
      WebElement searchBox = driver.findElement(By.name("q"));
      searchBox.sendKeys("Cucumber Tutorial");
      searchBox.submit();
      Assert.assertEquals(driver.getTitle(),"Cucumber Tutorial - Google Search");
      
  }
    
  @AfterMethod
  //@AfterTest
  public void tearDown() {
	  driver.close();
  }
  }
