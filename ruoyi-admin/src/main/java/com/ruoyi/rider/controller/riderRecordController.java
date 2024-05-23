package com.ruoyi.rider.controller;

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
import com.ruoyi.rider.domain.riderRecord;
import com.ruoyi.rider.service.IriderRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 历史接单记录Controller
 * 
 * @author xpl
 * @date 2024-05-22
 */
@RestController
@RequestMapping("/rider/riderRecord")
public class riderRecordController extends BaseController
{
    @Autowired
    private IriderRecordService riderRecordService;

    /**
     * 查询历史接单记录列表
     */
    @PreAuthorize("@ss.hasPermi('rider:riderRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(riderRecord riderRecord)
    {
        startPage();
        riderRecord.setRiderId(getLoginUser().getUserId());
        List<riderRecord> list = riderRecordService.selectriderRecordList(riderRecord);
        return getDataTable(list);
    }

    /**
     * 导出历史接单记录列表
     */
    @PreAuthorize("@ss.hasPermi('rider:riderRecord:export')")
    @Log(title = "历史接单记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, riderRecord riderRecord)
    {
        List<riderRecord> list = riderRecordService.selectriderRecordList(riderRecord);
        ExcelUtil<riderRecord> util = new ExcelUtil<riderRecord>(riderRecord.class);
        util.exportExcel(response, list, "历史接单记录数据");
    }

    /**
     * 获取历史接单记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('rider:riderRecord:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(riderRecordService.selectriderRecordByRecordId(recordId));
    }

    /**
     * 新增历史接单记录
     */
    @PreAuthorize("@ss.hasPermi('rider:riderRecord:add')")
    @Log(title = "历史接单记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody riderRecord riderRecord)
    {
        return toAjax(riderRecordService.insertriderRecord(riderRecord));
    }

    /**
     * 修改历史接单记录
     */
    @PreAuthorize("@ss.hasPermi('rider:riderRecord:edit')")
    @Log(title = "历史接单记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody riderRecord riderRecord)
    {
        return toAjax(riderRecordService.updateriderRecord(riderRecord));
    }

    /**
     * 删除历史接单记录
     */
    @PreAuthorize("@ss.hasPermi('rider:riderRecord:remove')")
    @Log(title = "历史接单记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(riderRecordService.deleteriderRecordByRecordIds(recordIds));
    }
}
