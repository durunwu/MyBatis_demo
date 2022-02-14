package mybatis.mapper;

import mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDynamicSQL {

    List<Employee> getEmpsByConditionIf(Employee employee);

    void updateEmployee(Employee employee);

    List<Employee> getEmpListId(List<Integer> ids);

    //批量添加
    void addEmp(@Param("emps") List<Employee> employeeList);

    void addEmp02(@Param("emps") List<Employee> employeeList);
}
