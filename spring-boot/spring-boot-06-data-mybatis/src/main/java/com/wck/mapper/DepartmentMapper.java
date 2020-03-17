package com.wck.mapper;

import com.wck.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * @author 御香烤翅
 * @create 2020-03-17 19:31
 */
//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    Department findByid(Integer id);

    @Delete("delete from department where id=#{id}")
    int deleteDepartmentById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) values(#{departmentName})")
    int insertDepartment(Department department);

    @Update("update department set departmentName = #{departmentName}")
    int updateDepartment(Department department);


}
