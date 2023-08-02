package com.chunfeng.controller;

import com.chunfeng.dao.entity.PermissionRouter;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IPermissionRouterService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限-路由关系控制层
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@RestController
@RequestMapping("/prt")
@Api(value = "权限-路由关系控制层", tags = "权限-路由关系操作接口")
public class PermissionRouterController {

    /**
     * 关系业务层
     */
    @Autowired
    private IPermissionRouterService permissionRouterService;

    /**
     * 分类筛选关系信息
     *
     * @param permissionRouter 条件
     * @return JSON
     */
    @GetMapping("/select")
    @ApiOperation(value = "分类筛选关系信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    public JsonRequest<List<PermissionRouter>> lookPermissionRouter(
            @ApiParam(value = "条件", required = true)
                    PermissionRouter permissionRouter) {
        return permissionRouterService.lookPermissionRouter(permissionRouter);
    }

    /**
     * 查询所有关系信息
     *
     * @return JSON
     */
    @GetMapping
    @ApiOperation(value = "查询所有关系信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    public JsonRequest<List<PermissionRouter>> lookAllPermissionRouter() {
        return permissionRouterService.lookAllPermissionRouter();
    }

    /**
     * 批量绑定关系信息
     *
     * @param permissionRouters 关系信息
     * @return JSON
     */
    @PostMapping
    @ApiOperation(value = "批量绑定关系信息")
    @ApiResponses({
            @ApiResponse(code = 502, message = "添加失败!"),
            @ApiResponse(code = 200, message = "添加成功!")
    })
    public JsonRequest<Integer> addOnePermissionRouter(
            @ApiParam(value = "待绑定的关系信息", required = true)
            @RequestBody List<PermissionRouter> permissionRouters) {
        return permissionRouterService.addPermissionRouter(permissionRouters);
    }

    /**
     * 批量修改关系数据
     *
     * @param permissionRouters 关系信息
     * @return JSON
     */
    @PutMapping
    @ApiOperation(value = "批量修改关系数据")
    @ApiResponses({
            @ApiResponse(code = 503, message = "修改失败!"),
            @ApiResponse(code = 200, message = "修改成功!")
    })
    public JsonRequest<Integer> updateOnePermissionRouter(
            @ApiParam(value = "待修改的关系信息", required = true)
            @RequestBody List<PermissionRouter> permissionRouters) {
        return permissionRouterService.updatePermissionRouter(permissionRouters);
    }

    /**
     * 批量解绑关系信息
     *
     * @param ids 关系ID
     * @return JSON
     */
    @DeleteMapping
    @ApiOperation(value = "批量解绑关系信息")
    @ApiResponses({
            @ApiResponse(code = 504, message = "删除失败!"),
            @ApiResponse(code = 200, message = "删除成功!")
    })
    public JsonRequest<Integer> deletePermissionRouter(
            @ApiParam(value = "待解绑的关系ID", required = true)
            @RequestBody String[] ids) {
        return permissionRouterService.deletePermissionRouter(ids);
    }


    /**
     * 通过路由ID解绑关系信息
     *
     * @param ids 路由ID
     * @return JSON
     */
    @DeleteMapping("/router")
    @ApiOperation(value = "通过路由ID解绑关系信息")
    @ApiResponses({
            @ApiResponse(code = 504, message = "删除失败!"),
            @ApiResponse(code = 200, message = "删除成功!")
    })
    public JsonRequest<Integer> deletePermissionRouterByRouter(
            @ApiParam(value = "路由ID", required = true)
            @RequestBody String[] ids) {
        return permissionRouterService.deletePermissionRouterByRouter(ids);
    }
}
