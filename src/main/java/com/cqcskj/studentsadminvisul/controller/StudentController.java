package com.cqcskj.studentsadminvisul.controller;

import com.cqcskj.studentsadminvisul.entity.Student;
import com.cqcskj.studentsadminvisul.service.StudentService;
import com.cqcskj.studentsadminvisul.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins ={"http://7512e470.r5.cpolar.top","*"} )
@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService studentService;

        @RequestMapping("/all")
    public ResultVo queryAllStudents() {
        return studentService.queryAllStudents();
    }
    @RequestMapping("/selectForLogin")
    public ResultVo selectStudentToLogin(@RequestParam("student_id")Long id){
        return studentService.selectStudentToLogin(id);
    }
    @RequestMapping("/insertOne")
    public ResultVo addOneStudent(@RequestBody Student student){
        return studentService.addOneStudent(student);
    }
    @RequestMapping("/insertStudents")
    public  ResultVo addStudents(@RequestBody List<Student> students){
        return  studentService.addStudents(students);
    }
    @RequestMapping("/collegeCount")
    public  ResultVo collegeStudentCount(){
        return  studentService.collegeStudentCount();
    }
    @RequestMapping("/selectStudentByCounselor")
    public ResultVo getStudentsTable(@RequestBody Student student){
        return  studentService.getStudentsTable(student);
    }
    @RequestMapping("/delStudent")
    public ResultVo deleteStudent(@RequestParam("student_id") Long id){
        return  studentService.deleteStudent(id);
    }
    @RequestMapping("/updateStudent")
    public ResultVo updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }
    @RequestMapping("getChinaMap")
    public ResultVo getChinaProvinceCount(){
        return studentService.getChinaProvinceCount();
    }
    @RequestMapping("getGenderBar")
    public ResultVo getFacultyGenderBar(){
        return studentService.getFacultyGenderBar();
    }
}
