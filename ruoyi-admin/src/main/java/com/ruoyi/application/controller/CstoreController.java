package com.ruoyi.application.controller;

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
import com.ruoyi.application.domain.Cstore;
import com.ruoyi.application.service.ICstoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商家申请Controller
 * 
 * @author ruoyi
 * @date 2024-05-05
 */
@RestController
@RequestMapping("/application/cstore")
public class CstoreController extends BaseController
{
    @Autowired
    private ICstoreService cstoreService;

    /**
     * 查询商家申请列表
     */
    @PreAuthorize("@ss.hasPermi('application:cstore:list')")
    @GetMapping("/list")
    public TableDataInfo list(Cstore cstore)
    {
        startPage();
        List<Cstore> list = cstoreService.selectCstoreList(cstore);
        return getDataTable(list);
    }

    /**
     * 导出商家申请列表
     */
    @PreAuthorize("@ss.hasPermi('application:cstore:export')")
    @Log(title = "商家申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Cstore cstore)
    {
        List<Cstore> list = cstoreService.selectCstoreList(cstore);
        ExcelUtil<Cstore> util = new ExcelUtil<Cstore>(Cstore.class);
        util.exportExcel(response, list, "商家申请数据");
    }

    /**
     * 获取商家申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('application:cstore:query')")
    @GetMapping(value = "/{cstoreId}")
    public AjaxResult getInfo(@PathVariable("cstoreId") Long cstoreId)
    {
        return success(cstoreService.selectCstoreByCstoreId(cstoreId));
    }

    /**
     * 新增商家申请
     */
    @PreAuthorize("@ss.hasPermi('application:cstore:add')")
    @Log(title = "商家申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Cstore cstore)
    {
        return toAjax(cstoreService.insertCstore(cstore));
    }

    /**
     * 修改商家申请
     */
    @PreAuthorize("@ss.hasPermi('application:cstore:edit')")
    @Log(title = "商家申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Cstore cstore)
    {
        return toAjax(cstoreService.updateCstore(cstore));
    }

    /**
     * 删除商家申请
     */
    @PreAuthorize("@ss.hasPermi('application:cstore:remove')")
    @Log(title = "商家申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cstoreIds}")
    public AjaxResult remove(@PathVariable Long[] cstoreIds)
    {
        return toAjax(cstoreService.deleteCstoreByCstoreIds(cstoreIds));
    }
}
