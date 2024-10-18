package com.cqcskj.studentsadminvisul.service;

import com.cqcskj.studentsadminvisul.entity.Dormitory;
import com.cqcskj.studentsadminvisul.vo.ResultVo;

public interface DormitoryService {
    ResultVo queryDormitory(Dormitory dormitory);

    ResultVo addDormitory(Dormitory dormitory);

    ResultVo deleteDormitory(int id);
}
