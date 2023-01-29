package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
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
}
