package com.ruoyi.itemcart.controller;

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
import com.ruoyi.itemcart.domain.Itemcart;
import com.ruoyi.itemcart.service.IItemcartService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 购物车Controller
 * 
 * @author ruoyi
 * @date 2024-05-11
 */
@RestController
@RequestMapping("/itemcart/itemcart")
public class ItemcartController extends BaseController
{
    @Autowired
    private IItemcartService itemcartService;

    /**
     * 查询购物车列表
     */
    @PreAuthorize("@ss.hasPermi('itemcart:itemcart:list')")
    @GetMapping("/list")
    public TableDataInfo list(Itemcart itemcart)
    {
        startPage();
        List<Itemcart> list = itemcartService.selectItemcartList(itemcart);
        return getDataTable(list);
    }

    /**
     * 导出购物车列表
     */
    @PreAuthorize("@ss.hasPermi('itemcart:itemcart:export')")
    @Log(title = "购物车", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Itemcart itemcart)
    {
        List<Itemcart> list = itemcartService.selectItemcartList(itemcart);
        ExcelUtil<Itemcart> util = new ExcelUtil<Itemcart>(Itemcart.class);
        util.exportExcel(response, list, "购物车数据");
    }

    /**
     * 获取购物车详细信息
     */
    @PreAuthorize("@ss.hasPermi('itemcart:itemcart:query')")
    @GetMapping(value = "/{itemcartId}")
    public AjaxResult getInfo(@PathVariable("itemcartId") Long itemcartId)
    {
        return success(itemcartService.selectItemcartByItemcartId(itemcartId));
    }

    /**
     * 新增购物车
     */
    @PreAuthorize("@ss.hasPermi('itemcart:itemcart:add')")
    @Log(title = "购物车", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Itemcart itemcart)
    {
        return toAjax(itemcartService.insertItemcart(itemcart));
    }

    /**
     * 修改购物车
     */
    @PreAuthorize("@ss.hasPermi('itemcart:itemcart:edit')")
    @Log(title = "购物车", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Itemcart itemcart)
    {
        return toAjax(itemcartService.updateItemcart(itemcart));
    }

    /**
     * 删除购物车
     */
    @PreAuthorize("@ss.hasPermi('itemcart:itemcart:remove')")
    @Log(title = "购物车", businessType = BusinessType.DELETE)
	@DeleteMapping("/{itemcartIds}")
    public AjaxResult remove(@PathVariable Long[] itemcartIds)
    {
        return toAjax(itemcartService.deleteItemcartByItemcartIds(itemcartIds));
    }

    /**
     * 用当前商户的当前购物车购买物品
     * 后端需要的参数：商家id，购物车id，订单类型（0表示仅跑腿单，1表示商店购买自提单，2表示商店购买跑腿单），下单时间，目的地址，预计送达时间
     */
    @PreAuthorize("@ss.hasPermi('itemcart:itemcart:buy')")
    @Log(title = "购物车", businessType = BusinessType.BUY)
    @PostMapping("/buy/{itemcartId})")
    //后端需要的参数：商家id，购物车id，订单类型（0表示仅跑腿单，1表示商店购买自提单，2表示商店购买跑腿单），下单时间，目的地址，预计送达时间
    public AjaxResult buyUseItemcart(@RequestBody long sid,long itemcartId,int type,String time,String address,String estimateTime)
    {
        return toAjax(itemcartService.buyUseItemcart(sid,itemcartId,type,time,address,estimateTime));
    }
}
