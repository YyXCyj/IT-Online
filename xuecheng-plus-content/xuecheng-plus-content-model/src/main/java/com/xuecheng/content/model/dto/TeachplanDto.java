package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author yy228
 * @version 1.0
 * @description TODO
 * @date 2023/2/2 10:58
 */
@Data
@ToString
public class TeachplanDto extends Teachplan {
 //课程计划关联的媒资信息
 TeachplanMedia teachplanMedia;

 //子结点
 List<TeachplanDto> teachPlanTreeNodes;
}
