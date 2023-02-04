package com.xuecheng.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * @author yy228
 * @version 1.0
 * @description TODO
 * @date 2023/1/31 11:16
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

 @ResponseBody
 @ExceptionHandler(XueChengPlusException.class)
 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
 public RestErrorResponse customException(XueChengPlusException e){
  log.error("【系统异常】{}",e.getErrMessage(),e);
  return new RestErrorResponse(e.getErrMessage());
 }

 @ResponseBody
 @ExceptionHandler(Exception.class)
 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
 public RestErrorResponse exception(Exception e) {

  log.error("【系统异常】{}",e.getMessage(),e);

  return new RestErrorResponse(CommonError.UNKOWN_ERROR.getErrMessage());

 }

 @ResponseBody
 @ExceptionHandler(value = MethodArgumentNotValidException.class)
 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
 public RestErrorResponse doValidException(MethodArgumentNotValidException argumentNotValidException) {

  BindingResult bindingResult = argumentNotValidException.getBindingResult();
  StringBuffer errMsg = new StringBuffer();

  List<FieldError> fieldErrors = bindingResult.getFieldErrors();
  fieldErrors.forEach(error -> {
   errMsg.append(error.getDefaultMessage()).append(",");
  });
  log.error(errMsg.toString());
  return new RestErrorResponse(errMsg.toString());
 }
}
