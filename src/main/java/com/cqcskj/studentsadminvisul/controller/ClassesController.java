package com.cqcskj.studentsadminvisul.controller;

import com.cqcskj.studentsadminvisul.entity.Classes;
import com.cqcskj.studentsadminvisul.service.ClassesService;
import com.cqcskj.studentsadminvisul.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/classes")
@CrossOrigin(origins ={"http://7512e470.r5.cpolar.top","*"} )
public class ClassesController {
    @Resource
    private ClassesService classesService;
    @RequestMapping("/addClass")
    public ResultVo insertClasses(@RequestBody Classes classes){
        return classesService.insertClasses(classes);
    }
    @RequestMapping("/getClassesTableData")
    public ResultVo getClassesTableData(@RequestBody Classes classes){
        return classesService.getClassesTableData(classes);
    }
    @RequestMapping("/delClass")
    public ResultVo deleteClass(int id){
        return classesService.deleteClass(id);
    }
    @RequestMapping("/getClassesByMajor")
    public ResultVo getClassesWithMajorAndGrade(@RequestParam("grade")String grade,@RequestParam("major_id")int major_id){
        return classesService.getClassesWithMajorAndGrade(grade,major_id);
    }
}
