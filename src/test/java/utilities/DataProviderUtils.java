package utilities;

import org.testng.annotations.DataProvider;

public class DataProviderUtils {

	
//	Dataprovider1
	
	@DataProvider(name="loginData")
	public String[][] data() throws Exception{
		String path=(".\\testData\\openHRM_LoginData.xlsx");
		exelUtils exutil = new exelUtils(path);
		
		int rcount=exutil.getRowCount("sheet1");
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
