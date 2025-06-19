package com.example.aop;

import com.example.mapper.OperateLogMapper;
import com.example.pojo.OperateLog;
import com.example.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper; // 操作日志的Mapper

    @Around("@annotation(com.example.anno.Log)")
    public Object recordOperationLog(ProceedingJoinPoint joinPoint) throws Throwable {

        Long startTime = System.currentTimeMillis();

        //执行目标方法
        Object result = joinPoint.proceed();

        //计算耗时
        Long endTime = System.currentTimeMillis();
        Long costTime = endTime - startTime;

        //构建日志实体
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentUserId());
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        operateLog.setReturnValue(result != null ? result.toString() : "void");
        operateLog.setCostTime(costTime);

        //保存日志
        log.info("保存操作日志：{}", operateLog);
        operateLogMapper.insert(operateLog);

        return result;
    }

    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}
