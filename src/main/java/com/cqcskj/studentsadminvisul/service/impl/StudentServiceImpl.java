package com.cqcskj.studentsadminvisul.service.impl;

import com.cqcskj.studentsadminvisul.entity.GraphCount;
import com.cqcskj.studentsadminvisul.entity.Student;
import com.cqcskj.studentsadminvisul.mapper.StudentMapper;
import com.cqcskj.studentsadminvisul.service.StudentService;
import com.cqcskj.studentsadminvisul.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Resource

    private StudentMapper studentMapper;
    @Override
    public ResultVo queryAllStudents(){
        ResultVo vo=null;
        int num= studentMapper.selectStudents();
        if (num==0) {//判断是否为空
            vo = new ResultVo(331, num, "无用户信息");
        } else {
            vo = new ResultVo(330, num, "查询成功");
        }
        //返回通用对象
        return vo;
    }
    @Override
    public ResultVo addOneStudent(Student students){
        ResultVo vo=null;
        String card=studentMapper.verifyStudent(students.getId_card());
        if (card==null) {//判断是否为空
            int num=studentMapper.insertOneStudent(students);
            vo = new ResultVo(330, num, "添加成功");
        } else {
            vo = new ResultVo(330, card, "学生已存在");
        }
        //返回通用对象
        return vo;
    }
    @Override
    public ResultVo addStudents(List<Student> students){
        ResultVo vo=null;
            int num=studentMapper.insertStudents(students);
            if (num==0) {//判断是否为空
                vo = new ResultVo(331, num, "添加失败");
            } else {
                vo = new ResultVo(330, num, "添加成功");
            }
        //返回通用对象
        return vo;
    }
    @Override
    public ResultVo collegeStudentCount(){
        ResultVo vo=null;
        List<GraphCount> students=studentMapper.collegeCount();
        if (students.isEmpty()) {//判断是否为空
            vo = new ResultVo(331, students, "查询失败");
        } else {
            vo = new ResultVo(330, students, "查询成功");
        }
        //返回通用对象
        return vo;
    }
    @Override
    public ResultVo getStudentsTable(Student student){
        ResultVo vo=null;
        List<Student> students=studentMapper.getStudentsTableData(student);
        if (students.isEmpty()) {//判断是否为空
            vo = new ResultVo(331, students, "查询失败");
        } else {
            vo = new ResultVo(330, students, "查询成功");
        }
        //返回通用对象
        return vo;
    }
    @Override
    public ResultVo deleteStudent(Long id){
        ResultVo vo=null;
        int num=studentMapper.delStudentById(id);
        if (num==0) {//判断是否为空
            vo = new ResultVo(331, num, "删除失败");
        } else {
            vo = new ResultVo(330, num, "删除成功");
        }
        //返回通用对象
        return vo;
    }
    @Override
    public ResultVo updateStudent(Student student){
        ResultVo vo=null;
        int num=studentMapper.updateStudentInfo(student);
        if (num==0) {//判断是否为空
            vo = new ResultVo(331, num, "更新失败");
        } else {
            vo = new ResultVo(330, num, "更新成功");
        }
        //返回通用对象
        return vo;
    }
    @Override
    public ResultVo selectStudentToLogin(Long id){
        ResultVo vo=null;
        List<Student> student=studentMapper.selectForLogin(id);
        if (student.isEmpty()) {//判断是否为空
            vo = new ResultVo(331, null, "无用户信息");
        } else {
            vo = new ResultVo(330, student, "用户存在");
        }
        //返回通用对象
        return vo;
    }
    @Override
    public ResultVo getChinaProvinceCount() {
        ResultVo vo=null;
        List<GraphCount> graphCount=studentMapper.mapChinaProvinceCount();
        if(graphCount.isEmpty()){
            vo = new ResultVo(331, graphCount, "无信息");
        } else {
            vo = new ResultVo(330, graphCount, "查询成功");
        }
        //返回通用对象
        return vo;
    }
    @Override
    public ResultVo getFacultyGenderBar() {
        ResultVo vo=null;
        List<GraphCount> graphCount=studentMapper.barForGenderStatistics();
        if(graphCount.isEmpty()){
            vo = new ResultVo(331, graphCount, "无信息");
        } else {
            vo = new ResultVo(330, graphCount, "查询成功");
        }
        //返回通用对象
        return vo;
    }
}
