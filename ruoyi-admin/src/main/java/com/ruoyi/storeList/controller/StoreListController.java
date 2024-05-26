package com.ruoyi.storeList.controller;

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
import com.ruoyi.storeList.domain.StoreList;
import com.ruoyi.storeList.service.IStoreListService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商家列表Controller
 * 
 * @author Syl
 * @date 2024-05-12
 */
@RestController
@RequestMapping("/storeList/storeList")
public class StoreListController extends BaseController
{
    @Autowired
    private IStoreListService storeListService;

    /**
     * 查询商家列表列表
     */
    
    @GetMapping("/list")
    public TableDataInfo list(StoreList storeList)
    {
        startPage();
        List<StoreList> list = storeListService.selectStoreListList(storeList);
        return getDataTable(list);
    }

    /**
     * 导出商家列表列表
     */
    
    @Log(title = "商家列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StoreList storeList)
    {
        List<StoreList> list = storeListService.selectStoreListList(storeList);
        ExcelUtil<StoreList> util = new ExcelUtil<StoreList>(StoreList.class);
        util.exportExcel(response, list, "商家列表数据");
    }

    /**
     * 获取商家列表详细信息
     */
    
    @GetMapping(value = "/{sId}")
    public AjaxResult getInfo(@PathVariable("sId") Long sId)
    {
        return success(storeListService.selectStoreListBySId(sId));
    }

    /**
     * 新增商家列表
     */
    
    @Log(title = "商家列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StoreList storeList)
    {
        return toAjax(storeListService.insertStoreList(storeList));
    }

    /**
     * 修改商家列表
     */
    
    @Log(title = "商家列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StoreList storeList)
    {
        return toAjax(storeListService.updateStoreList(storeList));
    }

    /**
     * 删除商家列表
     */
    
    @Log(title = "商家列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sIds}")
    public AjaxResult remove(@PathVariable Long[] sIds)
    {
        return toAjax(storeListService.deleteStoreListBySIds(sIds));
    }
}
