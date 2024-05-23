package com.ruoyi.record.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.store.domain.mystoreComment;
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
import com.ruoyi.record.domain.myriderecord;
import com.ruoyi.record.service.ImyriderecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我的跑腿订单Controller
 * 
 * @author xpl
 * @date 2024-05-16
 */
@RestController
@RequestMapping("/record/myriderecord")
public class myriderecordController extends BaseController
{
    @Autowired
    private ImyriderecordService myriderecordService;

    /**
     * 查询我的跑腿订单列表
     */
    @PreAuthorize("@ss.hasPermi('record:myriderecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(myriderecord myriderecord)
    {
        startPage();
        myriderecord.setType(0L);;
        List<myriderecord> list = myriderecordService.selectmyriderecordList(myriderecord);
        return getDataTable(list);
    }

    /**
     * 导出我的跑腿订单列表
     */
    @PreAuthorize("@ss.hasPermi('record:myriderecord:export')")
    @Log(title = "我的跑腿订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, myriderecord myriderecord)
    {
        List<myriderecord> list = myriderecordService.selectmyriderecordList(myriderecord);
        ExcelUtil<myriderecord> util = new ExcelUtil<myriderecord>(myriderecord.class);
        util.exportExcel(response, list, "我的跑腿订单数据");
    }

    /**
     * 获取我的跑腿订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('record:myriderecord:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(myriderecordService.selectmyriderecordByRecordId(recordId));
    }

    /**
     * 新增我的跑腿订单
     */
    @PreAuthorize("@ss.hasPermi('record:myriderecord:add')")
    @Log(title = "我的跑腿订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody myriderecord myriderecord)
    {
        return toAjax(myriderecordService.insertmyriderecord(myriderecord));
    }

    /**
     * 修改我的跑腿订单
     */
    @PreAuthorize("@ss.hasPermi('record:myriderecord:edit')")
    @Log(title = "我的跑腿订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody myriderecord myriderecord)
    {
        return toAjax(myriderecordService.updatemyriderecord(myriderecord));
    }

    /**
     * 删除我的跑腿订单
     */
    @PreAuthorize("@ss.hasPermi('record:myriderecord:remove')")
    @Log(title = "我的跑腿订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(myriderecordService.deletemyriderecordByRecordIds(recordIds));
    }

    @Log(title = "我的跑腿订单", businessType = BusinessType.INSERT)
    @PostMapping("/comment")
    public AjaxResult commentMyRideRecord(@RequestBody mystoreComment mystoreComment)
    {
        return toAjax(myriderecordService.insertmyriderecordcomment(mystoreComment));
    }
}
