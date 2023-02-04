package com.xuecheng.base.exception;

/**
 * @author yy228
 * @version 1.0
 * @description TODO
 * @date 2023/1/31 11:07
 */

public class XueChengPlusException extends RuntimeException{

 private String errMessage;
 public XueChengPlusException() {
 }

 public XueChengPlusException(String errMessage) {
  super(errMessage);
  this.errMessage = errMessage;
 }

 public String getErrMessage() {
  return errMessage;
 }
 public static void cast(CommonError commonError){
  throw new XueChengPlusException(commonError.getErrMessage());
 }
 public static void cast(String errMessage){
  throw new XueChengPlusException(errMessage);
 }
}
