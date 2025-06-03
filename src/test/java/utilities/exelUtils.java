package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class exelUtils {

	String path;
	
	public exelUtils(String path) {
	this.path=path;
	}
	
	public FileInputStream fi;
	public FileOutputStream fo;	
	public XSSFWorkbook wb;
	public XSSFSheet st;
	public XSSFRow ro;
	public XSSFCell cl;
	
	public int getRowCount(String sheetname ) throws Exception {
		fi= new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		st=wb.getSheet(sheetname);
		int rowCount=st.getLastRowNum();
		fi.close();
		wb.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetname, int rowNum ) throws Exception {
		fi= new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		st=wb.getSheet(sheetname);
		int CellCount=st.getRow(rowNum).getLastCellNum();
		fi.close();
		wb.close();
		return CellCount;
	}
	
	public String getCellData(String sheetname,int rowCount, int cellCount) throws Exception {
		
		fi= new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		st=wb.getSheet(sheetname);
		XSSFCell cell =st.getRow(rowCount).getCell(cellCount);
		String data;
		try {
		DataFormatter dataformatter=new DataFormatter();
		
		data=dataformatter.formatCellValue(cell);
	     }
		catch(Exception e) {
			data="";
		}
		
		fi.close();
		wb.close();
		return data;
	}
	
	public void creatAndsetCellData(String sheetname,int rowCount,int CellCount,String data ) throws Exception {
		
		wb=new XSSFWorkbook();
	    st=wb.createSheet(sheetname);
		ro=st.createRow(rowCount);
		cl=ro.createCell(CellCount);
		cl.setCellValue(data);
		fo=new FileOutputStream(path);
		wb.write(fo);
	
		fo.close();
		wb.close();
	}
	
	
        public void setCellData(String sheetname,int rowCount,int CellCount,String data ) throws Exception {
		
        fi=new FileInputStream(path);
        wb=new XSSFWorkbook(fi);
	    st=wb.getSheet(sheetname);
	    if(st.getRow(rowCount)==null) {
	    	ro=st.createRow(rowCount);
	    }
	    else{ro=st.getRow(rowCount);}
		cl=ro.createCell(CellCount);
		cl.setCellValue(data);
		fo=new FileOutputStream(path);
		wb.write(fo);
	
		fo.close();
		wb.close();
	}
	
	
	
	
	
	
	
	
	
	
}
