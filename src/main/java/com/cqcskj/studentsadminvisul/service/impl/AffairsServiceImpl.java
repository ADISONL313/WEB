package com.cqcskj.studentsadminvisul.service.impl;

import com.cqcskj.studentsadminvisul.entity.Affairs;
import com.cqcskj.studentsadminvisul.mapper.AffairsMapper;
import com.cqcskj.studentsadminvisul.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AffairsServiceImpl implements com.cqcskj.studentsadminvisul.service.AffairsService {
    @Resource
    private AffairsMapper affairsMapper;
    @Override
    public ResultVo insertAffair(Affairs affairs){
        ResultVo vo =null;
        int num=affairsMapper.verifyAffair(affairs.getStudent_id(),affairs.getAffairsType());
        if(num==0){
            int addNum=affairsMapper.addAffairs(affairs);
            vo = new ResultVo(330, addNum, "添加成功");
        }else {
            vo = new ResultVo(330, num, "添加失败,你已经有同类申请");
        }
        return vo;
    }
    @Override
    public ResultVo queryAffairsByTeacher(String teacher_id){
        ResultVo vo =null;
        List<Affairs> affairs=affairsMapper.selectAffairsByTeacher(teacher_id);
        if(affairs.isEmpty()){
            vo = new ResultVo(330, affairs, "暂无信息");
        }else {
            vo = new ResultVo(330, affairs, "查询成功");
        }
        return vo;
    }
    @Override
    public ResultVo updateAffairsState(Affairs affairs){
        ResultVo vo =null;
        int num=affairsMapper.updateApplyState(affairs);
        if(num==0){
            vo = new ResultVo(330, num, "失败");
        }else {
            vo = new ResultVo(330, num, "成功");
        }
        return vo;
    }
    @Override
    public ResultVo queryAllAffairs(Affairs affairs){
        ResultVo vo =null;
        List<Affairs> affair=affairsMapper.selectAllAffairs(affairs);
        if(affair.isEmpty()){
            vo = new ResultVo(330, affair, "失败");
        }else {
            vo = new ResultVo(330, affair, "成功");
        }
        return vo;
    }
    @Override
    public ResultVo deleteAffairs(int id){
        ResultVo vo =null;
        int num=affairsMapper.delAffairsById(id);
        if(num==0){
            vo = new ResultVo(330, num, "失败");
        }else {
            vo = new ResultVo(330, num, "成功");
        }
        return vo;
    }
}
