package com.cqcskj.studentsadminvisul.mapper;

import com.cqcskj.studentsadminvisul.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeacherMapper {
    List<Teacher> getAllTeachers(Teacher teacher);
    @Select("select * from teachers where teacher_id=#{teacher_id}")
    List<Teacher> selectByTeacherId(String teacher_id);
    @Select("select * from teachers where idCard=#{idCard}")
    List<Teacher> selectByIdCard(String idCard);
    int insertOneTeacher(Teacher teacher);
    int updateTeacherInfo(Teacher teacher);
}
