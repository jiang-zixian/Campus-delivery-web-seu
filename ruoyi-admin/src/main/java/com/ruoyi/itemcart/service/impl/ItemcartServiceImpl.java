package com.ruoyi.itemcart.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.itemcart.mapper.ItemcartMapper;
import com.ruoyi.itemcart.domain.Itemcart;
import com.ruoyi.itemcart.service.IItemcartService;

/**
 * 购物车Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-11
 */
@Service
public class ItemcartServiceImpl implements IItemcartService 
{
    @Autowired
    private ItemcartMapper itemcartMapper;

    /**
     * 查询购物车
     * 
     * @param itemcartId 购物车主键
     * @return 购物车
     */
    @Override
    public Itemcart selectItemcartByItemcartId(Long itemcartId)
    {
        return itemcartMapper.selectItemcartByItemcartId(itemcartId);
    }

    /**
     * 查询购物车列表
     * 
     * @param itemcart 购物车
     * @return 购物车
     */
    @Override
    public List<Itemcart> selectItemcartList(Itemcart itemcart)
    {
        return itemcartMapper.selectItemcartList(itemcart);
    }

    /**
     * 新增购物车
     * 
     * @param itemcart 购物车
     * @return 结果
     */
    @Override
    public int insertItemcart(Itemcart itemcart)
    {
        return itemcartMapper.insertItemcart(itemcart);
    }

    /**
     * 修改购物车
     * 
     * @param itemcart 购物车
     * @return 结果
     */
    @Override
    public int updateItemcart(Itemcart itemcart)
    {
        return itemcartMapper.updateItemcart(itemcart);
    }

    /**
     * 批量删除购物车
     * 
     * @param itemcartIds 需要删除的购物车主键
     * @return 结果
     */
    @Override
    public int deleteItemcartByItemcartIds(Long[] itemcartIds)
    {
        return itemcartMapper.deleteItemcartByItemcartIds(itemcartIds);
    }

    /**
     * 删除购物车信息
     * 
     * @param itemcartId 购物车主键
     * @return 结果
     */
    @Override
    public int deleteItemcartByItemcartId(Long itemcartId)
    {
        return itemcartMapper.deleteItemcartByItemcartId(itemcartId);
    }
}
