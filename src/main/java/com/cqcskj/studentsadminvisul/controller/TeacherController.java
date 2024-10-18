package com.cqcskj.studentsadminvisul.controller;

import com.cqcskj.studentsadminvisul.entity.Teacher;
import com.cqcskj.studentsadminvisul.service.TeacherService;
import com.cqcskj.studentsadminvisul.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin(origins ={"http://7512e470.r5.cpolar.top","*"} )
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private TeacherService teacherService;
    @RequestMapping("/all")
    public ResultVo queryAllTeachers(@RequestBody Teacher teacher) {
        return teacherService.queryAllTeachers(teacher);
    }
    @RequestMapping("/one")
    public ResultVo queryOneTeacher(@RequestParam("teacher_id")String id){
        return  teacherService.queryOneTeacher(id);
    }
    @RequestMapping("/addTeacher")
    public ResultVo insertTeacher(@RequestBody Teacher teacher){
        System.out.println(teacher.getGender()+"tip");
        return  teacherService.insertTeacher(teacher);
    }
    @RequestMapping("/updateTeacher")
    public ResultVo changeTeacherInfo(@RequestBody Teacher teacher){
        return teacherService.changeTeacherInfo(teacher);
    }
}
