package com.parabank.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/ParabankTestData.xlsx";
	private static Workbook workbook;
	private static Sheet  sheet;
	
	public static Object[][] getTestDat(String sheetName){
		FileInputStream fis = null;
		Object data[][] = null;
		
		try {
			fis = new FileInputStream(TEST_DATA_SHEET_PATH);
			workbook = WorkbookFactory.create(fis);
			sheet = workbook.getSheet(sheetName);
			
			if(sheet == null) {
				System.out.println("Sheet with name '" + sheetName + "' does not exist!");
				return new Object[0][0];
			}
			
			int totalRows = sheet.getLastRowNum();
			int totalCols = sheet.getRow(0).getLastCellNum();
			
			data = new Object[totalRows][totalCols];
			DataFormatter formatter = new DataFormatter();
			
			for(int i=0; i<totalRows; i++) {
				Row row = sheet.getRow(i + 1);
				
				for(int j=0; j<totalCols; j++) {
					Cell cell = row.getCell(j);
					
					data[i][j] = (cell != null)? formatter.formatCellValue(cell): "";
				}
			}
			
		} catch (IOException e) {
			System.err.println("Error: Failed to read Excel file.");
			e.printStackTrace();
		} finally {
			try {
				if(workbook != null) {
					workbook.close();
				}
				
				if(fis != null) {
					fis.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return data;
	}

}
