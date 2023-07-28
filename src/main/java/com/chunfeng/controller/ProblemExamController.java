package com.chunfeng.controller;

import com.chunfeng.dao.entity.ProblemExam;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IProblemExamService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题库-试卷关系业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@RestController
@RequestMapping("/pe")
@Api(value = "题库-试卷关系控制层", tags = "题库-试卷关系操作接口")
public class ProblemExamController {

    /**
     * 关系业务层
     */
    @Autowired
    private IProblemExamService problemExamService;

    /**
     * 分类筛选关系信息
     *
     * @param problemExam 条件
     * @return JSON
     */
    @GetMapping("/select")
    @ApiOperation(value = "分类筛选关系信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    public JsonRequest<List<ProblemExam>> lookProblemExam(
            @ApiParam(value = "条件", required = true)
                    ProblemExam problemExam) {
        return problemExamService.lookProblemExam(problemExam);
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
    public JsonRequest<List<ProblemExam>> lookAllProblemExam() {
        return problemExamService.lookAllProblemExam();
    }

    /**
     * 批量绑定关系信息
     *
     * @param problemExams 关系信息
     * @return JSON
     */
    @PostMapping
    @ApiOperation(value = "批量绑定关系信息")
    @ApiResponses({
            @ApiResponse(code = 502, message = "添加失败!"),
            @ApiResponse(code = 200, message = "添加成功!")
    })
    public JsonRequest<Integer> addOneProblemExam(
            @ApiParam(value = "待绑定的关系信息", required = true)
            @RequestBody List<ProblemExam> problemExams) {
        return problemExamService.addProblemExam(problemExams);
    }

    /**
     * 批量修改关系数据
     *
     * @param problemExams 关系信息
     * @return JSON
     */
    @PutMapping
    @ApiOperation(value = "批量修改关系数据")
    @ApiResponses({
            @ApiResponse(code = 503, message = "修改失败!"),
            @ApiResponse(code = 200, message = "修改成功!")
    })
    public JsonRequest<Integer> updateOneProblemExam(
            @ApiParam(value = "待修改的关系信息", required = true)
            @RequestBody List<ProblemExam> problemExams) {
        return problemExamService.updateProblemExam(problemExams);
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
    public JsonRequest<Integer> deleteProblemExam(
            @ApiParam(value = "待解绑的关系ID", required = true)
            @RequestBody String[] ids) {
        return problemExamService.deleteProblemExam(ids);
    }
}
