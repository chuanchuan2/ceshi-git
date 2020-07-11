package com.cloud.consumer.aspectj;
import org.springframework.stereotype.Service;

@Service
public class AserviceImpl implements Aservice {

    @Override
    public int add() {
        System.out.println("进入add方法");
        return 0;
    }

    @Override
    public String delete() {
        return "删除成功！";
    }
}
