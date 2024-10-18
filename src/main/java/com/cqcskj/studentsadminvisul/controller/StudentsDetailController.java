package com.cqcskj.studentsadminvisul.controller;

import com.cqcskj.studentsadminvisul.entity.StudentsDetail;
import com.cqcskj.studentsadminvisul.service.StudentsDetailService;
import com.cqcskj.studentsadminvisul.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/studentDetail")
@CrossOrigin(origins ={"http://7512e470.r5.cpolar.top","*"} )
public class StudentsDetailController {
    @Resource
    private StudentsDetailService studentsDetailService;
    @RequestMapping("/getStudentDetail")
    public ResultVo selectStudentDetail(@RequestParam("student_id") Long id){
        return studentsDetailService.selectStudentDetail(id);
    }
    @RequestMapping("/updateStudentDetail")
    public ResultVo updateStudentDetailById(@RequestBody StudentsDetail studentsDetail){
        return studentsDetailService.updateStudentDetailById(studentsDetail);
    }
    @RequestMapping("/getRoomAndTeacher")
    public ResultVo selectRoomAndTeacher(@RequestParam("student_id")Long student_id){
        return studentsDetailService.selectRoomAndTeacher(student_id);
    }
}
