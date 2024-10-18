package com.cqcskj.studentsadminvisul.service;

import com.cqcskj.studentsadminvisul.entity.Classes;
import com.cqcskj.studentsadminvisul.vo.ResultVo;

public interface ClassesService {
    ResultVo insertClasses(Classes classes);

    ResultVo getClassesTableData(Classes classes);
    ResultVo deleteClass(int id);

    ResultVo getClassesWithMajorAndGrade(String grade, int major);
}
