package org.example.sbgen.controller;

import org.example.sbgen.common.api.Result;
import org.example.sbgen.common.system.query.QueryGenerator;
import org.example.sbgen.entity.AuthInfoAdmin;
import org.example.sbgen.service.IAuthInfoAdminService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zhaolei
 * @since 2020-03-10
 */
@RestController
@Api(tags="用户表")
@RequestMapping("/auth-info-admin")
public class AuthInfoAdminController {

    @Autowired
    private IAuthInfoAdminService iAuthInfoAdminService;

    /**
    * 分页列表查询
    *
    * @param authInfoAdmin
    * @return
    */
    @ApiOperation(value="用户表-分页列表查询", notes="用户表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(AuthInfoAdmin authInfoAdmin,
                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                HttpServletRequest req) {
            QueryWrapper<AuthInfoAdmin> queryWrapper = QueryGenerator.initQueryWrapper(authInfoAdmin, req.getParameterMap());
            Page<AuthInfoAdmin> page = new Page<AuthInfoAdmin>(pageNo, pageSize);
            IPage<AuthInfoAdmin> pageList = iAuthInfoAdminService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

}
