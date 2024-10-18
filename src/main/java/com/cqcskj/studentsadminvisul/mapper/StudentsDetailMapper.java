package com.cqcskj.studentsadminvisul.mapper;

import com.cqcskj.studentsadminvisul.entity.StudentsDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentsDetailMapper {
    List<StudentsDetail> getStudentDetail(Long student_id);
    int addStudentDetail(StudentsDetail studentsDetail);
    @Select("select count(*) from studentsDetail where student_id=#{student_id}")
    int verifyStudentDetail(Long student_id);
    int updateStudentDetail(StudentsDetail studentsDetail);
    List<StudentsDetail> getRoomAndTeacher(Long student_id);
}