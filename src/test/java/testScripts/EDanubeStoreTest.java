package testScripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EDanubeStoreTest{
	WebDriver driver;
	Properties prop;
	
 @BeforeTest
  public void setup() throws IOException {
	  
	  WebDriverManager.chromedriver().setup();
	  driver=new ChromeDriver();
	  
	  //WebDriverManager.firefoxdriver().setup();
	  //driver=new FirefoxDriver();
	  
	  driver.manage().timeouts ().implicitlyWait (Duration.ofSeconds (30));
      driver.manage().window().maximize();
	  String path=System.getProperty("user.dir")+"\\src\\test\\resources\\ConfigFiles\\ExerciseTest.properties";
	  prop=new Properties ();
	  FileInputStream obtained = new FileInputStream(path);
	  prop.load(obtained);
  }
 
  @Test
  public void registerTest() throws InterruptedException
  {
	  driver.get(prop.getProperty("url")); 
	  driver.findElement(By.id("signup")).click();
	  driver.findElement(By.id("s-name")).sendKeys (prop.getProperty("name"));
	  driver.findElement(By.id("s-surname")).sendKeys (prop.getProperty("surname"));
	  driver.findElement(By.id("s-email")).sendKeys (prop.getProperty("email"));
	  driver.findElement(By.id("s-password2")).sendKeys (prop.getProperty("password"));
	  driver.findElement(By.id("s-company")).sendKeys (prop.getProperty("company"));
	  driver.findElement(By.id("myself")).click(); driver.findElement(By.id("marketing-agreement")).click();
	  driver.findElement(By.id("privacy-policy")).click();
	  driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();	
  
  }
  
  @Test
  public void searchTest() throws InterruptedException
  {
	  driver.findElement(By.name("searchbar")).sendKeys (prop.getProperty("search"));
	  driver.findElement(By.className("preview")).click();
	  driver.findElement(By.xpath("//button[text()='Add to cart']")).click(); 
	  driver.findElement(By.xpath("//button [ contains (text (), 'Checkout')]")).click();
	  driver.findElement(By.id("s-name")).sendKeys (prop.getProperty("name"));
	  driver.findElement(By.id("s-surname")).sendKeys (prop.getProperty("surname"));
	  driver.findElement(By.id("s-address")).sendKeys (prop.getProperty("address"));
	  driver.findElement(By.id("s-zipcode")).sendKeys (prop.getProperty("zipcode"));
	  driver.findElement(By.id("s-city")).sendKeys (prop.getProperty("city"));
	  driver.findElement(By.id("s-company")).sendKeys (prop.getProperty("company"));
	  driver.findElement(By.id("single")).click();
	  driver.findElement(By.xpath("//button [ contains (text(), 'Buy')]")).click();
  }
  
  @AfterTest
  public void tearDown() {
	  driver.close();
	  
  }
  
  
}