package com.cqcskj.studentsadminvisul.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //set/get/toString
@Builder//new对象
@NoArgsConstructor//无参构造
@AllArgsConstructor//所有有参构造
public class Affairs {
    private int id;
    private Long student_id;
    private String content;
    private String teacher_id;
    private int affairsType;
    private int audits;
    private String reason;
    private String studentName;
    private String sourceRoomOrMajor;
    private int majorOrRoomId;
    private String refuseReason;
    private String className;
    private String grade;
}
