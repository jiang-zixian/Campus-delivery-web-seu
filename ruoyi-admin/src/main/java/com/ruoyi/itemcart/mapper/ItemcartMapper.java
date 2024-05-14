package com.ruoyi.itemcart.mapper;

import java.util.List;
import com.ruoyi.itemcart.domain.Itemcart;

/**
 * 购物车Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-11
 */
public interface ItemcartMapper 
{
    /**
     * 查询购物车
     * 
     * @param itemcartId 购物车主键
     * @return 购物车
     */
    public Itemcart selectItemcartByItemcartId(Long itemcartId);

    /**
     * 查询购物车列表
     * 
     * @param itemcart 购物车
     * @return 购物车集合
     */
    public List<Itemcart> selectItemcartList(Itemcart itemcart);

    /**
     * 新增购物车
     * 
     * @param itemcart 购物车
     * @return 结果
     */
    public int insertItemcart(Itemcart itemcart);

    /**
     * 修改购物车
     * 
     * @param itemcart 购物车
     * @return 结果
     */
    public int updateItemcart(Itemcart itemcart);

    /**
     * 删除购物车
     * 
     * @param itemcartId 购物车主键
     * @return 结果
     */
    public int deleteItemcartByItemcartId(Long itemcartId);

    /**
     * 批量删除购物车
     * 
     * @param itemcartIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteItemcartByItemcartIds(Long[] itemcartIds);

}
