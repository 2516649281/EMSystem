package com.chunfeng.controller;

import com.chunfeng.dao.entity.Exam;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IExamService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 试卷控制层
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@RestController
@RequestMapping("/exam")
@Api(value = "试卷控制类", tags = "试卷操作接口")
public class ExamController {

    /**
     * 试卷业务层
     */
    @Autowired
    private IExamService examService;

    /**
     * 分类筛选试卷信息
     *
     * @param exam 条件
     * @return JSON
     */
    @GetMapping("/select")
    @ApiOperation(value = "分类筛选试卷信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    public JsonRequest<List<Exam>> lookExam(
            @ApiParam(value = "条件", required = true)
                    Exam exam) {
        return examService.lookExam(exam);
    }

    /**
     * 查询所有试卷信息
     *
     * @return JSON
     */
    @GetMapping
    @ApiOperation(value = "查询所有试卷信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    public JsonRequest<List<Exam>> lookAllExam() {
        return examService.lookAllExam();
    }

    /**
     * 新增一条试卷信息
     *
     * @param exam 试卷信息
     * @return JSON
     */
    @PostMapping
    @ApiOperation(value = "新增一条试卷信息")
    @ApiResponses({
            @ApiResponse(code = 502, message = "添加失败!"),
            @ApiResponse(code = 200, message = "添加成功!")
    })
    public JsonRequest<Integer> addOneExam(
            @ApiParam(value = "待添加的试卷信息", required = true)
            @RequestBody Exam exam) {
        return examService.addOneExam(exam);
    }

    /**
     * 修改一条试卷信息
     *
     * @param exam 试卷信息
     * @return JSON
     */
    @PutMapping
    @ApiOperation(value = "修改一条角色信息")
    @ApiResponses({
            @ApiResponse(code = 503, message = "修改失败!"),
            @ApiResponse(code = 200, message = "修改成功!")
    })
    public JsonRequest<Integer> updateOneExam(
            @ApiParam(value = "待修改的试卷信息", required = true)
            @RequestBody Exam exam) {
        return examService.updateOneExam(exam);
    }

    /**
     * 批量删除试卷信息
     *
     * @param ids 试卷ID
     * @return JSON
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除试卷信息")
    @ApiResponses({
            @ApiResponse(code = 504, message = "删除失败!"),
            @ApiResponse(code = 200, message = "删除成功!")
    })
    public JsonRequest<Integer> deleteExam(
            @ApiParam(value = "待修改的试卷ID", required = true)
            @RequestBody String[] ids) {
        return examService.deleteExam(ids);
    }
}
