package com.ruoyi.riderecord.controller;

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
import com.ruoyi.riderecord.domain.RideRecord;
import com.ruoyi.riderecord.service.IRideRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 跑腿Controller
 * 
 * @author wj
 * @date 2024-05-13
 */
@RestController
@RequestMapping("/riderecord/riderecord")
public class RideRecordController extends BaseController
{
    @Autowired
    private IRideRecordService rideRecordService;

    /**
     * 查询跑腿列表
     */
    @PreAuthorize("@ss.hasPermi('riderecord:riderecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(RideRecord rideRecord)
    {
        startPage();
        rideRecord.setuId(getLoginUser().getUserId());
        rideRecord.setType(0L);;
        List<RideRecord> list = rideRecordService.selectRideRecordList(rideRecord);
        return getDataTable(list);
    }

    /**
     * 导出跑腿列表
     */
    @PreAuthorize("@ss.hasPermi('riderecord:riderecord:export')")
    @Log(title = "跑腿", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RideRecord rideRecord)
    {
        List<RideRecord> list = rideRecordService.selectRideRecordList(rideRecord);
        ExcelUtil<RideRecord> util = new ExcelUtil<RideRecord>(RideRecord.class);
        util.exportExcel(response, list, "跑腿数据");
    }

    /**
     * 获取跑腿详细信息
     */
    @PreAuthorize("@ss.hasPermi('riderecord:riderecord:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(rideRecordService.selectRideRecordByRecordId(recordId));
    }

    /**
     * 新增跑腿
     */
    @PreAuthorize("@ss.hasPermi('riderecord:riderecord:add')")
    @Log(title = "跑腿", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RideRecord rideRecord)
    {
        return toAjax(rideRecordService.insertRideRecord(rideRecord));
    }

    /**
     * 修改跑腿
     */
    @PreAuthorize("@ss.hasPermi('riderecord:riderecord:edit')")
    @Log(title = "跑腿", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RideRecord rideRecord)
    {
        return toAjax(rideRecordService.updateRideRecord(rideRecord));
    }

    /**
     * 删除跑腿
     */
    @PreAuthorize("@ss.hasPermi('riderecord:riderecord:remove')")
    @Log(title = "跑腿", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(rideRecordService.deleteRideRecordByRecordIds(recordIds));
    }
}
