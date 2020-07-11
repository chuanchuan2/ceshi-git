package com.cloud.product.service.impl;

import com.cloud.product.dao.SubjectMapper;
import com.cloud.product.pojo.Subject;
import com.cloud.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class productServiceImpl implements ProductService {

    @Autowired
    private SubjectMapper subjectMapper;

    //通过fegin访问数据库测试返回实体
    @Override
    public String testConsummer(String id) {
        return "889";
    }


    //通过fegin访问数据库测试返回实体
    @Override
    public Subject searchDateBase(String id) {
        return subjectMapper.selectByPrimaryKey(Integer.valueOf(id));
    }

    //通过分布式事务通过fegin新增一门学科数据
    @Transactional
    @Override
    public String feginSeataAdd(Subject subject) {
        //subjectMapper.updateByPrimaryKey(subject);
        /*int a=0;
        int b=a/0;*/
        int insert = subjectMapper.insert(subject);
        if (insert>0){
            return "新增成功啦";
        }
        return  "新增失败啦";
    }


}
