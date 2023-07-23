package com.chunfeng.controller;

import com.chunfeng.dao.entity.Problem;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IProblemService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题库控制层
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@RestController
@RequestMapping("/pro")
@Api(value = "题库控制层", tags = "题库操作接口")
public class ProblemController {

    /**
     * 题库业务层
     */
    @Autowired
    private IProblemService problemService;

    /**
     * 分类筛选题库信息
     *
     * @param problem 条件
     * @return JSON
     */
    @GetMapping("/select")
    @ApiOperation(value = "分类筛选题库信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    @PreAuthorize("hasAnyAuthority('op:pro:select','user:pro:select')")
    public JsonRequest<List<Problem>> lookProblem(
            @ApiParam(value = "条件", required = true)
            @RequestParam Problem problem) {
        return problemService.lookProblem(problem);
    }

    /**
     * 查询所有题库信息
     *
     * @return JSON
     */
    @GetMapping
    @ApiOperation(value = "查询所有题库信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    @PreAuthorize("hasAnyAuthority('op:pro:select','user:pro:select')")
    public JsonRequest<List<Problem>> lookAllProblem() {
        return problemService.lookAllProblem();
    }

    /**
     * 新增一条题库信息
     *
     * @param problem 题库信息
     * @return JSON
     */
    @PostMapping
    @ApiOperation(value = "新增一条题库信息")
    @ApiResponses({
            @ApiResponse(code = 502, message = "添加失败!"),
            @ApiResponse(code = 200, message = "添加成功!")
    })
    @PreAuthorize("hasAnyAuthority('op:pro:insert')")
    public JsonRequest<Integer> addOneProblem(
            @ApiParam(value = "待添加的试卷信息", required = true)
            @RequestBody Problem problem) {
        return problemService.addOneProblem(problem);
    }

    /**
     * 修改一条题库信息
     *
     * @param problem 题库信息
     * @return JSON
     */
    @PutMapping
    @ApiOperation(value = "修改一条题库信息")
    @ApiResponses({
            @ApiResponse(code = 503, message = "修改失败!"),
            @ApiResponse(code = 200, message = "修改成功!")
    })
    @PreAuthorize("hasAnyAuthority('op:pro:update')")
    public JsonRequest<Integer> updateOneProblem(
            @ApiParam(value = "待修改的试卷信息", required = true)
            @RequestBody Problem problem) {
        return problemService.updateOneProblem(problem);
    }

    /**
     * 批量删除题库信息
     *
     * @param ids 题库ID
     * @return JSON
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除题库信息")
    @ApiResponses({
            @ApiResponse(code = 504, message = "删除失败!"),
            @ApiResponse(code = 200, message = "删除成功!")
    })
    @PreAuthorize("hasAnyAuthority('op:pro:delete')")
    public JsonRequest<Integer> deleteProblem(
            @ApiParam(value = "待解绑的试卷ID", required = true)
            @RequestBody String[] ids) {
        return problemService.deleteProblem(ids);
    }
}
