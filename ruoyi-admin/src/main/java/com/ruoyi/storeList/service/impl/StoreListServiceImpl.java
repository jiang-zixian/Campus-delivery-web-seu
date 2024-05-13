package com.ruoyi.storeList.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.storeList.mapper.StoreListMapper;
import com.ruoyi.storeList.domain.StoreList;
import com.ruoyi.storeList.service.IStoreListService;

/**
 * 商家列表Service业务层处理
 * 
 * @author Syl
 * @date 2024-05-12
 */
@Service
public class StoreListServiceImpl implements IStoreListService 
{
    @Autowired
    private StoreListMapper storeListMapper;

    /**
     * 查询商家列表
     * 
     * @param sId 商家列表主键
     * @return 商家列表
     */
    @Override
    public StoreList selectStoreListBySId(Long sId)
    {
        return storeListMapper.selectStoreListBySId(sId);
    }

    /**
     * 查询商家列表列表
     * 
     * @param storeList 商家列表
     * @return 商家列表
     */
    @Override
    public List<StoreList> selectStoreListList(StoreList storeList)
    {
        return storeListMapper.selectStoreListList(storeList);
    }

    /**
     * 新增商家列表
     * 
     * @param storeList 商家列表
     * @return 结果
     */
    @Override
    public int insertStoreList(StoreList storeList)
    {
        return storeListMapper.insertStoreList(storeList);
    }

    /**
     * 修改商家列表
     * 
     * @param storeList 商家列表
     * @return 结果
     */
    @Override
    public int updateStoreList(StoreList storeList)
    {
        return storeListMapper.updateStoreList(storeList);
    }

    /**
     * 批量删除商家列表
     * 
     * @param sIds 需要删除的商家列表主键
     * @return 结果
     */
    @Override
    public int deleteStoreListBySIds(Long[] sIds)
    {
        return storeListMapper.deleteStoreListBySIds(sIds);
    }

    /**
     * 删除商家列表信息
     * 
     * @param sId 商家列表主键
     * @return 结果
     */
    @Override
    public int deleteStoreListBySId(Long sId)
    {
        return storeListMapper.deleteStoreListBySId(sId);
    }
}
