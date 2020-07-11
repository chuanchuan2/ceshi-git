package com.cloud.consumer.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

public class ExcleUtils {
    private static final String XLS = ".xls";
    private static final String XLSX = ".xlsx";

    //创建workBook
    public static Workbook createWorkBook(InputStream inputStream, String filename) throws IOException {
        if (filename.endsWith(XLS)) {
            //2003的版本
            return new HSSFWorkbook(inputStream);
        }else if(filename.endsWith(XLSX)){
            //2007年以后的版本
            return new XSSFWorkbook(inputStream);
        }
        return null;
    }

    //创建sheet
    public static Sheet creatSheet(Workbook workbook, int sheetIndex) {
        return workbook.getSheetAt(sheetIndex);
    }
}