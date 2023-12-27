package com.CCPAAutomation.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class ReadExcelData {

	static FileInputStream fi;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;	
	static int rowcount;
	static int cellcount;
	static String filepath = System.getProperty("user.dir")+"/TestData/TestCases.xlsx";
	
	public int GetRowcount(String sheetName) throws IOException {
		fi = new FileInputStream(filepath);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;
	}
	public int GetCellCount(String sheetName) throws IOException {
		fi = new FileInputStream(filepath);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int cellCount = sheet.getRow(0).getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
	}
	
	public static List<Map<String,String>> GetSheetData(String sheetName){
		HashMap<String,String> map;
		List<Map<String,String>> mapList=null;
		try {			
			fi = new FileInputStream(filepath);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			rowcount = sheet.getLastRowNum();
			cellcount = sheet.getRow(0).getLastCellNum();
			/*for(int i=1;i<=rowcount;i++) {
				map=new HashMap<String,String>();
				for(int j=0;j<cellcount;j++) {					
					try {
						map.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i).getCell(j).toString());
					}catch(Exception e) {
						map.put(sheet.getRow(0).getCell(j).toString(), "");
					}					
				}
				mapList.add(map);
			}	*/		
			mapList =IntStream.range(1, rowcount+1).mapToObj(i-> IntStream.range(0, cellcount).mapToObj(j->j).collect(Collectors.toMap(k -> sheet.getRow(0).getCell(k).toString(), k -> sheet.getRow(i).getCell(k).toString()))).collect(Collectors.toList());
			//List<List<String>> lst = IntStream.range(1,rowcount+1).mapToObj(i-> IntStream.range(0,cellcount).mapToObj(j-> sheet.getRow(i).getCell(j).toString()).collect(Collectors.toList())).collect(Collectors.toList());
			System.out.println(mapList);
			//System.out.println(lst.parallelStream().filter(mp -> mp.get("FileFormat").equals("xls")).collect(Collectors.toList()));
			workbook.close();
			fi.close();
		}catch(Exception e) {
			Assert.fail(e.getMessage());
		}
		return mapList;
	}
	
	@DataProvider(name="testData")
	public Object[][] dataproviding(ITestContext context){	
		System.out.println(context.getName());
		List<Map<String,String>> testdatalist = GetSheetData(context.getName());
		System.out.println(testdatalist);
		Object[][] obj = new Object[testdatalist.size()][1];
		for(int i =0;i<testdatalist.size();i++) {
			HashMap<String,String> testDataMap;
			try {
				testDataMap=new HashMap<String,String>(testdatalist.get(i));
				obj[i][0] = testDataMap;			
			}catch(Exception e) {
					Assert.fail(e.getMessage());
			}
		}		
		return obj;
	}
	
}
