package mybatis.test;

import mybatis.bean.Department;
import mybatis.bean.Employee;
import mybatis.mapper.DepartmentMapper;
import mybatis.mapper.EmployeeDynamicSQL;
import mybatis.mapper.EmployeeMapper;
import mybatis.mapper.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyBatisTest {

    //获取 SqlSessionFactory
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        return sqlSessionFactory;
    }

    @Test
    public void test01() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        try{
//            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            //查询
//            System.out.println(mapper.getEmployee(1));
//            System.out.println(mapper.getEmployeeTest(1,"李白"));

            //传入两个查询参数 id 和 lastName ，把这两个参数封装到 Map ，在把 Map传入 mybtis会自动从Map解析出参数
//            Map<String, Object> map = new HashMap<>();
//            map.put("id",1);
//            map.put("lastName","李白");
//            Employee empAfferentMap = mapper.getEmpAfferentMap(map);
//            System.out.println(empAfferentMap.toString());

//            List<Employee> empAfferentList = mapper.getEmpAfferentList("%e%");
//            for(Employee employee : empAfferentList) System.out.println(employee);
//            System.out.println("查询成功！");

            //查询结果封装到Map返回
//            Map<String, Object> empAfferentReturnMap = mapper.getEmpAfferentReturnMap(1);
//            System.out.println(empAfferentReturnMap);


            //Map<Primarykey,Employee>
//            Map<Integer, Employee> empReturnPrimaryKeyAndEmp = mapper.getEmpReturnPrimaryKeyAndEmp("%e%");
//            System.out.println(empReturnPrimaryKeyAndEmp);

            //添加
//            Employee employee = new Employee(null,"runwu", "runwu@123", "男");
//            mapper.addEmployee(employee);
//            System.out.println(employee.getId());
//            System.out.println("添加成功!");

            //修改
//            Employee employee = new Employee(1,"李白", "libai@haha", "男");
//            mapper.updateEmployee(employee);
//            System.out.println("修改完成!");

            //删除
//            mapper.deleteEmployee(6);
//            System.out.println("删除成功!");

//            sqlSession.commit();//手动提交
        }finally {
            sqlSession.close();
        }
    }


    //自定义结果映射集 resultMap
    @Test
    public void testResultMap() throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        try {
//            EmployeeMapperPlus sqlSessionMapper = sqlSession.getMapper(EmployeeMapperPlus.class);
//            Employee employeeById = sqlSessionMapper.getEmployeeById(1);
//            System.out.println(employeeById);

//            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
//            Employee employee = mapper.getEmployeeByIdTest(2);
//            System.out.println(employee);
//            System.out.println(employee.getDept() );

            //分布查询
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employeeByIdStep = mapper.getEmployeeByIdStep(9);
//            System.out.println(employeeByIdStep);
//            System.out.println(employeeByIdStep.getDept());
            System.out.println(employeeByIdStep.getLastName());

        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void test03() throws IOException{
        SqlSession openSession = getSqlSessionFactory().openSession();
        try{
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
//            Department deptByIdPlus = mapper.getDeptByIdPlus(1);
//            System.out.println(deptByIdPlus);
//            System.out.println(deptByIdPlus.getEmpList());

            Department deptByIdStep = mapper.getDeptByIdStep(1);
            System.out.println(deptByIdStep);
            System.out.println(deptByIdStep.getEmpList());

//            Department deptByIdColl = mapper.getDeptByIdColl(1);
//            System.out.println(deptByIdColl);
//            System.out.println(deptByIdColl.getEmpList());
        }finally {
            openSession.close();
        }
    }

    @Test
    public void testDynamicSQL() throws IOException{
        SqlSession openSession = getSqlSessionFactory().openSession(true);
        try{
            EmployeeDynamicSQL mapper = openSession.getMapper(EmployeeDynamicSQL.class);
            //Integer id,String lastName, String email, String gender
//            Employee employee = new Employee(null,null,null,null);
//            List<Employee> empsByConditionIf = mapper.getEmpsByConditionIf(employee);
//            System.out.println(empsByConditionIf);

//            mapper.updateEmployee(new Employee(1,"徐凤年",null,null));

            List<Integer> integers = Arrays.asList(1, 2, 3);
            List<Employee> empListId = mapper.getEmpListId(integers);
            System.out.println(empListId);

        }finally {
            openSession.close();
        }
    }

    @Test
    public void testBatchAdd() throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        try{
            EmployeeDynamicSQL mapper = sqlSession.getMapper(EmployeeDynamicSQL.class);
            List<Employee> list = new ArrayList<>();
            list.add(new Employee(null,"log1","qwe@qwe","男",new Department(1)));
            list.add(new Employee(null,"log2","zxc@qwe","男",new Department(2)));
            list.add(new Employee(null,"log3","asd@qwe","男",new Department(3)));
            mapper.addEmp02(list);
            System.out.println("添加成功！");
        }finally {
            sqlSession.close();
        }
        //1.为什么不能 new Department().setId()   答：Employee的属性是dept，所有只能使用构造器
        //2.为什么括号要放在foreach里面   答：用的脑子好吗
    }

    //二级缓存
    @Test
    public void testCache() throws IOException{
        //实现二级缓存必须是同一个SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        EmployeeMapper mapper1 = sqlSession1.getMapper(EmployeeMapper.class);
        EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);

        System.out.println(mapper1.getEmployee(1));
        sqlSession1.close();
        System.out.println("sqlSession1关闭.....................");

        System.out.println(mapper2.getEmployee(1));
        sqlSession2.close();

    }
}
