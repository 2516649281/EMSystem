package com.chunfeng.controller;

import com.chunfeng.dao.entity.Subject;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.ISubjectService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 科目控制层
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@RestController
@RequestMapping("/subject")
@Api(value = "科目控制类", tags = "科目操作访问接口")
public class SubjectController {

    /**
     * 科目业务层
     */
    @Autowired
    private ISubjectService subjectService;

    /**
     * 分类筛选科目信息
     *
     * @param subject 条件
     * @return JSON
     */
    @GetMapping("/select")
    @ApiOperation(value = "分类筛选科目信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    public JsonRequest<List<Subject>> lookSubject(
            @ApiParam(value = "条件", required = true)
            @RequestParam Subject subject) {
        return subjectService.lookSubject(subject);
    }

    /**
     * 查询所有科目信息
     *
     * @return JSON
     */
    @GetMapping
    @ApiOperation(value = "查询所有科目信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    public JsonRequest<List<Subject>> lookAllSubject() {
        return lookSubject(new Subject());
    }

    /**
     * 新增一条科目信息
     *
     * @param subject 科目信息
     * @return JSON
     */
    @PostMapping
    @ApiOperation(value = "新增一条科目信息")
    @ApiResponses({
            @ApiResponse(code = 502, message = "添加失败!"),
            @ApiResponse(code = 200, message = "添加成功!")
    })
    public JsonRequest<Integer> addOneSubject(
            @ApiParam(value = "待新增的科目信息", required = true)
            @RequestBody Subject subject) {
        return subjectService.addOneSubject(subject);
    }

    /**
     * 修改一条科目信息
     *
     * @param subject 科目信息
     * @return JSON
     */
    @PutMapping
    @ApiOperation(value = "修改一条科目信息")
    @ApiResponses({
            @ApiResponse(code = 503, message = "修改失败!"),
            @ApiResponse(code = 200, message = "修改成功!")
    })
    public JsonRequest<Integer> updateOneSubject(
            @ApiParam(value = "待修改的科目信息", required = true)
            @RequestBody Subject subject) {
        return subjectService.updateOneSubject(subject);
    }

    /**
     * 批量删除科目信息
     *
     * @param ids 科目ID
     * @return JSON
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除科目信息")
    @ApiResponses({
            @ApiResponse(code = 504, message = "删除失败!"),
            @ApiResponse(code = 200, message = "删除成功!")
    })
    public JsonRequest<Integer> deleteSubject(
            @ApiParam(value = "待删除的科目ID", required = true)
            @RequestBody String[] ids) {
        return subjectService.deleteSubject(ids);
    }
}
