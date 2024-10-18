package com.cqcskj.studentsadminvisul.controller;

import com.cqcskj.studentsadminvisul.entity.Major;
import com.cqcskj.studentsadminvisul.service.MajorService;
import com.cqcskj.studentsadminvisul.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/major")
@CrossOrigin(origins ={"http://7512e470.r5.cpolar.top","*"} )
public class MajorController {
    @Resource
    private MajorService majorService;
    @RequestMapping("/insert")
    public ResultVo insertOneMajor(@RequestBody Major major){
        return majorService.insertOneMajor(major);
    }
    @RequestMapping("/selectMajor")
    public ResultVo queryMajorByCollege(@RequestParam("college") String college){
        return  majorService.queryMajorByCollege(college);
    }
    @RequestMapping("/selectMajors")
    public ResultVo queryMajorByCollegeAndMajor(@RequestParam("college")String college,@RequestParam("majorName")String majorName){
        return majorService.queryMajorByCollegeAndMajor(college,majorName);
    }
    @RequestMapping("/delMajor")
    public ResultVo delMajorById(@RequestParam("id") int id){
        return majorService.delMajorById(id);
    }
    @RequestMapping("/updateMajor")
    public ResultVo updateMajorName(@RequestParam("majorName") String majorName,@RequestParam("id")int id){
        return  majorService.updateMajorName(majorName,id);
    }
}
