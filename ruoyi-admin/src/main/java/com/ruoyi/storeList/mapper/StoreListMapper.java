package com.ruoyi.storeList.mapper;

import java.util.List;
import com.ruoyi.storeList.domain.StoreList;

/**
 * 商家列表Mapper接口
 * 
 * @author Syl
 * @date 2024-05-11
 */
public interface StoreListMapper 
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
     * 删除商家列表
     * 
     * @param sId 商家列表主键
     * @return 结果
     */
    public int deleteStoreListBySId(Long sId);

    /**
     * 批量删除商家列表
     * 
     * @param sIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStoreListBySIds(Long[] sIds);
}
