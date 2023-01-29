package com.xuecheng.content.model.dto;/**
 * @author yy
 * @version 1.0
 */

import lombok.Data;
import lombok.ToString;

/**
 * @author yy
 * @version 1.0
 * @description TODO
 * @date 2023/1/29 12:22
 */
@Data
@ToString
public class QueryCourseParamsDto {
 //审核状态
 private String auditStatus;
 //课程名称
 private String courseName;
 //发布状态
 private String publishStatus;
}
