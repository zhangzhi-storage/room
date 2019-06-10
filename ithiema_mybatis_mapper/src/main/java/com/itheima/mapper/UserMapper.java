package com.itheima.mapper;

import com.itheima.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    public List<User> findByCondition(User user);

//    public List<User> findByIds(List<Integer> ids);
    public List<User> findByIds(Integer[] ids);


    public void update(User user);



    public void callProcedure(Map map);
    public void callFunction(Map map);
}
