package com.cqcskj.studentsadminvisul.service;

import com.cqcskj.studentsadminvisul.entity.Teacher;
import com.cqcskj.studentsadminvisul.vo.ResultVo;

public interface TeacherService {
    ResultVo queryAllTeachers(Teacher teacher);

    ResultVo queryOneTeacher(String id);

    ResultVo insertTeacher(Teacher teacher);

    ResultVo changeTeacherInfo(Teacher teacher);
}
