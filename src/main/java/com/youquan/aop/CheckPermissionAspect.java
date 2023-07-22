package com.youquan.aop;

import com.youquan.exception.TliasException;
import com.youquan.mapper.EmpMapper;
import com.youquan.service.EmpService;
import com.youquan.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Fengyouquan
 */
@Slf4j
@RequiredArgsConstructor
@Component
@Aspect
public class CheckPermissionAspect {
    private final HttpServletRequest httpServletRequest;
    private final EmpMapper empMapper;

    @Before("@annotation(com.youquan.anno.CheckPermission)")
    public void checkPermission() {
        String token = httpServletRequest.getHeader("Token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");
        log.info("检测到非法权限，操作人员为：{},{}", claims.get("name").toString(), id);
        String deptName = empMapper.getDeptNameById(id);
        if (deptName == null || (!(Objects.equals(deptName, "学工部") && !Objects.equals(deptName, "咨询部")))) {
            throw new TliasException("权限不足,请联系学工部或咨询部员工");
        }
    }
}
