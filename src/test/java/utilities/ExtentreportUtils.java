package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.commonBase;

public class ExtentreportUtils implements ITestListener {

	public ExtentSparkReporter exSpark;
	public ExtentReports exReports;
	public ExtentTest exTest;
	
	String repName;
	
	public void onStart(ITestContext context) {
	  
	//SimpleDateFormat SDFormat= new SimpleDateFormat();
		//Date dt=new Date();
		//String currentDatetime=SDFormat.format(dt);
		
		String currentDT=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName="Test Report"+currentDT+".html";
		
		exSpark=new ExtentSparkReporter(".\\reports\\"+repName);
		
		exSpark.config().setDocumentTitle("OpenHRM Document Automation Report");
		exSpark.config().setReportName("OpenHRM Testing Report");
		exSpark.config().setTheme(Theme.DARK);
		
		exReports=new ExtentReports();
		exReports.attachReporter(exSpark);
		exReports.setSystemInfo("Application", "OpenHRM");
		exReports.setSystemInfo("Module", "Admin");
		exReports.setSystemInfo("Sub Modul", "Customers");
		exReports.setSystemInfo("User Name", System.getProperty("user.name"));
		exReports.setSystemInfo("Environment", "QA");
		
		String os=context.getCurrentXmlTest().getParameter("os");
		exReports.setSystemInfo("Operating System", os);
		
		String br=context.getCurrentXmlTest().getParameter("br");
		exReports.setSystemInfo("Browser", br);
		
		
		
	
		
		
	}
	
	 public void onTestStart(ITestResult result) {
		    // not implemented
		  }
	 
	 public void onTestSuccess(ITestResult result) {
	     //capture class name
		 exTest=exReports.createTest(result.getTestClass().getName());
		 
		 //would attach all groups which link to the method
		 //exTest.assignCategory(result.getMethod().getGroups());
		 
		 exTest.log(Status.PASS, result.getName()+"got Successfully executed");
		 
		  }
	 
	 public void onTestFailure(ITestResult result) {
		  
		 exTest=exReports.createTest(result.getTestClass().getName());
		 
		 exTest.log(Status.FAIL, result.getName()+"got Failed");
		 exTest.log(Status.INFO,result.getThrowable().getMessage());
		 
		 try {
			 
			 String filePath=new commonBase().captureScreenshot(result.getName());
			 exTest.addScreenCaptureFromPath(filePath);
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		  }
	 
	 public void onTestSkipped(ITestResult result) {
		    exTest=exReports.createTest(result.getTestClass().getName());
		    exTest.log(Status.SKIP, result.getName()+"got Skipped");
		    exTest.log(Status.INFO, result.getThrowable().getMessage());
		  }
	 
	 
	 
	 
	 
	 public void onFinish(ITestContext context) {
		    exReports.flush();
		    
		    String extentReportpath=System.getProperty("user.dir")+"\\reports\\"+repName;
		    File extentReport=new File(extentReportpath);
		    
		    try {
		    	Desktop.getDesktop().browse(extentReport.toURI());
		    }
		    catch(Exception e) {
		    	e.printStackTrace();
		    }
		    
		    
		  }
	
}
