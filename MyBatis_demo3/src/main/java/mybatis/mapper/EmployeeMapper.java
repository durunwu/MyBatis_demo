package mybatis.mapper;

import mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    //查询
    Employee getEmployee(Integer id);
    //传多个值查询时,因为参数被封装到Map集合,无序无法确定是那个参数,单个传参不影响,通过设置注解获取
    Employee getEmployeeTest(@Param("id") Integer id, @Param("lastName") String lastName);
    //传入map集合
    Employee getEmpAfferentMap(Map<String,Object> map);
    //查询多条语句封装到List
    List<Employee> getEmpAfferentList(String lastName);

    //返回Map集合 key是字段名 value是参数
    Map<String,Object> getEmpAfferentReturnMap(Integer id);

    //返回 Map集合 key 为 主键 ，value 为 POJO对象
    @MapKey("lastName")
    Map<Integer,Employee> getEmpReturnPrimaryKeyAndEmp(String lastName);

    //添加
    void addEmployee(Employee employee);
    //修改
    void updateEmployee(Employee employee);
    //删除
    void deleteEmployee(Integer id);

}
