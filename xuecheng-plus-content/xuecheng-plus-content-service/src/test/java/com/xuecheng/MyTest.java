package com.xuecheng;/**
 * @author yy
 * @version 1.0
 */

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yy
 * @version 1.0
 * @description TODO
 * @date 2023/1/29 19:01
 */
@SpringBootTest
public class MyTest {
 @Autowired(required = false)
 CourseBaseMapper courseBaseMapper;

 @Autowired
 CourseBaseInfoService courseBaseInfoService;


 @Test
 void testCourseBaseMapper() {
//  CourseBase courseBase = courseBaseMapper.selectById(74L);
//  Assertions.assertNotNull(courseBase);
  PageParams params = new PageParams();
  PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.queryCourseBaseList(params, new QueryCourseParamsDto());
  System.out.println(courseBasePageResult);
 }
}
