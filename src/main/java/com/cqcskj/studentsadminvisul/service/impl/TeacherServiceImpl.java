package com.cqcskj.studentsadminvisul.service.impl;

import com.cqcskj.studentsadminvisul.entity.Teacher;
import com.cqcskj.studentsadminvisul.mapper.TeacherMapper;
import com.cqcskj.studentsadminvisul.service.TeacherService;
import com.cqcskj.studentsadminvisul.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;
    @Override
    public ResultVo queryAllTeachers(Teacher teacher){
        ResultVo vo=null;
        List<Teacher> teachers= teacherMapper.getAllTeachers(teacher);
        if (teachers.isEmpty()) {//判断是否为空
            vo = new ResultVo(331, null, "无用户信息");
        } else {
            vo = new ResultVo(330, teachers, "查询成功");
        }
        //返回通用对象
        return vo;
    }
    @Override
    public ResultVo queryOneTeacher(String id){
        ResultVo vo=null;
        List<Teacher> teachers=teacherMapper.selectByTeacherId(id);
        if (teachers.isEmpty()) {//判断是否为空
            vo = new ResultVo(331, null, "无用户信息");
        } else {
            vo = new ResultVo(330, teachers, "查询成功");
        }
        //返回通用对象
        return vo;
    }
    @Override
    public ResultVo insertTeacher(Teacher teacher){
        ResultVo vo=null;
        List<Teacher> teachers=teacherMapper.selectByIdCard(teacher.getIdCard());
        if (teachers.isEmpty()) {//判断是否为空
            int num=teacherMapper.insertOneTeacher(teacher);
            if (num==1) {//判断是否为空
                vo = new ResultVo(330, num, "注册成功");
            } else {
                vo = new ResultVo(331, null, "注册失败");
            }
        } else {
            vo=new ResultVo(331,"用户已存在","注册失败");
        }
        //返回通用对象
        return vo;
    }
    @Override
    public ResultVo changeTeacherInfo(Teacher teacher){
        ResultVo vo=null;
        int num=teacherMapper.updateTeacherInfo(teacher);
        if (num==0) {//判断是否为空
            vo = new ResultVo(331, num, "修改失败");
        } else {
            vo = new ResultVo(330, num, "修改成功");
        }
        //返回通用对象
        return vo;
    }
}
