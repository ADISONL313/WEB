package com.cqcskj.studentsadminvisul.service;

import com.cqcskj.studentsadminvisul.entity.Student;
import com.cqcskj.studentsadminvisul.vo.ResultVo;

import java.util.List;

public interface StudentService {
    ResultVo queryAllStudents();

    ResultVo addOneStudent(Student students);

    ResultVo addStudents(List<Student> students);

    ResultVo collegeStudentCount();

    ResultVo getStudentsTable(Student student);

    ResultVo deleteStudent(Long id);

    ResultVo updateStudent(Student student);

    ResultVo selectStudentToLogin(Long id);

    ResultVo getChinaProvinceCount();

    ResultVo getFacultyGenderBar();
}
