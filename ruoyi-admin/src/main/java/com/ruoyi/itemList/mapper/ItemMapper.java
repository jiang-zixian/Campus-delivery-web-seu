package com.ruoyi.itemList.mapper;

import java.util.List;
import com.ruoyi.itemList.domain.Item;

/**
 * 商品列表Mapper接口
 * 
 * @author SYL
 * @date 2024-05-13
 */
public interface ItemMapper 
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
     * 删除商品列表
     * 
     * @param iId 商品列表主键
     * @return 结果
     */
    public int deleteItemByIId(Long iId);

    /**
     * 批量删除商品列表
     * 
     * @param iIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteItemByIIds(Long[] iIds);
}
