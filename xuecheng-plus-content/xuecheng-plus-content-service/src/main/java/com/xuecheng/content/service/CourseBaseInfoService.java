package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

/**
 * @author yy
 * @version 1.0
 * @description 课程管理
 * @date 2023/1/29 22:12
 */

public interface CourseBaseInfoService {
 /***
  * @description 课程查询接口
  * @param pageParams 分页参数
  * @param queryCourseParamsDto 条件
  * @return com.xuecheng.base.model.PageResult<com.xuecheng.content.model.po.CourseBase>
  * @author yy
  * @date 2023/1/29 22:16
 */
 PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

 /**
  * @description 添加课程基本信息
  * @param companyId  教学机构id
  * @param addCourseDto  课程基本信息
  * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
  * @author Mr.M
  * @date 2022/9/7 17:51
  */
 CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);
/***
 * @description 根据Id查询
 * @param courseId
 * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
 * @author yy228
 * @date 2023/1/31 13:09
*/
 public CourseBaseInfoDto getCourseBaseInfo(long courseId);
 /***
  * @description 修改
  * @param companyId
  * @param dto
  * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
  * @author yy228
  * @date 2023/1/31 13:09
 */
 public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto dto);


}
