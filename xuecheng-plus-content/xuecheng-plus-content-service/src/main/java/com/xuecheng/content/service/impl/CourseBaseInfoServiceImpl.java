package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.mapper.CourseMarketMapper;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CourseCategory;
import com.xuecheng.content.model.po.CourseMarket;
import com.xuecheng.content.service.CourseBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yy
 * @version 1.0
 * @description TODO
 * @date 2023/1/29 22:18
 */

@Service
@Slf4j
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {

 @Autowired
 CourseBaseMapper courseBaseMapper;
 @Autowired
 CourseMarketMapper courseMarketMapper;

 @Autowired
 CourseCategoryMapper courseCategoryMapper;

 @Autowired
 CourseMarketServiceImpl courseMarketService;

 @Override
 public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {
  //构建查询条件对象
  LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
  //构建查询条件，根据课程名称查询
  queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()),CourseBase::getName,queryCourseParamsDto.getCourseName());
  //构建查询条件，根据课程审核状态查询
  queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()),CourseBase::getAuditStatus,queryCourseParamsDto.getAuditStatus());
  //构建查询条件，根据课程发布状态查询
  queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getPublishStatus()),CourseBase::getStatus,queryCourseParamsDto.getPublishStatus());

  //分页对象
  Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
  // 查询数据内容获得结果
  Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, queryWrapper);
  // 获取数据列表
  List<CourseBase> list = pageResult.getRecords();
  // 获取数据总数
  long total = pageResult.getTotal();
  // 构建结果集
  PageResult<CourseBase> courseBasePageResult = new PageResult<>(list, total, pageParams.getPageNo(), pageParams.getPageSize());
  return courseBasePageResult;
 }
@Transactional
 @Override
 public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto dto) {
  if (StringUtils.isBlank(dto.getName())) {
//   throw new RuntimeException("课程名称为空");
   throw new XueChengPlusException("课程名称为空");
  }

  if (StringUtils.isBlank(dto.getMt())) {
//   throw new RuntimeException("课程分类为空");
   throw new XueChengPlusException("课程分类为空");
  }

  if (StringUtils.isBlank(dto.getSt())) {
//   throw new RuntimeException("课程分类为空");
   throw new XueChengPlusException("课程分类为空");
  }

  if (StringUtils.isBlank(dto.getGrade())) {
//   throw new RuntimeException("课程等级为空");
   throw new XueChengPlusException("课程等级为空");
  }

  if (StringUtils.isBlank(dto.getTeachmode())) {
//   throw new RuntimeException("教育模式为空");
   throw new XueChengPlusException("教育模式为空");
  }

  if (StringUtils.isBlank(dto.getUsers())) {
//   throw new RuntimeException("适应人群为空");
   throw new XueChengPlusException("适应人群");
  }

  if (StringUtils.isBlank(dto.getCharge())) {
//   throw new RuntimeException("收费规则为空");
   throw new XueChengPlusException("收费规则为空");
  }

  //新增对象
  CourseBase courseBaseNew = new CourseBase();
  //将填写的课程信息赋值给新增对象
  BeanUtils.copyProperties(dto,courseBaseNew);
  //设置审核状态
  courseBaseNew.setAuditStatus("202002");
  //设置发布状态
  courseBaseNew.setStatus("203001");
  //机构id
  courseBaseNew.setCompanyId(companyId);
  //添加时间
  courseBaseNew.setCreateDate(LocalDateTime.now());
  //插入课程基本信息表
  int insert = courseBaseMapper.insert(courseBaseNew);
  Long courseId = courseBaseNew.getId();
  //课程营销信息
  //先根据课程id查询营销信息
  CourseMarket courseMarketNew = courseMarketMapper.selectById(courseId);
  if(courseMarketNew!=null){
   throw new RuntimeException("收费课程价格不能为空");
  }
  courseMarketNew =  new CourseMarket();
  BeanUtils.copyProperties(dto,courseMarketNew);
  courseMarketNew.setId(courseId);
  //收费规则
  String charge = dto.getCharge();

  //收费课程必须写价格
  if ("201001".equals(charge)) {
   Float price = dto.getPrice();
//   if (ObjectUtils.isEmpty(price)) {
//    throw new XueChengPlusException("收费课程价格不能为空");
//   }
   if(price==null || price.floatValue()<=0){
//    throw new RuntimeException("收费课程价格不能为空");
    throw new XueChengPlusException("收费课程价格不能为空");
   }
   courseMarketNew.setPrice(dto.getPrice().floatValue());
   courseMarketNew.setOriginalPrice(dto.getOriginalPrice().floatValue());
  }

  //插入课程营销信息
  int insert1 = courseMarketMapper.insert(courseMarketNew);

  if(insert1<=0 || insert1<=0){
   throw new RuntimeException("新增课程基本信息失败");
  }
  //添加成功
  //返回添加的课程信息
  return getCourseBaseInfo(courseId);

 }

 public CourseBaseInfoDto getCourseBaseInfo(long courseId){

  CourseBase courseBase = courseBaseMapper.selectById(courseId);
  CourseMarket courseMarket = courseMarketMapper.selectById(courseId);

  if(courseBase == null){
   return null;
  }
  CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
  BeanUtils.copyProperties(courseBase,courseBaseInfoDto);
  if(courseMarket != null){
   BeanUtils.copyProperties(courseMarket,courseBaseInfoDto);
  }

  //查询分类名称
  CourseCategory courseCategoryBySt = courseCategoryMapper.selectById(courseBase.getSt());
  courseBaseInfoDto.setStName(courseCategoryBySt.getName());
  CourseCategory courseCategoryByMt = courseCategoryMapper.selectById(courseBase.getMt());
  courseBaseInfoDto.setMtName(courseCategoryByMt.getName());

  return courseBaseInfoDto;

 }

 @Override
 public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto dto) {
  //课程id
  Long courseId = dto.getId();
  CourseBase courseBaseUpdate = courseBaseMapper.selectById(courseId);
  if(courseBaseUpdate==null){
   XueChengPlusException.cast("课程不存在");
  }


  if(!companyId.equals(courseBaseUpdate.getCompanyId())){
   XueChengPlusException.cast("只允许修改本机构的课程");
  }

  BeanUtils.copyProperties(dto,courseBaseUpdate);
  courseBaseUpdate.setId(courseId);
  courseBaseUpdate.setCompanyId(companyId);
  //更新
  courseBaseUpdate.setChangeDate(LocalDateTime.now());
  int i = courseBaseMapper.updateById(courseBaseUpdate);


  //查询营销信息
//  CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
//  if(courseMarket==null){
//   courseMarket = new CourseMarket();
//  }

  CourseMarket courseMarket = new CourseMarket();
  BeanUtils.copyProperties(dto,courseMarket);

//  courseMarket.setId(courseId);
//  courseMarket.setCharge(dto.getCharge());
  //收费规则
  String charge = dto.getCharge();

  //收费课程必须写价格
  if ("201001".equals(charge)) {
   Float price = dto.getPrice();
   if (price==null || price.floatValue()<=0) {
    throw new XueChengPlusException("收费课程价格不能为空");
   }
//   courseMarket.setPrice(dto.getPrice().floatValue());
  }
  //保存课程营销信息，没有则添加，有则更新
  boolean b = courseMarketService.saveOrUpdate(courseMarket);


  //返回添加的课程信息
  return getCourseBaseInfo(courseId);
 }


}

























