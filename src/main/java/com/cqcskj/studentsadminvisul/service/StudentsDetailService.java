package com.cqcskj.studentsadminvisul.service;

import com.cqcskj.studentsadminvisul.entity.StudentsDetail;
import com.cqcskj.studentsadminvisul.vo.ResultVo;

public interface StudentsDetailService {
    ResultVo selectStudentDetail(Long id);

    ResultVo updateStudentDetailById(StudentsDetail studentsDetail);

    ResultVo selectRoomAndTeacher(Long student_id);
}
