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
import com.ruoyi.record.domain.zitiRecord;
import com.ruoyi.record.service.IzitiRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 自提订单Controller
 * 
 * @author wj
 * @date 2024-05-21
 */
@RestController
@RequestMapping("/record/zitiRecord")
public class zitiRecordController extends BaseController
{
    @Autowired
    private IzitiRecordService zitiRecordService;

    /**
     * 查询自提订单列表
     */
    @PreAuthorize("@ss.hasPermi('record:zitiRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(zitiRecord zitiRecord)
    {
        startPage();
        zitiRecord.setType(1L);;
        List<zitiRecord> list = zitiRecordService.selectzitiRecordList(zitiRecord);
        return getDataTable(list);
    }

    /**
     * 导出自提订单列表
     */
    @PreAuthorize("@ss.hasPermi('record:zitiRecord:export')")
    @Log(title = "自提订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, zitiRecord zitiRecord)
    {
        List<zitiRecord> list = zitiRecordService.selectzitiRecordList(zitiRecord);
        ExcelUtil<zitiRecord> util = new ExcelUtil<zitiRecord>(zitiRecord.class);
        util.exportExcel(response, list, "自提订单数据");
    }

    /**
     * 获取自提订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('record:zitiRecord:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(zitiRecordService.selectzitiRecordByRecordId(recordId));
    }

    /**
     * 新增自提订单
     */
    @PreAuthorize("@ss.hasPermi('record:zitiRecord:add')")
    @Log(title = "自提订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody zitiRecord zitiRecord)
    {
        return toAjax(zitiRecordService.insertzitiRecord(zitiRecord));
    }

    /**
     * 修改自提订单
     */
    @PreAuthorize("@ss.hasPermi('record:zitiRecord:edit')")
    @Log(title = "自提订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody zitiRecord zitiRecord)
    {
        return toAjax(zitiRecordService.updatezitiRecord(zitiRecord));
    }

    /**
     * 删除自提订单
     */
    @PreAuthorize("@ss.hasPermi('record:zitiRecord:remove')")
    @Log(title = "自提订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(zitiRecordService.deletezitiRecordByRecordIds(recordIds));
    }
}
