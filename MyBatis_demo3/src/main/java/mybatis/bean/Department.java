package mybatis.bean;

import java.io.Serializable;
import java.util.List;

public class Department implements Serializable {
    private Integer id;
    private String departmentName;
    private static final long serialVersionID = 1L;

    public Department(Integer id) {
        this.id = id;
    }

    //查询 某个部门的所有员工
    private List<Employee> empList;

    public List<Employee> getEmpList() {
        return empList;
    }

    @Override
    public String toString() {
        return "department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department(Integer id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public Department() {
    }


}
