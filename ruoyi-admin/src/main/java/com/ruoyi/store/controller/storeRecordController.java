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
import com.ruoyi.store.domain.storeRecord;
import com.ruoyi.store.service.IstoreRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商店订单记录Controller
 * 
 * @author xpl
 * @date 2024-05-16
 */
@RestController
@RequestMapping("/store/storeRecord")
public class storeRecordController extends BaseController
{
    @Autowired
    private IstoreRecordService storeRecordService;

    /**
     * 查询商店订单记录列表
     */
    
    @GetMapping("/list")
    public TableDataInfo list(storeRecord storeRecord)
    {
        startPage();
        List<storeRecord> list = storeRecordService.selectstoreRecordList(storeRecord);
        return getDataTable(list);
    }

    /**
     * 导出商店订单记录列表
     */
    
    @Log(title = "商店订单记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, storeRecord storeRecord)
    {
        List<storeRecord> list = storeRecordService.selectstoreRecordList(storeRecord);
        ExcelUtil<storeRecord> util = new ExcelUtil<storeRecord>(storeRecord.class);
        util.exportExcel(response, list, "商店订单记录数据");
    }

    /**
     * 获取商店订单记录详细信息
     */
    
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(storeRecordService.selectstoreRecordByRecordId(recordId));
    }

    /**
     * 新增商店订单记录
     */
    
    @Log(title = "商店订单记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody storeRecord storeRecord)
    {
        return toAjax(storeRecordService.insertstoreRecord(storeRecord));
    }

    /**
     * 修改商店订单记录
     */
    
    @Log(title = "商店订单记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody storeRecord storeRecord)
    {
        return toAjax(storeRecordService.updatestoreRecord(storeRecord));
    }

    /**
     * 删除商店订单记录
     */
    
    @Log(title = "商店订单记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(storeRecordService.deletestoreRecordByRecordIds(recordIds));
    }
}
