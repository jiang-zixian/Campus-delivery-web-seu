package com.ruoyi.application.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.service.ISysRoleService;
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
 * 商家提交申请Controller
 * 
 * @author xuguowei
 * @date 2024-05-11
 */
@RestController
@RequestMapping("/application/cstore")
public class CstoreController extends BaseController
{
    @Autowired
    private ICstoreService cstoreService;

    @Autowired
    private ISysRoleService roleService;

    /**
     * 查询商家提交申请列表
     */
    
    @GetMapping("/list")
    public TableDataInfo list(Cstore cstore)
    {
        startPage();
        List<Cstore> list = cstoreService.selectCstoreList(cstore);
        return getDataTable(list);
    }

    /**
     * 导出商家提交申请列表
     */
    
    @Log(title = "商家提交申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Cstore cstore)
    {
        List<Cstore> list = cstoreService.selectCstoreList(cstore);
        ExcelUtil<Cstore> util = new ExcelUtil<Cstore>(Cstore.class);
        util.exportExcel(response, list, "商家提交申请数据");
    }

    /**
     * 获取商家提交申请详细信息
     */
    
    @GetMapping(value = "/{cstoreId}")
    public AjaxResult getInfo(@PathVariable("cstoreId") Long cstoreId)
    {
        return success(cstoreService.selectCstoreByCstoreId(cstoreId));
    }

    /**
     * 新增商家提交申请
     */
    
    @Log(title = "商家提交申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Cstore cstore)
    {
        cstore.setuId(getLoginUser().getUserId());
        cstore.setStatus(0L);
        return toAjax(cstoreService.insertCstore(cstore));
    }

    /**
     * 修改商家提交申请
     */
    
    @Log(title = "商家提交申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Cstore cstore)
    {
        return toAjax(cstoreService.updateCstore(cstore));
    }

    /**
     * 删除商家提交申请
     */
    
    @Log(title = "商家提交申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cstoreIds}")
    public AjaxResult remove(@PathVariable Long[] cstoreIds)
    {
        return toAjax(cstoreService.deleteCstoreByCstoreIds(cstoreIds));
    }

    /**
     * 同意商家申请
     */
    @Log(title = "骑手申请", businessType = BusinessType.UPDATE)
    @DeleteMapping("/agree/{criderIds}")
    public AjaxResult agree(@PathVariable Long[] criderIds)
    {
        Long[] uids=cstoreService.selectUIdsByCstoreIds(criderIds);

        for(Long uid:uids){
            System.out.println(uid);
        }

        roleService.insertAuthUsers(101L,uids);

        return toAjax(cstoreService.agreeCstoreByCstoreIds(criderIds));
    }

    /**
     * 拒绝商家申请
     */
    @Log(title = "骑手申请", businessType = BusinessType.UPDATE)
    @DeleteMapping("/refuse/{criderIds}")
    public AjaxResult refuse(@PathVariable Long[] criderIds)
    {
        return toAjax(cstoreService.refuseCstoreByCstoreIds(criderIds));
    }

}
