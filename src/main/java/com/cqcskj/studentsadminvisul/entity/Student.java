package com.cqcskj.studentsadminvisul.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //set/get/toString
@Builder//new对象
@NoArgsConstructor//无参构造
@AllArgsConstructor//所有有参构造
public class Student {
    private Long student_id;
    private String studentName;
    private String password;
    private String id_card;
    private String mobile;
    private String gender;
    private String counselor;
    private String college;
    private int major;
    private String province;
    private String majorName;
    private String teacherId;
}
