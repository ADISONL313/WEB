package com.cqcskj.studentsadminvisul.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //set/get/toString
@Builder//new对象
@NoArgsConstructor//无参构造
@AllArgsConstructor//所有有参构造
public class Classes {
    private int id;
    private String className;
    private int major_id;
    private String college;
    private String majorName;
    private String grade;
}
