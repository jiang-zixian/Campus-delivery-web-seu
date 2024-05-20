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
import com.ruoyi.store.domain.myStore;
import com.ruoyi.store.service.ImyStoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我的商店Controller
 * 
 * @author xpl
 * @date 2024-05-13
 */
@RestController
@RequestMapping("/store/myStore")
public class myStoreController extends BaseController
{
    @Autowired
    private ImyStoreService myStoreService;

    /**
     * 查询我的商店列表
     */
    @PreAuthorize("@ss.hasPermi('store:myStore:list')")
    @GetMapping("/list")
    public TableDataInfo list(myStore myStore)
    {
        startPage();
        myStore.setuId(getLoginUser().getUserId());
        List<myStore> list = myStoreService.selectmyStoreList(myStore);
        return getDataTable(list);
    }

    /**
     * 导出我的商店列表
     */
    @PreAuthorize("@ss.hasPermi('store:myStore:export')")
    @Log(title = "我的商店", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, myStore myStore)
    {
        List<myStore> list = myStoreService.selectmyStoreList(myStore);
        ExcelUtil<myStore> util = new ExcelUtil<myStore>(myStore.class);
        util.exportExcel(response, list, "我的商店数据");
    }

    /**
     * 获取我的商店详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:myStore:query')")
    @GetMapping(value = "/{sId}")
    public AjaxResult getInfo(@PathVariable("sId") Long sId)
    {
        return success(myStoreService.selectmyStoreBySId(sId));
    }

    /**
     * 新增我的商店
     */
    @PreAuthorize("@ss.hasPermi('store:myStore:add')")
    @Log(title = "我的商店", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody myStore myStore)
    {
        myStore.setuId(getLoginUser().getUserId());
        return toAjax(myStoreService.insertmyStore(myStore));
    }

    /**
     * 修改我的商店
     */
    @PreAuthorize("@ss.hasPermi('store:myStore:edit')")
    @Log(title = "我的商店", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody myStore myStore)
    {
        return toAjax(myStoreService.updatemyStore(myStore));
    }

    /**
     * 删除我的商店
     */
    @PreAuthorize("@ss.hasPermi('store:myStore:remove')")
    @Log(title = "我的商店", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sIds}")
    public AjaxResult remove(@PathVariable Long[] sIds)
    {
        return toAjax(myStoreService.deletemyStoreBySIds(sIds));
    }
}
