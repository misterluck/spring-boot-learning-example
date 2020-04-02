package org.example.sbgen.controller;

import org.example.sbgen.common.api.Result;
import org.example.sbgen.common.system.query.QueryGenerator;
import org.example.sbgen.entity.AuthInfoAdmin;
import org.example.sbgen.service.IAuthInfoAdminService;

import java.util.Arrays;
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
 * @since 2020-04-02
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

    /**
    * 添加
    *
    * @param authInfoAdmin
    * @return
    */
    @ApiOperation(value="用户表-添加", notes="用户表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody AuthInfoAdmin authInfoAdmin) {
        iAuthInfoAdminService.save(authInfoAdmin);
        return Result.ok("添加成功！");
    }

    /**
    * 编辑
    *
    * @param authInfoAdmin
    * @return
    */
    @ApiOperation(value="用户表-编辑", notes="用户表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody AuthInfoAdmin authInfoAdmin) {
        iAuthInfoAdminService.updateById(authInfoAdmin);
        return Result.ok("编辑成功!");
    }

    /**
    * 通过id删除
    *
    * @param id
    * @return
    */
    @ApiOperation(value="用户表-通过id删除", notes="用户表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        iAuthInfoAdminService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
    * 批量删除
    *
    * @param ids
    * @return
    */
    @ApiOperation(value="用户表-批量删除", notes="用户表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        iAuthInfoAdminService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
    * 通过id查询
    *
    * @param id
    * @return
    */
    @ApiOperation(value="用户表-通过id查询", notes="用户表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
        AuthInfoAdmin authInfoAdmin = iAuthInfoAdminService.getById(id);
        return Result.ok(authInfoAdmin);
    }

}
