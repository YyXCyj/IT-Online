package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;

import java.util.List;

/**
 * @author yy228
 * @version 1.0
 * @description TODO
 * @date 2023/1/30 21:56
 */

public interface CourseCategoryService {
 public List<CourseCategoryTreeDto> queryTreeNodes(String id);
}
