package com.cqcskj.studentsadminvisul.service;

import com.cqcskj.studentsadminvisul.entity.Affairs;
import com.cqcskj.studentsadminvisul.vo.ResultVo;

public interface AffairsService {
    ResultVo insertAffair(Affairs affairs);

    ResultVo queryAffairsByTeacher(String teacher_id);

    ResultVo updateAffairsState(Affairs affairs);

    ResultVo queryAllAffairs(Affairs affairs);

    ResultVo deleteAffairs(int id);
}
