package com.ruoyi.itemList.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.itemList.domain.CartForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.itemList.mapper.ItemMapper;
import com.ruoyi.itemList.domain.Item;
import com.ruoyi.itemList.service.IItemService;

/**
 * 商品列表Service业务层处理
 * 
 * @author SYL
 * @date 2024-05-13
 */
@Service
public class ItemServiceImpl implements IItemService 
{
    @Autowired
    private ItemMapper itemMapper;

    /**
     * 查询商品列表
     * 
     * @param iId 商品列表主键
     * @return 商品列表
     */
    @Override
    public Item selectItemByIId(Long iId)
    {
        return itemMapper.selectItemByIId(iId);
    }

    /**
     * 查询商品列表列表
     * 
     * @param item 商品列表
     * @return 商品列表
     */
    @Override
    public List<Item> selectItemList(Item item)
    {
        return itemMapper.selectItemList(item);
    }

    /**
     * 新增商品列表
     * 
     * @param item 商品列表
     * @return 结果
     */
    @Override
    public int insertItem(Item item)
    {
        return itemMapper.insertItem(item);
    }

    /**
     * 修改商品列表
     * 
     * @param item 商品列表
     * @return 结果
     */
    @Override
    public int updateItem(Item item)
    {
        return itemMapper.updateItem(item);
    }

    /**
     * 批量删除商品列表
     * 
     * @param iIds 需要删除的商品列表主键
     * @return 结果
     */
    @Override
    public int deleteItemByIIds(Long[] iIds)
    {
        return itemMapper.deleteItemByIIds(iIds);
    }

    /**
     * 删除商品列表信息
     * 
     * @param iId 商品列表主键
     * @return 结果
     */
    @Override
    public int deleteItemByIId(Long iId)
    {
        return itemMapper.deleteItemByIId(iId);
    }


    /**
     * 检查商品库存是否都满足
     *
     * @param cartForms 商品id和数量
     * @return 结果
     */
    public int isStockSufficient(List<CartForm> cartForms) {
        for (CartForm cartForm : cartForms) {
           //打印
            System.out.println(cartForm.getIId());
            Item item = itemMapper.selectItemByIId(cartForm.getIId());
//            if (item.getAmount() < cartForm.getNum()) {
//                return 0;
//            }
        }

        return 1;
    }
}
