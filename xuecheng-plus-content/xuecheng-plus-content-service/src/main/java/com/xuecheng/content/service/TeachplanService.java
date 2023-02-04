package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;

import java.util.List;

/**
 * @author yy228
 * @version 1.0
 * @description TODO
 * @date 2023/2/2 23:50
 */

public interface TeachplanService {
 public List<TeachplanDto> findTeachplayTree(long courseId);
/***
 * @description 只在课程计划
 * @param teachplanDto  课程计划信息
 * @return void
 * @author yy228
 * @date 2023/2/4 16:53
*/
 public void saveTeachplan(SaveTeachplanDto teachplanDto);

 public void removeTeachPlan(Long teachPlanId);

 public void moveTeachPlan(Long teachPlanId,String moveType);
}
