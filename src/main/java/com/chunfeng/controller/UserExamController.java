package com.chunfeng.controller;

import com.chunfeng.dao.entity.UserExam;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IUserExamService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 历史试卷控制类
 *
 * @author by 春风能解释
 * <p>
 * 2023/9/6
 */
@RestController
@RequestMapping("/ue")
@Api(value = "历史试卷控制类", tags = {"历史试卷操作访问接口"})
public class UserExamController {

    /**
     * 业务层注入
     */
    @Autowired
    private IUserExamService userExamService;


    /**
     * 分类筛选关系信息
     *
     * @param userExam 条件
     * @return JSON
     */
    @GetMapping("/select")
    @ApiOperation(value = "分类筛选关系信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    public JsonRequest<List<UserExam>> lookUserExam(
            @ApiParam(value = "条件", required = true)
            UserExam userExam) {
        return userExamService.lookUserExam(userExam);
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
    public JsonRequest<List<UserExam>> lookAllUserExam() {
        return userExamService.lookAllUserExam();
    }

    /**
     * 根据ID值查询详细关系信息
     *
     * @param id 关系ID
     * @return JSON
     */
    @GetMapping("/one")
    @ApiOperation(value = "根据ID值查询详细关系信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    public JsonRequest<UserExam> lookUserExamById(
            @ApiParam(value = "条件ID", required = true)
            @RequestParam String id) {
        return userExamService.lookUserExamById(id);
    }


    /**
     * 批量绑定关系信息
     *
     * @param userExams 关系信息
     * @return JSON
     */
    @PostMapping
    @ApiOperation(value = "批量绑定关系信息")
    @ApiResponses({
            @ApiResponse(code = 502, message = "添加失败!"),
            @ApiResponse(code = 200, message = "添加成功!")
    })
    public JsonRequest<Integer> addUserExam(
            @ApiParam(value = "待绑定的关系信息", required = true)
            @RequestBody List<UserExam> userExams) {
        return userExamService.addUserExam(userExams);
    }

    /**
     * 批量修改关系数据
     *
     * @param userExams 关系信息
     * @return JSON
     */
    @PutMapping
    @ApiOperation(value = "批量修改关系数据")
    @ApiResponses({
            @ApiResponse(code = 503, message = "修改失败!"),
            @ApiResponse(code = 200, message = "修改成功!")
    })
    public JsonRequest<Integer> updateUserExam(
            @ApiParam(value = "待修改的关系信息", required = true)
            @RequestBody List<UserExam> userExams) {
        return userExamService.updateUserExam(userExams);
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
    public JsonRequest<Integer> deleteUserExam(
            @ApiParam(value = "待解绑的关系ID", required = true)
            @RequestBody String[] ids) {
        return userExamService.deleteUserExam(ids);
    }
}
