package com.cqcskj.studentsadminvisul.service;

import com.cqcskj.studentsadminvisul.entity.Major;
import com.cqcskj.studentsadminvisul.vo.ResultVo;
import org.springframework.stereotype.Service;

public interface MajorService {
    ResultVo insertOneMajor(Major major);

    ResultVo queryMajorByCollege(String college);

    ResultVo queryMajorByCollegeAndMajor(String college, String majorName);

    ResultVo delMajorById(int id);

    ResultVo updateMajorName(String majorName, int id);
}
