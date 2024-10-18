package com.cqcskj.studentsadminvisul.controller;

import com.cqcskj.studentsadminvisul.entity.Dormitory;
import com.cqcskj.studentsadminvisul.service.DormitoryService;
import com.cqcskj.studentsadminvisul.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dormitory")
@CrossOrigin(origins ={"http://7512e470.r5.cpolar.top","*"} )
public class DormitoryController {
    @Resource
    private DormitoryService dormitoryService;
    @RequestMapping("/getDormitories")
    public ResultVo queryDormitory(@RequestBody Dormitory dormitory){
        return dormitoryService.queryDormitory(dormitory);
    }
    @RequestMapping("/addDormitory")
    public  ResultVo addDormitory(@RequestBody Dormitory dormitory){
        return dormitoryService.addDormitory(dormitory);
    }
    @RequestMapping("/delDormitory")
    public ResultVo deleteDormitory(@RequestParam int id){
        return dormitoryService.deleteDormitory(id);
    }
}
