package com.atguigu.crud.test;

import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
    测试dao层工作
    1.导入SpringTest模块
    2.@ContextConfiguration指定Spring配置文件的位置
    3.直接autowired要使用的组件即可
 */

////指定单元测试模块
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Test
    public void testCRUD(){
//        //创建 spingioc容器
//        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//        //从ioc容器中获取bean
//        DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
//        System.out.println(bean);

        System.out.println(departmentMapper);

    }



}
