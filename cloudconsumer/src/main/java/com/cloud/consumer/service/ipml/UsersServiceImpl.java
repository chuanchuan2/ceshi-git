package com.cloud.consumer.service.ipml;

import com.cloud.consumer.dao.UsersMapper;
import com.cloud.consumer.pojo.Users;
import com.cloud.consumer.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;

    //新增用户信息
    @Override
    public String addUser(String userName,String passWord) {
        Users users=new Users();
        //Integer integer = Integer.valueOf(UUID.randomUUID().toString());
        users.setUserid(1);
        users.setUserName(userName);
        users.setUserPassword(passWord);
        int insert = usersMapper.insert(users);
        if (insert>0){
            return "添加成功！";
        }
        return "添加失败！";
    }
}
