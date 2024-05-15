package com.ruoyi.itemList.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.itemList.domain.Item;
import com.ruoyi.itemList.domain.CartForm;
import com.ruoyi.record.domain.Record;

/**
 * 商品列表Service接口
 * 
 * @author SYL
 * @date 2024-05-13
 */
public interface IItemService 
{
    /**
     * 查询商品列表
     * 
     * @param iId 商品列表主键
     * @return 商品列表
     */
    public Item selectItemByIId(Long iId);

    /**
     * 查询商品列表列表
     * 
     * @param item 商品列表
     * @return 商品列表集合
     */
    public List<Item> selectItemList(Item item);

    /**
     * 新增商品列表
     * 
     * @param item 商品列表
     * @return 结果
     */
    public int insertItem(Item item);

    /**
     * 修改商品列表
     * 
     * @param item 商品列表
     * @return 结果
     */
    public int updateItem(Item item);

    /**
     * 批量删除商品列表
     * 
     * @param iIds 需要删除的商品列表主键集合
     * @return 结果
     */
    public int deleteItemByIIds(Long[] iIds);

    /**
     * 删除商品列表信息
     * 
     * @param iId 商品列表主键
     * @return 结果
     */
    public int deleteItemByIId(Long iId);


    /**
     * 检查商品库存是否都满足
     *
     * @param cartForms 商品id和数量
     * @return 结果
     */
    public int isStockSufficient(Map<Long,Integer> cartForms);

    /**
     * 在商店买东西
     *
     * @param record 我的订单
     * @return 结果
     */
    public int buy(Record record);

}
