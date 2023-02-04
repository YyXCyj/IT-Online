package com.xuecheng.content.api;/**
 * @author yy
 * @version 1.0
 */

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yy
 * @version 1.0
 * @description TODO
 * @date 2023/1/29 12:45
 */
@RestController
@Api(value = "课程信息编辑接口",tags = "课程信息编辑接口")
public class CourseBaseInfoController {

    @Autowired
    CourseBaseInfoService courseBaseInfoService;

    @PostMapping("/course/list")
    @ApiOperation("课程查询接口")
    public PageResult<CourseBase> list (PageParams pageParams,@RequestBody QueryCourseParamsDto queryCourseParamsDto){
        return courseBaseInfoService.queryCourseBaseList(pageParams,queryCourseParamsDto);
    }

    @ApiOperation("新增课程基础信息")
    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody @Validated AddCourseDto addCourseDto){
        Long companyId = 3L;
        return courseBaseInfoService.createCourseBase(companyId,addCourseDto);
    }
/***
 * @description 根据Id查询课程信息
 * @param courseId
 * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
 * @author yy228
 * @date 2023/1/31 12:03
*/
    @ApiOperation("根据课程id查询课程基础信息")
    @GetMapping("/course/{courseId}")
    public CourseBaseInfoDto getCourseBaseById(@PathVariable Long courseId){
        CourseBaseInfoDto courseBaseInfo = courseBaseInfoService.getCourseBaseInfo(courseId);
        return courseBaseInfo;
    }


/***
 * @description 修改课程基础信息
 * @param  editCourseDto
 * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
 * @author yy228
 * @date 2023/1/31 11:57
*/

    @ApiOperation("修改课程基础信息")
    @PutMapping("/course")
    public CourseBaseInfoDto modifyCourseBase(@RequestBody @Validated EditCourseDto editCourseDto){
        //机构id，由于认证系统没有上线暂时硬编码
        Long companyId = 1232141425L;
        return courseBaseInfoService.updateCourseBase(companyId,editCourseDto);
    }




}
