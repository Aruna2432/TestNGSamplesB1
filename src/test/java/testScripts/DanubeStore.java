package testScripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DanubeStore {
	WebDriver driver;
	Properties prop;
	@BeforeTest
	public void setup() throws IOException
	{
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
		  driver.get(prop.getProperty("url")); 
	}
	
	
	@Test(dataProvider="RegisterTest")
	public void searchTest() throws InterruptedException, SAXException, IOException, ParserConfigurationException {
		
		driver.findElement(By.name("searchbar")).sendKeys (prop.getProperty("search"));
		driver.findElement(By.className("preview")).click();
		driver.findElement(By.xpath("//button[text()='Add to cart']")).click(); 
		driver.findElement(By.xpath("//button [ contains (text (), 'Checkout')]")).click();
		driver.findElement(By.id(readXMLData("name"))).sendKeys(prop.getProperty("name"));
		driver.findElement(By.id(readXMLData("surname"))).sendKeys(prop.getProperty("surname"));
		driver.findElement(By.id(readXMLData("address"))).sendKeys(prop.getProperty("address"));
		driver.findElement(By.id(readXMLData("zip"))).sendKeys(prop.getProperty("zipcode"));
		driver.findElement(By.id(readXMLData("city"))).sendKeys(prop.getProperty("city"));
		driver.findElement(By.id(readXMLData("company"))).sendKeys(prop.getProperty("company"));
		driver.findElement(By.id(readXMLData("shipment"))).click();
		driver.findElement(By.xpath(readXMLData("buy"))).click();
	}
	
	public String readData(String objName) throws InvalidFormatException, IOException {
		  String objPath="";
		  String path=System.getProperty("user.dir")+"//src//test//resources//testData//DanubeExcel.xlsx";
		  XSSFWorkbook workbook= new XSSFWorkbook(new File(path));
		  XSSFSheet sheet=workbook.getSheet("DataD");
		  int numRows=sheet.getLastRowNum();
		  for(int i=0; i<=numRows; i++)
		  {
			  XSSFRow row=sheet.getRow(i);
			  if(row.getCell(0).getStringCellValue().equalsIgnoreCase(objName))
				  objPath=row.getCell(1).getStringCellValue();
		  }
		  return objPath;
	}
	public String readXMLData(String tagname) throws SAXException, IOException, ParserConfigurationException {
		  String path=System.getProperty("user.dir")+"//src//test//resources//testData//XMLDanubeData.xml";
		  File file= new File(path);
		  DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
		  DocumentBuilder build=factory.newDocumentBuilder();
		  Document document= build.parse(file);
		  NodeList List= document.getElementsByTagName("DanubeData");
		  Node node1=List.item(0);
		  Element elem=(Element)node1;
		  return elem.getElementsByTagName(tagname).item(0).getTextContent();
	  
	  }
	
	 @DataProvider(name="RegisterTest")
	  public Object[][] getData() throws CsvValidationException, IOException{
		  String path=System.getProperty("user.dir")+"//src//test//resources//testData//DanubeData.csv";
		  String[] cols;
		  CSVReader reader = new CSVReader(new FileReader(path));
		  ArrayList<Object> dataList=new ArrayList<Object>();
		  while((cols=reader.readNext())!=null)
		  {
			  Object[] record= {cols[0], cols[1], cols[2], cols[3], cols[4]};
			  dataList.add(record);
		  }
		  return dataList.toArray(new Object[dataList.size()][]);
		  
	  }
	// @AfterTest
	  public void tearDown() {
		  driver.close();
		  
	  }
}
