package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yy228
 * @version 1.0
 * @description TODO
 * @date 2023/2/2 11:00
 */
@Api(value = "课程计划编辑接口",tags = "课程计划编辑接口")
@RestController
public class TeachplanController {

 @Autowired
 TeachplanService teachplanService;

 @ApiOperation("查询课程计划树形结构")
 @ApiImplicitParam(value = "courseId",name = "课程基础Id值",required = true,dataType = "Long",paramType = "path")
 @GetMapping("teachplan/{courseId}/tree-nodes")
 public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId){
  return teachplanService.findTeachplayTree(courseId);
 }

 @ApiOperation("课程计划创建或修改")
 @PostMapping("/teachplan")
 public void saveTeachplan( @RequestBody SaveTeachplanDto teachplan){
  teachplanService.saveTeachplan(teachplan);
 }


 @ApiOperation(value = "删除课程计划")
 @DeleteMapping("teachplan/{teachplanId}")
 public void removeTeachPlan(Long teachPlanId){
  teachplanService.removeTeachPlan(teachPlanId);
 }

 @ApiOperation(value = "移动课程计划")
 @PostMapping("teachplan/{moveType}/{teachplanId}")
 public void moveTeachPlan(@PathVariable String moveType,@PathVariable Long teachplanId){

 }




}
