package com.chunfeng.controller;

import com.chunfeng.dao.entity.Gradle;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IGradleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 年级控制层
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@RestController
@RequestMapping("/gradle")
@Api(value = "年级控制层", tags = "年级操作访问接口")
public class GradleController {

    /**
     * 年级业务层
     */
    @Autowired
    private IGradleService gradleService;

    /**
     * 分类筛选年级信息
     *
     * @param gradle 条件
     * @return JSON
     */
    @GetMapping("/select")
    @ApiOperation(value = "分类筛选年级信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    public JsonRequest<List<Gradle>> lookGradle(
            @ApiParam(value = "条件", required = true)
            Gradle gradle) {
        return gradleService.lookGradle(gradle);
    }

    /**
     * 查询所有年级信息
     *
     * @return JSON
     */
    @GetMapping
    @ApiOperation(value = "查询所有年级信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    public JsonRequest<List<Gradle>> lookAllGradle() {
        return gradleService.lookAllGradle();
    }

    /**
     * 新增一条年级信息
     *
     * @param gradle 年级信息
     * @return JSON
     */
    @PostMapping
    @ApiOperation(value = "新增一条年级信息")
    @ApiResponses({
            @ApiResponse(code = 502, message = "添加失败!"),
            @ApiResponse(code = 200, message = "添加成功!")
    })
    public JsonRequest<Integer> addOneGradle(
            @ApiParam(value = "待添加的年级信息", required = true)
            @RequestBody Gradle gradle) {
        return gradleService.addOneGradle(gradle);
    }

    /**
     * 修改一条年级信息
     *
     * @param gradle 年级信息
     * @return JSON
     */
    @PutMapping
    @ApiOperation(value = "修改一条年级信息")
    @ApiResponses({
            @ApiResponse(code = 503, message = "修改失败!"),
            @ApiResponse(code = 200, message = "修改成功!")
    })
    public JsonRequest<Integer> updateOneGradle(
            @ApiParam(value = "待修改的年级信息", required = true)
            @RequestBody Gradle gradle) {
        return gradleService.updateOneGradle(gradle);
    }

    /**
     * 批量删除年级信息
     *
     * @param ids 年级ID
     * @return JSON
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除年级信息")
    @ApiResponses({
            @ApiResponse(code = 504, message = "删除失败!"),
            @ApiResponse(code = 200, message = "删除成功!")
    })
    public JsonRequest<Integer> deleteGradle(
            @ApiParam(value = "待删除的年级ID", required = true)
            @RequestBody String[] ids) {
        return gradleService.deleteGradle(ids);
    }
}
