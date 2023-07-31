package com.chunfeng.controller;

import com.chunfeng.dao.entity.Role;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IRoleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制层
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@RestController
@RequestMapping("/role")
@Api(value = "角色控制层", tags = "角色操作访问接口")
public class RoleController {

    /**
     * 角色业务层
     */
    @Autowired
    private IRoleService roleService;

    /**
     * 分类筛选角色信息
     *
     * @param role 条件
     * @return JSON
     */
    @GetMapping("/select")
    @ApiOperation(value = "分类筛选角色信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:role:select')")
    public JsonRequest<List<Role>> lookRole(
            @ApiParam(value = "条件", required = true)
                    Role role) {
        return roleService.lookRole(role);
    }

    /**
     * 查询所有角色信息
     *
     * @return JSON
     */
    @GetMapping
    @ApiOperation(value = "查询所有角色信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:role:select')")
    public JsonRequest<List<Role>> lookAllRole() {
        return roleService.lookAllRole();
    }

    /**
     * 根据ID值查询角色信息
     *
     * @param roleId 角色ID
     * @return JSON
     */
    @GetMapping("/one")
    @ApiOperation(value = "查询单个角色信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:role:select','sys:per:select')")
    public JsonRequest<Role> lookOneRole(
            @ApiParam(value = "角色ID", required = true)
            @RequestParam String roleId) {
        return roleService.lookOneRole(roleId);
    }

    /**
     * 新增一条角色信息
     *
     * @param role 角色信息
     * @return JSON
     */
    @PostMapping
    @ApiOperation(value = "新增一条角色信息")
    @ApiResponses({
            @ApiResponse(code = 502, message = "添加失败!"),
            @ApiResponse(code = 200, message = "添加成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:role:insert')")
    public JsonRequest<Integer> addOneRole(
            @ApiParam(value = "待添加的角色信息", required = true)
            @RequestBody Role role) {
        return roleService.addOneRole(role);
    }

    /**
     * 修改一条角色信息
     *
     * @param role 角色信息
     * @return JSON
     */
    @PutMapping
    @ApiOperation(value = "修改一条角色信息")
    @ApiResponses({
            @ApiResponse(code = 503, message = "修改失败!"),
            @ApiResponse(code = 200, message = "修改成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:role:update')")
    public JsonRequest<Integer> updateOneRole(
            @ApiParam(value = "待修改的角色信息", required = true)
            @RequestBody Role role) {
        return roleService.updateOneRole(role);
    }

    /**
     * 批量删除角色信息
     *
     * @param ids 角色ID
     * @return JSON
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除角色信息")
    @ApiResponses({
            @ApiResponse(code = 504, message = "删除失败!"),
            @ApiResponse(code = 200, message = "删除成功!")
    })
    @PreAuthorize("hasAnyAuthority('sys:role:delete')")
    public JsonRequest<Integer> deleteRole(
            @ApiParam(value = "待删除的角色ID", required = true)
            @RequestBody String[] ids) {
        return roleService.deleteRole(ids);
    }
}
