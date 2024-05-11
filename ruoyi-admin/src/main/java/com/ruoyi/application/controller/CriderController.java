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
import com.ruoyi.application.domain.Crider;
import com.ruoyi.application.service.ICriderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.controller.system.SysRoleController;
import com.ruoyi.system.service.ISysRoleService;

/**
 * 骑手申请Controller
 * 
 * @author xuguowei
 * @date 2024-05-04
 */
@RestController
@RequestMapping("/application/crider")
public class CriderController extends BaseController
{
    @Autowired
    private ICriderService criderService;

    @Autowired
    private ISysRoleService roleService;

    /**
     * 查询所有骑手申请列表
     */
    @PreAuthorize("@ss.hasPermi('application:crider:list')")
    @GetMapping("/Allist")
    public TableDataInfo list(Crider crider)
    {
        startPage();
        List<Crider> list = criderService.selectCriderList(crider);
        return getDataTable(list);
    }

    /**
     * 查询当前用户的骑手申请列表
     */
    @GetMapping("/listByUser")
    public TableDataInfo listByUser(Crider crider)
    {
        startPage();
        crider.setuId(getLoginUser().getUserId());
        System.out.println(crider);
        List<Crider> list = criderService.selectCriderList(crider);
        return getDataTable(list);
    }

    /**
     * 导出骑手申请列表
     */
    @PreAuthorize("@ss.hasPermi('application:crider:export')")
    @Log(title = "骑手申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Crider crider)
    {
        List<Crider> list = criderService.selectCriderList(crider);
        ExcelUtil<Crider> util = new ExcelUtil<Crider>(Crider.class);
        util.exportExcel(response, list, "骑手申请数据");
    }

    /**
     * 获取骑手申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('application:crider:query')")
    @GetMapping(value = "/{criderId}")
    public AjaxResult getInfo(@PathVariable("criderId") Long criderId)
    {
        return success(criderService.selectCriderByCriderId(criderId));
    }

    /**
     * 新增骑手申请
     */
    @PreAuthorize("@ss.hasPermi('application:crider:add')")
    @Log(title = "骑手申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Crider crider)
    {
        crider.setuId(getLoginUser().getUserId());
        crider.setPass(0);
        crider.setStatus(0);
        System.out.println(crider);
        return toAjax(criderService.insertCrider(crider));
    }

    /**
     * 修改骑手申请
     */
    @PreAuthorize("@ss.hasPermi('application:crider:edit')")
    @Log(title = "骑手申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Crider crider)
    {
        return toAjax(criderService.updateCrider(crider));
    }

    /**
     * 删除骑手申请
     */
    @PreAuthorize("@ss.hasPermi('application:crider:remove')")
    @Log(title = "骑手申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{criderIds}")
    public AjaxResult remove(@PathVariable Long[] criderIds)
    {
        return toAjax(criderService.deleteCriderByCriderIds(criderIds));
    }

    /**
     * 同意骑手申请
     */
    @Log(title = "骑手申请", businessType = BusinessType.UPDATE)
    @DeleteMapping("/agree/{criderIds}")
    public AjaxResult agree(@PathVariable Long[] criderIds)
    {

       Long[] uids=criderService.selectUIdsByCriderIds(criderIds);

       for(Long uid:uids){
           System.out.println(uid);
       }

       roleService.insertAuthUsers(100L,uids);

        return toAjax(criderService.agreeCriderByCriderIds(criderIds));
    }

    /**
     * 拒绝骑手申请
     */
    @Log(title = "骑手申请", businessType = BusinessType.UPDATE)
    @DeleteMapping("/refuse/{criderIds}")
    public AjaxResult refuse(@PathVariable Long[] criderIds)
    {
        return toAjax(criderService.refuseCriderByCriderIds(criderIds));
    }
}
