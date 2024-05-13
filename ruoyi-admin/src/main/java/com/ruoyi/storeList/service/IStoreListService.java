package com.ruoyi.storeList.service;

import java.util.List;
import com.ruoyi.storeList.domain.StoreList;

/**
 * 商家列表Service接口
 * 
 * @author Syl
 * @date 2024-05-12
 */
public interface IStoreListService 
{
    /**
     * 查询商家列表
     * 
     * @param sId 商家列表主键
     * @return 商家列表
     */
    public StoreList selectStoreListBySId(Long sId);

    /**
     * 查询商家列表列表
     * 
     * @param storeList 商家列表
     * @return 商家列表集合
     */
    public List<StoreList> selectStoreListList(StoreList storeList);

    /**
     * 新增商家列表
     * 
     * @param storeList 商家列表
     * @return 结果
     */
    public int insertStoreList(StoreList storeList);

    /**
     * 修改商家列表
     * 
     * @param storeList 商家列表
     * @return 结果
     */
    public int updateStoreList(StoreList storeList);

    /**
     * 批量删除商家列表
     * 
     * @param sIds 需要删除的商家列表主键集合
     * @return 结果
     */
    public int deleteStoreListBySIds(Long[] sIds);

    /**
     * 删除商家列表信息
     * 
     * @param sId 商家列表主键
     * @return 结果
     */
    public int deleteStoreListBySId(Long sId);
}
