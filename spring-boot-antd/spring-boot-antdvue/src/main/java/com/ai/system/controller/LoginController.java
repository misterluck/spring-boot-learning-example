package com.ai.system.controller;

import com.ai.common.api.vo.Result;
import com.ai.common.constant.CommonConstant;
import com.ai.common.system.util.JwtUtil;
import com.ai.common.system.vo.LoginUser;
import com.ai.common.util.MD5Util;
import com.ai.common.util.PasswordUtil;
import com.ai.common.util.oConvertUtils;
import com.ai.system.entity.SysPermission;
import com.ai.system.entity.SysUser;
import com.ai.system.model.SysLoginModel;
import com.ai.system.service.ISysBaseAPI;
import com.ai.system.service.ISysPermissionService;
import com.ai.system.service.ISysUserService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.antdvue.constant.AuthConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/auth")
@Api(tags = "用户登录")
@Slf4j
public class LoginController {
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    /*@Autowired
    private RedisUtil redisUtil;*/
    @Autowired
    private ISysPermissionService sysPermissionService;

    @ApiOperation("登录接口")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<JSONObject> login(@RequestBody SysLoginModel sysLoginModel) {
        Result<JSONObject> result = new Result<>();
        String username = sysLoginModel.getUsername();
        String password = sysLoginModel.getPassword();

        System.out.println("####:"+username+"\t"+password);

        //1. 校验用户是否有效
        SysUser sysUser = sysUserService.getUserByName(username);
        result = sysUserService.checkUserIsEffective(sysUser);
        if (!result.isSuccess()) {
            return result;
        }

        //2. 校验用户名或密码是否正确
        String userPassword = PasswordUtil.encrypt(username, password, sysUser.getSalt());
        String sysPassword = sysUser.getPassword();
        if (!sysPassword.equals(userPassword)) {
            result.error500("用户名或密码错误");
            return result;
        }

        // 登录成功
        JSONObject data = new JSONObject();
        String token = JwtUtil.sign(username, sysPassword);
        // 设置token缓存有效时间
        /*redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);*/

        data.put("token", token);
        result.setResult(data);
        result.success("登录成功");
        return result;
    }

    @ApiOperation("获取登录用户信息接口")
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public Result userInfo(HttpServletRequest request) {
        String token = request.getHeader(AuthConstant.ACCESS_TOKEN);
        String username = JwtUtil.getUsername(token);
        if (StringUtils.isEmpty(username)) {
            return Result.error("用户未登录!");
        }
        LoginUser loginUser = sysBaseAPI.getUserByName(username);
        return Result.ok(loginUser);
    }

    @ApiOperation("获取登录用户菜单接口")
    @RequestMapping(value = "/userMenu", method = RequestMethod.GET)
    public Result userMenu(HttpServletRequest request) {
        String token = request.getHeader(AuthConstant.ACCESS_TOKEN);
        String username = JwtUtil.getUsername(token);
        if (StringUtils.isEmpty(username)) {
            return Result.error("用户未登录!");
        }

        List<SysPermission> menuList = sysPermissionService.queryByUser(username);
        JSONArray menuJsonArray = new JSONArray();
        for (SysPermission permission : menuList) {
            if (permission.getMenuType() == null) {
                continue;
            }
            JSONObject json = getPermissionJsonObject(permission);
            menuJsonArray.add(json);
        }

        return Result.ok(menuJsonArray);
    }

    /**
     * 根据菜单配置生成路由json
     * @param permission
     * @return
     */
    private JSONObject getPermissionJsonObject(SysPermission permission) {
        JSONObject json = new JSONObject();
        // 类型(0：一级菜单 1：子菜单 2：按钮)
        if (permission.getMenuType().equals(CommonConstant.MENU_TYPE_2)) {
            //json.put("action", permission.getPerms());
            //json.put("type", permission.getPermsType());
            //json.put("describe", permission.getName());
            return null;
        } else if (permission.getMenuType().equals(CommonConstant.MENU_TYPE_0) || permission.getMenuType().equals(CommonConstant.MENU_TYPE_1)) {
            json.put("id", permission.getId());
            json.put("parentId", permission.getParentId());
            json.put("redirect", permission.getRedirect());
            if (permission.isRoute()) {
                json.put("route", "1");// 表示生成路由
            } else {
                json.put("route", "0");// 表示不生成路由
            }

            if (isWWWHttpUrl(permission.getUrl())) {
                json.put("path", MD5Util.MD5Encode(permission.getUrl(), "utf-8"));
            } else {
                json.put("path", permission.getUrl());
            }

            // 重要规则：路由name (通过URL生成路由name,路由name供前端开发，页面跳转使用)
            if (oConvertUtils.isNotEmpty(permission.getComponentName())) {
                json.put("name", permission.getComponentName());
            } else {
                json.put("name", urlToRouteName(permission.getUrl()));
            }

            // 是否隐藏路由，默认都是显示的
            if (permission.isHidden()) {
                json.put("hidden", true);
            }
            // 聚合路由
            if (permission.isAlwaysShow()) {
                json.put("alwaysShow", true);
            }
            json.put("component", permission.getComponent());
            JSONObject meta = new JSONObject();
            // 由用户设置是否缓存页面 用布尔值
            if (permission.isKeepAlive()) {
                meta.put("keepAlive", true);
            } else {
                meta.put("keepAlive", false);
            }

            /*update_begin author:wuxianquan date:20190908 for:往菜单信息里添加外链菜单打开方式 */
            //外链菜单打开方式
            if (permission.isInternalOrExternal()) {
                meta.put("internalOrExternal", true);
            } else {
                meta.put("internalOrExternal", false);
            }
            /* update_end author:wuxianquan date:20190908 for: 往菜单信息里添加外链菜单打开方式*/

            meta.put("title", permission.getName());
            if (oConvertUtils.isEmpty(permission.getParentId())) {
                // 一级菜单跳转地址
                json.put("redirect", permission.getRedirect());
                if (oConvertUtils.isNotEmpty(permission.getIcon())) {
                    meta.put("icon", permission.getIcon());
                }
            } else {
                if (oConvertUtils.isNotEmpty(permission.getIcon())) {
                    meta.put("icon", permission.getIcon());
                }
            }
            if (isWWWHttpUrl(permission.getUrl())) {
                meta.put("url", permission.getUrl());
            }
            json.put("meta", meta);
        }

        return json;
    }

    /**
     * 判断是否外网URL 例如： http://localhost:8080/jeecg-boot/swagger-ui.html#/ 支持特殊格式： {{
     * window._CONFIG['domianURL'] }}/druid/ {{ JS代码片段 }}，前台解析会自动执行JS代码片段
     *
     * @return
     */
    private boolean isWWWHttpUrl(String url) {
        if (url != null && (url.startsWith("http://") || url.startsWith("https://") || url.startsWith("{{"))) {
            return true;
        }
        return false;
    }

    /**
     * 通过URL生成路由name（去掉URL前缀斜杠，替换内容中的斜杠‘/’为-） 举例： URL = /isystem/role RouteName =
     * isystem-role
     *
     * @return
     */
    private String urlToRouteName(String url) {
        if (oConvertUtils.isNotEmpty(url)) {
            if (url.startsWith("/")) {
                url = url.substring(1);
            }
            url = url.replace("/", "-");

            // 特殊标记
            url = url.replace(":", "@");
            return url;
        } else {
            return null;
        }
    }

}
