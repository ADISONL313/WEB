package com.cqcskj.studentsadminvisul.service.impl;

import com.cqcskj.studentsadminvisul.entity.Classes;
import com.cqcskj.studentsadminvisul.mapper.ClassesMapper;
import com.cqcskj.studentsadminvisul.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassesServiceImpl implements com.cqcskj.studentsadminvisul.service.ClassesService {
    @Resource
    private ClassesMapper classesMapper;
    @Override
    public ResultVo insertClasses(Classes classes){
        ResultVo vo=null;
        int num=classesMapper.verifyClass(classes.getClassName(),classes.getGrade());
        if(num==0){
            int addNum=classesMapper.addClasses(classes);
            vo = new ResultVo(330, addNum, "添加成功");
        }else {
            vo = new ResultVo(330, num, "添加失败,专业以存在");
        }
        return vo;
    }
    @Override
    public ResultVo getClassesTableData(Classes classes){
        ResultVo vo=null;
        List<Classes> list=classesMapper.selectClasses(classes);
        if(list.isEmpty()){
            vo = new ResultVo(330, list, "无数据");
        }else {
            vo = new ResultVo(330, list, "查询成功");
        }
        return vo;
    }
    @Override
    public ResultVo deleteClass(int id){
        ResultVo vo=null;
        int num=classesMapper.delClass(id);
        if(num==0){
            vo = new ResultVo(330, num, "删除失败");
        }else {
            vo = new ResultVo(331, num, "删除成功");
        }
        return vo;
    }
    @Override
    public ResultVo getClassesWithMajorAndGrade(String grade,int major){
        ResultVo vo=null;
        List<Classes> classes=classesMapper.getClassesByMajorAndGrade(grade,major);
        if(classes.isEmpty()){
            vo = new ResultVo(330, classes, "请先填写该学生的年级");
        }else {
            vo = new ResultVo(331, classes, "查询成功");
        }
        return vo;
    }
}
