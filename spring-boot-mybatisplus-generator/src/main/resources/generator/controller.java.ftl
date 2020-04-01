package ${package.Controller};

import org.example.sbgen.common.api.Result;
import org.example.sbgen.common.system.query.QueryGenerator;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};

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

    /**
    * 添加
    *
    * @param ${entity?uncap_first}
    * @return
    */
    @ApiOperation(value="${table.comment!}-添加", notes="${table.comment!}-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ${entity} ${entity?uncap_first}) {
        ${table.serviceName?uncap_first}.save(${entity?uncap_first});
        return Result.ok("添加成功！");
    }

    /**
    * 编辑
    *
    * @param ${entity?uncap_first}
    * @return
    */
    @ApiOperation(value="${table.comment!}-编辑", notes="${table.comment!}-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ${entity} ${entity?uncap_first}) {
        ${table.serviceName?uncap_first}.updateById(${entity?uncap_first});
        return Result.ok("编辑成功!");
    }

    /**
    * 通过id删除
    *
    * @param id
    * @return
    */
    @ApiOperation(value="${table.comment!}-通过id删除", notes="${table.comment!}-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        ${table.serviceName?uncap_first}.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
    * 批量删除
    *
    * @param ids
    * @return
    */
    @ApiOperation(value="${table.comment!}-批量删除", notes="${table.comment!}-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        ${table.serviceName?uncap_first}.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
    * 通过id查询
    *
    * @param id
    * @return
    */
    @ApiOperation(value="${table.comment!}-通过id查询", notes="${table.comment!}-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
        ${entity} ${entity?uncap_first} = ${table.serviceName?uncap_first}.getById(id);
        return Result.ok(${entity?uncap_first});
    }

}
</#if>
