package com.cqcskj.studentsadminvisul.service.impl;

import com.cqcskj.studentsadminvisul.entity.StudentsDetail;
import com.cqcskj.studentsadminvisul.mapper.StudentsDetailMapper;
import com.cqcskj.studentsadminvisul.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentsDetailServiceImpl implements com.cqcskj.studentsadminvisul.service.StudentsDetailService {
    @Resource
    private StudentsDetailMapper studentsDetailMapper;

    @Override
    public ResultVo selectStudentDetail(Long id){
        ResultVo vo=null;
        List<StudentsDetail> studentsDetails =studentsDetailMapper.getStudentDetail(id);
        if(studentsDetails.isEmpty()){
            vo = new ResultVo(330, studentsDetails, "无信息");
        }else {
            vo = new ResultVo(331, studentsDetails, "查询成功");
        }
        return vo;
    }
    @Override
    public ResultVo updateStudentDetailById(StudentsDetail studentsDetail){
        ResultVo vo=null;
        int fy=studentsDetailMapper.verifyStudentDetail(studentsDetail.getStudent_id());
        if(fy==0){
            int num= studentsDetailMapper.addStudentDetail(studentsDetail);
            if(num==0){
                vo = new ResultVo(330, num, "失败");
            }else {
                vo = new ResultVo(330, num, "成功");
            }
        }else {
            int num=studentsDetailMapper.updateStudentDetail(studentsDetail);
            if(num==0){
                vo = new ResultVo(330, num, "失败");
            }else {
                vo = new ResultVo(330, num, "成功");
            }
        }
        return vo;
    }
    @Override
    public ResultVo selectRoomAndTeacher(Long student_id){
        ResultVo vo=null;
        List<StudentsDetail> studentsDetails=studentsDetailMapper.getRoomAndTeacher(student_id);
        if(studentsDetails.isEmpty()){

            vo = new ResultVo(330, studentsDetails, "失败");
        }else {

            vo = new ResultVo(330, studentsDetails, "成功");
        }
        return vo;
    }
}
