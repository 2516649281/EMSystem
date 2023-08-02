package com.chunfeng.controller;

import com.chunfeng.dao.entity.Router;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IRouterService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 路由控制层
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@RestController
@RequestMapping("/router")
@Api(value = "路由控制层", tags = "路由操作访问接口")
public class RouterController {

    /**
     * 路由业务层
     */
    @Autowired
    private IRouterService routerService;

    /**
     * 分类筛选路由信息
     *
     * @param router 条件
     * @return JSON
     */
    @GetMapping("/select")
    @ApiOperation(value = "分类筛选路由信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    public JsonRequest<List<Router>> lookRouter(
            @ApiParam(value = "条件", required = true)
                    Router router) {
        return routerService.lookRouter(router);
    }

    /**
     * 查询所有路由信息
     *
     * @return JSON
     */
    @GetMapping
    @ApiOperation(value = "查询所有路由信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    public JsonRequest<List<Router>> lookAllRouter() {
        return routerService.lookAllRouter();
    }

    /**
     * 查询一条路由信息
     *
     * @param routerId 路由ID
     * @return JSON
     */
    @GetMapping("/one")
    @ApiOperation(value = "查询一条路由信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    public JsonRequest<Router> lookOneRouter(
            @RequestParam String routerId) {
        return routerService.lookOneRouter(routerId);
    }

    /**
     * 新增一条路由信息
     *
     * @param router 路由信息
     * @return JSON
     */
    @PostMapping
    @ApiOperation(value = "新增一条路由信息")
    @ApiResponses({
            @ApiResponse(code = 502, message = "添加失败!"),
            @ApiResponse(code = 200, message = "添加成功!")
    })
    public JsonRequest<Integer> addOneRouter(
            @ApiParam(value = "待添加的路由信息", required = true)
            @RequestBody Router router) {
        return routerService.addOneRouter(router);
    }

    /**
     * 修改一条路由信息
     *
     * @param router 路由信息
     * @return JSON
     */
    @PutMapping
    @ApiOperation(value = "修改一条路由信息")
    @ApiResponses({
            @ApiResponse(code = 503, message = "修改失败!"),
            @ApiResponse(code = 200, message = "修改成功!")
    })
    public JsonRequest<Integer> updateOneRouter(
            @ApiParam(value = "待修改的路由信息", required = true)
            @RequestBody Router router) {
        return routerService.updateOneRouter(router);
    }

    /**
     * 批量删除路由信息
     *
     * @param ids 路由ID
     * @return JSON
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除路由信息")
    @ApiResponses({
            @ApiResponse(code = 504, message = "删除失败!"),
            @ApiResponse(code = 200, message = "删除成功!")
    })
    public JsonRequest<Integer> deleteRouter(
            @ApiParam(value = "待删除的路由ID", required = true)
            @RequestBody String[] ids) {
        return routerService.deleteRouter(ids);
    }
}
