package com.cloud.consumer.service.ipml;

import com.cloud.consumer.pojo.Result;
import com.cloud.consumer.pojo.Student;
import com.cloud.consumer.service.UpLoadService;
import com.cloud.consumer.utils.ExcleUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl implements UpLoadService {

    //开始根据上传数据组装实体bean
    @Override
    public Result upLoadExcle(Workbook workBook, String filename,Result result) {
        StringBuffer stringBuffer = new StringBuffer();
        //获取sheet
        Sheet sheet = ExcleUtils.creatSheet(workBook, 0);
        //遍历Sheet获取每行数据
        for (int i = 1; i <=sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row.getCell(0) == null || row.getCell(1 )== null) {
                stringBuffer.append("第" + i + "行校验失败，出现空列");
                break;
            }
            String name=row.getCell(0).toString();
                    //.split("\\.")[0].trim();
            String age=row.getCell(1).toString();
                    //.split("\\.")[0].trim();
            Student student=new Student();
            student.setName(name);
            student.setAge(age);
            System.out.println(student.toString());
        }
        if (stringBuffer.length()>0){
            result.setCode("0");
            result.setMsg(stringBuffer.toString());
        }else {
            result.setCode("1");
            result.setMsg("文件上传成功！");
        }
        return result;
    }
}
