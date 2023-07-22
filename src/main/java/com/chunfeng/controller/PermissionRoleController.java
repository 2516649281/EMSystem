package com.chunfeng.controller;

import com.chunfeng.dao.entity.PermissionRole;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IPermissionRoleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限-角色关系控制层
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@RestController
@RequestMapping("/pr")
@Api(value = "权限-角色关系控制层", tags = "权限-角色关系操作接口")
public class PermissionRoleController {

    /**
     * 关系业务层
     */
    @Autowired
    private IPermissionRoleService permissionRoleService;

    /**
     * 分类筛选关系信息
     *
     * @param permissionRole 条件
     * @return JSON
     */
    @GetMapping("/select")
    @ApiOperation(value = "分类筛选关系信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:role:select','sys:per:select')")
    public JsonRequest<List<PermissionRole>> lookPermissionRole(
            @ApiParam(value = "条件", required = true)
            @RequestParam PermissionRole permissionRole) {
        return permissionRoleService.lookPermissionRole(permissionRole);
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
    @PreAuthorize("hasAnyAuthority('sys:role:select','sys:per:select')")
    public JsonRequest<List<PermissionRole>> lookAllPermissionRole() {
        return permissionRoleService.lookAllPermissionRole();
    }

    /**
     * 绑定一条关系信息
     *
     * @param permissionRole 关系信息
     * @return JSON
     */
    @PostMapping
    @ApiOperation(value = "绑定一条关系信息")
    @ApiResponses({
            @ApiResponse(code = 502, message = "添加失败!"),
            @ApiResponse(code = 200, message = "添加成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:role:insert','sys:per:insert')")
    public JsonRequest<Integer> addOnePermissionRole(
            @ApiParam(value = "待绑定的关系信息", required = true)
            @RequestBody PermissionRole permissionRole) {
        return permissionRoleService.addOnePermissionRole(permissionRole);
    }

    /**
     * 修改一条关系信息
     *
     * @param permissionRole 关系信息
     * @return JSON
     */
    @PutMapping
    @ApiOperation(value = "修改一条关系信息")
    @ApiResponses({
            @ApiResponse(code = 503, message = "修改失败!"),
            @ApiResponse(code = 200, message = "修改成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:role:update','sys:per:update')")
    public JsonRequest<Integer> updateOnePermissionRole(
            @ApiParam(value = "待修改的关系信息", required = true)
            @RequestBody PermissionRole permissionRole) {
        return permissionRoleService.updateOnePermissionRole(permissionRole);
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
    @PreAuthorize("hasAnyAuthority('sys:role:delete','sys:per:delete')")
    public JsonRequest<Integer> deletePermissionRole(
            @ApiParam(value = "待解绑的关系ID", required = true)
            @RequestBody String[] ids) {
        return permissionRoleService.deletePermissionRole(ids);
    }
}
