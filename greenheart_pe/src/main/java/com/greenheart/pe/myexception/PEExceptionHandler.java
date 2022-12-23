package com.greenheart.pe.myexception;

import entity.JsonResult;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//异常处理
@RestControllerAdvice
public class PEExceptionHandler {
    @ExceptionHandler(value=Exception.class)
    public JsonResult exception(Exception e){
        e.printStackTrace();
        return new JsonResult(false, StatusCode.ERROR,e.getMessage());
    }
}
