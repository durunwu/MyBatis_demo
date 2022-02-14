package mybatis.mapper;

import mybatis.bean.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeMapperPlus {

    //测试 ResultMap
    Employee getEmployeeById(Integer id);

    //级联查询
    Employee getEmployeeByIdTest(Integer id);

    //分布查询
    Employee getEmployeeByIdStep(Integer id);

    //association分布查询 查询某部门的所有员工
    List<Employee> getEmpList(Integer deptId);

    //collection分布查询 查询某部门的所有员工
    List<Employee> getEmpListCollection(Integer id);



}
