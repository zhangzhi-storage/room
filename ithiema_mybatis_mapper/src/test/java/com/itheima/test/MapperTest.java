package com.itheima.test;

import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperTest {
    //动态sql if
    @Test
    public void testFindByCondition() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User condition = new User();
        condition.setUsername("haohao");
        condition.setPassword("123");
        condition.setBirthday(new Date());

        List<User> users = mapper.findByCondition(condition);
        System.out.println(users);
    }

    //动态sql foreach
    @Test
    public void testFindByIds() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

//        List<Integer> ids = new ArrayList<Integer>();
//            ids.add(1);
//            ids.add(2);
//            ids.add(3);
        Integer[] ids = new Integer[] {1,3,6,9};

        List<User> users = mapper.findByIds(ids);
        System.out.println(users);

    }




    @Test
    public void testUpdate() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setId(9);
        user.setUsername("jerry3");
        user.setPassword("123456");

        mapper.update(user);
        sqlSession.commit();
        sqlSession.close();
    }




    @Test
    public void testP() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map map=new HashMap();
        map.put("p1", 7788);
        System.out.println("----"+map);
        mapper.callProcedure(map);
        System.out.println("----"+map);
        System.out.println("----=="+map.get("p2"));
    }
    @Test
    public void testF() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map map=new HashMap();
        map.put("p1", 7788);
        mapper.callFunction(map);
        System.out.println("------"+map.get("p2"));
    }
}
