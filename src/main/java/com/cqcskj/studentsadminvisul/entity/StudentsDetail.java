package com.cqcskj.studentsadminvisul.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //set/get/toString
@Builder//new对象
@NoArgsConstructor//无参构造
@AllArgsConstructor//所有有参构造
public class StudentsDetail {
    private int id;
    private Long student_id;
    private String level;
    private int moral;
    private int course;
    private int attendance;
    private int practice;
    private int ideology;
    private String evaluate;
    private int class_id;
    private String homeAddress;
    private String birthday;
    private int roomId;
    private String className;
    private String room_id;
    private String buildingName;
    private String teacher_id;
}
