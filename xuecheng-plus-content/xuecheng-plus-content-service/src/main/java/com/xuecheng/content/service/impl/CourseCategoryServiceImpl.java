package com.xuecheng.content.service.impl;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yy228
 * @version 1.0
 * @description TODO
 * @date 2023/1/30 21:57
 */
@Service
@Slf4j
public class CourseCategoryServiceImpl implements CourseCategoryService {

 @Autowired
 CourseCategoryMapper courseCategoryMapper;
 @Override
 public List<CourseCategoryTreeDto> queryTreeNodes(String id) {
  List<CourseCategoryTreeDto> categoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);

  List<CourseCategoryTreeDto> categoryTreeDtoList=new ArrayList<>();

  HashMap<String, CourseCategoryTreeDto> map = new HashMap<>();

  categoryTreeDtos.stream().forEach(item->{
   map.put(item.getId(),item);
   if(item.getParentid().equals(id)){
    categoryTreeDtoList.add(item);
   }
   String parentid = item.getParentid();
   CourseCategoryTreeDto parentNode = map.get(parentid);
   if(parentNode!=null){
    List childrenTreeNodes = parentNode.getChildrenTreeNodes();
    if(childrenTreeNodes==null){
     parentNode.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDto>());
    }
    parentNode.getChildrenTreeNodes().add(item);
   }
  });

  return categoryTreeDtoList;
 }
}
