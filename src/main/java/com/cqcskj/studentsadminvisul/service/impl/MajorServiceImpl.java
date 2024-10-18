package com.cqcskj.studentsadminvisul.service.impl;

import  com.cqcskj.studentsadminvisul.entity.Major;
import com.cqcskj.studentsadminvisul.mapper.MajorMapper;
import com.cqcskj.studentsadminvisul.service.MajorService;
import com.cqcskj.studentsadminvisul.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {
    @Resource
    private MajorMapper majorMapper;
    @Override
    public ResultVo insertOneMajor(Major major){
        ResultVo vo=null;
        String majors=majorMapper.majorVerify(major.getMajorName());
        if(majors==null){
            int num= majorMapper.insertMajor(major);
            vo = new ResultVo(330, num, "添加");
        }else {
            vo = new ResultVo(331, "专业已存在", "无用户信息");
        }
        return vo;
    }
    @Override
    public ResultVo queryMajorByCollege(String college){
        ResultVo vo=null;
        List<Major> majors=majorMapper.selectMajors(college);
        if(majors.isEmpty()){
            vo = new ResultVo(331, null, "无用户信息");
        }else {
            vo = new ResultVo(331, majors, "无用户信息");
        }
        return vo;
    }
    @Override
    public ResultVo queryMajorByCollegeAndMajor(String college, String majorName){
        ResultVo vo=null;
        List<Major> majors=majorMapper.selectMajorByNameAdnCollege(college,majorName);
        if(majors.isEmpty()){
            vo = new ResultVo(331, null, "无用户信息");
        }else {
            vo = new ResultVo(331, majors, "无用户信息");
        }
        return vo;
    }
    @Override
    public ResultVo delMajorById(int id){
        ResultVo vo=null;
        int fy=majorMapper.delMajorVerify(id);
        if(fy==0){
            int num=majorMapper.deleteMajorById(id);
            vo = new ResultVo(331, num, "删除成功");
        }else {
            vo = new ResultVo(331, fy, "删除失败，该专业已和其他数据绑定");
        }
        return vo;
    }
    @Override
    public ResultVo updateMajorName(String majorName, int id){
        ResultVo vo=null;
        String fi=majorMapper.majorVerify(majorName);
        if(fi==null){
            int num=majorMapper.updateMajor(majorName,id);
            vo = new ResultVo(331, num, "修改成功");
        }else {
            vo = new ResultVo(331, fi, "修改失败,专业已存在");
        }
        return vo;
    }
}
