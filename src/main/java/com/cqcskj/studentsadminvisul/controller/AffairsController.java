package com.cqcskj.studentsadminvisul.controller;

import com.cqcskj.studentsadminvisul.entity.Affairs;
import com.cqcskj.studentsadminvisul.service.AffairsService;
import com.cqcskj.studentsadminvisul.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/affairs")
@CrossOrigin(origins ={"http://7512e470.r5.cpolar.top","*"} )
public class AffairsController {
    @Resource private AffairsService affairsService;
    @RequestMapping("/addAffairs")
    public ResultVo insertAffair(@RequestBody Affairs affairs){
        return affairsService.insertAffair(affairs);
    }
    @RequestMapping("/selectAffairs")
    public ResultVo queryAffairsByTeacher(@RequestParam("teacher_id")String teacher_id){
        return affairsService.queryAffairsByTeacher(teacher_id);
    }
    @RequestMapping("/updateAffairs")
    public ResultVo updateAffairsState(@RequestBody Affairs affairs){
        return affairsService.updateAffairsState(affairs);
    }
    @RequestMapping("/queryAllAffairs")
    public ResultVo queryAllAffairs(@RequestBody Affairs affairs){
        return affairsService.queryAllAffairs(affairs);
    }
    @RequestMapping("/delAffairs")
    public ResultVo deleteAffairs(@RequestParam("id")int id){
        return affairsService.deleteAffairs(id);
    }
}
