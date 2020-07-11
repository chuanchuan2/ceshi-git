package com.cloud.consumer.service.ipml;

import com.cloud.consumer.client.ConsumerClient;
import com.cloud.consumer.dao.SubjectMapper;
import com.cloud.consumer.pojo.Subject;
import com.cloud.consumer.service.SeataTestService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeataTestServiceImpl implements SeataTestService {

    @Autowired
    private ConsumerClient consumerClient;

    @Autowired
    private SubjectMapper subjectMapper;

    //通过分布式事务通过fegin新增一门学科数据
    @GlobalTransactional
    @Transactional
    @Override
    public String feginSeataAdd(Subject subject) {
        //消费者增加一门学科
        int insert = subjectMapper.insert(subject);
        //生产者增加一门学科
        String productResult = consumerClient.feginSeataAdd(subject);

        if (StringUtils.equals("进入fallback，学科新增失败！",productResult)){
            throw new RuntimeException("进入fallback，学科新增失败！");
        }
        if (insert==0){
            throw new RuntimeException("消费者新增失败！");
        }
        return "新增成功";
    }
}
