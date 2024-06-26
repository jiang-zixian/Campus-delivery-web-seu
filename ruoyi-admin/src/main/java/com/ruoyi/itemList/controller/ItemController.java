package com.ruoyi.itemList.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import com.ruoyi.common.core.domain.R;
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
import com.ruoyi.itemList.domain.Item;
import com.ruoyi.itemList.service.IItemService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.record.service.IRecordService;
import com.ruoyi.record.domain.Record;
import com.ruoyi.itemList.domain.CartForm;

/**
 * 商品列表Controller
 * 
 * @author SYL
 * @date 2024-05-13
 */
@RestController
@RequestMapping("/itemList/itemList")
public class ItemController extends BaseController
{
    @Autowired
    private IItemService itemService;

    @Autowired
    private IRecordService iRecordService;

    /**
     * 查询商品列表列表
     */
    
    @GetMapping("/list")
    public TableDataInfo list(Item item)
    {
        startPage();
        List<Item> list = itemService.selectItemList(item);
        return getDataTable(list);
    }

    /**
     * 导出商品列表列表
     */
    
    @Log(title = "商品列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Item item)
    {
        List<Item> list = itemService.selectItemList(item);
        ExcelUtil<Item> util = new ExcelUtil<Item>(Item.class);
        util.exportExcel(response, list, "商品列表数据");
    }

    /**
     * 获取商品列表详细信息
     */
    
    @GetMapping(value = "/{iId}")
    public AjaxResult getInfo(@PathVariable("iId") Long iId)
    {
        return success(itemService.selectItemByIId(iId));
    }

    /**
     * 新增商品列表
     */
    
    @Log(title = "商品列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Item item)
    {
        return toAjax(itemService.insertItem(item));
    }

    /**
     * 修改商品列表
     */
    
    @Log(title = "商品列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Item item)
    {
        return toAjax(itemService.updateItem(item));
    }

    /**
     * 删除商品列表
     */
    
    @Log(title = "商品列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{iIds}")
    public AjaxResult remove(@PathVariable Long[] iIds)
    {
        return toAjax(itemService.deleteItemByIIds(iIds));
    }


    /**
     * 生成外卖订单
     * 1代表自提 2是外卖
     */
    @Log(title = "生成订单",businessType = BusinessType.INSERT)
    @PostMapping("/payallitem")
    public AjaxResult addRecord(@RequestBody Record record)
    {
        record.setuId(getLoginUser().getUserId());
        return toAjax(itemService.buy(record));
    }

    /**
     * 检查商品库存是否满足
     */
    @Log(title = "生成订单",businessType = BusinessType.INSERT)
    @PostMapping("/checkitem")
    public Integer checknum(@RequestBody Map<Long,Integer> cartforms)
    {
        return itemService.isStockSufficient(cartforms);
    }

}
