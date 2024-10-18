package com.cqcskj.studentsadminvisul.service.impl;

import com.cqcskj.studentsadminvisul.entity.Dormitory;
import com.cqcskj.studentsadminvisul.mapper.DormitoryMapper;
import com.cqcskj.studentsadminvisul.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DormitoryServiceImpl implements com.cqcskj.studentsadminvisul.service.DormitoryService {
    @Resource
    private DormitoryMapper dormitoryMapper;
    @Override
    public ResultVo queryDormitory(Dormitory dormitory){
        ResultVo vo=null;
        List<Dormitory> dormitories =dormitoryMapper.getDormitories(dormitory);
        if(dormitories.isEmpty()){
            vo = new ResultVo(330, dormitories, "无信息");
        }else {
            vo = new ResultVo(330, dormitories, "查询成功");
        }
        return vo;
    }
    @Override
    public ResultVo addDormitory(Dormitory dormitory){
        ResultVo vo=null;
        List<Dormitory> dormitories =dormitoryMapper.getDormitories(dormitory);
        if(dormitories.isEmpty()){
            int num=dormitoryMapper.insertDormitory(dormitory);
            vo = new ResultVo(330, num, "添加成功");
        }else {
            vo = new ResultVo(330, 0, "添加失败，该楼栋已有该房间");
        }
        return vo;
    }
    @Override
    public ResultVo deleteDormitory(int id){
        ResultVo vo=null;
        int num=dormitoryMapper.delDormitory(id);
        if(num==0){
            vo = new ResultVo(330, num, "删除失败");
        }else {
            vo = new ResultVo(330, num, "删除成功");
        }
        return vo;
    }
}
