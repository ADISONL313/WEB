package com.cqcskj.studentsadminvisul.mapper;

import com.cqcskj.studentsadminvisul.entity.Classes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassesMapper {
    @Select("select count(*) from classes where className=#{className} and grade=#{grade}")
    int verifyClass(@Param("className") String className,@Param("grade")String grade);
    @Insert("insert into classes (className,major_id,grade) value(#{className},#{major_id},#{grade})")
    int addClasses(Classes classes);
    List<Classes> selectClasses(Classes classes);
    @Delete("delete from classes where id=#{id}")
    int delClass(int id);
    @Select("select * from classes where grade =#{grade} AND major_id =#{major_id}")
    List<Classes> getClassesByMajorAndGrade(@Param("grade")String grade,@Param("major_id")int major_id);
}
