package com.ruoyi.store.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.store.mapper.StoreMapper;
import com.ruoyi.store.domain.Store;
import com.ruoyi.store.service.IStoreService;

/**
 * 我的商店Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-07
 */
@Service
public class StoreServiceImpl implements IStoreService 
{
    @Autowired
    private StoreMapper storeMapper;

    /**
     * 查询我的商店
     * 
     * @param sId 我的商店主键
     * @return 我的商店
     */
    @Override
    public Store selectStoreBySId(Long sId)
    {
        return storeMapper.selectStoreBySId(sId);
    }

    /**
     * 查询我的商店列表
     * 
     * @param store 我的商店
     * @return 我的商店
     */
    @Override
    public List<Store> selectStoreList(Store store)
    {
        return storeMapper.selectStoreList(store);
    }

    /**
     * 新增我的商店
     * 
     * @param store 我的商店
     * @return 结果
     */
    @Override
    public int insertStore(Store store)
    {
        return storeMapper.insertStore(store);
    }

    /**
     * 修改我的商店
     * 
     * @param store 我的商店
     * @return 结果
     */
    @Override
    public int updateStore(Store store)
    {
        return storeMapper.updateStore(store);
    }

    /**
     * 批量删除我的商店
     * 
     * @param sIds 需要删除的我的商店主键
     * @return 结果
     */
    @Override
    public int deleteStoreBySIds(Long[] sIds)
    {
        return storeMapper.deleteStoreBySIds(sIds);
    }

    /**
     * 删除我的商店信息
     * 
     * @param sId 我的商店主键
     * @return 结果
     */
    @Override
    public int deleteStoreBySId(Long sId)
    {
        return storeMapper.deleteStoreBySId(sId);
    }
}
