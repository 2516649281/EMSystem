package com.chunfeng.controller;

import com.chunfeng.dao.entity.FeedBack;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.service.IFeedBackService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 反馈业务层实现
 *
 * @author by 春风能解释
 * <p>
 * 2023/7/17
 */
@RestController
@RequestMapping("/feed")
@Api(value = "反馈信息控制类", tags = "反馈信息操作接口")
public class FeedBackController {

    /**
     * 反馈业务层
     */
    @Autowired
    private IFeedBackService feedBackService;

    /**
     * 分类筛选反馈信息
     *
     * @param feedBack 条件
     * @return JSON
     */
    @GetMapping("/select")
    @ApiOperation(value = "分类筛选反馈信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    @PreAuthorize("hasAuthority('sys:feed:select')")
    public JsonRequest<List<FeedBack>> lookFeedBack(
            @ApiParam(value = "条件", required = true)
            @RequestParam FeedBack feedBack) {
        return feedBackService.lookFeedBack(feedBack);
    }

    /**
     * 查询所有反馈信息
     *
     * @return JSON
     */
    @GetMapping
    @ApiOperation(value = "查询所有反馈信息")
    @ApiResponses({
            @ApiResponse(code = 404, message = "没有找到任何数据!"),
            @ApiResponse(code = 200, message = "查询成功!")
    })
    @PreAuthorize("hasAuthority('sys:feed:select')")
    public JsonRequest<List<FeedBack>> lookAllFeedBack() {
        return feedBackService.lookAllFeedBack();
    }

    /**
     * 新增一条反馈信息
     *
     * @param feedBack 反馈信息
     * @return JSON
     */
    @PostMapping
    @ApiOperation(value = "新增一条反馈信息")
    @ApiResponses({
            @ApiResponse(code = 502, message = "添加失败!"),
            @ApiResponse(code = 200, message = "添加成功!")
    })
    @PreAuthorize("hasAuthority('user:feed:insert')")
    public JsonRequest<Integer> addOneFeedBack(
            @ApiParam(value = "待添加的反馈信息", required = true)
            @RequestBody FeedBack feedBack) {
        return feedBackService.addOneFeedBack(feedBack);
    }

    /**
     * 批量删除反馈信息
     *
     * @param ids 反馈ID
     * @return JSON
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除反馈信息")
    @ApiResponses({
            @ApiResponse(code = 504, message = "删除失败!"),
            @ApiResponse(code = 200, message = "删除成功!")
    })
    @PreAuthorize("hasAuthority('sys:feed:delete')")
    public JsonRequest<Integer> deleteFeedBack(
            @ApiParam(value = "待修改的反馈信息ID", required = true)
            @RequestBody String[] ids) {
        return feedBackService.deleteFeedBack(ids);
    }
}
