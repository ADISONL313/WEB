package com.cqcskj.studentsadminvisul.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Date 2024/9/14 10:11
 * @Author 天涯
 * @Email: abc@qq.com
 * @Version v1.0
 * 提供给前端的通用实体类
 */
@Data //set/get/toString
@Builder//new对象
@AllArgsConstructor//所有有参构造
public class ResultVo {
    //响应码：请求成功310,311请求失败
    private int code;
    //返回给前端的数据
    private Object data;
    //响应给前端的消息
    private String message;
}
