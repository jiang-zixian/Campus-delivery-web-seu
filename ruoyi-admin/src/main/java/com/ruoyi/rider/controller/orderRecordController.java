package com.ruoyi.rider.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.framework.web.domain.server.Sys;
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
import com.ruoyi.rider.domain.orderRecord;
import com.ruoyi.rider.service.IorderRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我要接单Controller
 * 
 * @author xpl
 * @date 2024-05-17
 */
@RestController
@RequestMapping("/rider/takeOrders")
public class orderRecordController extends BaseController
{
    @Autowired
    private IorderRecordService orderRecordService;

    /**
     * 查询我要接单列表
     */
    
    @GetMapping("/list")
    public TableDataInfo list(orderRecord orderRecord)
    {
        startPage();
        List<orderRecord> list = orderRecordService.selectorderRecordList(orderRecord);
        return getDataTable(list);
    }

    @GetMapping("/test")
    public Long haveOrder()
    {
        Long uId = getLoginUser().getUserId();
        Long recordId = orderRecordService.ifHaveOrder(uId);
        return recordId;
    }



    /**
     * 导出我要接单列表
     */
    
    @Log(title = "我要接单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, orderRecord orderRecord)
    {
        List<orderRecord> list = orderRecordService.selectorderRecordList(orderRecord);
        ExcelUtil<orderRecord> util = new ExcelUtil<orderRecord>(orderRecord.class);
        util.exportExcel(response, list, "我要接单数据");
    }

    /**
     * 获取我要接单详细信息
     */
    
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(orderRecordService.selectorderRecordByRecordId(recordId));
    }

    /**
     * 新增我要接单
     */
    
    @Log(title = "我要接单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody orderRecord orderRecord)
    {
        return toAjax(orderRecordService.insertorderRecord(orderRecord));
    }

    /**
     * 修改我要接单
     */
    
    @Log(title = "我要接单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody orderRecord orderRecord)
    {
        orderRecord.setRiderId(getLoginUser().getUserId());
        return toAjax(orderRecordService.updateorderRecord(orderRecord));
    }

    /**
     * 删除我要接单
     */
    
    @Log(title = "我要接单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(orderRecordService.deleteorderRecordByRecordIds(recordIds));
    }
}
