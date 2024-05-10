package com.ruoyi.store.service;

import java.util.List;
import com.ruoyi.store.domain.Store;

/**
 * 我的商店Service接口
 * 
 * @author ruoyi
 * @date 2024-05-07
 */
public interface IStoreService 
{
    /**
     * 查询我的商店
     * 
     * @param sId 我的商店主键
     * @return 我的商店
     */
    public Store selectStoreBySId(Long sId);

    /**
     * 查询我的商店列表
     * 
     * @param store 我的商店
     * @return 我的商店集合
     */
    public List<Store> selectStoreList(Store store);

    /**
     * 新增我的商店
     * 
     * @param store 我的商店
     * @return 结果
     */
    public int insertStore(Store store);

    /**
     * 修改我的商店
     * 
     * @param store 我的商店
     * @return 结果
     */
    public int updateStore(Store store);

    /**
     * 批量删除我的商店
     * 
     * @param sIds 需要删除的我的商店主键集合
     * @return 结果
     */
    public int deleteStoreBySIds(Long[] sIds);

    /**
     * 删除我的商店信息
     * 
     * @param sId 我的商店主键
     * @return 结果
     */
    public int deleteStoreBySId(Long sId);
}
