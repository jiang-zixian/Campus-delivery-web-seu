package com.ruoyi.itemcart.service;

import java.util.List;
import com.ruoyi.itemcart.domain.Itemcart;

/**
 * 购物车Service接口
 * 
 * @author ruoyi
 * @date 2024-05-11
 */
public interface IItemcartService 
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
     * 批量删除购物车
     * 
     * @param itemcartIds 需要删除的购物车主键集合
     * @return 结果
     */
    public int deleteItemcartByItemcartIds(Long[] itemcartIds);

    /**
     * 删除购物车信息
     * 
     * @param itemcartId 购物车主键
     * @return 结果
     */
    public int deleteItemcartByItemcartId(Long itemcartId);

    /**
     * 用当前商户的当前购物车购买物品
     *
     * @param sid 商家id
     * @param itemcartId 购物车id
     * @param type 订单类型（0表示仅跑腿单，1表示商店购买自提单，2表示商店购买跑腿单）
     * @param time 下单时间
     * @param address 目的地址
     * @param estimateTime 预计送达时间
     * @return 结果
     */
    public int buyUseItemcart(long sid,long itemcartId,int type,String time,String address,String estimateTime);
}
