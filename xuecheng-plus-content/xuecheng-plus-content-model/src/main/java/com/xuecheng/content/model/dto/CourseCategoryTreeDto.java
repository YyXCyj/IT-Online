package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.CourseCategory;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yy228
 * @version 1.0
 * @description TODO
 * @date 2023/1/30 21:34
 */
@Data
public class CourseCategoryTreeDto extends CourseCategory implements Serializable {
 List<CourseCategoryTreeDto> childrenTreeNodes;
}
