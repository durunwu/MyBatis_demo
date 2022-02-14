package mybatis.mapper;

import mybatis.bean.Department;
import mybatis.bean.Employee;

import java.util.List;

public interface DepartmentMapper {
    Department getDepartmentById(Integer id);

    //多表联查 查询某部门的所有员工
    Department getDeptByIdPlus(Integer id);

    //association分布查询 查询某部门的所有员工
    Department getDeptByIdStep(Integer id);

    //collection分布查询 查询某部门的所有员工
    Department getDeptByIdColl(Integer id);
}
