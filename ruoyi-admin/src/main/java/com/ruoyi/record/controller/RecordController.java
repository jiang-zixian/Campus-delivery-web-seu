package com.ruoyi.record.controller;

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
import com.ruoyi.record.domain.Record;
import com.ruoyi.record.service.IRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我的订单Controller
 * 
 * @author ruoyi
 * @date 2024-05-11
 */
@RestController
@RequestMapping("/record/record")
public class RecordController extends BaseController
{
    @Autowired
    private IRecordService recordService;

    /**
     * 查询我的订单列表
     */
    @PreAuthorize("@ss.hasPermi('record:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(Record record)
    {
        startPage();
        if(!getLoginUser().getUser().isAdmin())
            record.setuId(getLoginUser().getUserId());
        List<Record> list = recordService.selectRecordList(record);
        return getDataTable(list);
    }

    /**
     * 导出我的订单列表
     */
    @PreAuthorize("@ss.hasPermi('record:record:export')")
    @Log(title = "我的订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Record record)
    {
        List<Record> list = recordService.selectRecordList(record);
        ExcelUtil<Record> util = new ExcelUtil<Record>(Record.class);
        util.exportExcel(response, list, "我的订单数据");
    }

    /**
     * 获取我的订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('record:record:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(recordService.selectRecordByRecordId(recordId));
    }

    /**
     * 新增我的订单
     */
    @PreAuthorize("@ss.hasPermi('record:record:add')")
    @Log(title = "我的订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Record record)
    {
        record.setuId(getLoginUser().getUserId());
        return toAjax(recordService.insertRecord(record));
    }

    /**
     * 修改我的订单
     */
    @PreAuthorize("@ss.hasPermi('record:record:edit')")
    @Log(title = "我的订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Record record)
    {
        return toAjax(recordService.updateRecord(record));
    }

    /**
     * 删除我的订单
     */
    @PreAuthorize("@ss.hasPermi('record:record:remove')")
    @Log(title = "我的订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(recordService.deleteRecordByRecordIds(recordIds));
    }
}
