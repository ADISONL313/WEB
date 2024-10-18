package com.cqcskj.studentsadminvisul.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //set/get/toString
@Builder//new对象
@NoArgsConstructor//无参构造
@AllArgsConstructor//所有有参构造
public class Teacher {
    private String teacher_id;
    private String password;
    private String teacherName;
    private String college;
    private String mobile;
    private String idCard;
    private String gender;
    private String remarks;
}
