package com.chunfeng.controller;

import com.chunfeng.dao.entity.Permission;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IPermissionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限控制层
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@RestController
@RequestMapping("/per")
@Api(value = "权限控制层", tags = "权限操作接口")
public class PermissionController {

    /**
     * 权限业务层
     */
    @Autowired
    private IPermissionService permissionService;

    /**
     * 分类筛选权限信息
     *
     * @param permission 条件
     * @return JSON
     */
    @GetMapping("/select")
    @ApiOperation(value = "分类筛选权限信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:per:select')")
    public JsonRequest<List<Permission>> lookPermission(
            @ApiParam(value = "条件", required = true)
                    Permission permission) {
        return permissionService.lookPermission(permission);
    }

    /**
     * 查询所有权限信息
     *
     * @return JSON
     */
    @GetMapping
    @ApiOperation(value = "查询所有权限信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:per:select')")
    public JsonRequest<List<Permission>> lookAllPermission() {
        return permissionService.lookAllPermission();
    }

    /**
     * 新增一条权限信息
     *
     * @param permission 权限信息
     * @return JSON
     */
    @PostMapping
    @ApiOperation(value = "新增一条权限信息")
    @ApiResponses({
            @ApiResponse(code = 502, message = "添加失败!"),
            @ApiResponse(code = 200, message = "添加成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:per:insert')")
    public JsonRequest<Integer> addOnePermission(
            @ApiParam(value = "待添加的权限信息", required = true)
            @RequestBody Permission permission) {
        return permissionService.addOnePermission(permission);
    }

    /**
     * 修改一条权限信息
     *
     * @param permission 权限信息
     * @return JSON
     */
    @PutMapping
    @ApiOperation(value = "修改一条权限信息")
    @ApiResponses({
            @ApiResponse(code = 503, message = "修改失败!"),
            @ApiResponse(code = 200, message = "修改成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:per:update')")
    public JsonRequest<Integer> updateOnePermission(
            @ApiParam(value = "待修改的权限信息", required = true)
                    Permission permission) {
        return permissionService.updateOnePermission(permission);
    }

    /**
     * 批量删除权限信息
     *
     * @param ids 权限ID
     * @return JSON
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除权限信息")
    @ApiResponses({
            @ApiResponse(code = 504, message = "删除失败!"),
            @ApiResponse(code = 200, message = "删除成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:per:delete')")
    public JsonRequest<Integer> deletePermission(
            @ApiParam(value = "待修改的权限ID", required = true)
            @RequestBody String[] ids) {
        return permissionService.deletePermission(ids);
    }
}
