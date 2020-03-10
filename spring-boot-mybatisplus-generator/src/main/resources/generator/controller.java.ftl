package ${package.Controller};

import org.example.sbgen.common.api.Result;
import org.example.sbgen.common.system.query.QueryGenerator;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@Api(tags="${table.comment!}")
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
    * 分页列表查询
    *
    * @param ${entity?uncap_first}
    * @return
    */
    @ApiOperation(value="${table.comment!}-分页列表查询", notes="${table.comment!}-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(${entity} ${entity?uncap_first},
                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                HttpServletRequest req) {
            QueryWrapper<${entity}> queryWrapper = QueryGenerator.initQueryWrapper(${entity?uncap_first}, req.getParameterMap());
            Page<${entity}> page = new Page<${entity}>(pageNo, pageSize);
            IPage<${entity}> pageList = ${table.serviceName?uncap_first}.page(page, queryWrapper);
        return Result.ok(pageList);
    }

}
</#if>
