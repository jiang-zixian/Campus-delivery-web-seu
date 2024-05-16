package com.ruoyi.store.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.store.domain.mystoreComment;
import com.ruoyi.store.service.ImystoreCommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我的评论Controller
 * 
 * @author ruoyi
 * @date 2024-05-15
 */
@RestController
@RequestMapping("/store/myComment")
public class mystoreCommentController extends BaseController
{
    @Autowired
    private ImystoreCommentService mystoreCommentService;

    /**
     * 查询我的评论列表
     */
    @PreAuthorize("@ss.hasPermi('store:myComment:list')")
    @GetMapping("/list")
    public TableDataInfo list(mystoreComment mystoreComment)
    {
        startPage();
        List<mystoreComment> list = mystoreCommentService.selectmystoreCommentList(mystoreComment);
        return getDataTable(list);
    }

    /**
     * 导出我的评论列表
     */
    @PreAuthorize("@ss.hasPermi('store:myComment:export')")
    @Log(title = "我的评论", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, mystoreComment mystoreComment)
    {
        List<mystoreComment> list = mystoreCommentService.selectmystoreCommentList(mystoreComment);
        ExcelUtil<mystoreComment> util = new ExcelUtil<mystoreComment>(mystoreComment.class);
        util.exportExcel(response, list, "我的评论数据");
    }

    /**
     * 获取我的评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:myComment:query')")
    @GetMapping(value = "/{commentId}")
    public AjaxResult getInfo(@PathVariable("commentId") Long commentId)
    {
        return success(mystoreCommentService.selectmystoreCommentByCommentId(commentId));
    }

    /**
     * 新增我的评论
     */
    @PreAuthorize("@ss.hasPermi('store:myComment:add')")
    @Log(title = "我的评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody mystoreComment mystoreComment)
    {
        return toAjax(mystoreCommentService.insertmystoreComment(mystoreComment));
    }

    /**
     * 修改我的评论
     */
    @PreAuthorize("@ss.hasPermi('store:myComment:edit')")
    @Log(title = "我的评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody mystoreComment mystoreComment)
    {
        return toAjax(mystoreCommentService.updatemystoreComment(mystoreComment));
    }

    /**
     * 删除我的评论
     */
    @PreAuthorize("@ss.hasPermi('store:myComment:remove')")
    @Log(title = "我的评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{commentIds}")
    public AjaxResult remove(@PathVariable Long[] commentIds)
    {
        return toAjax(mystoreCommentService.deletemystoreCommentByCommentIds(commentIds));
    }
}
