package com.cloud.consumer.service.ipml;

import com.cloud.consumer.service.DownLoadService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class DownLoadServiceImpl implements DownLoadService{
    //定义下载模板
    private static final String[] XIAZAIMOBAN= new String[]{"姓名", "年龄"};
    //下载测试
    @Override
    public void downLoadExcle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        OutputStream outputStream = null;
        Workbook workbook = null;
        try {
            outputStream=httpServletResponse.getOutputStream();
            workbook=new SXSSFWorkbook();
            Sheet sheet = workbook.createSheet("下载模板");
            Row titleRow = sheet.createRow(0);
            sheet.setDefaultColumnWidth(20);
            //遍历定义的下载模板
            for (int i = 0; i < XIAZAIMOBAN.length ; i++) {
                Cell cell = titleRow.createCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);//POI 中的CellType类型  值为String类型
                cell.setCellType(HSSFCell.ENCODING_UTF_16);//编码
                cell.setCellValue(XIAZAIMOBAN[i]);
            }
            httpServletResponse.setContentType("application/vnd.ms-excel");
            httpServletResponse.setHeader("content-disposition", "attachment;filename=a.xlsx");
            workbook.write(outputStream);
        } catch (IOException e) {
            System.out.println("模板导出异常");
            e.printStackTrace();
        }finally {
           //关闭资源
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.out.println("模板导出异常");
                }

            }
        }
    }
}
