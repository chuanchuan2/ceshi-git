package com.cloud.consumer.service.ipml;

import com.cloud.consumer.dao.SubjectMapper;
import com.cloud.consumer.pojo.Subject;
import com.cloud.consumer.service.SubjectService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("ALL")
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    //校验数据库是否配置连接成功
    @Override
    public Subject searchMySqlDate(String id) {
        return  subjectMapper.selectByPrimaryKey(Integer.valueOf(id));
    }

    //测试mybatis的pageHelper分页插件
    @Override
    public List<Subject> subjectList(String currentPage, String rows) {
        PageHelper.startPage(Integer.valueOf(currentPage),Integer.valueOf(rows));
        //分页查询数据
        return subjectMapper.selectAll();
    }

    //测试存储过程查询
    @Override
    public int searchMySqlPprocedure(String subjectName) {
        return subjectMapper.callSubject(subjectName,0);
    }

    //测试存储过程查询根据ID查询学科数据
    @Override
    public Subject searchBySid(String id) {
        return subjectMapper.searchBySid(id);
    }
}
