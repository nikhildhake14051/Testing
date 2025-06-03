package testBase;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import utilities.exelUtils;


public class commonBase {
    
	public Logger logger;
	public static WebDriver driver;
	
	@Parameters({"os","br"})
	@BeforeTest(groups= {"sanity"})
	public void setup(String os,String br) throws Exception {
		
		logger=LogManager.getLogger(this.getClass());
		
		FileReader file=new FileReader("./src//test//resources//LoginData.properties");
		Properties p= new Properties();
		p.load(file);
		System.out.println(p.getProperty("environment"));
		
		if(p.getProperty("environment").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities=new DesiredCapabilities();
			//Operating system of remote environment
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("Invalid OS input");
				return;
			}
			//browser
		
			switch(br.toLowerCase()) 
			{
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge": capabilities.setBrowserName("MicrosfotEdge");break;
			case "firefox":capabilities.setBrowserName("fireFox");break;
			default: System.out.println("Incorrect input server");return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444"),capabilities);// http://192.168.0.9:4444
			
			
		}
		
		
		
		if(p.getProperty("environment").equalsIgnoreCase("local")) {
		switch(br.toLowerCase()) 
		{
		case "chrome":driver=new ChromeDriver();break;
		case "edge": driver=new EdgeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
		default: System.out.println("Incorrect input server");return;
		}
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		logger.info("------Launching website------");
		
		driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
	}
	
	@AfterTest
	public void wrapup() {
		driver.quit();
}
	
	public String randomAlpha() {
		String generateRandom=RandomStringUtils.randomAlphabetic(5);
		return generateRandom;
	}
	public String randomAlphaNum() {
		String generateRandom=RandomStringUtils.randomAlphanumeric(8);
		return generateRandom;
	}
	public String randomNum() {
		String generateRandom=RandomStringUtils.randomNumeric(10);
		return generateRandom;
	}
	
	public String captureScreenshot(String name) {
		
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot TS= (TakesScreenshot) driver;
		
		File sourceFile=TS.getScreenshotAs(OutputType.FILE);
		
		String targetFile=System.getProperty("user.dir")+"\\screenshots\\"+name+"_"+timeStamp+".png";
		
		File TargetFile= new File (targetFile);
		 sourceFile.renameTo(TargetFile);
		 
		
		return targetFile;
	}
	
	
	
	@DataProvider(name="Logindata")
	public String[][] data() throws Exception{
		String path=(".\\testData\\openHRM_LoginData.xlsx");
		exelUtils exutil = new exelUtils(path);
		
		int rcount=exutil.getRowCount("sheet");
		int cCount=exutil.getCellCount("sheet1", 1);
		String LogData[][]=new String [rcount][cCount];
		for(int i=1;i<=rcount;i++) {
			for(int j=0;j<cCount;j++) {
			 LogData[i-1][j]=exutil.getCellData("sheet1", i, j);
			}
		}	
		return LogData;
	}
	
	
}
